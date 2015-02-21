package com.ericsson.msc.group5.dataAccessLayer;

import com.ericsson.msc.group5.entities.Country;

public interface CountryDAO {

	public Country getManagedCountry(String country);
}
