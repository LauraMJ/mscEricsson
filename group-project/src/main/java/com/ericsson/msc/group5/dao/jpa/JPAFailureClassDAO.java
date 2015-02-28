package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.core.api.annotation.Inject;
import com.ericsson.msc.group5.dao.FailureClassDAO;
import com.ericsson.msc.group5.entities.FailureClass;

@Local
@Stateless
public class JPAFailureClassDAO implements FailureClassDAO {

	@PersistenceContext
	private EntityManager em;

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
