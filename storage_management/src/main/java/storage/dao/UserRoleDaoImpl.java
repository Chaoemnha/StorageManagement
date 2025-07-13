package storage.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import storage.dto.UserRoleDTO;
import storage.model.Role;
import storage.model.User;
import storage.model.UserRole;
@Repository
@Transactional(rollbackFor = Exception.class)
public class UserRoleDaoImpl extends BaseDAOImpl<UserRole> implements UserRoleDAO<UserRole>{
	public List<UserRoleDTO> findUserRoleDTOsByUserId(Integer userId) {
	    String hql = "SELECT ur FROM UserRole ur "
	               + "JOIN FETCH ur.user u "
	               + "JOIN FETCH ur.role r "
	               + "WHERE u.id = :id";

	    List<UserRole> userRoles = sessionFactory.getCurrentSession()
	        .createQuery(hql, UserRole.class)
	        .setParameter("id", userId)
	        .getResultList();

	    List<UserRoleDTO> dtos = new ArrayList<>();
	    for (UserRole ur : userRoles) {
	        User user = ur.getUser();
	        Role role = ur.getRole();
	        UserRoleDTO dto = new UserRoleDTO(
	            ur.getId(),
	            user.getId(),
	            user.getUserName(),
	            role.getId(),
	            role.getRoleName()
	        );
	        dtos.add(dto);
	    }
	    return dtos;
	}
}
