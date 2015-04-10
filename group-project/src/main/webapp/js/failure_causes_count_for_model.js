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
		var model = data[i];
		var element = document.createElement("option");
		element.textContent = model;
		element.value = model;
		selector.appendChild(element);
	}
}

function loadFailures() {
	$('#datatable-1').DataTable().clear();

	var model = $("#model_dropdown").val();
	if(model.length == 0){
		$('#datatable-1').DataTable().draw();
		return;
	}
	
	var url = '../rest/query/eventCauseAndCountOfOccurencesForModel';
	$.ajax({
		url : url,
		type : "POST",
		data : model,
		contentType : "application/json",
		dataType : 'json',
		success : populateModelsTable
	});
}

function populateModelsTable(data) {
	var t = $('#datatable-1').DataTable();

	var i;
	for (i = 0; i < data.length; i++) {
		t.row.add([ data[i][1], data[i][0] ]); //data[i].description
	}
	t.draw();
}

getModels();