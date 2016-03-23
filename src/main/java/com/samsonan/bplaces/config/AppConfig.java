package com.samsonan.bplaces.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Spring annotation configuration to scan the service classes.
 */

@Configuration
@ComponentScan(basePackages = { "com.samsonan.bplaces" } /*, 
		excludeFilters = {@ComponentScan.Filter(type=FilterType.REGEX, pattern="com.samsonan.bplaces.test.*")}*/)
@EnableAsync
public class AppConfig {

}
