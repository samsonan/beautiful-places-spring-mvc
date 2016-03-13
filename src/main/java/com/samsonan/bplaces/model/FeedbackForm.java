package com.samsonan.bplaces.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.samsonan.bplaces.util.validation.ValidEmail;

public class FeedbackForm {

	@NotEmpty(message = "{NotEmpty.feedback.name}")
	private String userName;
	
	@ValidEmail(message="{ValidEmail.feedback.email}")
	@NotEmpty(message = "{NotEmpty.feedback.email}")
	private String userEmail;
	
	@NotEmpty(message = "{NotEmpty.feedback.message}")
	private String message;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
