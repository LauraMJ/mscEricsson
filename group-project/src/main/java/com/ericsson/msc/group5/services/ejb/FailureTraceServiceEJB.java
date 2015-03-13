package com.ericsson.msc.group5.services.ejb;

import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.FailureTraceDAO;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.FailureTrace;
import com.ericsson.msc.group5.services.FailureTraceService;

@Stateless
@Local
public class FailureTraceServiceEJB implements FailureTraceService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private FailureTraceDAO dao;

	public Collection <EventCause> getEventCauseCombinations(String imsi) {
		Collection <EventCause> eventCauseResultCollection = dao
				.getEventCauseForImsi(imsi);

		// for (java.util.Iterator <EventCause> it = eventCauseResultCollection
		// .iterator(); it.hasNext();) {
		// EventCause currentEventCauseObject = it.next();
		// JSONObject j = new JSONObject();
		// j.put("Event Id", currentEventCauseObject.getCauseCodeEventIdCK()
		// .getEventId());
		// j.put("Cause Code", currentEventCauseObject.getCauseCodeEventIdCK()
		// .getCauseCode());
		// j.put("Description", currentEventCauseObject.getDescription());
		// specifiedResultFieldCollection.add(j);
		// }
		return eventCauseResultCollection;
	}

	public Collection <String> getImsiOfFailureByTimePeriod(Date startTime,
			Date endTime) {
		// Collection <String> collectionOfFailureTraceObjects = dao
		// .getImsiOfFailureWithinTimePeriod(startTime, endTime);
		// Collection <String> imsiCollection = new ArrayList <String>();
		// for (java.util.Iterator <FailureTrace> it =
		// collectionOfFailureTraceObjects
		// .iterator(); it.hasNext();) {
		// FailureTrace ft = it.next();
		// imsiCollection.add(ft.getIMSI());
		// }
		return dao.getImsiOfFailureWithinTimePeriod(startTime, endTime);
	}

	public Collection <FailureTrace> getAllFailureTraces() {
		return dao.getAllFailureTraces();
	}
}