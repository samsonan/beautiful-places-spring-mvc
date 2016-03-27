package com.samsonan.bplaces.test.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailSender;

import com.samsonan.bplaces.config.AppConfig;

@Configuration
@PropertySource(value = { "classpath:application-${spring.profile.active}.properties" })
//we avoid AppConfig.class so that EnableAsync is not activated, we dont need asynce for test purposes
@ComponentScan(basePackages = { "com.samsonan.bplaces" },
	excludeFilters = {@ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, value = AppConfig.class)})
@Profile("junit_test")
public class TestContext {

    @Bean
    public MailSender mailSender() {
        return Mockito.mock(MailSender.class);
    }
    

}
