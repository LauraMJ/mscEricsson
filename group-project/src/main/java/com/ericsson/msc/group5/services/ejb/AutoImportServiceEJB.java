package com.ericsson.msc.group5.services.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import com.ericsson.msc.group5.dao.CountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.dao.CountryDAO;
import com.ericsson.msc.group5.dao.EventCauseDAO;
import com.ericsson.msc.group5.dao.FailureClassDAO;
import com.ericsson.msc.group5.dao.FailureTraceDAO;
import com.ericsson.msc.group5.dao.UserEquipmentDAO;
import com.ericsson.msc.group5.entities.Country;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCodeCK;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.EventCauseCK;
import com.ericsson.msc.group5.entities.FailureClass;
import com.ericsson.msc.group5.entities.FailureTrace;
import com.ericsson.msc.group5.entities.UserEquipment;
import com.ericsson.msc.group5.services.AutoImportService;
import com.ericsson.msc.group5.services.ErrorLogWriterService;
import com.ericsson.msc.group5.utils.Validator;

@Stateless
@Local
@Path("/import")
public class AutoImportServiceEJB implements AutoImportService {

	@Inject
	private CountryCodeNetworkCodeDAO countryCodeNetworkCodeDAO;
	@Inject
	private CountryDAO countryDAO;
	@Inject
	private EventCauseDAO eventCauseDAO;
	@Inject
	private FailureClassDAO failureClassDAO;
	@Inject
	private FailureTraceDAO failureTraceDAO;
	@Inject
	private UserEquipmentDAO userEquipmentDAO;
	@EJB
	private ErrorLogWriterService errorLogWriterService;

	private HashMap <Integer, EventCause> eventCauseHashMap = new HashMap <>();
	private HashMap <Integer, FailureClass> failureClassHashMap = new HashMap <>();
	private HashMap <Integer, UserEquipment> userEquipmentHashMap = new HashMap <>();
	private HashMap <Integer, CountryCodeNetworkCode> countryCodeNetworkCodeHashMap = new HashMap <>();
	private HashMap <Integer, Country> countryHashMap = new HashMap <>();
	public static long duration = 0;

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

	public void importSpreadsheet(HSSFWorkbook excelWorkbook) {
		Collection <EventCause> existingEventCauseCollection = eventCauseDAO.getAllEventCauses();
		for (EventCause evCa : existingEventCauseCollection) {
			eventCauseHashMap.put(evCa.getCauseCodeEventIdCK().hashCode(), evCa);
		}
		Collection <FailureClass> existingFailureClassCollection = failureClassDAO.getAllFailureClasses();
		for (FailureClass fail : existingFailureClassCollection) {
			failureClassHashMap.put(fail.getFailureClass(), fail);
		}
		Collection <UserEquipment> existingUserEquipmentCollection = userEquipmentDAO.getAllUserEquipment();
		for (UserEquipment ue : existingUserEquipmentCollection) {
			userEquipmentHashMap.put(ue.getTypeAllocationCode(), ue);
		}
		Collection <CountryCodeNetworkCode> existingCountryCodeNetworkCodeCollection = countryCodeNetworkCodeDAO.getAllCountryCodeNetworkCodes();
		for (CountryCodeNetworkCode cn : existingCountryCodeNetworkCodeCollection) {
			countryCodeNetworkCodeHashMap.put(cn.getCountryCodeNetworkCode().hashCode(), cn);
		}
		Collection <Country> existingCountryCollection = countryDAO.getAllCountries();
		for (Country c : existingCountryCollection) {
			countryHashMap.put(c.getCountryCode(), c);
		}
		long start = System.currentTimeMillis();

		readExcelDocument(excelWorkbook);

		duration = System.currentTimeMillis() - start;
		System.out.printf("The import took %d milliseconds.\n", duration);
	}

	private void readExcelDocument(Workbook excelWorkbook) {
		readUserEquipmentDataSheet(excelWorkbook);
		readFailureClassDataSheet(excelWorkbook);
		readEventCauseDataSheet(excelWorkbook);
		readOperatorDataSheet(excelWorkbook);
		readBaseDataSheet(excelWorkbook);
	}

	private void readBaseDataSheet(Workbook excelWorkbook) {
		HSSFSheet baseDataWorksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.BASE_DATA_TABLE.getPageNumber());
		Collection <FailureTrace> failureTraceCollectionToFlush = new ArrayList <FailureTrace>();
		int numRows = baseDataWorksheet.getLastRowNum();
		Long initialPKValue = failureTraceDAO.getTotalNumberOfEntries() + 1;
		for (int i = 1; i <= numRows; i++) {
			HSSFRow row = (HSSFRow) baseDataWorksheet.getRow(i);
			try {
				Date dateTime = row.getCell(0).getDateCellValue();
				int eventId = (int) row.getCell(1).getNumericCellValue();
				int failureClass = (int) row.getCell(2).getNumericCellValue();
				int ueType = (int) row.getCell(3).getNumericCellValue();
				int countryCode = (int) row.getCell(4).getNumericCellValue();
				int networkCode = (int) row.getCell(5).getNumericCellValue();
				int cellId = (int) row.getCell(6).getNumericCellValue();
				int duration = (int) row.getCell(7).getNumericCellValue();
				int causeCode = (int) row.getCell(8).getNumericCellValue();
				String neVersion = row.getCell(9).getStringCellValue();
				String imsi = Long.toString((long) row.getCell(10).getNumericCellValue());
				String hier3 = Long.toString((long) row.getCell(11).getNumericCellValue());
				String hier32 = Long.toString((long) row.getCell(12).getNumericCellValue());
				String hier321 = Long.toString((long) row.getCell(13).getNumericCellValue());

				EventCause existingEventCause = null;
				if (eventCauseHashMap.containsKey(new EventCauseCK(causeCode, eventId).hashCode())) {
					existingEventCause = eventCauseHashMap.get(new EventCauseCK(causeCode, eventId).hashCode());
				}
				CountryCodeNetworkCode existingCountryCodeNetworkCode = null;
				Country existingCountry = null;
				if (countryHashMap.containsKey(countryCode)) {
					existingCountry = countryHashMap.get(countryCode);
					if (countryCodeNetworkCodeHashMap.containsKey(new CountryCodeNetworkCodeCK(existingCountry, networkCode).hashCode())) {
						existingCountryCodeNetworkCode = countryCodeNetworkCodeHashMap.get(new CountryCodeNetworkCodeCK(existingCountry, networkCode)
								.hashCode());
					}
				}
				FailureClass existingFailureClass = null;
				if (failureClassHashMap.containsKey(failureClass)) {
					existingFailureClass = failureClassHashMap.get(failureClass);
				}
				UserEquipment existingUserEquipment = null;
				if (userEquipmentHashMap.containsKey(ueType)) {
					existingUserEquipment = userEquipmentHashMap.get(ueType);
				}

				FailureTrace createdFailureTrace = new FailureTrace();
				createdFailureTrace.setFailureTraceId(initialPKValue);
				createdFailureTrace.setDateTime(dateTime);
				createdFailureTrace.setCountryCodeNetworkCode(existingCountryCodeNetworkCode);
				createdFailureTrace.setDuration(duration);
				createdFailureTrace.setCellId(cellId);
				createdFailureTrace.setEventCause(existingEventCause);
				createdFailureTrace.setFailureClass(existingFailureClass);
				createdFailureTrace.setHier3Id(hier3);
				createdFailureTrace.setHier32Id(hier32);
				createdFailureTrace.setHier321Id(hier321);
				createdFailureTrace.setIMSI(imsi);
				createdFailureTrace.setNeVersion(neVersion);
				createdFailureTrace.setUserEquipment(existingUserEquipment);

				if ( !Validator.validateFailureTrace(createdFailureTrace)) {
					errorLogWriterService.writeToErrorLog(row, "");
					continue;
				}

				failureTraceCollectionToFlush.add(createdFailureTrace);
				initialPKValue++;
			}
			catch (IllegalStateException e) {
				errorLogWriterService.writeToErrorLog(row, "");
			}
		}
		failureTraceDAO.batchInsertFailureTrace(failureTraceCollectionToFlush);
	}

	private void readEventCauseDataSheet(Workbook excelWorkbook) {
		HSSFSheet eventCauseWorksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.EVENT_CAUSE_TABLE.getPageNumber());
		int numRows = eventCauseWorksheet.getLastRowNum();
		HSSFRow row;
		EventCauseCK managedEventCauseCK;
		EventCause managedEventCause;
		Collection <EventCause> eventCauseCollection = new ArrayList <EventCause>();
		for (int i = 1; i <= numRows; i++) {
			row = (HSSFRow) eventCauseWorksheet.getRow(i);
			int causeCode = (int) row.getCell(0).getNumericCellValue();
			int eventId = (int) row.getCell(1).getNumericCellValue();
			String description = row.getCell(2).getStringCellValue();
			managedEventCauseCK = new EventCauseCK(causeCode, eventId);
			managedEventCause = new EventCause((managedEventCauseCK), description);

			if (eventCauseHashMap.get(managedEventCauseCK.hashCode()) != null) {
				continue;
			}
			eventCauseCollection.add(managedEventCause);
			eventCauseHashMap.put((Integer) managedEventCauseCK.hashCode(), managedEventCause);
		}
		eventCauseDAO.batchInsertEventCause(eventCauseCollection);
	}

	private void readFailureClassDataSheet(Workbook excelWorkbook) {
		HSSFSheet failureClassWorksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.FAILURE_CLASS_TABLE.getPageNumber());
		int numRows = failureClassWorksheet.getLastRowNum();
		HSSFRow row;
		FailureClass managedFailureClass;
		Collection <FailureClass> failureClassCollection = new ArrayList <FailureClass>();
		for (int i = 1; i <= numRows; i++) {
			row = failureClassWorksheet.getRow(i);
			int failureClassId = (int) row.getCell(0).getNumericCellValue();
			String description = row.getCell(1).getStringCellValue();

			if (failureClassHashMap.get((Integer) failureClassId) != null) {
				continue;
			}

			managedFailureClass = new FailureClass(failureClassId, description);

			failureClassCollection.add(managedFailureClass);
			failureClassHashMap.put((Integer) failureClassId, managedFailureClass);
		}
		failureClassDAO.batchInsertFailureClasses(failureClassCollection);
	}

	private void readUserEquipmentDataSheet(Workbook excelWorkbook) {
		HSSFSheet userEquipmentWorksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.UE_TABLE.getPageNumber());

		int numRows = userEquipmentWorksheet.getLastRowNum();
		HSSFRow row;
		UserEquipment managedUserEquipment;
		Collection <UserEquipment> userEquipmentCollection = new ArrayList <UserEquipment>();
		for (int i = 1; i <= numRows; i++) {
			row = (HSSFRow) userEquipmentWorksheet.getRow(i);

			int typeAllocationCode = (int) row.getCell(0).getNumericCellValue();
			String marketName = "";
			String manufacturer = row.getCell(2).getStringCellValue();
			String accessCapability = row.getCell(3).getStringCellValue();
			String model = "";
			String vendor = row.getCell(5).getStringCellValue();
			String ueType = row.getCell(6).getStringCellValue();
			String os = row.getCell(7).getStringCellValue();
			String inputMode = row.getCell(8).getStringCellValue();
			try {
				marketName = row.getCell(1).getStringCellValue();
				model = row.getCell(4).getStringCellValue();
			}
			catch (IllegalStateException e) {
				HSSFCell marketNameCell = row.getCell(1);
				marketNameCell.setCellType(Cell.CELL_TYPE_STRING);
				HSSFCell modelNameCell = row.getCell(4);
				modelNameCell.setCellType(Cell.CELL_TYPE_STRING);
				marketName = marketNameCell.getStringCellValue();
				model = modelNameCell.getStringCellValue();
			}

			if (userEquipmentHashMap.get((Integer) typeAllocationCode) != null) {
				continue;
			}

			managedUserEquipment = new UserEquipment(typeAllocationCode, marketName, manufacturer, accessCapability, model, vendor, ueType, os, inputMode);
			userEquipmentCollection.add(managedUserEquipment);
			userEquipmentHashMap.put((Integer) typeAllocationCode, managedUserEquipment);
		}
		userEquipmentDAO.batchInsertUserEquipment(userEquipmentCollection);
	}

	private void readOperatorDataSheet(Workbook excelWorkbook) {
		HSSFSheet operatorWorksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.MCC_MNC_TABLE.getPageNumber());
		// NEW IMPLEMENTATION
		int numRows = operatorWorksheet.getLastRowNum();
		HSSFRow row;
		Country managedCountry;
		CountryCodeNetworkCodeCK managedCountryCodeNetworkCodeCK;
		CountryCodeNetworkCode managedCountryCodeNetworkCode;
		Collection <CountryCodeNetworkCode> countryCodeNetworkCodeCollection = new ArrayList <CountryCodeNetworkCode>();
		for (int i = 1; i <= numRows; i++) {
			row = (HSSFRow) operatorWorksheet.getRow(i);
			int countryCode = (int) row.getCell(0).getNumericCellValue();
			int networkCode = (int) row.getCell(1).getNumericCellValue();
			String country = row.getCell(2).getStringCellValue();
			String operator = row.getCell(3).getStringCellValue();

			if (countryHashMap.get((Integer) countryCode) != null) {
				managedCountry = countryHashMap.get((Integer) countryCode);
			}
			else {
				managedCountry = new Country(countryCode, country);
				countryHashMap.put(countryCode, managedCountry);
			}

			managedCountryCodeNetworkCodeCK = new CountryCodeNetworkCodeCK(managedCountry, networkCode);

			if (countryCodeNetworkCodeHashMap.get(managedCountryCodeNetworkCodeCK.hashCode()) != null) {
				continue;
			}

			managedCountryCodeNetworkCode = new CountryCodeNetworkCode(managedCountryCodeNetworkCodeCK, operator);

			countryCodeNetworkCodeCollection.add(managedCountryCodeNetworkCode);

			countryCodeNetworkCodeHashMap.put((Integer) managedCountryCodeNetworkCodeCK.hashCode(), managedCountryCodeNetworkCode);
		}
		countryCodeNetworkCodeDAO.batchInsertCountryCodeNetworkCode(countryCodeNetworkCodeCollection);
	}
}
