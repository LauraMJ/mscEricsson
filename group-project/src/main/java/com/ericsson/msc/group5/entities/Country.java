package com.ericsson.msc.group5.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Country {

	@Id
	private Integer mcc; // PK
	@Column(length = 45)
	private String country;

	@OneToMany(mappedBy = "country", targetEntity = MCC_MNC.class)
	private Collection <MCC_MNC> mccMnc;

	public Country() {
		//
	}

	public Country(Integer mcc, String country) {
		this.mcc = mcc;
		this.country = country;
	}
}
