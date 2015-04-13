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
public class DateValidatorTest {

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
	public void checkIfValidDate() {
		boolean expectedResult1 =true;
		boolean expectedResult2 =false;
		String dateString1 = "12/02/15 10:00";
		String dateString2 = "31/02/15 10:00";
		String dateString3 = null;
		String dateString4 = "31/02/16 10:00";
		String dateString5 = "32/02/16 10:00";
		String dateString6 = "30/03/14 10:00";
		String dateString7 = "30/03/14";
		String dateString8 = "30/09/14 10:00";
		assertEquals(expectedResult1, service.validateDate(dateString1));
		assertEquals(expectedResult2, service.validateDate(dateString2));
		assertEquals(expectedResult2, service.validateDate(dateString3));
		assertEquals(expectedResult2, service.validateDate(dateString4));
		assertEquals(expectedResult2, service.validateDate(dateString5));
		assertEquals(expectedResult1, service.validateDate(dateString6));
		assertEquals(expectedResult2, service.validateDate(dateString7));
		assertEquals(expectedResult1, service.validateDate(dateString8));
	}
}
