package com.ericsson.msc.group5.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ericsson.msc.group5.services.UserAuthenticationService;

@Path("/")
public class LoginRest {

	@EJB
	private UserAuthenticationService userAuthenticationService;

	@Path("/group-project/rest/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticateUser(@FormParam("username") String username,
			@FormParam("password") String password) {
		// return userAuthenticationService.authenticateUser(username,
		// password);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(username);
		stringBuilder.append(password);

		System.out.println("Data Received: " + stringBuilder.toString());

		// return HTTP response 200 in case of success
		return Response.status(200).entity(stringBuilder.toString()).build();
	}
}
