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
import com.ericsson.msc.group5.services.DataImportService;
import com.ericsson.msc.group5.services.ErrorLogWriterService;
import com.ericsson.msc.group5.utils.Validator;

@Stateless
@Local
@Path("/import")
public class DataImportServiceEJB implements DataImportService {

	@Inject
	private CountryCodeNetworkCodeDAO countryCodeNetworkCodeDAO;
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

	private HashMap <EventCauseCK, EventCause> eventCauseHashMap = new HashMap <>();
	private HashMap <Integer, FailureClass> failureClassHashMap = new HashMap <>();
	private HashMap <Integer, UserEquipment> userEquipmentHashMap = new HashMap <>();
	private HashMap <CountryCodeNetworkCodeCK, CountryCodeNetworkCode> countryCodeNetworkCodeHashMap = new HashMap <>();

	private enum ExcelDataSheet {
		BASE_DATA_TABLE(0), EVENT_CAUSE_TABLE(1), FAILURE_CLASS_TABLE(2), UE_TABLE(
				3), MCC_MNC_TABLE(4);

		private final int pageNumber;

		ExcelDataSheet(int pageNumber) {
			this.pageNumber = pageNumber;
		}

		int getPageNumber() {
			return pageNumber;
		}
	}

	// @POST
	// @Path("{loc}")
	public void importSpreadsheet(HSSFWorkbook excelWorkbook) {

		Collection <EventCause> existingEventCauseCollection = eventCauseDAO
				.getAllEventCauses();
		for (EventCause evCa : existingEventCauseCollection) {
			eventCauseHashMap.put(evCa.getCauseCodeEventIdCK(), evCa);
		}
		Collection <FailureClass> existingFailureClassCollection = failureClassDAO
				.getAllFailureClasses();
		for (FailureClass fail : existingFailureClassCollection) {
			failureClassHashMap.put(fail.getFailureClass(), fail);
		}
		Collection <UserEquipment> existingUserEquipmentCollection = userEquipmentDAO
				.getAllUserEquipment();
		for (UserEquipment ue : existingUserEquipmentCollection) {
			userEquipmentHashMap.put(ue.getTypeAllocationCode(), ue);
		}
		Collection <CountryCodeNetworkCode> existingCountryCodeNetworkCodeCollection = countryCodeNetworkCodeDAO
				.getAllCountryCodeNetworkCodes();
		for (CountryCodeNetworkCode cn : existingCountryCodeNetworkCodeCollection) {
			countryCodeNetworkCodeHashMap.put(cn.getCountryCodeNetworkCode(),
					cn);
		}
		System.out.println("EventCauses: " + eventCauseHashMap.size()
				+ ", FailureClasses: " + failureClassHashMap.size()
				+ ", UserEquipments: " + userEquipmentHashMap.size()
				+ ", CountryCodeNetworkCodes: "
				+ countryCodeNetworkCodeHashMap.size());

		long start = System.currentTimeMillis();

		// Workbook excelWorkbook = new HSSFWorkbook(excelInputStream);
		readExcelDocument(excelWorkbook);

		long duration = System.currentTimeMillis() - start;
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
		HSSFSheet baseDataWorksheet = (HSSFSheet) excelWorkbook
				.getSheetAt(ExcelDataSheet.BASE_DATA_TABLE.getPageNumber());

		Collection <FailureClass> failureClasses = new ArrayList <FailureClass>(
				500);

		int numRows = baseDataWorksheet.getLastRowNum();
		for (int i = 1; i <= numRows; i++) {
			HSSFRow row = (HSSFRow) baseDataWorksheet.getRow(i);
			try {
				Date dateTime = row.getCell(0).getDateCellValue();
				int eventId = (int) row.getCell(1).getNumericCellValue();
				int failureClass = (int) row.getCell(2).getNumericCellValue();
				int ueType = (int) row.getCell(3).getNumericCellValue();
				int market = (int) row.getCell(4).getNumericCellValue();
				int operator = (int) row.getCell(5).getNumericCellValue();
				int cellId = (int) row.getCell(6).getNumericCellValue();
				int duration = (int) row.getCell(7).getNumericCellValue();
				int causeCode = (int) row.getCell(8).getNumericCellValue();
				String neVersion = row.getCell(9).getStringCellValue();
				String imsi = Long.toString((long) row.getCell(10)
						.getNumericCellValue());
				String hier3 = Long.toString((long) row.getCell(11)
						.getNumericCellValue());
				String hier32 = Long.toString((long) row.getCell(12)
						.getNumericCellValue());
				String hier321 = Long.toString((long) row.getCell(13)
						.getNumericCellValue());

				// String dateAsString = DateUtil.formatDateAsString(dateTime);
				// Timestamp dateAsTimestamp = DateUtil
				// .formatDateStringAsTimestamp(dateAsString);

				// NEW HASHMAP RETIEVAL
				EventCause existingEventCause = null;
				if (eventCauseHashMap.get(new EventCauseCK(causeCode, eventId)) != null) {
					existingEventCause = eventCauseHashMap
							.get(new EventCauseCK(causeCode, eventId));
				}
				CountryCodeNetworkCode existingCountryCodeNetworkCode = null;
				Country existingCountry = new Country();
				existingCountry.setCountryCode(operator);
				if (countryCodeNetworkCodeHashMap
						.get(new CountryCodeNetworkCodeCK(existingCountry,
								market)) != null) {
					existingCountryCodeNetworkCode = countryCodeNetworkCodeHashMap
							.get(new CountryCodeNetworkCodeCK(existingCountry,
									market));
				}
				FailureClass existingFailureClass = null;
				if (failureClassHashMap.get(failureClass) != null) {
					existingFailureClass = failureClassHashMap
							.get(failureClass);
				}
				UserEquipment existingUserEquipment = null;
				if (userEquipmentHashMap.get(ueType) != null) {
					existingUserEquipment = userEquipmentHashMap.get(ueType);
				}

				// ORIGINAL DB RETIEVAL
				// EventCause existingEventCause = eventCauseDAO.getEventCause(
				// causeCode, eventId);
				// CountryCodeNetworkCode existingCountryCodeNetworkCode =
				// countryCodeNetworkCodeDAO
				// .getCountryCodeNetworkCode(operator, market);
				// FailureClass existingFailureClass = failureClassDAO
				// .getFailureClass(failureClass);
				// UserEquipment existingUserEquipment = userEquipmentDAO
				// .getUserEquipment(ueType);

				// DateFormat myDF = new DateFormat();

				FailureTrace newFailureTrace = new FailureTrace();
				newFailureTrace.setDateTime(dateTime);
				newFailureTrace
						.setCountryCodeNetworkCode(existingCountryCodeNetworkCode);
				newFailureTrace.setDuration(duration);
				newFailureTrace.setCellId(cellId);
				newFailureTrace.setEventCause(existingEventCause);
				newFailureTrace.setFailureClass(existingFailureClass);
				newFailureTrace.setHier3Id(hier3);
				newFailureTrace.setHier32Id(hier32);
				newFailureTrace.setHier321Id(hier321);
				newFailureTrace.setIMSI(imsi);
				newFailureTrace.setNeVersion(neVersion);
				newFailureTrace.setUserEqipment(existingUserEquipment);

				if ( !Validator.validateFailureTrace(newFailureTrace)) {
					errorLogWriterService.writeToErrorLog(row, "");
					continue;
				}

				failureTraceDAO.insertFailureTrace(newFailureTrace);
			}
			catch (IllegalStateException e) {
				errorLogWriterService.writeToErrorLog(row, "");
			}
		}
	}

	private void readEventCauseDataSheet(Workbook excelWorkbook) {
		HSSFSheet eventCauseWorksheet = (HSSFSheet) excelWorkbook
				.getSheetAt(ExcelDataSheet.EVENT_CAUSE_TABLE.getPageNumber());
		// NEW IMPLEMENTATION
		int numRows = eventCauseWorksheet.getLastRowNum();
		HSSFRow row;
		EventCauseCK managedEventCauseCK;
		EventCause managedEventCause;
		for (int i = 1; i <= numRows; i++) {
			System.out.println("Doing eventCause row " + i);
			row = (HSSFRow) eventCauseWorksheet.getRow(i);
			int causeCode = (int) row.getCell(0).getNumericCellValue();
			int eventId = (int) row.getCell(1).getNumericCellValue();
			String description = row.getCell(2).getStringCellValue();
			managedEventCauseCK = new EventCauseCK(causeCode, eventId);
			managedEventCause = new EventCause((managedEventCauseCK),
					description);

			if (eventCauseHashMap.get(managedEventCauseCK) != null) {
				// ORIGINAL DB LOOKUP
				// if (eventCauseDAO.getEventCause(causeCode, eventId) != null){
				continue;
			}

			System.out.println("Event Cause causeCode: "
					+ managedEventCauseCK.getCauseCode());
			System.out.println("Event Cause eventId: "
					+ managedEventCauseCK.getEventId());
			System.out.println("Event Cause description: "
					+ managedEventCause.getDescription());

			eventCauseDAO.insertEventCause(managedEventCause);
			eventCauseHashMap.put(managedEventCauseCK, managedEventCause);
		}
		// ORIGINAL IMPLEMENTATION
		// int numRows = eventCauseWorksheet.getLastRowNum();
		// HSSFRow row;
		// for (int i = 1; i <= numRows; i++) {
		// row = (HSSFRow) eventCauseWorksheet.getRow(i);
		//
		// int causeCode = (int) row.getCell(0).getNumericCellValue();
		// int eventId = (int) row.getCell(1).getNumericCellValue();
		//
		// if (eventCauseDAO.getEventCause(causeCode, eventId) != null) {
		// continue;
		// }
		//
		// String description = row.getCell(2).getStringCellValue();
		// eventCauseDAO.insertEventCause(new EventCause((new EventCauseCK(
		// causeCode, eventId)), description));
		// }
	}

	private void readFailureClassDataSheet(Workbook excelWorkbook) {
		HSSFSheet failureClassWorksheet = (HSSFSheet) excelWorkbook
				.getSheetAt(ExcelDataSheet.FAILURE_CLASS_TABLE.getPageNumber());
		// NEW IMPLEMENTATION
		int numRows = failureClassWorksheet.getLastRowNum();
		HSSFRow row;
		FailureClass managedFailureClass;
		for (int i = 1; i <= numRows; i++) {
			System.out.println("Doing failureClass row " + i);
			row = failureClassWorksheet.getRow(i);
			int failureClassId = (int) row.getCell(0).getNumericCellValue();
			String description = row.getCell(1).getStringCellValue();

			if (failureClassHashMap.get((Integer) failureClassId) != null) {
				// ORIGINAL DB LOOKUP
				// if (failureClassDAO.getFailureClass(failureClass) != null) {
				continue;
			}

			managedFailureClass = new FailureClass(failureClassId, description);

			failureClassDAO.insertFailureClass(managedFailureClass);
			failureClassHashMap.put((Integer) failureClassId,
					managedFailureClass);
		}

		// ORIGINAL IMPLEMENTATION
		// int numRows = failureClassWorksheet.getLastRowNum();
		// for (int i = 1; i <= numRows; i++) {
		// HSSFRow row = failureClassWorksheet.getRow(i);
		//
		// int failureClass = (int) row.getCell(0).getNumericCellValue();
		// if (failureClassDAO.getFailureClass(failureClass) != null) {
		// continue;
		// }
		//
		// String description = row.getCell(1).getStringCellValue();
		// failureClassDAO.insertFailureClass(new FailureClass(failureClass,
		// description));
		// }
	}

	private void readUserEquipmentDataSheet(Workbook excelWorkbook) {
		HSSFSheet userEquipmentWorksheet = (HSSFSheet) excelWorkbook
				.getSheetAt(ExcelDataSheet.UE_TABLE.getPageNumber());

		// NEW IMPLEMENTATION
		int numRows = userEquipmentWorksheet.getLastRowNum();
		HSSFRow row;
		UserEquipment managedUserEquipment;
		for (int i = 1; i <= numRows; i++) {
			System.out.println("Doing userEquipment row " + i);
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
				// ORIGINAL DB LOOKUP
				// if (userEquipmentDAO.getUserEquipment(typeAllocationCode) !=
				// null) {
				continue;
			}

			managedUserEquipment = new UserEquipment(typeAllocationCode,
					marketName, manufacturer, accessCapability, model, vendor,
					ueType, os, inputMode);

			userEquipmentDAO.insertUserEquipment(managedUserEquipment);
			userEquipmentHashMap.put((Integer) typeAllocationCode,
					managedUserEquipment);
		}

		// ORIGINAL IMPLEMENTATION
		// int numRows = userEquipmentWorksheet.getLastRowNum();
		// HSSFRow row;
		// for (int i = 1; i <= numRows; i++) {
		// row = (HSSFRow) userEquipmentWorksheet.getRow(i);
		//
		// int typeAllocationCode = (int) row.getCell(0).getNumericCellValue();
		//
		// if (userEquipmentDAO.getUserEquipment(typeAllocationCode) != null) {
		// continue;
		// }
		//
		// String marketName = "";
		// String manufacturer = row.getCell(2).getStringCellValue();
		// String accessCapability = row.getCell(3).getStringCellValue();
		// String model = "";
		// String vendor = row.getCell(5).getStringCellValue();
		// String ueType = row.getCell(6).getStringCellValue();
		// String os = row.getCell(7).getStringCellValue();
		// String inputMode = row.getCell(8).getStringCellValue();
		//
		// try {
		// marketName = row.getCell(1).getStringCellValue();
		// model = row.getCell(4).getStringCellValue();
		// }
		// catch (IllegalStateException e) {
		// HSSFCell marketNameCell = row.getCell(1);
		// marketNameCell.setCellType(Cell.CELL_TYPE_STRING);
		// HSSFCell modelNameCell = row.getCell(4);
		// modelNameCell.setCellType(Cell.CELL_TYPE_STRING);
		// marketName = marketNameCell.getStringCellValue();
		// model = modelNameCell.getStringCellValue();
		// }
		//
		// userEquipmentDAO.insertUserEquipment(new UserEquipment(
		// typeAllocationCode, marketName, manufacturer,
		// accessCapability, model, vendor, ueType, os, inputMode));
		// }
	}

	private void readOperatorDataSheet(Workbook excelWorkbook) {
		HSSFSheet operatorWorksheet = (HSSFSheet) excelWorkbook
				.getSheetAt(ExcelDataSheet.MCC_MNC_TABLE.getPageNumber());
		// NEW IMPLEMENTATION
		int numRows = operatorWorksheet.getLastRowNum();
		HSSFRow row;
		CountryCodeNetworkCode managedCountryCodeNetworkCode;
		CountryCodeNetworkCodeCK managedCountryCodeNetworkCodeCK;
		for (int i = 1; i <= numRows; i++) {
			row = (HSSFRow) operatorWorksheet.getRow(i);
			System.out.println("Doing operator row " + i);
			int countryCode = (int) row.getCell(0).getNumericCellValue();
			int networkCode = (int) row.getCell(1).getNumericCellValue();
			String country = row.getCell(2).getStringCellValue();
			String operator = row.getCell(3).getStringCellValue();

			managedCountryCodeNetworkCodeCK = new CountryCodeNetworkCodeCK(
					new Country(countryCode, country), networkCode);

			if (countryCodeNetworkCodeHashMap
					.get(managedCountryCodeNetworkCodeCK) != null) {
				// ORIGINAL DB LOOKUP
				// if (countryCodeNetworkCodeDAO.getCountryCodeNetworkCode(
				// networkCode, countryCode) != null) {
				continue;
			}

			managedCountryCodeNetworkCode = new CountryCodeNetworkCode(
					managedCountryCodeNetworkCodeCK, operator);

			countryCodeNetworkCodeDAO
					.insertCountryCodeNetworkCode(managedCountryCodeNetworkCode);
			countryCodeNetworkCodeHashMap.put(managedCountryCodeNetworkCodeCK,
					managedCountryCodeNetworkCode);
		}

		// OLD IMPLEMENTATION
		// int numRows = operatorWorksheet.getLastRowNum();
		// HSSFRow row;
		// for (int i = 1; i <= numRows; i++) {
		// row = (HSSFRow) operatorWorksheet.getRow(i);
		//
		// int countryCode = (int) row.getCell(0).getNumericCellValue();
		// int networkCode = (int) row.getCell(1).getNumericCellValue();
		// if (countryCodeNetworkCodeDAO.getCountryCodeNetworkCode(
		// networkCode, countryCode) != null) {
		// continue;
		// }
		//
		// String country = row.getCell(2).getStringCellValue();
		// String operator = row.getCell(3).getStringCellValue();
		//
		// countryCodeNetworkCodeDAO
		// .insertCountryCodeNetworkCode(new CountryCodeNetworkCode(
		// new CountryCodeNetworkCodeCK(new Country(
		// countryCode, country), networkCode),
		// operator));
		// }
	}

}
