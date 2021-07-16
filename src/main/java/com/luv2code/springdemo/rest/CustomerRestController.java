package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

//THIS IS CONTROLLER LAYER
@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	//private field customerService, so we can do dependency injection on this field
	//to delegate calls from CONTROLLER LAYER TO THE SERVICE LAYER
	@Autowired
	private CustomerService customerService;
	
		//****************************************************************************************************************
		//WE WILL USING POSTMAN REST CLIENT TO SHOW ALL RESULTS FROM THE HTTP REQUEST METHODS.
		//WE ADDED DEPENDENCY IN POM FILE FOR JACKSON , SO WHEN WE RUN OUR APP
		//AND ENTER THIS ENDPOINT /api/employees (GET) WI WILL HAVE RESPONSE OBJECTS FROM THE DATABASE IN .JSON FORMAT.
		//THAT DATA WE CAN SHOW IN BROWSER OR SOME OTHER REST CLIENT LIKE POSTMAN.
		//WHEN WE GET JAVA OBJECTS FROM THE DATABASE, SPRING BOOT HERE CALLS JACKSON TO CONVERT JAVA POJO IN .JSON FORMAT.
		//WHEN WE CONVERT JAVA POJO TO JSON--->JACKSON CALLS GETTER METHODS
		//WHEN WE CONVERT JSON TO JAVA POJO--->JACKSON CALLS SETTER METHODS
		//*****************************************************************************************************************
		
		
		//add mapping for GET /customers
		@GetMapping("/customers")
		public List<Customer> getCustomers(){
			
			//delegate calls from the CONTROLLER LAYER to the SERVICE LAYER
			return customerService.getCustomers();
			
		}
		
		
		// add mapping for GET /customers/{customerId}
		@GetMapping("/customers/{customerId}")
		public Customer getCustomer(@PathVariable int customerId) {
			
			//if customer is null, Jackson will return empty JSON body
			Customer customer = customerService.getCustomer(customerId);
			
			//3.and we want to do this, if customer is null we did like to throw an CustomerNotFoundException exception
			if (customer == null) {

				throw new CustomerNotFoundException("Customer id not found - " + customerId);
			}
			
			return customer;
		}
		
		
		// add mapping for POST /customers  - add new customer
		//WITH ANNOTATION @RequestBody, WE BINDING THAT OBJECT FROM .JSON TO THIS Customer customer OBJECT
		//IN POSTMAN REST CLIENT IN REQUEST BODY WE PASSING .JSON OBJECT TO BE SAVED TO DATABASE.
		//IN THE BACKGROUND JACKSON CALLING SETTER METHODS TO SET OUR CUSTOMER OBJECT TO DATA FROM .JSON.
		//WHEN WE PASS AN OBJECT IN REQUEST BODY WE DON'T PASS ID,BECAUSE ID IS AUTOMATICALLY GENERATED FROM THE DATABASE.
		//BECAUSE OF THAT, JUST IN CASE WE SET ID TO 0.IF USER PASS ID BY ACCIDENT 
		@PostMapping("/customers")
		public Customer addEmployee(@RequestBody Customer customer) {
			
			//also just in case the pass an id in JSON ... set id to 0
			//this is force a save of new item ... instead of update
			customer.setId(0);
			
			//WE ARE USING THIS SAME METHOD FOR SAVE AND FOR UPDATE
			customerService.saveCustomer(customer);
			
			return customer;
		}
		
		
		// add mapping for PUT /customers - update existing customer
		//WHEN WE PASS AN OBJECT IN REQUEST BODY WE ALSO PASSING ID THIS TIME, BECAUSE WE NEED TO DO AN UPDATE
		@PutMapping("/customers")
		public Customer updateEmployee(@RequestBody Customer customer) {
			
			//WE ARE USING THIS SAME METHOD FOR SAVE AND FOR UPDATE
			customerService.saveCustomer(customer);
			
			return customer;
		}
		
		
		// add mapping for DELETE /customers/{customerId} - delete customer
		@DeleteMapping("/customers/{customerId}")
		public String deleteEmployee(@PathVariable int customerId) {
			
			//get customer from database with id customerId
			Customer customer = customerService.getCustomer(customerId);
			
			//ID employee with that id doesn't exist, throw runtime exception
			if (customer == null) {
				throw new CustomerNotFoundException("Customer id not found - " + customerId);
			}
			
			//else delete customer with that id
			customerService.deleteCustomer(customerId);
			
			//return a message that 
			return "Deleted customer id - " + customerId;
		}
		
}
