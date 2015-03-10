package com.ericsson.msc.group5.dao;

import com.ericsson.msc.group5.entities.User;

public interface UserDAO {

	public void addUser(User user);
	
	public User getUser(String username);
}
