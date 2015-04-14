package com.ericsson.msc.group5.services.ejb;

import java.util.Collection;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.jpa.JPACountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;
import com.ericsson.msc.group5.services.CountryCodeNetworkCodeService;

@Stateless
@Local
public class CountryCodeNetworkCodeServiceEJB implements CountryCodeNetworkCodeService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private JPACountryCodeNetworkCodeDAO dao;

	@Override
	public Collection <CountryCodeNetworkCode> getCountryCodeNetworkCodes() {
		return dao.getAllCountryCodeNetworkCodes();
	}

	@Override
	public void addCountryCodeNetworkCodes(Collection <CountryCodeNetworkCode> countryNetworkCodes) {
		dao.batchInsertCountryCodeNetworkCode(countryNetworkCodes);

	}

	@Override
	public void addCountryCodeNetworkCode(CountryCodeNetworkCode countryCodeNetworkCode) {
		dao.insertCountryCodeNetworkCode(countryCodeNetworkCode);

	}

}
