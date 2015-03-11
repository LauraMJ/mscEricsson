package com.ericsson.msc.group5.services;

import javax.ejb.Local;
/**
 * DataImport service EJB interface.
 */
@Local
public interface DataImportService {
	public void importSpreadsheet(String location);
}
