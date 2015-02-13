package com.ericsson.msc.group5.dao.jpa;

import com.ericsson.msc.group5.entities.AccessCapability;
import com.ericsson.msc.group5.entities.Country;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.EventCauseCK;
import com.ericsson.msc.group5.entities.FailureClass;
import com.ericsson.msc.group5.entities.MCC_MNC;
import com.ericsson.msc.group5.entities.MCC_MNCCK;

public class PersistenceTest {
	//Variables for laura's tests
	EventCauseCK eventCauseCK = new EventCauseCK(4102, 203);
	MCC_MNCCK mccmnc = new MCC_MNCCK(123, 456);
	

	public static void main(String[] args) {
		new PersistenceTest();
	}

	public PersistenceTest() {
		PersistenceUtil.persist(new AccessCapability("TEST"));
		System.out.println("Entity saved");
		//PersistenceUtil.persist(new CellInfo(25, 12, "213421", "2341325", "TEST"));
		System.out.println("Entity saved");
		PersistenceUtil.persist(new FailureClass(4, "hello world"));
		System.out.println("Entity saved");
		
		
		//laura's tests
		PersistenceUtil.persist(new Country(340, "Denmark"));
		System.out.println("Entity saved");
		PersistenceUtil.persist(new EventCause(eventCauseCK, "this is a description of the event"));
		System.out.println("Entity saved");
		PersistenceUtil.persist(new MCC_MNC(mccmnc, "some operator name"));
		System.out.println("Entity saved");
		
	}
}
