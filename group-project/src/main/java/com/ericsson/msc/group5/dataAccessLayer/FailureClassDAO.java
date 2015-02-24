package com.ericsson.msc.group5.dataAccessLayer;

import javax.ejb.Local;
import com.ericsson.msc.group5.entities.FailureClass;

@Local
public interface FailureClassDAO {

	public FailureClass getManagedFailureClass(int failureClass,
			String description);
}
