package com.ericsson.msc.group5.dataAccessLayer;

import com.ericsson.msc.group5.entities.OperatingSystem;

public interface OperatingSystemDAO {

	public OperatingSystem getManagedOs(String operatingSystem);
}
