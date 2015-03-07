package com.ericsson.msc.group5.services;

import java.util.Collection;
import javax.ejb.Local;
import com.ericsson.msc.group5.entities.FailureClass;

/**
 * FailureClass service EJB interface.
 */
@Local
public interface FailureClassService {

	/**
	 * Retrieve a collection of all stored FailureClasses.
	 * 
	 * @return A collection of all FailureClass objects stored by the system.
	 */
	public Collection <FailureClass> getFailureClasses();

	/**
	 * Batch store a collection of FailureClasses. Optimized to handle large volumes of data efficiently.
	 * 
	 * @param failureClasses
	 *            A collection of FailureClass objects to store.
	 */
	public void addFailureClasses(Collection <FailureClass> failureClasses);
}
