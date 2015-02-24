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
public class ErrorLogTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(AccessCapability.class.getPackage())
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	private static String INITIAL_DATE_AND_TIME = "1/11/2013 17:15:00";
	private static String UPDATED_DATE_AND_TIME = "1/11/2013 17:34:00";
	private static Integer INITIAL_EVENT_ID = 4098;
	private static Integer UPDATED_EVENT_ID = 4099;
	private static Integer INITIAL_FAILURE_CLASS = 1;
	private static Integer UPDATED_FAILURE_CLASS = 0;
	private static Integer INITIAL_USER_EQUIPMENT_TYPE = 21060800;
	private static Integer UPDATED_USER_EQUIPMENT_TYPE = 33000253;
	private static Integer INITIAL_market = 344;
	private static Integer UPDATED_market = 240;
	private static Integer INITIAL_OPERATOR = 930;
	private static Integer UPDATED_OPERATOR = 21;
	private static Integer INITIAL_CELL_ID = 4;
	private static Integer UPDATED_CELL_ID = 5;
	private static Integer INITIAL_DURATION = 1000;
	private static Integer UPDATED_DURATION = 2000;
	private static Integer INITIAL_CAUSE_CODE = 13;
	private static Integer UPDATED_CAUSE_CODE = 2;
	private static String INITIAL_NE_VERSION = "11B";
	private static String UPDATED_NE_VERSION = "12A";
	private static Long INITIAL_IMSI = 344930000000011L;
	private static Long UPDATED_IMSI = 310560000000012L;
	private static Long INITIAL_HIER3_ID = 4809532081614990000L;
	private static Long UPDATED_HIER3_ID = 7302598826786560000L;
	private static Long INITIAL_HIER32_ID = 8226896360947470000L;
	private static Long UPDATED_HIER32_ID = 4970937722532610000L;
	private static Long INITIAL_HIER321_ID = 1150444940909480000L;
	private static Long UPDATED_HIER321_ID = 5509039050139200000L;

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
	public void basicCRUDTestDateAndTime() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_DATE_AND_TIME,
				INITIAL_EVENT_ID, INITIAL_FAILURE_CLASS,
				INITIAL_USER_EQUIPMENT_TYPE, INITIAL_market, INITIAL_OPERATOR,
				INITIAL_CELL_ID, INITIAL_DURATION, INITIAL_CAUSE_CODE,
				INITIAL_NE_VERSION, INITIAL_IMSI, INITIAL_HIER3_ID,
				INITIAL_HIER32_ID, INITIAL_HIER321_ID);

		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_DATE_AND_TIME,
				loadedEL.getDateAndTime());

		loadedEL.setDateAndTime(UPDATED_DATE_AND_TIME);
		ErrorLog updatedAC = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_DATE_AND_TIME,
				loadedEL.getDateAndTime());

		em.remove(updatedAC);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);

	}

	@Test
	public void basicCRUDTestEventId() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_DATE_AND_TIME,
				INITIAL_EVENT_ID, INITIAL_FAILURE_CLASS,
				INITIAL_USER_EQUIPMENT_TYPE, INITIAL_market, INITIAL_OPERATOR,
				INITIAL_CELL_ID, INITIAL_DURATION, INITIAL_CAUSE_CODE,
				INITIAL_NE_VERSION, INITIAL_IMSI, INITIAL_HIER3_ID,
				INITIAL_HIER32_ID, INITIAL_HIER321_ID);

		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_EVENT_ID, loadedEL.getEventId());

		loadedEL.setEventId(UPDATED_EVENT_ID);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_EVENT_ID, loadedEL.getEventId());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);

	}

	@Test
	public void basicCRUDTestFailureClass() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_DATE_AND_TIME,
				INITIAL_EVENT_ID, INITIAL_FAILURE_CLASS,
				INITIAL_USER_EQUIPMENT_TYPE, INITIAL_market, INITIAL_OPERATOR,
				INITIAL_CELL_ID, INITIAL_DURATION, INITIAL_CAUSE_CODE,
				INITIAL_NE_VERSION, INITIAL_IMSI, INITIAL_HIER3_ID,
				INITIAL_HIER32_ID, INITIAL_HIER321_ID);

		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_FAILURE_CLASS,
				loadedEL.getFailureClass());

		loadedEL.setFailureClass(UPDATED_FAILURE_CLASS);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_FAILURE_CLASS,
				loadedEL.getFailureClass());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);

	}

	@Test
	public void basicCRUDTestUserEquipmentType() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_DATE_AND_TIME,
				INITIAL_EVENT_ID, INITIAL_FAILURE_CLASS,
				INITIAL_USER_EQUIPMENT_TYPE, INITIAL_market, INITIAL_OPERATOR,
				INITIAL_CELL_ID, INITIAL_DURATION, INITIAL_CAUSE_CODE,
				INITIAL_NE_VERSION, INITIAL_IMSI, INITIAL_HIER3_ID,
				INITIAL_HIER32_ID, INITIAL_HIER321_ID);

		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_USER_EQUIPMENT_TYPE,
				loadedEL.getUserEquipmentType());

		loadedEL.setUserEquipmentType(UPDATED_USER_EQUIPMENT_TYPE);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_USER_EQUIPMENT_TYPE,
				loadedEL.getUserEquipmentType());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);

	}

	@Test
	public void basicCRUDTestMarket() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_DATE_AND_TIME,
				INITIAL_EVENT_ID, INITIAL_FAILURE_CLASS,
				INITIAL_USER_EQUIPMENT_TYPE, INITIAL_market, INITIAL_OPERATOR,
				INITIAL_CELL_ID, INITIAL_DURATION, INITIAL_CAUSE_CODE,
				INITIAL_NE_VERSION, INITIAL_IMSI, INITIAL_HIER3_ID,
				INITIAL_HIER32_ID, INITIAL_HIER321_ID);

		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_market, loadedEL.getMarket());

		loadedEL.setMarket(UPDATED_market);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_market, loadedEL.getMarket());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);

	}

	@Test
	public void basicCRUDTestOperator() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_DATE_AND_TIME,
				INITIAL_EVENT_ID, INITIAL_FAILURE_CLASS,
				INITIAL_USER_EQUIPMENT_TYPE, INITIAL_market, INITIAL_OPERATOR,
				INITIAL_CELL_ID, INITIAL_DURATION, INITIAL_CAUSE_CODE,
				INITIAL_NE_VERSION, INITIAL_IMSI, INITIAL_HIER3_ID,
				INITIAL_HIER32_ID, INITIAL_HIER321_ID);

		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_OPERATOR,
				loadedEL.getOperator());

		loadedEL.setOperator(UPDATED_OPERATOR);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_OPERATOR,
				loadedEL.getOperator());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);

	}

	@Test
	public void basicCRUDTestCellId() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_DATE_AND_TIME,
				INITIAL_EVENT_ID, INITIAL_FAILURE_CLASS,
				INITIAL_USER_EQUIPMENT_TYPE, INITIAL_market, INITIAL_OPERATOR,
				INITIAL_CELL_ID, INITIAL_DURATION, INITIAL_CAUSE_CODE,
				INITIAL_NE_VERSION, INITIAL_IMSI, INITIAL_HIER3_ID,
				INITIAL_HIER32_ID, INITIAL_HIER321_ID);

		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_CELL_ID, loadedEL.getCellId());

		loadedEL.setCellId(UPDATED_CELL_ID);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_CELL_ID, loadedEL.getCellId());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);

	}

	@Test
	public void basicCRUDTestDuration() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_DATE_AND_TIME,
				INITIAL_EVENT_ID, INITIAL_FAILURE_CLASS,
				INITIAL_USER_EQUIPMENT_TYPE, INITIAL_market, INITIAL_OPERATOR,
				INITIAL_CELL_ID, INITIAL_DURATION, INITIAL_CAUSE_CODE,
				INITIAL_NE_VERSION, INITIAL_IMSI, INITIAL_HIER3_ID,
				INITIAL_HIER32_ID, INITIAL_HIER321_ID);

		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_DURATION,
				loadedEL.getDuration());

		loadedEL.setDuration(UPDATED_DURATION);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_DURATION,
				loadedEL.getDuration());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);

	}

	@Test
	public void basicCRUDTestCauseCode() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_DATE_AND_TIME,
				INITIAL_EVENT_ID, INITIAL_FAILURE_CLASS,
				INITIAL_USER_EQUIPMENT_TYPE, INITIAL_market, INITIAL_OPERATOR,
				INITIAL_CELL_ID, INITIAL_DURATION, INITIAL_CAUSE_CODE,
				INITIAL_NE_VERSION, INITIAL_IMSI, INITIAL_HIER3_ID,
				INITIAL_HIER32_ID, INITIAL_HIER321_ID);

		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_CAUSE_CODE,
				loadedEL.getCauseCode());

		loadedEL.setCauseCode(UPDATED_CAUSE_CODE);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_CAUSE_CODE,
				loadedEL.getCauseCode());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);

	}

	@Test
	public void basicCRUDTestNEVersion() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_DATE_AND_TIME,
				INITIAL_EVENT_ID, INITIAL_FAILURE_CLASS,
				INITIAL_USER_EQUIPMENT_TYPE, INITIAL_market, INITIAL_OPERATOR,
				INITIAL_CELL_ID, INITIAL_DURATION, INITIAL_CAUSE_CODE,
				INITIAL_NE_VERSION, INITIAL_IMSI, INITIAL_HIER3_ID,
				INITIAL_HIER32_ID, INITIAL_HIER321_ID);

		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_NE_VERSION,
				loadedEL.getNeVersion());

		loadedEL.setNeVersion(UPDATED_NE_VERSION);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_NE_VERSION,
				loadedEL.getNeVersion());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);

	}

	@Test
	public void basicCRUDTestIMSI() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_DATE_AND_TIME,
				INITIAL_EVENT_ID, INITIAL_FAILURE_CLASS,
				INITIAL_USER_EQUIPMENT_TYPE, INITIAL_market, INITIAL_OPERATOR,
				INITIAL_CELL_ID, INITIAL_DURATION, INITIAL_CAUSE_CODE,
				INITIAL_NE_VERSION, INITIAL_IMSI, INITIAL_HIER3_ID,
				INITIAL_HIER32_ID, INITIAL_HIER321_ID);

		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_IMSI, loadedEL.getImsi());

		loadedEL.setImsi(UPDATED_IMSI);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_IMSI, loadedEL.getImsi());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);

	}

	@Test
	public void basicCRUDTestHier3Id() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_DATE_AND_TIME,
				INITIAL_EVENT_ID, INITIAL_FAILURE_CLASS,
				INITIAL_USER_EQUIPMENT_TYPE, INITIAL_market, INITIAL_OPERATOR,
				INITIAL_CELL_ID, INITIAL_DURATION, INITIAL_CAUSE_CODE,
				INITIAL_NE_VERSION, INITIAL_IMSI, INITIAL_HIER3_ID,
				INITIAL_HIER32_ID, INITIAL_HIER321_ID);

		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_HIER3_ID, loadedEL.getHier3Id());

		loadedEL.setHier3Id(UPDATED_HIER3_ID);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_HIER3_ID, loadedEL.getHier3Id());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);

	}

	@Test
	public void basicCRUDTestHier32Id() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_DATE_AND_TIME,
				INITIAL_EVENT_ID, INITIAL_FAILURE_CLASS,
				INITIAL_USER_EQUIPMENT_TYPE, INITIAL_market, INITIAL_OPERATOR,
				INITIAL_CELL_ID, INITIAL_DURATION, INITIAL_CAUSE_CODE,
				INITIAL_NE_VERSION, INITIAL_IMSI, INITIAL_HIER3_ID,
				INITIAL_HIER32_ID, INITIAL_HIER321_ID);

		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_HIER32_ID,
				loadedEL.getHier32Id());

		loadedEL.setHier32Id(UPDATED_HIER32_ID);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_HIER32_ID,
				loadedEL.getHier32Id());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);

	}

	@Test
	public void basicCRUDTestHier321Id() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_DATE_AND_TIME,
				INITIAL_EVENT_ID, INITIAL_FAILURE_CLASS,
				INITIAL_USER_EQUIPMENT_TYPE, INITIAL_market, INITIAL_OPERATOR,
				INITIAL_CELL_ID, INITIAL_DURATION, INITIAL_CAUSE_CODE,
				INITIAL_NE_VERSION, INITIAL_IMSI, INITIAL_HIER3_ID,
				INITIAL_HIER32_ID, INITIAL_HIER321_ID);

		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_HIER321_ID,
				loadedEL.getHier321Id());

		loadedEL.setHier321Id(UPDATED_HIER321_ID);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_HIER321_ID,
				loadedEL.getHier321Id());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);

	}

	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery(
				"delete from com.ericsson.msc.group5.entities.AccessCapability")
				.executeUpdate();
		utx.commit();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}

}