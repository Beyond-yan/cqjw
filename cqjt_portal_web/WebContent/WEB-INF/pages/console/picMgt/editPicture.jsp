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
<link href="${pageContext.request.contextPath}/plugin/ueditor/themes/default/css/ueditor.css" rel="stylesheet" type="text/css" />
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
.column {min-width:940px;}
.error{color:red;padding-left: 10px;}
</style>
<title>内容管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>内容管理：</span>
		图片管理
        <span>-></span>
		编辑
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>图片信息编辑</h2></div>
        <div class="con docEdit" >
			<form id="picNewsForm" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="picId" value="${picture.picId}"/>
			<input type="hidden" name="flag" id="newsFlag" />
            <ul>
                <li>
	                <label><span style="color:red">*</span>标题：</label>
	                <input type="text" name="picTitle" class="input input-medium" value="${picture.picTitle}">
                </li>
                <li>
	                <label><span style="color:red">*</span>副标题：</label>
	                <input type="text" name="subTitle" class="input input-medium" value="${picture.subTitle}">
                </li>
                <li>
                    <label><span style="color:red">*</span>作者：</label>
                    <input type="text" name="entryUser" class="input input-small" value="${picture.entryUser}" readOnly="true" >                   
	                <label><span style="color:red"></span>录入时间：</label>
                    <input type="text" name="entryDate" class="input input-small" pattern="yyyy-MM-dd" value="${picture.entryDate}" readOnly="true">
                </li>  
                    <label><span style="color:red">*</span>文档来源：</label>
                    <input type="text" name="source" class="input input-small" value="${picture.source}">
                    <label>审稿人：</label>
                    <select class="select-small" name="approvaler" style="width:192px">
						<c:forEach items="${picture.approvaler}" varStatus="status">
	                    <option>${status.current}</option>
	                    </c:forEach>
	               </select>
                </li>
                <li>
                	<label><span style="color:red">*</span>主图片：</label>
                	<input type="file" name="picFile" />
                	<input type="button" value="图片预览" onclick="show()">
                	<!--<label>图片链接：</label><a href="${StaticResourcePath}/${picture.picUrl}" target="_blank">${picture.picUrl}</a>-->
                	<input type="hidden" name="photo" value="${picture.picUrl}"/>
         	        <input type="hidden" id="picUrl" name="picUrl" class="input input-small">
                </li>
                <li>
               		<label></label>
                    <a href="javascript:;" class="btn-gray" onclick="savePicNews(1)"><b class="icon i-send"></b>发布</a>
                    <a href="javascript:;" class="btn-gray" onclick="savePicNews(0)"><b class="icon i-save"></b>存草稿</a>
                    <a href="javascript:history.go(-1);" class="btn-gray"><b class="icon i-back"></b>返 回</a>
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
	 window.open ('${StaticResourcePath}/${picture.picUrl}','add', 
	 'height=600, width=800, top=100, left=200, toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no, status=no') ;
	 }	
function checkFormData() {
	//待完成
	return true;
}
$(function(){
	$("input[name='picTitle']").keyup(function(){
		var title1 = $(this).val();
		if(title1 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>标题不能为空</span>");
		}
	});
	$("input[name='picFile']").click(function(){
		$(this).next(".error").remove();
	}); 	
});    
function savePicNews(flag) {
  	var picTitle =  $("input[name='picTitle']").val();
 	var entryUser = $("input[name='entryUser']").val();
	var subTitle = $("input[name='subTitle']").val();
	var source = $("input[name='source']").val();
	var picFile = $("input[name='picFile']").val();
    if(picTitle=="" && picTitle.length == 0){
    	$("input[name='picTitle']").next(".error").remove();
		$("input[name='picTitle']").after("<span class='error'>标题不能为空</span>");
		return;
	}     
 	if(entryUser == "" && entryUser.length == 0){
 		$("input[name='entryUser']").next(".error").remove();
		$("input[name='entryUser']").after("<span class='error'>作者不能为空</span>");
		return;
	}
 	if(subTitle == "" && subTitle.length == 0){
 		$("input[name='subTitle']").next(".error").remove();
		$("input[name='subTitle']").after("<span class='error'>副标题不能为空</span>");
		return;
	}
 	if(source == "" && source.length == 0){
 		$("input[name='source']").next(".error").remove();
		$("input[name='source']").after("<span class='error'>来源不能为空</span>");
		return;
	}
	$("#newsFlag").val(flag);
	$("#picUrl").val(picFile);
	var options = {
		url : 'pic.xhtml?action=savePicInfo',
		type : 'post',
		dataType : "html",
		beforeSubmit : checkFormData,
		success : function(data) {			
			var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg, "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "pic.xhtml?face=getPiclistView";
				}
				else {
					document.location.reload();
				}
			}); 
		},
		error :function(data){
			alert("内部错误，保存失败");
		}
	};
	$("#picNewsForm").ajaxSubmit(options);
}
function previewVideoNews(){
	location.href ="videoNews.xhtml?face=previewVideoNewsView";
}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.all.min.js"></script>
</html>
