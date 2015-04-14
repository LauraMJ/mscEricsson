package com.ericsson.msc.group5.services;

import java.util.ArrayList;
import javax.ejb.Local;
import org.json.simple.JSONObject;

@Local
public interface LogDetailsRetrieverService {

	public JSONObject retrieveLogDetailsAsJson();

	JSONObject createJsonObjectFromArrayListOfDetails(ArrayList <String> listOfDetailsForLastLogEntry);
}
