package storage.controller;

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

import storage.model.User;
import storage.service.UserService;
import storage.util.Constant;
import storage.validate.LoginValidator;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private LoginValidator loginValidator;
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		if(binder.getTarget()==null) return ;
		if(binder.getTarget().getClass()==User.class) {
			binder.setValidator(loginValidator);
		}
	}
	@GetMapping("/login")
	public String login(Model model) {
		//Set cac truong trong loginForm la thuoc tinh cua User
		model.addAttribute("loginForm", new User());
		//folder ten la login co file la login.jsp
		//Spring se tim trong duong dan cua order 0, 1, ko thay thi in loi ko thay file
		return "login/login";
	}
	@PostMapping("/processLogin")
	public String processLogin(Model model, @ModelAttribute("loginForm")@Validated User user, BindingResult result, HttpSession session) {
		//BindingRes la ket qua tra ve, httpSes giong SessionStorage
		if(result.hasErrors()) return "login/login";
		List<User> uzer = userService.findByProperty("userName", user.getUserName());
		if(!uzer.isEmpty())
		session.setAttribute(Constant.USER_INFO, uzer.get(0));
		else {model.addAttribute("error", "Không tìm thấy người dùng");return "login/login";}
		return "redirect:/index";
	}
}
