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
	
	@Column(length=60)
	private String failureTrace;
	
	@Column(name="date_time")
	private String dateTime;
	
	@Column(name="event_cd")
	private int eventId;
	
	@Column(name="failure_class")
	private int failureClass;
	
	@Column(name="ue_type")
	private int ueType;
	
	@Column(name="market_operator")
	private int marketOperator;
	
	@Column(name="cell_info_Id")
	private int cellInfoId;
	
	@Column(name="duration")
	private int duration;
	
	@Column(name="cause_code")
	private int causeCode;
	
	@Column(name="imsi")
	private String IMSI;

	public FailureTrace() {
		
	}
	
	public FailureTrace(int failureTraceId, String failureTrace){
		this.setFailureTraceId(failureTraceId);
		this.setFailureTrace(failureTrace);
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

	public int getCellInfoId() {
		return cellInfoId;
	}

	public void setCellInfoId(int cellInfoId) {
		this.cellInfoId = cellInfoId;
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
	public String getFailureTrace() {
		return failureTrace;
	}
	public void setFailureTrace(String failureTrace) {
		this.failureTrace = failureTrace;
	}
}
