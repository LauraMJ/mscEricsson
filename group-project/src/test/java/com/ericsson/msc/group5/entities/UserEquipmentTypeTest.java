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
public class UserEquipmentTypeTest {

	@Deployment
	public static Archive <?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(UserEquipmentType.class.getPackage())
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	private static String INITIAL_UserEquipmentType = "HANDHELD";
	private static String UPDATED_UserEquipmentType = "M2M";

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
		UserEquipmentType createdUE = new UserEquipmentType();
		createdUE.setUserEquipmentType(INITIAL_UserEquipmentType);
		em.persist(createdUE);
		int newId  = createdUE.getUserEquipmentTypeId();

		UserEquipmentType loadedUE = em.find(UserEquipmentType.class, newId);
		assertEquals("Failed to insert", INITIAL_UserEquipmentType,
				loadedUE.getUserEquipmentType());

		loadedUE.setUserEquipmentType(UPDATED_UserEquipmentType);
		UserEquipmentType updatedUE = em.find(UserEquipmentType.class, newId);
		assertEquals("Failed to update", UPDATED_UserEquipmentType,
				updatedUE.getUserEquipmentType());

		em.remove(updatedUE);
		UserEquipmentType shouldBeNull = em
				.find(UserEquipmentType.class, newId);
		assertNull("Failed to delete", shouldBeNull);
	}

	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery(
				"delete from com.ericsson.msc.group5.entities.UserEquipmentType")
				.executeUpdate();
		utx.commit();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
}
