package project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EventCause {
	@Id
	@Column(name="cause_code_event_id")
	private EventCauseCK causeCodeAndEventIdCK; //Cause_code and event_id CK
	@Column(name="description", length=100)
	private String description;

	public EventCause() {
		//
	}

	public EventCause(EventCauseCK causeCodeAndEventIdCK, String description){
		this.causeCodeAndEventIdCK = causeCodeAndEventIdCK;
		this.description = description;
	}
}
