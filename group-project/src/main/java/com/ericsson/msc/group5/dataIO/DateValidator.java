package com.ericsson.msc.group5.dataIO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {

	private String dateString;
	private Pattern pattern;
	private Matcher matcher;

	public DateValidator() {
		validate("12/02/76");
	}

	public DateValidator(String dateString) {
		validate(dateString.substring(0, 8));
		this.dateString = dateString;
	}

	public boolean validate(String date) {
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

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public static void main(String [] args) {
		new DateValidator();
	}

}
