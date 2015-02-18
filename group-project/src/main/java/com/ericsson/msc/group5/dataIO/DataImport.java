package com.ericsson.msc.group5.dataIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import com.ericsson.msc.group5.dao.jpa.PersistenceUtil;

public class DataImport {

	// Change this to where you've stored the base data spreadsheet
	private String fileName = "C:\\Users\\Siobhan\\Desktop\\baseData.xls";
	private FileInputStream fileInputStream;
	private ArrayList <BaseData> baseDataRows = new ArrayList <BaseData>();
	private ArrayList <BaseData> eventCauseRows = new ArrayList <BaseData>();
	private ArrayList <BaseData> failureClassRows = new ArrayList <BaseData>();
	private ArrayList <BaseData> UETableRows = new ArrayList <BaseData>();
	private ArrayList <BaseData> MCC_MNCRows = new ArrayList <BaseData>();
	private Workbook workbook;
	private DateFormat date, time;
	private HSSFSheet worksheet;
	private HSSFCell dateTime, eventId, failureClass, ueType;
	private HSSFCell market, operator, cellId, duration, causeCode;
	private HSSFCell neVersion, imsi, hier3, hier32, hier321;
	private HSSFRow row;
	private Integer eventIdVal, ueTypeVal, marketVal, operatorVal;
	private Integer causeCodeVal, failureClassVal, cellIdVal, durationVal;
	private String dateString, neVersionVal;
	private Long imsiVal, hier3Val, hier32Val, hier321Val;
	private Date dateTimeVal;

	private int counter = 0;
	private HSSFCell description;

	enum Sheet {
		BASE, EVENT_CAUSE, FAILURE_CLASS, UE_TABLE, MCC_MNC_TABLE;
	}

	public DataImport() {
		long start = System.currentTimeMillis();

		try {
			// Read in the base data sheet from the excel file
			fileInputStream = new FileInputStream(fileName);
			workbook = new HSSFWorkbook(fileInputStream);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found at" + fileName);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		// Set the locale to european
		date = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
		time = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.UK);

		// Import the data from the excel sheet
		readBaseDataSheet();

		// Export the data to the database
		System.out.println("Data formatted, starting export to database");
		PersistenceUtil.persistList(baseDataRows);

		long duration = System.currentTimeMillis() - start;
		System.out.println("Data imported from " + fileName);
		System.out.println("Sent to table 'baseData'");
		System.out.println("Time taken: " + duration + " ms");
	}

	public static void main(String [] args) {
		new DataImport();
	}

	private void readBaseDataSheet() {
		worksheet = (HSSFSheet) workbook.getSheetAt(0);

		// Get the number of rows in the input file
		int numRows = worksheet.getLastRowNum();

		// Select the row in the table
		for (int i = 1; i <= numRows; i++) {
			row = (HSSFRow) worksheet.getRow(i);

			// Read each cell in the row
			dateTime = row.getCell(0);
			eventId = row.getCell(1);
			failureClass = row.getCell(2);
			ueType = row.getCell(3);
			market = row.getCell(4);
			operator = row.getCell(5);
			cellId = row.getCell(6);
			duration = row.getCell(7);
			causeCode = row.getCell(8);
			neVersion = row.getCell(9);
			imsi = row.getCell(10);
			hier3 = row.getCell(11);
			hier32 = row.getCell(12);
			hier321 = row.getCell(13);
			formatBaseData();
			setRowData(Sheet.BASE);
		}
	}

	private void readEventCauseSheet() {
		worksheet = (HSSFSheet) workbook.getSheetAt(1);

		// Get the number of rows in the input file
		int numRows = worksheet.getLastRowNum();

		// Select the row in the table
		for (int i = 1; i <= numRows; i++) {
			row = (HSSFRow) worksheet.getRow(i);

			// Read each cell in the row
			causeCode = row.getCell(0);
			eventId = row.getCell(1);
			description = row.getCell(2);

			// TODO formatBaseData();
			// TODO eventCauseRows.add(setRowData());
		}
	}

	private void readFailureClassSheet() {
		worksheet = (HSSFSheet) workbook.getSheetAt(2);

		// Get the number of rows in the input file
		int numRows = worksheet.getLastRowNum();

		// Select the row in the table
		for (int i = 1; i <= numRows; i++) {
			row = (HSSFRow) worksheet.getRow(i);

			// Read each cell in the row
			failureClass = row.getCell(0);
			description = row.getCell(1);

			// TODO formatBaseData();
			// TODO eventCauseRows.add(setRowData());
		}
	}

	private void readUETableSheet() {
		worksheet = (HSSFSheet) workbook.getSheetAt(3);

		// Get the number of rows in the input file
		int numRows = worksheet.getLastRowNum();

		// Select the row in the table
		for (int i = 1; i <= numRows; i++) {
			row = (HSSFRow) worksheet.getRow(i);

			// Read each cell in the row
			// causeCode = row.getCell(0);
			// eventId = row.getCell(1);
			// description = row.getCell(2);

			// TODO formatBaseData();
			// TODO eventCauseRows.add(setRowData());
		}
	}

	private void readMCC_MNCSheet() {
		worksheet = (HSSFSheet) workbook.getSheetAt(4);

		// Get the number of rows in the input file
		int numRows = worksheet.getLastRowNum();

		// Select the row in the table
		for (int i = 1; i <= numRows; i++) {
			row = (HSSFRow) worksheet.getRow(i);

			// Read each cell in the row
			// causeCode = row.getCell(0);
			// eventId = row.getCell(1);
			// description = row.getCell(2);

			// TODO formatBaseData();
			// TODO eventCauseRows.add(setRowData());
		}
	}

	private void formatBaseData() {
		boolean isNull = false;

		dateTimeVal = dateTime.getDateCellValue();
		dateString = date.format(dateTimeVal) + " " + time.format(dateTimeVal);
		eventIdVal = (int) eventId.getNumericCellValue();
		try {
			failureClassVal = (int) failureClass.getNumericCellValue();
		}
		catch (IllegalStateException e) {
			if ( !isNull) {
				isNull = true;
				counter++;
			}
			// System.out.println("null detected in Failure Class");
		}
		ueTypeVal = (int) ueType.getNumericCellValue();
		marketVal = (int) market.getNumericCellValue();
		operatorVal = (int) operator.getNumericCellValue();
		cellIdVal = (int) cellId.getNumericCellValue();
		durationVal = (int) duration.getNumericCellValue();
		try {
			causeCodeVal = (int) causeCode.getNumericCellValue();
		}
		catch (IllegalStateException e) {
			if ( !isNull) {
				isNull = true;
				counter++;
			}
			// System.out.println("null detected in Cause Code");
		}
		neVersionVal = neVersion.getStringCellValue();
		imsiVal = (long) imsi.getNumericCellValue();
		hier3Val = (long) hier3.getNumericCellValue();
		hier32Val = (long) hier32.getNumericCellValue();
		hier321Val = (long) hier321.getNumericCellValue();
	}

	public void setRowData(Sheet sheet) {
		switch (sheet) {
			case BASE:
				setBaseRowData();
				break;
			default:
				break;

		}
	}

	private void setBaseRowData() {
		BaseData base = new BaseData();
		base.setCauseCodeVal(causeCodeVal);
		base.setCellIdVal(cellIdVal);
		base.setDateString(dateString);
		base.setDurationVal(durationVal);
		base.setEventIdVal(eventIdVal);
		base.setFailureClassVal(failureClassVal);
		base.setHier321Val(hier321Val);
		base.setHier32Val(hier32Val);
		base.setHier3Val(hier3Val);
		base.setImsiVal(imsiVal);
		base.setMarketVal(marketVal);
		base.setNeVersionVal(neVersionVal);
		base.setOperatorVal(operatorVal);
		base.setUeTypeVal(ueTypeVal);
		baseDataRows.add(base);
	}

}