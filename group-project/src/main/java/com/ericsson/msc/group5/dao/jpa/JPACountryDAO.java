package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import com.ericsson.msc.group5.dataAccessLayer.CountryDAO;
import com.ericsson.msc.group5.entities.Country;

public class JPACountryDAO implements CountryDAO {

	public Country getManagedCountry(int countryCode, String country) {
		EntityManager em = PersistenceUtil.createEM();
		List <Country> cList = em.createQuery("select c from " + Country.class.getName() + " c where c.countryCode = :countryCode", Country.class)
				.setParameter("countryCode", countryCode).getResultList();
		if (cList.isEmpty()) {
			Country c = new Country();
			c.setCountryCode(countryCode);
			c.setCountry(country);
			PersistenceUtil.persist(c);
			em.close();
			return c;
		}
		em.close();
		return cList.get(0);
	}
}
