package com.ericsson.msc.group5.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Event Cause JPA entity. Uses an embedded natural composite key made up of a
 * Cause Code and Event identifiers to map to a textual description of the
 * cause.
 */
@Entity
@Table(name = "event_cause")
@NamedQueries({@NamedQuery(name = "findAllEventCauses", query = "SELECT e FROM EventCause e")})
public class EventCause {

	@Id
	@Column(name = "cause_code_event_id")
	private EventCauseCK causeCodeEventIdCK;

	@Column(length = 100)
	private String description;

	@OneToMany(mappedBy = "eventCause", targetEntity = FailureTrace.class)
	private Collection <FailureTrace> failureTrace;

	/**
	 * No-args constructor used by the JPA.
	 */
	public EventCause() {
	}

	/**
	 * Create a Cause Code/Event combination given a composite key and String
	 * description.
	 * 
	 * @param causeCodeEventIdCK
	 *            Composite Cause Code/Event key.
	 * @param description
	 *            Textual description of the event.
	 */
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

	public EventCauseCK getCauseCodeEventIdCK() {
		return causeCodeEventIdCK;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((causeCodeEventIdCK == null) ? 0 : causeCodeEventIdCK.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventCause other = (EventCause) obj;
		if (causeCodeEventIdCK == null) {
			if (other.causeCodeEventIdCK != null)
				return false;
		}
		else if ( !causeCodeEventIdCK.equals(other.causeCodeEventIdCK))
			return false;
		return true;
	}
}
