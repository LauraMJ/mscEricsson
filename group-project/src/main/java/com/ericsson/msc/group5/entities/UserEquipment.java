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
public class UserEquipment {

	@Id
	@Column(name = "type_allocation_code")
	private Integer typeAllocationCode;
	@Column(name = "marketing_name", length = 60)
	private String marketingName;
	@Column(name = "manufacturer", length = 60)
	private String manufacturer;
	@Column(name = "model", length = 45)
	private String model;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "accessCapabilityId")
	private AccessCapability accessCapabilityClass;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "ueTypeId")
	private UEType ueType;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "osId")
	private OS os;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "inputModeId")
	private InputMode inputModeClass;

	@OneToMany(mappedBy = "userEqipment", targetEntity = FailureTrace.class)
	private Collection <FailureTrace> failureTrace;

	public UserEquipment() {

	}

	public UserEquipment(Integer typeAllocationCode, String marketingName, String manufacturer, AccessCapability accessCapabilityClass, String model,
			UEType ueType, OS oS, InputMode inputModeClass) {
		super();
		this.typeAllocationCode = typeAllocationCode;
		this.marketingName = marketingName;
		this.manufacturer = manufacturer;
		this.accessCapabilityClass = accessCapabilityClass;
		this.model = model;
		this.ueType = ueType;
		this.os = oS;
		this.inputModeClass = inputModeClass;
	}

	public Integer getTypeAllocationCode() {
		return typeAllocationCode;
	}

	public void setTypeAllocationCode(Integer typeAllocationCode) {
		this.typeAllocationCode = typeAllocationCode;
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public UEType getUeType() {
		return ueType;
	}

	public void setUeType(UEType ueType) {
		this.ueType = ueType;
	}

	public AccessCapability getAccessCapabilityClass() {
		return accessCapabilityClass;
	}

	public void setAccessCapabilityClass(AccessCapability accessCapabilityClass) {
		this.accessCapabilityClass = accessCapabilityClass;
	}

	public OS getoS() {
		return os;
	}

	public void setoS(OS oS) {
		this.os = oS;
	}

	public InputMode getInputModeClass() {
		return inputModeClass;
	}

	public void setInputModeClass(InputMode inputModeClass) {
		this.inputModeClass = inputModeClass;
	}
}
