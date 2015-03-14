package com.ericsson.msc.group5.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import com.ericsson.msc.group5.services.DataImportService;
import com.ericsson.msc.group5.services.FailureTraceService;

@Path("/restImportService")
public class RestImportService {

	@EJB
	private FailureTraceService failureTraceEJB;

	@Inject
	private DataImportService dataImport;

	@POST
	@Path("/import")
	@Consumes("multipart/form-data")
	public String importUploadedFile(@MultipartForm FileUploadForm form) {
		long startTime, endTime, totalTime;
		String resultString = "";
		startTime = System.currentTimeMillis();
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new ByteArrayInputStream(
					form.getFileData()));
			startTime = System.currentTimeMillis();
			dataImport.importSpreadsheet(wb);
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			resultString = "Time taken: " + totalTime + " milliseconds.";
			System.out.println(resultString);
		}
		catch (IOException e) {
			resultString = "Import was unsuccessful";
			e.printStackTrace();
		}
		return resultString;
	}
}
