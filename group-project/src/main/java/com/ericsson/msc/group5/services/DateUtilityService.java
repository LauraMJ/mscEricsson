package com.ericsson.msc.group5.services;

import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.Local;

@Local
public interface DateUtilityService {

	String formatDateAsString(Date dateCellValue);

	Timestamp formatDateStringAsTimestamp(String inputDateString);

}
