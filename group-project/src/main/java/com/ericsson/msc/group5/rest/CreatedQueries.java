package com.ericsson.msc.group5.rest;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.ericsson.msc.group5.entities.FailureTrace;
import com.ericsson.msc.group5.services.FailureTraceService;

@Path("/query")
public class CreatedQueries {

	@EJB
	private FailureTraceService failureTraceEJB;

	@POST
	@Path("/eventCause")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventCauseCombinations(String imsi) {

		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		long total = end - start;
		System.out.println("Time take %d milliseconds.\n" + total);
		return Response.ok().status(200)
				.entity(failureTraceEJB.getEventCauseCombinations(imsi))
				.build();
	}

	@GET
	@Path("getAllFailureTraces")
	public Collection <FailureTrace> getAllFailureTraces() {
		return failureTraceEJB.getAllFailureTraces();
	}
}
