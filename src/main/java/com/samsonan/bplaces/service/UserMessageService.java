package com.samsonan.bplaces.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import com.samsonan.bplaces.model.FeedbackForm;
import com.samsonan.bplaces.model.User;

public interface UserMessageService {

	String TYPE_REGISTER = "REGISTER";
	String TYPE_PASS_RESET = "PASS_RESET";
	String TYPE_FEEDBACK = "FEEDBACK";
	
	int STATUS_PENDING = 0;
	int STATUS_SUCCESS = 1;
	int STATUS_ERROR = -1;

	void sendRegistrationMessage(User user);

	boolean checkRegistrationUUID(int user_id, String uuid);

	void sendPasswordResetMessage(User user);

	boolean checkPasswordResetUUID(int user_id, String uuid);

	void sendFeedback(User user, FeedbackForm form);

	void setMailSender(MailSender mailSender);

	void sendMail(String from, String to, String subject, String msg) throws MailException;

}