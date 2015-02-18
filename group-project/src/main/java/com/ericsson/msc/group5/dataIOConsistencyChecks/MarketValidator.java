package com.ericsson.msc.group5.dataIOConsistencyChecks;

public class MarketValidator {

	private Integer marketInteger;

	public MarketValidator(Integer marketInteger) {
		validate(marketInteger);
	}

	public boolean validate(Integer input) {
		if (input >= 001 && input <= 999) {
			return true;
		}
		System.out.println("Invalid marketInteger");
		return false;
	}

	public Integer getMarketInteger() {
		return marketInteger;
	}

	public void setMarketInteger(Integer marketInteger) {
		this.marketInteger = marketInteger;
	}

}
