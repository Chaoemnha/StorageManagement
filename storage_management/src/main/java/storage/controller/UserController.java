package storage.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import storage.dto.UserRoleDTO;
import storage.model.Paging;
import storage.model.Role;
import storage.model.User;
import storage.model.UserRole;
import storage.service.RoleService;
import storage.service.UserRoleService;
import storage.service.UserService;
import storage.util.Constant;
import storage.util.HashEmail;
import storage.validate.RegisterValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RegisterValidator userValidator;
	@Autowired
	private UserRoleService userRoleService;
	static final Logger log = Logger.getLogger(UserController.class);

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		if (binder.getTarget() == null) {
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		if (binder.getTarget().getClass() == User.class) {
			binder.setValidator(userValidator);
		}
	}

	@RequestMapping(value = { "/user/list", "/user/list/" })
	public String redirect() {
		return "redirect:/user/list/1";
	}

	@RequestMapping(value = "/user/list/{page}")
	public String showUserList(Model model, HttpSession session, @ModelAttribute("searchForm") User user,
			@PathVariable("page") int page) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		List<User> users = userService.getUsersList(user, paging);
		if (session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		if (session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		model.addAttribute("pageInfo", paging);
		model.addAttribute("users", users);
		return "user-list";
	}

	@GetMapping("/user/add")
	public String add(Model model) {
		model.addAttribute("titlePage", "Add User");
		model.addAttribute("modelForm", new User());
		List<Role> roles = roleService.getRoleList(null, null);
		Map<String, String> mapRole = new HashMap<String, String>();
		for (Role role : roles) {
			mapRole.put(String.valueOf(role.getId()), role.getRoleName());
		}
		model.addAttribute("mapRole", mapRole);
		model.addAttribute("viewOnly", false);
		return "user-action";
	}

	@GetMapping("/user/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		log.info("Edit user with id=" + id);
		List<User> results = userService.findByProperty("id", id);
		if (results != null && !results.isEmpty()) {
			User user = results.get(0);
			List<Role> roles = roleService.getRoleList(null, null);
			Map<String, String> mapRole = new HashMap<>();
			for (Role role : roles) {
				mapRole.put(String.valueOf(role.getId()), role.getRoleName());
			}
			UserRole userRole = (UserRole) user.getUserRoles().iterator().next();
			user.setRoleId(userRole.getRole().getId());
			model.addAttribute("mapRole", mapRole);
			model.addAttribute("titlePage", "Edit User");
			model.addAttribute("modelForm", user);
			model.addAttribute("viewOnly", false);
			return "user-action";
		}
		return "redirect:/user/list";
	}

	@GetMapping("/user/view/{id}")
	public String view(Model model, @PathVariable("id") int id) {
		log.info("View user with id=" + id);
		List<User> results = userService.findByProperty("id", id);
		if (results != null && !results.isEmpty()) {
			User user = results.get(0);
			List<UserRoleDTO> userRoles = userRoleService.findUserRoleDTOsByUserId(user.getId());
			if(!userRoles.isEmpty()) {
				Set<UserRole> userRoles2 = new HashSet<UserRole>();
				userRoles2.add(new UserRole(user, new Role(userRoles.get(0).getRoleId(),userRoles.get(0).getRoleName(), "", 1),1));
				user.setUserRoles(userRoles2);
			}
			Map<String, String> mapRole = new HashMap<>();
			model.addAttribute("mapRole", mapRole);
			model.addAttribute("titlePage", "View User");
			model.addAttribute("modelForm", user);
			model.addAttribute("viewOnly", true);
			return "user-action";
		}
		return "redirect:/user/list";
	}

	@PostMapping("/user/save")
	public String save(Model model, @ModelAttribute("modelForm") @Validated User user, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			if (user.getId() != null) {
				model.addAttribute("titlePage", "Edit User");
			} else {
				model.addAttribute("titlePage", "Add User");
			}
			List<Role> roles = roleService.getRoleList(null, null);
			Map<String, String> mapRole = new HashMap<>();
			for (Role role : roles) {
				mapRole.put(String.valueOf(role.getId()), role.getRoleName());
			}
			model.addAttribute("mapRole", mapRole);
			model.addAttribute("modelForm", user);
			model.addAttribute("viewOnly", false);
			return "user-action";
		}
		// UserRole userRole = (UserRole) user.getUserRoles().iterator().next();
		if (user.getId() != null && user.getId() != 0) {
			try {
				List<User> users = userService.findByProperty("email", user.getEmail());
				if(users.isEmpty())
					user.setAvatar(HashEmail.sha256Hex(user.getEmail()));
				userService.update(user);
				session.setAttribute(Constant.MSG_SUCCESS, "Update success!!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage());
				session.setAttribute(Constant.MSG_ERROR, "Update has error");
			}
		} else {
			try {
				List<User> users = userService.findByProperty("email", user.getEmail());
				if(users.isEmpty())
					user.setAvatar(HashEmail.sha256Hex(user.getEmail()));
				userService.save(user);
				session.setAttribute(Constant.MSG_SUCCESS, "Insert success!!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Insert has error!!!");
			}
		}
		return "redirect:/user/list";
	}

	@GetMapping("/user/delete/{id}")
	public String delete(Model model, @PathVariable("id") int id, HttpSession session) {
		log.info("Delete user with id=" + id);
		List<User> results = userService.findByProperty("id", id);
		if (results != null && !results.isEmpty()) {
			User user = results.get(0);
			try {
				userService.deleteUser(user);
				session.setAttribute(Constant.MSG_SUCCESS, "Delete success!!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Delete has error!!!");
			}
		}
		return "redirect:/user/list";
	}
}
