package com.ericsson.group5.services.ejb;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.dao.FailureClassDAO;
import com.ericsson.msc.group5.dao.jpa.JPAFailureClassDAO;
import com.ericsson.msc.group5.entities.FailureClass;
import com.ericsson.msc.group5.services.FailureClassService;
import com.ericsson.msc.group5.services.ejb.FailureClassServiceEJB;

@RunWith(Arquillian.class)
public class FailuteClassServiceEJBTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackage(FailureClass.class.getPackage())
				.addClasses(FailureClass.class, FailureClassServiceEJB.class, FailureClassService.class, FailureClassDAO.class, JPAFailureClassDAO.class)
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	FailureClassServiceEJB failureClassServiceEJB;

	@Test
	public void addFailureClassesTest() {
		FailureClass [] failureClassArray = {new FailureClass(1, "first"), new FailureClass(2, "second"), new FailureClass(3, "third")};

		Collection <FailureClass> failureClasses = new ArrayList <>();
		for (FailureClass f : failureClassArray) {
			failureClasses.add(f);
		}

		failureClassServiceEJB.addFailureClasses(failureClasses);

		for (FailureClass f : failureClassArray) {
			assertTrue(failureClassServiceEJB.getFailureClasses().contains(f));
		}
	}
}
