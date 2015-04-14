package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.entities.FailureClass;
import com.ericsson.msc.group5.services.FailureClassService;

@RunWith(Arquillian.class)
public class FailureClassServiceEJBTest {

	@EJB
	private FailureClassService failureClassServiceEJB;

	@Test
	public void addFailureClassesTest() {
		FailureClass [] failureClassArray = {new FailureClass(1, "first"), new FailureClass(2, "second"), new FailureClass(3, "third")};

		Collection <FailureClass> failureClasses = new ArrayList <>();
		for (FailureClass f : failureClassArray) {
			failureClasses.add(f);
		}

		failureClassServiceEJB.addFailureClasses(failureClasses);

		Collection <FailureClass> retrievedFailureClasses = failureClassServiceEJB.getFailureClasses();

		for (FailureClass f : retrievedFailureClasses) {
			assertTrue("An object failed to be retrieved", failureClasses.contains(f));
			assertTrue(f.equals(f));
			assertTrue(f.hashCode() == f.hashCode());
		}
	}
}
