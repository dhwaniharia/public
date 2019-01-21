package com.callsign.UserAuthentication.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.callsign.UserAuthentication.exception.InvalidUserCredentails;
import com.callsign.UserAuthentication.exception.UserNotFoundException;
import com.callsign.UserAuthentication.model.Credentials;
import com.callsign.UserAuthentication.model.User;

@Service
public class UserServiceImpl implements UserService {
	/**
	 * Map of Username and User, 
	 * since there is no DB used, the user objects are stored in the map in memory
	 */
	Map<String, User> userMap = new HashMap<String, User>();
	public UserServiceImpl() {
		userMap.put("user1", new User("user1","secret1", "User 1", "United kingdom"));
	}
	
	@Override
	public User authenticateUser(Credentials credentials) throws UserNotFoundException, InvalidUserCredentails {
		if(userMap.get(credentials.getUsername()) == null)
		{
			throw new UserNotFoundException("Username does not exist");
		}
		else
		{
			if(!userMap.get(credentials.getUsername()).getPassword().equals(credentials.getPassword()))
			{
				throw new InvalidUserCredentails("Invalid Username or password");
			}
		}
		return userMap.get(credentials.getUsername());
	}

	@Override
	public String logout(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
