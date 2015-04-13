package com.ericsson.msc.group5.services.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.UserDAO;
import com.ericsson.msc.group5.entities.User;
import com.ericsson.msc.group5.services.PasswordGeneratorService;
import com.ericsson.msc.group5.services.UserAuthenticationService;

@Stateless
@Local
public class UserAuthenticationServiceEJB implements UserAuthenticationService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserDAO userDAO;
	@Inject
	private PasswordGeneratorService passwordGeneratorService;

	public String authenticateUser(String username, String password) {
		User user = userDAO.getUser(username);
		System.out.println("password is " + password);
		System.out.println("password is " + passwordGeneratorService.generate(password));
		if (user != null && user.getPassword().equals(passwordGeneratorService.generate(password)))
			return user.getRole();
		return null;
	}
}
