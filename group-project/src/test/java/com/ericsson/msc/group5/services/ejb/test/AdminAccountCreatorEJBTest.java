package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.*;

import java.io.File;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.msc.group5.services.UserService;

@RunWith(Arquillian.class)
public class AdminAccountCreatorEJBTest {
	
	@EJB
	private UserService service;
	
	@Test
	public void initTest() {
		assertEquals("administrator", service.getUser("administrator").getUsername());
	}
}
