package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dataAccessLayer.OperatingSystemDAO;
import com.ericsson.msc.group5.entities.OperatingSystem;

@Local
@Stateless
public class JPAOperatingSystemDAO implements OperatingSystemDAO {

	@PersistenceContext
	private EntityManager em;

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
			OperatingSystem newOS = new OperatingSystem();
			newOS.setOperatingSystem(operatingSystem);
			PersistenceUtil.persist(newOS);
			em.close();
			return newOS;
		}
		em.close();
		return operatingSystemList.get(0);
	}
}
