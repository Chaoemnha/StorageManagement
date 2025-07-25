package storage.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import storage.model.Menu;
@Repository
@Transactional(rollbackFor = Exception.class)
public class MenuDAOImpl extends BaseDAOImpl<Menu> implements MenuDAO<Menu>{

}
