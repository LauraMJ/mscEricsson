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

@Path("/add")
public class UserRESTResource {

	@EJB
	private UserService userServiceEJB;

	@POST
	@Path("/user")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		if (userServiceEJB.addUser(user.getUsername(), user.getPassword(), user.getRole())) {
			return Response.ok().status(200).entity("New user added successfully").build();
		}
		else {
			return Response.serverError().entity("User already exists, username must be unique").build();
		}
	}
}
