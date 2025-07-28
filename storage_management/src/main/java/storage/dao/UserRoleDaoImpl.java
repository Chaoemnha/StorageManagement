package storage.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import storage.model.UserRole;
@Repository
@Transactional(rollbackFor = Exception.class)
public class UserRoleDaoImpl extends BaseDAOImpl<UserRole> implements UserRoleDAO<UserRole>{
	
}
