package com.ericsson.msc.group5.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.FailureClassDAO;
import com.ericsson.msc.group5.entities.FailureClass;

public class JPAFailureClassDAO implements FailureClassDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List <FailureClass> getAllFailureClasses() {
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
	public void batchInsertFailureClasses(List <FailureClass> failureClassList) {
		// TODO Auto-generated method stub
		
	}

}
