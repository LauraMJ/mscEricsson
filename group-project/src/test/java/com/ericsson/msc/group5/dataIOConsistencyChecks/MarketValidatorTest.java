package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;

@RunWith(Arquillian.class)
public class MarketValidatorTest {

	@Inject
	ValidatorServiceEJB service;

	@Test
	public void validateMarket() {

		boolean result1 = true;
		boolean result2 = false;
		Integer market1 = 3;
		Integer market2 = 311;
		Integer market3 = null;

		assertEquals(result1, service.validateMarket(market2));
		assertEquals(result2, service.validateMarket(market1));
		assertEquals(result2, service.validateMarket(market3));
	}
}
