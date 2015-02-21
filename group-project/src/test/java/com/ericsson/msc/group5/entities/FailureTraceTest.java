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
public class FailureTraceTest {

	@Deployment
	public static Archive <?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(FailureTrace.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	private static int INITIAL_DURATION = 1000;
	private static int UPDATED_DURATION = 10000;
	private FailureTrace ft;
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

		AccessCapability ac = new AccessCapability();
		ac.setAccessCapability("access capability");
		em.persist(ac);

		UserEquipmentType uet = new UserEquipmentType();
		uet.setUserEquipmentType("user equipment type");
		em.persist(uet);

		OperatingSystem os = new OperatingSystem();
		os.setOperatingSystem("operating system");
		em.persist(os);

		InputMode im = new InputMode();
		im.setInputMode("input mode");
		em.persist(im);

		ft = new FailureTrace();

		UserEquipment ue = new UserEquipment(0, "marketing name", "manufacturer", ac, "model", uet, os, im);
		em.persist(ue);
		ft.setUserEqipment(ue);
		HierInfo hi = new HierInfo();
		ft.setHierInfo(hi);
		em.persist(hi);
		FailureClass fc = new FailureClass(0, "description");
		ft.setFailureClass(fc);
		em.persist(fc);
		EventCause ec = new EventCause(new EventCauseCK(0, 0), "desc");
		em.persist(ec);
		ft.setEventCause(ec);

		Country c = new Country();
		c.setCountry("country");
		c.setCountryCode(231);
		em.persist(c);
		CountryCodeNetworkCode mcc = new CountryCodeNetworkCode(new CountryCodeNetworkCodeCK(c, 0), "operator");
		ft.setCountryCodeNetworkCode(mcc);
		em.persist(mcc);
		ft.setDuration(INITIAL_DURATION);
		em.persist(ft);
		id = ft.getFailureTraceId();
		System.out.println("id =" + id);

		utx.commit();
		em.clear();
	}

	@Test
	public void basicCRUDTest() throws Exception {
		FailureTrace loadedFT = em.find(FailureTrace.class, id);
		assertEquals("Failed to insert", INITIAL_DURATION, (int) loadedFT.getDuration());

		loadedFT.setDuration(UPDATED_DURATION);
		FailureTrace updatedFT = em.find(FailureTrace.class, id);
		assertEquals("Failed to update", UPDATED_DURATION, (int) updatedFT.getDuration());

		em.remove(updatedFT);
		FailureTrace shouldBeNull = em.find(FailureTrace.class, id);
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
	 * Test the date time field - boundary/values
	 */
	@Test
	@Ignore
	public void testDateTimeSetter() {

	}

	/*
	 * Test the event id field - boundary/values
	 */
	@Test
	@Ignore
	public void testEventIdSetter() {

	}

	/*
	 * Test the cell id field - boundary/values
	 */
	@Test
	@Ignore
	public void testCellIdSetter() {

	}

	/*
	 * Test the duration field - boundary/values
	 */
	@Test
	@Ignore
	public void testDurationSetter() {

	}

	/*
	 * Test the network element field - boundary/values
	 */
	@Test
	@Ignore
	public void testNEVersionSetter() {

	}

	/*
	 * Test the imsi field - boundary/values
	 */
	@Test
	@Ignore
	public void testIMSISetter() {

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
