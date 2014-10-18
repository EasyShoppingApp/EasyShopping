var socket = new WebSocket("ws://localhost:8080/app/events/");
socket.onopen = function () {
    console.log("Соединение открылось");
};
socket.onclose = function () {
    console.log ("Соединение закрылось");
};
socket.onmessage = function (event) {
    console.log ("Пришло сообщение с содержанием:", event.data);
};