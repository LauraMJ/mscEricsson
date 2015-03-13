package com.ericsson.msc.group5.rest;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class FileUploadForm {

	public FileUploadForm() {
		// TODO Auto-generated constructor stub
	}
	
	private byte[] fileData;
	private String fileName;
	
	public String getFileName(){
			return fileName;
	}
	
	@FormParam("fileName")
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	public byte[] getFileData(){
		return fileData;
	}
	
	@FormParam("selectedFile")
	@PartType("application/octet-stream")
	public void setFileData(byte[] fileData){
		this.fileData = fileData;
	}

}
