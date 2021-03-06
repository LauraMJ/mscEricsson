package com.ericsson.msc.group5.services;

import javax.ejb.Local;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * DataImport service EJB interface.
 */
@Local
public interface DataImportService {

	public void importSpreadsheet(HSSFWorkbook excelWorkbook);

	public String getTimestamp();

	public String getAddedCount();

	public String getRejectedCount();

	public void finaliseErrorLogEntry(String importType);
}
