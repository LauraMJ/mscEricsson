package com.ericsson.msc.group5.services;

import org.apache.poi.hssf.usermodel.HSSFRow;

public interface ValidatorService {

	public boolean validateFailureTraceRow(HSSFRow row);

	public boolean validateFailureTraceRowFieldTypes(HSSFRow row);

	public boolean validateFailureTraceRowFieldValues(HSSFRow row);

	public boolean validateEventId(Integer eventId);

	public boolean validateFailureClass(Integer failureClass);

	public boolean validateUEType(Integer input);

	public boolean validateMarket(Integer input);

	public boolean validateOperator(Integer input);

	public boolean validateCellId(Integer input);

	public boolean validateDuration(Integer input);

	public boolean validateCauseCode(Integer input);

	public boolean validateNEVersion(String input);

	public boolean validateIMSI(Long input);

	public boolean validateDate(String dateString);

	public boolean validateTime(String time);

	public boolean checkIfFutureDate(String dateString);

	public boolean checkIfValidDate(String dateString);

	public String correctLengthOfDateString(String dateString);

	public String getErrorDescriptionString();

	public void setErrorDescriptionString(String errorDescriptionString);

}
