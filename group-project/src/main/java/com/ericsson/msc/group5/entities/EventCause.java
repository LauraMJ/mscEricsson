package com.ericsson.msc.group5.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "event_cause")
public class EventCause {

	@Id
	@Column(name = "cause_code_event_id")
	private EventCauseCK causeCodeEventIdCK;
	@Column(length = 100)
	private String description;
	@OneToMany(mappedBy = "causeCode", targetEntity = FailureTrace.class)
	private Collection <FailureTrace> failureTrace;

	public EventCause() {
		//
	}

	public EventCause(EventCauseCK causeCodeEventIdCK, String description) {
		this.causeCodeEventIdCK = causeCodeEventIdCK;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
