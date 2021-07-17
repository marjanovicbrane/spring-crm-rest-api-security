package com.brane.springdemo.rest;

//2.THIS IS OUR CUSTOM EXCEPTION CLASS
//THIS CLASS EXTENDS RuntimeException CLASS, WHICH IS UNCHECKED EXCEPTION AND ALL UNCHECKED EXCEPTIONS
//EXTENDS THIS CLASS RuntimeException
public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException() {
	}

	//WE WILL CALL THIS CONSTRUCTOR IN OUR REST CONTROLLER, WHEN WE NEED TO THROW CustomerNotFound EXCEPTION
	public CustomerNotFoundException(String message) {
		super(message);
	}

	public CustomerNotFoundException(Throwable cause) {
		super(cause);
	}

	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
