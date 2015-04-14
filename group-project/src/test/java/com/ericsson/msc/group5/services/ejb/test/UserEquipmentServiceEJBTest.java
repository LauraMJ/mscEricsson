package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.entities.UserEquipment;
import com.ericsson.msc.group5.services.UserEquipmentService;

@RunWith(Arquillian.class)
@Transactional
public class UserEquipmentServiceEJBTest {

	@EJB
	private UserEquipmentService service;

	@Test
	@Transactional(TransactionMode.ROLLBACK)
	public void addUserEquipmentTest() {
		UserEquipment [] userEquipmentArray = {
				new UserEquipment(1, "one", "one", "one", "one", "one", "one", "one", "one"),
				new UserEquipment(2, "two", "two", "two", "two", "two", "two", "two", "two")};

		Collection <UserEquipment> userEquipments = new ArrayList <>();
		for (UserEquipment ue : userEquipmentArray) {
			userEquipments.add(ue);
		}

		service.addUserEquipments(userEquipments);

		Collection <UserEquipment> retrievedUserEquipment = service.getAllUserEquipments();

		for (UserEquipment ue : retrievedUserEquipment) {
			assertTrue("An object failed to be retrieved", userEquipments.contains(ue));
			assertTrue(ue.equals(ue));
			assertTrue(ue.hashCode() == ue.hashCode());
		}
	}

}
