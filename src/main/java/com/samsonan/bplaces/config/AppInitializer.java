package com.samsonan.bplaces.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * DispatcherServlet configuration. JAVA alternative to web.xml
 * Only works with Servlet 3.0 container, like Apache Tomcat 7+ or Jetty 9+
 *
 */
public class AppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	/**
	 * Configuration class with beans
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	/**
	 * Map DispatcherServlet to '/'
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}