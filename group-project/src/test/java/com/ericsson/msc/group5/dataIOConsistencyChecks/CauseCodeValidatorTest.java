package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;

@RunWith(Arquillian.class)
public class CauseCodeValidatorTest {

	@Inject
	ValidatorServiceEJB service;

	@Test
	public void validateCauseCode() {
		boolean expectedResult = false;
		Integer causeCode = 50;

		boolean expectedResult2 = true;
		Integer causeCode2 = 10;
		Integer causeCode3 = null;

		assertEquals(expectedResult, service.validateCauseCode(causeCode));
		assertEquals(expectedResult2, service.validateCauseCode(causeCode2));
		assertEquals(expectedResult, service.validateCauseCode(causeCode3));
	}
}
