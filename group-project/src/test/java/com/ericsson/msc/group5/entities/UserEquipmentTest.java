package com.ericsson.msc.group5.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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

	private static String INITIAL_MARKETING_NAME = "initial marketing name";
	private static String UPDATED_MARKETING_NAME = "updated marketing name";

	private static String INITIAL_MANUFACTURER = "initial manufacturer";
	private static String UPDATED_MANUFACTURER = "updated manufacturer";

	private static String INITIAL_ACCESS_CAPABILITY = "initial ac";
	private static String UPDATED_ACCESS_CAPABILITY = "updated ac";

	private static String INITIAL_MODEL = "initial model";
	private static String UPDATED_MODEL = "updated model";

	private static String INITIAL_VENDOR = "initial vendor";
	private static String UPDATED_VENDOR = "updated vendor";

	private static String INITIAL_USER_EQUIPMENT_TYPE = "initial uet";
	private static String UPDATED_USER_EQUIPMENT_TYPE = "updated uet";

	private static String INITIAL_OPERATING_SYSTEM = "initial os";
	private static String UPDATED_OPERATING_SYSTEM = "updated os";

	private static String INITIAL_INPUT_MODE = "initial input mode";
	private static String UPDATED_INPUT_MODE = "updated input mode";
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

		UserEquipment ue = new UserEquipment(id, INITIAL_MARKETING_NAME, INITIAL_MANUFACTURER, INITIAL_ACCESS_CAPABILITY, INITIAL_MODEL, INITIAL_VENDOR, INITIAL_USER_EQUIPMENT_TYPE, INITIAL_OPERATING_SYSTEM, INITIAL_INPUT_MODE);
		em.persist(ue);

		utx.commit();
		em.clear();
	}

	@Test
	public void basicCRUDTest() throws Exception {
		UserEquipment loadedUE = em.find(UserEquipment.class, id);
		assertEquals("Failed to insert", INITIAL_MARKETING_NAME, loadedUE.getMarketingName());
		assertEquals("Failed to insert", INITIAL_MANUFACTURER, loadedUE.getManufacturer());
		assertEquals("Failed to insert", INITIAL_ACCESS_CAPABILITY, loadedUE.getAccessCapability());
		assertEquals("Failed to insert", INITIAL_MODEL, loadedUE.getModel());
		assertEquals("Failed to insert", INITIAL_VENDOR, loadedUE.getVendor());
		assertEquals("Failed to insert", INITIAL_USER_EQUIPMENT_TYPE, loadedUE.getUserEquipmentType());
		assertEquals("Failed to insert", INITIAL_OPERATING_SYSTEM, loadedUE.getOperatingSystem());
		assertEquals("Failed to insert", INITIAL_INPUT_MODE, loadedUE.getInputMode());

		loadedUE.setMarketingName(UPDATED_MARKETING_NAME);
		loadedUE.setManufacturer(UPDATED_MANUFACTURER);
		loadedUE.setAccessCapability(UPDATED_ACCESS_CAPABILITY);
		loadedUE.setModel(UPDATED_MODEL);
		loadedUE.setVendor(UPDATED_VENDOR);
		loadedUE.setUserEquipmentType(UPDATED_USER_EQUIPMENT_TYPE);
		loadedUE.setOperatingSystem(UPDATED_OPERATING_SYSTEM);
		loadedUE.setInputMode(UPDATED_INPUT_MODE);
		
		UserEquipment updatedUE = em.find(UserEquipment.class, id);
		assertTrue("Failed to match", loadedUE.equals(updatedUE));
		assertTrue("Failed to match", loadedUE.hashCode() == updatedUE.hashCode());
		assertEquals("Failed to insert", UPDATED_MARKETING_NAME, loadedUE.getMarketingName());
		assertEquals("Failed to insert", UPDATED_MARKETING_NAME, loadedUE.getMarketingName());
		assertEquals("Failed to update", UPDATED_MANUFACTURER, updatedUE.getManufacturer());
		assertEquals("Failed to insert", UPDATED_ACCESS_CAPABILITY, updatedUE.getAccessCapability());
		assertEquals("Failed to insert", UPDATED_MODEL, updatedUE.getModel());
		assertEquals("Failed to insert", UPDATED_VENDOR, updatedUE.getVendor());
		assertEquals("Failed to insert", UPDATED_USER_EQUIPMENT_TYPE, updatedUE.getUserEquipmentType());
		assertEquals("Failed to insert", UPDATED_OPERATING_SYSTEM, updatedUE.getOperatingSystem());
		assertEquals("Failed to insert", UPDATED_INPUT_MODE, loadedUE.getInputMode());

		em.remove(updatedUE);
		UserEquipment shouldBeNull = em.find(UserEquipment.class, id);
		assertNull("Failed to delete", shouldBeNull);
	}
	
	@Test
	public void equalityTest() throws Exception{
		UserEquipment loadedUE = em.find(UserEquipment.class, id);
		UserEquipment userEquipment = new UserEquipment();
		assertFalse("Failed to match", loadedUE.equals(null));
		assertFalse("Failed to match", loadedUE.equals(new Integer(1)));
		assertFalse("Failed to match", userEquipment.equals(loadedUE));
	}

	public void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery("delete from com.ericsson.msc.group5.entities.UserEquipment").executeUpdate();
		utx.commit();
	}

	public void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
}
