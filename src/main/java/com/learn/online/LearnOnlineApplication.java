package com.learn.online;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class LearnOnlineApplication {

	
	private static Logger LOGGER = LoggerFactory.getLogger("timeBased");
	public static void main(String[] args) {
		
		LOGGER.info("Spring main application starting... LearnOnlineApplication");

		SpringApplication.run(LearnOnlineApplication.class, args);
		
		LOGGER.info("Spring main application Completed. LearnOnlineApplication");
		
	}
	
	/*
	 * We made the resource bundle message source and
	 * added key value pair message for validation
	 * purpose that are use by JSR330 validation
	 * annotation
	 */
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	     
	    messageSource.setBasename("classpath:messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
	
	
	/*
	 * This is our local validator that uses our message source
	 * resource bundle to fetch the validation messages for 
	 * validation annotations
	 */
	@Bean
	public LocalValidatorFactoryBean getValidator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}
	
}
