package com.ericsson.msc.group5.services;

import javax.ejb.Local;

@Local
public interface DataImportService {
	public void importSpreadsheet(String location);
}
