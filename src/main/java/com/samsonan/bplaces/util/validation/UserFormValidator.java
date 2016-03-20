package com.samsonan.bplaces.util.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.user.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.user.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.user.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.user.password");
		
		User findUser = userService.findByName(user.getName());
		
		if (findUser != null && (user.getId() == null || !findUser.getId().equals(user.getId()))) {
			errors.rejectValue("name", "Exists.user.name");
		}

		findUser = userService.findByName(user.getName());
		
		if (findUser != null && (user.getId() == null || !findUser.getId().equals(user.getId()))) {
			errors.rejectValue("email", "Exists.user.email");
		}
		
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Diff.userform.confirmPassword");
		}
	}
}