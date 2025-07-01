package storage.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = Exception.class)
public class BaseDAOImpl<E> implements BaseDAO<E> {
		final static Logger log = Logger.getLogger(BaseDAOImpl.class);
		@Autowired
		SessionFactory sessionFactory; 
	@Override
	public List<E> findAll() {
		log.info("find all record from db");
		StringBuilder queryString = new StringBuilder();
		queryString.append(" from ").append(getGenericName()).append(" as model where model.activeFlag=1");
		log.info("Query find all ---->" + queryString.toString());
		return sessionFactory.getCurrentSession().createQuery(queryString.toString(), getEntityClass()).getResultList();
	}

	@Override
	public E findById(Class<E> e, Serializable id) {
		log.info("Find by ID");
		return sessionFactory.getCurrentSession().get(e, id);
	}

	@Override
	public List<E> findByProperty(String property, Object value) {
		log.info("Find by property");
		StringBuilder queryString = new StringBuilder();
		queryString.append(" from ").append(getGenericName()).append(" as model where model.activeFlag = 1 and model.").append(property).append(" = :value");
		Query<E> query = sessionFactory.getCurrentSession().createQuery(queryString.toString(), getEntityClass());
		log.info("Query find by prop ---->" + queryString.toString());
		query.setParameter("value", value);
		return query.getResultList();
		//vd: from auth as model where model.activeFlag = 1 and model.name=?
		//Su dung generic pattern co the tai su dung code cho bat ki kieu entity nao
	}

	@Override
	public void save(E instance) {
		log.info("save instance");
		sessionFactory.getCurrentSession().persist(instance);
	}

	@Override
	public void update(E instance) {
		log.info("update instance");
		sessionFactory.getCurrentSession().update(instance);
	}
	@SuppressWarnings("unchecked")
    public Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];
    }
	//
	public String getGenericName() {
		String s = getClass().getGenericSuperclass().toString();
		Pattern pattern = Pattern.compile("<(.+?)>");		
		Matcher m = pattern.matcher(s);
		String generic = "null";
		if(m.find()) {
			generic = m.group(1);
		}
		return generic;
	}
	
}
