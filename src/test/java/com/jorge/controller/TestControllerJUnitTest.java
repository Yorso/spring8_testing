package com.jorge.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jorge.config.AppConfig;
import com.jorge.dao.UserDAO;
import com.jorge.model.User;

/**
 * Unit testing with JUnit 4 using Spring's application context
 * 
 * JUnit tests are run outside Spring; Spring is not initialized before the tests are run. To be able to use
 * the beans defined in the configuration files and dependency injection, some bootstrapping code needs
 * to be added to the test class
 * 
 * You can choose to use a separate Spring configuration class to run your tests:
 * 		@ContextConfiguration(classes = {AppTestConfig.class})
 * 
 * You can also use the Spring main configuration class in combination with a test-specific configuration class:
 * 		@ContextConfiguration(classes = {AppConfig.class, AppTestConfig.class})
 * 
 * The order in which the classes are declared matters. In this example, beans from AppConfig can be
 * overridden in AppTestConfig . For example, you could choose to override a MySQL datasource by
 * an in-memory database datasource for your tests.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) // Executes the test with the Spring runner instead of the default JUnit runner. A runner is a class that runs a JUnit test
@ContextConfiguration(classes = {AppConfig.class}) // Loads the Spring configuration class and makes the class's beans available
@WebAppConfiguration // Prevents exceptions from being raised. Without it, @EnableWebMvc (in theSpring configuration) would raise the "A ServletContext is required to configure default servlet handling" exception
public class TestControllerJUnitTest {

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
