package com.ericsson.msc.group5.dataIO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.ericsson.msc.group5.entities.AccessCapability;
import com.ericsson.msc.group5.entities.InputMode;
import com.ericsson.msc.group5.entities.OS;
import com.ericsson.msc.group5.entities.UserEquipmentType;

@Entity
public class UETableData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Integer tac;
	private String marketName, manufacturer;
	private AccessCapability accessCapability;
	private String model, vendor;
	private UserEquipmentType ueType;
	private OS os;
	private InputMode inputMode;

	public UETableData() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getTac() {
		return tac;
	}

	public void setTac(Integer tac) {
		this.tac = tac;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public AccessCapability getAccessCapability() {
		return accessCapability;
	}

	public void setAccessCapability(AccessCapability accessCapability) {
		this.accessCapability = accessCapability;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public UserEquipmentType getUeType() {
		return ueType;
	}

	public void setUeType(UserEquipmentType ueType) {
		this.ueType = ueType;
	}

	public OS getOs() {
		return os;
	}

	public void setOs(OS os) {
		this.os = os;
	}

	public InputMode getInputMode() {
		return inputMode;
	}

	public void setInputMode(InputMode inputMode) {
		this.inputMode = inputMode;
	}

}
