package com.ericsson.msc.group5.services;

import javax.ejb.Local;
import org.apache.poi.hssf.usermodel.HSSFRow;
/**
 * Error Log Writer service EJB interface.
 */
@Local
public interface ErrorLogWriterService {

	public void writeToErrorLog(HSSFRow rowOfBaseData, String errorDescription);
}
