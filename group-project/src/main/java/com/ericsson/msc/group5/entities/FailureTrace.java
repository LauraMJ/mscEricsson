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
	@Column(name = "failure_trace_id")
	private Integer failureTraceId;
	@Column(name = "date_time")
	private String dateTime;
	@Column(name = "event_id")
	private Integer eventId;
	@Column(name = "cell_id")
	private Integer cellId;
	private Integer duration;
	@Column(name = "ne_version", length = 3)
	private String neVersion;
	@Column(name = "imsi", length = 20)
	private String IMSI;

	@Column(name = "market_operator")
	private MCC_MNC marketOperator;
	@Column(name = "failure_class")
	private FailureClass failureClass;
	@Column(name = "ue_type")
	private UEType ueType;
	@Column(name = "cause_code")
	private EventCause causeCode;
	@Column(name = "hier_info")
	private HierInfo hierInfo;

	public FailureTrace() {

	}

	public FailureTrace(Integer failureTraceId) {
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

	public FailureClass getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(FailureClass failureClass) {
		this.failureClass = failureClass;
	}

	public UEType getUeType() {
		return ueType;
	}

	public void setUeType(UEType ueType) {
		this.ueType = ueType;
	}

	public MCC_MNC getMarketOperator() {
		return marketOperator;
	}

	public void setMarketOperator(MCC_MNC marketOperator) {
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

	public HierInfo getHierInfoId() {
		return hierInfo;
	}

	public void setHierInfoId(HierInfo hierInfo) {
		this.hierInfo = hierInfo;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public EventCause getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(EventCause causeCode) {
		this.causeCode = causeCode;
	}

	public String getIMSI() {
		return IMSI;
	}

	public void setIMSI(String iMSI) {
		IMSI = iMSI;
	}

}
