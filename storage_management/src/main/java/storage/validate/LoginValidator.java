package storage.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import storage.model.User;
import storage.service.UserService;
import storage.util.HashingPassword;
@Component
public class LoginValidator implements Validator{
	@Autowired
	private UserService userService;
	public boolean supports(Class<?> clazz) {
		return clazz == User.class;
	}
	
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "msg.required");
		ValidationUtils.rejectIfEmpty(errors, "password", "msg.required");
		if(StringUtils.hasLength(user.getUserName())&& StringUtils.hasLength(user.getPassword())) {
			List<User> users = userService.findByProperty("userName", user.getUserName());
			if(users!=null && !users.isEmpty()) {
				try {
					if(!HashingPassword.decrypt(users.get(0).getPassword()).equals(user.getPassword())) {
						errors.rejectValue("password", "msg.wrong.password");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				else {
					errors.rejectValue("userName", "msg.wrong.username");
				}
		}
	}
}
