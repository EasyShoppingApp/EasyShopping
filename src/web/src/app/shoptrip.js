'use strict';

var shoptripApp = angular.module('shoptrip', []);

shoptripApp.run(['$rootScope', function($rootScope){
    $rootScope.createWS = function() {
        var socket = new WebSocket("ws://localhost:8080/app/events/");
        socket.onopen = function () {
            console.log("Соединение открылось");
            this.list();
        };
        socket.onclose = function () {
            console.log("Соединение закрылось");
            createWS()
        };
        socket.onmessage = function (event) {
            var response = JSON.parse(event.data);
            switch (response.action) {
                case 'list':
                    debugger;
                    $rootScope.$broadcast('list', response.object);
                    break;
                case 'delete':
                    $rootScope.$broadcast('delete', response.object);
                    break;
                case 'add':
                    $rootScope.$broadcast('add', response.object);
                    break;
                case 'toggle':
                    $rootScope.$broadcast('toggle', response.object);
                    break;
            }
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
            socket.send(JSON.stringify({action: "toggles", object: item}));
        };

        globalsocket = socket;

        return socket;
    };
    $rootScope.createWS();
}]);

var globalsocket = null;

createWS();