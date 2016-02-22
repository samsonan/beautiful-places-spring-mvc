package com.samsonan.bplaces.service;

import java.util.List;

import com.samsonan.bplaces.model.User;
import com.samsonan.bplaces.util.EmailExistsException;

public interface UserService {

	User registerNewUser(User user) throws EmailExistsException;

	User findById(int id);
    
    void saveUser(User user);
     
    void updateUser(User user);

    void deleteUser(int id);
    
    List<User> getAllUsers(); 
	
}
