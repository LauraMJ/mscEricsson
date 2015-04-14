package com.ericsson.msc.group5.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.FailureClassService;

@RunWith(Arquillian.class)
@Transactional
public class FailureClassTest {

	@PersistenceContext
	private EntityManager em;

	private static String INITIAL_DESCRIPTION = "HIGH PRIORITY ACCESS";
	
	@Inject
	private FailureClassService failureClassService;

	@Test
	@Transactional(TransactionMode.ROLLBACK)
	public void basicCRUDTest() throws Exception {
		int id = 0;

		FailureClass createdFC = new FailureClass(id, INITIAL_DESCRIPTION);
		failureClassService.addFailureClass(createdFC);

		ArrayList <FailureClass> failureClasses = new ArrayList <>();
		Collection <?> failureClassesCollection = failureClassService.getFailureClasses();

		for (Object e : failureClassesCollection) {
			failureClasses.add((FailureClass) e);
		};
		FailureClass loadedFC = null;
		for (FailureClass f : failureClasses) {
			if (f.getFailureClass().equals(id)) {
				loadedFC = f;
				break;
			}
		}
		assertEquals("Failed to insert", INITIAL_DESCRIPTION, loadedFC.getDescription());
	}
	
	@Test
	@Transactional(TransactionMode.ROLLBACK)
	public void testEquality(){
		FailureClass failureClass = new FailureClass(1, "description");
		FailureClass otherFailureClass = new FailureClass(2, "other description");
		
		assertTrue(failureClass.equals(new FailureClass(1, "other failure class")));
		assertFalse(failureClass.equals(null));
		assertFalse(failureClass.equals(new Integer(0)));
		assertFalse(failureClass.equals(new EventCauseCK(0, 1)));
		assertFalse((new FailureClass()).equals(failureClass));
		assertFalse(failureClass.equals(otherFailureClass));
		assertTrue(failureClass.hashCode() == (new FailureClass(1, "other failure class").hashCode()));
	}
}
