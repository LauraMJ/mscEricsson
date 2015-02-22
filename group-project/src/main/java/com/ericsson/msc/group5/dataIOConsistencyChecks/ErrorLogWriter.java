package com.ericsson.msc.group5.dataIOConsistencyChecks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFRow;
import com.ericsson.msc.group5.dataIO.DataImport;
import com.ericsson.msc.group5.entities.FailureTrace;

public class ErrorLogWriter {

	private static Date dateObj = new Date();
	static DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

	public static void writeToErrorLog(HSSFRow row, Object entityType) {
		String date = DataImport.formatDateAsString(row.getCell(0));
		if (entityType instanceof FailureTrace) {
			try (PrintWriter writer = new PrintWriter(new FileOutputStream(
					new File("tempErrorLog" + dateFormat.format(dateObj)
							+ ".txt"), true))) {
				writer.println("Invalid BASE_DATA entry found: ");
				writer.println("date: " + date);
				writer.println("eventId: "
						+ (long) row.getCell(1).getNumericCellValue());
				writer.println("failureClass: "
						+ row.getCell(2).getStringCellValue());
				writer.println("ueType: "
						+ (long) row.getCell(3).getNumericCellValue());
				writer.println("market: "
						+ (long) row.getCell(4).getNumericCellValue());
				writer.println("operator: "
						+ (long) row.getCell(5).getNumericCellValue());
				writer.println("cellId: "
						+ (long) row.getCell(6).getNumericCellValue());
				writer.println("duration: "
						+ (long) row.getCell(7).getNumericCellValue());
				writer.println("causeCode: "
						+ row.getCell(8).getStringCellValue());
				writer.println("neVersion: "
						+ row.getCell(9).getStringCellValue());
				writer.println("imsi: "
						+ (long) row.getCell(10).getNumericCellValue());
				writer.println("hier3: "
						+ (long) row.getCell(11).getNumericCellValue());
				writer.println("hier32: "
						+ (long) row.getCell(12).getNumericCellValue());
				writer.println("hier321: "
						+ (long) row.getCell(13).getNumericCellValue());
				writer.println();
			}
			catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
