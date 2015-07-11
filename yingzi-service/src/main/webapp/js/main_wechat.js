/**
 * 
 * @authors limeizhang (zhanglimeibupt@163.com)
 * @date    2015-07-04 17:01:19
 * @version $Id$
 */
(function(){
	$("#choose").on("tap",function(){
		$("#choose_layer").show();
		$("#choose_dialog").show();
	});
	$("#choose_layer").on("tap",function(){
		$("#choose_layer").hide();
		$("#choose_dialog").hide();
	});

	$(".header i").on("tap",function(){
		window.history.go(-1);
	});
})();
