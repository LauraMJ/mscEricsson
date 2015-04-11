package com.ericsson.msc.group5.utils;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class DateUtilTest {

	@Test
	public void testFormatDateAsString() {
		Date test = new Date("11/01/2013 17:15");
		Date testTwo = new Date("12/01/2014 11:25");
		Date testThree = new Date("02/11/2003 09:15");
		
		assertEquals(DateUtil.formatDateAsString(test), "01/11/13 17:15");
		assertEquals(DateUtil.formatDateAsString(testTwo), "01/12/14 11:25");
		assertEquals(DateUtil.formatDateAsString(testThree), "11/02/03 09:15");
	}

	@Test
	public void testFormatDateStringAsTimestamp() {
		String testOne = new String("01/02/2014 13:30");
		System.out.println(DateUtil.formatDateStringAsTimestamp(testOne));
		assertEquals(DateUtil.formatDateStringAsTimestamp(testOne).toString(), "2014-02-01 13:30:00.0");
	}

}
