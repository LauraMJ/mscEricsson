package com.ericsson.msc.group5.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "country_code")
	private Integer countryCode; // PK
	@Column(length = 45)
	private String country;

//	@OneToMany(mappedBy = "country")
//	private Collection <CountryCodeNetworkCode> countryCodeNetworkCode;

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
