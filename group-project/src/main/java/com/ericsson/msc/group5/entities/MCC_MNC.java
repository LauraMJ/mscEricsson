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
public class MCC_MNC {

	@Id
	@Column(name = "mcc_mnc")
	private MCC_MNCCK mccAndMnc;
	@Column(length = 100)
	private String operator;

	@OneToMany(mappedBy = "marketOperator", targetEntity = FailureTrace.class)
	private Collection <FailureTrace> failureTrace;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "mcc", referencedColumnName = "mcc", insertable = false, updatable = false)
	private Country country;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public MCC_MNC() {
		//
	}

	public MCC_MNC(MCC_MNCCK mccAndMnc, String operator) {
		this.mccAndMnc = mccAndMnc;
		this.operator = operator;
	}

}
