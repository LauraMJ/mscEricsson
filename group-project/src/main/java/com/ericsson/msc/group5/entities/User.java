package com.ericsson.msc.group5.entities;

public class User {

	private String userName;
	private String password;
	private String userType;

	public User(String userName, String password, String userType) {
		this.userName = userName;
		this.password = password;
		this.setUserType(userType);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
