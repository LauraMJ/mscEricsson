package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.msc.group5.services.LogDetailsRetrieverService;

@RunWith(Arquillian.class)
public class LogDetailsRetrieverTest {

	@Deployment(testable = true)
	public static Archive <?> createDeployment() {
		PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeAndTestDependencies();
		File [] libraries = pom.resolve("org.apache.poi:poi").withTransitivity().asFile();

		return ShrinkWrap.create(WebArchive.class, "test.war").addPackages(true, "com.ericsson").addAsLibraries(libraries)
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	LogDetailsRetrieverService logger;

	@Test
	public void testLogger() {
		ArrayList <String> logString = new ArrayList <String>();
		logString.add("Import Details=");
		logString.add("Timestamp=2015/04/14 12:04:52");
		logString.add("Time Taken=6088");
		logString.add("Valid Records=29997");
		logString.add("Invalid Records=1");
		logger.createJsonObjectFromArrayListOfDetails(logString);
		JSONObject json = logger.retrieveLogDetailsAsJson();
		assertEquals(true, json.isEmpty());
	}
}
