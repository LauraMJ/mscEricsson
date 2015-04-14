package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import javax.ejb.EJB;
import org.jboss.arquillian.junit.Arquillian;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.LogDetailsRetrieverService;

@RunWith(Arquillian.class)
public class LogDetailsRetrieverTest {

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
