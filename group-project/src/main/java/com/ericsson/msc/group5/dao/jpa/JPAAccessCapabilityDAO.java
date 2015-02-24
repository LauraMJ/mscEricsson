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
			AccessCapability ac = new AccessCapability();
			ac.setAccessCapability(accessCapability);
			PersistenceUtil.persist(ac);
			em.close();
			return ac;
		}
		em.close();
		return acList.get(0);
	}

}
