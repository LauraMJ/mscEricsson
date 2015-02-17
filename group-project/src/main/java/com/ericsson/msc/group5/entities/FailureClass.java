package com.ericsson.msc.group5.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Failure Class JPA entity. Matches an integer id of a failure to a string description.
 * 
 * @author szymon
 */
@Entity(name = "Failure_Class")
public class FailureClass {

	@Id
	@Column(name = "failure_class")
	private Integer failureClass;

	@Column(length = 100)
	private String description;

	@OneToMany(mappedBy = "failureClass", targetEntity = FailureTrace.class)
	private Collection <FailureTrace> failureTrace;

	/**
	 * No-args constructor used by the JPA.
	 */
	public FailureClass() {
	}

	/**
	 * Create a failure class with the given id and description.
	 * 
	 * @param failureClass
	 *            Integer id of the failure class.
	 * @param description
	 *            String description of the failure class.
	 */
	public FailureClass(Integer failureClass, String description) {
		this.failureClass = failureClass;
		this.description = description;
	}

	/**
	 * Returns the unique integer id of the failure class.
	 * 
	 * @return Integer id of the failure class.
	 */
	public Integer getFailureClass() {
		return failureClass;
	}

	/**
	 * Set the integer id of the failure class.
	 * 
	 * @param failureClass
	 *            Unique integer id.
	 */
	public void setFailureClass(Integer failureClass) {
		this.failureClass = failureClass;
	}

	/**
	 * Get the String description of the failure class.
	 * 
	 * @return String description of the failure class.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the String description of the failure class.
	 * 
	 * @param description
	 *            String description of the failure class.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
