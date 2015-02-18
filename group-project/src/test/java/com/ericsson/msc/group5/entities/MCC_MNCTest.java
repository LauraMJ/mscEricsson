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
public class MCC_MNCTest {

	@Deployment
	public static Archive <?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(MCC_MNC.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

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
		MCC_MNCCK pk = new MCC_MNCCK(1, 1);
		MCC_MNC createdMCC = new MCC_MNC(pk, INITIAL_OPERATOR);
		createdMCC.setCountry(new Country(1, "Denmark"));
		em.persist(createdMCC);

		MCC_MNC loadedMCC = em.find(MCC_MNC.class, pk);
		assertEquals("Failed to insert", INITIAL_OPERATOR, loadedMCC.getOperator());

		loadedMCC.setOperator(UPDATED_OPERATOR);
		MCC_MNC updatedMCC = em.find(MCC_MNC.class, pk);

		assertEquals("Failed to update", UPDATED_OPERATOR, updatedMCC.getOperator());

		em.remove(updatedMCC);
		MCC_MNC shouldBeNull = em.find(MCC_MNC.class, pk);
		assertNull("Failed to delete", shouldBeNull);
	}

	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery("delete from com.ericsson.msc.group5.entities.MCC_MNC").executeUpdate();
		utx.commit();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
}
