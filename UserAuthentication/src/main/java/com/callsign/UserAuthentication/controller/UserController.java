package com.callsign.UserAuthentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.callsign.UserAuthentication.exception.InvalidUserCredentails;
import com.callsign.UserAuthentication.exception.UserNotFoundException;
import com.callsign.UserAuthentication.model.Credentials;
import com.callsign.UserAuthentication.model.ErrorResponse;
import com.callsign.UserAuthentication.model.User;
import com.callsign.UserAuthentication.service.UserService;

@RestController
@RequestMapping(value="/application")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<User> authenticateUser(@RequestBody Credentials credentials) throws UserNotFoundException, InvalidUserCredentails
	{
		return new ResponseEntity<User>(userService.authenticateUser(credentials), HttpStatus.OK);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	 public ResponseEntity<ErrorResponse> userNotFoundExceptionHandler(Exception ex) {
		         ErrorResponse error = new ErrorResponse();
		         error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		         error.setErrorMessage(ex.getMessage());
		         return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
		     }
	
	@ExceptionHandler(InvalidUserCredentails.class)
	 public ResponseEntity<ErrorResponse> invalidUserCredentailsHandler(Exception ex) {
		         ErrorResponse error = new ErrorResponse();
		         error.setErrorCode(HttpStatus.UNAUTHORIZED.value());
		         error.setErrorMessage(ex.getMessage());
		         return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
		     }

}
