// test commit
package com.ericsson.msc.group5.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CountryCodeNetworkCodeCK implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer networkCode;
	@ManyToOne
	@JoinColumn(name = "countryCode")
	private Country country;

	public CountryCodeNetworkCodeCK() {
	}

	public CountryCodeNetworkCodeCK(Country country, Integer networkCode) {
		this.country = country;
		this.networkCode = networkCode;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Integer getNetworkCode() {
		return networkCode;
	}

	public void setNetworkCode(Integer networkCode) {
		this.networkCode = networkCode;
	}
}