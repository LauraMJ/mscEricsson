package project;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Event_CauseCK implements Serializable{
	private static final long serialVersionUID = 1L;
	private int causeCode;
	private int eventId;

	public Event_CauseCK() {
		//
	}
	
	public Event_CauseCK(int causeCode, int eventId){
		super(); //needed?
		this.causeCode = causeCode;
		this.eventId = eventId;
	}
	
	
	
	//required equals method
	public boolean equals(Object object){
		//need to add more code
		return false;
	}
	//required hashcode method
	public int hashCode(){
		int hashCode = 0; //temp to remove errors
		//generate hash for both fields together
		return hashCode;
	}
	
	

	public int getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(int causeCode) {
		this.causeCode = causeCode;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

}
