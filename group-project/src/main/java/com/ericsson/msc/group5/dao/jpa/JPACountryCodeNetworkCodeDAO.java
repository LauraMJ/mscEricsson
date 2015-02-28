package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.CountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.entities.Country;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;

public class JPACountryCodeNetworkCodeDAO implements CountryCodeNetworkCodeDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List <CountryCodeNetworkCode> getAllCountryCodeNetworkCodes() {
		return em.createNamedQuery("findAllCountryCodeNetworkCodes").getResultList();
	}

	@Override
	public CountryCodeNetworkCode getCountryCodeNetworkCode(int networkCode, int countryCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertCountryCodeNetworkCode(CountryCodeNetworkCode countryCodeNetworkCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCountryCodeNetworkCode(CountryCodeNetworkCode countryCodeNetworkCode) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteCountryCodeNetworkCode(CountryCodeNetworkCode countryCodeNetworkCode) {
		// TODO Auto-generated method stub
	}

	@Override
	public void batchInsertCountryCodeNetworkCode(List <CountryCodeNetworkCode> countryCodeNetworkCodeList) {
		// TODO Auto-generated method stub
	}

	@Override
	public void insertCountry(Country country) {
		// TODO Auto-generated method stub
	}

	@Override
	public Country getCountry(int countryCode) {
		// TODO Auto-generated method stub
		return null;
	}
}
