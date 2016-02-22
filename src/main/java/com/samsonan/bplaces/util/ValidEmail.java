package com.samsonan.bplaces.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * tutorial: http://www.javacodegeeks.com/2013/07/spring-mvc-custom-validation-annotations.html
 *
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE}) 
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {   
    String message() default "{ValidEmail}";
    Class<?>[] groups() default {}; 
    Class<? extends Payload>[] payload() default {};
}
