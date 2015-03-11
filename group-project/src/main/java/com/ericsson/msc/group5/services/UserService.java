package com.ericsson.msc.group5.services;

import java.util.Collection;

import javax.ejb.Local;
/**
 * User service EJB interface.
 */
@Local
public interface UserService {

	public void addUser(String username, String password, int type);
}
