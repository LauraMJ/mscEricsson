<<<<<<< HEAD
package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;
@RunWith(Arquillian.class)
public class ValidatorServiceEJBTest {
	
	HSSFWorkbook workbook2 = null;
	HSSFWorkbook workbook3 = null;
	
	@Before
	public void setup(){
		try {
			POIFSFileSystem fileSystem2 = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset2.xls"));
			workbook2 = new HSSFWorkbook(fileSystem2);
		}
		catch (IOException e) {
		}
		try {
			POIFSFileSystem fileSystem3 = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset3.xls"));
			workbook3 = new HSSFWorkbook(fileSystem3);
		}
		catch (IOException e) {
		}
	}
	
	@After
	public void tearDown(){
		HSSFWorkbook workbook2 = null;
		HSSFWorkbook workbook3 = null;
		HSSFSheet worksheet = null;
	}
	
	
	@Inject
	ValidatorServiceEJB validatorService;

	
	//validateFailureTraceRowFieldTypes
	@Test
	public void validateFailureTraceRow0(){
		HSSFSheet worksheet = (HSSFSheet) workbook2.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(0);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}
	
	@Test
	public void validateFailureTraceRow1(){
		HSSFSheet worksheet = (HSSFSheet) workbook2.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(1);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}
	
	@Test
	public void validateFailureTraceRow2(){
		HSSFSheet worksheet = (HSSFSheet) workbook2.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(2);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}
	
	@Test
	public void validateFailureTraceRow3(){
		HSSFSheet worksheet = (HSSFSheet) workbook2.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(3);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}
	
	@Test
	public void validateFailureTraceRow4(){
		HSSFSheet worksheet = (HSSFSheet) workbook2.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(4);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}
	
	@Test
	public void validateFailureTraceRow5(){
		HSSFSheet worksheet = (HSSFSheet) workbook2.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(5);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}
	
	@Test
	public void validateFailureTraceRow6(){
		HSSFSheet worksheet = (HSSFSheet) workbook2.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(6);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}
	
	@Test
	public void validateFailureTraceRow7(){
		HSSFSheet worksheet = (HSSFSheet) workbook2.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(7);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}
	
	@Test
	public void validateFailureTraceRow8(){
		HSSFSheet worksheet = (HSSFSheet) workbook2.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(8);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}
	
	@Test
	public void validateFailureTraceRow9(){
		HSSFSheet worksheet = (HSSFSheet) workbook2.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(9);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}
	
	@Test
	public void validateFailureTraceRow10(){
		HSSFSheet worksheet = (HSSFSheet) workbook2.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(10);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}
	
	@Test
	public void validateFailureTraceRow11(){
		HSSFSheet worksheet = (HSSFSheet) workbook2.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(11);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}
	
	@Test
	public void validateFailureTraceRow12(){
		HSSFSheet worksheet = (HSSFSheet) workbook2.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(12);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}
	
	@Test
	public void validateFailureTraceRow13(){
		HSSFSheet worksheet = (HSSFSheet) workbook2.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(13);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}
	
	//validateFailureTraceRowFieldValues
	@Test
	public void validateFailureTraceRowFieldValuesTestDate(){
		HSSFSheet worksheet = (HSSFSheet) workbook3.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(1);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
		
	}
	@Test
	public void validateFailureTraceRowFieldValuesTestEventId(){
		HSSFSheet worksheet = (HSSFSheet) workbook3.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(2);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}
	@Test
	public void validateFailureTraceRowFieldValuesTestFailureClass(){
		HSSFSheet worksheet = (HSSFSheet) workbook3.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(3);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}
	@Test
	public void validateFailureTraceRowFieldValuesTestUEType(){
		HSSFSheet worksheet = (HSSFSheet) workbook3.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(4);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}
	@Test
	public void validateFailureTraceRowFieldValuesTestMarket(){
		HSSFSheet worksheet = (HSSFSheet) workbook3.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(5);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}
	@Test
	public void validateFailureTraceRowFieldValuesTestOperator(){
		HSSFSheet worksheet = (HSSFSheet) workbook3.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(6);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}
	@Test
	public void validateFailureTraceRowFieldValuesTestCellId(){
		HSSFSheet worksheet = (HSSFSheet) workbook3.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(7);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}
	@Test
	public void validateFailureTraceRowFieldValuesTestDuration(){
		HSSFSheet worksheet = (HSSFSheet) workbook3.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(8);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}
	@Test
	public void validateFailureTraceRowFieldValuesTestCauseCode(){
		HSSFSheet worksheet = (HSSFSheet) workbook3.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(9);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}
	@Test
	public void validateFailureTraceRowFieldValuesTestNEVersion(){
		HSSFSheet worksheet = (HSSFSheet) workbook3.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(10);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}
	@Test
	public void validateFailureTraceRowFieldValuesTestImsi(){
		HSSFSheet worksheet = (HSSFSheet) workbook3.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(11);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}

}
=======
package com.ericsson.msc.group5.dataIOConsistencyChecks;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import javax.inject.Inject;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.ejb.ValidatorServiceEJB;

@RunWith(Arquillian.class)
public class ValidatorServiceEJBTest {

	@Inject
	ValidatorServiceEJB validatorService;

	// validateFailureTraceRowFieldTypes
	@Test
	public void validateFailureTraceRow0() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset2.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(0);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}

	@Test
	public void validateFailureTraceRow1() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset2.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(1);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}

	@Test
	public void validateFailureTraceRow2() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset2.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(2);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}

	@Test
	public void validateFailureTraceRow3() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset2.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(3);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}

	@Test
	public void validateFailureTraceRow4() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset2.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(4);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}

	@Test
	public void validateFailureTraceRow5() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset2.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(5);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}

	@Test
	public void validateFailureTraceRow6() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset2.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(6);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}

	@Test
	public void validateFailureTraceRow7() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset2.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(7);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}

	@Test
	public void validateFailureTraceRow8() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset2.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(8);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}

	@Test
	public void validateFailureTraceRow9() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset2.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(9);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}

	@Test
	public void validateFailureTraceRow10() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset2.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(10);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}

	@Test
	public void validateFailureTraceRow11() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset2.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(11);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}

	@Test
	public void validateFailureTraceRow12() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset2.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(12);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}

	@Test
	public void validateFailureTraceRow13() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset2.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(13);
		assertEquals(validatorService.validateFailureTraceRowFieldTypes(row), false);
	}

	// validateFailureTraceRowFieldValues
	@Test
	public void validateFailureTraceRowFieldValuesTestDate() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset3.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(1);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);

	}

	@Test
	public void validateFailureTraceRowFieldValuesTestEventId() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset3.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(2);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}

	@Test
	public void validateFailureTraceRowFieldValuesTestFailureClass() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset3.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(3);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}

	@Test
	public void validateFailureTraceRowFieldValuesTestUEType() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset3.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(4);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}

	@Test
	public void validateFailureTraceRowFieldValuesTestMarket() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset3.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(5);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}

	@Test
	public void validateFailureTraceRowFieldValuesTestOperator() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset3.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(6);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}

	@Test
	public void validateFailureTraceRowFieldValuesTestCellId() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset3.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(7);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}

	@Test
	public void validateFailureTraceRowFieldValuesTestDuration() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset3.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(8);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}

	@Test
	public void validateFailureTraceRowFieldValuesTestCauseCode() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset3.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(9);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}

	@Test
	public void validateFailureTraceRowFieldValuesTestNEVersion() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset3.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(10);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}

	@Test
	public void validateFailureTraceRowFieldValuesTestImsi() {
		HSSFWorkbook workbook = null;

		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset3.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {}
		HSSFSheet worksheet = (HSSFSheet) workbook.getSheetAt(0);
		HSSFRow row = (HSSFRow) worksheet.getRow(11);
		assertEquals(validatorService.validateFailureTraceRowFieldValues(row), false);
	}

}
>>>>>>> branch 'master' of https://github.com/LauraMJ/mscEricsson.git
