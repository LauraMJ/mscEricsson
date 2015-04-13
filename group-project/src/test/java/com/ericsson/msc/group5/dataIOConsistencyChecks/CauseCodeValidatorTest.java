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

import com.ericsson.msc.group5.dao.UserDAO;
import com.ericsson.msc.group5.dao.jpa.JPACountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.dao.jpa.JPACountryDAO;
import com.ericsson.msc.group5.entities.Country;
import com.ericsson.msc.group5.services.DataImportService;
import com.ericsson.msc.group5.services.ejb.DataImportServiceEJB;
import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;

@RunWith(Arquillian.class)
public class CauseCodeValidatorTest {

	@Deployment
	public static Archive <?> createDeployment() {
		PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml");
		File [] commonsLang = pom.resolve("org.apache.poi:poi").withTransitivity().asFile();
		
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(DataImportServiceEJB.class.getPackage())
				.addPackage(DataImportService.class.getPackage()).addPackage(UserDAO.class.getPackage()).addPackage(JPACountryCodeNetworkCodeDAO.class.getPackage()).addPackage(Country.class.getPackage()).addPackage(JPACountryDAO.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsLibraries(commonsLang)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	ValidatorServiceEJB service;

	@Test
	public void validateCauseCode() {
		boolean expectedResult = false;
		Integer causeCode = 50;
		
		boolean expectedResult2 = true;
		Integer causeCode2 = 10;

		assertEquals(expectedResult, service.validateCauseCode(causeCode));
		assertEquals(expectedResult2, service.validateCauseCode(causeCode2));
	}
}
