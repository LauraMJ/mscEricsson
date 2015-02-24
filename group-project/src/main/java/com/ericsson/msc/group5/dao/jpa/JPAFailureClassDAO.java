package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import org.jboss.arquillian.core.api.annotation.Inject;
import com.ericsson.msc.group5.dataAccessLayer.FailureClassDAO;
import com.ericsson.msc.group5.entities.FailureClass;

public class JPAFailureClassDAO implements FailureClassDAO {

	@Inject
	public FailureClassDAO failureClassDAO;

	public FailureClass getManagedFailureClass(int failureClass,
			String description) {
		EntityManager em = PersistenceUtil.createEM();
		List <FailureClass> fcList = em
				.createQuery(
						"select fc from " + FailureClass.class.getName()
								+ " fc where fc.failureClass = :failureClassId",
						FailureClass.class)
				.setParameter("failureClassId", failureClass).getResultList();
		if (fcList.isEmpty()) {
			FailureClass fc = new FailureClass();
			fc.setFailureClass(failureClass);
			fc.setDescription(description);
			PersistenceUtil.persist(fc);
			em.close();
			return fc;
		}
		em.close();
		return fcList.get(0);
	}
}
