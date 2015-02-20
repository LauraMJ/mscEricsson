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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class UserEquipmentTest {

	@Deployment
	public static Archive <?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(UserEquipment.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	private static String INITIAL_MANUFACTURER = "Samsung";
	private static String UPDATED_MANUFACTURER = "Apple";
	private UserEquipment ue;
	private int id = 0;

	@Before
	public void preparePersistenceTest() throws Exception {
		clearData();
		insertData();
		startTransaction();
	}

	@After
	public void commitTransaction() throws Exception {
		utx.commit();
	}

	private void insertData() throws Exception {
		utx.begin();
		em.joinTransaction();

		ue = new UserEquipment();
		ue.setTypeAllocationCode(id);
		ue.setManufacturer(INITIAL_MANUFACTURER);
		ue.setAccessCapabilityClass(new AccessCapability(0, "access capability"));
		ue.setInputModeClass(new InputMode(0, "input mode"));
		ue.setoS(new OS(0, "operating system"));
		ue.setUserEquipmentType(new UserEquipmentType(0, "user equipment type"));
		em.persist(ue);

		utx.commit();
		em.clear();
	}

	@Test
	public void basicCRUDTest() throws Exception {
		UserEquipment loadedUE = em.find(UserEquipment.class, id);
		assertEquals("Failed to insert", INITIAL_MANUFACTURER, loadedUE.getManufacturer());

		loadedUE.setManufacturer(UPDATED_MANUFACTURER);
		UserEquipment updatedUE = em.find(UserEquipment.class, id);
		assertEquals("Failed to update", UPDATED_MANUFACTURER, updatedUE.getManufacturer());

		em.remove(updatedUE);
		UserEquipment shouldBeNull = em.find(UserEquipment.class, id);
		assertNull("Failed to delete", shouldBeNull);
	}

	/*
	 * Test to ensure that deletes cascade/don't cascade properly to the owned entities.
	 */
	@Test
	@Ignore
	public void testDeleteCascade() {

	}

	/*
	 * Test the manufacturer field - boundary/values
	 */
	@Test
	@Ignore
	public void testManufacturerSetter() {

	}

	/*
	 * Test the marketing name field - boundary/values
	 */
	@Test
	@Ignore
	public void testMarketingNameSetter() {

	}

	/*
	 * Test the model field - boundary/values
	 */
	@Test
	@Ignore
	public void testModelSetter() {

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
