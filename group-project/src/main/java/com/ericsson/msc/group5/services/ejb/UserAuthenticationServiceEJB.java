package com.ericsson.msc.group5.services.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import com.ericsson.msc.group5.dao.UserDAO;
import com.ericsson.msc.group5.entities.User;
import com.ericsson.msc.group5.services.UserAuthenticationService;
import com.ericsson.msc.group5.utils.PasswordGenerator;

@Stateless
@Local
public class UserAuthenticationServiceEJB implements UserAuthenticationService {

	@Inject
	private UserDAO userDAO;

	public String authenticateUser(String username, String password) {
		User user = userDAO.getUser(username);
		System.out.println("password is " + password);
		System.out.println("password is " + PasswordGenerator.generate(password));
		if (user != null && user.getPassword().equals(PasswordGenerator.generate(password)))
			return user.getRole();
		return null;
	}
}
