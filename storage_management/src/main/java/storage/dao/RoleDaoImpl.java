package storage.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import storage.dto.AuthDTO;
import storage.dto.RoleDTO;
import storage.model.Auth;
import storage.model.Role;
@Repository
@Transactional(rollbackFor = Exception.class)
public class RoleDaoImpl extends BaseDAOImpl<Role> implements RoleDAO<Role>{
	public RoleDTO findRoleWithRoleId(Integer id) {
		String hql = "SELECT DISTINCT r FROM Role r "
		           + "JOIN FETCH r.auths a "
		           + "JOIN FETCH a.menu "
		           + "WHERE r.id = :id";
	    Role role = sessionFactory.getCurrentSession()
	        .createQuery(hql, Role.class)
	        .setParameter("id", id)
	        .uniqueResult();
	    Role dto;
	    Set<Auth> auths = role.getAuths();
	    List<AuthDTO> authDTOs = new ArrayList<AuthDTO>();
	    for(Auth auth:auths) {
	    	authDTOs.add(new AuthDTO(auth.getId(), auth.getActiveFlag(), auth.getPermission(), auth.getMenu()));
	    }
	    RoleDTO dto2 = new RoleDTO(id, authDTOs);
	    return dto2;
	}
}
