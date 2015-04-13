package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;

import java.io.File;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.dao.FailureTraceDAO;
import com.ericsson.msc.group5.dao.jpa.JPAFailureTraceDAO;
import com.ericsson.msc.group5.entities.FailureTrace;
import com.ericsson.msc.group5.services.DataImportService;
import com.ericsson.msc.group5.services.ejb.DataImportServiceEJB;
import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;

@RunWith(Arquillian.class)
public class FailureClassValidatorTest {

	@Deployment
	public static Archive <?> createDeployment() {
		PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml");
		File [] commonsLang = pom.resolve("org.apache.poi:poi").withTransitivity().asFile();
		
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(DataImportServiceEJB.class.getPackage())
				.addPackage(DataImportService.class.getPackage()).addPackage(FailureTraceDAO.class.getPackage()).addPackage(JPAFailureTraceDAO.class.getPackage()).addPackage(FailureTrace.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsLibraries(commonsLang)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Inject
	ValidatorServiceEJB service;

	@Test
	public void validateFailureClass() {
		boolean result1 = true;
		boolean result2 = false;
		Integer failureClass0 = 0;
		Integer failureClass1 = 1;
		Integer failureClass2 = 2;
		Integer failureClass3 = 3;
		Integer failureClass4 = 4;
		Integer failureClass5 = 5;

		assertEquals(result1, service.validateFailureClass(failureClass0));
		assertEquals(result1, service.validateFailureClass(failureClass1));
		assertEquals(result1, service.validateFailureClass(failureClass2));
		assertEquals(result1, service.validateFailureClass(failureClass3));
		assertEquals(result1, service.validateFailureClass(failureClass4));
		assertEquals(result2, service.validateFailureClass(failureClass5));
	}
}
