package com.ericsson.msc.group5.dataAccessLayer;

import com.ericsson.msc.group5.entities.AccessCapability;

public interface AccessCapabilityDAO {

	public AccessCapability getManagedAccessCapability(String accessCapability);

	public void checkExist();
}
