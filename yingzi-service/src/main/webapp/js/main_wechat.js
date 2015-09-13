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
	$(".choose_close").on("click",function(){
		$("#choose_layer").hide();
		$("#choose_dialog").hide();
	});

	// $(".header i").on("click",function(){
	// 	window.history.go(-1);
	// });
	
	$("#choose_g_a").on("click",function(){
		var gender = $("input[name='gender']:checked").val();
		var age = $("input[name='age']:checked").val();
		var ctype = $("#ctype").val();
		// alert("gender:"+gender+",age:"+age+",ctype:"+ctype);
		// alert("befor post");
		$.post("/weixin/consultant/query_consultants.do",{"gender":gender,"age":age,"ctype":ctype},function(data){
			
			$("#choose_layer").hide();
			$("#choose_dialog").hide();
			// alert("before parse");
			data = $.parseJSON(data);
			// alert("after parse");
			// alert(typeof data + ", typeof data");
			// alert("returnCode:"+data.returnCode);
			if(data.returnCode==0||data.returnCode=="0"){
				// console.log("gaga");
				$("#consultant_list").empty();
				// alert(typeof data.list+", typeof data.list");
				if((!data.list)||data.list.length==0){
					$("#consultant_list").append("没有符合条件的咨询师噢！");
				}else{
					var l = data.list.length;
					// alert("数据一共有"+l+"条");
					var tpl = "";
					for(var i = 0;i<l;i++){
						var _data = data.list[i];
						var _avatar = _data.avatar;
						if(_avatar==""||_avatar==null){
							_avatar = "../images/test.png";
						}
						tpl += '<li class="items"><a href="consultant_deatil.do?id='+_data.id+'"><img src="'+_avatar+'"><div class="info"><div class="name nameall">'+_data.name+'</div><div class="desc descall">'+_data.age+'岁，¥ '+_data.videoPrice+'元/次，地址：'+_data.address+'</div></div><div class="sig">签名：'+_data.signature+'</div></a></li>'
					}
					// alert(tpl);
					$("#consultant_list").append(tpl);
				}
			}else{
				alert("查询异常，请重试。");
			}
		});
	});
	
	
})();
