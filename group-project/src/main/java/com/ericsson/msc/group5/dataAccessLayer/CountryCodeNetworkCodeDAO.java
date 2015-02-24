package com.ericsson.msc.group5.dataAccessLayer;

import javax.ejb.Local;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;

@Local
public interface CountryCodeNetworkCodeDAO {

	public CountryCodeNetworkCode getManagedCountryCodeNetworkCode(
			int countryCode, int networkCode, String country, String operator);
}
