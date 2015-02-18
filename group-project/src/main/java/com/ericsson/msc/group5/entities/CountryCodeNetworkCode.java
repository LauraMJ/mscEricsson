package com.ericsson.msc.group5.entities;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CountryCodeNetworkCode {

	@Id
	@Column(name = "country_code_network_code")
	private CountryCodeNetworkCodeCK countryCodeNetworkCode;
	@Column(length = 100)
	private String operator;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "country_code", referencedColumnName = "country_code", insertable = false, updatable = false)
	private Country country;
	@OneToMany(mappedBy = "marketOperator", targetEntity = FailureTrace.class)
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
