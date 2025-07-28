package storage.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import storage.model.Role;
@Repository
@Transactional(rollbackFor = Exception.class)
public class RoleDaoImpl extends BaseDAOImpl<Role> implements RoleDAO<Role>{
	
}
