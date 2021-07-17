package com.brane.springdemo.dao;

import java.util.List;

import com.brane.springdemo.entity.Customer;

//we created interface with CRUD methods for customer DAO (Data Access Object)
//to access data from the database
public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
	
}
