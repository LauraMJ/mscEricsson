function addNewUser() {
	var url = '../rest/add/user';

	if ($("#username").val().length == 0) {
		alert("The username field can't be null");
		$("#username").focus();
		return;
	}
	if ($("#password").val().length == 0) {
		alert("The password field can't be null");
		$("#password").focus();
		return;
	}
	if ($("#role").val().length == 0){
		alert("The user must have a role");
		$("#role").focus();
		return;
	}

	$.ajax({
		url : url,
		type : 'POST',
		contentType : 'application/json',
		dataType : "json",
		data : JSON.stringify({
			"username" : $("#username").val(),
			"password" : $("#password").val(),
			"role" : $('#role').val()
		}),
		success : function(data) {
			var message = "User '" + data.username + "' has been added.";
			alert(message);

			$("#username").val("");
			$("#password").val("");
		},
		error : function(data) {
			alert("Failed to add user - username '" + $("#username").val()
					+ "' has already been taken.");
		}
	});
}