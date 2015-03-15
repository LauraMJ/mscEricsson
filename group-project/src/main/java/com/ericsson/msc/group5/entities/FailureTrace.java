package com.ericsson.msc.group5.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Failure Trace JPA entity. The main entity in the application, maps to the
 * Base Data table.
 */

// .causeCode, f.eventCause.causeCodeEventIdCK.eventId,
// .description
// f.eventCause.causeCodeEventIdCK.causeCode,
// f.eventCause.causeCodeEventIdCK.eventId, "
// + "f.eventCause.description
@Entity
@Table(name = "failure_trace")
@NamedQueries({
		@NamedQuery(name = "getAllFailureTraces", query = "SELECT f FROM FailureTrace f"),
		@NamedQuery(name = "getTotalNumberOfEntries", query = "SELECT count(f.failureTraceId) from FailureTrace f"),
		@NamedQuery(name = "getEventCauseCombinations", query = "SELECT DISTINCT (f.eventCause) FROM FailureTrace f WHERE f.IMSI = :givenImsi"),
		@NamedQuery(name = "getImsiOfFailureByTimePeriod", query = "SELECT f.IMSI FROM FailureTrace f WHERE f.dateTime BETWEEN :startTime AND :endTime "),
		@NamedQuery(name = "givenImsiByTimePeriod", query = "SELECT COUNT(f.dateTime) AND SUM(f.duration) FROM FailureTrace f WHERE f.IMSI = :givenImsi AND f.dateTime BETWEEN :startTime AND :endTime GROUP BY f.IMSI ")})
public class FailureTrace {

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "failure_trace_id")
	private Long failureTraceId;
	@Column(name = "date_time")
	private Date dateTime;
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
	@JoinColumn(name = "failure_class")
	private FailureClass failureClass;
	@ManyToOne
	@JoinColumn(name = "typeAllocationCode")
	private UserEquipment userEqipment;
	@ManyToOne
	@JoinColumns({@JoinColumn(name = "cause_code", referencedColumnName = "cause_code"),
			@JoinColumn(name = "event_id", referencedColumnName = "event_id")})
	private EventCause eventCause;
	@ManyToOne
	@JoinColumns({@JoinColumn(name = "country_code", referencedColumnName = "country_code"),
			@JoinColumn(name = "network_code", referencedColumnName = "network_code")})
	private CountryCodeNetworkCode countryCodeNetworkCode;

	/**
	 * No-args constructor used by the JPA.
	 */
	public FailureTrace() {
	}

	public Long getFailureTraceId() {
		return failureTraceId;
	}

	public void setFailureTraceId(Long failureTraceId) {
		this.failureTraceId = failureTraceId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
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
