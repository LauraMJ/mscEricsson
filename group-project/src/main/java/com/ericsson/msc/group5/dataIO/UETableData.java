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

	public UETableData() {
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
