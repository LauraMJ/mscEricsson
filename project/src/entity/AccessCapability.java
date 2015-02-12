package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Access_Capability")
public class AccessCapability {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="access_capability_id")
	private Integer accessCapabilityId;

	@Column(name="access_capability", length=120)
	private String accessCapability;

	public AccessCapability() {
	}

	public AccessCapability(String accessCapability) {
		this.accessCapability = accessCapability;
	}

	public Integer getAccessCapabilityId() {
		return accessCapabilityId;
	}

	public void setAccessCapabilityId(Integer accessCapabilityId) {
		this.accessCapabilityId = accessCapabilityId;
	}

	public String getAccessCapability() {
		return accessCapability;
	}

	public void setAccessCapability(String accessCapability) {
		this.accessCapability = accessCapability;
	}
}
