package com.ericsson.msc.group5.dao;

import java.util.Collection;
import com.ericsson.msc.group5.entities.EventCause;

/**
 * A Data Access Object interface for the EventCause entity. Defines common DAO methods.
 */
public interface EventCauseDAO {

	/**
	 * Retrieve all EventCause objects present in the data store.
	 * 
	 * @return a Collection of EventCause objects; empty collection if no EventCause objects are present in the data store.
	 */
	public Collection <EventCause> getAllEventCauses();

	/**
	 * Retrieve the EventCause associated with the 2-part composite key passed in as a parameter.
	 * 
	 * @param causeCode
	 *            first part of a 2-part composite key that uniquely identifies the EventCause to be retrieved.
	 * @param eventId
	 *            second part of a 2-part composite key that uniquely identifies the EventCause to be retrieved.
	 * @return EventCause with the provided causeCode+eventId combination iff present in the data store; otherwise null.
	 */
	public EventCause getEventCause(int causeCode, int eventId);

	/**
	 * Insert a new EventCause object into the data store.
	 * 
	 * @param eventCause
	 *            A new EventCause object.
	 */
	public void insertEventCause(EventCause eventCause);

	/**
	 * Batch insert a Collection of EventCause objects into the data store. Optimized for handling large volumes of data.
	 * 
	 * @param eventCauseList
	 *            A Collection of new EventCause objects.
	 */
	public void batchInsertEventCause(Collection <EventCause> eventCauseList);
}
