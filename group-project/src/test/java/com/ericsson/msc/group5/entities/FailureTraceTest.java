package com.ericsson.msc.group5.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.dao.FailureTraceDAO;

@RunWith(Arquillian.class)
public class FailureTraceTest {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;
	@Inject
	private FailureTraceDAO failureTraceDAO;

	private static Integer INITIAL_DURATION = 1000;
	private static Integer UPDATED_DURATION = 10000;
	private FailureTrace ft;
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

		ft = new FailureTrace();
		ft.setFailureTraceId(0L);

		UserEquipment ue = new UserEquipment(0, "marketing name", "manufacturer", "access capability", "model", "vendor", "user equipment type",
				"operating system", "input mode");
		em.persist(ue);

		ft.setUserEquipment(ue);

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

	@SuppressWarnings("deprecation")
	@Test
	public void basicCRUDTest() throws Exception {
		FailureTrace loadedFT = em.find(FailureTrace.class, id);
		assertEquals("Failed to insert", INITIAL_DURATION, loadedFT.getDuration());

		loadedFT.setDuration(UPDATED_DURATION);
		FailureTrace updatedFT = em.find(FailureTrace.class, id);
		assertEquals("Failed to update", UPDATED_DURATION, updatedFT.getDuration());

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
