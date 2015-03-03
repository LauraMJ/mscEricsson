package com.ericsson.msc.group5.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "error_log")
public class ErrorLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "error_log_id")
	private Integer errorLogId;
	@Column(name = "generation_time")
	private String generationTime;
	@Column(name = "error_description")
	private String errorDescription;
	@Column(name = "base_data", length = 350)
	private String baseData;

	public ErrorLog() {

	}

	public ErrorLog(String generationTime, String errorDescription, String baseData) {
		this.generationTime = generationTime;
		this.errorDescription = errorDescription;
		this.baseData = baseData;
	}

	public String getGenerationTime() {
		return generationTime;
	}

	public void setGenerationTime(String generationTime) {
		this.generationTime = generationTime;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getBaseData() {
		return baseData;
	}

	public void setBaseData(String baseData) {
		this.baseData = baseData;
	}

	public Integer getErrorLogId() {
		return errorLogId;
	}
}