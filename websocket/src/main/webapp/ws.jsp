<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Java API for WebSocket (JSR-356)</title>
</head>
<body>
<script type="text/javascript" src="./js/sockjs-0.3.min.js"></script>

<script type="text/javascript">
    var webSocketURL = 'ws://' + window.location.host + "/websocket/chatServer.do";
    var socketJSURL = 'ws://' + window.location.host + "/websocket/sockjs/chatServer.do";

    if ('WebSocket' in window) {
        websocket = new WebSocket(webSocketURL);
    }
    else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket(webSocketURL);
    }
    else {
        websocket = new SockJS(socketJSURL);
    }
    websocket.onopen = onOpen;
    websocket.onmessage = onMessage;
    websocket.onerror = onError;
    websocket.onclose = onClose;

    function onOpen(openEvt) {
        alert(openEvt.Data);
    }

    function onMessage(evt) {
        alert(evt.data);
    }
    function onError() {}
    function onClose() {}

    function doSend() {
        if (websocket.readyState == websocket.OPEN) {
            var msg = document.getElementById("inputMsg").value;
            websocket.send(msg);//调用后台handleTextMessage方法  
            alert("发送成功!");
        } else {
            alert("连接失败!");
        }
    }
</script>
请输入：<textarea rows="5" cols="10" id="inputMsg" name="inputMsg"></textarea>
<button onclick="doSend();">发送</button>
</body>
</html>