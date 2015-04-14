package com.ericsson.msc.group5.services;

import java.util.Collection;
import javax.ejb.Local;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;

/**
 * CountryCodeNetworkCode service EJB interface.
 */
@Local
public interface CountryCodeNetworkCodeService {

	public Collection <CountryCodeNetworkCode> getCountryCodeNetworkCodes();

	public void addCountryCodeNetworkCodes(Collection <CountryCodeNetworkCode> countryNetworkCodes);

	public void addCountryCodeNetworkCode(CountryCodeNetworkCode countryCodeNetworkCode);
}
