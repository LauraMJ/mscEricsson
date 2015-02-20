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
	public static Archive <?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(AccessCapability.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;
	
	private static String INITIAL_dateAndTime = "1/11/2013 17:15:00";
	private static String UPDATED_dateAndTime = "1/11/2013 17:34:00";
	private static Integer INITIAL_eventId = 4098;
	private static Integer UPDATED_eventId = 4099;
	private static Integer INITIAL_failureClass = 1;
	private static Integer UPDATED_failureClass = 0;
	private static Integer INITIAL_userEquipmentType = 21060800;
	private static Integer UPDATED_userEquipmentType = 33000253;
	private static Integer INITIAL_market = 344;
	private static Integer UPDATED_market = 240;
	private static Integer INITIAL_operator = 930;
	private static Integer UPDATED_operator = 21;
	private static Integer INITIAL_cellId = 4;
	private static Integer UPDATED_cellId = 5;
	private static Integer INITIAL_duration = 1000;
	private static Integer UPDATED_duration = 2000;
	private static Integer INITIAL_causeCode = 13;
	private static Integer UPDATED_causeCode = 2;
	private static String INITIAL_neVersion = "11B";
	private static String UPDATED_neVersion = "12A";
	private static Long INITIAL_imsi = 344930000000011L;
	private static Long UPDATED_imsi = 310560000000012L;
	private static Long INITIAL_hier3Id = 4809532081614990000L;
	private static Long UPDATED_hier3Id = 7302598826786560000L;
	private static Long INITIAL_hier32Id = 8226896360947470000L;
	private static Long UPDATED_hier32Id = 4970937722532610000L;
	private static Long INITIAL_hier321Id = 1150444940909480000L;
	private static Long UPDATED_hier321Id = 5509039050139200000L;
	
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
		ErrorLog createdEL = new ErrorLog(0, INITIAL_dateAndTime, INITIAL_eventId, INITIAL_failureClass,
				INITIAL_userEquipmentType, INITIAL_market, INITIAL_operator, INITIAL_cellId, INITIAL_duration,
				INITIAL_causeCode, INITIAL_neVersion, INITIAL_imsi, INITIAL_hier3Id, INITIAL_hier32Id, INITIAL_hier321Id);
		
		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_dateAndTime, loadedEL.getDateAndTime());

		loadedEL.setDateAndTime(UPDATED_dateAndTime);
		ErrorLog updatedAC = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_dateAndTime, loadedEL.getDateAndTime());

		em.remove(updatedAC);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);

	}
	
	@Test
	public void basicCRUDTestEventId() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_dateAndTime, INITIAL_eventId, INITIAL_failureClass,
				INITIAL_userEquipmentType, INITIAL_market, INITIAL_operator, INITIAL_cellId, INITIAL_duration,
				INITIAL_causeCode, INITIAL_neVersion, INITIAL_imsi, INITIAL_hier3Id, INITIAL_hier32Id, INITIAL_hier321Id);
		
		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();
		
		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_eventId, loadedEL.getEventId());

		loadedEL.setEventId(UPDATED_eventId);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_eventId, loadedEL.getEventId());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);
	
	}
	
	@Test
	public void basicCRUDTestFailureClass() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_dateAndTime, INITIAL_eventId, INITIAL_failureClass,
				INITIAL_userEquipmentType, INITIAL_market, INITIAL_operator, INITIAL_cellId, INITIAL_duration,
				INITIAL_causeCode, INITIAL_neVersion, INITIAL_imsi, INITIAL_hier3Id, INITIAL_hier32Id, INITIAL_hier321Id);
		
		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();
		
		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_failureClass, loadedEL.getFailureClass());

		loadedEL.setFailureClass(UPDATED_failureClass);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_failureClass, loadedEL.getFailureClass());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);
	
	}
	
	@Test
	public void basicCRUDTestUserEquipmentType() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_dateAndTime, INITIAL_eventId, INITIAL_failureClass,
				INITIAL_userEquipmentType, INITIAL_market, INITIAL_operator, INITIAL_cellId, INITIAL_duration,
				INITIAL_causeCode, INITIAL_neVersion, INITIAL_imsi, INITIAL_hier3Id, INITIAL_hier32Id, INITIAL_hier321Id);
		
		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();
		
		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_userEquipmentType, loadedEL.getUserEquipmentType());

		loadedEL.setUserEquipmentType(UPDATED_userEquipmentType);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_userEquipmentType, loadedEL.getUserEquipmentType());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);
	
	}
	
	@Test
	public void basicCRUDTestMarket() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_dateAndTime, INITIAL_eventId, INITIAL_failureClass,
				INITIAL_userEquipmentType, INITIAL_market, INITIAL_operator, INITIAL_cellId, INITIAL_duration,
				INITIAL_causeCode, INITIAL_neVersion, INITIAL_imsi, INITIAL_hier3Id, INITIAL_hier32Id, INITIAL_hier321Id);
		
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
		ErrorLog createdEL = new ErrorLog(0, INITIAL_dateAndTime, INITIAL_eventId, INITIAL_failureClass,
				INITIAL_userEquipmentType, INITIAL_market, INITIAL_operator, INITIAL_cellId, INITIAL_duration,
				INITIAL_causeCode, INITIAL_neVersion, INITIAL_imsi, INITIAL_hier3Id, INITIAL_hier32Id, INITIAL_hier321Id);
		
		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();
		
		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_operator, loadedEL.getOperator());

		loadedEL.setOperator(UPDATED_operator);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_operator, loadedEL.getOperator());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);
	
	}
	
	@Test
	public void basicCRUDTestCellId() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_dateAndTime, INITIAL_eventId, INITIAL_failureClass,
				INITIAL_userEquipmentType, INITIAL_market, INITIAL_operator, INITIAL_cellId, INITIAL_duration,
				INITIAL_causeCode, INITIAL_neVersion, INITIAL_imsi, INITIAL_hier3Id, INITIAL_hier32Id, INITIAL_hier321Id);
		
		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();
		
		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_cellId, loadedEL.getCellId());

		loadedEL.setCellId(UPDATED_cellId);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_cellId, loadedEL.getCellId());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);
	
	}
	
	@Test
	public void basicCRUDTestDuration() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_dateAndTime, INITIAL_eventId, INITIAL_failureClass,
				INITIAL_userEquipmentType, INITIAL_market, INITIAL_operator, INITIAL_cellId, INITIAL_duration,
				INITIAL_causeCode, INITIAL_neVersion, INITIAL_imsi, INITIAL_hier3Id, INITIAL_hier32Id, INITIAL_hier321Id);
		
		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();
		
		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_duration, loadedEL.getDuration());

		loadedEL.setDuration(UPDATED_duration);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_duration, loadedEL.getDuration());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);
	
	}
	
	@Test
	public void basicCRUDTestCauseCode() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_dateAndTime, INITIAL_eventId, INITIAL_failureClass,
				INITIAL_userEquipmentType, INITIAL_market, INITIAL_operator, INITIAL_cellId, INITIAL_duration,
				INITIAL_causeCode, INITIAL_neVersion, INITIAL_imsi, INITIAL_hier3Id, INITIAL_hier32Id, INITIAL_hier321Id);
		
		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();
		
		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_causeCode, loadedEL.getCauseCode());

		loadedEL.setCauseCode(UPDATED_causeCode);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_causeCode, loadedEL.getCauseCode());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);
	
	}
	
	@Test
	public void basicCRUDTestNEVersion() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_dateAndTime, INITIAL_eventId, INITIAL_failureClass,
				INITIAL_userEquipmentType, INITIAL_market, INITIAL_operator, INITIAL_cellId, INITIAL_duration,
				INITIAL_causeCode, INITIAL_neVersion, INITIAL_imsi, INITIAL_hier3Id, INITIAL_hier32Id, INITIAL_hier321Id);
		
		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();
		
		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_neVersion, loadedEL.getNeVersion());

		loadedEL.setNeVersion(UPDATED_neVersion);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_neVersion, loadedEL.getNeVersion());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);
	
	}
	
	@Test
	public void basicCRUDTestIMSI() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_dateAndTime, INITIAL_eventId, INITIAL_failureClass,
				INITIAL_userEquipmentType, INITIAL_market, INITIAL_operator, INITIAL_cellId, INITIAL_duration,
				INITIAL_causeCode, INITIAL_neVersion, INITIAL_imsi, INITIAL_hier3Id, INITIAL_hier32Id, INITIAL_hier321Id);
		
		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();
		
		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_imsi, loadedEL.getImsi());

		loadedEL.setImsi(UPDATED_imsi);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_imsi, loadedEL.getImsi());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);
	
	}
	
	@Test
	public void basicCRUDTestHier3Id() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_dateAndTime, INITIAL_eventId, INITIAL_failureClass,
				INITIAL_userEquipmentType, INITIAL_market, INITIAL_operator, INITIAL_cellId, INITIAL_duration,
				INITIAL_causeCode, INITIAL_neVersion, INITIAL_imsi, INITIAL_hier3Id, INITIAL_hier32Id, INITIAL_hier321Id);
		
		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();
		
		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_hier3Id, loadedEL.getHier3Id());

		loadedEL.setHier3Id(UPDATED_hier3Id);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_hier3Id, loadedEL.getHier3Id());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);
	
	}
	
	@Test
	public void basicCRUDTestHier32Id() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_dateAndTime, INITIAL_eventId, INITIAL_failureClass,
				INITIAL_userEquipmentType, INITIAL_market, INITIAL_operator, INITIAL_cellId, INITIAL_duration,
				INITIAL_causeCode, INITIAL_neVersion, INITIAL_imsi, INITIAL_hier3Id, INITIAL_hier32Id, INITIAL_hier321Id);
		
		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();
		
		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_hier32Id, loadedEL.getHier32Id());

		loadedEL.setHier32Id(UPDATED_hier32Id);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_hier32Id, loadedEL.getHier32Id());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
		assertNull("Failed to delete", shouldBeNull);
	
	}
	
	@Test
	public void basicCRUDTestHier321Id() throws Exception {
		ErrorLog createdEL = new ErrorLog(0, INITIAL_dateAndTime, INITIAL_eventId, INITIAL_failureClass,
				INITIAL_userEquipmentType, INITIAL_market, INITIAL_operator, INITIAL_cellId, INITIAL_duration,
				INITIAL_causeCode, INITIAL_neVersion, INITIAL_imsi, INITIAL_hier3Id, INITIAL_hier32Id, INITIAL_hier321Id);
		
		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();
		
		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_hier321Id, loadedEL.getHier321Id());

		loadedEL.setHier321Id(UPDATED_hier321Id);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to update", UPDATED_hier321Id, loadedEL.getHier321Id());

		em.remove(updatedEL);
		ErrorLog shouldBeNull = em.find(ErrorLog.class, newId);
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
