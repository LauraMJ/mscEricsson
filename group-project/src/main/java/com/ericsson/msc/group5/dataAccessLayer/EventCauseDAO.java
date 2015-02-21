package com.ericsson.msc.group5.dataAccessLayer;

import com.ericsson.msc.group5.entities.EventCause;

public interface EventCauseDAO {

	public EventCause getMangedEventCause(int causeCode, int eventId,
			String description);
}
