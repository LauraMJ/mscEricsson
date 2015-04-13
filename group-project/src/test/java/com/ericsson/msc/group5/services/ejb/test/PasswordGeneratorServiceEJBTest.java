package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.msc.group5.services.PasswordGeneratorService;
import com.ericsson.msc.group5.services.ejb.PasswordGeneratorServiceEJB;
@RunWith(Arquillian.class)
public class PasswordGeneratorServiceEJBTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
				.addClasses(PasswordGeneratorServiceEJB.class, PasswordGeneratorService.class)
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	PasswordGeneratorService service;

	@Test
	public void testGenerate() {
		String password = "password";

		assertEquals(service.generate(password), "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=");
	}

}
