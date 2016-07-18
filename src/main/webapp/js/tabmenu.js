// JavaScript Document
 
 jQuery(function($){  
   var tab = $('.tab_list');  
    tab.removeClass('js_off');  
   tab.css('height', tab.find('>ul>li>ul:visible').height()+40);  
     function onSelectTab(){  
        var t = $(this);  
        var myClass = t.parent('li').attr('class');  
        t.parents('.tab_list:first').attr('class', 'tab_list '+myClass);  
        tab.css('height', t.next('ul').height()+40);  
     }  
    tab.find('>ul>li>a').click(onSelectTab).focus(onSelectTab);  
 });  

