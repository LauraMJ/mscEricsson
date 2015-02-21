package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import com.ericsson.msc.group5.dataAccessLayer.AccessCapabilityDAO;
import com.ericsson.msc.group5.entities.AccessCapability;

public class JPAAccessCapabilityDAO implements AccessCapabilityDAO {

	public AccessCapability getManagedAccessCapability(String accessCapability) {
		EntityManager em = PersistenceUtil.createEM();
		List <AccessCapability> acList = em
				.createQuery(
						"select ac from " + AccessCapability.class.getName()
								+ " ac where ac.accessCapability = :access",
						AccessCapability.class)
				.setParameter("access", accessCapability).getResultList();
		if (acList.isEmpty()) {
			System.out.println("ac not found");
			AccessCapability ac = new AccessCapability();
			ac.setAccessCapability(accessCapability);
			PersistenceUtil.persist(ac);
			em.close();
			return ac;
		}
		System.out.println("ac found");
		em.close();
		return acList.get(0);
	}

}
