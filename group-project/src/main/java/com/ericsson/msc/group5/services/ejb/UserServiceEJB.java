package com.ericsson.msc.group5.services.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.UserDAO;
import com.ericsson.msc.group5.entities.User;
import com.ericsson.msc.group5.services.PasswordGeneratorService;
import com.ericsson.msc.group5.services.UserService;

@Stateless
@Local
public class UserServiceEJB implements UserService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserDAO dao;
	@Inject
	private PasswordGeneratorService passwordGeneratorService;

	@Override
	public boolean addUser(String username, String password, String userRole) {
		if (dao.getUser(username) != null)
			return false;
		dao.addUser(new User(username, passwordGeneratorService.generate(password), userRole));
		return true;
	}

	@Override
	public User getUser(String username) {
		return dao.getUser(username);
	}
}
