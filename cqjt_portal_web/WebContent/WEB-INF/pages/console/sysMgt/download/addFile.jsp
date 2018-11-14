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
<title>上传资料</title>
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
		资料管理
        <span>-></span>
                    资料新增
    </div>
    <!--path End-->
     <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>资料上传</h2></div>
         <div class="con">
		<form id="uploadFile" enctype="multipart/form-data" method="POST"
		name="uploadFile">
		<div class="docEdit" >
			<input type="hidden" name="fileId" value="${file.fileId}"/>
			<input type="hidden" name="attachment" value="${file.attachment}"/>
			<input type="hidden" name="fileSize" value="${file.fileSize}"/>
			<input type="hidden" name="path" value="${file.filePath}"/>
			<ul>
			 <li>
	                <label><span style="color:red"></span>上传时间：</label>
                    <input type="text" name="createDate" class="input input-small" value="${createDate}" disabled="true" >
                    <label><span style="color:red"></span>上传人：</label>
                    <input type="text" name="createBy" class="input input-small" value="${createBy}" disabled="true" >
                </li>
    			<li>
                    <label><span style="color:red">*</span>文件名称：</label>
                    <input type="text" name="fileName" value="${file.fileName}" class="input input-small" style="margin: 3px;width:600px;">
	    
                </li>
                <li>
                      <label><span style="color:red">*</span>上传附件：</label>
                      <input type="file"  value="${file.filePath}" name="filePath">
                      <input type="hidden" name="imageUrl" />
                	  <input type="hidden" id="fileUrl" name="fileUrl" />
                </li>
				<li>
					<label style="vertical-align: top;" >备注：</label> 
					<textarea  name="fileDesc" value="${file.fileDesc}" class="input input-small" style="margin: 3px; padding: 3px;width:600px; height:100px"></textarea>
				</li>
                 <li style="margin: 3px;">
                	<label>是否置顶：</label>
                    <input type="radio" name="isTop" value="1" checked="true"><span>是</span>
                    <input type="radio" name="isTop" value="0"><span>否</span>
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
<script language=JavaScript> window.opener.location.reload(); </script>
<script type="text/javascript">

$(function(){
	$("input[name='fileName']").keyup(function(){
		var fileName = $(this).val();
		if(fileName != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>文件名称不能为空</span>");
		}
	});
	$("input[name='filePath']").click(function(){
		$(this).next(".error").remove();
	});
})
function saveFileInfo() {
	var fileName =  $("input[name='fileName']").val();
	var filePath =  $("input[name='filePath']").val();
	if(fileName =="" && fileName.length == 0){
    	$("input[name='fileName']").next(".error").remove();
		$("input[name='fileName']").after("<span class='error'>文件名称不能为空</span>");
		return;
	}   
	var filePath =  $("input[name='filePath']").val();
	if(filePath =="" && filePath.length == 0){
    	$("input[name='filePath']").next(".error").remove();
		$("input[name='filePath']").after("<span class='error'>附件不能为空</span>");
		return;
	}else{
		$("input[name='filePath']").next(".error").remove();
	}    
	$("#fileUrl").val(filePath);
	var options = {
		url : 'download.xhtml?action=saveFileInfo',
		type : 'post',
		dataType : "html",
		beforeSubmit : function(){
			
		},
		success : function(data) {			
			var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg + "是否继续增加资料？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "download.xhtml?face=addFile";
				}else{
					document.location.href = "download.xhtml?face=getFileList";
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
