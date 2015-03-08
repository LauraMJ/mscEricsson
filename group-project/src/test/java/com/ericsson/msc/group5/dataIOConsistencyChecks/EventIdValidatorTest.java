package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class EventIdValidatorTest {

	private boolean result;
	private Integer eventId;

	@Parameters
	public static List <Object []> eventIds() {
		Object [][] vals = { {false, 2000}, {false, 6000}, {true, 4097},
				{false, 5000}};
		return Arrays.asList(vals);
	}

	public EventIdValidatorTest(boolean result, Integer eventId) {
		this.result = result;
		this.eventId = eventId;
	}

	@Test
	public void validateEventId() {
		assertEquals(result, Validator.validateEventId(eventId));
	}
}
