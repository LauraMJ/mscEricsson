package com.ericsson.msc.group5.services;

import javax.ejb.Local;
/**
 * User service EJB interface.
 */
@Local
public interface UserService {

	public boolean addUser(String username, String password, String userRole);
}
