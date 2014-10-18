function CanvasBuild () {
  var example = document.getElementById("example"),
    ctx       = example.getContext('2d'), // Контекст
    pic       = new Image();              // "Создаём" изображение
  pic.src    = 'map.gif';  // Источник изображения, позаимствовано на хабре
  pic.onload = function() {    // Событие onLoad, ждём момента пока загрузится изображение
    ctx.drawImage(pic, 0, 0);  // Рисуем изображение от точки с координатами 0, 0
  }
}

function jsonToMap (json) {
  $.each(json.contents, function(item){
    AddCircle(json.contents[item].x, json.contents[item].y, json.contents[item].status, json.contents[item].order);
  });

}
  // круг 

function AddCircle(x, y, status, order) {

    switch (order) {
       case 1:
          color = 'red'
          break;
       case 2:
          color = 'orange'
          break;
       case 3:
          color = 'yellow'
          break;
       case 4:
          color = 'green'
          break;
       case 5:
          color = 'blue'
          break;
       default:
          color = 'Orchid4'
          break;
    }
    subcolor = "dark"+color;

    if (status == "checked") {
      color = "grey";
      subcolor = "darkgrey";
    };
    ctx       = example.getContext('2d'), // Контекст
    ctx.beginPath();
    ctx.arc(x, y, 5, 0, 2*Math.PI, false);
    ctx.fillStyle = color;
    ctx.fill();
    ctx.lineWidth = 2;
    ctx.strokeStyle = subcolor;
    ctx.stroke();

  }


  // это скрипт кнопки, которая добавляет курги для демонстрации ↓↓↓

  ordercount = 1;
function AddCircles() {
    AddCircle(ordercount, ordercount*45, ordercount*35+ordercount, status);
    ordercount++;
  }

  // это скрипт кнопки, которая добавляет курги для демонстрации ↑↑↑ --}

