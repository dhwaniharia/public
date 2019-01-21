package com.callsign.UserAuthentication.service;

import com.callsign.UserAuthentication.exception.InvalidUserCredentails;
import com.callsign.UserAuthentication.exception.UserNotFoundException;
import com.callsign.UserAuthentication.model.Credentials;
import com.callsign.UserAuthentication.model.User;

public interface UserService {
	
	public User authenticateUser(Credentials credentials)  throws UserNotFoundException, InvalidUserCredentails ;
}
