package com.ericsson.msc.group5.dataIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.persistence.EntityManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jboss.arquillian.core.api.annotation.Inject;
import com.ericsson.msc.group5.dao.jpa.PersistenceUtil;
import com.ericsson.msc.group5.dataAccessLayer.AccessCapabilityDAO;
import com.ericsson.msc.group5.dataAccessLayer.CountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.dataAccessLayer.CountryDAO;
import com.ericsson.msc.group5.dataAccessLayer.EventCauseDAO;
import com.ericsson.msc.group5.dataAccessLayer.FailureClassDAO;
import com.ericsson.msc.group5.dataAccessLayer.HierInfoDAO;
import com.ericsson.msc.group5.dataAccessLayer.InputModeDAO;
import com.ericsson.msc.group5.dataAccessLayer.OperatingSystemDAO;
import com.ericsson.msc.group5.dataAccessLayer.UserEquipmentDAO;
import com.ericsson.msc.group5.dataAccessLayer.UserEquipmentTypeDAO;
import com.ericsson.msc.group5.entities.AccessCapability;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.FailureClass;
import com.ericsson.msc.group5.entities.FailureTrace;
import com.ericsson.msc.group5.entities.HierInfo;
import com.ericsson.msc.group5.entities.InputMode;
import com.ericsson.msc.group5.entities.OperatingSystem;
import com.ericsson.msc.group5.entities.UserEquipment;
import com.ericsson.msc.group5.entities.UserEquipmentType;

public class DataImport {

	private static final String EXCEL_SHEET_LOCATION = "C:\\Users\\Harry\\Documents\\College\\Masters\\Semester 2\\Group Project\\data.xls";
	private static ArrayList <Object> validData = new ArrayList <>();
	@Inject
	private AccessCapabilityDAO accessCapabilityDAO;
	@Inject
	private CountryCodeNetworkCodeDAO countryCodeNetworkCodeDAO;
	@Inject
	private CountryDAO countryDAO;
	@Inject
	private EventCauseDAO eventCauseDAO;
	@Inject
	private FailureClassDAO failureClassDAO;
	@Inject
	private HierInfoDAO hierInfoDAO;
	@Inject
	private InputModeDAO inputModeDAO;
	@Inject
	private OperatingSystemDAO operatingSystemDAO;
	@Inject
	private UserEquipmentDAO userEquipmentDAO;
	@Inject
	private UserEquipmentTypeDAO userEquipmentTypeDAO;

	// private Integer eventIdVal, ueTypeVal, marketVal, operatorVal;
	// private Integer causeCodeVal, failureClassVal, cellIdVal, durationVal;
	// private String dateString, neVersionVal;
	// private Long imsiVal, hier3Val, hier32Val, hier321Val;

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

	public DataImport(String location) {
		// long start = System.currentTimeMillis();
		try (FileInputStream excelInputStream = new FileInputStream(
				EXCEL_SHEET_LOCATION)) {
			Workbook excelWorkbook = new HSSFWorkbook(excelInputStream);
			readExcelDocument(excelWorkbook);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found at" + EXCEL_SHEET_LOCATION);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		// long duration = System.currentTimeMillis() - start;
	}

	public static void main(String [] args) {
		new DataImport(EXCEL_SHEET_LOCATION);
	}

	private void readExcelDocument(Workbook excelWorkbook) {
		readUserEquipmentDataSheet(excelWorkbook);
		readFailureClassDataSheet(excelWorkbook);
		readEventCauseDataSheet(excelWorkbook);
		readOperatorDataSheet(excelWorkbook);
		readBaseDataSheet(excelWorkbook);
	}

	private void readBaseDataSheet(Workbook excelWorkbook) {
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook
				.getSheetAt(ExcelDataSheet.BASE_DATA_TABLE.getPageNumber());

		int numRows = worksheet.getLastRowNum();
		for (int i = 1; i <= numRows; i++) {
			HSSFRow row = (HSSFRow) worksheet.getRow(i);

			// builder pattern?
			HSSFCell dateTime = row.getCell(0);
			HSSFCell eventId = row.getCell(1);
			HSSFCell failureClass = row.getCell(2);
			HSSFCell ueType = row.getCell(3);
			HSSFCell market = row.getCell(4);
			HSSFCell operator = row.getCell(5);
			HSSFCell cellId = row.getCell(6);
			HSSFCell duration = row.getCell(7);
			HSSFCell causeCode = row.getCell(8);
			HSSFCell neVersion = row.getCell(9);
			HSSFCell imsi = row.getCell(10);
			HSSFCell hier3 = row.getCell(11);
			HSSFCell hier32 = row.getCell(12);
			HSSFCell hier321 = row.getCell(13);

			String date = formatDateAsString(dateTime);

			try {
				EventCause ec = eventCauseDAO.getMangedEventCause(
						(int) causeCode.getNumericCellValue(),
						(int) eventId.getNumericCellValue(), "");
				CountryCodeNetworkCode ccnc = countryCodeNetworkCodeDAO
						.getManagedCountryCodeNetworkCode(
								(int) market.getNumericCellValue(),
								(int) operator.getNumericCellValue(), "", "");
				FailureClass fc = failureClassDAO.getManagedFailureClass(
						(int) failureClass.getNumericCellValue(), "");
				HierInfo hi = hierInfoDAO.getManagedHierInfo(
						(long) hier3.getNumericCellValue(),
						(long) hier32.getNumericCellValue(),
						(long) hier321.getNumericCellValue());
				UserEquipment ue = userEquipmentDAO
						.getManagedUserEquipment((int) ueType
								.getNumericCellValue());

				FailureTrace ft = new FailureTrace();
				ft.setDateTime(date);
				ft.setCountryCodeNetworkCode(ccnc);
				ft.setDuration((int) duration.getNumericCellValue());
				ft.setCellId((int) cellId.getNumericCellValue());
				ft.setEventCause(ec);
				ft.setFailureClass(fc);
				ft.setHierInfo(hi);
				ft.setIMSI(Long.toString((long) imsi.getNumericCellValue()));
				ft.setNeVersion(neVersion.getStringCellValue());
				ft.setUserEqipment(ue);

				PersistenceUtil.persist(ft);
			}
			catch (IllegalStateException e) {
				e.printStackTrace();
			}

			// ??ft.setEventId(eventId);
		}
	}

	private void readEventCauseDataSheet(Workbook excelWorkbook) {
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook
				.getSheetAt(ExcelDataSheet.EVENT_CAUSE_TABLE.getPageNumber());

		int numRows = worksheet.getLastRowNum();
		for (int i = 1; i <= numRows; i++) {
			HSSFRow row = (HSSFRow) worksheet.getRow(i);

			HSSFCell causeCode = row.getCell(0);
			HSSFCell eventId = row.getCell(1);
			HSSFCell description = row.getCell(2);

			EventCause eventCauseObject = eventCauseDAO.getMangedEventCause(
					(int) causeCode.getNumericCellValue(),
					(int) eventId.getNumericCellValue(),
					description.getStringCellValue());
		}
	}

	private void readFailureClassDataSheet(Workbook excelWorkbook) {
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook
				.getSheetAt(ExcelDataSheet.FAILURE_CLASS_TABLE.getPageNumber());

		int numRows = worksheet.getLastRowNum();
		for (int i = 1; i <= numRows; i++) {
			HSSFRow row = (HSSFRow) worksheet.getRow(i);

			HSSFCell failureClass = row.getCell(0);
			HSSFCell description = row.getCell(1);

			FailureClass failureClassObject = failureClassDAO
					.getManagedFailureClass(
							(int) failureClass.getNumericCellValue(),
							description.getStringCellValue());
		}
	}

	private void readUserEquipmentDataSheet(Workbook excelWorkbook) {
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook
				.getSheetAt(ExcelDataSheet.UE_TABLE.getPageNumber());

		System.out.println("here");
		int numRows = worksheet.getLastRowNum();
		for (int i = 1; i <= numRows; i++) {
			System.out.println("current row is: " + i);
			HSSFRow row = (HSSFRow) worksheet.getRow(i);

			HSSFCell tac = row.getCell(0);
			EntityManager em = PersistenceUtil.createEM();
			if (em.find(UserEquipment.class, (int) tac.getNumericCellValue()) != null) {
				em.close();
				continue;
			}
			em.close();

			HSSFCell marketName = row.getCell(1);
			HSSFCell manufacturer = row.getCell(2);
			HSSFCell accessCapability = row.getCell(3);
			HSSFCell model = row.getCell(4);
			HSSFCell vendor = row.getCell(5); // ue.set TODO NO VENDOR FIELD!!!
			HSSFCell ueType = row.getCell(6);
			HSSFCell os = row.getCell(7);
			HSSFCell inputMode = row.getCell(8);
			AccessCapability readAccessCapability = accessCapabilityDAO
					.getManagedAccessCapability(accessCapability
							.getStringCellValue());
			UserEquipmentType readUserEquipmentType = userEquipmentTypeDAO
					.getManagedUserEquipmentType(ueType.getStringCellValue());
			OperatingSystem readOs = operatingSystemDAO.getManagedOs(os
					.getStringCellValue());
			InputMode readInputMode = inputModeDAO
					.getManagedInputMode(inputMode.getStringCellValue());

			try {
				UserEquipment ue = new UserEquipment(
						(int) tac.getNumericCellValue(),
						marketName.getStringCellValue(),
						manufacturer.getStringCellValue(),
						readAccessCapability, model.getStringCellValue(),
						readUserEquipmentType, readOs, readInputMode);
				PersistenceUtil.persist(ue);
			}
			catch (IllegalStateException e) {
				// TODO: decide how to handle B63 and E63 - coin flip

				// marketName.setCellType(Cell.CELL_TYPE_STRING); // TODO ??
				// model.setCellType(Cell.CELL_TYPE_STRING); // TODO ??
			}
			// validData.add(ue);
			// if(validData.size() > 10)
			// em.persist(ue);
		}
	}

	private void readOperatorDataSheet(Workbook excelWorkbook) {
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook
				.getSheetAt(ExcelDataSheet.MCC_MNC_TABLE.getPageNumber());

		int numRows = worksheet.getLastRowNum();
		for (int i = 1; i <= numRows; i++) {
			HSSFRow row = (HSSFRow) worksheet.getRow(i);

			HSSFCell mcc = row.getCell(0);
			HSSFCell mnc = row.getCell(1);
			HSSFCell country = row.getCell(2);
			HSSFCell operator = row.getCell(3);

			CountryCodeNetworkCode countryNetworkCodeObject = countryCodeNetworkCodeDAO
					.getManagedCountryCodeNetworkCode(
							(int) mcc.getNumericCellValue(),
							(int) mnc.getNumericCellValue(),
							country.getStringCellValue(),
							operator.getStringCellValue());
		}
	}

	private String formatDateAsString(HSSFCell dateTime) {
		DateFormat dateTimeFormat = DateFormat.getDateInstance(
				DateFormat.SHORT, Locale.UK);
		Date dateTimeValue = dateTime.getDateCellValue();

		String dateTimeString = dateTimeFormat.format(dateTimeValue) + " "
				+ dateTimeFormat.format(dateTimeValue);
		return dateTimeString;
	}
}
