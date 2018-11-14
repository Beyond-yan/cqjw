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
<title>杂志刊物管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		杂志刊物报送
        <span>-></span>
	      报送
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>杂志刊物信息录入</h2></div>
        <div class="con docEdit" >
			<form id="magazineNewsForm" enctype="multipart/form-data" method="POST">
            <input type="hidden" name="flag" id="newsFlag" />
            <ul>
                <li>
	                <label><span style="color:red">*</span>标题：</label>
	                <input type="text" name="Title" class="input input-medium">
                </li>
                    <li>
                    <label><span style="color:red">*</span>投稿人：</label>
                    <input type="text" name="entryUser" class="input input-small" value="${entryUser}" > 
                    </li>
                    <li>                  
	                <label><span style="color:red">*</span>录入时间：</label>
                    <input type="text" name="entryDate" class="input input-small" value="${entryDate}" readOnly="true" >
                    </li>
                    <li>
                    <label><span style="color:red">*</span>杂志期数：</label>
                    <input type="text" name="magazineNo" class="input input-small" value="${magazineNo}">
                    </li>
                    <li>  
                      <label><span style="color:red">*</span>上传文件：</label>
                      <input type="file" name="magazineFile">
                      <input type="hidden" name="magazine" />
                      <input type="hidden" id="magazineUrl" name="magazineUrl" class="input input-small"> 
                     </li>
                 <li id="showPhotoState" >
                	<label><span style="color:red">*</span>主图片：</label>
                	<input type="file" name="photoFile" />
                	<input type="hidden" name="photo" />
                	<input type="hidden" id="photoUrl" name="photoUrl" class="input input-small">
                 </li>
                <li>
               		<label></label>
                </li>
                <li>
               		<label></label>
                    <a href="javascript:;" class="btn-gray" onclick="savemagazineNews(1)"><b class="icon i-send"></b>发布</a>
                    <a href="javascript:;" class="btn-gray" onclick="savemagazineNews(0)"><b class="icon i-save"></b>存草稿</a>
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
function checkFormData() {
	//待完成
	return true;
}
$(function(){
	$("input[name='title']").keyup(function(){
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
	$("input[name='magazineNo']").keyup(function(){
		var title3 = $(this).val();
		if(title3 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>杂志期数不能为空</span>");
		}
	});
	
	$("input[name='magazineFile']").click(function(){
		$(this).next(".error").remove();
	}); 
	$("input[name='photoFile']").click(function(){
		$(this).next(".error").remove();
	}); 
	
});    
function savemagazineNews(flag) {
  	var Title =  $("input[name='title']").val();
 	var entryUser = $("input[name='entryUser']").val();
 	var magazineNo = $("input[name='magazineNo']").val();
	var magazineFile = $("input[name='magazineFile']").val();
	var photoFile = $("input[name='photoFile']").val();
	//校验输入框
    if(Title=="" && Title.length == 0){
    	$("input[name='title']").next(".error").remove();
		$("input[name='title']").after("<span class='error'>标题不能为空</span>");
		return;
	}     
 	if(entryUser == "" && entryUser.length == 0){
 		$("input[name='entryUser']").next(".error").remove();
		$("input[name='entryUser']").after("<span class='error'>作者不能为空</span>");
		return;
	}
 	if(magazineNo == "" && magazineNo.length == 0){
 		$("input[name='magazineNo']").next(".error").remove();
		$("input[name='magazineNo']").after("<span class='error'>杂志期数不能为空</span>");
		return;
	}
 	if(magazineFile =="" && magazineFile.length == 0){
    	$("input[name='magazineFile']").next(".error").remove();
		$("input[name='magazineFile']").after("<span class='error'>杂志文件不能为空</span>");
		return;
	}else{
		$("input[name='magazineFile']").next(".error").remove();
	}
 	if(photoFile =="" && photoFile.length == 0){
    	$("input[name='photoFile']").next(".error").remove();
		$("input[name='photoFile']").after("<span class='error'>图片不能为空</span>");
		return;
	}else{
		$("input[name='photoFile']").next(".error").remove();
	}
 	
	$("#newsFlag").val(flag);
	$("#magazineUrl").val(magazineFile);
	$("#photoUrl").val(photoFile);
	
	var options = {
		url : 'magazine.xhtml?action=saveMagazineInfo',
		
		type : 'post',
		dataType : "html",
		beforeSubmit : checkFormData,
		success : function(data) {			
			var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg + "点击“确定”,继续录入信息,点击“取消”,将返回到查询页面！", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "magazine.xhtml?face=addMagazine";
				}
				else {
					document.location.href = "magazine.xhtml?face=getMagazinelistView";
				}
			}); 
		
		},
		error :function(data){
			alert("内部错误，保存失败");
		}
	};
	$("#magazineNewsForm").ajaxSubmit(options);
}

</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.all.min.js"></script>
</html>
