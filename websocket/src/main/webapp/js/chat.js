/**
 * 
 * @authors limeizhang (zhanglimeibupt@163.com)
 * @date    2015-08-08 23:05:11
 * @version $Id$
   弹窗
 */

(function(){

	//格式化时间
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
    	if (/(y+)/.test(fmt)){
    		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    	}
	    for (var k in o){
	    	if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    	return fmt;
	    }
	}
	

	

	//聊天详情
	var ws = null;
    var webSocketURL = 'ws://' + window.location.host + "/websocket/chatServer.do";
    var socketJSURL = 'ws://' + window.location.host +  "/websocket/sockjs/chatServer.do";
    var transports = [];

    function setConnected(connected) {
        // document.getElementById('connect').disabled = connected;
        // document.getElementById('disconnect').disabled = !connected;
        document.getElementById('echo').disabled = !connected;
    }

    function connect() {
    	console.log("connected successtip");
        if ('WebSocket' in window) {
            ws = new WebSocket(webSocketURL);
            alert(webSocketURL);
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket(webSocketURL);
        } else {
            ws = new SockJS(socketJSURL);
        }

        ws.onopen = function () {
            setConnected(true);
            log('info','connection opened.');
        };
        ws.onmessage = function (event) {
            log('user',event.data);
        };
        ws.onclose = function (event) {
            setConnected(false);
            log('info','connection closed.');
            log(event);
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
            log('me',message);
            ws.send(message);
        } else {
            alert('connection not established, please connect.');
        }
    }

    function log(author,message) {
        var console = document.getElementById('console');
        var time = "";
        if(author == 'me'){
        	time = new Date().Format("yyyy-MM-dd HH:mm:ss"); 
        }else if(author == 'user'){
        	var i = message.indexOf(';');
        	time = message.substring(0,i);
        	message = message.substring(i);
        }
        console.appendChild("<li class='"+author+"'><span>"+time+"</span><div>"+message+"</div></li>");
        while (console.childNodes.length > 25) {
            console.removeChild(console.firstChild);
        }
        console.scrollTop = console.scrollHeight;
    }

    //发送消息
    $("#echo").on("click",echo);

})();
