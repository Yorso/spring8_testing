/**
 * This is a configuration class
 * 
 */

package com.jorge.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.jorge.controller", "com.jorge.dao" })
public class AppConfig{

	/**
	 * If we aren't going to use Tiles, uncomment jspViewResolver() method and
	 * comment tilesConfigurer() and tilesViewResolver() methods
	 *
	 * Necessary to authenticating users using a CUSTOM login page, not for DEFAULT page
	 * 
	 */
	 @Bean 
	 public ViewResolver jspViewResolver(){ 
		 InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		 resolver.setViewClass(JstlView.class);
		 resolver.setPrefix("/WEB-INF/jsp/"); // These folders must be created on /src/main/webapp/ 
		 resolver.setSuffix(".jsp"); 
		 return resolver; 
	 }
	 
	/***************
	 *  DATABASES  *
	 ***************/ 
	// Database connection details
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test1");
		dataSource.setUsername("user1");
		dataSource.setPassword("user1pass");
		return dataSource;
	}
	
	/**
	 * It is a Spring object that provides convenient methods to query a database
	 * using JDBC. It uses the previously defined DataSource bean (above). We will use the JdbcTemplate bean
	 * from our DAO classes
	 * 
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	/**
	 * Reverting incomplete database modifications using transactions
	 * Some database modifications involve several SQL queries, for example, inserting an object with
	 * attributes spread across several tables. If one of the queries fails, we would want to undo any
	 * previous ones that were successful
	 * 
	 */
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}

}