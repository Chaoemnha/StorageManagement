package storage.validate;

import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import storage.model.ProductInfo;
import storage.service.ProductService;
@Component
public class ProductInfoValidator implements Validator{
	@Autowired
	private ProductService productService;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == ProductInfo.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ProductInfo productInfo = (ProductInfo) target;
		ValidationUtils.rejectIfEmpty(errors, "code", "msg.required");
		ValidationUtils.rejectIfEmpty(errors, "name", "msg.required");
		ValidationUtils.rejectIfEmpty(errors, "description", "msg.required");
		ValidationUtils.rejectIfEmpty(errors, "multipartFile", "msg.required");
		if(productInfo.getCode()!=null) {
		List<ProductInfo> result =  productService.findProductInfo("code", productInfo.getCode());
		if(productInfo.getId()!=null && productInfo.getId()!=0)
			if(result.get(0).getId()!=productInfo.getId())
				errors.rejectValue("code", "msg.code.exists");
		if(productInfo.getId()==null)
			if(!result.isEmpty()) errors.rejectValue("code", "msg.code.exists");
		}
		if(productInfo.getMultipartFile()!=null) {
			String extension = FilenameUtils.getExtension(productInfo.getMultipartFile().getOriginalFilename());
			if(!extension.equals("jpg")||!extension.equals("png")) {
				errors.rejectValue("multipartFile", "msg.file.extension.error");
			}
		}
	}

}
