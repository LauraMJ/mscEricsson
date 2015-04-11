function getUserRole() {
	$.ajax({
		url : "../rest/get/userrole",
		type : "GET",
		contentType : 'application/json',
		dataType : "json",
		success : enableSidebarItems
	});
}

function enableSidebarItems(data) {
	if (data.length == 0)
		return;
	var role = data.role;

	switch (role) {
	case "customer service rep":
		$("#customer_rep_funct").show();
		break;
	case "support engineer":
		$("#customer_rep_funct").show();
		$("#support_eng_funct").show();
		break;
	case "network mgmt engineer":
		$("#customer_rep_funct").show();
		$("#support_eng_funct").show();
		$("#network_eng_funct").show();
		break;
	case "administrator":
		$("#customer_rep_funct").show();
		$("#support_eng_funct").show();
		$("#network_eng_funct").show();
		$("#administrator_funct").show();
		break;
	}
}

getUserRole();