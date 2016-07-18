    /* 카드 슬라이드 안에 사진 자동 변경 */
    
    function slide(){
      var card_slider = document.getElementById("card_slider");
      var slideArray = card_slider.getElementsByTagName("li");
      var slideMax = slideArray.length - 1;
      var curSlideNo = 0;
      var nextSlideNo = null;
      var fadeStart = false;
      var curSlideLevel = 1;
      var nextSlideLevel = 0;
    
      for (i = 0; i <= slideMax; i++) {
        if (i == curSlideNo) changeOpacity(slideArray[i], 1);
        else changeOpacity(slideArray[i], 0);
      }
    
      function startSlide(dir){
        if (fadeStart === true) return;
        if( dir == "prev" ) {
          nextSlideNo = curSlideNo - 1;
          if ( nextSlideNo < 0 ) nextSlideNo = slideMax;
        } else if( dir == "next" ) {
          nextSlideNo = curSlideNo + 1;
          if ( nextSlideNo > slideMax ) nextSlideNo = 0;
        } else{
          fadeStart = false;
          return; }
        fadeStart = true;
        changeOpacity(slideArray[curSlideNo], curSlideLevel);
        changeOpacity(slideArray[nextSlideNo], nextSlideLevel);
        fadeInOutAction(dir);
      }
    
      function fadeInOutAction(dir) {
        curSlideLevel = curSlideLevel - 0.1;
        nextSlideLevel = nextSlideLevel + 0.1;
        if( curSlideLevel <= 0 ) {
          changeOpacity(slideArray[curSlideNo], 0);
          changeOpacity(slideArray[nextSlideNo], 1);
          if(dir == "prev") {
            curSlideNo = curSlideNo - 1;
            if (curSlideNo < 0) curSlideNo = slideMax; 
          } else {
            curSlideNo = curSlideNo + 1;
            if (curSlideNo > slideMax) curSlideNo = 0; }
          curSlideLevel = 1;
          nextSlideLevel = 0;
          fadeStart = false;
          return; }
        changeOpacity(slideArray[curSlideNo], curSlideLevel);
        changeOpacity(slideArray[nextSlideNo], nextSlideLevel);
        setTimeout(function () {
          fadeInOutAction(dir);
        }, 50);
      }
    
      function changeOpacity(obj,level) {
        obj.style.opacity = level; 
        obj.style.MozOpacity = level; 
        obj.style.KhtmlOpacity = level;
        obj.style.MsFilter = "'progid:DXImageTransform.Microsoft.Alpha(Opacity=" + (level * 100) + ")'";
        obj.style.filter = "alpha(opacity=" + (level * 100) + ");"; }
      // 1초마다 Fade In/Out 슬라이드를 반복하는 코드
      var autoslider = setInterval( function(){startSlide('next');}, 1500);
    };