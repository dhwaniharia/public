package com.callsign.UserAuthentication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.callsign.UserAuthentication.exception.InvalidUserCredentails;
import com.callsign.UserAuthentication.exception.UserNotFoundException;
import com.callsign.UserAuthentication.model.Credentials;
import com.callsign.UserAuthentication.model.User;
import com.callsign.UserAuthentication.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAuthenticationApplicationTests {

	@Autowired
	UserService userService;
	
	@Test
	public void testUserAuthenticationSuccess() throws UserNotFoundException, InvalidUserCredentails
	{
		Credentials credentials = new Credentials();
		credentials.setUsername("user1");
		credentials.setPassword("secret1");
		User user = userService.authenticateUser(credentials);
		assertEquals("user1", user.getUsername());
	}
	
	@Test(expected = InvalidUserCredentails.class)
	public void testInvalidCredentials() throws UserNotFoundException, InvalidUserCredentails
	{
		Credentials credentials = new Credentials();
		credentials.setUsername("user1");
		credentials.setPassword("secret123");
		User user = userService.authenticateUser(credentials);
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testInvalidUsername() throws UserNotFoundException, InvalidUserCredentails
	{
		Credentials credentials = new Credentials();
		credentials.setUsername("user1123");
		credentials.setPassword("secret123");
		User user = userService.authenticateUser(credentials);
	}

}

