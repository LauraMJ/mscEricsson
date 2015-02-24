package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.ericsson.msc.group5.dataAccessLayer.CountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.dataAccessLayer.CountryDAO;
import com.ericsson.msc.group5.entities.Country;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCodeCK;

public class JPACountryCodeNetworkCodeDAO implements CountryCodeNetworkCodeDAO {

	@Inject
	private CountryDAO countryDAO;

	public CountryDAO getCountryDAO() {
		return countryDAO;
	}

	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	public CountryCodeNetworkCode getManagedCountryCodeNetworkCode(int countryCode, int networkCode, String country, String operator) {
		EntityManager em = PersistenceUtil.createEM();
		List <CountryCodeNetworkCode> cnList = em
				.createQuery(
						"select cn from "
								+ CountryCodeNetworkCode.class.getName()
								+ " cn where cn.countryCodeNetworkCode.country.countryCode = :countryCode AND cn.countryCodeNetworkCode.networkCode = :networkCode",
						CountryCodeNetworkCode.class).setParameter("countryCode", countryCode).setParameter("networkCode", networkCode).getResultList();
		if (cnList.isEmpty()) {
			Country countryEntity = countryDAO.getManagedCountry(countryCode, country);
			CountryCodeNetworkCode cn = new CountryCodeNetworkCode(new CountryCodeNetworkCodeCK(countryEntity, networkCode), operator);

			PersistenceUtil.persist(cn);
			em.close();
			return cn;
		}
		em.close();
		return cnList.get(0);
	}
}
