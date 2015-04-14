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
public class DurationValidatorTest {

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
	
	@Inject
	ValidatorServiceEJB service;

	@Test
	public void validateDuration() {
		boolean expectedResult1 = true;
		boolean expectedResult2 = false;
		Integer duration1 = 500;
		Integer duration2 = 100000;
		Integer duration3 = null;
		assertEquals(expectedResult1, service.validateDuration(duration1));
		assertEquals(expectedResult2, service.validateDuration(duration2));
		assertEquals(expectedResult2, service.validateDuration(duration3));
	}
}
