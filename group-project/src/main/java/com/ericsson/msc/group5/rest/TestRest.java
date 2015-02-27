package com.ericsson.msc.group5.rest;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.ericsson.msc.group5.dao.jpa.PersistenceUtil;
import com.ericsson.msc.group5.dataIO.DataImport;
import com.ericsson.msc.group5.entities.FailureClass;

@Path("/test")
public class TestRest {

	@Inject
	private DataImport service;
	@PersistenceContext
	EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <FailureClass> getFailureClass() {
		List<FailureClass> ls = new ArrayList<FailureClass>();
		ls.add(new FailureClass(1, "hello"));
		return ls;
//		return em.createQuery("select ft from FailureClass ft ").getResultList();
	}
}
