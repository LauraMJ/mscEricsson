package com.ericsson.msc.group5.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "os")
public class OS {

	@Id
	@Column(name = "os_id")
	private Integer osId;
	@Column(length = 45)
	private String os;

	@OneToMany(mappedBy = "os", targetEntity = UserEquipment.class)
	private Collection <UserEquipment> userEquipment;

	public Collection <UserEquipment> getUserEquipment() {
		return userEquipment;
	}

	public void setUserEquipment(Collection <UserEquipment> userEquipment) {
		this.userEquipment = userEquipment;
	}

	public OS() {

	}

	public OS(Integer osId, String os) {
		this.osId = osId;
		this.os = os;
	}

	public Integer getOsId() {
		return osId;
	}

	public void setOsId(Integer osId) {
		this.osId = osId;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
}