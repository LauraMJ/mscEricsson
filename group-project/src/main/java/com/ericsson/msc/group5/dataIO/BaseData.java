package com.ericsson.msc.group5.dataIO;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BaseData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	Date dateTimeVal;
	String dateString;
	Integer eventIdVal;
	Integer failureClassVal;
	Integer ueTypeVal;
	Integer marketVal;
	Integer operatorVal;
	Integer cellIdVal;
	Integer durationVal;
	Integer causeCodeVal;
	String neVersionVal;
	Long imsiVal;
	Long hier3Val;
	Long hier32Val;
	Long hier321Val;
	
	public BaseData() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateTimeVal() {
		return dateTimeVal;
	}

	public void setDateTimeVal(Date dateTimeVal) {
		this.dateTimeVal = dateTimeVal;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public Integer getEventIdVal() {
		return eventIdVal;
	}

	public void setEventIdVal(Integer eventIdVal) {
		this.eventIdVal = eventIdVal;
	}

	public Integer getFailureClassVal() {
		return failureClassVal;
	}

	public void setFailureClassVal(Integer failureClassVal2) {
		this.failureClassVal = failureClassVal2;
	}

	public Integer getUeTypeVal() {
		return ueTypeVal;
	}

	public void setUeTypeVal(Integer ueTypeVal) {
		this.ueTypeVal = ueTypeVal;
	}

	public Integer getMarketVal() {
		return marketVal;
	}

	public void setMarketVal(Integer marketVal) {
		this.marketVal = marketVal;
	}

	public Integer getOperatorVal() {
		return operatorVal;
	}

	public void setOperatorVal(Integer operatorVal) {
		this.operatorVal = operatorVal;
	}

	public Integer getCellIdVal() {
		return cellIdVal;
	}

	public void setCellIdVal(Integer cellIdVal) {
		this.cellIdVal = cellIdVal;
	}

	public Integer getDurationVal() {
		return durationVal;
	}

	public void setDurationVal(Integer durationVal) {
		this.durationVal = durationVal;
	}

	public Integer getCauseCodeVal() {
		return causeCodeVal;
	}

	public void setCauseCodeVal(Integer causeCodeVal2) {
		this.causeCodeVal = causeCodeVal2;
	}

	public String getNeVersionVal() {
		return neVersionVal;
	}

	public void setNeVersionVal(String neVersionVal) {
		this.neVersionVal = neVersionVal;
	}

	public Long getImsiVal() {
		return imsiVal;
	}

	public void setImsiVal(Long imsiVal) {
		this.imsiVal = imsiVal;
	}

	public Long getHier3Val() {
		return hier3Val;
	}

	public void setHier3Val(Long hier3Val) {
		this.hier3Val = hier3Val;
	}

	public Long getHier32Val() {
		return hier32Val;
	}

	public void setHier32Val(Long hier32Val) {
		this.hier32Val = hier32Val;
	}

	public Long getHier321Val() {
		return hier321Val;
	}

	public void setHier321Val(Long hier321Val) {
		this.hier321Val = hier321Val;
	}
}
