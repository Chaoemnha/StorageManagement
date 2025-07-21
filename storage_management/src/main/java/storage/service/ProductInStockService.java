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

import storage.dao.ProductInStockDAO;
import storage.model.Invoice;
import storage.model.Paging;
import storage.model.ProductInStock;
import storage.model.ProductInfo;
import storage.util.Constant;

@Service
public class ProductInStockService {
	@Autowired
	private ProductInStockDAO<ProductInStock> productInStockDAO;
	private static final Logger LOGGER = Logger.getLogger(ProductInStockService.class);
	public List<ProductInStock> getAll(ProductInStock productInStock, Paging paging) {
		LOGGER.info("show all product in storage");
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<String, Object>();
		if(productInStock!=null && productInStock.getProductInfo()!=null) {
			if(StringUtils.hasLength(productInStock.getProductInfo().getCategory().getName())) {
				queryStr.append(" and model.productInfo.category.name like :cateName");
				mapParams.put("cateName", "%"+ productInStock.getProductInfo().getCategory().getName()+"%");
			}
			//Kiem tra ca rong thi cta cx ko xly
			if(productInStock.getProductInfo().getCode()!=null&&StringUtils.hasLength(productInStock.getProductInfo().getCode())) {
				queryStr.append(" and model.productInfo.code=:code");
				mapParams.put("code", productInStock.getProductInfo().getCode());
			}
			if(productInStock.getProductInfo().getName()!=null&&StringUtils.hasLength(productInStock.getProductInfo().getName())) {
				queryStr.append(" and model.productInfo.name like :name");
				mapParams.put("name", "%"+productInStock.getProductInfo().getName()+"%");
			}
		}
		return productInStockDAO.findAll(queryStr.toString(), mapParams, paging);
	}
	//Khi nhap hang hoa thi save hoa don day vao trong PIS de no cap nhat so luong hang hoa trong kho
	public void saveOrUpdate(Invoice invoice){
	    LOGGER.info("Product in storage ");
	    if(invoice.getProductInfo()!=null) {
	        String code = invoice.getProductInfo().getCode();
	        List<ProductInStock> list = productInStockDAO.findByProperty("productInfo.code", code);
	        
	        if (!list.isEmpty()) {
	            ProductInStock productInStock = list.get(0);
	            LOGGER.info("update qty="+invoice.getQty()+" and price="+invoice.getPrice());
	            productInStock.setQty(productInStock.getQty()+invoice.getQty());
	            
	            if(invoice.getType()==Constant.TYPE_GOODS_RECEIPT)
	                productInStock.setPrice(invoice.getPrice());
	            
	            productInStock.setUpdateDate(new Timestamp(new Date().getTime()));
	            productInStockDAO.update(productInStock);
	        } else if(invoice.getType()==Constant.TYPE_GOODS_RECEIPT) {
	            LOGGER.info("insert to storage qty="+invoice.getQty()+" and price="+invoice.getPrice());
	            ProductInStock productInStock = new ProductInStock();
	            ProductInfo productInfo = new ProductInfo();
	            productInfo.setId(invoice.getProductInfo().getId());
	            productInStock.setProductInfo(productInfo);
	            productInStock.setActiveFlag(1);
	            productInStock.setCreateDate(new Timestamp(new Date().getTime()));
	            productInStock.setUpdateDate(new Timestamp(new Date().getTime()));
	            productInStock.setQty(invoice.getQty());
	            productInStock.setPrice(invoice.getPrice());
	            productInStockDAO.save(productInStock);
	        }
	    }
	}
}
