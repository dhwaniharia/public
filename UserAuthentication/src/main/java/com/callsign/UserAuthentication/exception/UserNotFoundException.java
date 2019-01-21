package com.callsign.UserAuthentication.exception;

public class UserNotFoundException extends Exception {

	private String errorMessage = null;
	public UserNotFoundException()
	{
		super();
	}
	public UserNotFoundException(String errorMessage)
	{
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage()
	{
		return this.errorMessage;
	}

}
