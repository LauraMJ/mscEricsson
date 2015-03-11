package com.ericsson.msc.group5.rest;

import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.ericsson.msc.group5.services.DataImportService;
import com.ericsson.msc.group5.services.FailureTraceService;

@Path("/testingQueries")
public class TestRest {

	@EJB
	private FailureTraceService failureTraceEJB;

	@Inject
	private DataImportService dataImport;

	// dataImport.importSpreadsheet("C:\\Users\\Harry\\Documents\\data.xls");
	// return failureTraceEJB.findImsiOfFailureByTimePeriod(
	// "11/01/2013  17:15:00", "11/01/2013  17:39:00");
	// return failureTraceEJB.getEventCauseCombinations("344930000000011");
	// dataImport.importSpreadsheet("C:\\Users\\Harry\\Documents\\data.xls");

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection <String> getImsiOfFailureByTimePeriod() {
		dataImport.importSpreadsheet("C:\\Users\\D14125353\\Desktop\\data.xls");
		System.out.println("OMG THINGS");
		System.out.println("FUCK JBOSS");
		Date startTime = new Date(1357924500000L);
		Date endTime = new Date(1357924920000L);;
		return failureTraceEJB.getImsiOfFailureByTimePeriod(startTime, endTime);
	}

	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	// public Collection <FailureTrace> getAllFailureTraces() {
	// return failureTraceEJB.getAllFailureTraces();
	// }

}
