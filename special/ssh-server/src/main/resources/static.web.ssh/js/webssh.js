function WSSHClient() {
};

var stompClient = null;
WSSHClient.prototype.connect = function (options) {
//    var endpoint = this._generateEndpoint();

    if (window.WebSocket) {

        //如果支持websocket
        var socket = new SockJS("http://localhost:7901/demo/webSsh");
        stompClient = Stomp.over(socket);
        var connectionId = options.connectInfo.connectionId;
        stompClient.connect({
           Authorization: "ssh:这是一个随机数"
        },
        function connectCallback(frame) {
           // 连接成功时（服务器响应 CONNECTED 帧）的回调方法
//           alert("success");
           subscribe(connectionId, options);
           options.onConnect();

        },
        function errorCallBack(error) {
           // 连接失败时（服务器响应 ERROR 帧）的回调方法
           options.onError('连接失败');
        });


    }else {
        //否则报错
        options.onError('WebSocket Not Supported');
        return;
    }

//    this._connection.onopen = function () {
//        options.onConnect();
//    };
//
//    this._connection.onmessage = function (evt) {
//        var data = evt.data.toString();
//        //data = base64.decode(data);
//        options.onData(data);
//    };
//
//
//    this._connection.onclose = function (evt) {
//        options.onClose();
//    };
};

 //订阅消息
function subscribe(connectionId, options) {
    stompClient.subscribe('/user/queue/' + connectionId, function (response) {
        console.log("你接收到的消息为:" + response);
        options.onData(response.body);
    });
    stompClient.subscribe('/user/queue/ping/' + connectionId, function (response) {
        console.log("你接收到的心跳消息为:" + response);
        stompClient.send("/app/pong", {}, connectionId);
        // options.onData(response.body);
    });

}



WSSHClient.prototype.send = function (data) {
    this._connection.send(JSON.stringify(data));
};

WSSHClient.prototype.sendInitData = function (options) {
    //连接参数
    stompClient.send("/app/connect", {}, JSON.stringify(options));
}

WSSHClient.prototype.sendClientData = function (connectionId, data) {
    //发送指令
    stompClient.send("/app/command", {}, JSON.stringify({"operate": "command", "command": data, "connectionId": connectionId}))
}

WSSHClient.prototype.sendSize = function (connectionId, data) {
    //改变屏幕size
    stompClient.send("/app/resize", {}, JSON.stringify({ "size": data, "connectionId": connectionId}))
}

var client = new WSSHClient();

function randomString(e) {
    e = e || 32;
    var t = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678",
        a = t.length,
        n = "";
    for (i = 0; i < e; i++) n += t.charAt(Math.floor(Math.random() * a));
    return n
}

function Map() {
    var struct = function (key, value) {
        this.key = key;
        this.value = value;
    }
    var put = function (key, value) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                this.arr[i].value = value;
                return;
            }
        }
        this.arr[this.arr.length] = new struct(key, value);
    }
    var get = function (key) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                return this.arr[i].value;
            }
        }
        return null;
    }
    var remove = function (key) {
        var v;
        for (var i = 0; i < this.arr.length; i++) {
            v = this.arr.pop();
            if (v.key === key) {
                continue;
            }
            this.arr.unshift(v);
        }
    }
    var size = function () {
        return this.arr.length;
    }
    var isEmpty = function () {
        return this.arr.length <= 0;
    }
    this.arr = new Array();
    this.get = get;
    this.put = put;
    this.remove = remove;
    this.size = size;
    this.isEmpty = isEmpty;
}