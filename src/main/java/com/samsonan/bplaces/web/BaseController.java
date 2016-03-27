package com.samsonan.bplaces.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public abstract class BaseController {

    public static final String FLASH_CSS_ATTR = "css"; //to super
    public static final String FLASH_MSG_ATTR = "msg"; //to super

    public static final String FLASH_CSS_VALUE_OK = "success"; //to super
    public static final String FLASH_CSS_VALUE_ERROR = "danger"; //to super

    public static final String VIEW_404 = "/error/404"; //to super
	
	@Autowired
    private MessageSource messageSource;
	
	/**
	 * Utility methods - move to super
	 */
    protected String createRedirectViewPath(String requestMapping) {
        StringBuilder redirectViewPath = new StringBuilder();
        redirectViewPath.append("redirect:");
        redirectViewPath.append(requestMapping);
        return redirectViewPath.toString();
    }
    
    protected String getMessage(String messageCode, Object... messageParameters) {
        Locale current = LocaleContextHolder.getLocale();
        return messageSource.getMessage(messageCode, messageParameters, current);
    }

}
