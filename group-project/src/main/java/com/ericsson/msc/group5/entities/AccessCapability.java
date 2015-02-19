package com.ericsson.msc.group5.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Access Capability JPA entity. Maps an integer id to a string representing the
 * access capabilities of a user device.
 * 
 * @author szymon
 */
@Entity(name = "access_capability")
public class AccessCapability {

	@Id
	@Column(name = "access_capability_id")
	private Integer accessCapabilityId;
	@Column(name = "access_capability", length = 120)
	private String accessCapability;

	@OneToMany(mappedBy = "accessCapabilityClass", targetEntity = UserEquipment.class)
	private Collection <UserEquipment> userEquipment;

	public Collection <UserEquipment> getUserEquipment() {
		return userEquipment;
	}

	public void setUserEquipment(Collection <UserEquipment> userEquipment) {
		this.userEquipment = userEquipment;
	}

	/**
	 * No-args constructor used by the JPA.
	 */
	public AccessCapability() {
	}

	/**
	 * Create an Access Capability that maps to the supplied String. The integer
	 * id will be autoassigned on database write.
	 * 
	 * @param accessCapability
	 *            A String listing all access capabilities of the user device.
	 */
	public AccessCapability(Integer accessCapabilityId, String accessCapability) {
		this.accessCapabilityId = accessCapabilityId;
		this.accessCapability = accessCapability;
	}

	/**
	 * Get the unique identifier.
	 * 
	 * @return Unique db-assigned integer identifier, or null if the object has
	 *         not been persisted.
	 */
	public Integer getAccessCapabilityId() {
		return accessCapabilityId;
	}

	/**
	 * Set the unique identifier.
	 * 
	 * @param accessCapabilityId
	 *            a unique integer identifier.
	 */
	public void setAccessCapabilityId(Integer accessCapabilityId) {
		this.accessCapabilityId = accessCapabilityId;
	}

	/**
	 * Returns the String representing the access capabilities of the user
	 * device.
	 * 
	 * @return Access capabilities as a String.
	 */
	public String getAccessCapability() {
		return accessCapability;
	}

	/**
	 * Set the access capabilities String.
	 * 
	 * @param accessCapability
	 *            A string representing the access capabilities of the user
	 *            device.
	 */
	public void setAccessCapability(String accessCapability) {
		this.accessCapability = accessCapability;
	}
}
