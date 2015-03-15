package com.ericsson.msc.group5.rest;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import com.ericsson.msc.group5.entities.User;
import com.ericsson.msc.group5.services.UserService;

@Path("/")
public class UserRESTResource {

	@EJB
	private UserService userServiceEJB;

	@Resource
	private LoginContext loginContext;
	
    @Resource SessionContext ctx;

	@POST
	@Path("/add/user")
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

//	@POST
//	@Path("/logout")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response logout(@Context SecurityContext sc) {
////		ctx.getContextData().
//		try {
//			loginContext.logout();
//		}
//		catch (LoginException e) {
//			e.printStackTrace();
//		}
//		return Response.ok().status(200).entity("logout").build();
//	}
}
