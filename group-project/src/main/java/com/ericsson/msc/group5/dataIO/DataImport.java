package com.ericsson.msc.group5.dataIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import com.ericsson.msc.group5.dao.jpa.PersistenceUtil;
import com.ericsson.msc.group5.dataAccessLayer.AccessCapabilityDAO;
import com.ericsson.msc.group5.dataAccessLayer.CountryCodeNetworkCodeDAO;
import com.ericsson.msc.group5.dataAccessLayer.CountryDAO;
// import com.ericsson.msc.group5.dataAccessLayer.CountryDAO;
import com.ericsson.msc.group5.dataAccessLayer.EventCauseDAO;
import com.ericsson.msc.group5.dataAccessLayer.FailureClassDAO;
import com.ericsson.msc.group5.dataAccessLayer.HierInfoDAO;
import com.ericsson.msc.group5.dataAccessLayer.InputModeDAO;
import com.ericsson.msc.group5.dataAccessLayer.OperatingSystemDAO;
import com.ericsson.msc.group5.dataAccessLayer.UserEquipmentDAO;
import com.ericsson.msc.group5.dataAccessLayer.UserEquipmentTypeDAO;
import com.ericsson.msc.group5.dataIOConsistencyChecks.ErrorLogWriter;
import com.ericsson.msc.group5.dataIOConsistencyChecks.Validator;
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

	private static String EXCEL_SHEET_LOCATION; // =
												// "C:\\Users\\Szymon\\Desktop\\sample.xls";
	// private static ArrayList <Object> validData = new ArrayList <>();
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
	private Date dateObj = new Date();

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

	public static String formatDateAsString(HSSFCell dateTime) {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
		DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.UK);
		Date dateTimeValue = dateTime.getDateCellValue();

		String dateTimeString = dateFormat.format(dateTimeValue) + " " + timeFormat.format(dateTimeValue);
		return dateTimeString;
	}

	public void begin(String location) {
		// long start = System.currentTimeMillis();
		getFileFromDialog();
		try (FileInputStream excelInputStream = new FileInputStream(EXCEL_SHEET_LOCATION)) {
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

	// public static void main(String [] args) {
	// new DataImport(EXCEL_SHEET_LOCATION);
	// }

	private void getFileFromDialog() {
		JFrame f = new JFrame();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		JFileChooser fileChooser = new JFileChooser();

		if (fileChooser.showOpenDialog(f) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			// try {
			// Desktop.getDesktop().open(file);
			// }
			// catch (IOException e) {
			// System.out.println("File not found");
			// }
			EXCEL_SHEET_LOCATION = file.getAbsolutePath();
		}

	}

	private void readExcelDocument(Workbook excelWorkbook) {
		readUserEquipmentDataSheet(excelWorkbook);
		readFailureClassDataSheet(excelWorkbook);
		readEventCauseDataSheet(excelWorkbook);
		readOperatorDataSheet(excelWorkbook);
		readBaseDataSheet(excelWorkbook);
	}

	private void readBaseDataSheet(Workbook excelWorkbook) {
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.BASE_DATA_TABLE.getPageNumber());

		int numRows = worksheet.getLastRowNum();
		HSSFRow row;
		HSSFCell dateTime, eventId, failureClass, ueType, market;
		HSSFCell operator, cellId, duration, causeCode;
		HSSFCell neVersion, imsi, hier3, hier32, hier321;
		for (int i = 1; i <= numRows; i++) {
			row = (HSSFRow) worksheet.getRow(i);

			// builder pattern?
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
			if (Validator.validateFieldTypes(row, new FailureTrace())) {
				try {
					if (Validator.validateFieldValues(row, new FailureTrace())) {
						EventCause ec = eventCauseDAO.getMangedEventCause((int) causeCode.getNumericCellValue(), (int) eventId.getNumericCellValue(), "");
						CountryCodeNetworkCode ccnc = countryCodeNetworkCodeDAO.getManagedCountryCodeNetworkCode((int) market.getNumericCellValue(),
								(int) operator.getNumericCellValue(), "", "");
						FailureClass fc = failureClassDAO.getManagedFailureClass((int) failureClass.getNumericCellValue(), "");
						HierInfo hi = hierInfoDAO.getManagedHierInfo((long) hier3.getNumericCellValue(), (long) hier32.getNumericCellValue(),
								(long) hier321.getNumericCellValue());
						UserEquipment ue = userEquipmentDAO.getManagedUserEquipment((int) ueType.getNumericCellValue());

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
				}
				catch (IllegalStateException e) {
//					e.printStackTrace();
					ErrorLogWriter.writeToErrorLog(row, new FailureTrace());
				}
			}
			else {
				ErrorLogWriter.writeToErrorLog(row, new FailureTrace());
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

			EventCause eventCauseObject = eventCauseDAO.getMangedEventCause((int) causeCode.getNumericCellValue(), (int) eventId.getNumericCellValue(),
					description.getStringCellValue());
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

			FailureClass failureClassObject = failureClassDAO
					.getManagedFailureClass((int) failureClass.getNumericCellValue(), description.getStringCellValue());
		}
	}

	private void readUserEquipmentDataSheet(Workbook excelWorkbook) {
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.UE_TABLE.getPageNumber());

		int numRows = worksheet.getLastRowNum();
		HSSFRow row;
		HSSFCell tac, marketName, manufacturer, accessCapability, model;
		HSSFCell vendor, ueType, os, inputMode;
		for (int i = 1; i <= numRows; i++) {
			row = (HSSFRow) worksheet.getRow(i);

			tac = row.getCell(0);
			EntityManager em = PersistenceUtil.createEM();
			if (em.find(UserEquipment.class, (int) tac.getNumericCellValue()) != null) {
				em.close();
				continue;
			}
			em.close();

			marketName = row.getCell(1);
			manufacturer = row.getCell(2);
			accessCapability = row.getCell(3);
			model = row.getCell(4);
			vendor = row.getCell(5); // ue.set TODO NO VENDOR FIELD!!!
			ueType = row.getCell(6);
			os = row.getCell(7);
			inputMode = row.getCell(8);
			AccessCapability readAccessCapability = accessCapabilityDAO.getManagedAccessCapability(accessCapability.getStringCellValue());
			UserEquipmentType readUserEquipmentType = userEquipmentTypeDAO.getManagedUserEquipmentType(ueType.getStringCellValue());
			OperatingSystem readOs = operatingSystemDAO.getManagedOs(os.getStringCellValue());
			InputMode readInputMode = inputModeDAO.getManagedInputMode(inputMode.getStringCellValue());

			try {
				UserEquipment ue = new UserEquipment((int) tac.getNumericCellValue(), marketName.getStringCellValue(), manufacturer.getStringCellValue(),
						readAccessCapability, model.getStringCellValue(), readUserEquipmentType, readOs, readInputMode);
				PersistenceUtil.persist(ue);
			}
			catch (IllegalStateException e) {
				// TODO: decide how to handle B63 and E63 - coin flip
				marketName.setCellType(Cell.CELL_TYPE_STRING);
				model.setCellType(Cell.CELL_TYPE_STRING);
				UserEquipment ue = new UserEquipment((int) tac.getNumericCellValue(), marketName.getStringCellValue(), manufacturer.getStringCellValue(),
						readAccessCapability, model.getStringCellValue(), readUserEquipmentType, readOs, readInputMode);
				PersistenceUtil.persist(ue);
			}
			// validData.add(ue);
			// if(validData.size() > 10)
			// em.persist(ue);
		}
	}

	private void readOperatorDataSheet(Workbook excelWorkbook) {
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.MCC_MNC_TABLE.getPageNumber());

		int numRows = worksheet.getLastRowNum();
		for (int i = 1; i <= numRows; i++) {
			HSSFRow row = (HSSFRow) worksheet.getRow(i);

			HSSFCell mcc = row.getCell(0);
			HSSFCell mnc = row.getCell(1);
			HSSFCell country = row.getCell(2);
			HSSFCell operator = row.getCell(3);

			CountryCodeNetworkCode countryNetworkCodeObject = countryCodeNetworkCodeDAO.getManagedCountryCodeNetworkCode((int) mcc.getNumericCellValue(),
					(int) mnc.getNumericCellValue(), country.getStringCellValue(), operator.getStringCellValue());
		}
	}

	public AccessCapabilityDAO getAccessCapabilityDAO() {
		return accessCapabilityDAO;
	}

	public void setAccessCapabilityDAO(AccessCapabilityDAO accessCapabilityDAO) {
		this.accessCapabilityDAO = accessCapabilityDAO;
	}

	public CountryCodeNetworkCodeDAO getCountryCodeNetworkCodeDAO() {
		return countryCodeNetworkCodeDAO;
	}

	public void setCountryCodeNetworkCodeDAO(CountryCodeNetworkCodeDAO countryCodeNetworkCodeDAO) {
		this.countryCodeNetworkCodeDAO = countryCodeNetworkCodeDAO;
	}

	public EventCauseDAO getEventCauseDAO() {
		return eventCauseDAO;
	}

	public void setEventCauseDAO(EventCauseDAO eventCauseDAO) {
		this.eventCauseDAO = eventCauseDAO;
	}

	public FailureClassDAO getFailureClassDAO() {
		return failureClassDAO;
	}

	public void setFailureClassDAO(FailureClassDAO failureClassDAO) {
		this.failureClassDAO = failureClassDAO;
	}

	public HierInfoDAO getHierInfoDAO() {
		return hierInfoDAO;
	}

	public void setHierInfoDAO(HierInfoDAO hierInfoDAO) {
		this.hierInfoDAO = hierInfoDAO;
	}

	public InputModeDAO getInputModeDAO() {
		return inputModeDAO;
	}

	public void setInputModeDAO(InputModeDAO inputModeDAO) {
		this.inputModeDAO = inputModeDAO;
	}

	public OperatingSystemDAO getOperatingSystemDAO() {
		return operatingSystemDAO;
	}

	public void setOperatingSystemDAO(OperatingSystemDAO operatingSystemDAO) {
		this.operatingSystemDAO = operatingSystemDAO;
	}

	public UserEquipmentDAO getUserEquipmentDAO() {
		return userEquipmentDAO;
	}

	public void setUserEquipmentDAO(UserEquipmentDAO userEquipmentDAO) {
		this.userEquipmentDAO = userEquipmentDAO;
	}

	public UserEquipmentTypeDAO getUserEquipmentTypeDAO() {
		return userEquipmentTypeDAO;
	}

	public void setUserEquipmentTypeDAO(UserEquipmentTypeDAO userEquipmentTypeDAO) {
		this.userEquipmentTypeDAO = userEquipmentTypeDAO;
	}
}