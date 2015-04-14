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
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(ErrorLog.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	private static String INITIAL_DATE_AND_TIME = "1/11/2013 17:15:00";
	private static String UPDATED_DATE_AND_TIME = "1/11/2013 17:34:00";
	private static String INITIAL_ERROR_MESSAGE = "Missing MCC foreign key value";
	private static String UPDATED_ERROR_MESSAGE = "Missing UE foreign key value";
	private static String INITIAL_DATA = "Date / Time: 11/01/13 17:15 Event Id: 4099 Failure Class: (null) UE Type: 21060800 Market: 344 Operator: 930 Cell Id: 4 Duration: 1000 Cause Code: (null) NE Version: 11B IMSI: 2147483647 HIER3_ID: 2147483647 HIER32_ID: 2147483647 HIER321_ID: 2147483647";
	private static String UPDATED_DATA = "Date / Time: 11/01/13 17:25 Event Id: 4099 Failure Class: (null) UE Type: 21060800 Market: 344 Operator: 930 Cell Id: 4 Duration: 1000 Cause Code: (null) NE Version: 11B IMSI: 2147483647 HIER3_ID: 2147483647 HIER32_ID: 2147483647 HIER321_ID: 2147483647";

	@Before
	public void preparePersistenceTest() throws Exception {
		// clearData();
		startTransaction();
	}

	@After
	public void commitTransaction() throws Exception {
		utx.commit();
	}

	@Test
	public void basicCRUDTest() throws Exception {
		ErrorLog createdEL = new ErrorLog(INITIAL_DATE_AND_TIME, INITIAL_ERROR_MESSAGE, INITIAL_DATA);

		em.persist(createdEL);
		int newId = createdEL.getErrorLogId();

		ErrorLog loadedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", INITIAL_DATE_AND_TIME, loadedEL.getGenerationTime());
		assertEquals("Failed to insert", INITIAL_ERROR_MESSAGE, loadedEL.getErrorDescription());
		assertEquals("Failed to insert", INITIAL_DATA, loadedEL.getBaseData());

		loadedEL.setBaseData(UPDATED_DATA);
		loadedEL.setErrorDescription(UPDATED_ERROR_MESSAGE);
		loadedEL.setGenerationTime(UPDATED_DATE_AND_TIME);
		ErrorLog updatedEL = em.find(ErrorLog.class, newId);
		assertEquals("Failed to insert", UPDATED_DATE_AND_TIME, updatedEL.getGenerationTime());
		assertEquals("Failed to insert", UPDATED_ERROR_MESSAGE, updatedEL.getErrorDescription());
		assertEquals("Failed to insert", UPDATED_DATA, updatedEL.getBaseData());

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