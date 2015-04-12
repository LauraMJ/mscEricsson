function getFailuresByDate() {
	var url = '../rest/query/imsiByTimePeriod';

	var fromDateTime = moment($("#fromDateTime").data("DateTimePicker").date())
			.format("YYYY-MM-DD HH:mm");
	var toDateTime = moment($("#toDateTime").data("DateTimePicker").date())
			.format("YYYY-MM-DD HH:mm");

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

	var i;
	for (i = 0; i < data.length; i++) {
		t.row.add([ data[i] ]);
	}
	t.draw();
}
