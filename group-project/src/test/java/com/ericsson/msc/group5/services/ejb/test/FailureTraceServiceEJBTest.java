package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.msc.group5.dao.CountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.dao.EventCauseDAO;
import com.ericsson.msc.group5.dao.FailureClassDAO;
import com.ericsson.msc.group5.dao.FailureTraceDAO;
import com.ericsson.msc.group5.dao.UserEquipmentDAO;
import com.ericsson.msc.group5.dao.jpa.JPACountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.dao.jpa.JPAEventCauseDAO;
import com.ericsson.msc.group5.dao.jpa.JPAFailureClassDAO;
import com.ericsson.msc.group5.dao.jpa.JPAFailureTraceDAO;
import com.ericsson.msc.group5.dao.jpa.JPAUserEquipmentDAO;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCodeCK;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.FailureClass;
import com.ericsson.msc.group5.entities.FailureTrace;
import com.ericsson.msc.group5.entities.UserEquipment;
import com.ericsson.msc.group5.services.CountryCodeNetworkCodeService;
import com.ericsson.msc.group5.services.EventCauseService;
import com.ericsson.msc.group5.services.FailureClassService;
import com.ericsson.msc.group5.services.FailureTraceService;
import com.ericsson.msc.group5.services.UserEquipmentService;
import com.ericsson.msc.group5.services.ejb.CountryCodeNetworkCodeServiceEJB;
import com.ericsson.msc.group5.services.ejb.EventCauseServiceEJB;
import com.ericsson.msc.group5.services.ejb.FailureClassServiceEJB;
import com.ericsson.msc.group5.services.ejb.FailureTraceServiceEJB;
import com.ericsson.msc.group5.services.ejb.UserEquipmentServiceEJB;

@RunWith(Arquillian.class)
public class FailureTraceServiceEJBTest {

	@Deployment(testable = true)
	public static Archive <?> createDeployment() {
		PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeAndTestDependencies();
		File [] libraries = pom.resolve("org.apache.poi:poi").withTransitivity().asFile();

		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(true, "com.ericsson")
				.addAsLibraries(libraries)
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	FailureTraceService service;
	
	@Test
	public void addFailureTracesTest() {
		
		FailureTrace failureTraceOne =  new FailureTrace();
		failureTraceOne.setFailureTraceId(10L);
		FailureTrace failureTraceTwo =  new FailureTrace();
		failureTraceTwo.setFailureTraceId(20L);

		FailureTrace [] failureTraceArray = {failureTraceOne, failureTraceTwo};

		Collection <FailureTrace> failureTraces = new ArrayList <>();
		for (FailureTrace f : failureTraceArray) {
			failureTraces.add(f);
		}

		service.addFailureTraces(failureTraces);

		Collection <FailureTrace> retrievedFailureTraces = service.getAllFailureTraces();

		for(FailureTrace f : retrievedFailureTraces){
			assertEquals(false, failureTraces.contains(f));
		}
	}
	
	
}
