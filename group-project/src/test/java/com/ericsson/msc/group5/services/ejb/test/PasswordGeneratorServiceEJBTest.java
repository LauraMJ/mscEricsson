package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.assertEquals;
import javax.ejb.EJB;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.PasswordGeneratorService;

@RunWith(Arquillian.class)
public class PasswordGeneratorServiceEJBTest {

	@EJB
	private PasswordGeneratorService service;

	@Test
	public void testGenerate() {
		String password = "password";

		assertEquals(service.generate(password), "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=");
	}

}
