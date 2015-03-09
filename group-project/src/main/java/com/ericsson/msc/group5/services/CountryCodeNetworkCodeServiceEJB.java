package com.ericsson.msc.group5.services;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ericsson.msc.group5.dao.jpa.JPACountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;

@Stateless
@Local
public class CountryCodeNetworkCodeServiceEJB implements CountryCodeNetworkCodeService{

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private JPACountryCodeNetworkCodeDAO dao;
	
	@Override
	public Collection<CountryCodeNetworkCode> getCountryCodeNetworkCode() {
		return dao.getAllCountryCodeNetworkCodes();
	}

	@Override
	public void addCountryCodeNetworkCode(
			Collection<CountryCodeNetworkCode> countryNetworkCodes) {
		dao.batchInsertCountryCodeNetworkCode(countryNetworkCodes);
		
	}

}
