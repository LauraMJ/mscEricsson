package com.ericsson.msc.group5.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Error log JPA entity. Used to store any erroneous data that is rejected by the data validation tests when the data arrives in the system.
 */
@Entity
@Table(name = "error_log")
@NamedQueries({@NamedQuery(name = "findAllErrorLogs", query = "SELECT e FROM ErrorLog e")})
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

	/**
	 * No-args constructor used by the JPA.
	 */
	public ErrorLog() {
	}

	/**
	 * Create an error log entity.
	 * 
	 * @param generationTime
	 *            Date and time as a String used to identify when the data was read in. Ideally all items acquired through in a single batch import of data will
	 *            have the same date/time combination.
	 * @param errorDescription
	 *            Error description associated with the entry - human-readable reason for rejection.
	 * @param baseData
	 *            The base data as it was read in by the application concatenated into a single String.
	 */
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