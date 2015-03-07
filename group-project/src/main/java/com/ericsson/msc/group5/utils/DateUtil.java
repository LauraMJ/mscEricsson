package com.ericsson.msc.group5.utils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Date utility containing Date-related static methods.
 */
public class DateUtil {

	/**
	 * Convert a Date/Time object into an application-appropriate string of the UK form (24-hour clock): "dd/mm/yyyy hh:mm"
	 * 
	 * @param dateTime
	 *            The date object to covert into a string.
	 * @return Date time string in the UK locale ("dd/mm/yyyy hh:mm").
	 */
	public static String formatDateAsString(Date dateTime) {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
		DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.UK);

		String dateTimeString = dateFormat.format(dateTime) + " " + timeFormat.format(dateTime);
		return dateTimeString;
	}
}
