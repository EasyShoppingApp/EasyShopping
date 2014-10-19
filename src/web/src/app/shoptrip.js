'use strict';

var shoptripApp = angular.module('shoptrip', []);

shoptripApp.filter('getById', function() {
    return function(input, id) {
        var i=0, len=input.length;
        for (; i<len; i++) {
            if (+input[i]._id.$oid == +id) {
                return input[i];
            }
        }
        return null;
    }
});

shoptripApp.run(['$rootScope', function($rootScope){
    $rootScope.createWS = function() {
        var socket = new WebSocket("ws://10.168.0.216:8080/app/events/");
        socket.onopen = function () {
            console.log("Соединение открылось");
        };
        socket.onclose = function () {
            console.log("Соединение закрылось");
            createWS()
        };
        socket.onmessage = function (event) {
            var response = JSON.parse(event.data);
            $rootScope.$broadcast(response.action, response.object);
            console.log("Пришло сообщение с содержанием:", event.data);
        };

        socket.list = function () {
            socket.send(JSON.stringify({action: "list"}));
        };

        socket.delete = function (item) {
            socket.send(JSON.stringify({action: "delete", object: item}));
        };

        socket.add = function (item) {
            socket.send(JSON.stringify({action: "add", object: item}));
        };

        socket.toggle = function (item) {
            socket.send(JSON.stringify({action: "toggle", object: item}));
        };

        globalsocket = socket;

        return socket;
    };
    $rootScope.createWS();
}]);

var globalsocket = null;