package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.EventCauseCK;
import com.ericsson.msc.group5.services.EventCauseService;

@RunWith(Arquillian.class)
public class EventCauseServiceEJBTest {

	@EJB
	EventCauseService service;

	@Test
	public void addEventCause() {
		EventCauseCK ckOne = new EventCauseCK(1, 1);
		EventCauseCK ckTwo = new EventCauseCK(2, 2);
		EventCauseCK ckThree = new EventCauseCK(3, 3);
		EventCause [] eventCauseArray = {new EventCause(ckOne, "first"), new EventCause(ckTwo, "second"), new EventCause(ckThree, "third")};

		Collection <EventCause> eventCauses = new ArrayList <>();
		for (EventCause e : eventCauseArray) {
			eventCauses.add(e);
		}

		service.addEventCause(eventCauses);

		Collection <EventCause> retrievedEventCauses = service.getCauseCode();

		for (EventCause e : retrievedEventCauses) {
			assertTrue("An object failed to be retrieved", eventCauses.contains(e));
			assertTrue(e.equals(e));
			assertTrue(e.hashCode() == e.hashCode());
		}

	}

}
