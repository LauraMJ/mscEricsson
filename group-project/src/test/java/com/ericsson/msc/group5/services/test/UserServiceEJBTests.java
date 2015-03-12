package com.ericsson.msc.group5.services.test;

import static org.junit.Assert.*;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.msc.group5.entities.User;
import com.ericsson.msc.group5.services.UserService;

@RunWith(Arquillian.class)
public class UserServiceEJBTests {
	
	
	
	@Deployment
	public static Archive <?> createDeployment(){
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(UserService.class.getPackage())
				.addAsResource("test-persistence.xml", "META_INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Inject
	UserService service;
	
	
	User newUser = new User("SAM", "password", "administrator");
	
	@Test
	public void testUserIsAdded(){
		
		assertEquals(newUser.getUsername(), "SAM");
	}

}
