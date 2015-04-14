package com.ericsson.msc.group5.dao.jpa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.dao.UserDAO;
import com.ericsson.msc.group5.entities.User;

@RunWith(Arquillian.class)
public class JPAUserDAOTest {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserDAO dao;

	@Inject
	private UserTransaction utx;

	@Before
	public void preparePersistenceTest() throws Exception {
		clearData();
		startTransaction();
	}

	@After
	public void commitTransaction() throws Exception {
		utx.commit();
	}

	@Test
	public void addUserTest() {
		User user = new User("user", "password", "administrator");
		dao.addUser(user);
		assertNotNull(dao.getUser("user"));
	}

	@Test
	public void getUserTest() {
		assertNotNull(dao.getUser("user"));
		assertTrue(dao.getUser("user").getRole() == "administrator");
	}

	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		em.createQuery("delete from com.ericsson.msc.group5.entities.FailureTrace").executeUpdate();
		utx.commit();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
}
