package com.ericsson.msc.group5.dao.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.ericsson.msc.group5.dataIO.BaseData;
import com.ericsson.msc.group5.dataIO.EventCauseData;
import com.ericsson.msc.group5.dataIO.FailureClassData;
import com.ericsson.msc.group5.dataIO.MccMncData;
import com.ericsson.msc.group5.dataIO.UETableData;

public class PersistenceUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("callFailureLogs");

	public static void persist(Object entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public static void persistBaseData(ArrayList <BaseData> list) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		for (BaseData row : list) {
			entityManager.persist(row);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	public static void persistUEType(ArrayList <UETableData> list) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		for (UETableData row : list) {
			entityManager.persist(row);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	public static void persistFailureClass(ArrayList <FailureClassData> list) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		for (FailureClassData row : list) {
			entityManager.persist(row);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	public static void persistEventCause(ArrayList <EventCauseData> list) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		for (EventCauseData row : list) {
			entityManager.persist(row);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	public static void persistMccMnc(ArrayList <MccMncData> list) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		for (MccMncData row : list) {
			entityManager.persist(row);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	public static void remove(Object entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Object mergedEntity = entityManager.merge(entity);
		entityManager.remove(mergedEntity);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public static Object merge(Object entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entity = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
		return entity;
	}

	public static EntityManager createEM() {
		return entityManagerFactory.createEntityManager();
	}
}