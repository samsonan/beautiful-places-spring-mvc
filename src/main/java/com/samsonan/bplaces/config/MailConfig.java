package com.samsonan.bplaces.config;

import java.util.Properties;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class MailConfig {

    @Autowired
    private Environment environment;
    
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(environment.getRequiredProperty("mail.host"));
		mailSender.setPort(Integer.parseInt( environment.getRequiredProperty("mail.port")));
		mailSender.setUsername(environment.getRequiredProperty("mail.username"));
		mailSender.setPassword(environment.getRequiredProperty("mail.password"));
		
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", environment.getRequiredProperty("mail.transport.protocol"));
		props.setProperty("mail.smtp.auth", environment.getRequiredProperty("mail.smtp.auth"));
		props.setProperty("mail.smtp.starttls.enable", environment.getRequiredProperty("mail.smtp.starttls.enable"));
		props.setProperty("mail.debug", environment.getRequiredProperty("mail.debug"));
		
		mailSender.setJavaMailProperties(props);
		
        return mailSender;
	}

}
