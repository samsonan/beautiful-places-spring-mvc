package com.samsonan.bplaces.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samsonan.bplaces.dao.UserDao;
import com.samsonan.bplaces.exception.UserNotFoundException;
import com.samsonan.bplaces.model.User;
import com.samsonan.bplaces.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    
    @Autowired
    UserDao userDao;

	@Autowired
	MessageServiceImpl mailService;
    
    @Override
    public User registerNewUser(User user) {
        userDao.saveOrUpdate(user);
                
        mailService.sendRegistrationMessage(user);
        return user;
    }
    
    public void restoreUserPassword(User user) {
    	
    	mailService.sendPasswordResetMessage(user);
    }
    
	public List<User> findAll(){
		return userDao.findAll();
	}

	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}

	@Override
	public User findByName(String name) {
		return userDao.findByName(name);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	@Override
	public void saveUser(User user) {
		userDao.saveOrUpdate(user);
	}

	@Override
	public void deleteById(int id) throws UserNotFoundException {
		userDao.deleteById(id);
	}
  
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {

		com.samsonan.bplaces.model.User domainUser = userDao.findByName(login);
		if (domainUser==null) throw new UsernameNotFoundException("User with name "+login+" not found");
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new org.springframework.security.core.userdetails.User(
				domainUser.getName(), 
				domainUser.getPassword(), 
				enabled, 
				accountNonExpired, 
				credentialsNonExpired, 
				accountNonLocked,
				getAuthorities(domainUser.getRole())
		);
	}

	public Collection<SimpleGrantedAuthority> getAuthorities(String role) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

	@Override
	public void setUserStatus(int id, int status) {
		User user = findById(id);
		user.setStatus(status);
   		saveUser(user);
	}

	@Override
	public void setUserPassword(int id, String password) {
		User user = findById(id);
		user.setPassword(password);
		saveUser(user);
		
	}	
    
    
}