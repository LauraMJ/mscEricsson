package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;

@RunWith(Parameterized.class)
public class CauseCodeValidatorTest {

	private boolean expectedResult;
	private Integer causeCode;
	
	@Inject
	ValidatorServiceEJB service;

	@Parameters
	public static List <Object []> params() {
		Object [][] data = new Object[][] { {false, 50}, {true, 25}};
		return Arrays.asList(data);
	}

	public CauseCodeValidatorTest(boolean expectedResult, Integer causeCode) {
		this.causeCode = causeCode;
		this.expectedResult = expectedResult;
	}

	@Test
	public void validateCauseCode() {
		assertEquals(expectedResult, service.validateCauseCode(causeCode));
	}
}
