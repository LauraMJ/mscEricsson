//
package com.ericsson.msc.group5.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//This test makes no sense as the class being tested is a CK class and not an entity
@RunWith(Arquillian.class)
public class CountryCodeNetworkCodeCKTest {

	@Deployment
	public static Archive <?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(AccessCapability.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;
	
	private static Integer INITIAL_COUNTRY_CODE = 111;
	private static Integer UPDATED_COUNTRY_CODE = 222;
	private static Integer INITIAL_NETWORK_CODE = 333;
	private static Integer UPDATED_NETWORK_CODE = 444;
	
	
	@Before
	public void preparePersistenceTest() throws Exception {
		clearData();
		startTransaction();
	}

	@After
	public void commitTransaction() throws Exception {
		utx.commit();
	}

	@Test
	public void basicCRUDTest() throws Exception {
		CountryCodeNetworkCodeCK createdCCNCCK = new CountryCodeNetworkCodeCK(INITIAL_COUNTRY_CODE, INITIAL_NETWORK_CODE);
		
		em.persist(createdCCNCCK);
		
		Integer newId = createdCCNCCK.getCountryCode();
		createdCCNCCK = em.find(CountryCodeNetworkCodeCK.class, newId);
		assertEquals("Failed to insert", INITIAL_COUNTRY_CODE, createdCCNCCK.getCountryCode());

		CountryCodeNetworkCodeCK updatedCCNCCK = em.find(CountryCodeNetworkCodeCK.class, newId);
		updatedCCNCCK.setCountryCode(UPDATED_COUNTRY_CODE);
		assertEquals("Failed to update", UPDATED_COUNTRY_CODE, createdCCNCCK.getCountryCode());

		em.remove(updatedCCNCCK);

	}
	
	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery("delete from com.ericsson.msc.group5.entities.AccessCapability").executeUpdate();
		utx.commit();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}

}
