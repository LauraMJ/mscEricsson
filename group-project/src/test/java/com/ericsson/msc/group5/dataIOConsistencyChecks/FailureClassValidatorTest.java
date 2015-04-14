package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;

@RunWith(Arquillian.class)
public class FailureClassValidatorTest {

	@Inject
	private ValidatorServiceEJB service;

	@Test
	public void validateFailureClass() {
		boolean result1 = true;
		boolean result2 = false;
		Integer failureClass0 = 0;
		Integer failureClass1 = 1;
		Integer failureClass2 = 2;
		Integer failureClass3 = 3;
		Integer failureClass4 = 4;
		Integer failureClass5 = 5;
		Integer failureClass6 = null;

		assertEquals(result1, service.validateFailureClass(failureClass0));
		assertEquals(result1, service.validateFailureClass(failureClass1));
		assertEquals(result1, service.validateFailureClass(failureClass2));
		assertEquals(result1, service.validateFailureClass(failureClass3));
		assertEquals(result1, service.validateFailureClass(failureClass4));
		assertEquals(result2, service.validateFailureClass(failureClass5));
		assertEquals(result2, service.validateFailureClass(failureClass6));
	}
}
