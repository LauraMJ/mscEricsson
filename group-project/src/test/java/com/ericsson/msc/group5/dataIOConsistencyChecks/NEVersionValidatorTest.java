package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;

@RunWith(Arquillian.class)
public class NEVersionValidatorTest {

	private boolean expectedResult1 = true;
	private String neVersion1 = "222";

	private boolean expectedResult2 = false;
	private String neVersion2 = "10";
	private String neVersion3 = null;

	@Inject
	ValidatorServiceEJB service;

	@Test
	public void validateNEVersion() {
		assertEquals(expectedResult1, service.validateNEVersion(neVersion1));
		assertEquals(expectedResult2, service.validateNEVersion(neVersion2));
		assertEquals(expectedResult2, service.validateNEVersion(neVersion3));
	}

}
