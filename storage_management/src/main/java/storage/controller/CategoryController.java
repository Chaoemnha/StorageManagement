package storage.controller;

import java.util.List;

import org.apache.log4j.Logger;import org.apache.velocity.app.event.ReferenceInsertionEventHandler.referenceInsertExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import storage.model.Category;
import storage.service.ProductService;
import storage.validate.CategoryValidator;

@Controller
public class CategoryController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryValidator categoryValidator;
	static final Logger LOGGER = Logger.getLogger(CategoryController.class);
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		if(binder.getTarget()==null)
			return;
		if(binder.getTarget().getClass()==Category.class)
			binder.setValidator(categoryValidator);
	}
	
	@GetMapping("/category/list")
	public String showCategoryList(Model model) {
		List<Category> categories = productService.getAll();
		model.addAttribute("categories",categories);
		return "category-list";
	}
	
	@GetMapping("/category/add")
	public String add(Model model) {
		model.addAttribute("titlePage", "Add category");
		model.addAttribute("modelForm", new Category());
		model.addAttribute("viewOnly", false);
		return "category-action";
	}
	
	@GetMapping("/category/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		LOGGER.info("Edit category with id: "+id);
		Category category = productService.findByIdCategory(id);
		if(category!=null) {
			model.addAttribute("titlePage", "Edit Category");
			model.addAttribute("modelForm", category);
			model.addAttribute("viewOnly", false);
			return "category-action";
		}
		return "redirect:/category/list";
	}
	
	@GetMapping("/category/view/{id}")
	public String view(Model model, @PathVariable("id") int id) {
		LOGGER.info("View category with id: "+id);
		Category category = productService.findByIdCategory(id);
		if(category!=null) {
			model.addAttribute("titlePage", "Edit Category");
			model.addAttribute("modelForm", category);
			model.addAttribute("viewOnly", true);
			return "category-action";
		}
		return "redirect:/category/list";
	}
	
	@GetMapping("/category/save")
	public String save(Model model, @ModelAttribute("modelForm") @Validated Category category, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "category-action";
		if(category.getId()!=null && category.getId()!=0) {
			productService.updateCategory(category);
			model.addAttribute("message", "Update success!");}
		else {
			productService.updateCategory(category);
			model.addAttribute("message", "Insert success!");}
		return showCategoryList(model);
	}
	
	@GetMapping("/category/delete/{id}")
	public String delete(Model model, @PathVariable("id") int id) {
		LOGGER.info("Delete category with id: "+id);
		Category category = productService.findByIdCategory(id);
		if(category!=null) {
			productService.deleteCategory(category);
		}
		return "redirect:/category/list";
	}
}
