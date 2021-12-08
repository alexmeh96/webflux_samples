var ws = null;

var user = "user" + Math.round(Math.random() * 100);
var url = `ws://localhost:8080/chat?user=${user}`;

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('send').disabled = !connected;
}

function connect() {
    ws = new WebSocket(url);
    ws.onopen = function () {
        setConnected(true);
        log('Info: Connection Established.');
    };

    ws.onmessage = function (event) {
        log(event.data);
    };


    ws.onclose = function (event) {
        setConnected(false);
        log('Info: Closing Connection.');
    };
}

function disconnect() {
    if (ws != null) {
        ws.close();
        ws = null;
    }
    setConnected(false);
}

function send() {
    if (ws != null) {
        var message = document.getElementById('message').value;
        var userTo = document.getElementById('user').value;
        log('Sent to server :: ' + message);

        const msg = {
            userFrom: user,
            message,
            userTo
        }

        ws.send(JSON.stringify(msg));
    } else {
        alert('connection not established, please connect.');
    }
}

function log(message) {
    var console = document.getElementById('logging');
    var p = document.createElement('p');
    p.appendChild(document.createTextNode(message));
    console.appendChild(p);
}