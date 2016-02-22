package com.samsonan.bplaces.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samsonan.bplaces.dao.UserDao;
import com.samsonan.bplaces.model.Place;
import com.samsonan.bplaces.model.PlaceFilters;
import com.samsonan.bplaces.model.User;
import com.samsonan.bplaces.util.EmailExistsException;

/**
 * TODO: all methods in all service classes to similar names
 * @author ShamanXXI
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserDao dao;

    @Override
    public User registerNewUser(User user) throws EmailExistsException {
    	
    	List<User> users = dao.findByEmail(user.getEmail());
        if (users!=null && users.size()>0) {  
            throw new EmailExistsException("There is an account with that email adress: " + users.get(0));
        }
        dao.save(user);
        return user;
    }
    
	public List<User> getAllUsers(){
		return dao.findAll();
	}

	@Override
	public User findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveUser(User user) {
		dao.save(user);
	}

	@Override
	public void deleteUser(int id) {
		dao.deleteById(id);
	}

	@Override
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
        if(entity!=null){
        	entity.setEmail(user.getEmail());
        	entity.setFirstName(user.getFirstName());
        	entity.setLastName(user.getLastName());
        }	
    }    
    
    
}