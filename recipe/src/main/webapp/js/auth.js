$.post("auth/log.do", {
}, function(result) {
	if (result.status == "success") {
		$('#logout').css("display", "");
		$('#mypage').css("display", "");
		$('#login').css("display", "none");
		$('#signup').css("display", "none");z
	} else {
		$('#logout').css("display", "none");
		$('#mypage').css("display", "none");
		$('#login').css("display", "");
		$('#signup').css("display", "");
	}
}, "json");	
