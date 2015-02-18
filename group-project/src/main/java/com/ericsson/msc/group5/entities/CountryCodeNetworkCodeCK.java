// test commit
package com.ericsson.msc.group5.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class CountryCodeNetworkCodeCK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer countryCode;
	private Integer networkCode;

	public CountryCodeNetworkCodeCK() {
		//
	}

	public CountryCodeNetworkCodeCK(Integer countryCode, Integer networkCode) {
		super();
		this.countryCode = countryCode;
		this.networkCode = networkCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryCodeNetworkCodeCK other = (CountryCodeNetworkCodeCK) obj;
		if (countryCode != other.countryCode)
			return false;
		if (networkCode != other.networkCode)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + countryCode;
		result = prime * result + networkCode;
		return result;
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getNetworkCode() {
		return networkCode;
	}

	public void setNetworkCode(Integer networkCode) {
		this.networkCode = networkCode;
	}
}