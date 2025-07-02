package storage.service;



import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import storage.dao.CategoryDAO;
import storage.model.Category;

@Service
public class ProductService {
	@Autowired
	private CategoryDAO<Category> categoryDAO;
	private static final Logger LOGGER = Logger.getLogger(ProductService.class);
	public void save(Category category) {
		LOGGER.info("Insert category " + category.toString());
		category.setActiveFlag(1);
		category.setCreateDate(new Timestamp(new Date().getTime()));
		category.setUpdateDate(new Timestamp(new Date().getTime()));
		categoryDAO.save(category);
	}
	
	public void updateCategory(Category category) {
		category.setUpdateDate(new Timestamp(new Date().getTime()));
		categoryDAO.update(category);
	}
	
	public void deleteCategory(Category category) {
		category.setActiveFlag(0);
		LOGGER.info("Delete category "+category.toString());
		categoryDAO.update(category);
	}
	
	public List<Category> findCategory(String property, Object object) {
		LOGGER.info("====Find by property category start====");
		LOGGER.info("property ="+property+" value"+object.toString());
		return categoryDAO.findByProperty(property, object);
	}
}
