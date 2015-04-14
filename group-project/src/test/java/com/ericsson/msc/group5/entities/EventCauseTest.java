package com.ericsson.msc.group5.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.EventCauseService;

@RunWith(Arquillian.class)
@Transactional
public class EventCauseTest {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private EventCauseService eventCauseService;

	private static String INITIAL_DESCRIPTION = "RRC CONN SETUP-UE BEARERS REJECTED DUE TO ARP ADM REJ AND LICENSES MISSING";
	private static String UPDATED_DESCRIPTION = "UE CTXT RELEASE-UNKNOWN OR ALREADY ALLOCATED ENB UE S1AP ID";


	@Test
	@Transactional(TransactionMode.ROLLBACK)
	public void basicCRUDTest() throws Exception {
		EventCauseCK pk = new EventCauseCK();
		pk.setCauseCode(1);
		pk.setEventId(1);
		EventCause createdEC = new EventCause(pk, INITIAL_DESCRIPTION);
		eventCauseService.addEventCause(createdEC);

		ArrayList <EventCause> eventCauses = new ArrayList <>();
		Collection <?> eventCausesCollection = eventCauseService.getCauseCode();

		for (Object e : eventCausesCollection) {
			eventCauses.add((EventCause) e);
		};
		EventCause loadedEC = null;
		for (EventCause f : eventCauses) {
			if (f.getCauseCodeEventIdCK().equals(pk)) {
				loadedEC = f;
				break;
			}
		}

		assertEquals("Failed to insert", INITIAL_DESCRIPTION, loadedEC.getDescription());

		loadedEC.setDescription(UPDATED_DESCRIPTION);
	}

	@Test
	@Transactional(TransactionMode.ROLLBACK)
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
	@Transactional(TransactionMode.ROLLBACK)
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
}
