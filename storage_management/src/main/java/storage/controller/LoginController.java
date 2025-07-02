package storage.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import storage.model.Auth;
import storage.model.Menu;
import storage.model.Role;
import storage.model.User;
import storage.model.UserRole;
import storage.service.RoleService;
import storage.service.UserRoleService;
import storage.service.UserService;
import storage.util.Constant;
import storage.util.HashEmail;
import storage.validate.LoginValidator;
import storage.validate.RegisterValidator;

@Controller
public class LoginController {
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private LoginValidator loginValidator;
	@Autowired
	private RegisterValidator registerValidator;
	@InitBinder("loginForm")
	protected void initLoginBinder(WebDataBinder binder) {
	    binder.setValidator(loginValidator);
	}

	@InitBinder("registerForm")
	protected void initRegisterBinder(WebDataBinder binder) {
	    binder.setValidator(registerValidator);
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		//Set cac truong trong loginForm la thuoc tinh cua User
		model.addAttribute("loginForm", new User());
		model.addAttribute("registerForm", new User());
		//folder ten la login co file la login.jsp
		//Spring se tim trong duong dan cua order 0, 1, ko thay thi in loi ko thay file
		return "login/login";
	}
	
	@PutMapping("/login")
	public String processRegister(@ModelAttribute("registerForm") @Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/login#signup";
        }
        // Tạo URL Gravatar từ email
        // Lưu user mới
        String avatar = HashEmail.sha256Hex(user.getEmail());
        System.out.println("####Avatar: " + avatar);
        User userSaved = userService.save(new User(user.getUserName(), user.getPassword(), user.getEmail(), user.getName(), avatar,1));
        userRoleService.save(new UserRole(userSaved, roleService.findByProperty("id", 2).get(0), 1));
        return "redirect:/login#signin";
    }

	@PostMapping("/login")
	public String processLogin(Model model, @ModelAttribute("loginForm")@Validated User user, BindingResult result, HttpSession session) {
		//BindingRes la ket qua tra ve, httpSes giong SessionStorage
		if(result.hasErrors()) return "redirect:/login#signin";
		//Sinh menu dong
		User uzer = userService.findByProperty("userName", user.getUserName()).get(0);
		UserRole userRole = userRoleService.findByProperty("user", uzer).get(0);
		List<Menu> menuList = new ArrayList<Menu>();
		Role role = userRole.getRole();
		List<Menu> menuChildList = new ArrayList<Menu>();
		//Lay menu theo role id, tim menu cha truoc (co parent id =0)
		for(Object obj : role.getAuths()) {
			Auth auth = (Auth) obj;
			Menu menu = auth.getMenu();
			if(menu.getParentId() ==0 && menu.getOrderIndex()!=-1&&menu.getActiveFlag()==1&&auth.getPermission()==1&&auth.getActiveFlag()==1) {
				menu.setIdMenu(menu.getUrl().replace("/", "")+"Id"); //vd catogory/list -> categorylistId
				menuList.add(menu);
			}
			else if(menu.getParentId() !=0 && menu.getOrderIndex()!=-1&&menu.getActiveFlag()==1&&auth.getPermission()==1&&auth.getActiveFlag()==1) {
				menu.setIdMenu(menu.getUrl().replace("/", "")+"Id"); //vd catogory/list -> categorylistId
				menuChildList.add(menu);
			}
		}
		for(Menu menu: menuList) {
			List<Menu> childList = new ArrayList<Menu>();
			for(Menu childMenu:menuChildList) {
				if(childMenu.getParentId()==menu.getId())
					childList.add(childMenu);
			}
			menu.setChild(childList);
		}
		sortMenu(menuList);
		for(Menu menu: menuList)
			sortMenu(menu.getChild());

		session.setAttribute(Constant.USER_INFO, uzer);
		session.setAttribute(Constant.MENU_SESSION, menuList);
		return "redirect:/index";
	}

	//Ham sap xep menu theo order
	public void sortMenu(List<Menu> menus) {
		Collections.sort(menus, new Comparator<Menu>() {
			@Override
			public int compare(Menu o1, Menu o2) {
				return o1.getOrderIndex()-o2.getOrderIndex();
			}
		});
	}
	//Ongoing
	@GetMapping("/upadmin")
	public String UpAdmin() {
		return "redirect:/login";
	}
	
}
