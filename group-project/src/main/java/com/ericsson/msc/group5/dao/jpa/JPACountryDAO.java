package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
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
		EntityManager em = PersistenceUtil.createEM();
		List <Country> cList = em
				.createQuery(
						"select c from " + Country.class.getName()
								+ " c where c.countryCode = :countryCode",
						Country.class).setParameter("countryCode", countryCode)
				.getResultList();
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
