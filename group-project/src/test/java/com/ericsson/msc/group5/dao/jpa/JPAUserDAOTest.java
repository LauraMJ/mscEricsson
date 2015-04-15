package com.ericsson.msc.group5.dao.jpa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.UserService;

@RunWith(Arquillian.class)
@Transactional
public class JPAUserDAOTest {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserService userService;

	@Test
	@Transactional(TransactionMode.ROLLBACK)
	public void addUserTest() {
		// User user = new User("user", "password", "administrator");
		userService.addUser("user", "password", "administrator");
		assertNotNull(userService.getUser("user"));
	}

	@Test
	@Transactional(TransactionMode.ROLLBACK)
	public void getUserTest() {
		userService.addUser("user", "password", "administrator");
		assertNotNull(userService.getUser("user"));
		assertTrue(userService.getUser("user").getRole() == "administrator");
	}

}
