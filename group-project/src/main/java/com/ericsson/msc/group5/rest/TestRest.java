package com.ericsson.msc.group5.rest;

import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

	@POST
	@Path("/import")
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection <String> getImsiOfFailureByTimePeriod(String path) {
		// dataImport.importSpreadsheet("C:\\Users\\D14125353\\Desktop\\data.xls");
		dataImport.importSpreadsheet(path);
		long start = System.currentTimeMillis();
		Date startTime = new Date(1357924500000L);
		Date endTime = new Date(1357924920000L);
		Collection <String> returnCollection = failureTraceEJB.getImsiOfFailureByTimePeriod(startTime, endTime);
		long end = System.currentTimeMillis();
		long total = end - start;
		System.out.println("Time take %d milliseconds.\n" + total);
		return returnCollection;
	}

	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	// public Collection <FailureTrace> getAllFailureTraces() {
	// return failureTraceEJB.getAllFailureTraces();
	// }

}
