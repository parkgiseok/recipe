$(function() {
	$.ajax("auth/loging.do?", {
		method : "POST",
		success : function(result) {
			if (result.status == "success") {
				sessionStorage.setItem("login","success");
				$(".not_login").css("display","none")
				$(".login").css("display","")
			} else if ( result.status == "failure") {
				sessionStorage.removeItem("login");
				$(".login").css("display","none")
				$(".not_login").css("display","")
			}
		}
	});
})
$(function() {
	 $.ajax("auth/loging.do?", {
	    method : "POST",
	    success : function(result) {
	      if (result.status == "success") {
	    	  $("#reply").attr("placeholder","댓글을 남겨주세요.")
	    	  $("#reply").removeAttr("readonly");
	      } else if ( result.status == "failure") {
	    	  $("#reply").attr("placeholder","로그인 후 이용이 가능합니다.");
	    	  $("#reply").attr("readonly","readonly");
	      }
	    }
	  });
})