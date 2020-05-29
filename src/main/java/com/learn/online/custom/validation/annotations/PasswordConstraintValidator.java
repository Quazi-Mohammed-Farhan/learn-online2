package com.learn.online.custom.validation.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***********************************************************************************************************
 * <h1>PasswordConstraintValidator!</h1>																					 
 *                                                                                                       
 * PasswordConstraintValidator belongs to custom made annotation for validating password.  
 * Its partner annotation is ValidPassword. Both of them is used to check password. They check following things
 * for validating password.
 * 1. If password has at least one special character, atleast one digital value, at least 
 *    one lower case letter and at least  one upper case letter and 
 * 2. Size of password should betweeen 8 to 35 character long.
 *                                                                                                                 
 * @author  Quazi Mohammed Farhan Ali.                                                                             
 * @version 1.0           
 * @Purpose PIP Assignment to employee by Cognizant                                                                                           
 * @since   2020-05-29                                                                                                                                                                                                                  
 ************************************************************************************************************/

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

	private static Logger LOGGER = LogManager.getLogger(PasswordConstraintValidator.class);
	
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
      
    	LOGGER.debug("PasswordConstraintValidator::isValid() Started");
    	LOGGER.debug("Validating password constraints.");
    	
    	PasswordValidator validator = new PasswordValidator(Arrays.asList(
        		
                // at least 8 characters
                new LengthRule(8, 30),

                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // at least one symbol (special character)
                new CharacterRule(EnglishCharacterData.Special, 1),

                // no whitespace
                new WhitespaceRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()) {
        	LOGGER.debug("Validation is successful");
        	LOGGER.debug("PasswordConstraintValidator::isValid() Competed");
            return true;
        }

        List<String> messages = validator.getMessages(result);
        String messageTemplate = messages.stream().collect(Collectors.joining(","));
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        
        LOGGER.debug("Validation is failed");
    	LOGGER.debug("PasswordConstraintValidator::isValid() Competed");
        return false;
    }
}