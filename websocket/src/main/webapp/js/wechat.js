/**
 * 
 * @authors limeizhang (zhanglimeibupt@163.com)
 * @date    2015-08-15 23:05:11
 * @version $Id$
   弹窗
 */

(function(){

	//格式化时间
	Date.prototype.Format = function(){
        var now = new Date();
        var date = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate()+" "+now.getHours()+":"+now.getMinutes()+":"+now.getSeconds();
        return date;
	}
	

	//聊天详情
	var ws = null;
    var webSocketURL = 'ws://' + window.location.host + "/websocket/chatServer.do";
    var socketJSURL = 'ws://' + window.location.host +  "/websocket/sockjs/chatServer.do";
    var transports = [];

    function setConnected(connected) {
        document.getElementById('echo').disabled = !connected;
    }

    function connect() {
    	console.log("connected successtip");
        if ('WebSocket' in window) {
            ws = new WebSocket(webSocketURL);
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket(webSocketURL);
        } else {
            ws = new SockJS(socketJSURL);
        }

        ws.onopen = function () {
            setConnected(true);
            log('infomation','现在您可以进行聊天了！');
        };
        ws.onmessage = function (event) {
            log('ms_rp',event.data);
        };
        ws.onclose = function (event) {
            setConnected(false);
            // log('infomation','对话已关闭！');
            // log('',event);
        };
    }

    connect();

    function disconnect() {
        if (ws != null) {
            ws.close();
            ws = null;
        }
        setConnected(false);
    }

    function echo() {
        if (ws != null) {
            var message = document.getElementById('message').value;
            // log('ms_my',message);
            if($.trim(message)!=""){
                ws.send(message);
                log('ms_my',message);
                $("#message").val("");//.focus();
            }
        } else {
            alert('未建立对话连接，请重试！');
        }
    }

    function log(author,message) {
        var console = document.getElementById('console');
        var time = "";
        if(author == 'ms_my'){
        	time = new Date().Format(); 
        }else if(author == 'ms_rp'){
        	var i = message.indexOf(';');
        	time = message.substring(0,i);
        	message = message.substring(i+1);
        }

        $("#console").append("<li class='"+author+"'><span>"+time+"</span><div class='ms_content'>"+message+"</div></li>");
        // $("#message").val("");
        console.scrollTop = console.scrollHeight;
    }

    //发送消息
    $("#echo").on("click",echo);

})();
