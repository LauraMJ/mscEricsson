package com.ericsson.msc.group5.dao;

import java.util.List;
import javax.ejb.Local;
import com.ericsson.msc.group5.entities.EventCause;

/**
 * A Data Access Object interface for the EventCause entity. Defines common DAO methods.
 */
@Local
public interface EventCauseDAO {

	/**
	 * Retrieve all EventCause objects present in the data store.
	 * 
	 * @return a List of EventCause objects; empty list if no EventCause objects are present in the data store.
	 */
	public List <EventCause> getAllEventCauses();

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
	 * Update an EventCause object that exists in the data store. Object comparison is based on the composite key causeCode+eventId combination.
	 * 
	 * @param eventCause
	 *            An EventCause entity that exists in the data store. An exception will be thrown the provided EventCause is not in the data store.
	 */
	public void updateEventCause(EventCause eventCause);

	/**
	 * Delete an existing EventCause object from the data store. Object comparison is based on the composite key causeCode+eventId combination.
	 * 
	 * @param eventCause
	 *            An EventCause entity that exists in the data store. An exception will be thrown the provided EventCause is not in the data store.
	 */
	public void deleteEventCause(EventCause eventCause);

	/**
	 * Batch insert a list of EventCause objects into the data store. Optimized for handling large volumes of data.
	 * 
	 * @param eventCauseList
	 *            A list of new EventCause objects.
	 */
	public void batchInsertEventCause(List <EventCause> eventCauseList);
}
