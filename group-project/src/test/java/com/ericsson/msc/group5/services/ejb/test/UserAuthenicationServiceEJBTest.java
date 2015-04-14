package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.assertEquals;
import javax.ejb.EJB;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.entities.User;
import com.ericsson.msc.group5.services.UserAuthenticationService;
import com.ericsson.msc.group5.services.UserService;

@RunWith(Arquillian.class)
public class UserAuthenicationServiceEJBTest {

	@EJB
	private UserAuthenticationService service;

	@EJB
	UserService userService;

	@Test
	public void authenticateUserTest() {
		userService.addUser("John", "nothing", "administrator");
		User user = userService.getUser("John");
		assertEquals(service.authenticateUser("John", "nothing"), "administrator");
	}

	@Test
	public void authenticalUserNullTest() {
		assertEquals(service.authenticateUser("Should", "fail"), null);
	}

}
