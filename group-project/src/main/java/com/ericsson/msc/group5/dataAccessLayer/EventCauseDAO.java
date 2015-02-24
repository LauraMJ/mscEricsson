package com.ericsson.msc.group5.dataAccessLayer;

import javax.ejb.Local;
import com.ericsson.msc.group5.entities.EventCause;

@Local
public interface EventCauseDAO {

	public EventCause getMangedEventCause(int causeCode, int eventId,
			String description);
}
