package com.ericsson.msc.group5.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.ericsson.msc.group5.services.LogDetailsRetrieverService;

@Path("/importLog")
public class ImportLogRestService {

	@Inject
	private LogDetailsRetrieverService logDetailsRetriever;

	@GET
	@Path("/getLastImportDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLogDetails() {
		return Response.ok().status(200).entity(logDetailsRetriever.retrieveLogDetailsAsJson()).build();
	}
}
