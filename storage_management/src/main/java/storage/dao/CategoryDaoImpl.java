package storage.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import storage.model.Category;
@Repository
@Transactional(rollbackFor = Exception.class)
public class CategoryDaoImpl extends BaseDAOImpl<Category> implements CategoryDAO<Category>{

}
