package com.ericsson.msc.group5.dataAccessLayer;

import com.ericsson.msc.group5.entities.FailureClass;

public interface FailureClassDAO {

	public FailureClass getManagedFailureClass(int failureClass,
			String description);
}
