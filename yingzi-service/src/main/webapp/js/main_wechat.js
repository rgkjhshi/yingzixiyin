/**
 * 
 * @authors limeizhang (zhanglimeibupt@163.com)
 * @date    2015-07-04 17:01:19
 * @version $Id$
 */
//alert("test");
(function(){
//	alert("a");
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
	
	$("#choose_g_a").on("tap",function(){
		var gender = $("input[name='gender']:checked").val();
		var age = $("input[name='age']:checked").val();
		var ctype = $("#ctype").val();
		$.ajax({
			url:"query_consultants.do",
			async:true,
			type:"post",
			data:{"gender":gender,"age":age,"ctype":ctype},
			success:function(data){
				console.log(data);
			},
			error:function(){
				alert("error");
			}
		});
	});
//	alert("b");
})();
