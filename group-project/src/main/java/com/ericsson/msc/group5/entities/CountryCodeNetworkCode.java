package com.ericsson.msc.group5.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "country_code_network_code")
public class CountryCodeNetworkCode {

	@Id
	@Column(name = "country_code_network_code")
	private CountryCodeNetworkCodeCK countryCodeNetworkCode;
	@Column(length = 100)
	private String operator;
	@OneToMany(mappedBy = "countryCodeNetworkCode", targetEntity = FailureTrace.class)
	private Collection <FailureTrace> failureTrace;

	public CountryCodeNetworkCode() {
		//
	}

	public CountryCodeNetworkCode(
			CountryCodeNetworkCodeCK countryCodeNetworkCode, String operator) {
		this.countryCodeNetworkCode = countryCodeNetworkCode;
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}
