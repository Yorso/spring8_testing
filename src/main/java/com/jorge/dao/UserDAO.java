package com.jorge.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jorge.model.User;

@Repository //Necessary to use DAO and Spring. It allows the UserDAO class to be automatically discovered and instantiated as a bean
@Transactional // Necessary to reverting incomplete database modifications using transactions.
			   // @Transactional will enclose each DAO method in a BEGIN...COMMIT SQL block. So if there's an
			   // error (a runtime exception), any modification made by the DAO method to the database will be rolled back.
public class UserDAO {
	
	@Autowired // Dependency injection
	private JdbcTemplate jdbcTemplate; // This field will be initialized automatically by Spring via dependency injection with
									   // the JdbcTemplate bean defined previously in AppConfig.java

	/**
	 * Retrieve database rows and create a list of objects from them.
	 * 
	 * The query() method uses RowMapper to generate objects from the returned database rows.
	 * We used a ParameterizedBeanPropertyRowMapper class assuming that the database table columns
	 * match the object attributes; however, as in the previous recipe, a custom RowMapper interface can be used
	 * 
	 * Perform an SQL select query and generate a list of objects from the result using RowMapper
	 * 
	 */
	public List<User> findAll() {
		String sql = "select * from user";
		List<User> userList = jdbcTemplate.query(sql, new UserMapper());
		return userList;
	}
	
	
	/********************
	 *  INLINE CLASSES  *
	 ********************/
	
	/**
	 * This is an inline class implementing RowMapper. This class defines how to
	 * generate an User object from a database row
	 *
	 */
	private class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet row, int rowNum) throws SQLException {
			User user = new User();
			user.setId(row.getLong("id"));
			user.setFirstName(row.getString("first_name"));
			user.setAge(row.getInt("age"));
			return user;
		}
	}
	
	
	
}
