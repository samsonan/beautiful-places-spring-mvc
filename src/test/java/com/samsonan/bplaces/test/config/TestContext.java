package com.samsonan.bplaces.test.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailSender;

@Configuration
@PropertySource(value = { "classpath:application-${spring.profile.active}.properties" })
@ComponentScan(basePackages = { "com.samsonan.bplaces.config" })
@Profile("junit_test")
public class TestContext {

    @Bean
    public MailSender mailSender() {
        return Mockito.mock(MailSender.class);
    }
    

}
