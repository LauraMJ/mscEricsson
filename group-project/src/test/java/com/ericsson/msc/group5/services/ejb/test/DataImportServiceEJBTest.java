package com.ericsson.msc.group5.services.ejb.test;

import java.io.File;
import java.io.IOException;
import javax.ejb.EJB;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.services.DataImportService;

@RunWith(Arquillian.class)
public class DataImportServiceEJBTest {

	@Deployment(testable = true)
	public static Archive <?> createDeployment() {
		PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeAndTestDependencies();
		File [] libraries = pom.resolve("org.apache.poi:poi").withTransitivity().asFile();

		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(true, "com.ericsson")
				.addAsLibraries(libraries)
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsResource("TestingDataset.xls")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private DataImportService dataImportService;

	@Test
	public void testDataImportService() {

		System.out.println("WORK DAMN YOU");
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
