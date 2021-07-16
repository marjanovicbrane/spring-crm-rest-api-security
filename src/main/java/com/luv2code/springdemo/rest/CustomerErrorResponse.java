package com.luv2code.springdemo.rest;

//1.HERE WE CREATED OUR CUSTOM ERROR RESPONSE CLASS
//THIS CLASS WILL BE MAPPED BY JACKSON AND THEN HE WILL CALL APPROPRIATE GETTER METHOD TO CONVERT JAVA POJO TO JSON,
//AND RETURN THAT RESULT TO CALLING PROGRAM (REST CLIENT).
public class CustomerErrorResponse {

	//IN OUR CUSTOM ERROR MESSAGE WE WANT TO HAVE:404 STATUS,ERROR MESSAGE AND TIME STAMP
	private int status;
	private String message;
	private long timeStamp;
	
	//default constructor
	public CustomerErrorResponse() {
		
	}

	//constructor with all fields
	public CustomerErrorResponse(int status, String message, long timeStamp) {
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	//getter and setter methods
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
