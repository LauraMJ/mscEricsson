package com.ericsson.msc.group5.services.ejb.test;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.msc.group5.dao.ErrorLogDAO;
import com.ericsson.msc.group5.dao.jpa.JPAErrorLogDAO;
import com.ericsson.msc.group5.entities.ErrorLog;
import com.ericsson.msc.group5.services.ErrorLogWriterService;

@RunWith(Arquillian.class)
public class ErrorLogServiceEJBTest {
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackage(ErrorLog.class.getPackage())
				.addClasses(ErrorLog.class, ErrorLogServiceEJBTest.class, ErrorLogWriterService.class, ErrorLogDAO.class, JPAErrorLogDAO.class)
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@EJB
	ErrorLogWriterService service;
	
	
	@Test
	public void writeToErrorLogTest(){
		
		ErrorLogDAO errorLogDAO = null;
		HSSFRow rowOfBaseData =  null;
		String errorDescription = "errors";
		
		service.writeToErrorLog(rowOfBaseData, errorDescription);
		
		assertTrue("An object failed to be retrieved", errorLogDAO.getAllErrorLogs().contains(errorDescription));
		
	}

}
