package storage.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import storage.model.User;
@Repository
@Transactional(rollbackFor = Exception.class)
public class UserDaoImpl extends BaseDAOImpl<User> implements UserDAO<User>{
	public User findUserWithUserRolesById(Integer id) {
	    String hql = "SELECT u FROM User u JOIN FETCH u.userRoles WHERE u.id = :id";
	    return (User) sessionFactory.getCurrentSession()
	        .createQuery(hql)
	        .setParameter("id", id)
	        .uniqueResult();
	}
}
