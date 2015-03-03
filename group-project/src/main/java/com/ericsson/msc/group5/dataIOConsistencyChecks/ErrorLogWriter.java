//package com.ericsson.msc.group5.dataIOConsistencyChecks;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import com.ericsson.msc.group5.dao.jpa.PersistenceUtil;
//import com.ericsson.msc.group5.dataIO.DataImport;
//import com.ericsson.msc.group5.entities.ErrorLog;
//
//public class ErrorLogWriter {
//
//	private static Date dateObj = new Date();
//	static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//
//	private final static String [] headings = {"Date / Time: ", " Event Id: ", " Failure Class: ", " UE Type: ", " Market: ", " Operator: ", " Cell Id: ",
//			" Duration: ", " Cause Code: ", " NE Version: ", " IMSI: ", " HIER3_ID: ", " HIER32_ID: ", " HIER321_ID: "};
//
//	public static void writeToErrorLog(HSSFRow row, String errorDescription) {
//		StringBuilder buffer = new StringBuilder(400);
//		String date = DataImport.formatDateAsString(row.getCell(0));
//		buffer.append(headings[0]);
//		buffer.append(date);
//
//		for (int i = 1; i < headings.length; i++) {
//			buffer.append(headings[i]);
//			HSSFCell cell = row.getCell(i);
//			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
//				buffer.append((int) cell.getNumericCellValue());
//			}
//			else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
//				buffer.append(cell.getStringCellValue());
//			}
//		}
//		PersistenceUtil.persist(new ErrorLog(dateFormat.format(dateObj), errorDescription, buffer.toString()));
//	}
//}
