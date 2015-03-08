package com.ericsson.msc.group5.services;

import java.util.Collection;

import javax.ejb.Local;

import com.ericsson.msc.group5.entities.FailureTrace;

@Local
public interface FailureTraceService {

	public Collection <FailureTrace> getFailureTraces();

	public void addFailureTraces(Collection <FailureTrace> failureTraces);

	public Collection<FailureTrace> getEventCauseCombinations(String string);
	
}
