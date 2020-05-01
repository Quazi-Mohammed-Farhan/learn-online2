package com.learn.online.custom.validation.annotations;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;


public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

	private String firstFieldName;
	private String secondFieldName;
	private String message;
	
	@Override
	public void initialize(FieldMatch constraintValication) {
		this.firstFieldName = constraintValication.first();
		this.secondFieldName = 	constraintValication.second();	
		this.message = constraintValication.message();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		boolean valid = true;
		try {
			final Object firstObject = BeanUtils.getProperty(value, firstFieldName);
			final Object secondObject = BeanUtils.getProperty(value, secondFieldName);
			
			
			valid =  firstObject == null && secondObject == null || firstObject != null && firstObject.equals(secondObject);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
		}
		
		if (!valid){
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
		
		return valid;
	}

}
