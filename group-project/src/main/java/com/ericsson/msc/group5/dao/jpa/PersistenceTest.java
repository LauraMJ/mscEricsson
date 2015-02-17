package com.ericsson.msc.group5.dao.jpa;

import com.ericsson.msc.group5.entities.AccessCapability;
import com.ericsson.msc.group5.entities.Country;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.EventCauseCK;
import com.ericsson.msc.group5.entities.FailureClass;
import com.ericsson.msc.group5.entities.InputMode;
import com.ericsson.msc.group5.entities.MCC_MNC;
import com.ericsson.msc.group5.entities.MCC_MNCCK;
import com.ericsson.msc.group5.entities.OS;
import com.ericsson.msc.group5.entities.UEType;
import com.ericsson.msc.group5.entities.UserEquipment;

public class PersistenceTest {

	// Variables for laura's tests
	EventCauseCK eventCauseCK = new EventCauseCK(4102, 203);
	MCC_MNCCK mccmnc = new MCC_MNCCK(123, 456);

	public static void main(String [] args) {
		new PersistenceTest();
	}

	public PersistenceTest() {
		PersistenceUtil.persist(new AccessCapability("TEST"));
		System.out.println("Entity saved");
		PersistenceUtil.persist(new FailureClass(4, "hello world"));
		System.out.println("Entity saved");

		// Siobhan's tests
		// PersistenceUtil.persist(new FailureTrace());
		// System.out.println("Entity saved");
		// PersistenceUtil.persist(new HierInfo());
		// System.out.println("Entity saved");
		PersistenceUtil.persist(new InputMode(1, "BASIC"));
		System.out.println("Entity saved");
		PersistenceUtil.persist(new OS(2, "BLACKBERRY"));
		System.out.println("Entity saved");
		PersistenceUtil.persist(new UEType(3, "HANDHELD"));
		System.out.println("Entity saved");

		// laura's tests
		PersistenceUtil.persist(new Country(340, "Denmark"));
		System.out.println("Entity saved");
		PersistenceUtil.persist(new EventCause(eventCauseCK,
				"this is a description of the event"));
		System.out.println("Entity saved");
		PersistenceUtil.persist(new MCC_MNC(mccmnc, "some operator name"));
		System.out.println("Entity saved");
		PersistenceUtil.persist(new UserEquipment(123245, "TestMarketingName",
				"GSM 1800", "BrickPhone1.0", null, null, null));
		System.out.println("Entity saved");

	}
}
