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
public class MarketValidatorTest {

	private boolean result;
	private Integer market;

	@Parameters
	public static List <Object []> params() {
		Object [][] vals = { {false, 0}, {false, 001}, {true, 999},
				{false, 1011}};
		return Arrays.asList(vals);
	}

	public MarketValidatorTest(boolean result, Integer market) {
		this.result = result;
		this.market = market;
	}

	@Test
	public void validateMarket() {
		assertEquals(result, ValidatorServiceEJB.validateMarket(market));
	}
}
