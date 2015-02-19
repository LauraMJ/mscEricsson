package com.ericsson.msc.group5.dataIO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FailureClassData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	Integer failureClass;
	String description;

	public FailureClassData() {

	}

	/**
	 * @return the failureClass
	 */
	public Integer getFailureClass() {
		return failureClass;
	}

	/**
	 * @param failureClass
	 *            the failureClass to set
	 */
	public void setFailureClass(Integer failureClass) {
		this.failureClass = failureClass;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
