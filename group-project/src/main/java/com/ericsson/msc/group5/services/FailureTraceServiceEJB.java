package com.ericsson.msc.group5.services;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ericsson.msc.group5.dao.FailureTraceDAO;
import com.ericsson.msc.group5.entities.FailureTrace;

@Stateless
@Local
public class FailureTraceServiceEJB implements FailureTraceService{

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private FailureTraceDAO dao;
	
	@Override
	public Collection<FailureTrace> getFailureTraces() {
		return dao.getAllFailureTraces();
	}

	@Override
	public void addFailureTraces(Collection<FailureTrace> failureTraces) {
		dao.batchInsertFailureTrace(failureTraces);
	}

	@Override
	public Collection<FailureTrace> getEventCauseCombinations(String string) {
		return dao.getEventCauseForImsi(string);
	}

}
