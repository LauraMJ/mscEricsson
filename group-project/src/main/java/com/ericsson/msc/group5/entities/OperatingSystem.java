package com.ericsson.msc.group5.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "operating_system")
public class OperatingSystem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "operating_system_id")
	private Integer operatingSystemId;
	@Column(name = "operating_system", length = 45)
	private String operatingSystem;
	@OneToMany(mappedBy = "operatingSystem")
	private Collection <UserEquipment> userEquipment;

	public OperatingSystem() {
	}

	public OperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public Integer getOperatingSystemId() {
		return operatingSystemId;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
}
