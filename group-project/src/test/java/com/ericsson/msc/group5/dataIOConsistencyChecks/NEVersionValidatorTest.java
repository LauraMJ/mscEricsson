package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class NEVersionValidatorTest {

	private boolean expectedResult;
	private String neVersion;

	@Parameters
	public static List <Object []> params() {
		Object [][] data = new Object[][] { {true, "11B"}, {true, "12A"},
				{true, "10C"},};
		return Arrays.asList(data);
	}

	public NEVersionValidatorTest(boolean expectedResult, String neVersion) {
		this.neVersion = neVersion;
		this.expectedResult = expectedResult;
	}

	@Test
	public void validateNEVersion() {
		assertEquals(expectedResult, Validator.validateNEVersion(neVersion));
	}

}
