package com.ericsson.msc.group5.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
import com.ericsson.msc.group5.entities.FailureTrace;
import com.ericsson.msc.group5.services.FailureTraceService;

@Path("/query")
public class CreatedQueries {

	@EJB
	private FailureTraceService failureTraceEJB;

	@POST
	@Path("/eventCausePerImsi")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventCauseCombinations(String imsi) {

		// long start = System.currentTimeMillis();
		// long end = System.currentTimeMillis();
		// long total = end - start;
		// System.out.println("Time take %d milliseconds.\n" + total);
		return Response.ok().status(200).entity(failureTraceEJB.getEventCauseCombinations(imsi)).build();
	}

	@POST
	@Path("/imsiByTimePeriod")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getImsiByTimePeriod(JSONObject JSONDateObject) {

		String startDate = JSONDateObject.get("Date1").toString();
		String endDate = JSONDateObject.get("Date2").toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateOne = null;
		Date dateTwo = null;
		try {
			dateOne = sdf.parse(startDate);
			dateTwo = sdf.parse(endDate);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

		return Response.ok().status(200).entity(failureTraceEJB.getImsiOfFailureByTimePeriod(dateOne, dateTwo)).build();
	}

	@POST
	@Path("/givenImsiByTimePeriod")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGivenImsiByTimePeriod(JSONObject JSONImsiDateObject) {
		String startDate = JSONImsiDateObject.get("Date1").toString();
		String endDate = JSONImsiDateObject.get("Date2").toString();
		String Imsi = JSONImsiDateObject.get("Imsi").toString();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateOne = null;
		Date dateTwo = null;
		try {
			dateOne = sdf.parse(startDate);
			dateTwo = sdf.parse(endDate);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(Imsi);
		return Response.ok().status(200).entity(failureTraceEJB.getGivenImsiOfFailureWithinTimePeriod(dateOne, dateTwo, Imsi)).build();

	}
	
	@POST
	@Path("/givenModelByTimePeriod")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response givenModelByTimePeriod(JSONObject JSONModelDateObject) {
		String startDate = JSONModelDateObject.get("Date1").toString();
		String endDate = JSONModelDateObject.get("Date2").toString();
		String model = JSONModelDateObject.get("Model").toString();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateOne = null;
		Date dateTwo = null;
		try {
			dateOne = sdf.parse(startDate);
			dateTwo = sdf.parse(endDate);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println(model);
		return Response.ok().status(200).entity(failureTraceEJB.getCountFailsForModelWithinTimePeriod(model, dateOne, dateTwo)).build();
	}

	@GET
	@Path("getAllFailureTraces")
	public Collection <FailureTrace> getAllFailureTraces() {
		return failureTraceEJB.getAllFailureTraces();
	}
}
