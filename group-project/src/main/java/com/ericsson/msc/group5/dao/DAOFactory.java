package com.ericsson.msc.group5.dao;

/**
 * Interface DAO factory - based on the factory method pattern. Provides a single interface to the Data Access Layer, encapsulates the specific implementation.
 */
public interface DAOFactory {

	/**
	 * Retrieve a DAO for CountryCodeNetworkCode objects.
	 * 
	 * @return a DAO for CountryCodeNetworkCode objects
	 */
	public CountryCodeNetworkCodeDAO getCountryCodeNetworkCodeDAO();

	/**
	 * Retrieve a DAO for EventCause objects.
	 * 
	 * @returna DAO for EventCause objects
	 */
	public EventCauseDAO getEventCauseDAO();

	/**
	 * Retrieve a DAO for FailureClass objects.
	 * 
	 * @return DAO for FailureClass objects
	 */
	public FailureClassDAO getFailureClassDAO();

	/**
	 * Retrieve a DAO for FailureTrace objects.
	 * 
	 * @return DAO for FailureTrace objects
	 */
	public FailureTraceDAO getFailureTraceDAO();

	/**
	 * Retrieve a DAO for UserEqipment objects.
	 * 
	 * @return DAO for UserEqipment objects
	 */
	public UserEquipmentDAO getUserEqipmentDAO();
}
