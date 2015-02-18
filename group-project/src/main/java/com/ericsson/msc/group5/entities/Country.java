package com.ericsson.msc.group5.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Country {

	@Id
	private Integer countryCode; // PK
	@Column(length = 45)
	private String country;

	@OneToMany(mappedBy = "country", targetEntity = CountryCodeNetworkCode.class)
	private Collection <CountryCodeNetworkCode> counryCodeNetworkCode;

	public Country() {
		//
	}

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
}
