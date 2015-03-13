package com.ericsson.msc.group5.services;

import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.FailureTrace;

/**
 * FailureTrace service EJB interface.
 */
@Local
public interface FailureTraceService {

	public Collection <String> getImsiOfFailureByTimePeriod(Date startTime,
			Date endTime);

	public Collection <EventCause> getEventCauseCombinations(String imsi);

	public Collection <FailureTrace> getAllFailureTraces();
}