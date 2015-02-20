package com.ericsson.msc.group5.dataIO;

public class Validator {

	public static void main(String [] args) {
		new Validator();
	}

	public Validator() {
		System.out.println(validateDate("29/02/15"));
	}

	public static boolean validateDate(String date) {
		// Assumes the short year format refers to years in the 21st century
		int year = Integer.parseInt(date.substring(6, 8)) + 2000;
		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(3, 5));

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

	public static boolean validateEventId(Integer input) {
		if (input >= 4000 && input <= 5000) {
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
