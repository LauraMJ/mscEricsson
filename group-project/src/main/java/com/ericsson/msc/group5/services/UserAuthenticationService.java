package com.ericsson.msc.group5.services;

import javax.ejb.Local;
/**
 * UserAuthentication service EJB interface.
 */
@Local
public interface UserAuthenticationService {
	public int authenticateUser(String username, String password);
}
