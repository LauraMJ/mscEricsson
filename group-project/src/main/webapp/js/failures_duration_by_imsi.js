function getIMSIs() {
	$.ajax({
		get : 'GET',
		url : '../rest/query/getAllIMSIs',
		success : populateDropdown,
		contentType : 'application/json'
	});
}

function populateDropdown(data) {
	for (var i = 0; i < data.length; i++) {
		data[i] = String(data[i]);
	}
	$("#imsiNumber").autocomplete({source: data, minLength: 0, delay: 500});
}

function getFailureCount(){
	var imsi = $("#imsiNumber").val();
	if(imsi.length < 15){
		alert("Provided IMSI is invalid");
		$('#datatable-1').DataTable().clear().draw();
		return;
	}
	
	var url = '../rest/query/givenImsiByTimePeriod';

	var fromDateTime = moment($("#fromDateTime").data("DateTimePicker").date()).format("YYYY-MM-DD HH:mm");
	var toDateTime = moment($("#toDateTime").data("DateTimePicker").date()).format("YYYY-MM-DD HH:mm");
	
	if(imsi.length == 0){
		alert("You must select an IMSI.");
		$("#imsi_dropdown").focus();
		return;
	}
	if(fromDateTime == "Invalid date"){
		alert("The 'From' date field cannot be empty.");
		$("#fromDateTime").focus();
		return;
	}
	if(toDateTime == "Invalid date"){
		alert("The 'To' date field cannot be empty.");
		$("#toDateTime").focus();
		return;
	}
	
	var JSONObject = {
		"Date1" : fromDateTime,
		"Date2" : toDateTime,
		"Imsi" : imsi
	};
	JSONObject = JSON.stringify(JSONObject);
	
	$.ajax({
		url : url,
		type : "POST",
		data : JSONObject,
		contentType : "application/json",
		dataType : 'json',
		success : function(data) {
			if (data.length == 0) {
				alert("No results for lookup");
				$("#failureCount").val(0);
				$("#totalDuration").val(0);
				return;
			}
			$("#failureCount").val(data[0][0]);
			$("#totalDuration").val(data[0][1]);
		}
	});
}

getIMSIs();