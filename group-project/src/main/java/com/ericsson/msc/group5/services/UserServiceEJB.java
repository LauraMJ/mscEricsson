package com.ericsson.msc.group5.services;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ericsson.msc.group5.dao.UserDAO;
import com.ericsson.msc.group5.entities.User;

@Stateless
@Local
public class UserServiceEJB implements UserService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserDAO dao;

	@Override
	public void addUser(String username, String password, int userType) {
		User user = new User(username, password, userType);
		dao.addUser(user);
	}
}
