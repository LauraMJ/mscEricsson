package com.ericsson.msc.group5.dataIOConsistencyChecks;

public class FailureClassValidator {

	private Integer failureclassInteger;

	public FailureClassValidator(Integer failureclassInteger) {
		validate(failureclassInteger);
	}

	public boolean validate(Integer input) {
		if (input >= 0 && input <= 4) {
			return true;
		}
		System.out.println("Invalid failureclass");
		return false;
	}

	public Integer getFailureclassInteger() {
		return failureclassInteger;
	}

	public void setFailureclassInteger(Integer failureclassInteger) {
		this.failureclassInteger = failureclassInteger;
	}

}
