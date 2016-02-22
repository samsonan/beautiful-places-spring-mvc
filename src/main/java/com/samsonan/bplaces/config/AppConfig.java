package com.samsonan.bplaces.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring annotation configuration to scan the service classes.
 *
 * TODO: tutorial: //http://websystique.com/springmvc/spring-4-mvc-and-hibernate4-integration-example-using-annotations/
 */

@Configuration
@ComponentScan({ "com.samsonan.bplaces" })
public class AppConfig {

}
