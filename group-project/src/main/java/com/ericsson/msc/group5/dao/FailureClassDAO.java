package com.ericsson.msc.group5.dao;

import java.util.Collection;
import com.ericsson.msc.group5.entities.FailureClass;

/**
 * A Data Access Object interface for the FailureClass entity. Defines common DAO methods.
 */
public interface FailureClassDAO {

	/**
	 * Retrieve all FailureClass objects present in the data store.
	 * 
	 * @return a Collection of FailureClass objects; empty collection if no FailureClass objects are present in the data store.
	 */
	public Collection <FailureClass> getAllFailureClasses();

	/**
	 * Retrieve the FailureClass associated with the unique id passed in as a parameter.
	 * 
	 * @param failureClassId
	 *            a unique id of the FailureClass to be retrieved.
	 * @return FailureClass with the provided failureClassId iff present in the data store; otherwise null.
	 */
	public FailureClass getFailureClass(int failureClassId);

	/**
	 * Insert a new FailureClass object into the data store.
	 * 
	 * @param failureClass
	 *            A new FailureClass object.
	 */
	public void insertFailureClass(FailureClass failureClass);

	/**
	 * Update a FailureClass object that exists in the data store. Object comparison is based on the unique failureClassId.
	 * 
	 * @param failureClass
	 *            A FailureClass entity that exists in the data store. An exception will be thrown the provided FailureClass is not in the data store.
	 */
	public void updateFailureClass(FailureClass failureClass);

	/**
	 * Delete an existing FailureClass object from the data store. Object comparison is based on the unique failureClassId.
	 * 
	 * @param failureClass
	 *            A FailureClass entity that exists in the data store. An exception will be thrown the provided FailureClass is not in the data store.
	 */
	public void deleteFailureClass(FailureClass failureClass);

	/**
	 * Batch insert a collection of FailureClass objects into the data store. Optimized for handling large volumes of data.
	 * 
	 * @param failureClassList
	 *            A collection of new FailureClass objects.
	 */
	public void batchInsertFailureClasses(Collection <FailureClass> failureClassList);
}
