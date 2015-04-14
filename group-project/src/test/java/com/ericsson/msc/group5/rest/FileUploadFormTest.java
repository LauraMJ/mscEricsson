package com.ericsson.msc.group5.rest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FileUploadFormTest {

	@Test
	public void testGetAndSetFileName() {
		FileUploadForm form = new FileUploadForm();
		form.setFileName("/test/file.txt");
		String name = form.getFileName();
		assertEquals("/test/file.txt", name);
	}

	@Test
	public void testGetAndSetFileData() {
		FileUploadForm form = new FileUploadForm();
		byte [] data = {Byte.parseByte("1"), Byte.parseByte("2")};
		form.setFileData(data);
		byte [] name = form.getFileData();
		assertEquals(data, name);
	}
}
