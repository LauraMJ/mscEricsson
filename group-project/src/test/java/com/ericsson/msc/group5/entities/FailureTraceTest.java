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

		ft = new FailureTrace();
		UserEquipment ue = new UserEquipment(0, "marketing name", "manufacturer", new AccessCapability(0, "access capability"), "model", new UserEquipmentType(
				0, "user equipment type"), new OS(0, "operating system"), new InputMode(0, "input mode"));
		ft.setUserEqipment(ue);
		HierInfo hi = new HierInfo();
		ft.setHierInfo(hi);
		FailureClass fc = new FailureClass(0, "description");
		ft.setFailureClass(fc);
		EventCause ec = new EventCause(new EventCauseCK(0, 0), "desc");
		ft.setCauseCode(ec);
		CountryCodeNetworkCode mcc = new CountryCodeNetworkCode(new CountryCodeNetworkCodeCK(0, 0), "operator");
		mcc.setCountry(new Country(0, "country"));
		ft.setMarketOperator(mcc);
		ft.setDuration(INITIAL_DURATION);
		em.persist(ft);
		id = ft.getFailureTraceId();

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
