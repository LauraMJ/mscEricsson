package com.ericsson.msc.group5.dataIOConsistencyChecks;

public class EventIdValidator {

	private int eventId;

	public EventIdValidator(int eventId) {
		validate(eventId);
	}

	public boolean validate(int eventId) {

		if (eventId <= 4000 && eventId <= 5000) {
			System.out.println("Invalid eventId");
			return false;
		}
		return true;

	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

}
