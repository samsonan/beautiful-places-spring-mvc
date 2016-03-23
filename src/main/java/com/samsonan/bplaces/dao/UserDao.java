package com.samsonan.bplaces.dao;

import java.util.List;

import com.samsonan.bplaces.exception.UserNotFoundException;
import com.samsonan.bplaces.model.User;

public interface UserDao {

	List<User> findAll();
    User findByEmail(String email);
	User findByName(String name);
    
	User findById(int id);
     
    void saveOrUpdate(User user);
     
    void deleteById(int id) throws UserNotFoundException;	

    
}
