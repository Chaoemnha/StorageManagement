package storage.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import storage.dao.RoleDAO;
import storage.dto.RoleDTO;
import storage.model.Role;

@Service
public class RoleService {
	final static Logger log = Logger.getLogger(RoleService.class);
	@Autowired
	private RoleDAO<Role> roleDAO;
	public List<Role> findByProperty(String property, Object value) {
		log.info("Find role by property start");
		return roleDAO.findByProperty(property, value);
	}
	public void save(Role role) {
		roleDAO.save(role);
	};
	public RoleDTO findRoleWithRoleId(Integer id) {
		return roleDAO.findRoleWithRoleId(id);
	}
}
