function getIMSIs() {
	$.ajax({
		get : 'GET',
		url : '../rest/query/getAllIMSIs',
		success : populateDropdown,
		contentType : 'application/json'
	});
}

function populateDropdown(data) {
	var selector = document.getElementById("imsi_dropdown");

	for (var i = 0; i < data.length; i++) {
		var imsi = data[i];
		var element = document.createElement("option");
		element.textContent = imsi;
		element.value = imsi;
		selector.appendChild(element);
	}
}

function loadFailures() {
	alert("here");
	$('#datatable-1').DataTable().clear();
	alert("here");

	var imsi = $("#imsi_dropdown").val();
	alert(imsi);
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

	var i;
	for (i = 0; i < data.length; i++) {
		t.row.add([ data[i].causeCodeEventIdCK.eventId, data[i].causeCodeEventIdCK.causeCode, data[i].description ]);
	}
	t.draw();
}

getIMSIs();