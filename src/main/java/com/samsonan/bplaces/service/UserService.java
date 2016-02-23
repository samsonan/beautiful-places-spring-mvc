package com.samsonan.bplaces.service;

import java.util.List;

import com.samsonan.bplaces.model.User;

public interface UserService {

	User registerNewUser(User user);

    List<User> findAll(); 
    
	User findById(int id);
	User findByName(String name);
	
    void saveUser(User user);
     
    void deleteById(int id);
    
	
}
