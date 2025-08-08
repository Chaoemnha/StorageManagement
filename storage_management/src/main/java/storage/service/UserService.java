package storage.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import storage.dao.RoleDAO;
import storage.dao.UserDAO;
import storage.dao.UserRoleDAO;
import storage.model.Paging;
import storage.model.Role;
import storage.model.User;
import storage.model.UserRole;
import storage.util.HashingPassword;

@Service
public class UserService {
	final static Logger log = Logger.getLogger(UserService.class);
	@Autowired
	private UserDAO<User> userDAO;
	@Autowired
	private UserRoleDAO<UserRole> userRoleDAO;
	@Autowired
	private RoleDAO<Role> roleDAO;

	public List<User> findByProperty(String property, Object value) {
		log.info("Find user by property start");
		return userDAO.findByProperty(property, value);
	}

	public User findById(Integer id) {
		log.info("Find user by id ");
		return userDAO.findById(User.class, id);
	}

	public void save(User user) throws Exception {
		user.setActiveFlag(1);
		user.setCreateDate(new Timestamp(new Date().getTime()));
		user.setUpdateDate(new Timestamp(new Date().getTime()));
		user.setPassword(HashingPassword.encrypt(user.getPassword()));
		userDAO.save(user);
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		Role role = roleDAO.findById(Role.class, user.getRoleId());
		userRole.setRole(role);
		userRole.setActiveFlag(1);
		userRole.setCreateDate(new Timestamp(new Date().getTime()));
		userRole.setUpdateDate(new Timestamp(new Date().getTime()));
		userRoleDAO.save(userRole);
	}

	public void update(User users) {
		User user = findById(users.getId());
		if (user != null) {
			UserRole userRole = (UserRole) user.getUserRoles().iterator().next();
			Role role = userRole.getRole();
			role.setId(users.getRoleId());
			userRole.setRole(role);
			userRole.setUpdateDate(new Timestamp(new Date().getTime()));
			user.setName(users.getName());
			user.setEmail(users.getEmail());
			user.setUserName(users.getUserName());
			user.setUpdateDate(new Timestamp(new Date().getTime()));
			userRoleDAO.update(userRole);
		}
		userDAO.update(user);
	}

	public void deleteUser(User user) {
		user.setActiveFlag(0);
		user.setUpdateDate(new Timestamp(new Date().getTime()));
		userDAO.update(user);
	}

	public List<User> getUsersList(User users, Paging paging) {
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<String, Object>();
		if (users != null) {
			if (StringUtils.hasLength(users.getName())) {
				queryStr.append(" and model.name like : name");
				mapParams.put("name", "%" + users.getName() + "%");
			}
			if (StringUtils.hasLength(users.getUserName())) {
				queryStr.append(" and model.userName like: userName");
				mapParams.put("userName", "%" + users.getUserName() + "%");
			}
			if (StringUtils.hasLength(users.getEmail())) {
				queryStr.append(" and model.email like: email");
				mapParams.put("email", "%" + users.getEmail() + "%");
			}
		}
		return userDAO.findAll(queryStr.toString(), mapParams, paging);
	}
}
