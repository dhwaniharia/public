package com.callsign.UserAuthentication.exception;

public class InvalidUserCredentails extends Exception {

	private String errorMessage = null;
	public InvalidUserCredentails()
	{
		super();
	}
	public InvalidUserCredentails(String errorMessage)
	{
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage()
	{
		return this.errorMessage;
	}

}
