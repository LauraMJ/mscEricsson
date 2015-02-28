package com.ericsson.msc.group5.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Failure Trace JPA entity. The main entity in the application, maps to the Base Data table.
 */
@Entity
@Table(name = "failure_trace")
@NamedQueries({@NamedQuery(name = "findAllFailureTraces", query = "SELECT f FROM FailureTrace f")})
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
	@Column(name = "hier3_id", length = 20)
	private String hier3Id;
	@Column(name = "hier32_id", length = 20)
	private String hier32Id;
	@Column(name = "hier321_id", length = 20)
	private String hier321Id;

	@ManyToOne
	@JoinColumn(name = "failureClass")
	private FailureClass failureClass;
	@ManyToOne
	@JoinColumn(name = "typeAllocationCode")
	private UserEquipment userEqipment;
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "causeCode", referencedColumnName = "causeCode"),
			@JoinColumn(name = "eventId", referencedColumnName = "eventId")})
	private EventCause eventCause;
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "countryCode", referencedColumnName = "countryCode"),
			@JoinColumn(name = "networkCode", referencedColumnName = "networkCode")})
	private CountryCodeNetworkCode countryCodeNetworkCode;

	/**
	 * No-args constructor used by the JPA.
	 */
	public FailureTrace() {
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

	public String getNeVersion() {
		return neVersion;
	}

	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public String getIMSI() {
		return IMSI;
	}

	public void setIMSI(String iMSI) {
		IMSI = iMSI;
	}

	public String getHier3Id() {
		return hier3Id;
	}

	public void setHier3Id(String hier3Id) {
		this.hier3Id = hier3Id;
	}

	public String getHier32Id() {
		return hier32Id;
	}

	public void setHier32Id(String hier32Id) {
		this.hier32Id = hier32Id;
	}

	public String getHier321Id() {
		return hier321Id;
	}

	public void setHier321Id(String hier321Id) {
		this.hier321Id = hier321Id;
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

	public EventCause getEventCause() {
		return eventCause;
	}

	public void setEventCause(EventCause eventCause) {
		this.eventCause = eventCause;
	}

	public CountryCodeNetworkCode getCountryCodeNetworkCode() {
		return countryCodeNetworkCode;
	}

	public void setCountryCodeNetworkCode(CountryCodeNetworkCode countryCodeNetworkCode) {
		this.countryCodeNetworkCode = countryCodeNetworkCode;
	}
}