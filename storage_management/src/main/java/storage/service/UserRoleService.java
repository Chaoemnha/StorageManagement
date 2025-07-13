package storage.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import storage.dao.UserRoleDAO;
import storage.dto.UserRoleDTO;
import storage.model.UserRole;

@Service
public class UserRoleService {
	final static Logger log = Logger.getLogger(UserRoleService.class);
	@Autowired
	private UserRoleDAO<UserRole> userRoleDAO;
	public List<UserRole> findByProperty(String property, Object value) {
		log.info("Find userRole by property start");
		return userRoleDAO.findByProperty(property, value);
	}
	public void save(UserRole userRole) {
		userRoleDAO.save(userRole);
	};
	public List<UserRoleDTO> findUserRoleDTOsByUserId(Integer id) {
		return userRoleDAO.findUserRoleDTOsByUserId(id);
	}
}