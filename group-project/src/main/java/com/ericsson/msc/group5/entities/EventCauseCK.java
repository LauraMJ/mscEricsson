package com.ericsson.msc.group5.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Composite Key that helps to map/uniquely identify an event using a combination of Cause Code/Event id.
 */
@Embeddable
public class EventCauseCK implements Serializable {

	private static final long serialVersionUID = 3373166981656864744L;

	@Column(name = "cause_code")
	private Integer causeCode;
	@Column(name = "event_id")
	private Integer eventId;

	/**
	 * No-args constructor used by the JPA.
	 */
	public EventCauseCK() {
	}

	/**
	 * Create a composite key made up of a Cause Code and an Event id. Together they can be used to uniquely identify an event.
	 * 
	 * @param causeCode
	 *            The first part of the composite key - a non-unique Cause Code. This key is non-unique by itself, but forms a unique composite key when
	 *            combined with the Event id.
	 * @param eventId
	 *            The second part of the composite key - a non-unique Event id. This key is non-unique by itself, but forms a unique composite key when combined
	 *            with the Cause Code.
	 */
	public EventCauseCK(Integer causeCode, Integer eventId) {
		super();
		this.causeCode = causeCode;
		this.eventId = eventId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventCauseCK other = (EventCauseCK) obj;
		if (causeCode != other.causeCode)
			return false;
		if (eventId != other.eventId)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + causeCode;
		result = prime * result + eventId;
		return result;
	}

	public Integer getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(Integer causeCode) {
		this.causeCode = causeCode;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
}
