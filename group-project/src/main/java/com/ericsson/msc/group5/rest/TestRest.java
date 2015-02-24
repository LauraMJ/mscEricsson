package com.ericsson.msc.group5.rest;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.ericsson.msc.group5.dataIO.DataImport;
import com.ericsson.msc.group5.entities.FailureTrace;

@Path("/test")
public class TestRest {

	@Inject
	private DataImport service;
	@PersistenceContext
	EntityManager em;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <FailureTrace> getFailureTrace() {
		return em.createQuery("select * from FailureTrace ft ").getResultList();
	}

}
