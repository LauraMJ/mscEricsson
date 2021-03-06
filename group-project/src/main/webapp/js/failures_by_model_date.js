function getModels() {
	$.ajax({
		get : 'GET',
		url : '../rest/query/getAllModels',
		success : populateDropdown,
		contentType : 'application/json'
	});
}

function populateDropdown(data) {
	var selector = document.getElementById("model_dropdown");

	for (var i = 0; i < data.length; i++) {
		var imsi = data[i];
		var element = document.createElement("option");
		element.textContent = imsi;
		element.value = imsi;
		selector.appendChild(element);
	}
}

function getFailureCount() {
	var model = $("#model_dropdown").val();

	var url = '../rest/query/givenModelByTimePeriod';

	var fromDateTime = moment($("#fromDateTime").data("DateTimePicker").date())
			.format("YYYY-MM-DD HH:mm");
	var toDateTime = moment($("#toDateTime").data("DateTimePicker").date())
			.format("YYYY-MM-DD HH:mm");

	if(model.length == 0){
		alert("You must select a Model.");
		$("#model_dropdown").focus();
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
		"Model" : model,
		"Date1" : fromDateTime,
		"Date2" : toDateTime
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
				return;
			}
			$("#failureCount").val(data[0]);
		}
	});
}

getModels();