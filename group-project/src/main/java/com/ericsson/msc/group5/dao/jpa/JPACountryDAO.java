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

	@Override
	public Collection <Country> getAllCountries() {
		return em.createNamedQuery("findAllCountries").getResultList();
	}

}
