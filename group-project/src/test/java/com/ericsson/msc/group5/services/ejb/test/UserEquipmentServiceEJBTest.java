package com.ericsson.msc.group5.services.ejb.test;

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
import com.ericsson.msc.group5.dao.UserEquipmentDAO;
import com.ericsson.msc.group5.dao.jpa.JPAUserEquipmentDAO;
import com.ericsson.msc.group5.entities.UserEquipment;
import com.ericsson.msc.group5.services.UserEquipmentService;
import com.ericsson.msc.group5.services.ejb.UserEquipmentServiceEJB;

@RunWith(Arquillian.class)
public class UserEquipmentServiceEJBTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackage(UserEquipment.class.getPackage())
				.addClasses(UserEquipment.class, UserEquipmentServiceEJB.class, UserEquipmentService.class, UserEquipmentDAO.class, JPAUserEquipmentDAO.class)
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	UserEquipmentService service;

	@Test
	public void addUserEquipmentTest() {
		UserEquipment [] userEquipmentArray = {
				new UserEquipment(1, "one", "one", "one", "one", "one", "one", "one", "one"),
				new UserEquipment(2, "two", "two", "two", "two", "two", "two", "two", "two")};

		Collection <UserEquipment> userEquipments = new ArrayList <>();
		for (UserEquipment ue : userEquipmentArray) {
			userEquipments.add(ue);
		}

		service.addUserEquipment(userEquipments);

		Collection <UserEquipment> retrievedUserEquipment = service.getUserEquipment();

		for (UserEquipment ue : retrievedUserEquipment) {
			assertTrue("An object failed to be retrieved", userEquipments.contains(ue));
		}
	}

}
