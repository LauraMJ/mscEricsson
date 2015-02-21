package com.ericsson.msc.group5.dao.jpa;

import com.ericsson.msc.group5.entities.AccessCapability;
import com.ericsson.msc.group5.entities.Country;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCodeCK;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.EventCauseCK;
import com.ericsson.msc.group5.entities.FailureClass;
import com.ericsson.msc.group5.entities.InputMode;
import com.ericsson.msc.group5.entities.OS;
import com.ericsson.msc.group5.entities.UserEquipmentType;

public class PersistenceTest {

	// Variables for laura's tests
	EventCauseCK eventCauseCK = new EventCauseCK(4102, 203);
	CountryCodeNetworkCodeCK mccmnc = new CountryCodeNetworkCodeCK(new Country(123, "Denmark"), 456);

	public static void main(String [] args) {
		new PersistenceTest();
	}

	public PersistenceTest() {
		PersistenceUtil.persist(new AccessCapability(1919191, "TEST"));
		System.out.println("AccessCapability entity saved");
		PersistenceUtil.persist(new FailureClass(4, "hello world"));
		System.out.println("FailureClass entity saved");

		// Siobhan's tests
		// PersistenceUtil.persist(new FailureTrace());
		// System.out.println("Entity saved");
		// PersistenceUtil.persist(new HierInfo());
		// System.out.println("Entity saved");
		PersistenceUtil.persist(new InputMode(1, "BASIC"));
		System.out.println("InputMode entity saved");
		PersistenceUtil.persist(new OS(2, "BLACKBERRY"));
		System.out.println("OS entity saved");
		PersistenceUtil.persist(new UserEquipmentType(3, "HANDHELD"));
		System.out.println("UserEquipmentType entity saved");

		// laura's tests
		PersistenceUtil.persist(new Country(340, "Denmark"));
		System.out.println("Country entity saved");
		PersistenceUtil.persist(new EventCause(eventCauseCK, "this is a description of the event"));
		System.out.println("EventCause entity saved");
		PersistenceUtil.persist(new CountryCodeNetworkCode(mccmnc, "some operator name"));
		System.out.println("CountryCodeNetworkCode entity saved");
		// PersistenceUtil.persist(new UserEquipment(123245,
		// "TestMarketingName", "GSM 1800", "BrickPhone1.0", null, null, null));
		// System.out.println("UserEquipment entity saved");
	}
}
