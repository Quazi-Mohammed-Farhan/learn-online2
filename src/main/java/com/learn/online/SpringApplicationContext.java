package com.learn.online;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**************************************************************************************************************
 * <h1>SpringApplicationContext!</h1>																					 
 *  	
 *Here I implemented application context to access the spring manage beans in Authentication and authorization
 * filters because we cannot use spring autowiring features in filters hence to bridge the gap I did it. 
 * Filter need spring managed bean of StudentServiceImpl to access the student detail attempt to login. 
 * StudentServideImple has loadUserByUsername method this method providesthe instance of UserPrincipal that
 *  provides all authorization, role information from db to filters bean        
 *                                                                                                                 
 * @author  Quazi Mohammed Farhan Ali.                                                                             
 * @version 1.0           
 * @Purpose PIP Assignment to employee by Cognizant                                                                                           
 * @since   2020-05-29                                                                                                                                                                                                                  
 **************************************************************************************************************/


@Component
public class SpringApplicationContext implements ApplicationContextAware {

	private static ApplicationContext APPLICATION_CONTEXT;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		APPLICATION_CONTEXT = applicationContext;

	}
	
	public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return APPLICATION_CONTEXT.getBean(name, requiredType);
		
	}

}
