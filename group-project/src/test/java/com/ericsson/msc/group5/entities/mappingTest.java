//package com.ericsson.msc.group5.entities;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
//public class mappingTest {
//
//	public static void main(String [] args) {
//		HierInfo hi = new HierInfo();
//		hi.setHier3Id("327093718947");
//		hi.setHier32Id("27454325151");
//		hi.setHier321Id("35279815702");
//		FailureClass fc = new FailureClass(0, "description");
//		EventCause ec = new EventCause(new EventCauseCK(0, 0), "description");
//		CountryCodeNetworkCode mcc = new CountryCodeNetworkCode(
//				new CountryCodeNetworkCodeCK(new Country(0, "country"), 0),
//				"operator string");
//
//		AccessCapability ac = new AccessCapability();
//		ac.setAccessCapability("TEST ACCESS CAPABILITY");
//
//		UserEquipmentType ue = new UserEquipmentType();
//		ue.setUserEquipmentType("TEST UE TYPE");
//
//		OperatingSystem os = new OperatingSystem();
//		os.setOperatingSystem("TEST OS");
//
//		InputMode input = new InputMode();
//		input.setInputMode(null);
//
//		UserEquipment userEquip = new UserEquipment(123, "market",
//				"manufacturerName", ac, "model1", ue, os, input);
//		/*
//		 * userEquip.setAccessCapabilityClass(ac); userEquip.setUeType(ue);
//		 * userEquip.setoS(os); userEquip.setInputModeClass(input);
//		 */
//
//		// other stuff
//		FailureTrace ft = new FailureTrace();
//		ft.setEventCause(ec);
//		ft.setCellId(0);
//		ft.setDateTime("11/01/2013 17:15:00");
//		ft.setDuration(1000);
//		ft.setFailureClass(fc);
//		ft.setHierInfoId(hi);
//		ft.setIMSI("2315312");
//		ft.setCountryCodeNetworkCode(mcc);
//		ft.setNeVersion("B12");
//		ft.setUserEqipment(userEquip);
//
//		EntityManagerFactory entityManagerFactory = Persistence
//				.createEntityManagerFactory("callFailureLogs");
//
//		EntityManager entityManager = entityManagerFactory
//				.createEntityManager();
//
//		EntityTransaction entityTransaction = null;
//
//		try {
//			entityTransaction = entityManager.getTransaction();
//			entityTransaction.begin();
//			entityManager.persist(ac);
//			entityManager.persist(ue);
//			entityManager.persist(input);
//			entityManager.persist(os);
//			entityManager.persist(userEquip);
//
//			// new stuff
//			entityManager.persist(ft);
//
//			entityTransaction.commit();
//		}
//		catch (RuntimeException e) {
//			if (entityTransaction.isActive())
//				entityTransaction.rollback();
//			throw e;
//		}
//		System.out.println("Done");
//	}
//
//}
