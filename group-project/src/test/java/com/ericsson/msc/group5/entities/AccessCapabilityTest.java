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

@RunWith(Arquillian.class)
public class AccessCapabilityTest {

	@Deployment
	public static Archive <?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(AccessCapability.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	private static String INITIAL_ACCESS_CAPABILITY = "GSM 1800";
	private static String UPDATED_ACCESS_CAPABILITY = "GSM 1800, GSM 1900, GSM 900, GSM850 (GSM800), WCDMA FDD Band I, WCDMA FDD Band II, WCDMA FDD Band V";

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
		AccessCapability createdAC = new AccessCapability(INITIAL_ACCESS_CAPABILITY);
		em.persist(createdAC);
		int newId = createdAC.getAccessCapabilityId();

		AccessCapability loadedAC = em.find(AccessCapability.class, newId);
		assertEquals("Failed to insert", INITIAL_ACCESS_CAPABILITY, loadedAC.getAccessCapability());

		loadedAC.setAccessCapability(UPDATED_ACCESS_CAPABILITY);
		AccessCapability updatedAC = em.find(AccessCapability.class, newId);
		assertEquals("Failed to update", UPDATED_ACCESS_CAPABILITY, updatedAC.getAccessCapability());

		em.remove(updatedAC);
		AccessCapability shouldBeNull = em.find(AccessCapability.class, newId);
		assertNull("Failed to delete", shouldBeNull);
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
