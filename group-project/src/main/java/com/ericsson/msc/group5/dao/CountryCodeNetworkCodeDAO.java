package com.ericsson.msc.group5.dao;

import java.util.List;
import javax.ejb.Local;
import com.ericsson.msc.group5.entities.Country;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;

/**
 * A Data Access Object interface for the CountryCodeNetworkCode entity. Defines common DAO methods.
 */
@Local
public interface CountryCodeNetworkCodeDAO {

	/**
	 * Retrieve all CountryCodeNetworkCode objects present in the data store.
	 * 
	 * @return a List of CountryCodeNetworkCode objects; empty list if no CountryCodeNetworkCode objects are present in the data store.
	 */
	public List <CountryCodeNetworkCode> getAllCountryCodeNetworkCodes();

	/**
	 * Retrieve the CountryCodeNetworkCode associated with the 2-part composite key passed in as a parameter.
	 * 
	 * @param networkCode
	 *            first part of a 2-part composite key that uniquely identifies the CountryCodeNetworkCode to be retrieved.
	 * @param countryCode
	 *            second part of a 2-part composite key that uniquely identifies the CountryCodeNetworkCode to be retrieved.
	 * @return CountryCodeNetworkCode with the provided networkCode+countryCode combination iff present in the data store; otherwise null.
	 */
	public CountryCodeNetworkCode getCountryCodeNetworkCode(int networkCode, int countryCode);

	/**
	 * Insert a new CountryCodeNetworkCode object into the data store.
	 * 
	 * @param countryCodeNetworkCode
	 *            A new CountryCodeNetworkCode object.
	 */
	public void insertCountryCodeNetworkCode(CountryCodeNetworkCode countryCodeNetworkCode);

	/**
	 * Update an CountryCodeNetworkCode object that exists in the data store. Object comparison is based on the composite key networkCode+countryCode
	 * combination.
	 * 
	 * @param countryCodeNetworkCode
	 *            A CountryCodeNetworkCode entity that exists in the data store. An exception will be thrown the provided CountryCodeNetworkCode is not in the
	 *            data store.
	 */
	public void updateCountryCodeNetworkCode(CountryCodeNetworkCode countryCodeNetworkCode);

	/**
	 * Delete an existing CountryCodeNetworkCode object from the data store. Object comparison is based on the composite key networkCode+countryCode
	 * combination.
	 * 
	 * @param countryCodeNetworkCode
	 *            A CountryCodeNetworkCode entity that exists in the data store. An exception will be thrown the provided CountryCodeNetworkCode is not in the
	 *            data store.
	 */
	public void deleteCountryCodeNetworkCode(CountryCodeNetworkCode countryCodeNetworkCode);

	/**
	 * Batch insert a list of CountryCodeNetworkCode objects into the data store. Optimized for handling large volumes of data.
	 * 
	 * @param countryCodeNetworkCodeList
	 *            A list of new CountryCodeNetworkCode objects.
	 */
	public void batchInsertCountryCodeNetworkCode(List <CountryCodeNetworkCode> countryCodeNetworkCodeList);

	/**
	 * Insert a new Country object into the data store.
	 * 
	 * @param country
	 *            A new Country object.
	 */
	public void insertCountry(Country country);

	/**
	 * Retrieve the Country associated with the unique id passed in as a parameter.
	 * 
	 * @param countryCode
	 *            a unique id of the Country to be retrieved.
	 * @return Country with the provided countryCode iff present in the data store; otherwise null.
	 */
	public Country getCountry(int countryCode);
}
