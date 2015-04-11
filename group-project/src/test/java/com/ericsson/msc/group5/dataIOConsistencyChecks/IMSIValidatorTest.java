package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;

@RunWith(Parameterized.class)
public class IMSIValidatorTest {

	private boolean expectedResult;
	private Long imsi;

	@Parameters
	public static List <Object []> params() {
		Object [][] data = new Object[][] { {false, 1000000000000000L},
				{true, 132435465768798L}};
		return Arrays.asList(data);
	}

	public IMSIValidatorTest(boolean expectedResult, Long imsi) {
		this.imsi = imsi;
		this.expectedResult = expectedResult;
	}

	@Test
	public void validateIMSI() {
		assertEquals(expectedResult, ValidatorServiceEJB.validateIMSI(imsi));
	}
}
