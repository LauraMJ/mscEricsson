package com.ericsson.msc.group5.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MCC_MNC {
	@Id
	@Column(name="mcc_mnc")
	private MCC_MNCCK mccAndMnc; 
	@Column(length=100)
	private String operator;

	public MCC_MNC() {
		//
	}
	
	public MCC_MNC(MCC_MNCCK mccAndMnc, String operator){
		this.mccAndMnc = mccAndMnc;
		this.operator = operator;
	}

}
