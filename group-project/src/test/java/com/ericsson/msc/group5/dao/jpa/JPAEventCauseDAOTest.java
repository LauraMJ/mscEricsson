package com.ericsson.msc.group5.dao.jpa;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.msc.group5.dao.EventCauseDAO;
import com.ericsson.msc.group5.dao.FailureTraceDAO;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.EventCauseCK;

@RunWith(Arquillian.class)
public class JPAEventCauseDAOTest {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private FailureTraceDAO failureTraceDAO;

	@Inject
	private UserTransaction utx;
	
	@Inject
	private EventCauseDAO eventCauseDAO;
	
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
	public void insertEventCauseTest(){
		EventCauseCK ck = new EventCauseCK(123, 546);
		EventCause ec = new EventCause(ck, "desc");
		eventCauseDAO.insertEventCause(ec);
		assertNotNull(eventCauseDAO.getAllEventCauses());
		assertTrue(eventCauseDAO.getAllEventCauses().size() == 1);
	}
	
	@Test
	public void batchInsertEventCauseTest(){
		EventCauseCK ck = new EventCauseCK(123, 546);
		EventCause ec = new EventCause(ck, "desc");
		EventCauseCK ckk = new EventCauseCK(654, 321);;
		EventCause ecc = new EventCause(ckk, " desc");;
		
		ArrayList<EventCause> list = new ArrayList<EventCause>();
		list.add(ec);
		list.add(ecc);
		
		eventCauseDAO.batchInsertEventCause(list);
		assertNotNull(eventCauseDAO.getAllEventCauses());
		assertTrue(eventCauseDAO.getAllEventCauses().size() == 2);
	}
	
	@Test
	public void getEventCauseTest(){
		EventCauseCK ck = new EventCauseCK(123, 546);
		EventCause ec = new EventCause(ck, "desc");
		EventCauseCK ckk = new EventCauseCK(654, 321);;
		EventCause ecc = new EventCause(ckk, " not");;
		
		ArrayList<EventCause> list = new ArrayList<EventCause>();
		list.add(ec);
		list.add(ecc);
		
		eventCauseDAO.batchInsertEventCause(list);
		assertTrue(eventCauseDAO.getEventCause(123, 546).getDescription()=="desc");
		
	}
	
	@Test
	public void getAllEventCausesTest(){
		EventCauseCK ck = new EventCauseCK(123, 546);
		EventCause ec = new EventCause(ck, "desc");
		EventCauseCK ckk = new EventCauseCK(654, 321);;
		EventCause ecc = new EventCause(ckk, " not");;
		
		ArrayList<EventCause> list = new ArrayList<EventCause>();
		list.add(ec);
		list.add(ecc);
		
		eventCauseDAO.batchInsertEventCause(list);
		assertNotNull(eventCauseDAO.getAllEventCauses());
		assertTrue(eventCauseDAO.getAllEventCauses().size() == 2);
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
