package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

//THIS IS SERVICE LAYER
@Service
public class CustomerServiceImpl implements CustomerService {

	//private field employeeDAO, so we can do dependency injection on this field
	//to delegate calls from SERVICE LAYER TO THE DAO LAYER
	@Autowired
	private CustomerDAO customerDAO;
	

	@Override
	//annotation for "begin transaction" and "commit transaction"
	@Transactional
	public List<Customer> getCustomers() {
		
		//DELEGATE CALLS TO THE DAO LAYER
		return customerDAO.getCustomers();
	}


	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {

		customerDAO.saveCustomer(theCustomer);
	}


	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		return customerDAO.getCustomer(theId);
	}


	@Override
	@Transactional
	public void deleteCustomer(int theId) {

		customerDAO.deleteCustomer(theId);
	}
}





