define([],
  function () {

    var Map = function() {
      
     var JSON = {
  "listID": 12345,
  "shopperName": "Ваня Иванов",
  "shopperEmail": "ivanov@example.com",
  "contents": [
    {
      "productID": 34,
      "productName": "Колбаса",
      "quantity": 1,
      "x": 35,
      "y": 20,
      "order":1,
      "status": "unchecked"
    },
    {
      "productID": 56,
      "productName": "Хлеб",
      "quantity": 3,
      "x": 10,
      "y": 40,
      "order":2,
      "status": "unchecked"
    },
    {
      "productID": 56,
      "productName": "Пельмени",
      "quantity": 2,
      "x": 43,
      "y": 70,
      "order":3,
      "status": "checked"
    },
    {
      "productID": 56,
      "productName": "Помидоры",
      "quantity": 2,
      "x": 50,
      "y": 50,
      "order":4,
      "status": "checked"
    }
  ],
  "orderCompleted": true
};

function jsonToMap(json) {  // принимает JSON и генерирует divы с заданными координатами
  var count = 1;
  $.each(json.contents, function(item){
  var circle = 
  $('<div id="circle_'+json.contents[item].order+'">'+json.contents[item].order+'</div>');
  circle.appendTo($('#canvas')); 
  var imgHeight = 376; // высота картинки
  var imgWidth = 606; // ширина картинки
  Xkoof = 6.06;
  Ykoof = 3.76;
  $('#circle_'+count).css({top:json.contents[item].x*Ykoof, left: json.contents[item].y*Xkoof }); 
   count++;
  });
   $('[id^=circle_]').show(700); 


  $('[id^=circle_]').click(function(e){
    $('#info').html('');  
    $('#tip').hide();  
    var cLeft = $(this).css('left');
    var cTop = $(this).css('top');
    cLeft = cLeft.replace('px', '');
    cTop = cTop.replace('px', '');
    cTop = parseInt(cTop);
    cLeft = parseInt(cLeft);
    cTop += 5;
    cLeft += 15;
    var order =  $(this).attr("id").replace('circle_', '');
    order = parseInt(order) - 1; 
    console.log(order);
     $('#tip').css({left:cLeft+'px', top:cTop+'px'}); 
     var myP = $('<p>'+json.contents[order].productName+'</p>'); 
     myP.appendTo($('#info'));
      $('#tip').show(300); 
     
  }); 
   $('#close').click(function(){
      $('#tip').hide(200); 
       $('#info').html(''); 
   }); 
    $('#map').click(function(){
        $('#tip').hide(200); 
        $('#info').html('');
    }); 
}

// function CheckEvent(order){
//   order = parseInt(order);
//   order--;
//    $('#circle_'+order).css({border:"2px solid #333", background: "#999", color: "#000"}); 
// }

     var init = function(){
      jsonToMap(JSON);
    }

    return {
      Init: init
    }
  };

  var map = new Map();
  map.Init();
    //return map;
  });