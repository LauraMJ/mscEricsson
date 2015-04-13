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

function loadFailures() {
	$('#datatable-1').DataTable().clear();

	var imsi = $("#imsiNumber").val();
	if(imsi.length < 15){
		alert("Provided IMSI is invalid");
		$('#datatable-1').DataTable().draw();
		return;
	}
	
	var url = '../rest/query/eventCausePerImsi';
	$.ajax({
		url : url,
		type : "POST",
		data : imsi,
		contentType : "application/json",
		dataType : 'json',
		success : populateFailuresTable
	});
}

function populateFailuresTable(data) {
	var t = $('#datatable-1').DataTable();
	
	if(data.length==0){
		alert("No results for lookup");
	}

	var i;
	for (i = 0; i < data.length; i++) {
		t.row.add([ data[i] ]);
	}
	t.draw();
}

getIMSIs();