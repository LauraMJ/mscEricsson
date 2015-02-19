package com.ericsson.msc.group5.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity(name = "failure_trace")
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

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "failureClass")
	private FailureClass failureClass;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "typeAllocationCode")
	private UserEquipment userEqipment;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "hierInfoId")
	private HierInfo hierInfo;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumns({
			@JoinColumn(name = "causeCode", referencedColumnName = "causeCode"),
			@JoinColumn(name = "eventId", referencedColumnName = "eventId")})
	private EventCause causeCode;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumns({
			@JoinColumn(name = "countryCode", referencedColumnName = "countryCode"),
			@JoinColumn(name = "networkCode", referencedColumnName = "networkCode")})
	private CountryCodeNetworkCode marketOperator;

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

	public UserEquipment getUserEqipment() {
		return userEqipment;
	}

	public void setUserEqipment(UserEquipment userEqipment) {
		this.userEqipment = userEqipment;
	}

	public CountryCodeNetworkCode getMarketOperator() {
		return marketOperator;
	}

	public void setMarketOperator(CountryCodeNetworkCode marketOperator) {
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