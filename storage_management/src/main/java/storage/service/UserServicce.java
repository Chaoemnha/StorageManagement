package storage.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import storage.dao.UserDAO;
import storage.model.User;

@Service
public class UserServicce {
	final static Logger log = Logger.getLogger(UserServicce.class);
	@Autowired
	private UserDAO<User> userDAO;
	public List<User> findByProperty(String property, Object value) {
		log.info("Find user by property start");
		return userDAO.findByProperty(property, value);
	}
}
