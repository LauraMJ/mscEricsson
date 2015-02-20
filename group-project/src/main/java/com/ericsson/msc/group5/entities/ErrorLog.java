package com.ericsson.msc.group5.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "error_log")
public class ErrorLog {
	@Id
	//Needs generating
	private int errorLogId;
	//Do these need to be changed to wrapper classes?
	private String dateAndTime;
	private int eventId;
	private int failureClass;
	private int userEquipmentType;
	private int market;
	private int operator;
	private int cellId;
	private int duration;
	private int causeCode;
	private String neVersion;
	private long imsi;
	private long hier3Id;
	private long hier32Id;
	private long hier321Id;
	

	
	public ErrorLog() {
		// TODO Auto-generated constructor stub
	}



	public int getErrorLogId() {
		return errorLogId;
	}



	public void setErrorLogId(int errorLogId) {
		this.errorLogId = errorLogId;
	}



	public String getDateAndTime() {
		return dateAndTime;
	}



	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}



	public int getEventId() {
		return eventId;
	}



	public void setEventId(int eventId) {
		this.eventId = eventId;
	}



	public int getFailureClass() {
		return failureClass;
	}



	public void setFailureClass(int failureClass) {
		this.failureClass = failureClass;
	}



	public int getUserEquipmentType() {
		return userEquipmentType;
	}



	public void setUserEquipmentType(int userEquipmentType) {
		this.userEquipmentType = userEquipmentType;
	}



	public int getMarket() {
		return market;
	}



	public void setMarket(int market) {
		this.market = market;
	}



	public int getOperator() {
		return operator;
	}



	public void setOperator(int operator) {
		this.operator = operator;
	}



	public int getCellId() {
		return cellId;
	}



	public void setCellId(int cellId) {
		this.cellId = cellId;
	}



	public int getDuration() {
		return duration;
	}



	public void setDuration(int duration) {
		this.duration = duration;
	}



	public int getCauseCode() {
		return causeCode;
	}



	public void setCauseCode(int causeCode) {
		this.causeCode = causeCode;
	}



	public String getNeVersion() {
		return neVersion;
	}



	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}



	public long getImsi() {
		return imsi;
	}



	public void setImsi(long imsi) {
		this.imsi = imsi;
	}



	public long getHier3Id() {
		return hier3Id;
	}



	public void setHier3Id(long hier3Id) {
		this.hier3Id = hier3Id;
	}



	public long getHier32Id() {
		return hier32Id;
	}



	public void setHier32Id(long hier32Id) {
		this.hier32Id = hier32Id;
	}



	public long getHier321Id() {
		return hier321Id;
	}



	public void setHier321Id(long hier321Id) {
		this.hier321Id = hier321Id;
	}

	
}
