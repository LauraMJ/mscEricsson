package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CellIdValidatiorTest {

	private boolean expectedResult;
	private Integer cellId;

	@Parameters
	public static List <Object []> params() {
		Object [][] data = new Object[][] { {false, 4000}, {true, 3200}};
		return Arrays.asList(data);
	}

	public CellIdValidatiorTest(boolean expectedResult, Integer cellId) {
		this.cellId = cellId;
		this.expectedResult = expectedResult;
	}

	@Test
	public void validateCellId() {
		assertEquals(expectedResult, Validator.validateCellId(cellId));
	}
}
