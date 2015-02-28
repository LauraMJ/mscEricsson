package com.ericsson.msc.group5.users;

public class User {

	private String username;
	private String password;
	private UserType user;

	private static enum UserType {
		NETWORK_MANAGER, CUSTOMER_SERVICE_REP, SUPPORT_ENGINEER;
	};

	public User() {

	}

	public User(String username, String password, UserType userType) {
		setUsername(username);
		setPassword(password);
		setUserType(userType);
	}

	private void setAccessRights() {
		switch (user) {
			case CUSTOMER_SERVICE_REP:
				break;
			case NETWORK_MANAGER:
				break;
			case SUPPORT_ENGINEER:
				break;
			default:
				break;
		}
	}

	/**
	 * @return the user
	 */
	public UserType getUserType() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUserType(UserType userType) {
		this.user = userType;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}