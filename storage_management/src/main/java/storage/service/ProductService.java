package storage.service;



import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import storage.dao.CategoryDAO;
import storage.dao.ProductInfoDAO;
import storage.model.Category;
import storage.model.Paging;
import storage.model.ProductInfo;
import storage.util.ConfigLoader;

@Service
public class ProductService {
	@Autowired
	private CategoryDAO<Category> categoryDAO;
	@Autowired
	private ProductInfoDAO<ProductInfo> productInfoDAO;
	private static final Logger LOGGER = Logger.getLogger(ProductService.class);
	public void save(Category category) {
		LOGGER.info("Insert category " + category.toString());
		category.setActiveFlag(1);
		category.setCreateDate(new Timestamp(new Date().getTime()));
		category.setUpdateDate(new Timestamp(new Date().getTime()));
		categoryDAO.save(category);
	}
	
	public void updateCategory(Category category) {
		LOGGER.info("Update category "+category.toString());
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
	
	public List<Category> getAllCategory(Category category, Paging paging) {
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
	
	//PRODUCT INFO
	
	public void saveProductInfo(ProductInfo productInfo) throws IllegalStateException, IOException {
		LOGGER.info("Insert productInfo " + productInfo.toString());
		productInfo.setActiveFlag(1);
		productInfo.setCreateDate(new Timestamp(new Date().getTime()));
		productInfo.setUpdateDate(new Timestamp(new Date().getTime()));
		String fileName = System.currentTimeMillis()+"_"+productInfo.getMultipartFile().getOriginalFilename();
		processUploadFile(productInfo.getMultipartFile(), fileName);
		productInfo.setImgUrl("/upload/"+fileName);
		productInfoDAO.save(productInfo);
	}
	
	public void updateProductInfo(ProductInfo productInfo) throws IllegalStateException, IOException {
		LOGGER.info("Update productInfo "+productInfo.toString());
		productInfo.setUpdateDate(new Timestamp(new Date().getTime()));
		if(!productInfo.getMultipartFile().getOriginalFilename().isEmpty()) {
			String fileName = System.currentTimeMillis()+"_"+productInfo.getMultipartFile().getOriginalFilename();
			processUploadFile(productInfo.getMultipartFile(), fileName);
			productInfo.setImgUrl("/upload/"+productInfo.getMultipartFile().getOriginalFilename());
		}
		productInfoDAO.update(productInfo);
	}
	
	public void deleteProductInfo(ProductInfo productInfo) {
		productInfo.setActiveFlag(0);
		LOGGER.info("Delete productInfo "+productInfo.toString());
		productInfoDAO.update(productInfo);
	}
	
	public List<ProductInfo> findProductInfo(String property, Object object) {
		LOGGER.info("====Find by property productInfo start====");
		LOGGER.info("property ="+property+" value"+object.toString());
		return productInfoDAO.findByProperty(property, object);
	}
	
	public List<ProductInfo> getAllProductInfo(ProductInfo productInfo, Paging paging) {
		LOGGER.info("show all productInfo");
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<String, Object>();
		if(productInfo!=null) {
			if(productInfo.getId()!=null&&productInfo.getId()!=0) {
				queryStr.append(" and model.id=:id");
				mapParams.put("id", productInfo.getId());
			}
			//Kiem tra ca rong thi cta cx ko xly
			if(productInfo.getCode()!=null&&StringUtils.hasLength(productInfo.getCode())) {
				queryStr.append(" and model.code=:code");
				mapParams.put("code", productInfo.getCode());
			}
			if(productInfo.getName()!=null&&StringUtils.hasLength(productInfo.getName())) {
				queryStr.append(" and model.name like :name");
				mapParams.put("name", "%"+productInfo.getName()+"%");
			}
		}
		return productInfoDAO.findAll(queryStr.toString(), mapParams, paging);
	}
	
	public ProductInfo findByIdProductInfo(int id) {
		LOGGER.info("find productInfo by id ="+id);
		return productInfoDAO.findById(ProductInfo.class, id);
	}
	
	private void processUploadFile(MultipartFile multipartFile, String fileName) throws IllegalStateException, IOException {
		//Thong nhat file name, tranh chenh lenh vai mili giay
		if(!multipartFile.getOriginalFilename().isEmpty()) {
			File dir = new File(ConfigLoader.getInstance().getValue("upload.location"));
			if(!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File(ConfigLoader.getInstance().getValue("upload.location"), fileName);
			multipartFile.transferTo(file);
		}
	}
}
