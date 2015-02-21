package com.ericsson.msc.group5.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "user_equipment_type")
public class UserEquipmentType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_equipment_type_id")
	private Integer userEquipmentTypeId;
	@Column(name = "user_equipment_type", length = 45)
	private String userEquipmentType;

	@OneToMany(mappedBy = "UserEquipmentType")
	private Collection <UserEquipment> userEquipment;

	public UserEquipmentType() {
	}

	public UserEquipmentType(String userEquipmentType) {
		this.userEquipmentType = userEquipmentType;
	}

	public Integer getUserEquipmentTypeId() {
		return userEquipmentTypeId;
	}

	public String getUserEquipmentType() {
		return userEquipmentType;
	}

	public void setUserEquipmentType(String userEquipmentType) {
		this.userEquipmentType = userEquipmentType;
	}
}