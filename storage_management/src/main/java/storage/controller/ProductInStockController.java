package storage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import storage.model.ProductInStock;
import storage.model.Paging;
import storage.service.ProductInStockService;

@Controller
public class ProductInStockController {
	@Autowired
	private ProductInStockService productInStockService;
	static final Logger LOGGER = Logger.getLogger(ProductInStockController.class);
	
	@RequestMapping(value={"/product-in-stock/list","/product-in-stock/list/"})
	public String redirect() {
		return "redirect:/product-in-stock/list/1";
	}
	
	@RequestMapping("/product-in-stock/list/{page}")
	public String list(Model model, HttpSession httpSession, @ModelAttribute("searchForm") ProductInStock productInStock, @PathVariable("page") int page) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		List<ProductInStock> productInStocks = productInStockService.getAll(productInStock, paging);
		model.addAttribute("pageInfo", paging);
		model.addAttribute("products",productInStocks);
		return "product-in-stock";
	}
}
