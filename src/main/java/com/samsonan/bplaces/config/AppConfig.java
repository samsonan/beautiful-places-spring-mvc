package com.samsonan.bplaces.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring annotation configuration to scan the service classes.
 */

@Configuration
@ComponentScan({ "com.samsonan.bplaces" })
public class AppConfig {

}
