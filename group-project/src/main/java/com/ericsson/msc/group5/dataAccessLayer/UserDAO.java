package com.ericsson.msc.group5.dataAccessLayer;

import com.ericsson.msc.group5.entities.EventCause;

public interface UserDAO {

	public EventCause getUsers(int causeCode, int eventId, String description);

}
