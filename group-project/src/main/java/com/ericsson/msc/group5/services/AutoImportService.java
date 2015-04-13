package com.ericsson.msc.group5.services;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface AutoImportService {

	void importSpreadsheet(HSSFWorkbook excelWorkbook);
}
