package com.samsonan.bplaces.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.samsonan.bplaces.dao.AbstractDao;
import com.samsonan.bplaces.dao.UserDao;
import com.samsonan.bplaces.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Serializable, User> implements UserDao {
	
	public void save(User user) {
		persist(user);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
	}
	
	@Override
	public User findById(int id) {
		return getByKey(id);
	}
	
	@Override
	public void deleteById(int id) {
		User user =  getByKey(id);
        delete(user);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> findByEmail(String email) {
		Criteria criteria = createEntityCriteria();
        Criteria placeCriteria = criteria.createCriteria("user");
        placeCriteria.add(Restrictions.eq("email", email));
        return criteria.list();
	}
} 
