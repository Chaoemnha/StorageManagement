package storage.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import storage.model.ProductInStock;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ProductInStockDAOImpl extends BaseDAOImpl<ProductInStock> implements ProductInStockDAO<ProductInStock>{

}
