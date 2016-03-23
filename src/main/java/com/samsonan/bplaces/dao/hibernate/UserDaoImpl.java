package com.samsonan.bplaces.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.samsonan.bplaces.dao.UserDao;
import com.samsonan.bplaces.exception.UserNotFoundException;
import com.samsonan.bplaces.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Serializable, User> implements UserDao {

	public void saveOrUpdate(User user) {
		
		if (user.getId() != null && findById(user.getId()) != null) {
			merge(user);
		} else {
			persist(user);
		}
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
	public void deleteById(int id) throws UserNotFoundException {
		User user = getByKey(id);

		if (user == null)
			throw new UserNotFoundException();

		delete(user);
	}

	@Override
	public User findByEmail(String email) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("email", email));
		return (User) criteria.uniqueResult();
	}

	@Override
	public User findByName(String name) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		return  (User) criteria.uniqueResult();
	}

}
