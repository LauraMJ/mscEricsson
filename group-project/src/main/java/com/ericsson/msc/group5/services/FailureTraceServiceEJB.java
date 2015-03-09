package com.ericsson.msc.group5.services;

import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.json.simple.JSONObject;
import com.ericsson.msc.group5.dao.FailureTraceDAO;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.FailureTrace;

@Stateless
@Local
public class FailureTraceServiceEJB implements FailureTraceService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private FailureTraceDAO dao;

	public Collection <JSONObject> getEventCauseCombinations(String imsi) {
		Collection <JSONObject> specifiedResultFieldCollection = new ArrayList <JSONObject>();
		Collection <EventCause> eventCauseResultCollection = dao
				.getEventCauseForImsi(imsi);
		for (java.util.Iterator <EventCause> it = eventCauseResultCollection
				.iterator(); it.hasNext();) {
			EventCause currentEventCauseObject = it.next();
			JSONObject j = new JSONObject();
			j.put("Event Id", currentEventCauseObject.getCauseCodeEventIdCK()
					.getEventId());
			j.put("Cause Code", currentEventCauseObject.getCauseCodeEventIdCK()
					.getCauseCode());
			j.put("Description", currentEventCauseObject.getDescription());
			specifiedResultFieldCollection.add(j);
		}
		return specifiedResultFieldCollection;
	}

	public Collection <FailureTrace> findImsiOfFailureByTimePeriod(
			String startTime, String endTime) {
		return dao.getImsiOfFailureWithinTimePeriod(startTime, endTime);
	}

	public Collection <FailureTrace> getAllFailureTraces() {
		return dao.getAllFailureTraces();
	}
}
