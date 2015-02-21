package com.ericsson.msc.group5.dataAccessLayer;

import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;

public interface CountryCodeNetworkCodeDAO {

	public CountryCodeNetworkCode getManagedCountryCodeNetworkCode(
			int countryCode, int networkCode, String country, String operator);
}
