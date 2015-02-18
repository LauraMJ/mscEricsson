package com.ericsson.msc.group5.dataIOConsistencyChecks;

public class OperatorValidator {

	private Integer operatorInteger;

	public OperatorValidator(Integer operatorInteger) {
		validate(operatorInteger);
	}

	private boolean validate(Integer input) {
		if (input >= 01 && input <= 999) {
			return true;
		}
		System.out.println("Invalid marketInteger");
		return false;
	}

	public Integer getOperatorInteger() {
		return operatorInteger;
	}

	public void setOperatorInteger(Integer operatorInteger) {
		this.operatorInteger = operatorInteger;
	}

}
