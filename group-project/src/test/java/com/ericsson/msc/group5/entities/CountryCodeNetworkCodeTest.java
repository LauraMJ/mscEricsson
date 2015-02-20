package com.ericsson.msc.group5.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CountryCodeNetworkCodeTest {

	@Deployment
	public static Archive <?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(CountryCodeNetworkCode.class.getPackage())
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	private static String INITIAL_OPERATOR = "Oklahoma Western Telephone Company US";
	private static String UPDATED_OPERATOR = "Clearnet CA";
	private static Country INITIAL_COUNTRY = new Country(555, "Dublin");
	private static Country UPDATED_COUNTRY = new Country(222, "France");

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
		CountryCodeNetworkCodeCK pk = new CountryCodeNetworkCodeCK(1, 1);
		CountryCodeNetworkCode createdCountryCodeNetworkCode = new CountryCodeNetworkCode(
				pk, INITIAL_OPERATOR);
		createdCountryCodeNetworkCode.setCountry(new Country(1, "Denmark"));
		em.persist(createdCountryCodeNetworkCode);

		CountryCodeNetworkCode loadedCountryCodeNetworkCode = em.find(
				CountryCodeNetworkCode.class, pk);
		assertEquals("Failed to insert", INITIAL_OPERATOR,
				loadedCountryCodeNetworkCode.getOperator());

		loadedCountryCodeNetworkCode.setOperator(UPDATED_OPERATOR);
		CountryCodeNetworkCode updatedCountryCodeNetworkCode = em.find(
				CountryCodeNetworkCode.class, pk);

		assertEquals("Failed to update", UPDATED_OPERATOR,
				updatedCountryCodeNetworkCode.getOperator());
		
		loadedCountryCodeNetworkCode.setCountry(UPDATED_COUNTRY);
		assertEquals("Failed to update", UPDATED_COUNTRY, updatedCountryCodeNetworkCode.getCountry());

		em.remove(updatedCountryCodeNetworkCode);
		CountryCodeNetworkCode shouldBeNull = em.find(
				CountryCodeNetworkCode.class, pk);
		assertNull("Failed to delete", shouldBeNull);
	}

	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery(
				"delete from com.ericsson.msc.group5.entities.CountryCodeNetworkCode")
				.executeUpdate();
		utx.commit();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
}
