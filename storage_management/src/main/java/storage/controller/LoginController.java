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

import storage.dto.AuthDTO;
import storage.dto.RoleDTO;
import storage.dto.UserRoleDTO;
import storage.model.Menu;
import storage.model.Role;
import storage.model.User;
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
	
	@PostMapping("/processLogin")
	public String processRegister(@ModelAttribute("registerForm") @Validated User user, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            model.addAttribute("registerForm", user);
            model.addAttribute("loginForm", new User());
        	return "login/login";
        }
        // Tạo URL Gravatar từ email
        // Lưu user mới
        String avatar = HashEmail.sha256Hex(user.getEmail());
        System.out.println("####Avatar: " + avatar);
        User userSaved = new User(user.getUserName(), user.getPassword(), user.getEmail(), user.getName(), avatar,1,1);
        userService.save(userSaved);
        List<Role> role = roleService.findByProperty("id", 1);
        if(!role.isEmpty()) {
        return "redirect:/login#signin";
        }
        return "login/login";
    }

	@PostMapping("/login")
	public String processLogin(Model model, @ModelAttribute("loginForm")@Validated User user, BindingResult result, HttpSession session) {
		//BindingRes la ket qua tra ve, httpSes giong SessionStorage
		if(result.hasErrors()) {
		    model.addAttribute("registerForm", new User());
		    model.addAttribute("loginForm", user);return "login/login";}
		//Sinh menu dong
		List<User> uzer = userService.findByProperty("userName", user.getUserName());
		if(!uzer.isEmpty()) {
		List<UserRoleDTO> userRole = userRoleService.findUserRoleDTOsByUserId(uzer.get(0).getId());
		List<Menu> menuList = new ArrayList<Menu>();
		RoleDTO role = roleService.findRoleWithRoleId(userRole.get(0).getRole());
		List<Menu> menuChildList = new ArrayList<Menu>();
		//Lay menu theo role id, tim menu cha truoc (co parent id =0)
		for(AuthDTO obj : role.getAuths()) {
			Menu menu = obj.getMenu();
			if(menu.getParentId() ==0 && menu.getOrderIndex()!=-1&&menu.getActiveFlag()==1&&obj.getPermission()==1&&obj.getActiveFlag()==1) {
				menu.setIdMenu(menu.getUrl().replace("/", "")+"Id"); //vd catogory/list -> categorylistId
				menuList.add(menu);
			}
			else if(menu.getParentId() !=0 && menu.getOrderIndex()!=-1&&menu.getActiveFlag()==1&&obj.getPermission()==1&&obj.getActiveFlag()==1) {
				menu.setIdMenu(menu.getUrl().replace("/", "")+"Id"); //vd catogory/list -> categorylistId
				menuChildList.add(menu);
		}
		}
		for(Menu men: menuList) {
			List<Menu> childList = new ArrayList<Menu>();
			for(Menu childMenu:menuChildList) {
				if(childMenu.getParentId()==men.getId())
					childList.add(childMenu);
			}
			men.setChild(childList);
		}
		sortMenu(menuList);
		for(Menu men: menuList)
			sortMenu(men.getChild());

		session.setAttribute(Constant.USER_INFO, uzer.get(0));
		session.setAttribute(Constant.MENU_SESSION, menuList);
		return "redirect:/index";
		}
	    model.addAttribute("registerForm", new User());
	    model.addAttribute("loginForm", user);
		return "login/login";
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
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}
	
	@GetMapping("/logout")
	public String llogout(HttpSession session) {
		session.removeAttribute(Constant.MENU_SESSION);
		session.removeAttribute(Constant.USER_INFO);
				return "redirect:/login";
	}
	
}
