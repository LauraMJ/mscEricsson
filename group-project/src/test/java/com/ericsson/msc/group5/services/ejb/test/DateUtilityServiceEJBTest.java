package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import javax.ejb.EJB;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.DateUtilityService;

@RunWith(Arquillian.class)
public class DateUtilityServiceEJBTest {

	@EJB
	private DateUtilityService service;

	@Test
	public void testFormatDateAsString() {
		Date test = new Date("11/01/2013 17:15");
		Date testTwo = new Date("12/01/2014 11:25");
		Date testThree = new Date("02/11/2003 09:15");
		if (service == null) {
			System.out.println("Service null");
		}
		assertEquals(service.formatDateAsString(test), "01/11/13 17:15");
		assertEquals(service.formatDateAsString(testTwo), "01/12/14 11:25");
		assertEquals(service.formatDateAsString(testThree), "11/02/03 09:15");
	}

	@Test
	public void testFormatDateStringAsTimestamp() {
		String testOne = new String("01/02/2014 13:30");
		String testTwo = new String("02/02/2015 16:30");
		String testThree = new String("01/02/2010 13:30");
		System.out.println(testOne);
		System.out.println(service.formatDateStringAsTimestamp(testOne).toString());
		assertEquals(service.formatDateStringAsTimestamp(testOne).toString(), "2014-02-01 13:30:00.0");
		assertEquals(service.formatDateStringAsTimestamp(testTwo).toString(), "2015-02-02 16:30:00.0");
		assertEquals(service.formatDateStringAsTimestamp(testThree).toString(), "2010-02-01 13:30:00.0");
	}

	@Test
	public void testParseException() {
		String testNotDate = new String("hello");
		assertEquals(service.formatDateStringAsTimestamp(testNotDate), null);
	}

}
