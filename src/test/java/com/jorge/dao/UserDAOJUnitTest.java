package com.jorge.dao;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
@RunWith(SpringJUnit4ClassRunner.class) // Executes the test with the Spring runner instead of the default JUnit runner. A runner is a class that runs a JUnit test
@ContextConfiguration(classes = {AppConfig.class}) // Loads the Spring configuration class and makes the class's beans available
@WebAppConfiguration // Prevents exceptions from being raised. Without it, @EnableWebMvc (in theSpring configuration) would raise the "A ServletContext is required to configure default servlet handling" exception
@Transactional // Necessary in JUnit
public class UserDAOJUnitTest {

	@Autowired
	private UserDAO userDAO;
	
	
	@Test
	public void testAdd() {
		User user = new User();
		ArrayList<User> listUser = new ArrayList<User>();
		
		for(int i=0; i<4; i++){
			user = new User("Name" + String.valueOf(i + 1), 30 + i + 1);
			listUser.add(user);
		}
		
		for(User u : listUser){
			System.out.println("Checking user: " + u.getFirstName() + " " + u.getAge());
			userDAO.add(u);
		}
	}
}
