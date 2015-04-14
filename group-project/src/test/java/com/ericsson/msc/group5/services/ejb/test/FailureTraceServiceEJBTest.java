package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.entities.FailureTrace;
import com.ericsson.msc.group5.services.FailureTraceService;

@RunWith(Arquillian.class)
public class FailureTraceServiceEJBTest {

	@EJB
	private FailureTraceService service;

	@Test
	public void addFailureTracesTest() {

		FailureTrace failureTraceOne = new FailureTrace();
		failureTraceOne.setFailureTraceId(10L);
		FailureTrace failureTraceTwo = new FailureTrace();
		failureTraceTwo.setFailureTraceId(20L);

		FailureTrace [] failureTraceArray = {failureTraceOne, failureTraceTwo};

		Collection <FailureTrace> failureTraces = new ArrayList <>();
		for (FailureTrace f : failureTraceArray) {
			failureTraces.add(f);
		}

		service.addFailureTraces(failureTraces);

		Collection <FailureTrace> retrievedFailureTraces = service.getAllFailureTraces();

		for (FailureTrace f : retrievedFailureTraces) {
			assertEquals(false, failureTraces.contains(f));
		}
	}

}
