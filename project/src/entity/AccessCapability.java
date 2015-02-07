package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccessCapability {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accessCapabilityId;

	private String accessCapability;

	public AccessCapability() {
	}

	public AccessCapability(String accessCapability) {
		this.accessCapability = accessCapability;
	}

	public int getAccessCapabilityId() {
		return accessCapabilityId;
	}

	public void setAccessCapabilityId(int accessCapabilityId) {
		this.accessCapabilityId = accessCapabilityId;
	}

	public String getAccessCapability() {
		return accessCapability;
	}

	public void setAccessCapability(String accessCapability) {
		this.accessCapability = accessCapability;
	}
}
