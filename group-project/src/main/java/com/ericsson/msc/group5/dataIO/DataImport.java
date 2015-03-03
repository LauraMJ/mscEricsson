package com.ericsson.msc.group5.dataIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import com.ericsson.msc.group5.dao.CountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.dao.EventCauseDAO;
import com.ericsson.msc.group5.dao.FailureClassDAO;
import com.ericsson.msc.group5.dao.FailureTraceDAO;
import com.ericsson.msc.group5.dao.UserEquipmentDAO;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.EventCauseCK;
import com.ericsson.msc.group5.entities.FailureClass;
import com.ericsson.msc.group5.entities.FailureTrace;
import com.ericsson.msc.group5.entities.UserEquipment;

// import com.ericsson.msc.group5.dataAccessLayer.CountryDAO;

public class DataImport {

	@Inject
	private CountryCodeNetworkCodeDAO countryCodeNetworkCodeDAO;
	// @Inject
	// private CountryDAO countryDAO;
	@Inject
	private EventCauseDAO eventCauseDAO;
	@Inject
	private FailureClassDAO failureClassDAO;
	@Inject
	private FailureTraceDAO failureTraceDAO;
	@Inject
	private UserEquipmentDAO userEquipmentDAO;

	private enum ExcelDataSheet {
		BASE_DATA_TABLE(0), EVENT_CAUSE_TABLE(1), FAILURE_CLASS_TABLE(2), UE_TABLE(3), MCC_MNC_TABLE(4);

		private final int pageNumber;

		ExcelDataSheet(int pageNumber) {
			this.pageNumber = pageNumber;
		}

		int getPageNumber() {
			return pageNumber;
		}
	}

	public void importSpreadsheet(String location) {
		long start = System.currentTimeMillis();
		try (FileInputStream excelInputStream = new FileInputStream(location)) {
			Workbook excelWorkbook = new HSSFWorkbook(excelInputStream);
			readExcelDocument(excelWorkbook);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found at" + location);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		long duration = System.currentTimeMillis() - start;
		System.out.printf("The import took %d milliseconds.\n", duration);
	}

	private void readExcelDocument(Workbook excelWorkbook) {
		// readUserEquipmentDataSheet(excelWorkbook);
		readFailureClassDataSheet(excelWorkbook);
		// readEventCauseDataSheet(excelWorkbook);
		// readOperatorDataSheet(excelWorkbook);
		// readBaseDataSheet(excelWorkbook);
	}

	private void readBaseDataSheet(Workbook excelWorkbook) {
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.BASE_DATA_TABLE.getPageNumber());

		Collection <FailureClass> failureClasses = new ArrayList <FailureClass>(500);

		int numRows = worksheet.getLastRowNum();
		HSSFRow row;
		HSSFCell dateTime, eventId, failureClass, ueType, market, operator, cellId, duration, causeCode, neVersion, imsi, hier3, hier32, hier321;
		for (int i = 1; i <= numRows; i++) {
			row = (HSSFRow) worksheet.getRow(i);
			// if ( !Validator.validateFailureTraceRowFieldTypes(row)) {
			// ErrorLogWriter.writeToErrorLog(row, "");
			// continue;
			// }
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

			String date = formatDateAsString(dateTime);
			try {
				EventCause existingEventCause = eventCauseDAO.getEventCause((int) causeCode.getNumericCellValue(), (int) eventId.getNumericCellValue());
				CountryCodeNetworkCode exisingCountryCodeNetworkCode = countryCodeNetworkCodeDAO.getCountryCodeNetworkCode(
						(int) operator.getNumericCellValue(), (int) market.getNumericCellValue());
				FailureClass existingFailureClass = failureClassDAO.getFailureClass((int) failureClass.getNumericCellValue());
				UserEquipment existingUserEquipment = userEquipmentDAO.getUserEquipment((int) ueType.getNumericCellValue());

				FailureTrace newFailureTrace = new FailureTrace();
				newFailureTrace.setDateTime(date);
				newFailureTrace.setCountryCodeNetworkCode(exisingCountryCodeNetworkCode);
				newFailureTrace.setDuration((int) duration.getNumericCellValue());
				newFailureTrace.setCellId((int) cellId.getNumericCellValue());
				newFailureTrace.setEventCause(existingEventCause);
				newFailureTrace.setFailureClass(existingFailureClass);
				// newFailureTrace.setHier3Id(hier3.getNumericCellValue());
				// newFailureTrace.setHier32Id(hier32Id);
				// newFailureTrace.setHier321Id(hier321Id);
				newFailureTrace.setIMSI(Long.toString((long) imsi.getNumericCellValue()));
				newFailureTrace.setNeVersion(neVersion.getStringCellValue());
				newFailureTrace.setUserEqipment(existingUserEquipment);

			}
			catch (IllegalStateException e) {
				// e.printStackTrace();
				// ErrorLogWriter.writeToErrorLog(row, "");
			}
		}
	}

	private void readEventCauseDataSheet(Workbook excelWorkbook) {
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.EVENT_CAUSE_TABLE.getPageNumber());

		int numRows = worksheet.getLastRowNum();
		HSSFRow row;
		HSSFCell causeCode, eventId, description;
		for (int i = 1; i <= numRows; i++) {
			row = (HSSFRow) worksheet.getRow(i);

			causeCode = row.getCell(0);
			eventId = row.getCell(1);
			description = row.getCell(2);

			if (eventCauseDAO.getEventCause((int) causeCode.getNumericCellValue(), (int) eventId.getNumericCellValue()) != null) {
				continue;
			}

			eventCauseDAO.insertEventCause(new EventCause((new EventCauseCK((int) causeCode.getNumericCellValue(), (int) eventId.getNumericCellValue())),
					description.getStringCellValue()));
		}
	}

	private void readFailureClassDataSheet(Workbook excelWorkbook) {
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.FAILURE_CLASS_TABLE.getPageNumber());

		int numRows = worksheet.getLastRowNum();
		HSSFRow row;
		HSSFCell failureClass, description;
		for (int i = 1; i <= numRows; i++) {
			row = (HSSFRow) worksheet.getRow(i);

			failureClass = row.getCell(0);
			description = row.getCell(1);

			if (failureClassDAO.getFailureClass((int) failureClass.getNumericCellValue()) != null)
				continue;

			failureClassDAO.insertFailureClass(new FailureClass((int) failureClass.getNumericCellValue(), description.getStringCellValue()));
		}
	}

	//
	// private void readUserEquipmentDataSheet(Workbook excelWorkbook) {
	// HSSFSheet worksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.UE_TABLE.getPageNumber());
	//
	// int numRows = worksheet.getLastRowNum();
	// HSSFRow row;
	// HSSFCell tac, marketName, manufacturer, accessCapability, model;
	// HSSFCell vendor, ueType, os, inputMode;
	// for (int i = 1; i <= numRows; i++) {
	// row = (HSSFRow) worksheet.getRow(i);
	//
	// tac = row.getCell(0);
	// EntityManager em = PersistenceUtil.createEM();
	// if (em.find(UserEquipment.class, (int) tac.getNumericCellValue()) != null) {
	// em.close();
	// continue;
	// }
	// em.close();
	//
	// marketName = row.getCell(1);
	// manufacturer = row.getCell(2);
	// accessCapability = row.getCell(3);
	// model = row.getCell(4);
	// vendor = row.getCell(5); // ue.set TODO NO VENDOR FIELD!!!
	// ueType = row.getCell(6);
	// os = row.getCell(7);
	// inputMode = row.getCell(8);
	// AccessCapability readAccessCapability = accessCapabilityDAO.getManagedAccessCapability(accessCapability.getStringCellValue());
	// UserEquipmentType readUserEquipmentType = userEquipmentTypeDAO.getManagedUserEquipmentType(ueType.getStringCellValue());
	// OperatingSystem readOs = operatingSystemDAO.getManagedOs(os.getStringCellValue());
	// InputMode readInputMode = inputModeDAO.getManagedInputMode(inputMode.getStringCellValue());
	//
	// try {
	// UserEquipment ue = new UserEquipment((int) tac.getNumericCellValue(), marketName.getStringCellValue(), manufacturer.getStringCellValue(),
	// readAccessCapability, model.getStringCellValue(), readUserEquipmentType, readOs, readInputMode);
	// PersistenceUtil.persist(ue);
	// }
	// catch (IllegalStateException e) {
	// // TODO: decide how to handle B63 and E63 - coin flip
	// marketName.setCellType(Cell.CELL_TYPE_STRING);
	// model.setCellType(Cell.CELL_TYPE_STRING);
	// UserEquipment ue = new UserEquipment((int) tac.getNumericCellValue(), marketName.getStringCellValue(), manufacturer.getStringCellValue(),
	// readAccessCapability, model.getStringCellValue(), readUserEquipmentType, readOs, readInputMode);
	// PersistenceUtil.persist(ue);
	// }
	// // validData.add(ue);
	// // if(validData.size() > 10)
	// // em.persist(ue);
	// }
	// }
	//
	// private void readOperatorDataSheet(Workbook excelWorkbook) {
	// HSSFSheet worksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.MCC_MNC_TABLE.getPageNumber());
	//
	// int numRows = worksheet.getLastRowNum();
	// for (int i = 1; i <= numRows; i++) {
	// HSSFRow row = (HSSFRow) worksheet.getRow(i);
	//
	// HSSFCell mcc = row.getCell(0);
	// HSSFCell mnc = row.getCell(1);
	// HSSFCell country = row.getCell(2);
	// HSSFCell operator = row.getCell(3);
	//
	// CountryCodeNetworkCode countryNetworkCodeObject = countryCodeNetworkCodeDAO.getManagedCountryCodeNetworkCode((int) mcc.getNumericCellValue(),
	// (int) mnc.getNumericCellValue(), country.getStringCellValue(), operator.getStringCellValue());
	// }
	// }

	private static String formatDateAsString(HSSFCell dateTimeHSSFCell) {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
		DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.UK);
		Date dateTime = dateTimeHSSFCell.getDateCellValue();

		String dateTimeString = dateFormat.format(dateTime) + " " + timeFormat.format(dateTime);
		return dateTimeString;
	}
}
