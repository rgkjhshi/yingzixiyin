/**
 * 
 * @authors limeizhang (zhanglimeibupt@163.com)
 * @date    2015-07-21 23:05:11
 * @version $Id$
   弹窗
 */
 
 function init(){
 	var layer = $("<div class='layer'></div>");
 	var tips = $('<div class="detail_box"><i id="closeDialogBtn"></i><div class="dialog_header" node-type="header">来自&nbsp;<strong>张三</strong>&nbsp;的咨询</div><div class="dialog_body"><div class="dialog_content"><ul id="ms_inner"></ul></div><div class="dialog_reply"><div class="reply_inner"><textarea class="reply_content"></textarea><a class="send_btn">发送</a></div></div></div>');
 	$("body").append(layer).append(tips);
 }

 function showDialog(type,mid){//传入弹窗类型：咨询详情   mid：咨询id
 	if(type=="messagedetail"){

 		$(".layer").show();
 		$(".detail_box").show();
 		
  		//根据mid拉取所有未读消息
 		var url = "";
 		/*
 		$.getJSON(url,function(data){
 			//将data添加到弹窗中
 			//var l = data.length;
 			var tpl = "";
 			for(var i=0;i<l;i++){
 				//data.source: 消息来源－用户发送 user /我发送的 me
 				//data.time: 消息发送的时间
 				//data.content: 消息的具体内容
 				tpl += '<li class="'+data.source+'"><span>'+data.time+'</span><div>'+data.content+'</div></li>';
 			}
 			$("#ms_inner").append(tpl);
 		});
		*/

		//test data below
		var tpl = '<li class="user"><span>2015-07-20 15:30:21</span><div>您好。</div></li><li class="user"><span>2015-07-20 15:30:21</span><div>我想咨询几个问题。</div></li><li class="me"><span>2015-07-20 15:30:21</span><div>您好，请讲。</div></li><li class="user"><span>2015-07-20 15:30:21</span><div>就是啊，我最近啊，写代码总是很不顺利，我就想试试换行之后的效果是什么样的，会不会有什么bug，作为一个程序员还真的是拼了呀，我的天哪。哈哈哈，就先这样吧。</div></li><li class="me"><span>2015-07-20 15:30:21</span><div>您好，你这是病呀，这得治啊。</div></li><li class="me"><span>2015-07-20 15:30:21</span><div>你找我就对了，我是人称的张神医，啊哈哈，赶紧给我一万块，不然揍你哦！</div></li><li class="user"><span>2015-07-20 15:30:21</span><div>我还想测一下滚动条呢，看看怎么样。</div></li>';
		$("#ms_inner").append(tpl);
 	}else{
 		console.log("弹窗类型参数有误");
 	}
 }

function hideDialog(type,mid){
	$(".detail_box").hide();
	$("#ms_inner").empty();
	$(".layer").hide();
}

init();

//打开咨询详情弹窗
$(".message_item").on("click",function(){
	showDialog("messagedetail",312);  // 312 is just an example
});

//关闭弹窗
$("#closeDialogBtn").on("click",function(){
	hideDialog();
});
