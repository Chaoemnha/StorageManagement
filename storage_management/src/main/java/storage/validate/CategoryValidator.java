package storage.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import storage.model.Category;
import storage.service.ProductService;

public class CategoryValidator implements Validator{
	@Autowired
	private ProductService productService;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == Category.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Category category = (Category) target;
		ValidationUtils.rejectIfEmpty(errors, "code", "msg.required");
		ValidationUtils.rejectIfEmpty(errors, "name", "msg.required");
		ValidationUtils.rejectIfEmpty(errors, "description", "msg.required");
		if(category.getCode()!=null) {
		List<Category> result =  productService.findCategory("code", category.getCode());
		if(result!=null && !result.isEmpty()) {
			errors.rejectValue("code", "msg.code.exists");
		}
		}
	}

}
