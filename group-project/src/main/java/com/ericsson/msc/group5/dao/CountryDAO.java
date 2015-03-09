package com.ericsson.msc.group5.dao;

import java.util.Collection;
import com.ericsson.msc.group5.entities.Country;

/**
 * A Data Access Object interface for the Country entity. Defines common DAO methods.
 */

public interface CountryDAO {

	public Country getManagedCountry(int countryCode, String country);
	
	/**
	 * Retrieve all Country objects present in the data store.
	 * 
	 * @return a Collection of Country objects; empty collection if no Country objects are present in the data store.
	 */
	public Collection <Country> getAllCountries();
	
	/**
	 * Batch insert a collection of Country objects into the data store. Optimized for handling large volumes of data.
	 * 
	 * @param countryCodeNetworkCodeList
	 *            A collection of new Country objects.
	 */
	public void batchInsertCountries(Collection <Country> countryList);
}
