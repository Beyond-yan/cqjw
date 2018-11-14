<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><!DOCTYPE html 
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
a {color:blue;cursor: pointer;}
.column {min-width:940px;}
.error{color:red;padding-left: 10px;}
</style>
<title>新增人事变动</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		内容管理
        <span>-></span>
		新增人事变动
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>信息录入</h2></div>
        <div class="con docEdit" >
			<form id="textNewsForm" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="personContent" id="personContent" />
			<input type="hidden" name="entryDept" value="${userDeptID}"/>
            <ul><br/>
                <li>
	                <label><span style="color:red">*</span>信息标题：</label>
	                <input maxlength="50" type="text" name="personTitle" class="input input-large">
                </li>
                 <br/>
                <li>
                	<label><span style="color:red">*</span>上传人：</label>
                    <input maxlength="20" type="text" name="pubUser"  value="${userName}"class="input input-small">
               		<label>发文日期：</label>
	                <div class="data" id="dataSelect-start">
	                    <input maxlength="20" type="text"  name="pubDate" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
	                </div>  
	                 
                </li><br/>
                <li>
                	<label>主题分类：</label>
                    <input maxlength="20" type="text" name="subjectCat" class="input input-small">
                	<label>任免分类：</label>
	        		<select class="input-text" id="personCat"  name="appointCat" style="height: 28px;">
	        			<option value="交通运输部领导" >交通运输部领导</option>
	        			<option value="委系统市管干部" >委系统市管干部</option>
	        			<option value="委管干部" >委管干部</option>
	        			<option value="区县交委" >区县交委</option>
	        			<option value="其他" >其他</option>
	        		</select>
                </li>
                <br/>
                <li>
                 	<label>发布机构：</label>
                    <input maxlength="20" type="text" name="pubDapt" class="input input-small">
                    <label>文号：</label>
                    <input maxlength="20" type="text" name="textNum" class="input input-small">
	                <input type="hidden" name="deptName" value="${userDeptName}" class="input input-small">
                    <input type="hidden" name="entryUser" value="${userName}" class="input input-small">
                     
                </li>
                 
                <br/>
                <li class="clearfix">
                    <label class="l"><span style="color:red">*</span>内容：</label>
                    <div class="textEditBox">
               	    	<script type="text/plain" id="myEditor">
                          ${personContent}
                        </script>
               	    </div>
                </li>
                 <li></li>
                <br/>
                <li>
                	<label></label>
                    <a href="javascript:;" class="btn-gray" onclick="saveTextNews()"><b class="icon i-send"></b>提 交</a>
                     
                    <a href="javascript:history.go(-1);" class="btn-gray"><b class="icon i-back"></b>返 回</a>
                </li>
                <br/>
            </ul>
            </form>
        </div>
    </div>
    <!--column01 End-->
</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/ajaxfileupload.js"></script>

<script type="text/javascript">
window.APP_HOME_URL = "${pageContext.request.contextPath}";
$(function(){
	UE.getEditor('myEditor');
});

function delImg(obj){
	$(obj).remove();
}
function uploadFile(obj){
    ajaxFileUpload(obj);
}

function saveTextNews() {
 
	//校验标题
  	var newstitle =  $("input[name='personTitle']").val();
    if(newstitle =="" && newstitle.length == 0){
    	$("input[name='personTitle']").next(".error").remove();
		$("input[name='personTitle']").after("<span class='error'>标题不能为空</span>");
		return;
	} 
    
	//校验内容
	$("#personContent").val(UE.getEditor('myEditor').getContent());
	if(UE.getEditor('myEditor').getContent() == ''|| UE.getEditor('myEditor').getContent()==0){
		$(".textEditBox").next(".error").remove();
		$(".textEditBox").after("<span class='error'>内容不能为空</span>");
		return;
	}else{
		$(".textEditBox").next(".error").remove();
	}
	 
 
	var options = {
		url : 'textPerson.xhtml?action=savePerson',
		type : 'post',
		dataType : "html",
		success : function(data) {
			var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg + "是否继续增加？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "textPerson.xhtml?face=addPersonView";
				}else{
					document.location.href = "textPerson.xhtml?face=PersonlistView";
				}
			}); 
		},
		error :function(data){
			alert("内部错误，保存失败");
		}
	};
	$("#textNewsForm").ajaxSubmit(options);
}
 
 
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.all.min.js"></script>
</html>
