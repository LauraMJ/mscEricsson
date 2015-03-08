package com.ericsson.msc.group5.services;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ericsson.msc.group5.dao.CountryDAO;
import com.ericsson.msc.group5.entities.Country;
import com.google.inject.Inject;

public class CountryServiceEJB implements CountryService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private CountryDAO dao;

	@Override
	public Collection<Country> getManagedCountry() {
		return dao.getAllCountries();
	}

	@Override
	public void addCountry(Collection<Country> countries) {
		dao.batchInsertCountries(countries);
		
	}

}
