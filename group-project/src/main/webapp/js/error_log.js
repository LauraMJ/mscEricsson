$(document).ready(function() {
	var table = $('#datatable-1').DataTable();

	var query = window.location.toString();
	var index = query.indexOf("?");
	query = query.substring(index + 1);
	query = unescape(query);
	var data = query.split('&');
	for (i = 0; i < data.length; i++) {
		data[i] = unescape(data[i]);
	}

	var time = data[0];
	time = time.split(" ")[0] + "_" + time.split(" ")[1];
	alert("here");
	alert(time);

	getDataForTable(time);
});

function getDataForTable(time) {
	$.ajax({
		type : 'GET',
		url : '../rest/query/getErrorLogByImportDate/?importDate=' + time,
		success : populateErrorLogTable,
		contentType : 'application/json'
	});
}

function populateErrorLogTable(data) {
	var t = $('#datatable-1').DataTable();
	for (i = 0; i < data.length; i++) {
		t.row.add([ data[i].baseData, data[i].errorDescription ]);
	}
	t.draw();
}