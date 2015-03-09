package com.ericsson.msc.group5.dao.jpa;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.UserDAO;
import com.ericsson.msc.group5.users.User;

public class JPAUserDao implements UserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Collection <User> getUsers() {
		return null;
	}

	@Override
	public void addUser(User user) {
		em.persist(user);
	}

}
