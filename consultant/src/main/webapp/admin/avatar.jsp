<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>头像管理-英姿吸引</title>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <link href="../favicon.ico" rel="shortcut icon">
        <link href="../css/base.css" rel="stylesheet" type="text/css">
        <script src="../js/jquery-1.9.1.min.js"></script>
        <script src="http://cdn.bootcss.com/jquery.form/3.51/jquery.form.min.js"></script>
    </head>
    <body>
        <script>
        function checkimgs(){
            // 判断图片类别
            var path = $("#filepath").val();
            if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(path)) {
                alert("图像格式有误！");
                return false;
            }
            // 判断图片大小
            var input = $("body").find("input[type='file']")[0];
            var size = input.files[0].size / 1024;
            if (size > 1000) { //3M
                alert("图片不得大于1M！");
                return false;
            }
            return true;
        }

        //头像上传 图片上传预览    IE是用了滤镜。
        function previewImage(file){
            // $("#preview").css("background-image":"none");
            var bo = checkimgs();
            if(!bo){
                // return;
                window.location.reload();
            }
            var MAXWIDTH  = 260; 
            var MAXHEIGHT = 180;
            var div = document.getElementById('preview');
            if (file.files && file.files[0]){
                $(".avatar_preview").show();
                var reader = new FileReader();
                reader.onload = function(evt){
                    $(".avatar_preview").css("background-image","url("+evt.target.result+")");
                }
                reader.readAsDataURL(file.files[0]);
            }else{//兼容IE
                try{
                    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
                    file.select();
                    var src = document.selection.createRange().text;
                    div.innerHTML = '<img id=imghead>';
                    var img = document.getElementById('imghead');
                    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
                    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
                    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
                }catch(err){
                    alert("IE浏览器下不支持图片预览，推荐使用chrome浏览器");
                }
            }
            $("#upload_btn").css("display","inline-block");
            $(".file_upload").css({"background":"#aaa","border-color":"#ccc"})
            $("#chooseImgText").text("重新选择");
        }
        function clacImgZoomParam(maxWidth, maxHeight, width, height ){
            var param = {top:0, left:0, width:width, height:height};
            if( width>maxWidth || height>maxHeight )
            {
                rateWidth = width / maxWidth;
                rateHeight = height / maxHeight;
                 
                if( rateWidth > rateHeight )
                {
                    param.width =  maxWidth;
                    param.height = Math.round(height / rateWidth);
                }else
                {
                    param.width = Math.round(width / rateHeight);
                    param.height = maxHeight;
                }
            }
             
            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);
            return param;
        }
        </script>
        <%@ include file="../parts/head.jsp" %> 
        <div class="info">
            <div class="layout">
            	<%@ include file="../parts/left.jsp" %> 
            	<div class="maincontent">
            		<div class="subtitle">上传头像</div>
                    <div class="mainup">
                        <ul class="up_tips">
                            <li class="uc-tips">
                                IE浏览器下无法进行头像实时预览，建议您使用chrome、firefox等浏览器上传。
                                <br/>
                                建议上传头像长宽比例为1:1。最佳尺寸为100*100。上传图片大小不能超过1M。
                            </li>
                        </ul>
                        <form id="avatarform" method="post" enctype="multipart/form-data" action="/consultant/admin/uploadPicApi.htm">
                            <div class="sign_content file_upload"><span id="chooseImgText">选择图片</span>
                                <input type="file" id="filepath" name="img" onchange="previewImage(this)"/>
                            </div>
                            <div class="avatar_preview" id="preview"><img id="imghead" src="" /></div>
                            <div id="upload_btn">保存</div>
                        </form>
                    </div>
            	</div>
            </div>
        </div>
        <%@ include file="../parts/foot.jsp" %> 
    <script src="../js/main_consultant.js"></script>
    <script>
        $("#info").addClass("cur");
        $("#info_all").show();
        $("#avatarinfo").addClass("active");
    </script>
    </body>
</html>
