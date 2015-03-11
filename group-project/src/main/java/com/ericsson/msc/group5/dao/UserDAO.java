package com.ericsson.msc.group5.dao;

import com.ericsson.msc.group5.entities.User;
/**
 * A Data Access Object interface for the User entity. Defines common
 * DAO methods.
 */
public interface UserDAO {

	/**
	 * Add a User 
	 */
	public void addUser(User user);
	
	/**
	 * Retrieve the User 
	 */
	public User getUser(String username);
}
