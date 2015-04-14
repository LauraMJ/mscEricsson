package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.assertTrue;
import java.io.File;
import javax.ejb.EJB;
import javax.inject.Inject;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ericsson.msc.group5.dao.ErrorLogDAO;
import com.ericsson.msc.group5.services.ErrorLogWriterService;

@RunWith(Arquillian.class)
public class ErrorLogServiceEJBTest {

	// @Deployment
	// public static JavaArchive createDeployment() {
	// return ShrinkWrap.create(JavaArchive.class,
	// "test.jar").addPackage(ErrorLog.class.getPackage())
	// .addClasses(ErrorLog.class, ErrorLogWriterService.class,
	// ErrorLogWriterServiceEJB.class, ErrorLogDAO.class,
	// JPAErrorLogDAO.class, DateUtilityServiceEJB.class,
	// DateUtilityService.class,
	// org.apache.poi.ss.usermodel.Row.class,
	// org.apache.poi.hssf.usermodel.HSSFRow.class)
	// .addAsResource("test-persistence.xml",
	// "META-INF/persistence.xml").addAsManifestResource(EmptyAsset.INSTANCE,
	// "beans.xml");
	// }

	@Deployment
	public static WebArchive createDeployment() {

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
	private ErrorLogWriterService service;
	@Inject
	private ErrorLogDAO errorLogDAO;

	@Test
	public void writeToErrorLogTest() {

		HSSFRow rowOfBaseData = null;
		String errorDescription = "errors";

		service.writeToErrorLog(rowOfBaseData, errorDescription);

		assertTrue("An object failed to be retrieved", errorLogDAO.getAllErrorLogs().contains(errorDescription));

	}
}
