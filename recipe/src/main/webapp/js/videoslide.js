/* 동영상 슬라이드 */
    var sliderCampaignCount = 0;
    if (sliderCampaignCount > 5) {
      sliderCampaignCount = 5;
    } else {
      sliderCampaignCount = 1;
    }
    $('.slider').slick({
      variableWidth : true,
      centerMode : true,
      infinite : true,
      adaptiveHeight : true,
      slidesToShow : sliderCampaignCount,
      slidesToScroll : 5,
      autoplay : true,
      autoplaySpeed : 2000,
      dots : true
    });
    $("img.img-banner").lazyload({
      effect : "fadeIn"
    });
    (function() {
      var currentPage = 0;
      var $header = $(".slide-category-header");
      var $content = $(".slide-category-list .campaign-slide-content");
      var pageSize = Math.ceil($content.find("ul").size());
      $content.find('ul').width(1216);
      $content.width($content.find('ul').width() * 4);
      var pageWidth = $content.find('ul').width();
      $header.find(".total-page").text(pageSize);
      showPageInfo(currentPage);
      function showPageAt(page) {
        var left = -(page * pageWidth);
        $content.stop().animate({
          left : left
        }, 300, function() {
          showPageInfo(page);
        });
      }
      function showPageInfo(page) {
        $header.find(".current-page").html((page + 1));
        $(document).trigger('scroll');
      }
    })();
    $(function() {
      $('.slider').fadeIn(1500);
    });