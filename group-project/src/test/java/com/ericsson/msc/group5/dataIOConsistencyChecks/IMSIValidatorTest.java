package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;

@RunWith(Arquillian.class)
public class IMSIValidatorTest {

	@Inject
	ValidatorServiceEJB service;

	@Test
	public void validateIMSI() {
		boolean expectedResult1 = true;
		boolean expectedResult2 = false;
		Long imsi1 = 12345678910111L;
		Long imsi2 = 12345L;
		Long imsi3 = null;

		assertEquals(expectedResult1, service.validateIMSI(imsi1));
		assertEquals(expectedResult2, service.validateIMSI(imsi2));
		assertEquals(expectedResult2, service.validateIMSI(imsi3));
	}
}
