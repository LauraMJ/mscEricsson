package com.ericsson.msc.group5.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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

@RunWith(Arquillian.class)
public class EventCauseTest {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	private static String INITIAL_DESCRIPTION = "RRC CONN SETUP-UE BEARERS REJECTED DUE TO ARP ADM REJ AND LICENSES MISSING";
	private static String UPDATED_DESCRIPTION = "UE CTXT RELEASE-UNKNOWN OR ALREADY ALLOCATED ENB UE S1AP ID";

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
		EventCauseCK pk = new EventCauseCK();
		pk.setCauseCode(1);
		pk.setEventId(1);
		EventCause createdEC = new EventCause(pk, INITIAL_DESCRIPTION);
		em.persist(createdEC);

		EventCause loadedEC = em.find(EventCause.class, pk);
		assertEquals("Failed to insert", INITIAL_DESCRIPTION, loadedEC.getDescription());

		loadedEC.setDescription(UPDATED_DESCRIPTION);
		EventCause updatedEC = em.find(EventCause.class, pk);

		assertEquals("Failed to update", UPDATED_DESCRIPTION, updatedEC.getDescription());

		em.remove(updatedEC);
		EventCause shouldBeNull = em.find(EventCause.class, pk);
		assertNull("Failed to delete", shouldBeNull);
	}

	@Test
	public void compositeKeyTest() {
		int oldCauseCode = 21;
		int oldEventId = 45;
		Integer newCauseCode = 5000;
		Integer newEventId = 241;
		EventCauseCK ck = new EventCauseCK(oldCauseCode, oldEventId);
		ck.setCauseCode(newCauseCode);
		ck.setEventId(newEventId);
		assertEquals("failed to set cause code", newCauseCode, ck.getCauseCode());
		assertEquals("failed to set event id", newEventId, ck.getEventId());
		EventCauseCK ckCopy = new EventCauseCK(newCauseCode, newEventId);
		assertEquals("the two objects should be equal since they have the same state", ck, ckCopy);
	}
	
	@Test
	public void testEquality(){
		EventCauseCK pk = new EventCauseCK(1, 1);
		EventCauseCK other = new EventCauseCK(1, 1);
		
		assertTrue(pk.equals(other));
		assertFalse(pk.equals(null));
		assertFalse(pk.equals(new Integer(0)));
		assertFalse(pk.equals(new EventCauseCK(0, 1)));
		assertFalse(pk.equals(new EventCauseCK(1, 0)));
		assertTrue(pk.hashCode() == (other.hashCode()));
		
		EventCause eventCause = new EventCause(pk, "description");
		EventCause otherEventCause = new EventCause(other, "description");
		
		assertTrue(eventCause.equals(otherEventCause));
		assertFalse(eventCause.equals(null));
		assertFalse(eventCause.equals(new Integer(0)));
		assertFalse(eventCause.equals(new EventCause(new EventCauseCK(0, 1), "description")));
		assertFalse(eventCause.equals(new EventCause(new EventCauseCK(1, 0), "description")));
		assertTrue(eventCause.hashCode() == (otherEventCause.hashCode()));
	}

	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery("delete from com.ericsson.msc.group5.entities.EventCause").executeUpdate();
		utx.commit();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
}
