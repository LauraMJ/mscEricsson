package com.ericsson.msc.group5.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class mappingTest {

	public static void main(String [] args) {
		AccessCapability ac = new AccessCapability();
		ac.setAccessCapability("TEST ACCESS CAPABILITY");
		ac.setAccessCapabilityId(0);

		UEType ue = new UEType();
		ue.setUeType("TEST UE TYPE");
		ue.setUeTypeId(0);

		OS os = new OS();
		os.setOs("TEST OS");
		os.setOsId(0);

		InputMode input = new InputMode();
		input.setInputMode(null);
		input.setInputModeId(0);

		UserEquipment userEquip = new UserEquipment(123, "market", "manufacturerName", ac, "model1", ue, os, input);
		/*
		 * userEquip.setAccessCapabilityClass(ac); userEquip.setUeType(ue); userEquip.setoS(os); userEquip.setInputModeClass(input);
		 */

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("callFailureLogs");

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction entityTransaction = null;

		try {
			System.out.println("hello");
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			System.out.println("hello1");
			entityManager.persist(ac);
			System.out.println("hello2");
			entityManager.persist(ue);
			System.out.println("hello3");
			entityManager.persist(input);
			System.out.println("hello4");
			entityManager.persist(os);
			System.out.println("hello5");
			entityManager.persist(userEquip);
			System.out.println("hello6");

			entityTransaction.commit();
		}
		catch (RuntimeException e) {
			if (entityTransaction.isActive())
				entityTransaction.rollback();
			throw e;
		}
	}

}
