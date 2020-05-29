package com.learn.online.custom.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/***********************************************************************************************************
 * <h1>FieldMatch!</h1>																					 
 *                                                                                                       
 * FeildMatch is custom made annotation for cross checking the two given property of bean. This annotation
 * of validation is useful for password confirmation and email confirmation. Because It checks two 
 * properties of class hence made it class level validation annotation. This annotation has it validator
 * which name is FieldMatcherValidator.
 *                                                                                                                 
 * @author  Quazi Mohammed Farhan Ali.                                                                             
 * @version 1.0           
 * @Purpose PIP Assignment to employee by Cognizant                                                                                           
 * @since   2020-05-29                                                                                                                                                                                                                  
 ************************************************************************************************************/

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch {
	
	String message() default "{feild.match.constraint}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String first();
	String second();
	
	
	@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		
		FieldMatch[] value();
	}
}
