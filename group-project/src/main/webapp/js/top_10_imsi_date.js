function getTop10IMSI() {
	var url = '../rest/query/topTenIMSIsWithFailures';

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
		success : populateTable
	});
}

function populateTable(data) {
	var t = $('#datatable-1').DataTable();
	t.clear();
	
	if (data.length == 0) {
		alert("No results for lookup.");
	}

	var i;
	for (i = 0; i < data.length; i++) {
		t.row.add([ data[i][0], data[i][1] ]);
	}
	t.draw();
	/*var cont = document.getElementById("graph-button-container");
	cont.innerHTML = "<button id = 'graph'\
						onclick = 'drawGraph()'\
						class='btn btn-primary btn-lg btn-block'>\
							Show Graph\
					  </button>"*/
}
function passData(data){
	$.getScript("../bower_components/raphael/raphael.js",function(data){
		var paper = Raphael(10, 50, 320, 200);
		var circle = paper.circle(50, 40, 10);
		circle.attr("fill", "#f00");
		circle.attr("stroke", "#fff");
		}
	)
}
