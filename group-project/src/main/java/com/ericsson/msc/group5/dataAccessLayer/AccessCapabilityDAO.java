package com.ericsson.msc.group5.dataAccessLayer;

import javax.ejb.Local;
import com.ericsson.msc.group5.entities.AccessCapability;

@Local
public interface AccessCapabilityDAO {

	public AccessCapability getManagedAccessCapability(String accessCapability);

}
