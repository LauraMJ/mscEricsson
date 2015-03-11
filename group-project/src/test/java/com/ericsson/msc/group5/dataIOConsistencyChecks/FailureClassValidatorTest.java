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
public class FailureClassValidatorTest {

	private boolean result;
	private Integer failureClass;

	@Parameters
	public static List <Object []> params() {
		Object [][] vals = { {false, -5}, {true, 0}, {true, 4}, {false, 8}, {false, null}};
		return Arrays.asList(vals);
	}

	public FailureClassValidatorTest(boolean result, Integer failureClass) {
		this.result = result;
		this.failureClass = failureClass;
	}

	@Test
	public void validateFailureClass() {
		assertEquals(result, Validator.validateFailureClass(failureClass));
	}
}
