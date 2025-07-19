package storage.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import storage.model.ProductInfo;
import storage.model.Category;
import storage.model.Paging;
import storage.service.ProductService;
import storage.util.Constant;
import storage.validate.ProductInfoValidator;

@Controller
public class ProductInfoController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductInfoValidator productInfoValidator;
	static final Logger LOGGER = Logger.getLogger(ProductInfoController.class);
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		if(binder.getTarget()==null)
			return;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		if(binder.getTarget().getClass()==ProductInfo.class)
			binder.setValidator(productInfoValidator);
	}
	@RequestMapping(value={"/product-info/list","/product-info/list/"})
	public String redirect() {
		return "redirect:/product-info/list/1";
	}
	
	@RequestMapping("/product-info/list/{page}")
	public String showProductInfoList(Model model, HttpSession httpSession, @ModelAttribute("searchForm") ProductInfo productInfo, @PathVariable("page") int page) {
		Paging paging = new Paging(3);
		paging.setIndexPage(page);
		List<ProductInfo> categories = productService.getAllProductInfo(productInfo, paging);
	     if (httpSession.getAttribute(Constant.MSG_SUCCESS) != null) {
	         model.addAttribute(Constant.MSG_SUCCESS, httpSession.getAttribute(Constant.MSG_SUCCESS));
	         httpSession.removeAttribute(Constant.MSG_SUCCESS);
	     }

		if(httpSession.getAttribute(Constant.MSG_ERROR)!=null) {
			model.addAttribute(Constant.MSG_ERROR, httpSession.getAttribute(Constant.MSG_ERROR));
			httpSession.removeAttribute(Constant.MSG_ERROR);
		}
		model.addAttribute("pageInfo", paging);
		model.addAttribute("categories",categories);
		return "productInfo-list";
	}
	
	@GetMapping("/product-info/add")
	public String add(Model model) {
		model.addAttribute("titlePage", "Add ProductInfo");
		model.addAttribute("modelForm", new ProductInfo());
		model.addAttribute("viewOnly", false);
		List<Category> categories = productService.getAllCategory(null, null);
		Map<String, String> mapCategory = new HashMap<String, String>();
		for(Category category:categories) {
			mapCategory.put(String.valueOf(category.getId()), category.getName());
		}
		model.addAttribute("mapCategory",mapCategory);
		return "productInfo-action";
	}
	
	@GetMapping("/product-info/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		LOGGER.info("Edit productInfo with id: "+id);
		ProductInfo productInfo = productService.findByIdProductInfo(id);
		if(productInfo!=null) {
			List<Category> categories = productService.getAllCategory(null, null);
			Map<String, String> mapCategory = new HashMap<String, String>();
			for(Category category:categories) {
				mapCategory.put(String.valueOf(category.getId()), category.getName());
			}
			productInfo.setCateId(productInfo.getCategory().getId());
			model.addAttribute("mapCategory",mapCategory);
			model.addAttribute("titlePage", "Edit ProductInfo");
			model.addAttribute("modelForm", productInfo);
			model.addAttribute("viewOnly", false);
			return "productInfo-action";
		}
		return "redirect:/product-info/list/1";
	}
	
	@GetMapping("/product-info/view/{id}")
	public String view(Model model, @PathVariable("id") int id) {
		LOGGER.info("View productInfo with id: "+id);
		ProductInfo productInfo = productService.findByIdProductInfo(id);
		if(productInfo!=null) {
			model.addAttribute("titlePage", "Edit ProductInfo");
			model.addAttribute("modelForm", productInfo);
			model.addAttribute("viewOnly", true);
			return "productInfo-action";
		}
		return "redirect:/product-info/list/1";
	}
	
	@PostMapping("/product-info/save")
	public String save(Model model, @ModelAttribute("modelForm") @Validated ProductInfo productInfo, BindingResult bindingResult, HttpSession httpSession) {
		if(bindingResult.hasErrors()) {
			if(productInfo.getId()!=null)
				model.addAttribute("titlePage", "Edit ProductInfo");
			else
				model.addAttribute("titlePage", "Add ProductInfo");
			List<Category> categories = productService.getAllCategory(null, null);
			Map<String, String> mapCategory = new HashMap<String, String>();
			for(Category category:categories) {
				mapCategory.put(String.valueOf(category.getId()), category.getName());
			}
			model.addAttribute("mapCategory",mapCategory);
			model.addAttribute("modelForm", productInfo);
			model.addAttribute("viewOnly", false);
			return "productInfo-action";
		}
		Category category = new Category();
		category.setId(productInfo.getCateId());
		productInfo.setCategory(category);
		if(productInfo.getId()!=null && productInfo.getId()!=0) {
			try {
			productService.updateProductInfo(productInfo);
			httpSession.setAttribute(Constant.MSG_SUCCESS, "Update success!");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				httpSession.setAttribute(Constant.MSG_ERROR, "Update has error");
			}}
		else {
			try {
			productService.saveProductInfo(productInfo);
			httpSession.setAttribute(Constant.MSG_SUCCESS, "Insert success!");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				httpSession.setAttribute(Constant.MSG_ERROR, "Insert has error");
			}}
		return "redirect:/product-info/list/1";
	}
	
	@GetMapping("/product-info/delete/{id}")
	public String delete(Model model, @PathVariable("id") int id, HttpSession httpSession) {
		LOGGER.info("Delete productInfo with id: "+id);
		ProductInfo productInfo = productService.findByIdProductInfo(id);
		if(productInfo!=null) {
			try {
			productService.deleteProductInfo(productInfo);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				httpSession.setAttribute(Constant.MSG_ERROR, "Delete has error");
			}
		}
		return "redirect:/product-info/list/1";
	}
}
