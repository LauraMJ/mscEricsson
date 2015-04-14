package com.ericsson.msc.group5.dao.jpa;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.msc.group5.dao.CountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.dao.CountryDAO;
import com.ericsson.msc.group5.entities.Country;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCodeCK;

@RunWith(Arquillian.class)
public class JPACountryCodeNetworkCodeDAOTest {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private CountryCodeNetworkCodeDAO countryDAO;

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
		Country country = new Country(1, "country");
		CountryCodeNetworkCodeCK ck = new CountryCodeNetworkCodeCK(country, 1);
		CountryCodeNetworkCode ccncck = new CountryCodeNetworkCode(ck, "one");
		countryDAO.insertCountryCodeNetworkCode(ccncck);
		assertNotNull(countryDAO.getAllCountryCodeNetworkCodes());
		assertTrue(countryDAO.getAllCountryCodeNetworkCodes().size() == 1);
		
	}
	
	@Test
	public void testGetAllCountryCodeNetworkCodes(){
		Country country = new Country(1, "country");
		CountryCodeNetworkCodeCK ck = new CountryCodeNetworkCodeCK(country, 1);
		CountryCodeNetworkCode ccncck = new CountryCodeNetworkCode(ck, "one");

		Country countryTwo = new Country(2, "countryTwo");
		CountryCodeNetworkCodeCK ckTwo = new CountryCodeNetworkCodeCK(countryTwo, 2);
		CountryCodeNetworkCode ccncckTwo = new CountryCodeNetworkCode(ckTwo, "two");

		countryDAO.insertCountryCodeNetworkCode(ccncck);
		countryDAO.insertCountryCodeNetworkCode(ccncckTwo);
		
		assertTrue(countryDAO.getAllCountryCodeNetworkCodes().contains(ccncck));
		assertTrue(countryDAO.getAllCountryCodeNetworkCodes().contains(ccncckTwo));
	}
	
	@Test 
	public void testGetCountryCodeNetworkCode(){
		
	}
	
	@Test
	public void testBatchInsertCountryCodeNetworkCode(){
		
	}
	
	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery("delete from com.ericsson.msc.group5.entities.CountryCodeNetworkCode").executeUpdate();
		utx.commit();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
	
}
