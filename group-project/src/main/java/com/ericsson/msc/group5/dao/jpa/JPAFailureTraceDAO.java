package com.ericsson.msc.group5.dao.jpa;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.FailureTraceDAO;
import com.ericsson.msc.group5.entities.FailureTrace;

public class JPAFailureTraceDAO implements FailureTraceDAO {

	@PersistenceContext
	private EntityManager em;

	public Collection <FailureTrace> getEventCauseForImsi(String imsi) {
		System.out.println("tried to run query");
		return em.createNamedQuery("getEventCauseCombinations")
				.setParameter("givenImsi", imsi).getResultList();
	}

	@Override
	public Collection <FailureTrace> getAllFailureTraces() {
		return em.createNamedQuery("findAllFailureTraces").getResultList();
	}

	@Override
	public void insertFailureTrace(FailureTrace failureTrace) {
		em.persist(failureTrace);
	}

	@Override
	public void updateFailureTrace(FailureTrace failureTrace) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteFailureTrace(FailureTrace failureTrace) {
		// TODO Auto-generated method stub
	}

	@Override
	public void batchInsertFailureTrace(
			Collection <FailureTrace> failureTraceList) {
		for (FailureTrace failureTrace : failureTraceList)
			em.persist(failureTrace);
	}

}
