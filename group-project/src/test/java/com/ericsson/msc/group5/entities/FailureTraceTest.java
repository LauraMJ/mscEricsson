package com.ericsson.msc.group5.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class FailureTraceTest {
	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	private final static Integer INITIAL_DURATION = 1000;
	private final static Integer UPDATED_DURATION = 10000;
	
	private final static Integer INITIAL_CELL_ID = 10;
	private final static Integer UPDATED_CELL_ID = 20;
	
	private final static Date INITIAL_DATE = new Date(100100101);
	private final static Date UPDATED_DATE = new Date(1001001011532L);
	
	private final static String INITIAL_HIER321 = "hier321"; 
	private final static String UPDATED_HIER321 = "updated hier321"; 
	
	private final static String INITIAL_HIER32 = "hier32";
	private final static String UPDATED_HIER32 = "updated hier32";
	
	private final static String INITIAL_HIER3 = "hier3";
	private final static String UPDATED_HIER3 = "updated hier3";
	
	private final static String INITIAL_IMSI = "hier3";
	private final static String UPDATED_IMSI = "updated hier3";
	
	private final static String INITIAL_NE_VERSION = "12A";
	private final static String UPDATED_NE_VERSION = "26B";
	
	private FailureClass fc;
	private EventCause ec;
	private UserEquipment ue;
	private Country c;
	private CountryCodeNetworkCode mcc;
	
	private Long id = 0L;

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

		FailureTrace ft = new FailureTrace();
		ft.setFailureTraceId(0L);
		
		ft.setCellId(INITIAL_CELL_ID);
		ft.setDateTime(INITIAL_DATE);
		ft.setDuration(INITIAL_DURATION);
		ft.setHier321Id(INITIAL_HIER321);
		ft.setHier32Id(INITIAL_HIER32);
		ft.setHier3Id(INITIAL_HIER3);
		ft.setIMSI(INITIAL_IMSI);
		ft.setNeVersion(INITIAL_NE_VERSION);

		ue = new UserEquipment(0, "marketing name", "manufacturer", "access capability", "model", "vendor", "user equipment type",
				"operating system", "input mode");
		em.persist(ue);

		ft.setUserEquipment(ue);

		fc = new FailureClass(0, "description");
		ft.setFailureClass(fc);
		em.persist(fc);
		ec = new EventCause(new EventCauseCK(0, 0), "desc");
		em.persist(ec);
		ft.setEventCause(ec);

		c = new Country();
		c.setCountry("country");
		c.setCountryCode(231);
		em.persist(c);
		mcc = new CountryCodeNetworkCode(new CountryCodeNetworkCodeCK(c, 0), "operator");
		ft.setCountryCodeNetworkCode(mcc);
		em.persist(mcc);
		ft.setDuration(INITIAL_DURATION);
		em.persist(ft);
		id = ft.getFailureTraceId();

		utx.commit();
		em.clear();
	}

	@Test
	public void basicCRUDTest() throws Exception {
		FailureTrace loadedFT = em.find(FailureTrace.class, id);
		assertEquals("Failed to fetch", loadedFT.getCountryCodeNetworkCode(), mcc);
		assertEquals("Failed to fetch", loadedFT.getEventCause(), ec);
		assertEquals("Failed to fetch", loadedFT.getFailureClass(), fc);
		assertEquals("Failed to fetch", loadedFT.getUserEquipment(), ue);
		assertEquals("Failed to insert", INITIAL_DURATION, loadedFT.getDuration());
		assertEquals("Failed to insert", INITIAL_CELL_ID, loadedFT.getCellId());
		assertEquals("Failed to insert", INITIAL_DATE, loadedFT.getDateTime());
		assertEquals("Failed to insert", INITIAL_HIER321, loadedFT.getHier321Id());
		assertEquals("Failed to insert", INITIAL_HIER32, loadedFT.getHier32Id());
		assertEquals("Failed to insert", INITIAL_HIER3, loadedFT.getHier3Id());
		assertEquals("Failed to insert", INITIAL_IMSI, loadedFT.getIMSI());
		assertEquals("Failed to insert", INITIAL_NE_VERSION, loadedFT.getNeVersion());
		
		loadedFT.setCellId(UPDATED_CELL_ID);
		loadedFT.setDateTime(UPDATED_DATE);
		loadedFT.setDuration(UPDATED_DURATION);
		loadedFT.setHier321Id(UPDATED_HIER321);
		loadedFT.setHier32Id(UPDATED_HIER32);
		loadedFT.setHier3Id(UPDATED_HIER3);
		loadedFT.setIMSI(UPDATED_IMSI);
		loadedFT.setNeVersion(UPDATED_NE_VERSION);
		
		em.merge(loadedFT);
		
		FailureTrace updatedFT = em.find(FailureTrace.class, id);
		assertEquals("Failed to insert", UPDATED_DURATION, updatedFT.getDuration());
		assertEquals("Failed to insert", UPDATED_CELL_ID, updatedFT.getCellId());
		assertEquals("Failed to insert", UPDATED_DATE, updatedFT.getDateTime());
		assertEquals("Failed to insert", UPDATED_HIER321, updatedFT.getHier321Id());
		assertEquals("Failed to insert", UPDATED_HIER32, updatedFT.getHier32Id());
		assertEquals("Failed to insert", UPDATED_HIER3, updatedFT.getHier3Id());
		assertEquals("Failed to insert", UPDATED_IMSI, updatedFT.getIMSI());
		assertEquals("Failed to insert", UPDATED_NE_VERSION, updatedFT.getNeVersion());

		em.remove(updatedFT);
		FailureTrace shouldBeNull = em.find(FailureTrace.class, id);
		assertNull("Failed to delete", shouldBeNull);

		em.remove(loadedFT);
	}

	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery("delete from com.ericsson.msc.group5.entities.FailureTrace").executeUpdate();
		utx.commit();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
}
