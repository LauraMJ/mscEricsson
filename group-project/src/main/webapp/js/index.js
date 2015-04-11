var adminHeadings = [ "Import Data", "Add Users" ];
var adminDescriptions = [ "Import failure trace data from an excel file",
		"Add a new user" ];
var adminAddresses = [ "import_xml.html", "add_user.html" ];

var custHeadings = [ "Failures for IMSI", "Failure Count for IMSI and Date",
		"Cause Codes for IMSI" ];
var custDescriptions = [
		"Display the Event ID and Cause Code for all failures affecting the selected IMSI",
		"Display the failure count for the selected IMSI in the given time period",
		"Display all the unique Cause Codes for the selected IMSI" ];
var custAddresses = [ "failures_by_imsi.html",
		"cust_failures_by_imsi_date.html", "cause_codes_for_imsi.html" ];

var suppHeadings = [ "Failures for Date", "Failure Count for Model and Date",
		"Affected IMSIs for Cause Codes" ];
var suppDescriptions = [
		"Display all IMSIs with call failures during a given time period",
		"Display the failure count for the selected Phone Model in the given time period",
		"Display all IMSIs affected by the selected Cause Class" ];
var suppAddresses = [ "failures_by_date.html", "failures_by_model_date.html",
		"failures_by_cause_class.html" ];

var netHeadings = [ "Failure Count and Total Duration for IMSI and Date",
		"Failure Causes for Model", "Top 10 Failures by Market for Date",
		"Top 10 IMSIs for Date" ];
var netDescriptions = [
		"Display the failure count for the selected IMSI in the given time period",
		"Display all the unique failure Event Id and Cause Code combinations and the number of occurrences for the selected model of phone",
		"Display the Top 10 Market/Operator/Cell ID combinations that had call failures during a time period",
		"Display the Top 10 IMSIs that had call failures during a time period" ];
var netAddresses = [ "failures_duration_by_imsi.html",
		"failure_causes_count_for_model.html", "top_10_market_date.html",
		"top_10_imsi_date.html" ];

function populatePage(data) {
	if (data.length == 0)
		return;
	var role = data.role;
	
	var table = $('#datatable-1').DataTable();

	switch (role) {
	case "customer service rep":
		addCustomerServiceRepQueries(table);
		break;
	case "support engineer":
		addCustomerServiceRepQueries(table);
		addSupportEngQueries(table);
		break;
	case "network mgmt engineer":
		addCustomerServiceRepQueries(table);
		addSupportEngQueries(table);
		addNetworkEngQueries(table);
		break;
	case "administrator":
		addCustomerServiceRepQueries(table);
		addSupportEngQueries(table);
		addNetworkEngQueries(table);
		addAdministratorQueries(table);
		break;
	}
	table.draw();
}

function addAdministratorQueries(table){
	for(var i=0; i<adminHeadings.length; i++){
		table.row.add([ adminHeadings[i], adminDescriptions[i], adminAddresses[i] ]);
	}
}

function addCustomerServiceRepQueries(table){
	for(var i=0; i<custHeadings.length; i++){
		table.row.add([ custHeadings[i], custDescriptions[i], custAddresses[i] ]);
	}
}

function addSupportEngQueries(table){
	for(var i=0; i<suppHeadings.length; i++){
		table.row.add([ suppHeadings[i], suppDescriptions[i], suppAddresses[i] ]);
	}
}

function addNetworkEngQueries(table){
	for(var i=0; i<netHeadings.length; i++){
		table.row.add([ netHeadings[i], netDescriptions[i], netAddresses[i] ]);
	}
}