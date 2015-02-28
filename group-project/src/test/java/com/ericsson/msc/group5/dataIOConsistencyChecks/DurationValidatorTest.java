package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DurationValidatorTest {

	private boolean expectedResult;
	private Integer duration;

	@Parameters
	public static List <Object []> params() {
		Object [][] data = new Object[][] { {false, 4000000}, {false, 3200},
				{true, 1000}};
		return Arrays.asList(data);
	}

	public DurationValidatorTest(boolean expectedResult, Integer duration) {
		this.duration = duration;
		this.expectedResult = expectedResult;
	}

	@Test
	public void validateDuration() {
		assertEquals(expectedResult, Validator.validateDuration(duration));
	}
}
