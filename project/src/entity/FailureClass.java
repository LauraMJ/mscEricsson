package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Failure_Class")
public class FailureClass {
	@Id
	@Column(name="failure_class")
	private Integer failureClass;
	@Column(length=100)
	private String description;

	public FailureClass() {
	}

	public FailureClass(Integer failureClass, String description) {
		this.failureClass = failureClass;
		this.description = description;
	}

	public Integer getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(Integer failureClass) {
		this.failureClass = failureClass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
