package com.ericsson.msc.group5.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class FailureClassTest {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	private static String INITIAL_DESCRIPTION = "HIGH PRIORITY ACCESS";
	private static String UPDATED_DESCRIPTION = "EMERGENCY";

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
	public void basicCRUDTest() throws Exception {
		int id = 0;
		int newId = 50;

		FailureClass createdFC = new FailureClass(id, INITIAL_DESCRIPTION);
		em.persist(createdFC);

		FailureClass loadedFC = em.find(FailureClass.class, id);
		assertEquals("Failed to insert", INITIAL_DESCRIPTION, loadedFC.getDescription());

		loadedFC.setDescription(UPDATED_DESCRIPTION);
		loadedFC.setFailureClass(newId);
		FailureClass updatedFC = em.find(FailureClass.class, id);
		assertEquals("Failed to update", UPDATED_DESCRIPTION, updatedFC.getDescription());
		assertEquals("Failed to update", newId, (int) updatedFC.getFailureClass());

		em.remove(updatedFC);
		FailureClass shouldBeNull = em.find(FailureClass.class, id);
		assertNull("Failed to delete", shouldBeNull);
	}

	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery("delete from com.ericsson.msc.group5.entities.FailureClass").executeUpdate();
		utx.commit();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
}
