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
@Component
public class RegisterValidator implements Validator{
	@Autowired
	private UserService userService;
	public boolean supports(Class<?> clazz) {
		return clazz == User.class;
	}
	
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "msg.required");
		if(StringUtils.hasLength(user.getUserName())&& StringUtils.hasLength(user.getPassword())&&StringUtils.hasLength(user.getEmail())) {
			List<User> users = userService.findByProperty("userName", user.getUserName());
			List<User> userz = userService.findByProperty("email", user.getEmail());
			if(users!=null && !users.isEmpty()) {
				errors.rejectValue("userName", "msg.username.exists");
			}
				else if(userz!=null && !userz.isEmpty()) {
					errors.rejectValue("email", "msg.email.exists");
				}
		}
	}
}
