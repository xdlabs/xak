package com.xenondigilabs.alpha.commons.core.types;
/*
 * For Output
 * */
public class Result {

	//to identify response is error or success
	boolean status;
	String message;//Appropriate message according to status

	public Result() {}
	//set value of status
	public Result(boolean status) {
		super();
		this.status = status;
	}
	//set value of status and message
	public Result(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	//Accessor and Mutators 
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean isSuccess()
	{
		if(status)
			return true;
		return false;
		
	}
	public boolean isError()
	{
		if(status)
			return true;
		return false;
	}
	
	
}
