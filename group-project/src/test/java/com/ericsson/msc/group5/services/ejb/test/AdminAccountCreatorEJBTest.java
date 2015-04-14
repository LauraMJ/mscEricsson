package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.assertEquals;
import javax.ejb.EJB;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.UserService;

@RunWith(Arquillian.class)
public class AdminAccountCreatorEJBTest {

	@EJB
	UserService service;

	@Test
	public void initTest() {
		assertEquals("administrator", service.getUser("administrator").getUsername());
	}
}
