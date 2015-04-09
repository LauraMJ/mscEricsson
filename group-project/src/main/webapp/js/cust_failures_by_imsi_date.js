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

function getFailureCount(){
	var imsi = $("#imsi_dropdown").val();
	
	var url = '../rest/query/givenImsiAndTimePeriodReturnNumberOfFailures';

	var fromDateTime = moment($("#fromDateTime").data("DateTimePicker").date()).format("YYYY-DD-MM HH:mm");
	var toDateTime = moment($("#toDateTime").data("DateTimePicker").date()).format("YYYY-DD-MM HH:mm");
	
	// LOOKING FOR A "YYYY-DD-MM TT:TT" FORMAT
//	var DateOne = document.getElementById('dateOne1').value + " "
//			+ document.getElementById('timeOne1').value;
//	var DateTwo = document.getElementById('dateTwo2').value + " "
//			+ document.getElementById('timeTwo2').value;
	
	alert(imsi);
	alert(fromDateTime);
	alert(toDateTime);
	
	var JSONObject = {
		"DateOne" : fromDateTime,
		"DateTwo" : toDateTime,
		"Imsi" : imsi
	};
	JSONObject = JSON.stringify(JSONObject);
	
	alert(JSONObject);

	$.ajax({
		url : url,
		type : "POST",
		data : JSONObject,
		contentType : "application/json",
		dataType : 'json',
		success : function(data) {
			alert("inside success");
			alert(data);
			if (data.length == 0) {
				alert("No results for lookup");
				return;
			}
			$("#failureCount").val(data[0]);
		}
	});
}


////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//function givenImsiAndTimePeriodReturnNumberOfFailures() {
//	var selected = document.getElementById('imsiEnter').value;
//	var url = 'rest/query/givenImsiAndTimePeriodReturnNumberOfFailures';
//	var DateOne = document.getElementById('dateOne1').value + " "
//			+ document.getElementById('timeOne1').value;
//	var DateTwo = document.getElementById('dateTwo2').value + " "
//			+ document.getElementById('timeTwo2').value;
//	var JSONObject = {
//		"DateOne" : DateOne,
//		"DateTwo" : DateTwo,
//		"Imsi" : selected
//	};
//	JSONObject = JSON.stringify(JSONObject);
//
//	$.ajax({
//		url : url,
//		type : "POST",
//		data : JSONObject,
//		contentType : "application/json",
//		dataType : 'json',
//		success : function(data) {
//			var text = "<b> Results for IMSI </b><u>" + selected
//					+ "</u><b> for time period </b><i>" + DateOne
//					+ "<i><b> to </b><i>" + DateTwo + "</i><br>";
//			if (data.length == 0) {
//				text = "<b>No results for lookup.</b><br>";
//			}
//
//			for (var i = 0; i < data.length; i++) {
//				var obj = data[i];
//				var printable = "<b>Number of failures: </b>" + obj;
//				text = text + printable;
//
//			}
//			document.getElementById("jsChangeArea").innerHTML = text;
//		}
//	});
//}






getIMSIs();