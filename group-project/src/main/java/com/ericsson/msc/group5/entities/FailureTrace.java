package com.ericsson.msc.group5.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FailureTrace {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="failure_trace_id")
	private Integer failureTraceId;
	
	@Column(name="date_time")
	private String dateTime;
	
	@Column(name="event_id")
	private Integer eventId;
	
	@Column(name="failure_class")
	private Integer failureClass;
	
	@Column(name="ue_type")
	private Integer ueType;
	
	@Column(name="market_operator")
	private Integer marketOperator;
	
	@Column(name="cell_id")
	private Integer cellId;
	
	@Column
	private Integer duration;
	
	@Column(name="cause_code")
	private Integer causeCode;
	
	@Column(name="ne_version", length=3)
	private String neVersion;
	
	@Column(name="imsi", length=20)
	private String IMSI;
	
	@Column(name="hier_info_id")
	private Integer hierInfoId;

	public FailureTrace() {
		
	}
	
	public FailureTrace(Integer failureTraceId){
		this.setFailureTraceId(failureTraceId);
	}

	public Integer getFailureTraceId() {
		return failureTraceId;
	}

	public void setFailureTraceId(Integer failureTraceId) {
		this.failureTraceId = failureTraceId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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

	public Integer getUeType() {
		return ueType;
	}

	public void setUeType(Integer ueType) {
		this.ueType = ueType;
	}

	public Integer getMarketOperator() {
		return marketOperator;
	}

	public void setMarketOperator(Integer marketOperator) {
		this.marketOperator = marketOperator;
	}

	public Integer getCellId() {
		return cellId;
	}

	public void setCellId(Integer cellId) {
		this.cellId = cellId;
	}

	public String getNeVersion() {
		return neVersion;
	}

	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public Integer getHierInfoId() {
		return hierInfoId;
	}

	public void setHierInfoId(Integer hierInfoId) {
		this.hierInfoId = hierInfoId;
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

	public String getIMSI() {
		return IMSI;
	}

	public void setIMSI(String iMSI) {
		IMSI = iMSI;
	}

}
