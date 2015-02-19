package com.ericsson.msc.group5.dataIO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UETableData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Integer tac;
	private String marketName, manufacturer, accessCapability;
	private String model, vendor, ueType, os, inputMode;

	public UETableData() {
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the vendor
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * @param vendor
	 *            the vendor to set
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return the ueType
	 */
	public String getUeType() {
		return ueType;
	}

	/**
	 * @param ueType
	 *            the ueType to set
	 */
	public void setUeType(String ueType) {
		this.ueType = ueType;
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os
	 *            the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the inputMode
	 */
	public String getInputMode() {
		return inputMode;
	}

	/**
	 * @param inputMode
	 *            the inputMode to set
	 */
	public void setInputMode(String inputMode) {
		this.inputMode = inputMode;
	}

	/**
	 * @return the tac
	 */
	public Integer getTac() {
		return tac;
	}

	/**
	 * @param tac
	 *            the tac to set
	 */
	public void setTac(Integer tac) {
		this.tac = tac;
	}

	/**
	 * @return the marketName
	 */
	public String getMarketName() {
		return marketName;
	}

	/**
	 * @param marketName
	 *            the marketName to set
	 */
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the accessCapability
	 */
	public String getAccessCapability() {
		return accessCapability;
	}

	/**
	 * @param accessCapability
	 *            the accessCapability to set
	 */
	public void setAccessCapability(String accessCapability) {
		this.accessCapability = accessCapability;
	}

}
