package com.ericsson.msc.group5.services;

import java.util.Collection;
import javax.ejb.Local;
import org.json.simple.JSONObject;
import com.ericsson.msc.group5.entities.FailureTrace;

@Local
public interface FailureTraceService {

	public Collection <FailureTrace> findImsiOfFailureByTimePeriod(
			String startTime, String endTime);

	public Collection <JSONObject> getEventCauseCombinations(String imsi);

	public Collection <FailureTrace> getAllFailureTraces();
}
