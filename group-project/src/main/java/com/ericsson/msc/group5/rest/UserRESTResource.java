package com.ericsson.msc.group5.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import com.ericsson.msc.group5.services.UserService;

@Path("/add")
public class UserRESTResource {

	@EJB
	private UserService userServiceEJB;

	@POST
	@Path("/user")
//	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addUser(@FormParam("username") String username, @FormParam("password") String password, @FormParam("role") String role) {
		System.out.println("here");
		System.out.println(username);
		System.out.println(password);
		System.out.println(role);
		return userServiceEJB.addUser(username, password, role);
	}
}
