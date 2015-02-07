package project;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Event_Cause {
	@Id
	private Event_CauseCK causeCodeAndEventIdCK; //Cause_code and event_id CK
	private String description;

	public Event_Cause() {
		//
	}

	public Event_Cause(Event_CauseCK causeCodeAndEventIdCK, String description){
		this.causeCodeAndEventIdCK = causeCodeAndEventIdCK;
		this.description = description;
	}
}
