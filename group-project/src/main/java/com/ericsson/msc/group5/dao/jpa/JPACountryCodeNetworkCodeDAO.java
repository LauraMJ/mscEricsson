package com.ericsson.msc.group5.dao.jpa;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.CountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.entities.Country;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCodeCK;

public class JPACountryCodeNetworkCodeDAO implements CountryCodeNetworkCodeDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Collection <CountryCodeNetworkCode> getAllCountryCodeNetworkCodes() {
		return em.createNamedQuery("findAllCountryCodeNetworkCodes").getResultList();
	}

	@Override
	public CountryCodeNetworkCode getCountryCodeNetworkCode(int networkCode, int countryCode) {
		Country country = new Country();
		country.setCountryCode(countryCode);
		return em.find(CountryCodeNetworkCode.class, new CountryCodeNetworkCodeCK(country, networkCode));
	}

	@Override
	public void insertCountryCodeNetworkCode(CountryCodeNetworkCode countryCodeNetworkCode) {
		Country country = countryCodeNetworkCode.getCountryCodeNetworkCode().getCountry();
		if (em.find(Country.class, country.getCountryCode()) == null)
			em.persist(country);
		em.persist(countryCodeNetworkCode);
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
	public void batchInsertCountryCodeNetworkCode(Collection <CountryCodeNetworkCode> countryCodeNetworkCodeList) {
		for (CountryCodeNetworkCode countryCodeNetworkCode : countryCodeNetworkCodeList) {
			Country country = countryCodeNetworkCode.getCountryCodeNetworkCode().getCountry();
			if (em.find(Country.class, country.getCountryCode()) == null)
				em.persist(country);
			em.persist(countryCodeNetworkCode);
		}
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
