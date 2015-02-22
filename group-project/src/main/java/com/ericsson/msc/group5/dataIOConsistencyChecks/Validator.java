package com.ericsson.msc.group5.dataIOConsistencyChecks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import com.ericsson.msc.group5.dataIO.DataImport;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.FailureClass;
import com.ericsson.msc.group5.entities.FailureTrace;
import com.ericsson.msc.group5.entities.HierInfo;
import com.ericsson.msc.group5.entities.UserEquipment;

public class Validator {

	// public static void main(String [] args) {
	// new Validator();
	// }
	//
	// public Validator() {
	// validateDate("28/02/12");
	// }

	public static boolean validateFieldTypes(HSSFRow row, Object entity) {
		if (entity instanceof FailureTrace) {
			return validateFailureTraceRowFieldTypes(row);
		}
		if (entity instanceof UserEquipment) {
			return validateUserEquipmentRowFieldTypes(row);
		}
		if (entity instanceof EventCause) {
			return validateEventCauseRowFieldTypes(row);
		}
		if (entity instanceof FailureClass) {
			return validateFailureClassRowFieldTypes(row);
		}
		if (entity instanceof CountryCodeNetworkCode) {
			return validateCountryCodeNetworkCodeRowFieldTypes(row);
		}
		if (entity instanceof HierInfo) {
			return validateHierInfoRowFieldTypes(row);
		}
		return false;
	}

	private static boolean validateFailureTraceRowFieldTypes(HSSFRow row) {

		if (row.getCell(0).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(1).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(2).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(3).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(4).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(5).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(6).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(7).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(8).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(9).getCellType() != Cell.CELL_TYPE_STRING) {
			return false;
		}
		if (row.getCell(10).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(11).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(12).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(13).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		return true;
	}

	private static boolean validateUserEquipmentRowFieldTypes(HSSFRow row) {
		if (row.getCell(0).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(1).getCellType() != Cell.CELL_TYPE_STRING) {
			return false;
		}
		if (row.getCell(2).getCellType() != Cell.CELL_TYPE_STRING) {
			return false;
		}
		if (row.getCell(3).getCellType() != Cell.CELL_TYPE_STRING) {
			return false;
		}
		if (row.getCell(4).getCellType() != Cell.CELL_TYPE_STRING) {
			return false;
		}
		if (row.getCell(5).getCellType() != Cell.CELL_TYPE_STRING) {
			return false;
		}
		if (row.getCell(6).getCellType() != Cell.CELL_TYPE_STRING) {
			return false;
		}
		if (row.getCell(7).getCellType() != Cell.CELL_TYPE_STRING) {
			return false;
		}
		if (row.getCell(8).getCellType() != Cell.CELL_TYPE_STRING) {
			return false;
		}
		return true;
	}

	private static boolean validateCountryCodeNetworkCodeRowFieldTypes(
			HSSFRow row) {
		if (row.getCell(0).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(1).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(2).getCellType() != Cell.CELL_TYPE_STRING) {
			return false;
		}
		if (row.getCell(3).getCellType() != Cell.CELL_TYPE_STRING) {
			return false;
		}
		return false;
	}

	private static boolean validateHierInfoRowFieldTypes(HSSFRow row) {
		if (row.getCell(0).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(1).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(2).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return true;
		}
		return false;
	}

	private static boolean validateFailureClassRowFieldTypes(HSSFRow row) {
		if (row.getCell(0).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(1).getCellType() != Cell.CELL_TYPE_STRING) {
			return false;
		}
		return true;
	}

	private static boolean validateEventCauseRowFieldTypes(HSSFRow row) {
		if (row.getCell(0).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(1).getCellType() != Cell.CELL_TYPE_NUMERIC) {
			return false;
		}
		if (row.getCell(2).getCellType() != Cell.CELL_TYPE_STRING) {
			return false;
		}
		return true;
	}

	public static boolean validateFieldValues(HSSFRow row, Object entity) {
		if (entity instanceof FailureTrace) {
			return validateFailureTraceRowFieldValues(row);
		}
		return true;
	}

	private static boolean validateFailureTraceRowFieldValues(HSSFRow row) {

		if ( !validateDate(DataImport.formatDateAsString(row.getCell(0)))) {
			System.out.println("Date not ok");
			return false;
		}
		if ( !validateEventId(row.getCell(1).getNumericCellValue())) {
			System.out.println("Event ID not ok");
			return false;
		}
		if ( !validateFailureClass((int) row.getCell(2).getNumericCellValue())) {
			System.out.println("Failure class not ok");
			return false;
		}
		if ( !validateUEType((int) row.getCell(3).getNumericCellValue())) {
			System.out.println("Ue type not ok");
			return false;
		}
		if ( !validateMarket((int) row.getCell(4).getNumericCellValue())) {
			System.out.println("Market not ok");
			return false;
		}
		if ( !validateOperator((int) row.getCell(5).getNumericCellValue())) {
			System.out.println("operator not ok");
			return false;
		}
		if ( !validateCellId((int) row.getCell(6).getNumericCellValue())) {
			System.out.println("cell id not ok");
			return false;
		}
		if ( !validateDuration((int) row.getCell(7).getNumericCellValue())) {
			System.out.println("duration not ok");
			return false;
		}
		if ( !validateCauseCode((int) row.getCell(8).getNumericCellValue())) {
			System.out.println("cause code not ok");
			return false;
		}
		if ( !validateNEVersione(row.getCell(9).getStringCellValue())) {
			System.out.println("ne version not ok");
			return false;
		}
		if ( !validateIMSI((int) row.getCell(10).getNumericCellValue())) {
			System.out.println("imsi not ok");
			return false;
		}
		return true;
	}

	public static boolean validateDate(String dateString) {
		boolean isRealDate = checkIfValidDate(dateString);
		if (isRealDate)
			return checkIfFutureDate(dateString);
		return false;
	}

	private static boolean checkIfFutureDate(String dateString) {
		Calendar testDate = Calendar.getInstance();
		Calendar currentDate = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		formatter.setLenient(false);
		try {
			Date date = formatter.parse(dateString);
			testDate.setTime(date);
			if (testDate.after(currentDate)) {
				System.out.println("Date is in the future!");
				return false;
			}
		}
		catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Couldn't parse date");
			return false;
		}
		return true;
	}

	private static boolean checkIfValidDate(String dateString) {
		// Assumes the short year format refers to years in the 21st century
		int year = Integer.parseInt(dateString.substring(6, 8)) + 2000;
		int day = Integer.parseInt(dateString.substring(0, 2));
		int month = Integer.parseInt(dateString.substring(3, 5));

		if (day < 1 || day > 31 || month < 1 || month > 12) {
			System.out.println("Invalid date");
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
		System.out.println(numDaysInMonth);
		if (day > numDaysInMonth) {
			System.out.println("Invalid date");
			return false;
		}
		return true;
	}

	public static boolean validateEventId(double d) {
		if (d >= 4000 && d <= 5000) {
			return true;
		}
		System.out.println("Invalid eventId");
		return false;
	}

	public static boolean validateFailureClass(Integer input) {
		if (input >= 0 && input <= 4) {
			return true;
		}
		System.out.println("Invalid failureclass");
		return false;
	}

	public static boolean validateUEType(Integer input) {
		if (input >= 100000 && input <= 99999999) {
			return true;
		}
		System.out.println("Invalid ue type");
		return false;
	}

	public static boolean validateMarket(Integer input) {
		if (input >= 001 && input <= 999) {
			return true;
		}
		System.out.println("Invalid market");
		return false;
	}

	public static boolean validateOperator(Integer input) {
		if (input >= 01 && input <= 999) {
			return true;
		}
		System.out.println("Invalid operator");
		return false;
	}

	public static boolean validateCellId(Integer input) {
		if (input < 3843) {
			return true;
		}
		System.out.println("Invalid cell id");
		return false;
	}

	public static boolean validateDuration(Integer input) {
		// 60000 = 60 mins
		if (input < 3.6e+6) {
			return true;
		}
		System.out.println("Invalid duration");
		return false;
	}

	public static boolean validateCauseCode(Integer input) {
		if (input <= 27) {
			return true;
		}
		System.out.println("Invalid cause code");
		return false;
	}

	public static boolean validateNEVersione(String input) {
		if (input.equals("11B") || input.equals("12A")) {
			return true;
		}
		System.out.println("Invalid NE version");
		return false;
	}

	public static boolean validateIMSI(Integer input) {
		if (input <= 999999999999999L) {
			return true;
		}
		System.out.println("Invalid IMSI");
		return false;
	}

}
