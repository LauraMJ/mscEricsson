package com.ericsson.msc.group5.dao.jpa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import com.ericsson.msc.group5.dao.FailureTraceDAO;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.EventCauseCK;
import com.ericsson.msc.group5.entities.FailureTrace;
import com.ericsson.msc.group5.entities.UserEquipment;

@RunWith(Arquillian.class)
public class JPAFailureTraceDAOTest {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private FailureTraceDAO failureTraceDAO;

	@Inject
	private UserTransaction utx;

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
	public void testInsertion() {
		FailureTrace ft = new FailureTrace();
		ft.setFailureTraceId(0L);
		failureTraceDAO.insertFailureTrace(ft);
		assertNotNull(failureTraceDAO.getAllFailureTraces());
		assertTrue(failureTraceDAO.getAllFailureTraces().size() == 1);
	}

	@Test
	public void testGetEventCauseForIMSI() {
		String imsi = "1234";
		EventCause eventCause = new EventCause(new EventCauseCK(1, 1), "description");
		em.persist(eventCause);

		FailureTrace ft = new FailureTrace();
		ft.setFailureTraceId(0L);
		ft.setEventCause(eventCause);
		ft.setIMSI(imsi);
		failureTraceDAO.insertFailureTrace(ft);
		assertTrue(failureTraceDAO.getEventCauseForImsi(imsi).contains("description"));
		assertTrue(failureTraceDAO.getEventCauseForImsi("12345").size() == 0);
	}

	@Test
	public void getImsiOfFailureWithinTimePeriod() {
		String imsi1 = "1234";
		long days1 = 213412;
		Date date1 = new Date(days1);
		String imsi2 = "123456";
		long days2 = days1 + 6;
		Date date2 = new Date(days2);

		FailureTrace ft1 = new FailureTrace();
		ft1.setFailureTraceId(0L);
		ft1.setIMSI(imsi1);
		ft1.setDateTime(date1);

		FailureTrace ft2 = new FailureTrace();
		ft2.setFailureTraceId(1L);
		ft2.setIMSI(imsi2);
		ft2.setDateTime(date2);

		failureTraceDAO.insertFailureTrace(ft1);
		failureTraceDAO.insertFailureTrace(ft2);

		assertTrue(failureTraceDAO.getImsiOfFailureWithinTimePeriod(new Date(days1 - 1), new Date(days2 + 1)).size() == 2);
		assertTrue(failureTraceDAO.getImsiOfFailureWithinTimePeriod(new Date(days1 - 1), new Date(days2 + 1)).contains(imsi1));
		assertTrue(failureTraceDAO.getImsiOfFailureWithinTimePeriod(new Date(days1 - 1), new Date(days2 + 1)).contains(imsi2));
	}

	@Test
	public void testGetCountAndTotalDurationForGivenImsiWithinTimePeriod() {
		String imsi = "1234";
		long days = 213412;
		Date date = new Date(days);

		FailureTrace ft = new FailureTrace();
		ft.setFailureTraceId(0L);
		ft.setIMSI(imsi);
		ft.setDateTime(date);

		failureTraceDAO.insertFailureTrace(ft);

		assertTrue(failureTraceDAO.getCountAndTotalDurationForGivenImsiWithinTimePeriod(new Date(days - 1), new Date(days + 1), imsi).contains(imsi));
		assertTrue(failureTraceDAO.getCountAndTotalDurationForGivenImsiWithinTimePeriod(new Date(days - 2), new Date(days - 1), imsi).size() == 0);
		assertTrue(failureTraceDAO.getCountAndTotalDurationForGivenImsiWithinTimePeriod(new Date(days + 1), new Date(days + 2), imsi).size() == 0);
		assertTrue(failureTraceDAO.getCountAndTotalDurationForGivenImsiWithinTimePeriod(new Date(days - 1), new Date(days + 1), "12345").size() == 0);
	}

	@Test
	public void testGetCountOfFailuresForModelWithinTimePeriod() {
		String model = "model";
		UserEquipment ue = new UserEquipment();
		ue.setModel(model);
		ue.setTypeAllocationCode(1);
		em.persist(ue);

		String differentModel = "different model";
		UserEquipment ue2 = new UserEquipment();
		ue2.setModel(differentModel);
		ue2.setTypeAllocationCode(2);
		em.persist(ue2);

		long days = 213412;
		Date date = new Date(days);
		FailureTrace ft1 = new FailureTrace();
		ft1.setFailureTraceId(0L);
		ft1.setIMSI("12");
		ft1.setUserEquipment(ue);
		ft1.setDateTime(date);
		FailureTrace ft2 = new FailureTrace();
		ft2.setIMSI("12");
		ft2.setFailureTraceId(1L);
		ft2.setUserEquipment(ue);
		ft2.setDateTime(date);
		FailureTrace ft3 = new FailureTrace();
		ft3.setIMSI("12");
		ft3.setFailureTraceId(2L);
		ft3.setUserEquipment(ue2);
		ft3.setDateTime(date);

		failureTraceDAO.insertFailureTrace(ft1);
		failureTraceDAO.insertFailureTrace(ft2);
		failureTraceDAO.insertFailureTrace(ft3);

		System.out.println(failureTraceDAO.getCountOfFailuresForModelWithinTimePeriod(model, new Date(days - 1), new Date(days + 1)));
		System.out.println(failureTraceDAO.getCountOfFailuresForModelWithinTimePeriod(differentModel, new Date(days - 1), new Date(days + 1)));

		assertTrue(failureTraceDAO.getCountOfFailuresForModelWithinTimePeriod(model, new Date(days - 1), new Date(days + 1)).contains("2"));
		assertTrue(failureTraceDAO.getCountOfFailuresForModelWithinTimePeriod(differentModel, new Date(days - 1), new Date(days + 1)).contains("1"));
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
