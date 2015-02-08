package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserEquipment {
	
	@Id
	@Column(name="tac")
	private int TAC;
	@Column(name="marketing_name", length=60)
	private String marketingName;
	@Column(name="manufacturer", length=60)
	private String manufacturer;
	@Column(name="access_capability")
	private int accessCapability;
	@Column(name="model", length=45)
	private String model;
	@Column(name="ue_type")
	private int UEType;
	@Column(name="os")
	private int OS;
	@Column(name="input_mode")
	private int inputMode;
	
	public UserEquipment(){
		
	}
	
	public UserEquipment(int TAC, String marketingName, int accessCapability, 
			String model, int UEType, int OS, int inputMode){
		this.TAC = TAC;
		this.marketingName = marketingName;
		this.accessCapability = accessCapability;
		this.model = model;
		this.UEType = UEType;
		this.OS = OS;
		this.inputMode = inputMode;
	}
	
	public int getTAC() {
		return TAC;
	}
	public void setTAC(int tAC) {
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
	public int getAccessCapability() {
		return accessCapability;
	}
	public void setAccessCapability(int accessCapability) {
		this.accessCapability = accessCapability;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getUEType() {
		return UEType;
	}
	public void setUEType(int uEType) {
		UEType = uEType;
	}
	public int getOS() {
		return OS;
	}
	public void setOS(int oS) {
		OS = oS;
	}
	public int getInputMode() {
		return inputMode;
	}
	public void setInputMode(int inputMode) {
		this.inputMode = inputMode;
	}

}
