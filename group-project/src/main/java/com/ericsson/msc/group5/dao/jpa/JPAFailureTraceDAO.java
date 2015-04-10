package com.ericsson.msc.group5.dao.jpa;

import java.util.Collection;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.FailureTraceDAO;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.FailureClass;
import com.ericsson.msc.group5.entities.FailureTrace;

public class JPAFailureTraceDAO implements FailureTraceDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Collection <EventCause> getEventCauseForImsi(String imsi) {
		return em.createNamedQuery("getEventCauseCombinations").setParameter("givenImsi", imsi).getResultList();
	}

	@Override
	public Collection <String> getImsiOfFailureWithinTimePeriod(Date startTime, Date endTime) {
		return em.createNamedQuery("getImsiOfFailureByTimePeriod").setParameter("startTime", startTime).setParameter("endTime", endTime)
				.getResultList();
	}

	@Override
	public Collection <String> getGivenImsiOfFailureWithinTimePeriod(Date startTime, Date endTime, String Imsi) {
		return em.createNamedQuery("givenImsiByTimePeriod").setParameter("startTime", startTime).setParameter("endTime", endTime)
				.setParameter("Imsi", Imsi).getResultList();
	}

	@Override
	public Collection <String> getCountFailsForModelWithinTimePeriod(String model, Date startTime, Date endTime) {
		return em.createNamedQuery("givenModelByTimePeriod").setParameter("model", model).setParameter("startTime", startTime)
				.setParameter("endTime", endTime).getResultList();
	}

	@Override
	public Collection <String> getEventCauseCombinationsForModel(String model) {
		return em.createNamedQuery("getEventCauseCombinationsForModel").setParameter("model", model).getResultList();
	}

	@Override
	public Collection <String> getTop10MarketOperatorCellIdCombinations(Date startTime, Date endTime) {
		return em.createNamedQuery("top10MarketOperatorCellIdCombinations").setParameter("startTime", startTime).setParameter("endTime", endTime)
				.setMaxResults(10).getResultList();
	}

	@Override
	public Collection <String> getImsiOfFailureTraceByFailureClass(Integer failureClass) {
		return em.createNamedQuery("getImsiOfFailureTraceByFailureClass").setParameter("givenFailureClass", failureClass).getResultList();
	}

	@Override
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

	// For a given IMSI, count the number of failures it has had during a given time period.
	@Override
	public Collection <String> givenImsiAndTimePeriodReturnNumberOfFailures(String Imsi, Date startTime, Date endTime) {
		return em.createNamedQuery("givenImsiAndTimePeriodReturnNumberOfFailures").setParameter("Imsi", Imsi).setParameter("startTime", startTime)
				.setParameter("endTime", endTime).getResultList();
	}

	@Override
	public Collection <String> getAllIMSIs() {
		return em.createNamedQuery("getAllIMSIs").getResultList();
	}

	@Override
	public Collection <String> getAllModels() {
		return em.createNamedQuery("getAllModels").getResultList();
	}

	@Override
	public Collection <Integer> getCauseCodesForImsi(String imsi) {
		return em.createNamedQuery("getCauseCodeImsi").setParameter("givenImsi", imsi).getResultList();
	}
	
	//Show the Top 10 IMSIs that had call failures during a time period
	@Override
	public Collection <String> topTenIMSIsWithFailures(Date startTime, Date endTime){
		return em.createNamedQuery("topTenIMSIsWithFailures").setParameter("startTime", startTime).setParameter("endTime", endTime).setMaxResults(10).getResultList();
		
	}

	@Override
	public Collection <FailureClass> getAllFailureClasses() {
		return em.createNamedQuery("getAllFailureClasses").getResultList();
	}
}
