package com.callsign.UserAuthentication.model;

public class User {
	
	private String userId = null;
	private String username = null;
	private String password = null;
	private String name = null;
	private String country = null;
	
	public User()
	{
		
	}
	
	public User(String userId, String username, String password, String name, String country)
	{
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.country = country;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	

}
