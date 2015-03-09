package com.ericsson.msc.group5.users;

import java.util.Collection;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;
import com.ericsson.msc.group5.dao.UserDAO;

// Business logic here
@Stateless
@Local
@Path("/user-service")
public class UserServiceEJB implements UserService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserDAO dao;

	@Override
	public Collection <User> getUsers() {
		return dao.getUsers();

	}

	@Override
	public void addUsers(Collection <User> users) {

	}
}
