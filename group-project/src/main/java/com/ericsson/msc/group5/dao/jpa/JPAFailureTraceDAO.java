package com.ericsson.msc.group5.dao.jpa;

import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.FailureTraceDAO;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.FailureTrace;

public class JPAFailureTraceDAO implements FailureTraceDAO {

	@PersistenceContext
	private EntityManager em;

	public Collection <EventCause> getEventCauseForImsi(String imsi) {
		return em.createNamedQuery("getEventCauseCombinations").setParameter("givenImsi", imsi).getResultList();
	}

	public Collection <String> getImsiOfFailureWithinTimePeriod(Date startTime, Date endTime) {
		return em.createNamedQuery("getImsiOfFailureByTimePeriod").setParameter("startTime", startTime).setParameter("endTime", endTime)
				.getResultList();
	}
	
	public Collection <String> getGivenImsiOfFailureWithinTimePeriod(Date startTime, Date endTime, String Imsi){
		return em.createNamedQuery("givenImsiByTimePeriod").setParameter("startTime", startTime).setParameter("endTime", endTime).setParameter("Imsi", Imsi).getResultList();
	}

	public Long getTotalNumberOfEntries() {
		return (Long) em.createNamedQuery("getTotalNumberOfEntries").getSingleResult();
	}

	@Override
	public Collection <FailureTrace> getAllFailureTraces() {
		return em.createNamedQuery("getAllFailureTraces").getResultList();
	}

	@Override
	public void insertFailureTrace(FailureTrace failureTrace) {
		em.persist(failureTrace);
	}

	@Override
	public void batchInsertFailureTrace(Collection <FailureTrace> failureTraceList) {
		for (FailureTrace failureTrace : failureTraceList) {
			em.persist(failureTrace);
		}
	}
}
