package com.ericsson.msc.group5.dao;

import java.util.Collection;

import com.ericsson.msc.group5.entities.User;

public interface UserDAO {

	public Collection <User> getUsers();

	public void addUser(User user);
	
	public User getUser(String username);
}
