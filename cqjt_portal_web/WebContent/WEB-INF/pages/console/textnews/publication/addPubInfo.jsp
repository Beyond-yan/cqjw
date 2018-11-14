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
a {color:blue;cursor: pointer;}
.column {min-width:940px;}
.error{color:red;padding-left: 10px;}
</style>
<title>投稿</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		信息报送
		<span>-></span>
			<a href="govTextNews.xhtml?face=listPubInfoView">政务信息刊物管理</a>
		<span>-></span>
		刊物新增
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>刊物新增</h2></div>
        <div class="con docEdit" >
			<form id="pubInfoForm" enctype="multipart/form-data" method="POST">
            <input type="hidden" name="isPublic" id="isPublic">
            <ul>
                <li>
                    <label><span style="color:red">*</span>创建人：</label>
                    <input type="text" name="createUser" class="input;"  value="${createUserName}" readonly="true" style="color:gray">
                </li>
                <li>
	                <label><span style="color:red">*</span>刊物名称：</label>
	                <input type="text" name="pubName" class="input input-small">
                </li>
                <li>
	                <label><span style="color:red">*</span>刊物号：</label>
	                <input type="text" name="pubCode" class="input input-small">
                </li>
                <li>
                	<label></label>
                    <a href="javascript:;" class="btn-gray" onclick="savePubInfo(0)"><b class="icon i-send"></b>保 存</a>
                    <a href="govTextNews.xhtml?face=listPubInfoView" class="btn-gray"><b class="icon i-back"></b>返 回</a>
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
//window.APP_HOME_URL = "${pageContext.request.contextPath}";

$(function(){
	$("input[name='createUser']").keyup(function(){
		var title1 = $(this).val();
		if(title1 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>创建人不能为空</span>");
		}
	});
	$("input[name='pubName']").keyup(function(){
		var title2 = $(this).val();
		if(title2 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>刊物名称不能为空</span>");
		}
	});
	$("input[name='pubCode']").keyup(function(){
		var title3 = $(this).val();
		if(title3 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>刊物号不能为空</span>");
		}
	});
	
});    
function savePubInfo(flag) {
  	var user =  $("input[name='createUser']").val();
 	var name = $("input[name='pubName']").val();
	var code = $("input[name='pubCode']").val(); 
	//校验输入框
    if(user =="" && user.length == 0){
    	$("input[name='createUser']").next(".error").remove();
		$("input[name='createUser']").after("<span class='error'>创建人不能为空</span>");
		return;
	}     
 	if(name == "" && name.length == 0){
 		$("input[name='pubName']").next(".error").remove();
		$("input[name='pubName']").after("<span class='error'>刊物名称不能为空</span>");
		return;
	}
	if(code ==""){
		$("input[name='pubCode']").next(".error").remove();
		$("input[name='pubCode']").after("<span class='error'>刊物号不能为空</span>");
		return;
	}
	$("#isPublic").val(flag);
	
	var options = {
		url : 'govTextNews.xhtml?action=savePubInfo',
		type : 'post',
		dataType : "html",
		success : function(data) {			
			 var data = eval('(' + data + ')');
			asyncbox.confirm(data.msg + "是否清空表单？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "govTextNews.xhtml?face=listPubInfoView";
				}
			}); 
		},
		error :function(data){
			var data = eval('(' + data + ')');
			asyncbox.confirm(data.msg + "是否清空表单？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.reload();
				}
			}); 
		/* alert("内部错误，保存失败"); */ 
		}
	};
	$("#pubInfoForm").ajaxSubmit(options);
}
</script>
</html>
