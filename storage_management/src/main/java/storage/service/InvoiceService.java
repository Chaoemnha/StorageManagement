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

import storage.dao.InvoiceDAO;
import storage.model.Invoice;
import storage.model.Paging;
import storage.model.User;
import storage.util.Constant;

@Service
public class InvoiceService {
	@Autowired
	private ProductService productService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private ProductInStockService productInStockService;
	@Autowired
	private InvoiceDAO<Invoice> invoiceDAO;
	static final Logger log = Logger.getLogger(InvoiceService.class);

	public void save(Invoice invoice, User user) throws Exception {
		invoice.setActiveFlag(1);
		invoice.setCreateDate(new Timestamp(new Date().getTime()));
		invoice.setUpdateDate(new Timestamp(new Date().getTime()));
		invoice.setProductInfo(productService.findProductInfo("id", invoice.getProductId()).get(0));
		invoice.setUser(user);
		invoiceDAO.save(invoice);
		historyService.save(invoice, Constant.ACTION_ADD);
		// Khi them hang hoa thi PIS se cap nhat hang hoa ben trong no
		productInStockService.saveOrUpdate(invoice);
	}

	public void update(Invoice invoice) throws Exception {
		// Lay so luong hang hoa hien tai
		int originQty = invoiceDAO.findById(Invoice.class, invoice.getId()).getQty();
		invoice.setUpdateDate(new Timestamp(new Date().getTime()));
		invoice.setCode(invoice.getCode());
		invoice.setProductInfo(productService.findProductInfo("id", invoice.getProductId()).get(0));
		invoice.setQty(invoice.getQty());// hien tai=10, update qty=5 => 5-10=-5 => co the am/duong => am														// la xuat hang, duong la nhap hang
		invoice.setPrice(invoice.getPrice());
		// save invoice ben jsp vao DAO
		invoiceDAO.update(invoice);
		historyService.save(invoice, Constant.ACTION_EDIT);
		productInStockService.saveOrUpdate(invoice);
	}

	public List<Invoice> find(String property, Object value) {
		return invoiceDAO.findByProperty(property, value);
	}

	public List<Invoice> getList(Invoice invoice, Paging paging) {
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<>();
		if (invoice != null) {
			if (invoice.getType() != 0) {
				//Tim tat ca cac invoice co type 1 hoac 2
				queryStr.append(" and model.type=:type");
				mapParams.put("type", invoice.getType());
			}
			if (StringUtils.hasLength(invoice.getCode())) {
				queryStr.append(" and model.code =: code ");
				mapParams.put("code", invoice.getCode());
			}
			//O model them from va to de dung cho viec search ngay thang nhu sau
			if (invoice.getFromDate() != null) {
				queryStr.append(" and model.updateDate >= : fromDate");
				mapParams.put("fromDate", invoice.getFromDate());
			}
			if (invoice.getToDate() != null) {
				queryStr.append(" and model.updateDate <= : toDate");
				mapParams.put("toDate", invoice.getToDate());
			}
		}
		return invoiceDAO.findAll(queryStr.toString(), mapParams, paging);
	}
}
