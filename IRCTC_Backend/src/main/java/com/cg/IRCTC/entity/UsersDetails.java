package com.cg.IRCTC.entity;

public class UsersDetails {

	private String role;
	private String userName;
	private String password;
	
	
	public UsersDetails(String role, String userName, String password) {
		super();
		this.role = role;
		this.userName = userName;
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	
}
