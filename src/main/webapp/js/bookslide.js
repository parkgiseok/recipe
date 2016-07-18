    function loadApp() {
      $('.flipbook').turn({
        width : 902,
        height : 449,
        elevation : 50,
        gradients : true,
        autoCenter : true,
        page: 2
      });
    }
    // Load the HTML4 version if there's not CSS transform
    yepnope({
      test : Modernizr.csstransforms,
      yep : [ 'js/turn.js' ],
      nope : [ 'js/turn.html4.js' ],
      both : [ 'css/basic.css' ],
      complete : loadApp
    });