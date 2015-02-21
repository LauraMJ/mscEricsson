package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import com.ericsson.msc.group5.dataAccessLayer.OperatingSystemDAO;
import com.ericsson.msc.group5.entities.OperatingSystem;

public class JPAOperatingSystemDAO implements OperatingSystemDAO {

	public OperatingSystem getManagedOs(String operatingSystem) {
		EntityManager em = PersistenceUtil.createEM();
		List <OperatingSystem> operatingSystemList = em
				.createQuery(
						"select os from "
								+ OperatingSystem.class.getName()
								+ " os where os.operatingSystem = :operatingsys",
						OperatingSystem.class)
				.setParameter("operatingsys", operatingSystem).getResultList();
		if (operatingSystemList.isEmpty()) {
			System.out.println("os not found");
			OperatingSystem newOS = new OperatingSystem();
			newOS.setOperatingSystem(operatingSystem);
			PersistenceUtil.persist(newOS);
			em.close();
			return newOS;
		}
		System.out.println("os found");
		em.close();
		return operatingSystemList.get(0);
	}
}
