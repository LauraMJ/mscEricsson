package com.ericsson.msc.group5.services;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ericsson.msc.group5.dao.EventCauseDAO;
import com.ericsson.msc.group5.entities.EventCause;



@Stateless
@Local
public class EventCauseServiceEJB implements EventCauseService{
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private EventCauseDAO dao;

	@Override
	public Collection <EventCause> getCauseCode() {
		return dao.getAllEventCauses();
	}
	
	@Override
	public void addEventCause(Collection<EventCause> eventCauses) {
		dao.batchInsertEventCause(eventCauses);
		
	}
	

}
