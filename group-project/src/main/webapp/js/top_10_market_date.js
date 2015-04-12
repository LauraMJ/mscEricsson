function getTop10Market() {
	var url = '../rest/query/top10MarketOperatorCellIdCombinations';

	var fromDateTime = moment($("#fromDateTime").data("DateTimePicker").date())
			.format("YYYY-MM-DD HH:mm");
	var toDateTime = moment($("#toDateTime").data("DateTimePicker").date())
			.format("YYYY-MM-DD HH:mm");
	
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
		"Date2" : toDateTime
	};
	JSONObject = JSON.stringify(JSONObject);

	$.ajax({
		url : url,
		type : "POST",
		data : JSONObject,
		contentType : "application/json",
		dataType : 'json',
		success : populateTable
	});
}

function populateTable(data) {
	var t = $('#datatable-1').DataTable();
	
	if (data.length == 0) {
		alert("No results for lookup.");
	}

	var i;
	for (i = 0; i < data.length; i++) {
		var obj = data[i];
		var count = obj[0];
		var cellId = obj[1];
		var operator = obj[2];
		var market = obj[3];
		t.row.add([ count, cellId, operator, market ]);
	}
	t.draw();
}
