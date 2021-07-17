
package com.brane.springdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
//THIS IS JAVA CONFIG CLASS.WE ARE USING PURE JAVA CONFIG, NO XML
@Configuration
//@EnableWebSecurity annotation is used to enable SpringSecurity in our project.
@EnableWebSecurity
//We need to extend WebSecurityConfigurerAdapter class.
//It allows configuring things that impact all of web security.
//WebSecurityConfigurerAdapter allows customization to both WebSecurity and HttpSecurity.
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//Add our users for in memory authentication.
		//Method withDefaultPasswordEncoder() using PLAIN TEXT(default password).
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		
		//Memory authentication
		auth.inMemoryAuthentication()
			.withUser(users.username("jeff").password("test123").roles("EMPLOYEE"))
			.withUser(users.username("elon").password("test123").roles("EMPLOYEE", "MANAGER"))
			.withUser(users.username("brane").password("test123").roles("EMPLOYEE", "ADMIN"));
	}

	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Secures all REST endpoints under "/api/customers" and adds following security authorizations:

		// EMPLOYEE role can perform following:
		// 1. Get a list of all customers.  GET /api/customers
		// 2. Get a single customer.  GET /api/customers/{customerId}
		
		// MANAGER role can perform following:
		// 1. Add a new customer.  POST /api/customers
		// 2. Update an existing customer.  PUT /api/customers
		
		// ADMIN role can perform following:
		// 1. Delete a customer.  DELETE /api/customers/{customerId}

		http.authorizeRequests()

		.antMatchers(HttpMethod.GET, "/api/customers").hasRole("EMPLOYEE")
		.antMatchers(HttpMethod.GET, "/api/customers/**").hasRole("EMPLOYEE")

		.antMatchers(HttpMethod.POST, "/api/customers").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/customers/**").hasAnyRole("MANAGER", "ADMIN")

		.antMatchers(HttpMethod.PUT, "/api/customers").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/customers/**").hasAnyRole("MANAGER", "ADMIN")

		.antMatchers(HttpMethod.DELETE, "/api/customers/**").hasRole("ADMIN")
		.and()
		//whith this method we will enable http basic autentication for our app.
		//When we don't have our custom login page, we need to use this method httpBasic(),
		//because this method provides us default login page.
		.httpBasic()
		.and()
		//CSRF (Cross Site Request Forgery) is a technique in which an attacker attempts to trick you into 
		//performing an action using an existing session of a different website.
		//Spring Security when combined with Thymeleaf templates, automatically inserts a token 
		//into all web forms as a hidden field.
		
		//when we are have REST SERVICE, then we don't need CSRF.
		//Recommendation is to use CSRF protection for any request that could be processed by a browser 
		//by normal users. If you are only creating a service that is used by non-browser clients, 
		.csrf().disable()
		
		//We can control exactly when our session gets created and how Spring Security will interact with it:
		//always – a session will always be created if one doesn't already exist
		//ifRequired – a session will be created only if required (default)
		//never – the framework will never create a session itself but it will use one if it already exists
		//stateless – no session will be created or used by Spring Security
		//option – “stateless” – is a guarantee that the application will not create any session at all.
		//For our application, we would like avoid the use of cookies for sesson tracking. 
		//This should force the REST client to enter user name and password for each request.
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}	

}