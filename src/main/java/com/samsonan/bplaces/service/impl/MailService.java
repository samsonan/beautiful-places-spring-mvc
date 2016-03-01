package com.samsonan.bplaces.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService
{
	private final Logger logger = LoggerFactory.getLogger(MailService.class);	
	
    @Autowired
    private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Async
	public void sendMail(String from, String to, String subject, String msg) {

		logger.debug("Sending message to={0} with subject={1}",to, subject);
		
		try{
			
			SimpleMailMessage message = new SimpleMailMessage();
		
			message.setFrom(from);
			message.setTo(to);
			message.setSubject(subject);
			message.setText(msg);
			mailSender.send(message);
			
		} catch (Exception ex){
			logger.error("Error sending message",ex);
		}
	}
}