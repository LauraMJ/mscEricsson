package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FailureClass {
	@Id
	private int failureClass;
	private String description;

	public FailureClass() {
	}

	public FailureClass(int failureClass, String description) {
		this.failureClass = failureClass;
		this.description = description;
	}

	public int getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(int failureClass) {
		this.failureClass = failureClass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
