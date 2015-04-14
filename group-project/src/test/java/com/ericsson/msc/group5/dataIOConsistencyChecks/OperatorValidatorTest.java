package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;

@RunWith(Arquillian.class)
public class OperatorValidatorTest {

	@Inject
	ValidatorServiceEJB service;

	@Test
	public void validateOperator() {

		boolean expectedResult = true;
		Integer operator1 = 122;
		boolean expectedResult2 = false;
		Integer operator2 = 1000;
		Integer operator3 = null;

		assertEquals(expectedResult, service.validateOperator(operator1));
		assertEquals(expectedResult2, service.validateOperator(operator2));
		assertEquals(expectedResult2, service.validateOperator(operator3));
	}
}
