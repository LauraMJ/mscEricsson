package com.ericsson.msc.group5.dao;

import java.util.Collection;
import com.ericsson.msc.group5.entities.ErrorLog;

/**
 * A Data Access Object interface for the ErrorLog entity. Defines common DAO methods.
 */
public interface ErrorLogDAO {

	/**
	 * Retrieve all ErrorLog objects present in the data store.
	 * 
	 * @return a Collection of ErrorLog objects; empty collection if no ErrorLog objects are present in the data store.
	 */
	public Collection <ErrorLog> getAllErrorLogs();

	/**
	 * Insert a new ErrorLog object into the data store.
	 * 
	 * @param errorLog
	 *            A new ErrorLog object.
	 */
	public void insertErrorLog(ErrorLog errorLog);

	/**
	 * Batch insert a Collection of ErrorLog objects into the data store. Optimized for handling large volumes of data.
	 * 
	 * @param errorLogList
	 *            A Collection of new ErrorLog objects.
	 */
	public void batchInsertErrorLog(Collection <ErrorLog> errorLogList);

	/**
	 * Delete all ErrorLog objects from the data store.
	 */
	public void deleteErrorLogs();
}
