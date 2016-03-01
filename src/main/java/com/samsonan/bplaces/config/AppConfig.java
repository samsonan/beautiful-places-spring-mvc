package com.samsonan.bplaces.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Spring annotation configuration to scan the service classes.
 */

@Configuration
@ComponentScan({ "com.samsonan.bplaces" })
@EnableAsync
public class AppConfig {

}
