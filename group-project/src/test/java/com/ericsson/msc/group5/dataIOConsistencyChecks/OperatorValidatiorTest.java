package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class OperatorValidatiorTest {

	private boolean expectedResult;
	private Integer operator;

	@Parameters
	public static List <Object []> params() {
		Object [][] data = new Object[][] { {false, 0}, {true, 01}, {true, 999}, {false, 1011}};
		return Arrays.asList(data);
	}

	public OperatorValidatiorTest(boolean expectedResult, Integer operator) {
		this.operator = operator;
		this.expectedResult = expectedResult;
	}

	@Test
	public void validateOperator() {
		assertEquals(expectedResult, Validator.validateOperator(operator));
	}
}
