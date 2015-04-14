package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.LogDetailsRetrieverService;
import com.ericsson.msc.group5.services.ejb.LogDetailsRetriever;

@RunWith(Arquillian.class)
public class LogDetailsRetrieverTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackage(LogDetailsRetriever.class.getPackage())
				.addClasses(LogDetailsRetrieverService.class, LogDetailsRetriever.class).addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
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
		assertFalse(json.isEmpty());
	}
}
