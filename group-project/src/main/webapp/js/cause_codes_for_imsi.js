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
	$('#datatable-1').DataTable().clear();

	var imsi = $("#imsi_dropdown").val();
	if(imsi.length == 0){
		$('#datatable-1').DataTable().draw();
		return;
	}
	
	var url = '../rest/query/causeCodesPerImsi';
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
		t.row.add([ data[i] ]);
	}
	t.draw();
}

getIMSIs();