var member;
  function c_like(e) {
    var bno = e.getAttribute("data-bno");
    console.log(bno);
    $.ajax({
          method : "POST",
          url : "./auth/log.do",
          dataType : "json",
          async : false,
          success : function(result) {
             if(result.status=="failure") {
                    window.alert("로그인 해야 이용할 수 있습니다.");
                  } else {
                     var state = e.getAttribute("data-state");
                        if (state == "before") {
                          e.setAttribute("data-state", "after");
                          $("#like_"+bno).html(+($("#like_"+bno).html().split("명")[0])+1+"명");
                          //window.alert("좋아용!!!");
                          $("#like_"+bno).css("background", "url(./img/icon_heart_full.png) 7px center no-repeat");
                          $.ajax("./ajax/likes/add.do", {
                            method : "POST",
                            dataType : "json",
                            data : {
                                  bno : bno
                                }
                            });
                        } else {
                          $("#like_"+bno).css("background", "url(./img/icon_heart_full.png) 7px center no-repeat");
                          e.setAttribute("data-state", "before");
                          $("#like_"+bno).html(+($("#like_"+bno).html().split("명")[0])-1+"명");
                          //window.alert("싫어용!!!");
                          $("#like_"+bno).css("background", "url(./img/icon_heart_empty.png) 7px center no-repeat");
                          $.ajax("./ajax/likes/delete.do", {
                            method : "POST",
                            dataType : "json",
                            data : {
                              bno : bno
                            }});
                        }
                  }
           }
        });      
    }