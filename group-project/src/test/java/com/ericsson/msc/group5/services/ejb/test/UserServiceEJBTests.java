package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.assertEquals;
import javax.ejb.EJB;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.entities.User;
import com.ericsson.msc.group5.services.UserService;

@RunWith(Arquillian.class)
public class UserServiceEJBTests {

	@EJB
	private UserService service;

	@Test
	public void testUserIsAdded() {
		service.addUser("SAM", "password", "administrator");
		User newUser = service.getUser("SAM");
		assertEquals(newUser.getUsername(), "SAM");
	}

	@Test
	public void testExistingUser() {
		service.addUser("test", "pass", "administrator");
		assertEquals(service.addUser("test", "pass", "administrator"), false);
	}

}
