package com.ericsson.msc.group5.services;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface UserService {

	public void addUser(String username, String password, int type);
}
