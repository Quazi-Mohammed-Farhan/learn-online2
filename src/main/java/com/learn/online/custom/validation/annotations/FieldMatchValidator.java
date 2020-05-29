package com.learn.online.custom.validation.annotations;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/***********************************************************************************************************
 * <h1>FieldMatchValidator!</h1>																					 
 *                                                                                                       
 * FieldMatchValidator belongs to  FieldMatch custom made annotation for cross checking the two given 
 * property of bean. The FieldMatcherValidator along with FieldMatch annotation is useful for 
 * password confirmation and email confirmation. Because It checks two properties
 *                                                                                                                 
 * @author  Quazi Mohammed Farhan Ali.                                                                             
 * @version 1.0           
 * @Purpose PIP Assignment to employee by Cognizant                                                                                           
 * @since   2020-05-29                                                                                                                                                                                                                  
 ************************************************************************************************************/

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

	private static Logger LOGGER = LogManager.getLogger(PasswordConstraintValidator.class);
	
	private String firstFieldName;
	private String secondFieldName;
	private String message;
	
	@Override
	public void initialize(FieldMatch constraintValication) {
		
		LOGGER.debug("FieldMatchValidator::initialize() Started");
		LOGGER.debug("Initializing the FiledMatchValidator");
    	
		this.firstFieldName = constraintValication.first();
		this.secondFieldName = 	constraintValication.second();	
		this.message = constraintValication.message();
		
		LOGGER.debug("Initializing the FiledMatchValidator");
		LOGGER.debug("FieldMatchValidator::initialize() Completed");
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		LOGGER.debug("FieldMatchValidator::isValid() Started");
    	LOGGER.debug("Cross fields matching validation. If two propeties of bean matches "
    			+ " the validation is passed.");
		
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
		
		LOGGER.debug("Validation passd = " + valid);
		LOGGER.debug("FieldMatchValidator::isValid() Completed.");
		return valid;
	}

}
