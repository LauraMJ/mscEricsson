package com.ericsson.msc.group5.services;

import java.util.Collection;

import javax.ejb.Local;

import com.ericsson.msc.group5.entities.UserEquipment;

@Local
public interface UserEquipmentService {

	public Collection <UserEquipment> getTypeAllocationCode();
	
	public void addTypeAllocationCode(Collection <UserEquipment> allocationCodes);

	public Collection <UserEquipment> getMarketingName();
	
	public void addMarketingName(Collection <UserEquipment> marketingNames);
	
	public Collection <UserEquipment> getManufacturer();
	
	public void addManufacturer(Collection <UserEquipment> manufacturers);
	
	public Collection <UserEquipment> getAccessCapability();
	
	public void addAccessCapability (Collection <UserEquipment> accessCapabilities);
	
	public Collection <UserEquipment> getModel();
	
	public void addModel (Collection <UserEquipment> models);
	
	public Collection <UserEquipment> getVendor();
	
	public void addVendor (Collection <UserEquipment> vendors);
	
	public Collection <UserEquipment> getUserEquipmentType();
	
	public void addUserEquipmentType (Collection <UserEquipment> userEquipmentTypes);
	
	public Collection <UserEquipment> getOperatingSystem();
	
	public void addOperatingSystem (Collection <UserEquipment> operatingSystems);
	
	public Collection <UserEquipment> getInputMode();
	
	public void addInputMode (Collection <UserEquipment> inputModes);
	
	public Collection <UserEquipment> getFailureTrace();
	
	public void addFailureTrace (Collection <UserEquipment> failureTraces);
	
	
}
