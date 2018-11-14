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
		视频信息编辑
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>视频信息编辑</h2></div>
        <div class="con docEdit" >
			<form id="videoMgtForm" enctype="multipart/form-data" method="POST">
            	<input type="hidden" name="videoId" value="${video.videoId}">
            	<input type="hidden" name="flag" id="VideoFlag" />
            	<input type="hidden" name="fileName" value="${video.fileName}">
            	<input type="hidden" name="imageName" value="${video.imageName}">    	
            <ul>
                <li>
	                <label><span style="color:red">*</span>标题：</label>
	                <input type="text" name="videoTitle" class="input input-medium"value="${video.videoTitle}"  style="padding-right: 50px;">
                </li>
                
                <li>
                    <label><span style="color:red">*</span>作者：</label>
                    <input type="text" name="entryUser" class="input input-small" value="${video.entryUser}" readOnly="true">
                      <label><span style="color:red"></span>录入时间：</label>
                    <input type="text" name="createDate" class="input input-small" value="${video.createDate}" readOnly="true" >
                </li> 
                <li>
                    <label>来源出处：</label>
                    <input type="text" name="videoSource" class="input input-small"value="${video.videoSource}">
                      <label><span style="color:red">*</span>投稿部门：</label>
                    <select class="select-small" style="margin: 4px; width: 190px;" disabled="disabled" name="entryDeptName">
	                    <option value="${video.entryDeptId}">${video.entryDeptName}</option>
	                </select>
	                <input type="hidden" name="entryDeptName" value="${video.entryDeptName}" class="input input-small">
	                <input type="hidden" name="entryDeptId" value="${video.entryDeptId}" class="input input-small">
                </li>            
               <li>
                      <label><span style="color:red">*</span>上传视频：</label>
                      <input type="file" name="videoFile" />
                      <input type="button" value="视频预览" onclick="show1()">
                      <!--<label>视频链接：</label><a href="${StaticResourcePath}/${video.videoUrl}" target="_blank">${video.videoUrl}</a>-->
                      <input type="hidden" name="video" value="${video.videoUrl}"/>
                      <input type="hidden" id="videoUrl" name="videoUrl" class="input input-small">
                </li>
                <li id="showPhotoState" >
                	<label><span style="color:red">*</span>主图片：</label>
                	<input type="file" name="photoFile" />
                	<input type="button" value="图片预览" onclick="show()">
                	<!-- <label>图片链接：</label><a href="${StaticResourcePath}/${video.photoUrl}" target="_blank">${video.photoUrl}</a>-->
                	<input type="hidden" name="photo" value="${video.photoUrl}"/>
                	<input type="hidden" id="photoUrl" name="photoUrl" class="input input-small">
                </li>
                 <li>
               		<label></label>
                </li>
                <li>
                <label></label>
                    <a href="javascript:;" class="btn-gray" onclick="saveTextNews(1)"><b class="icon i-send"></b>视频录入</a>
                    <a href="javascript:;" class="btn-gray" onclick="saveTextNews(0)"><b class="icon i-save"></b>存草稿</a>
                    <a href="javascript:history.back(-1);" class="btn-gray"><b class="icon i-back"></b>返 回</a>
                    <!-- <a href="videoNews.html?face=listView" class="btn-gray"><b class="icon i-back"></b>返 回</a> -->
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
function show(){
	 window.open ('${StaticResourcePath}/${video.photoUrl}','add', 
	 'height=600, width=800, top=100, left=200, toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no, status=no') ;
	 }	
function show1(){
	 window.open ('${StaticResourcePath}/${video.videoUrl}','add', 
	 'height=600, width=800, top=100, left=200, toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no, status=no') ;
	 }	
function checkFormData() {
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
});    
function saveTextNews(flag) {
  	var videoTitle =  $("input[name='videoTitle']").val();
 	var entryUser = $("input[name='entryUser']").val();
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
 	
	$("#VideoFlag").val(flag);
	
	$("#photoUrl").val(photoFile);
	$("#videoUrl").val(videoFile);
	
	var options = {
		url : 'videoMgt.xhtml?action=saveVideoInfo',
		type : 'post',
		dataType : "html",
		beforeSubmit : checkFormData,
		success : function(data) {			
			var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg +"是否返回视频查询页面？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "videoMgt.xhtml?face=listView";
				}
			}); 
		},
		error :function(data){
			alert("内部错误，保存失败");
		}
	};
	$("#videoMgtForm").ajaxSubmit(options);
}
</script>
</html>
