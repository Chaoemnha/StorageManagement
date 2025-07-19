package storage.service;



import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import storage.dao.CategoryDAO;
import storage.model.Category;
import storage.model.Paging;

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
	
	public List<Category> getAll(Category category, Paging paging) {
		LOGGER.info("show all category");
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<String, Object>();
		if(category!=null) {
			if(category.getId()!=null&&category.getId()!=0) {
				queryStr.append(" and model.id=:id");
				mapParams.put("id", category.getId());
			}
			//Kiem tra ca rong thi cta cx ko xly
			if(category.getCode()!=null&&StringUtils.hasLength(category.getCode())) {
				queryStr.append(" and model.code=:code");
				mapParams.put("code", category.getCode());
			}
			if(category.getName()!=null&&StringUtils.hasLength(category.getName())) {
				queryStr.append(" and model.name like :name");
				mapParams.put("name", "%"+category.getName()+"%");
			}
		}
		return categoryDAO.findAll(queryStr.toString(), mapParams, paging);
	}
	
	public Category findByIdCategory(int id) {
		LOGGER.info("find category by id ="+id);
		return categoryDAO.findById(Category.class, id);
	}
}
