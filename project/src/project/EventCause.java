package project;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EventCause {
	@Id
	private EventCauseCK causeCodeAndEventIdCK; //Cause_code and event_id CK
	private String description;

	public EventCause() {
		//
	}

	public EventCause(EventCauseCK causeCodeAndEventIdCK, String description){
		this.causeCodeAndEventIdCK = causeCodeAndEventIdCK;
		this.description = description;
	}
}
