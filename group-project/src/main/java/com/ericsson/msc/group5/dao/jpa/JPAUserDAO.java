package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ericsson.msc.group5.dao.UserDAO;
import com.ericsson.msc.group5.entities.User;

public class JPAUserDAO implements UserDAO {

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

	@Override
	public User getUser(String username) {
		List<User> resultList = em.createNamedQuery("findUserByUsername").setParameter("username", username).getResultList();
		if(resultList.size() == 0)
			return null;
		return resultList.get(0);
	}
}
