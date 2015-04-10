function getCauseClasses() {
	$.ajax({
		get : 'GET',
		url : '../rest/query/getAllFailureClasses',
		success : populateDropdown,
		contentType : 'application/json'
	});
}

function populateDropdown(data) {
	var selector = document.getElementById("cause_class_dropdown");

	for (var i = 0; i < data.length; i++) {
		var causeClass = data[i];
		var element = document.createElement("option");
		element.textContent = causeClass.description;
		element.value = causeClass.failureClass;
		selector.appendChild(element);
	}
}

function loadFailures() {
	$('#datatable-1').DataTable().clear();

	var causeCode = $("#cause_class_dropdown").val();
	if(causeCode.length == 0){
		$('#datatable-1').DataTable().draw();
		return;
	}
	
	var url = '../rest/query/imsiAffectedByFailureClass';
	$.ajax({
		url : url,
		type : "POST",
		data : causeCode,
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

getCauseClasses();