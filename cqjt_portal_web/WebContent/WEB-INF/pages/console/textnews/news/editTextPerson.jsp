<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html 
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
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
.column {min-width:940px;}
.error{color:red;padding-left: 10px;}
</style>
<title>编辑法律法规</title>
</head>
<body onload="init()" >
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		内容管理
        <span>-></span>
		编辑法律法规
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>投稿信息编辑</h2></div>
        <div class="con docEdit" >
			<form id="textpersonForm" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="personContent" id="personContent" />
			<input type="hidden" name="flag" id="personFlag"  value="${textNewsEditInfo.flag}"/>
			<input type="hidden" name="fg"  value="${textNewsEditInfo.flag}"/>
			<input type="hidden" name="entryDept" value="${userDeptID}"/>
			<input type="hidden" name="personId" value="${textNewsEditInfo.personId}"/>
 		 
 
 
			<input type="hidden" name="createDate" value="${textNewsEditInfo.createDate}">
            <ul>
            	<br/>
                <li>
	                <label><span style="color:red">*</span>信息标题：</label>
	                <input maxlength="50" type="text" name="personTitle" value="${textNewsEditInfo.personTitle}" class="input input-large">
                </li>
                 <br/>
                <li>
                	<label><span style="color:red">*</span>上传人：</label>
                    <input maxlength="20" type="text" name="pubUser"  value="${textNewsEditInfo.pubUser}"class="input input-small">
               		<label>发文日期：</label>
	                <div class="data" id="dataSelect-start">
	                    <input maxlength="20" type="text"  name="pubDate"  value="${textNewsEditInfo.pubDate}" class="input-text tcal tcalInput">
	                </div>  
	                 
                </li>
                 <br/>
                <li>
                	<label>主题分类：</label>
                    <input maxlength="20" type="text" value="${textNewsEditInfo.subjectCat}" name="subjectCat" class="input input-small">
                	<label>任免分类：</label>
	        		<select class="input-text" id="personCat"  name="appointCat" style="height: 28px;">
	        			<option value="交通运输部领导" <c:if test="${textNewsEditInfo.appointCat=='交通运输部领导'}">selected</c:if>>交通运输部领导</option>
	        			<option value="委系统市管干部" <c:if test="${textNewsEditInfo.appointCat=='委系统市管干部'}">selected</c:if>>委系统市管干部</option>
	        			<option value="委管干部" <c:if test="${textNewsEditInfo.appointCat=='委管干部'}">selected</c:if>>委管干部</option>
	        			<option value="区县交委" <c:if test="${textNewsEditInfo.appointCat=='区县交委'}">selected</c:if>>区县交委</option>
	        			<option value="其他" <c:if test="${textNewsEditInfo.appointCat=='其他'}">selected</c:if>>其他</option>
	        		</select>
                </li>
                <br/>
                <li>
                 	<label>发布机构：</label>
                    <input maxlength="20" type="text" name="pubDapt" value="${textNewsEditInfo.pubDapt}" class="input input-small">
                    <label>文号：</label>
                    <input maxlength="20" type="text" name="textNum" value="${textNewsEditInfo.textNum}" class="input input-small">
	                <input type="hidden" name="deptName" value="${userDeptName}" class="input input-small">
                    <input type="hidden" name="entryUser" value="${userName}" class="input input-small">
                     
                </li>
                 
                <br/>
                <li class="clearfix">
                    <label class="l"><span style="color:red">*</span>内容：</label>
                    <div class="textEditBox">
               	    	<script type="text/plain" id="myEditor">
                          ${textNewsEditInfo.personContent}
                        </script>
               	    </div>
                </li>
                 <li></li>
                <br/>
                <li>
                	<label></label>
                    <input id="btn1"  type="button" class="btn-gray" onclick="saveSubmit()"  value="保存修改" />
                    <a href="javascript:history.go(-1);" class="btn-gray">返 回</a>
                </li>
                <br/>
            </ul>
            </form>
        </div>
    </div>
    <!--column01 End-->
</div>
</body>

<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>

<script type="text/javascript">
window.APP_HOME_URL = "${pageContext.request.contextPath}";
function show1(){
	 window.open ('${StaticResourcePath}/${textpersonEditInfo.photoUrl}','add', 
	 'height=600, width=800, top=100, left=200, toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no, status=no') ;
	 } 
$(function(){
	UE.getEditor('myEditor');
});

 
 
$(function(){
	$("input[name='personTitle']").keyup(function(){
		var title1 = $(this).val();
		if(title1 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>标题不能为空</span>");
		}
	});
	$("input[name='pubUser']").keyup(function(){
		var title2 = $(this).val();
		if(title2 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>上传人不能为空</span>");
		}
	});
	 
  	$("#personContent").val(UE.getEditor('myEditor').getContent());
	$("#myEditor").keyup(function(){
		var title5 = $("#personContent").val();
		if(title5 != "" ){
			$(this).next(".error").remove();
		}
	});   
});    

function saveSubmit(){
	asyncbox.confirm("涉密信息禁止上网!", "信息窗口", function(action) { 
		if (action == "ok") {
			saveTextperson();
		}
	});
}

function saveTextperson() {
 
  	var persontitle =  $("input[name='personTitle']").val();
 	var pubUser = $("input[name='pubUser']").val();
	//校验输入框
    if(persontitle =="" && persontitle.length == 0){
    	$("input[name='personTitle']").next(".error").remove();
		$("input[name='personTitle']").after("<span class='error'>标题不能为空</span>");
		return;
	}     
 
 	if(pubUser == "" && pubUser.length == 0){
 		$("input[name='pubUser']").next(".error").remove();
		$("input[name='pubUser']").after("<span class='error'>上传人不能为空</span>");
		return;
	}
 
 
	$("#personContent").val(UE.getEditor('myEditor').getContent());
	//校验内容
	if($("#personContent").val() == ''||$("#personContent").val().length==0){
		$(".textEditBox").next(".error").remove();
		$(".textEditBox").after("<span class='error'>内容不能为空</span>");
		return;
	}else{
		$(".textEditBox").next(".error").remove();
	}
 
 
	var options = {
		url : 'textPerson.xhtml?action=updateTextPerson',
		type : 'post',
		dataType : "html",
		success : function(data) {			
		    var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg + "是否返回人事变动页面？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "textPerson.xhtml?face=PersonlistView";
				}
			});  
		},
		error :function(data){
			asyncbox.confirm("内部错误，保存失败", "信息窗口");
		}
	};
	$("#textpersonForm").ajaxSubmit(options);
}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.all.min.js"></script>
</html>
