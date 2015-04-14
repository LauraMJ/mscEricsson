package com.ericsson.msc.group5.dao.jpa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.msc.group5.dao.CountryDAO;
import com.ericsson.msc.group5.entities.Country;

@RunWith(Arquillian.class)
public class JPACountryDAOTest {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private CountryDAO countryDAO;

	@Inject
	private UserTransaction utx;
	
	@Before
	public void preparePersistenceTest() throws Exception {
		clearData();
		startTransaction();
	}
	
	@After
	public void commitTransaction() throws Exception {
		utx.commit();
	}
	
	@Test
	public void testInsertion(){
		Country c = new Country(12, "Japan");
		countryDAO.insertCountry(c);
		assertNotNull(countryDAO.getAllCountries());
		assertTrue(countryDAO.getAllCountries().size() == 1);
	}
	
	@Test
	public void testGetAllCountries(){
		Country c = new Country(12, "Japan");
		Country c2 = new Country(21, "Kenya");
		countryDAO.insertCountry(c);
		countryDAO.insertCountry(c2);
		assertTrue(countryDAO.getAllCountries().size() == 2);
	}
	
	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery("delete from com.ericsson.msc.group5.entities.Country").executeUpdate();
		utx.commit();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
}
