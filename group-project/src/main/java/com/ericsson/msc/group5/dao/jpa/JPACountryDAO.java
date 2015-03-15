package com.ericsson.msc.group5.dao.jpa;

import java.util.Collection;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.CountryDAO;
import com.ericsson.msc.group5.entities.Country;

@Local
@Stateless
public class JPACountryDAO implements CountryDAO {

	@PersistenceContext
	private EntityManager em;

	public Country getManagedCountry(int countryCode, String country) {
		return em.find(Country.class, new Country(countryCode, country));
	}

	@Override
	public Collection <Country> getAllCountries() {
		return em.createNamedQuery("findAllCountries").getResultList();
	}

	@Override
	public void batchInsertCountries(Collection <Country> countryList) {
		for (Country country : countryList)
			em.persist(country);
	}
}
