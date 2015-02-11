package entity;

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
	private int failureTraceId;
	
	@Column(name="date_time")
	private String dateTime;
	
	@Column(name="event_id")
	private int eventId;
	
	@Column(name="failure_class")
	private int failureClass;
	
	@Column(name="ue_type")
	private int ueType;
	
	@Column(name="market_operator")
	private int marketOperator;
	
	@Column(name="cell_id")
	private int cellId;
	
	@Column
	private int duration;
	
	@Column(name="cause_code")
	private int causeCode;
	
	@Column(name="ne_version", length=3)
	private String neVersion;
	
	@Column(name="imsi", length=20)
	private String IMSI;
	
	@Column(name="hier_info_id")
	private int hierInfoId;

	public FailureTrace() {
		
	}
	
	public FailureTrace(int failureTraceId){
		this.setFailureTraceId(failureTraceId);
	}

	public int getFailureTraceId() {
		return failureTraceId;
	}

	public void setFailureTraceId(int failureTraceId) {
		this.failureTraceId = failureTraceId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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

	public int getUeType() {
		return ueType;
	}

	public void setUeType(int ueType) {
		this.ueType = ueType;
	}

	public int getMarketOperator() {
		return marketOperator;
	}

	public void setMarketOperator(int marketOperator) {
		this.marketOperator = marketOperator;
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public String getNeVersion() {
		return neVersion;
	}

	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public int getHierInfoId() {
		return hierInfoId;
	}

	public void setHierInfoId(int hierInfoId) {
		this.hierInfoId = hierInfoId;
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

	public String getIMSI() {
		return IMSI;
	}

	public void setIMSI(String iMSI) {
		IMSI = iMSI;
	}

}
