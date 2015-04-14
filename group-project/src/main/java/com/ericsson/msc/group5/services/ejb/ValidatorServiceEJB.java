package com.ericsson.msc.group5.services.ejb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import com.ericsson.msc.group5.services.DateUtilityService;
import com.ericsson.msc.group5.services.ValidatorService;

public class ValidatorServiceEJB implements ValidatorService {

	@Inject
	private DateUtilityService dateUtilityService;

	private String errorDescriptionString = "";

	public boolean validateFailureTraceRow(HSSFRow row) {
		if (validateFailureTraceRowFieldTypes(row) && validateFailureTraceRowFieldValues(row)) {
			return true;
		}
		return false;
	}

	public boolean validateFailureTraceRowFieldTypes(HSSFRow row) {

		if (row.getCell(0).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			setErrorDescriptionString("Row field 0 not Numeric");
			return false;
		}
		if (row.getCell(1).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			setErrorDescriptionString("Row field 1 not Numeric");
			return false;
		}
		if (row.getCell(2).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			setErrorDescriptionString("Row field 2 not Numeric");
			return false;
		}
		if (row.getCell(3).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			setErrorDescriptionString("Row field 3 not Numeric");
			return false;
		}
		if (row.getCell(4).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			setErrorDescriptionString("Row field 4 not Numeric");
			return false;
		}
		if (row.getCell(5).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			setErrorDescriptionString("Row field 5 not Numeric");
			return false;
		}
		if (row.getCell(6).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			setErrorDescriptionString("Row field 6 not Numeric");
			return false;
		}
		if (row.getCell(7).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			setErrorDescriptionString("Row field 7 not Numeric");
			return false;
		}
		if (row.getCell(8).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			setErrorDescriptionString("Row field 8 not Numeric");
			return false;
		}
		if (row.getCell(9).getCellType() != Cell.CELL_TYPE_STRING) {
			setErrorDescriptionString("Row field 9 not a String");
			return false;
		}
		if (row.getCell(10).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			setErrorDescriptionString("Row field 10 not Numeric");
			return false;
		}
		if (row.getCell(11).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			setErrorDescriptionString("Row field 11 not Numeric");
			return false;
		}
		if (row.getCell(12).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			setErrorDescriptionString("Row field 12 not Numeric");
			return false;
		}
		if (row.getCell(13).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			setErrorDescriptionString("Row field 13 not Numeric");
			return false;
		}
		return true;
	}

	public boolean validateFailureTraceRowFieldValues(HSSFRow row) {
		if ( !validateDate(dateUtilityService.formatDateAsString(row.getCell(0).getDateCellValue()))) {
			setErrorDescriptionString("Date not ok");
			return false;
		}
		if ( !validateEventId((int) row.getCell(1).getNumericCellValue())) {
			setErrorDescriptionString("Event ID not ok");
			return false;
		}
		if ( !validateFailureClass((int) row.getCell(2).getNumericCellValue())) {
			setErrorDescriptionString("Failure class not ok");
			return false;
		}
		if ( !validateUEType((int) row.getCell(3).getNumericCellValue())) {
			setErrorDescriptionString("Ue type not ok");
			return false;
		}
		if ( !validateMarket((int) row.getCell(4).getNumericCellValue())) {
			setErrorDescriptionString("Market not ok");
			return false;
		}
		if ( !validateOperator((int) row.getCell(5).getNumericCellValue())) {
			setErrorDescriptionString("operator not ok");
			return false;
		}
		if ( !validateCellId((int) row.getCell(6).getNumericCellValue())) {
			setErrorDescriptionString("cell id not ok");
			return false;
		}
		if ( !validateDuration((int) row.getCell(7).getNumericCellValue())) {
			setErrorDescriptionString("duration not ok");
			return false;
		}
		if ( !validateCauseCode((int) row.getCell(8).getNumericCellValue())) {
			setErrorDescriptionString("cause code not ok");
			return false;
		}
		if ( !validateNEVersion(row.getCell(9).getStringCellValue())) {
			setErrorDescriptionString("ne version not ok");
			return false;
		}
		if ( !validateIMSI((long) row.getCell(10).getNumericCellValue())) {
			setErrorDescriptionString("imsi not ok");
			return false;
		}
		return true;
	}

	public boolean validateEventId(Integer eventId) {
		try {
			if (eventId == 4097) {
				return true;
			}
			if (eventId == 4098) {
				return true;
			}
			if (eventId == 4125) {
				return true;
			}
			if (eventId == 4106) {
				return true;
			}
		}
		catch (NullPointerException e) {
			return false;
		}
		return false;
	}

	public boolean validateFailureClass(Integer failureClass) {
		try {
			if (failureClass == 0) {
				return true;
			}
			if (failureClass == 1) {
				return true;
			}
			if (failureClass == 2) {
				return true;
			}
			if (failureClass == 3) {
				return true;
			}
			if (failureClass == 4) {
				return true;
			}
		}
		catch (NullPointerException e) {
			return false;
		}
		return false;
	}

	public boolean validateUEType(Integer input) {
		try {
			int numDigits = Integer.toString(input).length();
			if (numDigits >= 6 && numDigits <= 8) {
				return true;
			}
		}
		catch (NullPointerException e) {
			return false;
		}
		return false;
	}

	public boolean validateMarket(Integer input) {
		try {
			int numDigits = Integer.toString(input).length();
			if (numDigits == 3) {
				return true;
			}
		}
		catch (NullPointerException e) {
			return false;
		}
		return false;
	}

	public boolean validateOperator(Integer input) {
		try {
			int numDigits = Integer.toString(input).length();
			if (numDigits >= 1 && numDigits <= 3 && input >= 01 && input <= 999) {
				return true;
			}
		}
		catch (NullPointerException e) {
			return false;
		}
		return false;
	}

	public boolean validateCellId(Integer input) {
		try {
			if (input < 10000) {
				return true;
			}
		}
		catch (NullPointerException e) {
			return false;
		}
		return false;
	}

	public boolean validateDuration(Integer input) {
		try {
			if (input < 10000) {
				return true;
			}
		}
		catch (NullPointerException e) {
			return false;
		}
		return false;
	}

	public boolean validateCauseCode(Integer input) {
		try {
			if (input >= 0 && input <= 33) {
				return true;
			}
		}
		catch (NullPointerException e) {
			return false;
		}
		return false;
	}

	public boolean validateNEVersion(String input) {
		try {
			if (input.length() == 3) {
				return true;
			}
		}
		catch (NullPointerException e) {
			return false;
		}
		return false;
	}

	public boolean validateIMSI(Long input) {
		try {
			int numDigits = Long.toString(input).length();
			if (numDigits == 14 || numDigits == 15) {
				return true;
			}
		}
		catch (NullPointerException e) {
			return false;
		}
		return false;
	}

	public boolean validateDate(String dateString) {
		try {
			boolean isValidDate = checkIfValidDate(dateString);
			boolean isValidTime = validateTime(dateString);
			boolean isNotFutureDate = checkIfFutureDate(dateString);

			return isValidDate && isValidTime && isNotFutureDate;
		}
		catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * Validate time in 24 hours format with regular expression
	 * 
	 * @param time
	 *            time address for validation
	 * @return true valid time fromat, false invalid time format
	 */
	public boolean validateTime(String time) {
		String timePattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		Pattern pattern = Pattern
				.compile(timePattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(time);
		return matcher.find();
	}

	public boolean checkIfFutureDate(String dateString) {
		dateString = correctLengthOfDateString(dateString);
		dateString = dateString.substring(0, 6) + "20"
				+ dateString.substring(6);
		Calendar testDate = Calendar.getInstance();
		Calendar currentDate = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		try {
			Date date = formatter.parse(dateString);
			testDate.setTime(date);
			if (testDate.after(currentDate)) {
				return false;
			}
		}
		catch (ParseException e) {
			return false;
		}
		return true;
	}

	public boolean checkIfValidDate(String dateString) {
		dateString = correctLengthOfDateString(dateString);

		// Assumes the short year format refers to years in the 21st century
		int year = Integer.parseInt(dateString.substring(6, 8)) + 2000;
		int day = Integer.parseInt(dateString.substring(0, 2));
		int month = Integer.parseInt(dateString.substring(3, 5));

		if (day < 1 || day > 31 || month < 1 || month > 12) {
			return false;
		}

		int numDaysInMonth = 0;
		switch (month) {
			case 4:
			case 6:
			case 9:
			case 11:
				numDaysInMonth = 30;
				break;
			case 2:
				if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
					numDaysInMonth = 29;
				else
					numDaysInMonth = 28;
				break;
			default:
				numDaysInMonth = 31;
				break;
		}
		if (day > numDaysInMonth) {
			return false;
		}
		return true;
	}

	public String correctLengthOfDateString(String dateString) {
		// Pad single digit day with a zero
		if ((int) dateString.charAt(1) < 48 || (int) dateString.charAt(1) > 57) {
			String temp = dateString;
			dateString = new String();
			dateString = "0" + temp;
		}
		// Pad single digit month with a zero
		if ((int) dateString.charAt(4) < 48 || (int) dateString.charAt(4) > 57) {
			String temp = dateString;
			dateString = new String();
			dateString = temp.substring(0, 3) + "0" + temp.substring(3);
		}

		return dateString;
	}

	public String getErrorDescriptionString() {
		return errorDescriptionString;
	}

	public void setErrorDescriptionString(String errorDescriptionString) {
		this.errorDescriptionString = errorDescriptionString;
	}

}
