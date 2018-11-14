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
.docEdit label{width:100px}
</style>
<title>文字类信息</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		内容管理
        <span>-></span>
		文字类信息编辑
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>文字类信息编辑</h2></div>
        <div class="con docEdit" >
			<form id="textNewsForm" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="textContent" id="textContent" />
			<input type="hidden" name="status" id="status" />
			<input type="hidden" name="textId" value="${textData.textId}"/>
            <ul>
                <li>
	                <label><span style="color:red">*</span>标题：</label>
	                <input type="text" name="title" class="input input-large" value="${textData.title}">
                </li>
                <li>
	                <label>版面位置：</label>
	                <input type="hidden" id="position" value="${textData.position}">
	                <input type="radio" name="position" id="public2" value="2"><span>普通新闻</span>&nbsp;&nbsp;
	                <input type="radio" name="position" id="public1" value="1"><span>头版头条</span>&nbsp;&nbsp;
                    <input type="radio" name="position" id="Public0" value="0"><span>头版次条</span>
                </li>
                <li>
                    <label>作者：</label>
                    <input type="text" name="author"  value="${textData.author}" class="input input-small" readOnly="true">
                    <label><span style="color:red">*</span>投稿部门：</label>
                    <input type="text" name="entryDept" class="input input-small" value="${textData.entryDept}">
                </li>
                
                <li>
                    <label>文档来源：</label>
                    <input type="hidden" name="source" id="source" class="input input-small" value="${textData.textSource}">
                    <select class="select-small" name="textSource" id="textSource" style="width:192px">
                    	<option value="重庆交通调度中心">重庆交通调度中心</option>
                    	<option value="调度中心综合">调度中心综合</option>
                    	<option value="本站综合">本站综合</option>
                    	<option value="公路建设管理处">公路建设管理处</option>
                    	<option value="办公室">办公室</option>
                    	<option value="质监站">质监站</option>
                    	<option value="财务处">财务处</option>
                    	<option value="综合运输处">综合运输处</option>
                    	<option value="运管局">运管局</option>
                    	<option value="港航局">港航局</option>
                    	<option value="高速集团">高速集团</option>
                    	<option value="中国水运报">中国水运报</option>
                    	<option value="养护处">养护处</option>
                    	<option value="综合规划处">综合规划处</option>
                    	<option value="公路局">公路局</option>
                    	<option value="重庆市交通委员会">重庆市交通委员会</option>
                    	<option value="《重庆交通》">《重庆交通》</option>
                    </select>
                    <label>审核人：</label>
	                <select class="select-small" name="approvaler" style="width:192px">
						<option>${textData.approvaler}</option>
	                </select>
                </li>
                
               <li class="clearfix">
                    <label class="l"><span style="color:red">*</span>内容：</label>
                    <div class="textEditBox">
               	    	<script type="text/plain" id="myEditor">
                          ${textData.textContent}
                        </script>
               	    </div>
                </li>
                <li id="showPublicState">
                    <label>图片滚动展示：</label>
                    <input type="hidden" id="publicChecked" value="${textData.isPhotoShow}">
                    <input type="radio" name="isPhotoShow" id="public" value="1"><span>是</span>&nbsp;&nbsp;
                    <input type="radio" name="isPhotoShow" id="unPublic" value="0" checked="checked"><span>否</span>
                </li>
                <li id="showPhotoState">
                	<label>主题图片：</label>
                	<input type="file" name="photo" />
                	<label>图片链接：</label><a href="${StaticResourcePath}/${textData.photoUrl}" target="_blank">${textData.photoUrl}</a>
                	<input type="hidden" name="photos" value="${textData.photoUrl}"/>
                	<input type="hidden" id="photoUrl" name="photoUrl">
                </li>
                <li>
                	<label></label>
                    <a href="javascript:;" class="btn-gray" onclick="saveTextData(1)"><b class="icon i-send"></b>发 布</a>
                    <a href="javascript:;" class="btn-gray" onclick="saveTextData(0)"><b class="icon i-save"></b>存草稿</a>
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
$(function(){
	UE.getEditor('myEditor');
});

$(function(){
	//图片是否滚动显示
	var valPublic = $("#publicChecked").val();
	$("input[name='isPhotoShow']:radio").each(function(){
		if($(this).val()==valPublic){
			$(this).attr("checked","checked");
		}
	});
	//版面栏位
	var position = $("#position").val();
	$("input[name='position']:radio").each(function(){
		if($(this).val()==position){
			$(this).attr("checked","checked");
		}
	});
	//文档来源
	var source = $("#source");
	 $("#textSource option").each(function(){
	  if($(this).val()==source.val()){
	   $(this).attr("selected",true);
	  }
	 }); 

	$("input[name='title']").keyup(function(){
		var title1 = $(this).val();
		if(title1 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>标题不能为空</span>");
		}
	});
	
	$("select[name='entryDept']").click(function(){
		var title3 = $(this).val();
		if(title3 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>投稿部门不能为空</span>");
		}
	});
  	$("#textContent").val(UE.getEditor('myEditor').getContent());
	$("#myEditor").keyup(function(){
		var title5 = $("#textContent").val();
		if(title5 != "" ){
			$(this).next(".error").remove();
		}
	});   alert('445544');
	
});    
function saveTextData(flag) {
  	var title =  $("input[name='title']").val();
	var entryDept = $("select[name='entryDept']").val(); 
	var photo = $("input[name='photo']").val();
	
	$("#photoUrl").val(photo);
	
	//校验输入框
    if(title =="" && title.length == 0){
    	$("input[name='title']").next(".error").remove();
		$("input[name='title']").after("<span class='error'>标题不能为空</span>");
		return;
	}     
 	
	if(entryDept ==""){
		$("select[name='entryDept']").next(".error").remove();
		$("select[name='entryDept']").after("<span class='error'>投稿部门不能为空</span>");
		return;
	}
 
	
	$("#status").val(flag);
	
	$("#textContent").val(UE.getEditor('myEditor').getContent());
	//校验内容
	if($("#textContent").val() == ''||$("#textContent").val().length==0){
		$(".textEditBox").next(".error").remove();
		$(".textEditBox").after("<span class='error'>内容不能为空</span>");
		return;
	}else{
		$(".textEditBox").next(".error").remove();
	}
	
	var options = {
		url : 'textData.xhtml?action=save',
		type : 'post',
		dataType : "html",
		success : function(data) {			
			var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg + "是否清空表单？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "textData.xhtml?face=getTextDataList";
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
