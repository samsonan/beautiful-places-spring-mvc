package com.samsonan.bplaces.util.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.samsonan.bplaces.model.User;
import com.samsonan.bplaces.service.UserService;

@Component
public class UserFormValidator implements Validator {

	@Autowired
	UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		User user = (User) target;
		
		if (userService.findByName(user.getName()) != null) {
			errors.rejectValue("name", "Exists.user.name");
		}

		if (userService.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Exists.user.email");
		}
		
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Diff.userform.confirmPassword");
		}
	}
}