package com.samsonan.bplaces.service;

import java.util.List;

import com.samsonan.bplaces.model.User;

public interface UserService {

	User registerNewUser(User user);

    List<User> findAll(); 
    
	User findById(int id);
	User findByName(String name);
	User findByEmail(String email);
	
    void saveUser(User user);
     
    void deleteById(int id);
    
    void restoreUserPassword(User user);
    
    void setUserStatus(int id, int status);
    void setUserPassword(int id, String password);
	
}
