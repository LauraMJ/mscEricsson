package com.ericsson.msc.group5.dataIO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EventCauseData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Integer causeCode, eventId;
	private String description;

	/**
	 * @return the causeCode
	 */
	public Integer getCauseCode() {
		return causeCode;
	}

	/**
	 * @param causeCode
	 *            the causeCode to set
	 */
	public void setCauseCode(Integer causeCode) {
		this.causeCode = causeCode;
	}

	/**
	 * @return the eventId
	 */
	public Integer getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public EventCauseData() {

	}
}
