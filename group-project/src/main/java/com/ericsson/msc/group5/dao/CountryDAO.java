package com.ericsson.msc.group5.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.ericsson.msc.group5.entities.Country;

@Local
public interface CountryDAO {

	public Country getManagedCountry(int countryCode, String country);
	
	public Collection <Country> getAllCountries();
	
	public void batchInsertCountries(Collection <Country> countryList);
}
