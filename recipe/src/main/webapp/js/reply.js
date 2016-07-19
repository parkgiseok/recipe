var bno = location.href.split("=")[1];
$(function(){
   $("#sendReview").click(function() {
    if (sessionStorage.getItem("login") != "success") {
      swal("로그인 오류", "로그인 해야 이용할 수 있습니다.", "warning")
      return;
    }
      $.ajax("./ajax/reply/add.do?", {
          method : "POST",
          dataType : "json",
          data : {
            bno : bno,
            r_cont :  $("#reply").val()
          },
          success : function(result) {
            if (result.status == "success") {
              location.href = "urlPage.html?bno="+bno;
            } else if ( result.status == "failure") {
              window.alert("댓글 등록에 실패했습니다.")
            } else {
            	swal("댓글 오류", "댓글은 하나만 등록할 수 있습니다.", "warning")
            }
          }
      });
   })  
})

$(function() {
  $.ajax("./ajax/reply/list.do?bno="+ location.href.split("=")[1],{
  method : "GET",
  dataType : "json",
  success : function(result) {
	  for(var i=0; i <= (result.list).length-1; i++) {
		result.list[i].r_w_dt = moment(result.list[i].r_w_dt, 'MM/DD/YYYY').format().substr(0, 10)
	  }
      var templateData = $("#reviewrepeat").html();
      var template = Handlebars.compile(templateData);
      var html = template(result);
      $("#reviewtext").append(html);
      $(".review_update_delete").css("display","none");
      $("#review_"+result.no).css("display","");
      $("#review_update_"+result.no).click(function() {
        if ($("#review_text_"+result.no).attr("readonly") == "readonly") {
          $("#review_text_"+result.no).removeAttr("readonly");
        } else {
              $.ajax("./ajax/reply/update.do?", {
                  method : "POST",
                  dataType : "json",
                  data : {
                    bno : bno,
                    r_cont :  $("#review_text_"+result.no).val(),
                  },
                  success : function(result) {
                    if (result.status == "failure") {
                      window.alert("댓글 수정에 실패했습니다.");
                      location.href = "urlPage.html?bno="+bno;
                    }
                  }
              });
          $("#review_text_"+result.no).attr("readonly","readonly");
        }
      });
      $("#review_delete_"+result.no).click(function() {
    	  swal(
		{
			title : "댓글을 삭제하시겠습니까?",
			text : "댓글을 삭제하게 되면 복구할 수 없습니다.",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "삭제하기",
			closeOnConfirm : false
		},
		function() {
			$.ajax("./ajax/reply/delete.do?", {
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
		});

      });
    }
  })
});  