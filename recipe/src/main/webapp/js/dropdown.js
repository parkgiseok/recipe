$('#dropdown-1 .dropdown-menu li > a').bind('click', function (e) {
    var html = $(this).html();
    $('#dropdown-1 button.dropdown-toggle').html(html + ' <span class="caret"></span>');
});