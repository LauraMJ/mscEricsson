package com.ericsson.msc.group5.dataAccessLayer;

import javax.ejb.Local;
import com.ericsson.msc.group5.entities.OperatingSystem;

@Local
public interface OperatingSystemDAO {

	public OperatingSystem getManagedOs(String operatingSystem);
}
