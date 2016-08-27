package com.jorge.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jorge.config.AppConfig;
import com.jorge.model.User;

/**
 * Unit testing with transactions
 * 
 * To test a DAO class, for example, you will need to perform database queries that won't be persisted.
 * For example, to test the DAO method to add a user, you want to make sure that the user is actually
 * created in the database, but you don't want that test user to remain in the database. Transactions help
 * you to do this with minimum effort.
 *
 *Each test method of the class will automatically:
 *		Start a new transaction
 *		Execute as normal
 *		Rollback the transaction (so any modifications to a database will be reverted)
 *
 */
@ContextConfiguration(classes = {AppConfig.class}) // Loads the Spring configuration file in Spring's context
@WebAppConfiguration // Prevents exceptions from being raised. Without it, @EnableWebMvc (in theSpring configuration) would raise the "A ServletContext is required to configure default servlet handling" exception.
public class UserDAOTestNGTest extends AbstractTransactionalTestNGSpringContextTests{ // Automatically revert the database modifications performed by a test method

	@Autowired
	private UserDAO userDAO;
	
	/**
	 * Saving an object
	 * 
	 * Save an object in the database; a row will be added to
	 * the corresponding database table
	 *
	 * Define an SQL insert query with question marks as placeholders for the actual row values. Use the
	 * update() method to execute the query using the actual values from the object
	 * 
	 * Examples with @BeforeClass and @AfterClass annotations to get connection and close connection with DB without use connection defined in AppConfig.java:
	 * 		https://www.seleniumeasy.com/selenium-tutorials/database-testing-example-with-selenium-using-java
	 */
	
	@DataProvider
	public Object[][] values() {
		return new Object[][] {
			new Object[] {new User("Name1", 31)},
			new Object[] {new User("Name2", 32)},
			new Object[] {new User("Name3", 33)},
			new Object[] {new User("Name4", 34)}
		};
	}
	
	@Test(dataProvider = "values")
	public void testAdd(User user) {
		userDAO.add(user);
	}
	
}
