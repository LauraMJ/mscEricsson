package com.ericsson.msc.group5.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Composite Key that helps to map/uniquely identify an operator using a combination of Country/Network.
 */
@Embeddable
public class CountryCodeNetworkCodeCK implements Serializable {

	private static final long serialVersionUID = -4973782694581889595L;

	private Integer networkCode;
	@ManyToOne
	@JoinColumn(name = "countryCode")
	private Country country;

	/**
	 * No-args constructor used by the JPA.
	 */
	public CountryCodeNetworkCodeCK() {
	}

	/**
	 * Create a composite key made up of a Country object and a networkCode. Together they can be used to uniquely identify an operator.
	 * 
	 * @param country
	 *            The first part of the composite key - a country with a unique country id.
	 * @param networkCode
	 *            The second part of the composite key - a non-unique network code id. This key is non-unique by itself, but forms a unique composite key when
	 *            combined with the country code.
	 */
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