package storage.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import storage.model.ProductInfo;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ProductInfoDAOImpl extends BaseDAOImpl<ProductInfo> implements ProductInfoDAO<ProductInfo>{

}
