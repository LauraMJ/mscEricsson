package com.ericsson.msc.group5.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.io.File;
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
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class EventCauseTest {

	@Deployment(testable = true)
	public static Archive <?> createDeployment() {
		PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeAndTestDependencies();
		File [] libraries = pom.resolve("org.apache.poi:poi").withTransitivity().asFile();

		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(true, "com.ericsson")
				.addAsLibraries(libraries)
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	private static String INITIAL_DESCRIPTION = "RRC CONN SETUP-UE BEARERS REJECTED DUE TO ARP ADM REJ AND LICENSES MISSING";
	private static String UPDATED_DESCRIPTION = "UE CTXT RELEASE-UNKNOWN OR ALREADY ALLOCATED ENB UE S1AP ID";

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
		EventCauseCK pk = new EventCauseCK(1, 1);
		EventCause createdEC = new EventCause(pk, INITIAL_DESCRIPTION);
		em.persist(createdEC);

		EventCause loadedEC = em.find(EventCause.class, pk);
		assertEquals("Failed to insert", INITIAL_DESCRIPTION, loadedEC.getDescription());

		loadedEC.setDescription(UPDATED_DESCRIPTION);
		EventCause updatedEC = em.find(EventCause.class, pk);

		assertEquals("Failed to update", UPDATED_DESCRIPTION, updatedEC.getDescription());

		em.remove(updatedEC);
		EventCause shouldBeNull = em.find(EventCause.class, pk);
		assertNull("Failed to delete", shouldBeNull);
	}

	@Test
	public void compositeKeyTest() {
		int oldCauseCode = 21;
		int oldEventId = 45;
		Integer newCauseCode = 5000;
		Integer newEventId = 241;
		EventCauseCK ck = new EventCauseCK(oldCauseCode, oldEventId);
		ck.setCauseCode(newCauseCode);
		ck.setEventId(newEventId);
		assertEquals("failed to set cause code", newCauseCode, ck.getCauseCode());
		assertEquals("failed to set event id", newEventId, ck.getEventId());
		EventCauseCK ckCopy = new EventCauseCK(newCauseCode, newEventId);
		assertEquals("the two objects should be equal since they have the same state", ck, ckCopy);
	}

	private void clearData() throws Exception {
		utx.begin();
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery("delete from com.ericsson.msc.group5.entities.EventCause").executeUpdate();
		utx.commit();
	}

	private void startTransaction() throws Exception {
		utx.begin();
		em.joinTransaction();
	}
}
