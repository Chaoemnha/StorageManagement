package storage.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import storage.model.Paging;
import storage.model.Role;
import storage.model.Auth;
import storage.model.Menu;
import storage.model.AuthForm;
import storage.service.MenuService;
import storage.service.RoleService;
import storage.util.Constant;

@Controller
public class MenuController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	@RequestMapping(value={"/menu/list","/menu/list/"})
	public String redirect() {
		return "redirect:/menu/list/1";
	}
	@RequestMapping("/menu/list/{page}")
	public String menuList(Model model, @PathVariable("page") int page, @ModelAttribute("searchForm") Menu menu) {
		Paging paging = new Paging(15);
		paging.setIndexPage(page);
		List<Menu> menus = menuService.getListMenu(paging, menu);
		List<Role> roles = roleService.getRoleList(null, null);
		//Sap xep de forEach khong bi lon xon
		Collections.sort(roles, (o1,o2)->o1.getId()-o2.getId());
		for(Menu menu2: menus) {
			//HashMap thi luu khong theo thu tu con treemap ho tro luu theo thu tu
			Map<Integer, Integer> mapAuth = new TreeMap<Integer, Integer>();
			//Khoi tao vong for nay truoc de khoi tao gia tri
			for(Role role:roles) {
				mapAuth.put(role.getId(), 0);//1-0 2-0 3-0
			}
			//Day cac gia tri tu Auth vao
			for(Object obj: menu2.getAuths()) {
				Auth auth = (Auth) obj;
				mapAuth.put(auth.getRole().getId(), auth.getPermission());//1-1, 2-0, 3-0
			}
			//Trong TH Auth chung ta chua set
			menu2.setMapAuth(mapAuth);
		}
		model.addAttribute("menuList",menus);
		model.addAttribute("roles",roles);
		model.addAttribute("pageInfo", paging);
		return "menu-list";
	}
	
	@GetMapping("/menu/change-status/{id}")
	public String change(Model model, @PathVariable("id") int id, HttpSession httpSession) {
		try {
			menuService.changeStatus(id);
			httpSession.setAttribute(Constant.MSG_SUCCESS, "Change status success!!!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			httpSession.setAttribute(Constant.MSG_ERROR, "Change status has error!!!");
		}
		return "redirect:/menu/list";
	}
	
	@GetMapping("/menu/permission")
	public String permission(Model model) {
		model.addAttribute("modelForm", new AuthForm());
		initSelectbox(model);
		return "menu-action";
	}
	
	@PostMapping("/menu/update-permission")
	public String updatePermission(Model model, HttpSession httpSession, @ModelAttribute("modelForm") AuthForm authForm) {
		try {
			menuService.updatePermission(authForm);
			httpSession.setAttribute(Constant.MSG_SUCCESS, "Update success!!!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			httpSession.setAttribute(Constant.MSG_ERROR, "Update error!!!");
		}
		return "redirect:/menu/list";
	}
	
	private void initSelectbox(Model model) {
		List<Role> roles = roleService.getRoleList(null, null);
		List<Menu> menus = menuService.getListMenu(null, null);
		Map<Integer, String> mapRole = new HashMap<Integer, String>();
		Map<Integer, String> mapMenu = new HashMap<Integer, String>();
		for(Role role:roles) {
			mapRole.put(role.getId(), role.getRoleName());
		}
		for(Menu menu:menus) {
			mapMenu.put(menu.getId(), menu.getUrl());
		}
		model.addAttribute("mapRole", mapRole);
		model.addAttribute("mapMenu", mapMenu);
	}
}
