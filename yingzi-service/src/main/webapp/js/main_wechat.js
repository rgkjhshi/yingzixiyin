/**
 * 
 * @authors limeizhang (zhanglimeibupt@163.com)
 * @date    2015-07-04 17:01:19
 * @version $Id$
 */
(function(){
	$("#choose").on("click",function(){
		$("#choose_layer").show();
		$("#choose_dialog").show();
	});
	$("#choose_layer").on("click",function(){
		$("#choose_layer").hide();
		$("#choose_dialog").hide();
	});

	$(".header i").on("click",function(){
		window.history.go(-1);
	});
	
	$("#choose_g_a").on("click",function(){
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
				$("#choose_dialog").hide();
				data = $.parseJSON(data);
				if(data.returnCode==0){
					$("#consultant_list").empty();
					if((!data.list)||data.list.length==0){
						$("#consultant_list").append("没有符合条件的咨询师噢！");
					}else{
						var l = data.list.length;
						var tpl = "";
						for(var i = 0;i<l;i++){
							var _data = data.list[i];
							tpl += '<li class="items"><a href="consultant_deatil.do?id='+_data.id+'"><img src="'+_data.avatar+'"><div class="info"><div class="name">'+_data.name+'</div><div class="desc">'+_data.introduce+'</div></div></a></li>'
						}
						$("#consultant_list").append(tpl);
					}
				}else{
					alert("查询异常，请重试。");
				}
			},
			error:function(){
				$("#choose_dialog").hide();
				alert("对不起，出错啦！");
			}
		});
	});
})();
