package com.ericsson.msc.group5.entities;

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
	@Column(name = "cell_id")
	private Integer cellId;
	private Integer duration;
	@Column(name = "ne_version", length = 3)
	private String neVersion;
	@Column(name = "imsi", length = 20)
	private String IMSI;

	@ManyToOne
	@JoinColumn(name = "failureClass")
	private FailureClass failureClass;
	@ManyToOne
	@JoinColumn
	private UserEquipment userEqipment;
	@ManyToOne
	@JoinColumn(name = "hierInfoId")
	private HierInfo hierInfo;
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "cause_code", referencedColumnName = "cause_code"),
			@JoinColumn(name = "eventId", referencedColumnName = "eventId")})
	private EventCause eventCause;
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "countryCode", referencedColumnName = "countryCode"),
			@JoinColumn(name = "networkCode", referencedColumnName = "networkCode")})
	private CountryCodeNetworkCode countryCodeNetworkCode;

	public FailureTrace() {
	}

	public Integer getFailureTraceId() {
		return failureTraceId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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

	public CountryCodeNetworkCode getCountryCodeNetworkCode() {
		return countryCodeNetworkCode;
	}

	public void setCountryCodeNetworkCode(
			CountryCodeNetworkCode countryCodeNetworkCode) {
		this.countryCodeNetworkCode = countryCodeNetworkCode;
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

	public EventCause getEventCause() {
		return eventCause;
	}

	public void setEventCause(EventCause eventCause) {
		this.eventCause = eventCause;
	}

	public String getIMSI() {
		return IMSI;
	}

	public void setIMSI(String iMSI) {
		IMSI = iMSI;
	}

	public HierInfo getHierInfo() {
		return hierInfo;
	}

	public void setHierInfo(HierInfo hierInfo) {
		this.hierInfo = hierInfo;
	}
}