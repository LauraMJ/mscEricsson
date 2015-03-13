package com.ericsson.msc.group5.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Country and Network code JPA entity. Uses an embedded natural composite key made up of the network and country identifiers to map to an operator.
 */
@Entity
@Table(name = "country_code_network_code")
@NamedQueries({@NamedQuery(name = "findAllCountryCodeNetworkCodes", query = "SELECT c FROM CountryCodeNetworkCode c")})
public class CountryCodeNetworkCode {

	@Id
	@Column(name = "country_code_network_code")
	private CountryCodeNetworkCodeCK countryCodeNetworkCode;
	@Column(length = 100)
	private String operator;

	@OneToMany(mappedBy = "countryCodeNetworkCode", targetEntity = FailureTrace.class)
	private Collection <FailureTrace> failureTrace;

	/**
	 * No-args constructor used by the JPA.
	 */
	public CountryCodeNetworkCode() {
	}

	/**
	 * Create a Country/Network combination given a composite key and String operator.
	 * 
	 * @param countryCodeNetworkCode
	 *            Composite Country/Network key.
	 * @param operator
	 *            Operator name for the Country/Network combination.
	 */
	public CountryCodeNetworkCode(CountryCodeNetworkCodeCK countryCodeNetworkCode, String operator) {
		this.countryCodeNetworkCode = countryCodeNetworkCode;
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public CountryCodeNetworkCodeCK getCountryCodeNetworkCode() {
		return countryCodeNetworkCode;
	}

	public void setCountryCodeNetworkCode(CountryCodeNetworkCodeCK countryCodeNetworkCode) {
		this.countryCodeNetworkCode = countryCodeNetworkCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryCodeNetworkCode == null) ? 0 : countryCodeNetworkCode.hashCode());
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
		CountryCodeNetworkCode other = (CountryCodeNetworkCode) obj;
		if (countryCodeNetworkCode == null) {
			if (other.countryCodeNetworkCode != null)
				return false;
		}
		else if ( !countryCodeNetworkCode.equals(other.countryCodeNetworkCode))
			return false;
		return true;
	}
}
