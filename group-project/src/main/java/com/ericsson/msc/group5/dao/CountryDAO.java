package com.ericsson.msc.group5.dao;

import javax.ejb.Local;
import com.ericsson.msc.group5.entities.Country;

@Local
public interface CountryDAO {

	public Country getManagedCountry(int countryCode, String country);
}
