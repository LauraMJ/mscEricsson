package com.ericsson.msc.group5.services;

import java.util.Collection;

import javax.ejb.Local;

import com.ericsson.msc.group5.entities.UserEquipment;

@Local
public interface UserEquipmentService {

	public Collection <UserEquipment> getTypeAllocationCode();
	
	public void addTypeAllocationCode(Collection <UserEquipment> allocationCodes);
}
