package com.ericsson.msc.group5.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

//http://stackoverflow.com/questions/25797650/fileupload-with-jaxrs **this one is being used**
//http://stackoverflow.com/questions/14740727/upload-excel-file-into-database-using-apache-poi-and-spring-framework
//http://www.html5rocks.com/en/tutorials/file/dndfiles/
//http://www.mysamplecode.com/2012/03/java-excel-file-upload-read.html
//http://www.java-tips.org/other-api-tips/jexcel/how-do-i-read-an-excel-file-uploaded-through-a-web-browser-to-a-se-3.html
//http://stackoverflow.com/questions/22985809/upload-read-an-excel-file-in-a-jsp-using-poi
//http://www.juniper.net/techpubs/en_US/junos-space-sdk/13.1/apiref/com.juniper.junos_space.sdk.help/html/guides/appdevguide/fileupload.html

import com.ericsson.msc.group5.services.DataImportService;
import com.ericsson.msc.group5.services.FailureTraceService;

@Path("/testingQueries")
public class TestRest {

	@EJB
	private FailureTraceService failureTraceEJB;

	@Inject
	private DataImportService dataImport;

	// dataImport.importSpreadsheet("C:\\Users\\Harry\\Documents\\data.xls");
	// return failureTraceEJB.findImsiOfFailureByTimePeriod(
	// "11/01/2013  17:15:00", "11/01/2013  17:39:00");
	// return failureTraceEJB.getEventCauseCombinations("344930000000011");
	// dataImport.importSpreadsheet("C:\\Users\\Harry\\Documents\\data.xls");

	@POST
	@Path("/import")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Collection <String> getImsiOfFailureByTimePeriod(File file) {
		// dataImport.importSpreadsheet("C:\\Users\\D14125353\\Desktop\\data.xls");
		
		//LAURA'S WORK*******************************************************

		System.out.println("trying to read");

		
		try {
			FormFile formfile = new FormFile(file);
			System.out.println("Path: "+formfile.getCanonicalPath());
			String path = formfile.getCanonicalPath();
			dataImport.importSpreadsheet(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//END****************************************************************
		
		
		
		long start = System.currentTimeMillis();
		Date startTime = new Date(1357924500000L);
		Date endTime = new Date(1357924920000L);
		Collection <String> returnCollection = failureTraceEJB.getImsiOfFailureByTimePeriod(startTime, endTime);
		long end = System.currentTimeMillis();
		long total = end - start;
		System.out.println("Time take %d milliseconds.\n" + total);
		return returnCollection;
	}

	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	// public Collection <FailureTrace> getAllFailureTraces() {
	// return failureTraceEJB.getAllFailureTraces();
	// }

}
