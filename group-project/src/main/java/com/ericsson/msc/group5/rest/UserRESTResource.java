package com.ericsson.msc.group5.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.ericsson.msc.group5.entities.User;
import com.ericsson.msc.group5.services.UserService;

@Path("/")
public class UserRESTResource {

	@EJB
	private UserService userServiceEJB;

	@POST
	@Path("/add/user")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		if (userServiceEJB.addUser(user.getUsername(), user.getPassword(), user.getRole())) {
			return Response.ok(user).build();
		}
		else {
			return Response.status(Response.Status.PRECONDITION_FAILED).entity(user).build();
//			return Response.status(Response.Status.PRECONDITION_FAILED).entity(new String("Username already exists: " + user.getUsername())).build();
		}
	}
}
