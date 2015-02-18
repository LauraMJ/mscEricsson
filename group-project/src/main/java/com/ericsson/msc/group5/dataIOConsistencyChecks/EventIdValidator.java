package com.ericsson.msc.group5.dataIOConsistencyChecks;

public class EventIdValidator {

	private Integer eventIdInteger;

	public EventIdValidator(Integer eventIdInteger) {
		validate(eventIdInteger);
	}

	public boolean validate(Integer input) {
		if (input >= 4000 && input <= 5000) {
			return true;
		}
		System.out.println("Invalid eventId");
		return false;
	}

	public Integer getEventIdInteger() {
		return eventIdInteger;
	}

	public void setEventIdInteger(Integer eventIdInteger) {
		this.eventIdInteger = eventIdInteger;
	}

}
