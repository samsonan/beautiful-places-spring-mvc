package com.samsonan.bplaces.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.springframework.ui.velocity.VelocityEngineUtils;

import com.samsonan.bplaces.model.FeedbackForm;
import com.samsonan.bplaces.model.User;
import com.samsonan.bplaces.service.UserMessageService;

@Service("userMessageService")
public class MessageServiceImpl implements UserMessageService
{

	//TODO: config
	private final static String SUBJECT_REGISTER = "Beautiful Places Registration. Confirm your e-mail";
	private final static String SUBJECT_PASS_RESET = "Beautiful Places Password Reset";
	private final static String SUBJECT_FEEDBACK = "Beautiful Places User Feedback";
	
	//TODO: config
	private final static String TEMPLATE_REGISTER = "register.vm";
	private final static String TEMPLATE_PASS_RESET = "reset_pass.vm";
	private final static String TEMPLATE_FEEDBACK = "feedback.vm";
	
	
	private final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);	
	
    @Autowired
    private MailSender mailSender;

    @Autowired
	DataSource dataSource;

    @Autowired
    private VelocityEngine velocityEngine;    
    
	/* (non-Javadoc)
	 * @see com.samsonan.bplaces.service.impl.UserMessageService#sendRegistrationMessage(com.samsonan.bplaces.model.User)
	 */
	@Override
	@Async
    public void sendRegistrationMessage(User user) {
    	
		logger.debug("Prepare to Send Registration email for user {}", user);
		
        //generate unique confirmation ID to your application
        String uuid = java.util.UUID.randomUUID().toString();

        //Send the URL+ID (http://yourapp.com/confirm?id=UUID) as an email
        String content = "";
        String fromEmail = "samsonan.info@gmail.com"; //TODO: config
        String hostname = "localhost:8080";//TODO:config
        
        try {
        	
        	Map<String, Object> model = new HashMap<>();	             
        	model.put("email_to",user.getEmail());
        	model.put("user_id",user.getId());
        	model.put("hostname",hostname);
        	model.put("user_name",user.getName());
        	model.put("confirm_url","spring4/confirm_reg");//TODO: config
        	model.put("uuid",uuid);
            
            content = VelocityEngineUtils.mergeTemplateIntoString(
            		velocityEngine, TEMPLATE_REGISTER, "UTF-8", model);
        	
        } catch( Exception ex ) {
			logger.error("Error creating message content using velocity",ex);
        }
        
        //store the ID with the account;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("MESSAGE_LOG").usingGeneratedKeyColumns("id");
        
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("TYPE",TYPE_REGISTER);
        parameters.put("USER_ID",user.getId());
        parameters.put("SUBJECT",SUBJECT_REGISTER);
        parameters.put("EMAIL_TO",user.getEmail());
        parameters.put("EMAIL_FROM",fromEmail);
        parameters.put("STATUS",STATUS_PENDING);
        parameters.put("UUID",uuid);

        parameters.put("CONTENT",content);
        
        Number key = insert.executeAndReturnKey(parameters);
        final long logId = key.longValue();

        logger.debug("insert message executed. id: {}", logId);
        
    	try{
//    		sendMail(fromEmail, user.getEmail(), SUBJECT_REGISTER, content);
    	}catch (Exception ex){
			logger.error("Error sending message",ex);
			logError(logId, ex.getMessage(), jdbcTemplate);
    	}

    	logSuccess(logId, jdbcTemplate);
    	
    	//AFTER:
    	//request mapping GET /confirm?id=UUID
        //redirect somewhere
    }    
	
	/* (non-Javadoc)
	 * @see com.samsonan.bplaces.service.impl.UserMessageService#checkRegistrationUUID(int, java.lang.String)
	 */
	@Override
	public boolean checkRegistrationUUID(int user_id, String uuid){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT UUID FROM MESSAGE_LOG WHERE TYPE = ? and UUID = ? and USER_ID = ?";
		 
		String res = (String) jdbcTemplate.queryForObject(
				sql, new Object[] { TYPE_REGISTER, uuid, user_id }, String.class);
		
		return res!=null && res.equals(uuid);
	}

	/* (non-Javadoc)
	 * @see com.samsonan.bplaces.service.impl.UserMessageService#sendPasswordResetMessage(com.samsonan.bplaces.model.User)
	 */
	@Override
	@Async
    public void sendPasswordResetMessage(User user) {
    	
    	//generate UUID with exp date (TODO)
        String uuid = java.util.UUID.randomUUID().toString();

        //store the ID with the account;
        //Send the URL+ID (http://yourapp.com/reset?id=UUID) as an email
        String content = "";
        String fromEmail = "samsonan.info@gmail.com"; //TODO: config
        String hostname = "localhost:8080";//TODO:config
        
        try {
        	
        	Map<String, Object> model = new HashMap<>();	             
        	model.put("email_to",user.getEmail());
        	model.put("user_id",user.getId());
        	model.put("hostname",hostname);
        	model.put("user_name",user.getName());
        	model.put("confirm_reset","spring4/confirm_reset");//TODO: config
        	model.put("uuid",uuid);
            
            content = VelocityEngineUtils.mergeTemplateIntoString(
            		velocityEngine, TEMPLATE_PASS_RESET, "UTF-8", model);
        	
        } catch( Exception ex ) {
			logger.error("Error creating message content using velocity",ex);
        }
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("MESSAGE_LOG").usingGeneratedKeyColumns("id");
        
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("TYPE",TYPE_PASS_RESET);
        parameters.put("USER_ID",user.getId());
        parameters.put("SUBJECT",SUBJECT_PASS_RESET);
        parameters.put("EMAIL_TO",user.getEmail());
        parameters.put("EMAIL_FROM",fromEmail);
        parameters.put("STATUS",STATUS_PENDING);
        parameters.put("UUID",uuid);

        parameters.put("CONTENT",content);
        
        Number key = insert.executeAndReturnKey(parameters);
        final long logId = key.longValue();
        
    	try{
//    		sendMail(fromEmail, user.getEmail(), SUBJECT_PASS_RESET, content);
    	}catch (Exception ex){
			logger.error("Error sending message",ex);
			logError(logId, ex.getMessage(), jdbcTemplate);
    	}

    	logSuccess(logId, jdbcTemplate);
    	
    	//AFTER:
        //request mapping GET /reset?id=UUID
    	//redirect to login
    }  	

	/* (non-Javadoc)
	 * @see com.samsonan.bplaces.service.impl.UserMessageService#checkPasswordResetUUID(int, java.lang.String)
	 */
	@Override
	public boolean checkPasswordResetUUID(int user_id, String uuid){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT UUID FROM MESSAGE_LOG WHERE TYPE = ? and UUID = ? and USER_ID = ?";
		 
		//TODO: check expiration
		String res = (String) jdbcTemplate.queryForObject(
				sql, new Object[] { TYPE_PASS_RESET, uuid, user_id }, String.class);
		
		return res!=null && res.equals(uuid);
	}    
    
	/* (non-Javadoc)
	 * @see com.samsonan.bplaces.service.impl.UserMessageService#sendFeedback(com.samsonan.bplaces.model.User, com.samsonan.bplaces.model.FeedbackForm)
	 */
	@Override
	@Async
    public void sendFeedback(User user, FeedbackForm form) {
    	
        String content = "";
        String siteEmail = "samsonan.info@gmail.com"; //TODO: config
        
        try {
        	
        	Map<String, Object> model = new HashMap<>();
        	
        	model.put("form_email",form.getUserEmail());
        	model.put("form_name",form.getUserName());

        	if (user != null) {
	        	model.put("user_email",user.getEmail());
	        	model.put("user_name",user.getName());
	        	model.put("user_id",user.getId());
        	} else {
	        	model.put("user_email", "N/A");
	        	model.put("user_name", "N/A");
	        	model.put("user_id", "N/A");
        	}
        	
        	model.put("feedback_text",form.getMessage());
            
            content = VelocityEngineUtils.mergeTemplateIntoString(
            		velocityEngine, TEMPLATE_FEEDBACK, "UTF-8", model);
        	
        } catch( Exception ex ) {
			logger.error("Error creating message content using velocity",ex);
        }
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("MESSAGE_LOG").usingGeneratedKeyColumns("id");
        
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("TYPE",TYPE_FEEDBACK);
        
        if (user != null){
            parameters.put("USER_ID",user.getId());
        }
        
        parameters.put("SUBJECT",SUBJECT_FEEDBACK);
        
        parameters.put("EMAIL_TO",siteEmail);
        parameters.put("EMAIL_FROM",siteEmail);
        parameters.put("STATUS",STATUS_PENDING);

        parameters.put("CONTENT",content);
        
        Number key = insert.executeAndReturnKey(parameters);
        final long logId = key.longValue();
        
    	try{
    		//sendMail(siteEmail, siteEmail, SUBJECT_FEEDBACK, content);
    	}catch (Exception ex){
			logger.error("Error sending message",ex);
			logError(logId, ex.getMessage(), jdbcTemplate);
    	}

    	logSuccess(logId, jdbcTemplate);
    }      
    
	private void logError(long logId, String errorMsg, JdbcTemplate jdbcTemplate) {
		jdbcTemplate.update("update MESSAGE_LOG set status = ?, status_timestamp=?, err_message=? where id = ?", 
				STATUS_ERROR, new Date(), errorMsg, logId);
	}

	private void logSuccess(long logId, JdbcTemplate jdbcTemplate) {
		jdbcTemplate.update("update MESSAGE_LOG set status = ?, status_timestamp=? where id = ?", STATUS_SUCCESS, new Date(), logId);
	}
	
	/* (non-Javadoc)
	 * @see com.samsonan.bplaces.service.impl.UserMessageService#setMailSender(org.springframework.mail.MailSender)
	 */
	@Override
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	/* (non-Javadoc)
	 * @see com.samsonan.bplaces.service.impl.UserMessageService#sendMail(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendMail(String from, String to, String subject, String msg) throws MailException {

		logger.debug("Sending message to={0} with subject={1}", to, subject);

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
			
	}
}