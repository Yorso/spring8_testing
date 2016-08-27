package com.jorge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import com.jorge.config.AppConfig;
import com.jorge.dao.UserDAO;
import com.jorge.model.User;

/**
 * Unit testing with TestNG 6 using Spring's application context
 * 
 * TestNG tests are run outside Spring; Spring is not initialized before the tests are run. To be able to
 * use the beans defined in the configuration files and dependency injection, some bootstrapping code
 * needs to be added to the test class.
 * 
 * You can choose to use a separate Spring configuration class to run your tests:
 * 		@ContextConfiguration(classes = {AppTestConfig.class})
 * 
 * You can also use Spring's main configuration in combination with a test-specific configuration:
 * 		@ContextConfiguration(classes = {AppConfig.class, AppTestConfig.class})
 * 
 * The order in which the classes are declared matters. In this example, beans from AppConfig can be
 * overridden in AppTestConfig . For example, you could choose to override a MySQL datasource by
 * an in-memory database datasource for your tests.
 *
 */
@ContextConfiguration(classes = {AppConfig.class}) // Loads the Spring configuration file in Spring's context
@WebAppConfiguration // Prevents exceptions from being raised. Without it, @EnableWebMvc (in theSpring configuration) would raise the "A ServletContext is required to configure default servlet handling" exception.
public class TestControllerTestNGTest extends AbstractTestNGSpringContextTests{ // Extending AbstractTestNGSpringContextTests initializes Spring's context and makes it available to the test class

	@Autowired
	private UserDAO userDAO;
	
	@Test
	public void testListUsers() {
		List<User> users = userDAO.findAll();
		
		for(User user : users){
			System.out.println("Name: " + user.getFirstName());
			System.out.println("Age: " + user.getAge());
		}
	}
	
}
