package com.ericsson.msc.group5.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "user_equipment_type")
public class UserEquipmentType {

	@Id
	@Column(name = "user_equipment_type_id")
	private Integer userEquipmentTypeId;
	@Column(name = "user_equipment_type", length = 45)
	private String userEquipmentType;

	@OneToMany(mappedBy = "ueType", targetEntity = UserEquipment.class)
	private Collection <UserEquipment> userEquipment;

	public Collection <UserEquipment> getUserEquipment() {
		return userEquipment;
	}

	public void setUserEquipment(Collection <UserEquipment> userEquipment) {
		this.userEquipment = userEquipment;
	}

	public UserEquipmentType() {
	}

	public UserEquipmentType(Integer ueTypeId, String ueType) {
		this.userEquipmentTypeId = ueTypeId;
		this.userEquipmentType = ueType;
	}

	public Integer getUeTypeId() {
		return userEquipmentTypeId;
	}

	public void setUeTypeId(Integer ueTypeId) {
		this.userEquipmentTypeId = ueTypeId;
	}

	public String getUeType() {
		return userEquipmentType;
	}

	public void setUeType(String ueType) {
		this.userEquipmentType = ueType;
	}
}