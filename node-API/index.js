var app = require("express")();
var http = require("http").Server(app);
var io = require("socket.io")(http);

app.listen(3000);

io.on("connection", function(socket) {
    socket.on("enter-room", function(roomname) {
        socket.join(roomname);
    });
})