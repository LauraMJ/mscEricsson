package com.ericsson.msc.group5.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Country JPA entity. Matches an integer id to a String country name. Links to the CountryCodeNetworkCode in a 1:many fashion.
 */
@Entity
@Table(name = "country")
@NamedQueries({@NamedQuery(name = "findAllCountries", query = "SELECT c FROM Country c")})
public class Country implements Serializable{

	private static final long serialVersionUID = 529321768960748141L;
	
	@Id
	@Column(name = "country_code")
	private Integer countryCode;
	@Column(length = 45)
	private String country;

	/**
	 * No-args constructor used by the JPA.
	 */
	public Country() {
	}

	/**
	 * Create a country with the given country code (unique) and country name as a String.
	 * 
	 * @param countryCode
	 *            Integer id of the country.
	 * @param country
	 *            Name of the country as a String.
	 */
	public Country(Integer countryCode, String country) {
		this.countryCode = countryCode;
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		}
		else if ( !countryCode.equals(other.countryCode))
			return false;
		return true;
	}
}
