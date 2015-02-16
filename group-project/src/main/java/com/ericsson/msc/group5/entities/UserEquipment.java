package com.ericsson.msc.group5.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class UserEquipment {

	@Id
	@Column(name = "tac")
	private Integer TAC;
	@Column(name = "marketing_name", length = 60)
	private String marketingName;
	@Column(name = "manufacturer", length = 60)
	private String manufacturer;
	@Column(name = "access_capability", length = 100)

	private String accessCapability;
	@Column(name = "model", length = 45)
	private String model;

	@Column(name = "ue_type")
	private Integer UEType;

	@Column(name = "os")
	private Integer OS;

	@Column(name = "input_mode")
	private Integer inputMode;

	public UserEquipment() {

	}

	public UserEquipment(Integer TAC, String marketingName,
			String accessCapability, String model, Integer UEType, Integer OS,
			Integer inputMode) {
		this.TAC = TAC;
		this.marketingName = marketingName;
		this.accessCapability = accessCapability;
		this.model = model;
		this.UEType = UEType;
		this.OS = OS;
		this.inputMode = inputMode;
	}

	public Integer getTAC() {
		return TAC;
	}

	public void setTAC(Integer tAC) {
		TAC = tAC;
	}

	public String getMarketingName() {
		return marketingName;
	}

	public void setMarketingName(String marketingName) {
		this.marketingName = marketingName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getAccessCapability() {
		return accessCapability;
	}

	public void setAccessCapability(String accessCapability) {
		this.accessCapability = accessCapability;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getUEType() {
		return UEType;
	}

	public void setUEType(Integer uEType) {
		UEType = uEType;
	}

	public Integer getOS() {
		return OS;
	}

	public void setOS(Integer oS) {
		OS = oS;
	}

	public Integer getInputMode() {
		return inputMode;
	}

	public void setInputMode(Integer inputMode) {
		this.inputMode = inputMode;
	}
}
