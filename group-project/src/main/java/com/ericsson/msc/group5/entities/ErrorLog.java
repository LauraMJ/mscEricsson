package com.ericsson.msc.group5.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "error_log")
public class ErrorLog {
	@Id
	@Column(name = "error_log_id")
	//Needs generating
	private int errorLogId;
	//Do these need to be changed to wrapper classes?
	@Column(name = "date_and_time")
	private String dateAndTime;
	@Column(name = "event_id")
	private int eventId;
	@Column(name = "failure_class")
	private int failureClass;
	@Column(name = "user_equipment_type")
	private int userEquipmentType;
	private int market;
	private int operator;
	@Column(name = "cell_id")
	private int cellId;
	private int duration;
	@Column(name = "cause_code")
	private int causeCode;
	@Column(name = "ne_version", length = 3)
	private String neVersion;
	@Column(length = 20)
	private long imsi;
	@Column(length = 20)
	private long hier3Id;
	@Column(length = 20)
	private long hier32Id;
	@Column(length = 20)
	private long hier321Id;
	

	
	public ErrorLog() {
	
	}
	
	public ErrorLog(int errorLogId, String dateAndTime, int eventId, int failureClass, int userEquipmentType,
			int market, int operator, int cellId, int duration, int causeCode, String neVersion, long imsi,
			long hier3Id, long hier32Id, long hier321Id){
		this.errorLogId = errorLogId;
		this.dateAndTime = dateAndTime;
		this.eventId = eventId;
		this.failureClass = failureClass;
		this.userEquipmentType = userEquipmentType;
		this.market = market;
		this.operator = operator;
		this.cellId = cellId;
		this.duration = duration;
		this.causeCode = causeCode;
		this.neVersion = neVersion;
		this.imsi = imsi;
		this.hier3Id = hier3Id;
		this.hier32Id = hier32Id;
		this.hier321Id = hier321Id;
		
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
