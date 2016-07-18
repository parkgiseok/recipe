/* 카드 움직임 */  
    $(document).ready(function() {
      $(document).on('click', '.card', function() {
        var clickid = '#' + $(this).parent(".lis").attr('id');
        if ($(clickid).css('left') != '1px') {
          cardleft = $(clickid).css('left');
        }

        if ($(this).parent(".lis").css('left') == '1px') {
          $(clickid + ' .desc').toggle("slide", {}, 300);
          window.setTimeout(function() {
            for (i = 0; i < 4; i++) {
              var id = '#a' + i;
              if (id != clickid) { $(id).show(300); }
            }
            $(clickid).animate({ 'left' : cardleft }, 300); }, 300);
        } else {
          for (i = 0; i < 4; i++) {
            var id = '#a' + i;
            if (id != clickid) { $(id).hide(300); }
          }
          $(this).parent(".lis").animate({ 'left' : 1 }, 300);
          window.setTimeout(function() {
            $(clickid + ' .desc').toggle("slide", {}, 300);
          }, 300);
        }
      });
    });