package com.ericsson.msc.group5.dao.jpa;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.EventCauseDAO;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.EventCauseCK;

public class JPAEventCauseDAO implements EventCauseDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Collection <EventCause> getAllEventCauses() {
		return em.createNamedQuery("findAllEventCauses").getResultList();
	}

	@Override
	public EventCause getEventCause(int causeCode, int eventId) {
		return em.find(EventCause.class, new EventCauseCK(causeCode, eventId));
	}

	@Override
	public void insertEventCause(EventCause eventCause) {
		em.persist(eventCause);
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
	public void batchInsertEventCause(Collection <EventCause> eventCauseList) {
		for(EventCause eventCause : eventCauseList)
			em.persist(eventCause);
	}
}
