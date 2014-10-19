define([],
    function () {

        var Map = function () {

            var elms = {
                imgHeight: 376,
                imgWidth: 606,
                Xkoof: 6.06,
                Ykoof: 3.76,
                iconWidth: 50
            }

            /*var JSON = {
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
                        "order": 1,
                        "status": "unchecked"
                    },
                    {
                        "productID": 56,
                        "productName": "Хлеб",
                        "quantity": 3,
                        "x": 10,
                        "y": 40,
                        "order": 2,
                        "status": "unchecked"
                    },
                    {
                        "productID": 56,
                        "productName": "Пельмени",
                        "quantity": 2,
                        "x": 43,
                        "y": 70,
                        "order": 3,
                        "status": "checked"
                    },
                    {
                        "productID": 56,
                        "productName": "Помидоры",
                        "quantity": 2,
                        "x": 50,
                        "y": 50,
                        "order": 4,
                        "status": "checked"
                    }
                ],
                "orderCompleted": true
            };*/
            function DeletePoint(item) {
                $("img[id='" + item._id.$oid + "']").remove();
            }

            function TogglePoint(item) {
                var opacity = item.done ? 0.5: 1;
                $("img[id='" + item._id.$oid + "']").css({opacity: opacity});
            }


            function AddPoint(item) {

                var img = $("<img id='" + item._id.$oid + "'/>");
                img[0].src = item.imgUrl;
                var opacity = item.done ? 0.5: 1;
                $(img).css({
                    width: elms.iconWidth + "px",
                    top: item.x * elms.Ykoof,
                    left: item.y * elms.Xkoof,
                    position: "absolute",
                    opacity: opacity
                });

                console.log(item.imgUrl);

                var canvas = $('#canvas');
                img.appendTo(canvas);
            };


            function jsonToMap(json) {
                var count = 1;
                $.each(json, function (item) {
                    var product = json[item];
                    AddPoint(product);
                    /*debugger;
                    var order = item + 1;
                    var circle =
                        $('<div id="circle_' + order + '">' + order + '</div>');
                    circle.appendTo($('#canvas'));
                    var imgHeight = 376;
                    var imgWidth = 606;
                    Xkoof = 6.06;
                    Ykoof = 3.76;
                    $('#circle_' + count).css({top: product.x * Ykoof, left: product.y * Xkoof });
                    count++;*/
                });
                $('[id^=circle_]').show(700);


                $('[id^=circle_]').click(function (e) {
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
                    var order = $(this).attr("id").replace('circle_', '');
                    order = parseInt(order) - 1;
                    console.log(order);
                    $('#tip').css({left: cLeft + 'px', top: cTop + 'px'});
                    var myP = $('<p>' + json[order].productName + '</p>');
                    myP.appendTo($('#info'));
                    $('#tip').show(300);

                });
                $('#close').click(function () {
                    $('#tip').hide(200);
                    $('#info').html('');
                });
                $('#map').click(function () {
                    $('#tip').hide(200);
                    $('#info').html('');
                });
            }

            var init = function (scope) {
                globalsocket.list();
                console.log(scope);
            }

            return {
                Init: init,
                TogglePoint: TogglePoint,
                AddPoint: AddPoint,
                DeletePoint: DeletePoint,
                JsonToMap: jsonToMap
            }
        };

        var map = new Map();
//    map.Init();
        return map;

    });