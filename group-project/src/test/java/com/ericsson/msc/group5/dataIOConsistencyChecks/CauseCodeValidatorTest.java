package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import com.ericsson.msc.group5.utils.Validator;

@RunWith(Parameterized.class)
public class CauseCodeValidatorTest {

	private boolean expectedResult;
	private Integer causeCode;

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
		assertEquals(expectedResult, Validator.validateCauseCode(causeCode));
	}
}
