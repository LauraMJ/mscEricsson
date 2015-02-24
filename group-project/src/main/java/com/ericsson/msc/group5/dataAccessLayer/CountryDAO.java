package com.ericsson.msc.group5.dataAccessLayer;

import javax.ejb.Local;
import com.ericsson.msc.group5.entities.Country;

@Local
public interface CountryDAO {

	public Country getManagedCountry(int countryCode, String country);
}
