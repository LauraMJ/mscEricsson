package com.ericsson.msc.group5.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * User equipment JPA entity. Uses a natural primary key (TAC - type allocation code) to store information about user devices.
 */
@Entity(name = "user_equipment")
public class UserEquipment {

	@Id
	@Column(name = "type_allocation_code")
	private Integer typeAllocationCode;
	@Column(name = "marketing_name", length = 60)
	private String marketingName;
	@Column(name = "manufacturer", length = 60)
	private String manufacturer;
	@Column(name = "access_capability", length = 200)
	private String accessCapability;
	@Column(name = "model", length = 70)
	private String model;
	@Column(name = "user_equipment_type", length = 45)
	private String userEquipmentType;
	@Column(name = "operating_system", length = 45)
	private String operatingSystem;
	@Column(name = "input_mode", length = 45)
	private String inputMode;

	@OneToMany(mappedBy = "userEqipment")
	private Collection <FailureTrace> failureTrace;

	/**
	 * No-args constructor used by the JPA.
	 */
	public UserEquipment() {
	}

	/**
	 * Create a user equipment entity. Stores information about user devices.
	 * 
	 * @param typeAllocationCode
	 *            A unique User Equipment identifier. A natural primary key.
	 * @param marketingName
	 *            The marketing name of the device.
	 * @param manufacturer
	 *            The manufacturer's name.
	 * @param accessCapability
	 *            String list of frequency bands supported by the user device.
	 * @param model
	 *            Model name of the device.
	 * @param userEquipmentType
	 *            Device type.
	 * @param operatingSystem
	 *            The OS present on the device.
	 * @param inputMode
	 *            Input modes accepted by the device.
	 */
	public UserEquipment(Integer typeAllocationCode, String marketingName, String manufacturer, String accessCapability, String model,
			String userEquipmentType, String operatingSystem, String inputMode) {
		super();
		this.typeAllocationCode = typeAllocationCode;
		this.marketingName = marketingName;
		this.manufacturer = manufacturer;
		this.accessCapability = accessCapability;
		this.model = model;
		this.userEquipmentType = userEquipmentType;
		this.operatingSystem = operatingSystem;
		this.inputMode = inputMode;
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

	public String getUserEquipmentType() {
		return userEquipmentType;
	}

	public void setUserEquipmentType(String userEquipmentType) {
		this.userEquipmentType = userEquipmentType;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getInputMode() {
		return inputMode;
	}

	public void setInputMode(String inputMode) {
		this.inputMode = inputMode;
	}

	public Collection <FailureTrace> getFailureTrace() {
		return failureTrace;
	}

	public void setFailureTrace(Collection <FailureTrace> failureTrace) {
		this.failureTrace = failureTrace;
	}

}
