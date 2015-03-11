package com.ericsson.msc.group5.services.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import com.ericsson.msc.group5.dao.UserDAO;
import com.ericsson.msc.group5.entities.User;
import com.ericsson.msc.group5.services.UserAuthenticationService;

@Stateless
@Local
public class UserAuthenticationServiceEJB implements UserAuthenticationService{
	
	@Inject
	private UserDAO userDAO;
	
	public int authenticateUser(String username, String password){
		User user = userDAO.getUser(username);
		if(user != null && user.getPassword().equals(password))
			return user.getUserType();
		return -1;
	}
}
