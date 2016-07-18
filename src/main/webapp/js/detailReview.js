   var bno = location.href.split("=")[1];
$(function(){
   $("#sendReview").click(function() {
		if (sessionStorage.getItem("/log") != "success") {
			window.alert("로그인 해야 이용할 수 있습니다.");
			return;
		}
      $.ajax("../ajax/reply/add.do?", {
          method : "POST",
          dataType : "json",
          data : {
            bno : bno,
            cont :  $("#reply").val()
          },
          success : function(result) {
            if (result.status == "success") {
            	location.href = "urlPage.html?bno="+bno;
            } else if ( result.status == "failure") {
            	window.alert("댓글 등록에 실패했습니다.")
            } else {
            	window.alert("댓글 등록은 하나만 가능합니다.")
            }
          }
      });
   })	 
})

$(function() {
	$.ajax("../ajax/reply/list.do?bno="+location.href.split("=")[1],{
	method : "POST",
	dataType : "json",
	success : function(result) {
			var templateData = $("#reviewrepeat").html();
			var template = Handlebars.compile(templateData);
			var html = template(result);
			$("#reviewtext").append(html);
			$(".review_update_delete").css("display","none");
			$("#review_"+result.userNo).css("display","");
			$("#review_update_"+result.userNo).click(function() {
				if ($("#review_text_"+result.userNo).attr("readonly") == "readonly") {
					$("#review_text_"+result.userNo).removeAttr("readonly");
				} else {
				      $.ajax("../ajax/reply/update.do?", {
				          method : "POST",
				          dataType : "json",
				          data : {
				            bno : bno,
				            cont :  $("#review_text_"+cont.userNo).val(),
				          },
				          success : function(result) {
				            if (result.status == "failure") {
				            	window.alert("댓글 수정에 실패했습니다.");
				            	location.href = "urlPage.html?bno="+bno;
				            }
				          }
				      });
					$("#review_text_"+result.userNo).attr("readonly","readonly");
				}
			});
			$("#review_delete_"+result.userNo).click(function() {
				if (window.confirm("댓글을 삭제하시겠습니까?") == true) {
			      $.ajax("../ajax/reply/delete.do?", {
			          method : "POST",
			          dataType : "json",
			          data : {
			            bno : bno
			          },
			          success : function(result) {
			            if (result.status == "failure") {
			            	window.alert("댓글 삭제에 실패했습니다.");
			            } else {
			            	location.href = "urlPage.html?bno="+bno;
			            }
			          }
			      });
				}
			});
		}
	})
});
