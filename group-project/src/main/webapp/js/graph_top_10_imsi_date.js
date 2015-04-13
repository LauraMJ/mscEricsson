function getTop10IMSI() {
	var url = '../rest/query/topTenIMSIsWithFailures';

	var fromDateTime = moment($("#fromDateTime").data("DateTimePicker").date())
			.format("YYYY-DD-MM HH:mm");
	var toDateTime = moment($("#toDateTime").data("DateTimePicker").date())
			.format("YYYY-DD-MM HH:mm");

	var JSONObject = {
		"DateOne" : fromDateTime,
		"DateTwo" : toDateTime
	};
	JSONObject = JSON.stringify(JSONObject);

	$.ajax({
		url : url,
		type : "POST",
		data : JSONObject,
		contentType : "application/json",
		dataType : 'json',
		success : drawTable
	});
}

function populateTable(data) {
	var t = $('#datatable-1').DataTable();
	
	if (data.length == 0) {
		alert("No results for lookup.");
	}

	var i;
	for (i = 0; i < data.length; i++) {
		t.row.add([ data[i][0], data[i][1] ]);
	}
	t.draw();
}
function drawTable(data) {
	var table = [0][0];
	if (data.length == 0) {
		alert("No results for lookup.");
	}

	var i;
	for (i = 0; i < 1; i++) {
		var j;
		for (j = 0; j < data.length; j++) {
			table[i][j] = data[i][j];
		}
	}
	Morris.Bar({
         element: 'morris-bar-chart',
         data: table,
         xkey: 'y',
         ykeys: ['a', 'b'],
         labels: ['Series A', 'Series B'],
         hideHover: 'auto',
         resize: true,
         stacked: false
     });
}
