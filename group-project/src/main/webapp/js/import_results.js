$(document).ready(function() {
	var query = window.location.toString();
	var index = query.indexOf("?");
	query = query.substring(index + 1);
	query = unescape(query);
	var data = query.split('&');
	for (i = 0; i < data.length; i++) {
		data[i] = unescape(data[i]);
	}

	var timeTaken = data[0].split("time_taken=")[1];
	
	var time = data[1].split("timestamp=")[1];
	var date = time.split("+")[0];
	var time = time.split("+")[1];
	
	var validCount = data[2].split("added=")[1];
	
	var rejectedCount = data[3].split("rejected=")[1];

	$("#timestamp").val(date + " " + time);
	$("#timeTaken").val(timeTaken);
	$("#validRecords").val(validCount);
	$("#invalidRecords").val(rejectedCount);
});

function redirectHome() {
	window.location.href = "index.html";
}

function redirectImportPage() {
	window.location.href = "import_xml.html";
}

function redirectViewErrors() {
	var timestamp = $("#timestamp").val();
	var packed = escape(timestamp);
	alert(timestamp);
	alert(packed);
	window.location.href = "error_log.html?" + packed;
}
