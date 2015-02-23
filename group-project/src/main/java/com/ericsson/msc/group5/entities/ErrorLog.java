package com.ericsson.msc.group5.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "error_log")
public class ErrorLog {
	// Needs generating
	@Id
	@Column(name = "error_log_id")
	private Integer errorLogId;
	@Column(name = "date_and_time")
	private String dateAndTime;
	@Column(name = "event_id")
	private Integer eventId;
	@Column(name = "failure_class")
	private Integer failureClass;
	@Column(name = "user_equipment_type")
	private Integer userEquipmentType;
	private Integer market;
	private Integer operator;
	@Column(name = "cell_id")
	private Integer cellId;
	private Integer duration;
	@Column(name = "cause_code")
	private Integer causeCode;
	@Column(name = "ne_version", length = 3)
	private String neVersion;
	@Column(length = 20)
	private Long imsi;
	@Column(length = 20)
	private Long hier3Id;
	@Column(length = 20)
	private Long hier32Id;
	@Column(length = 20)
	private Long hier321Id;

	public ErrorLog() {

	}

	public ErrorLog(Integer errorLogId, String dateAndTime, Integer eventId,
			Integer failureClass, Integer userEquipmentType, Integer market,
			Integer operator, Integer cellId, Integer duration,
			Integer causeCode, String neVersion, Long imsi, Long hier3Id,
			Long hier32Id, Long hier321Id) {
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

	public Integer getErrorLogId() {
		return errorLogId;
	}

	public void setErrorLogId(Integer errorLogId) {
		this.errorLogId = errorLogId;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(Integer failureClass) {
		this.failureClass = failureClass;
	}

	public Integer getUserEquipmentType() {
		return userEquipmentType;
	}

	public void setUserEquipmentType(Integer userEquipmentType) {
		this.userEquipmentType = userEquipmentType;
	}

	public Integer getMarket() {
		return market;
	}

	public void setMarket(Integer market) {
		this.market = market;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Integer getCellId() {
		return cellId;
	}

	public void setCellId(Integer cellId) {
		this.cellId = cellId;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(Integer causeCode) {
		this.causeCode = causeCode;
	}

	public String getNeVersion() {
		return neVersion;
	}

	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public Long getImsi() {
		return imsi;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	public Long getHier3Id() {
		return hier3Id;
	}

	public void setHier3Id(Long hier3Id) {
		this.hier3Id = hier3Id;
	}

	public Long getHier32Id() {
		return hier32Id;
	}

	public void setHier32Id(Long hier32Id) {
		this.hier32Id = hier32Id;
	}

	public Long getHier321Id() {
		return hier321Id;
	}

	public void setHier321Id(Long hier321Id) {
		this.hier321Id = hier321Id;
	}

}