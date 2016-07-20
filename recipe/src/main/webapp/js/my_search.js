// 서치 기능
  var searchValue;
  $('input').keyup(function() {
    searchValue = $(this).val();
  });
  function fncSubmit() {
    location.href = '../search.html?value=' + searchValue;
  }