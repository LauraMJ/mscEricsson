package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;

@RunWith(Arquillian.class)
public class CellIdValidatorTest {

	@Inject
	ValidatorServiceEJB service;

	@Test
	public void validateCellId() {
		boolean expectedResult = true;
		Integer cellId = 50;
		boolean expectedResult2 = false;
		Integer cellId2 = 50000000;
		Integer cellId3 = null;

		assertEquals(expectedResult, service.validateCellId(cellId));
		assertEquals(expectedResult2, service.validateCellId(cellId2));
		assertEquals(expectedResult2, service.validateCellId(cellId3));
	}
}
