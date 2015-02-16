package com.ericsson.msc.group5.dataIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import com.ericsson.msc.group5.dao.jpa.PersistenceUtil;
import com.ericsson.msc.group5.entities.AccessCapability;

public class DataImport {
	//Change this to where you've stored the base data spreadsheet
	private String fileName = "C:\\Users\\User\\Desktop\\test.xlsx";
	private FileInputStream fileInputStream;
	private Workbook workbook;
	private DateFormat date, time;
	private XSSFSheet worksheet;
	private XSSFCell dateTime, eventId, failureClass, ueType;
	private XSSFCell market, operator, cellId, duration, causeCode;
	private XSSFCell neVersion, imsi, hier3, hier32, hier321;
	private XSSFRow row;
	private Date dateTimeVal;
	private Integer eventIdVal, ueTypeVal, marketVal, operatorVal, cellIdVal, durationVal;
	private String causeCodeVal, neVersionVal, failureClassVal;
	private String dateString;
	private Long imsiVal, hier3Val, hier32Val, hier321Val;
	private ArrayList<BaseData> baseDataRows = new ArrayList<BaseData>();
	
	public DataImport(){
		long start = System.currentTimeMillis();
		try {
			//Read in the base data sheet from the excel file
			fileInputStream = new FileInputStream(fileName);
			workbook = new XSSFWorkbook(fileInputStream); 
			worksheet = (XSSFSheet) workbook.getSheetAt(0);
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found at" +fileName);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//Set the locale to european
		date = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
		time = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.UK);
		
		//Import the data from the excel sheet
		System.out.println("Starting import");
		for(int i = 1; i <= 1000; i++){
				readRowFromSheet(i);
		}
		
		//Export the data to the database
		System.out.println("Data formatted, starting export to database");
		for(BaseData row:baseDataRows){
			sendToDB(row);
		}
		
		long duration = System.currentTimeMillis() - start;
		System.out.println("Data imported from " +fileName);
		System.out.println("Sent to table 'baseData'");
		System.out.println("Time taken: "+duration +" ms");
	}
	
	public static void main(String[] args) {
		new DataImport();
	}

	private void readRowFromSheet(int rowNum) {
		//Select the row in the table
		row = (XSSFRow)worksheet.getRow(rowNum);
		
		//Read each cell in the row
		dateTime =  row.getCell(0);
		eventId =  row.getCell(1);	
		failureClass  = row.getCell(2);
		ueType = row.getCell(3);
		market =  row.getCell(4);
		operator =  row.getCell(5);
		cellId = row.getCell(6);
		duration =  row.getCell(7);
		causeCode =  row.getCell(8);
		neVersion =  row.getCell(9);
		imsi = row.getCell(10);
		hier3 =  row.getCell(11);
		hier32 =  row.getCell(12);
		hier321 =  row.getCell(13);
		
		//Needed to handle nulls
		failureClass.setCellType(Cell.CELL_TYPE_STRING);
		causeCode.setCellType(Cell.CELL_TYPE_STRING);
		
		//Format the data in each cell appropriately
		formatInputs();
		baseDataRows.add(setRowData());
	}
	
	private void sendToDB(BaseData b){
		PersistenceUtil.persist(b);
	}
	
	private void formatInputs() {
		dateTimeVal = dateTime.getDateCellValue();
		dateString = date.format(dateTimeVal) +" " +time.format(dateTimeVal);
		eventIdVal = (int) eventId.getNumericCellValue();
		failureClassVal = failureClass.getStringCellValue();
	    ueTypeVal = (int) ueType.getNumericCellValue();
		marketVal = (int) market.getNumericCellValue();
		operatorVal = (int) operator.getNumericCellValue();
		cellIdVal = (int) cellId.getNumericCellValue();
		durationVal = (int) duration.getNumericCellValue();
		causeCodeVal = causeCode.getStringCellValue();
		neVersionVal = neVersion.getStringCellValue();
		imsiVal = (long) imsi.getNumericCellValue();
		hier3Val = (long) hier3.getNumericCellValue();
		hier32Val = (long) hier32.getNumericCellValue();
		hier321Val = (long) hier321.getNumericCellValue();
	}
	
	public BaseData setRowData(){
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
		return base;
	}
}