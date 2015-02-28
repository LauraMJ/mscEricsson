package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.EventCauseDAO;
import com.ericsson.msc.group5.entities.EventCause;

public class JPAEventCauseDAO implements EventCauseDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List <EventCause> getAllEventCauses() {
		return em.createNamedQuery("findAllEventCauses").getResultList();
	}

	@Override
	public EventCause getEventCause(int causeCode, int eventId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertEventCause(EventCause eventCause) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEventCause(EventCause eventCause) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEventCause(EventCause eventCause) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchInsertEventCause(List <EventCause> eventCauseList) {
		// TODO Auto-generated method stub
		
	}

}
