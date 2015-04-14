package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;

@RunWith(Arquillian.class)
public class DurationValidatorTest {

	@Inject
	ValidatorServiceEJB service;

	@Test
	public void validateDuration() {
		boolean expectedResult1 = true;
		boolean expectedResult2 = false;
		Integer duration1 = 500;
		Integer duration2 = 100000;
		Integer duration3 = null;
		assertEquals(expectedResult1, service.validateDuration(duration1));
		assertEquals(expectedResult2, service.validateDuration(duration2));
		assertEquals(expectedResult2, service.validateDuration(duration3));
	}
}
