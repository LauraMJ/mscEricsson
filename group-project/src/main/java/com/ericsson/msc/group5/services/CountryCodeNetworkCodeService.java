package com.ericsson.msc.group5.services;

import java.util.Collection;
import javax.ejb.Local;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;

@Local
public interface CountryCodeNetworkCodeService {

	public Collection <CountryCodeNetworkCode> getCountryCodeNetworkCode();
	
	public void addCountryCodeNetworkCode(Collection <CountryCodeNetworkCode> countryNetworkCodes);
}
