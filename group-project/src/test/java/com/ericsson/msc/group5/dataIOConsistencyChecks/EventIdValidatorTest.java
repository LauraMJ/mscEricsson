package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;

@RunWith(Arquillian.class)
public class EventIdValidatorTest {

	private boolean result1 = true;
	private boolean result2 = false;
	private Integer eventId1 = 4097;
	private Integer eventId2 = 4098;
	private Integer eventId3 = 4125;
	private Integer eventId4 = 4106;
	private Integer eventId5 = 5000;
	private Integer eventId6 = null;

	@Inject
	ValidatorServiceEJB service;

	@Test
	public void validateEventId() {
		assertEquals(result1, service.validateEventId(eventId1));
		assertEquals(result1, service.validateEventId(eventId2));
		assertEquals(result1, service.validateEventId(eventId3));
		assertEquals(result1, service.validateEventId(eventId4));
		assertEquals(result2, service.validateEventId(eventId5));
		assertEquals(result2, service.validateEventId(eventId6));
	}
}
