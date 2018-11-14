<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>考核历史信息</title>
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#search {float: left;width:1000px;}
#Permission {float: left; margin-left:10px;}
a {color:blue;cursor: pointer;}
.error{color:red;padding-left: 10px;}
</style>
</head>
<body>
<div>
<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
         <a href="checkHistory.xhtml?face=getCheckHistoryList">历史考核记录</a>
        <span>-></span>
      	  编辑考核历史信息
    </div>
    <!--path End-->
     <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>编辑考核历史信息</h2></div>
         <div class="con">
		<form id="uploadFile" enctype="multipart/form-data" method="POST"
		name="uploadFile">
		<div class="docEdit" >
			<input type="hidden" name="checkId" value="${file.checkId}"/>
			<input type="hidden" name="attachment" value="${file.attachment}"/>
			<input type="hidden" name="path" value="${file.filePath}"/>
			<ul>
			 <li>
	                <label><span style="color:red"></span>上传时间：</label>
                    <input type="text" name="createDate" class="input input-small" value="${file.createDate}" disabled="true" >
                	<label><span style="color:red"></span>上传人：</label>
                    <input type="text" name="createBy" class="input input-small" value="${file.createBy}" disabled="true" >
                </li>
    			<li>
                    <label><span style="color:red">*</span>文件名称：</label>
                    <input type="text" name="checkName" value="${file.checkName}" class="input input-small" style="margin: 3px;width:600px;">
	    
                </li>
                <li>
                      <label><span style="color:red">*</span>上传附件：</label>
                      <input type="file" name="filePath">
                      <label id="tu">附件链接：</label><a href="${StaticResourcePath}/${file.filePath}" target="_blank">${file.filePath}</a>
                	  <input type="hidden" name="imageUrl" value="${file.filePath}"/>
                	  <input type="hidden" id="fileUrl" name="fileUrl" />
                </li>
				<li>
					<label style="vertical-align: top;" >备注：</label> 
					<textarea  name="checkDesc"  class="input input-small" style="margin: 3px; padding: 3px;width:600px; height:100px">${file.checkDesc}</textarea>
				</li>
                 <li style="margin: 3px;">
                	<label>类别：</label>
                	<c:if test="${file.checkType==1}">
                       <input type="radio" name="checkType" value="1" checked="true"><span>网站信息</span>
                       <input type="radio" name="checkType" value="0"><span>政务信息</span>
                    </c:if>
                    <c:if test="${file.checkType==0}">
                       <input type="radio" name="checkType" value="1" ><span>网站信息</span>
                       <input type="radio" name="checkType" value="0" checked="true"><span>政务信息</span>
                    </c:if>
                </li>
                <li style="margin: 3px;">
                	<label>考核分组：</label>
                    <input type="radio" name="checkGroup" value="1"><span>市交委机关</span>
                    <input type="radio" name="checkGroup" value="2"><span>委属单位</span>
                    <input type="radio" name="checkGroup" value="3"><span>区县交通局</span>
                    <input type="radio" name="checkGroup" value="4"><span>市属相关交通企业</span>
                    <input type="hidden" id="checkGroup" value="${file.checkGroup}">
                </li>
				<li class="infoCon clearfix">
					<label></label>
					<div class="btn">
						<a  href="javascript:;"   onclick="saveFileInfo();return false;" class="btn-gray"><b class="icon i-save"></b>保存</a>
					    <a href="javascript:history.go(-1);" class="btn-gray"><b class="icon i-back"></b>返 回</a>
					</div>
				</li>
			</ul>
		</div>
	</form>
	 	</div>
</div>
    <!--column01 End-->
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function(){
	$("input[name='checkName']").keyup(function(){
		var checkName = $(this).val();
		if(checkName != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>文件名称不能为空</span>");
		}
	});
	
	var checkGroup = $("#checkGroup").val();
	$("input[name='checkGroup']:radio").each(function(){
		if($(this).val()==checkGroup){
			$(this).attr("checked","checked");
		}
	});
})
function saveFileInfo() {
	var checkName =  $("input[name='checkName']").val();
	var filePath =  $("input[name='filePath']").val();
	if(checkName =="" && checkName.length == 0){
    	$("input[name='checkName']").next(".error").remove();
		$("input[name='checkName']").after("<span class='error'>文件名称不能为空</span>");
		return;
	}   
	$("#fileUrl").val(filePath);
	var options = {
		url : 'checkHistory.xhtml?action=saveFileInfo',
		type : 'post',
		dataType : "html",
		beforeSubmit : function(){
			
		},
		success : function(data) {			
			var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg + "是否清空表单？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "checkHistory.xhtml?face=getCheckHistoryList";
				}
			}); 
		},
		error :function(data){
			alert("内部错误，保存失败");
		}
	};
	$("#uploadFile").ajaxSubmit(options);
	
}
		
</script>
</html>
