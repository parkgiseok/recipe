  var allid = [];
  ddd();
  function ddd() {
        $.ajax({
          method : "GET",
          url : "ajax/member/list.do",
          dataType : "json",
          async : false,
          success : function(result) {
            for (var i=0; i<result.length; i++) {
               allid[i] = result[i].id;
            }
          }
        });
      }
  var emailchecking = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
  var passwordchecking = /^[a-zA-Z0-9]{6,15}$/;
  
    $("#test_form").submit(function() {
      for(var i = 0; i<allid.length; i++) {
      if ($("#jid").val() == allid[i]) {
        swal({confirmButtonColor: "#DD6B55",
              title: '아이디 중복',
              text: "이미 사용중인 아이디 입니다.",
              type: 'warning'}) 
        return false;
        }
      }
      if ($("#jid").val() == "") {
        swal({confirmButtonColor: "#DD6B55",
                title: "오류",
                text: "아이디를 입력해 주세요.",
                type: 'warning'}) 
        return false;
      } else if ($("#jemail").val() == "") {
        swal({confirmButtonColor: "#DD6B55",
              title: '오류',
              text: "이메일을 입력해 주세요.",
              type: 'warning'})
        return false;
      } else if (!($("#jemail").val()).match(emailchecking)) {
        swal({confirmButtonColor: "#DD6B55",
              title: '이메일 형식 오류',
              text: "유효한 이메일 형식이 아닙니다. (aaa@bbb.com",
              type: 'warning'}) 
        return false;
      } else if ($("#jPassword").val() == "") {
        swal({confirmButtonColor: "#DD6B55",
              title: '오류',
              text: "패스워드를 입력해 주세요.",
              type: 'warning'})
        return false;
      } else if (!($("#jPassword").val()).match(passwordchecking)) {
        swal({confirmButtonColor: "#DD6B55",
              title: '암호 형식 오류',
              text: "암호는 최소 6자 최대 15자의 영문+숫자 조합으로 가능합니다.",
              type: 'warning'})
        return false;
      }
      return;
    });
