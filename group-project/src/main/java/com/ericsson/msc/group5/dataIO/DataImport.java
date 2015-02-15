package com.ericsson.msc.group5.dataIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

public class DataImport {
	//Change this to where you've stored the base data spreadsheet
	private String path = "C:\\Users\\User\\Desktop\\";
	private String fileName = "test.xlsx";
	
	private FileInputStream fileInputStream;
	private Workbook workbook;
	private DateFormat date, time;
	private XSSFSheet worksheet;
	private XSSFCell dateTime, eventId, failureClass, ueType;
	private XSSFCell market, operator, cellId, duration, causeCode;
	private XSSFCell neVersion, imsi, hier3, hier32, hier321;
	private XSSFRow row;
	
	public DataImport(){
		try {
			//Read in the base data sheet from the excel file
			fileInputStream = new FileInputStream(path + fileName);
			workbook = new XSSFWorkbook(fileInputStream); 
			worksheet = (XSSFSheet) workbook.getSheetAt(0);
			
			//Set the locale to european (english locale is american so didn't use that)
			date = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
			time = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.UK);
			
			//Need to change this later to export to DB instead of printing
			printRow(1);
			System.out.println();
			printRow(5);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new DataImport();
	}

	private void printRow(int rowNum) {
		//Select the row in the table
		row = (XSSFRow)worksheet.getRow(rowNum);
		
		//Read each cell in the row
		dateTime = (XSSFCell) row.getCell(0);
		eventId = (XSSFCell) row.getCell(1);	
		failureClass  = (XSSFCell) row.getCell(2);
		ueType = (XSSFCell) row.getCell(3);
		market = (XSSFCell) row.getCell(4);
		operator = (XSSFCell) row.getCell(5);
		cellId = (XSSFCell) row.getCell(6);
		duration = (XSSFCell) row.getCell(7);
		causeCode = (XSSFCell) row.getCell(8);
		neVersion = (XSSFCell) row.getCell(9);
		imsi = (XSSFCell) row.getCell(10);
		hier3 = (XSSFCell) row.getCell(11);
		hier32 = (XSSFCell) row.getCell(12);
		hier321 = (XSSFCell) row.getCell(13);
		
		//Needed to handle nulls
		failureClass.setCellType(Cell.CELL_TYPE_STRING);
		causeCode.setCellType(Cell.CELL_TYPE_STRING);
		
		//Format the data in each cell appropriately
		Date dateTimeVal = dateTime.getDateCellValue();
		String dateString = date.format(dateTimeVal) +" " +time.format(dateTimeVal);
		Integer eventIdVal = (int) eventId.getNumericCellValue();
		String failureClassVal = failureClass.getStringCellValue();
		Integer ueTypeVal = (int) ueType.getNumericCellValue();
		Integer marketVal = (int) market.getNumericCellValue();
		Integer operatorVal = (int) operator.getNumericCellValue();
		Integer cellIdVal = (int) cellId.getNumericCellValue();
		Integer durationVal = (int) duration.getNumericCellValue();
		String causeCodeVal = causeCode.getStringCellValue();
		String neVersionVal = neVersion.getStringCellValue();
		Long imsiVal = (long) imsi.getNumericCellValue();
		Long hier3Val = (long) hier3.getNumericCellValue();
		Long hier32Val = (long) hier32.getNumericCellValue();
		Long hier321Val = (long) hier321.getNumericCellValue();
		
		/* Print out the data imported from one row in the base data.
		 * Will be replaced by inserts into the database
		 */
		System.out.println("dateTime: \t" + dateString);
		System.out.println("eventId: \t" + eventIdVal);
		System.out.println("failureClass: \t" + failureClassVal);
		System.out.println("ueType: \t" + ueTypeVal);
		System.out.println("market: \t" + marketVal);
		System.out.println("operator: \t" + operatorVal);
		System.out.println("cellId: \t" + cellIdVal);
		System.out.println("duration: \t" + durationVal);
		System.out.println("causeCode: \t" + causeCodeVal);
		System.out.println("neVersion: \t" + neVersionVal);
		System.out.println("imsi: \t\t" + imsiVal);
		System.out.println("hier3: \t\t" + hier3Val );
		System.out.println("hier32: \t" + hier32Val );
		System.out.println("hier321: \t" + hier321Val );
	}
}
