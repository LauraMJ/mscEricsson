package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EventCauseCK implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer causeCode;
	private Integer eventId;

	public EventCauseCK() {
		//
	}
	
	public EventCauseCK(Integer causeCode, Integer eventId){
		super(); //needed?
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
