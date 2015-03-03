package com.ericsson.msc.group5.dao.jpa;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.FailureClassDAO;
import com.ericsson.msc.group5.entities.FailureClass;

public class JPAFailureClassDAO implements FailureClassDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Collection <FailureClass> getAllFailureClasses() {
		return em.createNamedQuery("findAllFailureClasses").getResultList();
	}

	@Override
	public FailureClass getFailureClass(int failureClassId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertFailureClass(FailureClass failureClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFailureClass(FailureClass failureClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFailureClass(FailureClass failureClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchInsertFailureClasses(Collection <FailureClass> failureClassList) {
		for(FailureClass failureClass : failureClassList)
			em.persist(failureClass);
	}
}
