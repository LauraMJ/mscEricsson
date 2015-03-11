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


import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;
import com.ericsson.msc.group5.services.CountryCodeNetworkCodeService;


@RunWith(Arquillian.class)
public class CountryCodeNetworkCodeEJBTest {

	@Deployment
	public static Archive <?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(CountryCodeNetworkCode.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Inject
	CountryCodeNetworkCodeService service;
	
	@Test
	public void testThatTheAllCountryCodeNetworkCodesAreReturned(){
		assertEquals(service.getCountryCodeNetworkCode().size(),1);
	}
}
