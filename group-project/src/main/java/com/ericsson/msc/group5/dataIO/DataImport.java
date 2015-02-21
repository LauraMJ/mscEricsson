package com.ericsson.msc.group5.dataIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import com.ericsson.msc.group5.dao.jpa.PersistenceUtil;
import com.ericsson.msc.group5.entities.AccessCapability;
import com.ericsson.msc.group5.entities.Country;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCode;
import com.ericsson.msc.group5.entities.CountryCodeNetworkCodeCK;
import com.ericsson.msc.group5.entities.EventCause;
import com.ericsson.msc.group5.entities.EventCauseCK;
import com.ericsson.msc.group5.entities.FailureClass;
import com.ericsson.msc.group5.entities.FailureTrace;
import com.ericsson.msc.group5.entities.HierInfo;
import com.ericsson.msc.group5.entities.InputMode;
import com.ericsson.msc.group5.entities.OS;
import com.ericsson.msc.group5.entities.UserEquipment;
import com.ericsson.msc.group5.entities.UserEquipmentType;

public class DataImport {

	private static final String EXCEL_SHEET_LOCATION = "C:\\Users\\Szymon\\Desktop\\sample.xls";
	private static ArrayList <Object> validData = new ArrayList <>();

	// private Integer eventIdVal, ueTypeVal, marketVal, operatorVal;
	// private Integer causeCodeVal, failureClassVal, cellIdVal, durationVal;
	// private String dateString, neVersionVal;
	// private Long imsiVal, hier3Val, hier32Val, hier321Val;

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

	public DataImport(String location) {
		// long start = System.currentTimeMillis();
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
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.BASE_DATA_TABLE.getPageNumber());

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
				EventCause ec = getManagedEventCause((int) causeCode.getNumericCellValue(), (int) eventId.getNumericCellValue(), "");
				CountryCodeNetworkCode ccnc = getManagedCountryCodeNetworkCode((int) market.getNumericCellValue(), (int) operator.getNumericCellValue(), "", "");
				FailureClass fc = getManagedFailureClass((int) failureClass.getNumericCellValue(), "");
				HierInfo hi = getManagedHierInfo((long) hier3.getNumericCellValue(), (long) hier32.getNumericCellValue(), (long) hier321.getNumericCellValue());
				UserEquipment ue = getManagedUserEquipment((int) ueType.getNumericCellValue());

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
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.EVENT_CAUSE_TABLE.getPageNumber());

		int numRows = worksheet.getLastRowNum();
		for (int i = 1; i <= numRows; i++) {
			HSSFRow row = (HSSFRow) worksheet.getRow(i);

			HSSFCell causeCode = row.getCell(0);
			HSSFCell eventId = row.getCell(1);
			HSSFCell description = row.getCell(2);

			EventCause eventCauseObject = getManagedEventCause((int) causeCode.getNumericCellValue(), (int) eventId.getNumericCellValue(),
					description.getStringCellValue());
		}
	}

	private void readFailureClassDataSheet(Workbook excelWorkbook) {
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.FAILURE_CLASS_TABLE.getPageNumber());

		int numRows = worksheet.getLastRowNum();
		for (int i = 1; i <= numRows; i++) {
			HSSFRow row = (HSSFRow) worksheet.getRow(i);

			HSSFCell failureClass = row.getCell(0);
			HSSFCell description = row.getCell(1);

			FailureClass failureClassObject = getManagedFailureClass((int) failureClass.getNumericCellValue(), description.getStringCellValue());
		}
	}

	private void readUserEquipmentDataSheet(Workbook excelWorkbook) {
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.UE_TABLE.getPageNumber());

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
			AccessCapability readAccessCapability = getManagedAccessCapability(accessCapability.getStringCellValue());
			UserEquipmentType readUserEquipmentType = getManagedUserEquipmentType(ueType.getStringCellValue());
			OS readOs = getManagedOs(os.getStringCellValue());
			InputMode readInputMode = getManagedInputMode(inputMode.getStringCellValue());

			try {
				UserEquipment ue = new UserEquipment((int) tac.getNumericCellValue(), marketName.getStringCellValue(), manufacturer.getStringCellValue(),
						readAccessCapability, model.getStringCellValue(), readUserEquipmentType, readOs, readInputMode);
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
		HSSFSheet worksheet = (HSSFSheet) excelWorkbook.getSheetAt(ExcelDataSheet.MCC_MNC_TABLE.getPageNumber());

		int numRows = worksheet.getLastRowNum();
		for (int i = 1; i <= numRows; i++) {
			HSSFRow row = (HSSFRow) worksheet.getRow(i);

			HSSFCell mcc = row.getCell(0);
			HSSFCell mnc = row.getCell(1);
			HSSFCell country = row.getCell(2);
			HSSFCell operator = row.getCell(3);

			CountryCodeNetworkCode countryNetworkCodeObject = getManagedCountryCodeNetworkCode((int) mcc.getNumericCellValue(),
					(int) mnc.getNumericCellValue(), country.getStringCellValue(), operator.getStringCellValue());
		}
	}

	private UserEquipment getManagedUserEquipment(int tac) {
		EntityManager em = PersistenceUtil.createEM();
		UserEquipment ue = em.createQuery("select ue from " + UserEquipment.class.getName() + " ue where ue.typeAllocationCode = :tac", UserEquipment.class)
				.setParameter("tac", tac).getSingleResult();
		System.out.println("hi found");
		em.close();
		return ue;
	}

	private HierInfo getManagedHierInfo(long hier3Id, long hier32Id, long hier321Id) {
		EntityManager em = PersistenceUtil.createEM();
		List <HierInfo> hiList = em
				.createQuery(
						"select hi from " + HierInfo.class.getName()
								+ " hi where hi.hier3Id = :hier3Id AND hi.hier32Id = :hier32Id AND hi.hier321Id = :hier321Id", HierInfo.class)
				.setParameter("hier3Id", Long.toString(hier3Id)).setParameter("hier32Id", Long.toString(hier32Id))
				.setParameter("hier321Id", Long.toString(hier321Id)).getResultList();
		if (hiList.isEmpty()) {
			System.out.println("hi not found");
			HierInfo hi = new HierInfo();
			hi.setHier3Id(Long.toString(hier3Id));
			hi.setHier32Id(Long.toString(hier32Id));
			hi.setHier321Id(Long.toString(hier321Id));

			PersistenceUtil.persist(hi);
			em.close();
			return hi;
		}
		System.out.println("hi not found");
		em.close();
		return hiList.get(0);
	}

	private CountryCodeNetworkCode getManagedCountryCodeNetworkCode(int countryCode, int networkCode, String country, String operator) {
		EntityManager em = PersistenceUtil.createEM();
		System.out.println("trying cc " + countryCode + " nc " + networkCode);
		List <CountryCodeNetworkCode> cnList = em
				.createQuery(
						"select cn from "
								+ CountryCodeNetworkCode.class.getName()
								+ " cn where cn.countryCodeNetworkCode.country.countryCode = :countryCode AND cn.countryCodeNetworkCode.networkCode = :networkCode",
						CountryCodeNetworkCode.class).setParameter("countryCode", countryCode).setParameter("networkCode", networkCode).getResultList();
		if (cnList.isEmpty()) {
			System.out.println("cn not found");
			Country countryEntity = getManagedCountry(countryCode, country);
			System.out.println("here" + countryEntity.getCountryCode() + countryEntity.getCountry());
			CountryCodeNetworkCode cn = new CountryCodeNetworkCode(new CountryCodeNetworkCodeCK(countryEntity, networkCode), operator);

			PersistenceUtil.persist(cn);
			em.close();
			return cn;
		}
		System.out.println("cn not found");
		em.close();
		return cnList.get(0);
	}

	private Country getManagedCountry(int countryCode, String country) {
		EntityManager em = PersistenceUtil.createEM();
		List <Country> cList = em.createQuery("select c from " + Country.class.getName() + " c where c.country = :country", Country.class)
				.setParameter("country", country).getResultList();
		if (cList.isEmpty()) {
			System.out.println("c not found");
			Country c = new Country();
			c.setCountry(country);
			c.setCountryCode(countryCode);
			PersistenceUtil.persist(c);
			em.close();
			return c;
		}
		System.out.println("c found");
		em.close();
		return cList.get(0);
	}

	private EventCause getManagedEventCause(int causeCode, int eventId, String description) {
		EntityManager em = PersistenceUtil.createEM();
		List <EventCause> ecList = em
				.createQuery(
						"select ec from " + EventCause.class.getName()
								+ " ec where ec.causeCodeEventIdCK.causeCode = :causeCode AND ec.causeCodeEventIdCK.eventId = :eventId", EventCause.class)
				.setParameter("causeCode", causeCode).setParameter("eventId", eventId).getResultList();
		if (ecList.isEmpty()) {
			System.out.println("ec not found");
			EventCause ec = new EventCause(new EventCauseCK(causeCode, eventId), description);
			PersistenceUtil.persist(ec);
			em.close();
			return ec;
		}
		System.out.println("ec not found");
		em.close();
		return ecList.get(0);
	}

	private FailureClass getManagedFailureClass(int failureClass, String description) {
		EntityManager em = PersistenceUtil.createEM();
		List <FailureClass> fcList = em
				.createQuery("select fc from " + FailureClass.class.getName() + " fc where fc.failureClass = :failureClassId", FailureClass.class)
				.setParameter("failureClassId", failureClass).getResultList();
		if (fcList.isEmpty()) {
			System.out.println("fc not found");
			FailureClass fc = new FailureClass();
			fc.setFailureClass(failureClass);
			fc.setDescription(description);
			PersistenceUtil.persist(fc);
			em.close();
			return fc;
		}
		System.out.println("fc not found");
		em.close();
		return fcList.get(0);
	}

	private AccessCapability getManagedAccessCapability(String accessCapability) {
		EntityManager em = PersistenceUtil.createEM();
		List <AccessCapability> acList = em
				.createQuery("select ac from " + AccessCapability.class.getName() + " ac where ac.accessCapability = :access", AccessCapability.class)
				.setParameter("access", accessCapability).getResultList();
		if (acList.isEmpty()) {
			System.out.println("ac not found");
			AccessCapability ac = new AccessCapability();
			ac.setAccessCapability(accessCapability);
			PersistenceUtil.persist(ac);
			em.close();
			return ac;
		}
		System.out.println("ac found");
		em.close();
		return acList.get(0);
	}

	private UserEquipmentType getManagedUserEquipmentType(String ueType) {
		EntityManager em = PersistenceUtil.createEM();
		List <UserEquipmentType> userEqiupentTypeList = em
				.createQuery("select ueT from " + UserEquipmentType.class.getName() + " ueT where ueT.userEquipmentType = :uetype", UserEquipmentType.class)
				.setParameter("uetype", ueType).getResultList();
		if (userEqiupentTypeList.isEmpty()) {
			System.out.println("ue not found");
			UserEquipmentType userEquipmentType = new UserEquipmentType();
			userEquipmentType.setUserEquipmentType(ueType);
			PersistenceUtil.persist(userEquipmentType);
			em.close();
			return userEquipmentType;
		}
		System.out.println("ue found");
		em.close();
		return userEqiupentTypeList.get(0);
	}

	private OS getManagedOs(String os) {
		EntityManager em = PersistenceUtil.createEM();
		List <OS> osList = em.createQuery("select os from " + OS.class.getName() + " os where os.os = :operatingsys", OS.class)
				.setParameter("operatingsys", os).getResultList();
		if (osList.isEmpty()) {
			System.out.println("os not found");
			OS newOS = new OS();
			newOS.setOs(os);
			PersistenceUtil.persist(newOS);
			em.close();
			return newOS;
		}
		System.out.println("os found");
		em.close();
		return osList.get(0);
	}

	private InputMode getManagedInputMode(String inputMode) {
		EntityManager em = PersistenceUtil.createEM();
		List <InputMode> inputModeList = em.createQuery("select im from " + InputMode.class.getName() + " im where im.inputMode = :imode", InputMode.class)
				.setParameter("imode", inputMode).getResultList();
		if (inputModeList.isEmpty()) {
			System.out.println("im not found");
			InputMode inputModeObject = new InputMode();
			inputModeObject.setInputMode(inputMode);
			PersistenceUtil.persist(inputModeObject);
			em.close();
			return inputModeObject;
		}
		System.out.println("im found");
		em.close();
		return inputModeList.get(0);
	}

	private String formatDateAsString(HSSFCell dateTime) {
		DateFormat dateTimeFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
		Date dateTimeValue = dateTime.getDateCellValue();

		String dateTimeString = dateTimeFormat.format(dateTimeValue) + " " + dateTimeFormat.format(dateTimeValue);
		return dateTimeString;
	}
}