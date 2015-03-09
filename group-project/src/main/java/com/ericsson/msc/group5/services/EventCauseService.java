package com.ericsson.msc.group5.services;

import java.util.Collection;

import javax.ejb.Local;

import com.ericsson.msc.group5.entities.EventCause;

@Local
public interface EventCauseService {

	public Collection<EventCause> getCauseCode();
	
	public void addEventCause(Collection <EventCause> eventCauses);
}
