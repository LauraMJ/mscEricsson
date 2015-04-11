package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;

@RunWith(Parameterized.class)
public class CellIdValidatorTest {

	private boolean expectedResult;
	private Integer cellId;
	
	@Inject
	ValidatorServiceEJB service;

	@Parameters
	public static List <Object []> params() {
		Object [][] data = new Object[][] { {true, 4000}, {true, 3200},
				{false, 12000}};
		return Arrays.asList(data);
	}

	public CellIdValidatorTest(boolean expectedResult, Integer cellId) {
		this.cellId = cellId;
		this.expectedResult = expectedResult;
	}

	@Test
	public void validateCellId() {
		assertEquals(expectedResult, service.validateCellId(cellId));
	}
}
