<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
.column {min-width:940px;}
.error{color:red;padding-left: 10px;}
</style>
<title>视频报送</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		视频报送
        <span>-></span>
		报送
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>视频信息录入</h2></div>
        <div class="con docEdit" >
			<form id="videoMgtForm" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="flag" id="newsFlag" />
            <ul>
                <li>
	                <label><span style="color:red">*</span>标题：</label>
	                <input type="text" name="videoTitle" class="input input-medium"  style="padding-right: 50px;">
                </li>
                
                <li>
                    <label><span style="color:red">*</span>作者：</label>
                    <input type="text" name="entryUser" class="input input-small" value="${userName}" readOnly="true">                   
	                <label><span style="color:red"></span>录入时间：</label>
                    <input type="text" name="createDate" class="input input-small" value="${createDate}" readOnly="true">
                </li>
                
                <li>
                    <label>来源出处：</label>
                    <input type="text" name="videoSource" class="input input-small">
                    
                    <label><span style="color:red">*</span>投稿部门：</label>
                    <select class="select-small" style="margin:4px;width: 190px;" disabled="disabled" name="entryDeptName">
	                    <option value="${userDeptID}">${userDeptName}</option>
	                </select>
	                <input type="hidden" name="entryDeptName" value="${userDeptName}" class="input input-small">
	                <input type="hidden" name="entryDeptId" value="${userDeptID}" class="input input-small">
                </li>
               <li>
                      <label><span style="color:red">*</span>上传视频：</label>
                      <input type="file" name="videoFile">
                      <input type="hidden" name="video" value="${video.videoUrl}"/>
                      <input type="hidden" id="videoUrl" name="videoUrl" class="input input-small">
                </li>
                <li>
                	<label><span style="color:red">*</span>主图片：</label>
                	<input type="file" name="photoFile" />
                	<input type="hidden" name="photo" value="${video.photoUrl}"/>
                	<input type="hidden" id="photoUrl" name="photoUrl" class="input input-small">
                </li>
                <li>
               		<label></label>
                </li>
                <li>
               		<label></label>
                    <a href="javascript:;" class="btn-gray" onclick="saveVideoNews(1)"><b class="icon i-send"></b>视频录入</a>
                    <a href="javascript:;" class="btn-gray" onclick="saveVideoNews(0)"><b class="icon i-save"></b>存草稿</a>
                    <a href="videoMgt.xhtml?face=listView" class="btn-gray"><b class="icon i-back"></b>返 回</a>
                </li>
            </ul>
            </form>
        </div>
    </div>
    <!--column01 End-->
</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
window.APP_HOME_URL = "${pageContext.request.contextPath}";

function checkFormData() {
	//待完成
	return true;
}
$(function(){
	$("input[name='videoTitle']").keyup(function(){
		var title1 = $(this).val();
		if(title1 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>标题不能为空</span>");
		}
	});
	$("input[name='entryUser']").keyup(function(){
		var title2 = $(this).val();
		if(title2 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>作者不能为空</span>");
		}
	});
	
	$("input[name='videoFile']").click(function(){
		$(this).next(".error").remove();
	}); 
	$("input[name='photoFile']").click(function(){
		$(this).next(".error").remove();
	}); 
	
});    
function saveVideoNews(flag) {
  	var videoTitle =  $("input[name='videoTitle']").val();
 	var entryUser = $("input[name='entryUser']").val();
	var approvaler = $("select[name='approvaler']").val();
	var videoFile = $("input[name='videoFile']").val();
	var photoFile = $("input[name='photoFile']").val();
	//校验输入框
    if(videoTitle =="" && videoTitle.length == 0){
    	$("input[name='videoTitle']").next(".error").remove();
		$("input[name='videoTitle']").after("<span class='error'>标题不能为空</span>");
		return;
	}     
 	if(entryUser == "" && entryUser.length == 0){
 		$("input[name='entryUser']").next(".error").remove();
		$("input[name='entryUser']").after("<span class='error'>作者不能为空</span>");
		return;
	}
 	if(videoFile =="" && videoFile.length == 0){
    	$("input[name='videoFile']").next(".error").remove();
		$("input[name='videoFile']").after("<span class='error'>视频不能为空</span>");
		return;
	}else{
		$("input[name='videoFile']").next(".error").remove();
	}
 	if(photoFile =="" && photoFile.length == 0){
    	$("input[name='photoFile']").next(".error").remove();
		$("input[name='photoFile']").after("<span class='error'>图片不能为空</span>");
		return;
	}else{
		$("input[name='photoFile']").next(".error").remove();
	}
 	
	$("#newsFlag").val(flag);
	$("#photoUrl").val(photoFile);
	$("#videoUrl").val(videoFile);
	var options = {
		url : 'videoMgt.xhtml?action=saveVideoInfo',
		type : 'post',
		dataType : "html",
		beforeSubmit : checkFormData,
		success : function(data) {			
			var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg + "点击“确定”,继续录入信息,点击“取消”,将返回到查询页面！", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "videoMgt.xhtml?face=addVideoMgtView";
				}
				else {
					document.location.href = "videoMgt.xhtml?face=listView";
				}
			}); 
			/* alert("保存成功");
			document.location.href = "textNews.xhtml?face=addView";*/
		},
		error :function(data){
			alert("内部错误，保存失败");
		}
	};
	$("#videoMgtForm").ajaxSubmit(options);
}
</script>
</html>
