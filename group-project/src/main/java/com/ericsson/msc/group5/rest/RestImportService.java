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
import com.ericsson.msc.group5.services.ejb.DataImportServiceEJB;
import com.ericsson.msc.group5.utils.FolderListener;

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
		String resultString = "";
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new ByteArrayInputStream(form.getFileData()));
			dataImport.importSpreadsheet(wb);
			resultString = "Time taken: " + DataImportServiceEJB.duration + " milliseconds.";
			System.out.println(resultString);
		}
		catch (IOException e) {
			resultString = "Import was unsuccessful";
			e.printStackTrace();
		}
		return resultString;
	}

	@POST
	@Path("/autoImport")
	//@Consumes(MediaType.APPLICATION_JSON)
	public String applyFileChanges(/*String path*/) {
		System.out.println("Rest method called");
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e1) {
			e1.printStackTrace();

		}
		//		FolderListener.begin(path);
		//FolderListener.begin("C:\\Users\\User\\Desktop\\test\\");
		ByteArrayInputStream stream = FolderListener.getFile();
		String resultString = "";
		try {
			HSSFWorkbook wb = new HSSFWorkbook(stream);
			dataImport.importSpreadsheet(wb);
			resultString = "Time taken: " + DataImportServiceEJB.duration + " milliseconds.";
			System.out.println(resultString);
		}
		catch (IOException e) {
			resultString = "Import was unsuccessful";
			e.printStackTrace();
		}
		return resultString;
	}
}
