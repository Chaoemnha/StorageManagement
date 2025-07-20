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

import storage.dao.HistoryDAO;
import storage.model.History;
import storage.model.Invoice;
import storage.model.Paging;

@Service
public class HistoryService {
	@Autowired
	private HistoryDAO<History> historyDAO;
	private static final Logger LOGGER = Logger.getLogger(HistoryService.class);

	public List<History> getAll(History history, Paging paging) {
		LOGGER.info("show all history");
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<String, Object>();
		if(history!=null) {
			if(history.getProductInfo()!=null) {
				if(StringUtils.hasLength(history.getProductInfo().getCategory().getName()) ) { 
				queryStr.append(" and model.productInfo.category.name like: cateName");
				mapParams.put("cateName", "%"+history.getProductInfo().getCategory().getName()+"&");
				}
				if(StringUtils.hasLength(history.getProductInfo().getCode())) {
					queryStr.append(" and model.productInfo.code=:code");
					mapParams.put("code", history.getProductInfo().getCode());
				}
				if(StringUtils.hasLength(history.getProductInfo().getName()) ) {
					queryStr.append(" and model.productInfo.name like : name");
					mapParams.put("name", "%"+history.getProductInfo().getName()+"%");
				}
			}
			if(StringUtils.hasLength(history.getActionName()) ) {
				queryStr.append(" and model.actionName like actionName"); 
				mapParams.put("actionName", "%"+history.getActionName()+"%");
			}
			if(history.getType()!=0) {
				queryStr.append(" and model.type = :type");
				mapParams.put("type", history.getType());
			}
		}
	return historyDAO.findAll(queryStr.toString(),mapParams,paging);
	}
	//luu thong tin nhap, xuat
	public void save(Invoice invoice, String action) {
		History history = new History();
		history.setProductInfo(invoice.getProductInfo());
		history.setQty(invoice.getQty());
		history.setType(invoice.getType());
		history.setPrice(invoice.getPrice());
		history.setActiveFlag(1);
		history.setActionName (action);
		history.setCreateDate(new Timestamp(new Date().getTime()));
		history.setUpdateDate(new Timestamp(new Date().getTime()));
		historyDAO.save(history);
	}

}
