package storage.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import storage.model.User;
@Repository
@Transactional(rollbackFor = Exception.class)
public class UserDaoImpl extends BaseDAOImpl<User> implements UserDAO<User>{
	
}
