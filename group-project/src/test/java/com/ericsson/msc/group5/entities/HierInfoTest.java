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
public class HierInfoTest {

	@Deployment
	public static Archive <?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(HierInfo.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	private static String INITIAL_HIER3 = "4809532081614990000";
	private static String INITIAL_HIER32 = "8226896360947470000";
	private static String INITIAL_HIER321 = "1150444940909480000";
	private static String UPDATED_HIER3 = "229001099943031000";
	private static String UPDATED_HIER32 = "4970937722532610000";
	private static String UPDATED_HIER321 = "6079299740152730000";

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
		HierInfo createdHI = new HierInfo();
		createdHI.setHier3Id(INITIAL_HIER3);
		createdHI.setHier32Id(INITIAL_HIER32);
		createdHI.setHier321Id(INITIAL_HIER321);
		em.persist(createdHI);
		int newId = createdHI.getHierInfoId();

		HierInfo loadedHI = em.find(HierInfo.class, newId);
		assertEquals("Failed to insert", INITIAL_HIER3, loadedHI.getHier3Id());
		assertEquals("Failed to insert", INITIAL_HIER32, loadedHI.getHier32Id());
		assertEquals("Failed to insert", INITIAL_HIER321, loadedHI.getHier321Id());

		loadedHI.setHier3Id(UPDATED_HIER3);
		loadedHI.setHier32Id(UPDATED_HIER32);
		loadedHI.setHier321Id(UPDATED_HIER321);
		HierInfo updatedHI = em.find(HierInfo.class, newId);
		assertEquals("Failed to update", UPDATED_HIER3, updatedHI.getHier3Id());
		assertEquals("Failed to update", UPDATED_HIER32, updatedHI.getHier32Id());
		assertEquals("Failed to update", UPDATED_HIER321, updatedHI.getHier321Id());

		em.remove(updatedHI);
		HierInfo shouldBeNull = em.find(HierInfo.class, newId);
		assertNull("Failed to delete", shouldBeNull);
	}

	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery("delete from com.ericsson.msc.group5.entities.HierInfo").executeUpdate();
		utx.commit();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
}
