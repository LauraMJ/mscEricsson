package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DateValidatorTest {

	private boolean expectedResult;
	private String dateString;

	@Parameters
	public static List <Object []> params() {
		Object [][] data = new Object[][] { {true, "1/1/11 15:35"}, {true, "31/1/12 00:00"}, {true, "29/02/12 00:00"}, {false, "29/02/15"},
				{false, "31/04/15"}, {false, "00/02/15"}, {false, "19/02/75"}};
		return Arrays.asList(data);
	}

	public DateValidatorTest(boolean expectedResult, String dateString) {
		this.dateString = dateString;
		this.expectedResult = expectedResult;
	}

	@Test
	public void checkIfValidDate() {
		assertEquals(expectedResult, Validator.validateDate(dateString));
	}
}
