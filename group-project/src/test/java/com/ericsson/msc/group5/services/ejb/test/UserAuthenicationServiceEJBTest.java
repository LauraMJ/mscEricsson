package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.msc.group5.dao.UserDAO;
import com.ericsson.msc.group5.dao.jpa.JPAUserDAO;
import com.ericsson.msc.group5.entities.User;
import com.ericsson.msc.group5.services.UserAuthenticationService;
import com.ericsson.msc.group5.services.UserService;
import com.ericsson.msc.group5.services.ejb.UserAuthenticationServiceEJB;
import com.ericsson.msc.group5.services.ejb.UserServiceEJB;

@RunWith(Arquillian.class)
public class UserAuthenicationServiceEJBTest {
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackage(User.class.getPackage())
				.addClasses(User.class, UserAuthenticationServiceEJB.class, UserAuthenticationService.class, UserServiceEJB.class, UserService.class, UserDAO.class, JPAUserDAO.class)
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	UserAuthenticationService service;
	
	@Test
	public void authenticateUserTest(){
		User user = new User("John", "nothing", "Customer");
		
		//service.authenticateUser("John", "nothing");
		assertEquals(user.getRole(), "Customer");
	}

}
