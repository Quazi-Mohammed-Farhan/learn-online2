package com.learn.online.custom.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface CheckTokenSize {
	
	String message() default "{feild.match.constraint}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String[] value();
}
