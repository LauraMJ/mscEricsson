package com.ericsson.msc.group5.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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

@RunWith(Arquillian.class)
public class CountryCodeNetworkCodeTest {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	private static String INITIAL_OPERATOR = "Oklahoma Western Telephone Company US";
	private static String UPDATED_OPERATOR = "Clearnet CA";

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
	public void basicCRUDTest() throws Exception {
		Country c = new Country(0, "country");
		em.persist(c);
		CountryCodeNetworkCodeCK pk = new CountryCodeNetworkCodeCK(c, 1);
		CountryCodeNetworkCode createdCountryCodeNetworkCode = new CountryCodeNetworkCode(pk, INITIAL_OPERATOR);
		em.persist(createdCountryCodeNetworkCode);

		CountryCodeNetworkCode loadedCountryCodeNetworkCode = em.find(CountryCodeNetworkCode.class, pk);
		assertEquals("Failed to insert", INITIAL_OPERATOR, loadedCountryCodeNetworkCode.getOperator());

		loadedCountryCodeNetworkCode.setOperator(UPDATED_OPERATOR);
		CountryCodeNetworkCode updatedCountryCodeNetworkCode = em.find(CountryCodeNetworkCode.class, pk);

		assertEquals("Failed to update", UPDATED_OPERATOR, updatedCountryCodeNetworkCode.getOperator());

		em.remove(updatedCountryCodeNetworkCode);
		CountryCodeNetworkCode shouldBeNull = em.find(CountryCodeNetworkCode.class, pk);
		assertNull("Failed to delete", shouldBeNull);
	}

	@Test
	public void compositeKeyTest() {
		int oldCode = 21;
		int newCode = 5000;
		Country newCountry = new Country(newCode, "new country");

		CountryCodeNetworkCodeCK ck = new CountryCodeNetworkCodeCK(new Country(oldCode, "country"), oldCode);
		ck.setCountry(newCountry);
		ck.setNetworkCode(newCode);
		assertEquals("failed to set network code", newCode, (int) ck.getNetworkCode());
		assertEquals("failed to set country", newCountry, ck.getCountry());
	}

	@Test
	public void testGeneratedMethods() {
		Country countryOne = new Country(0, "Test country");

		CountryCodeNetworkCodeCK countryCodeNetworkCodeCKOne = new CountryCodeNetworkCodeCK();
		countryCodeNetworkCodeCKOne.setCountry(countryOne);
		countryCodeNetworkCodeCKOne.setNetworkCode(0);

		CountryCodeNetworkCodeCK countryCodeNetworkCodeCKTwo = new CountryCodeNetworkCodeCK();
		countryCodeNetworkCodeCKTwo.setCountry(countryOne);
		countryCodeNetworkCodeCKTwo.setNetworkCode(1);

		CountryCodeNetworkCode countryCodeNetworkCodeOne = new CountryCodeNetworkCode();
		countryCodeNetworkCodeOne.setCountryCodeNetworkCode(countryCodeNetworkCodeCKOne);
		countryCodeNetworkCodeOne.setOperator(INITIAL_OPERATOR);

		CountryCodeNetworkCode countryCodeNetworkCodeTwo = new CountryCodeNetworkCode();
		countryCodeNetworkCodeTwo.setCountryCodeNetworkCode(countryCodeNetworkCodeCKTwo);
		countryCodeNetworkCodeTwo.setOperator(UPDATED_OPERATOR);

		// Check same objects equal same
		assertTrue(countryCodeNetworkCodeCKOne.equals(countryCodeNetworkCodeCKOne));
		assertFalse(countryCodeNetworkCodeCKOne.equals(countryCodeNetworkCodeCKTwo));
		// Check hash code works correctly
		assertFalse(countryCodeNetworkCodeCKOne.hashCode() == countryCodeNetworkCodeCKTwo.hashCode());

		// Check same objects equal same
		assertTrue(countryCodeNetworkCodeOne.equals(countryCodeNetworkCodeOne));
		assertFalse(countryCodeNetworkCodeOne.equals(countryCodeNetworkCodeTwo));
		// Check hash code works correctly
		assertFalse((countryCodeNetworkCodeOne.hashCode() == countryCodeNetworkCodeTwo.hashCode()));

		// Check .equals for null and different object type
		countryCodeNetworkCodeTwo = null;
		assertFalse(countryCodeNetworkCodeOne.equals(countryCodeNetworkCodeTwo));
		assertFalse(countryCodeNetworkCodeOne.equals(new String()));
		// Check .equals on empty required field
		countryCodeNetworkCodeTwo = new CountryCodeNetworkCode();
		assertFalse(countryCodeNetworkCodeOne.equals(countryCodeNetworkCodeTwo));

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
