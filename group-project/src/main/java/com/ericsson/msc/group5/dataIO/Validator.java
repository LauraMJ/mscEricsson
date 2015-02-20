package com.ericsson.msc.group5.dataIO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Validator {

	private static DateFormat dateFormatter, timeFormatter;

	public static void main(String [] args) {
		new Validator();
	}

	public Validator() {
		// Set the locale to european
		dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
		timeFormatter = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.UK);
		validateDate("28/02/15");
	}

	public static boolean validateDate(String dateString) {
		boolean isRealDate = checkIfValidDate(dateString);
		if (isRealDate)
			checkIfFutureDate(dateString);
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
		if (input >= 100000 && input <= 29999999) {
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
