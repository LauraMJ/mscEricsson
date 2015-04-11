package com.ericsson.msc.group5.services;

import org.apache.poi.hssf.usermodel.HSSFRow;

public interface ValidatorService {

	public boolean validateFailureTraceRow(HSSFRow row);
	
	public boolean validateFailureTraceRowFieldValues(HSSFRow row);
}
