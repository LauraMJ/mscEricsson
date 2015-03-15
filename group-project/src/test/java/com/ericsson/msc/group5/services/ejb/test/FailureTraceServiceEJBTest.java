package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.msc.group5.dao.FailureTraceDAO;
import com.ericsson.msc.group5.dao.jpa.JPAFailureTraceDAO;
import com.ericsson.msc.group5.entities.FailureClass;
import com.ericsson.msc.group5.entities.FailureTrace;
import com.ericsson.msc.group5.services.FailureTraceService;
import com.ericsson.msc.group5.services.ejb.FailureTraceServiceEJB;

@RunWith(Arquillian.class)
public class FailureTraceServiceEJBTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackage(FailureTrace.class.getPackage())
				.addClasses(FailureClass.class, FailureTraceServiceEJB.class, FailureTraceService.class, FailureTraceDAO.class, JPAFailureTraceDAO.class)
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	FailureTraceService service;
	
	@Test
	public void addFailureTracesTest() {
		FailureTrace [] failureTraceArray = {new FailureTrace(), new FailureTrace(), new FailureTrace()};

		Collection <FailureTrace> failureTraces = new ArrayList <>();
		for (FailureTrace f : failureTraceArray) {
			failureTraces.add(f);
		}

		service.addFailureTraces(failureTraces);

		Collection <FailureTrace> retrievedFailureTraces = service.getAllFailureTraces();

		for(FailureTrace f : retrievedFailureTraces){
			assertTrue("An object failed to be retrieved", failureTraces.contains(f));
		}
	}
	
	
}
