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
public class OperatingSystemTest {

	@Deployment
	public static Archive <?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(OperatingSystem.class.getPackage())
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	private static String INITIAL_OS = "BLACKBERRY";
	private static String UPDATED_OS = "IOS";

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
		OperatingSystem createdOperatingSystem = new OperatingSystem();
		createdOperatingSystem.setOperatingSystem(INITIAL_OS);
		em.persist(createdOperatingSystem);
		int newId = createdOperatingSystem.getOperatingSystemId();

		OperatingSystem loadedOS = em.find(OperatingSystem.class, newId);
		assertEquals("Failed to insert", INITIAL_OS,
				loadedOS.getOperatingSystem());

		loadedOS.setOperatingSystem(UPDATED_OS);
		OperatingSystem updatedOS = em.find(OperatingSystem.class, newId);
		assertEquals("Failed to update", UPDATED_OS,
				updatedOS.getOperatingSystem());

		em.remove(updatedOS);
		OperatingSystem shouldBeNull = em.find(OperatingSystem.class, newId);
		assertNull("Failed to delete", shouldBeNull);
	}

	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery(
				"delete from com.ericsson.msc.group5.entities.OperatingSystem")
				.executeUpdate();
		utx.commit();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
}
