package com.ericsson.msc.group5.services;

import java.util.Collection;
import javax.ejb.Local;
import com.ericsson.msc.group5.entities.EventCause;

/**
 * EventCause service EJB interface.
 */
@Local
public interface EventCauseService {

	public Collection <EventCause> getCauseCode();

	public void addEventCauses(Collection <EventCause> eventCauses);

	public void addEventCause(EventCause testEventCause);
}
