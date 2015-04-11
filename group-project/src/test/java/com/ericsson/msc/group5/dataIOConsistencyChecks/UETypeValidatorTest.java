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
public class UETypeValidatorTest {

	private boolean result;
	private Integer ueType;

	@Parameters
	public static List <Object []> params() {
		Object [][] vals = { {false, 2000}, {true, 100000}, {true, 99999999}, {false, 100000000}};
		return Arrays.asList(vals);
	}

	public UETypeValidatorTest(boolean result, Integer ueType) {
		this.result = result;
		this.ueType = ueType;
	}

	@Test
	public void validateUEType() {
		assertEquals(result, ValidatorServiceEJB.validateUEType(ueType));
	}

}