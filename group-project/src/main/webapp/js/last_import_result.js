$(document).ready(function() {
	var url = '../rest/importLog/getLastImportDetails';
	$.ajax({
		url : url,
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			if (typeof data.Timestamp != "undefined") {
				$("#timestamp").val(data.Timestamp);
			} else {
				$("#timestamp").val("None");
			}
			if (typeof data.TimeTaken != "undefined") {
				$("#timeTaken").val(data.TimeTaken);
			} else {
				$("#timeTaken").val("None");
			}
			if (typeof data.ValidRecords != "undefined") {
				$("#validRecords").val(data.ValidRecords);
			} else {
				$("#validRecords").val("None");
			}
			if (typeof data.InvalidRecords != "undefined") {
				$("#invalidRecords").val(data.InvalidRecords);
			} else {
				$("#invalidRecords").val("None");
			}
			if (typeof data.ImportType != "undefined") {
				$("#importType").val(data.ImportType);
			} else {
				$("#importType").val("None");
			}
		}
	});
});

function redirectViewErrors() {
	if ($("#timestamp").val().length == 0) {
		alert("No data found, cannot view rejections");
		return;
	}
	var timestamp = $("#timestamp").val();
	var packed = escape(timestamp);
	window.location.href = "error_log.html?" + packed;
}
