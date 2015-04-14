package com.ericsson.msc.group5.services.ejb.test;

import java.io.IOException;
import javax.ejb.EJB;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.DataImportService;

@RunWith(Arquillian.class)
public class DataImportServiceEJBTest {

	@EJB
	private DataImportService dataImportService;

	@Test
	public void testDataImportService() {

		HSSFWorkbook workbook = null;
		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(this.getClass().getResourceAsStream("/TestingDataset.xls"));
			workbook = new HSSFWorkbook(fileSystem);
		}
		catch (IOException e) {
			// File will be there unless removed from resources.
		}
		dataImportService.importSpreadsheet(workbook);
	}
}
