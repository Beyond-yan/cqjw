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
<title>新增法律法规</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		内容管理
        <span>-></span>
		新增法律法规
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>信息录入</h2></div>
        <div class="con docEdit" >
			<form id="textNewsForm" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="statuteContent" id="newsContent" />
			<input type="hidden" name="statuteTagsStr" id="statuteTags" />
			<input type="hidden" name="entryDept" value="${userDeptID}"/>
			<input type="hidden" name="deptCategory" value="${deptCategory}"/>
            <input type="hidden" name="outerCategory" class="input input-small"/>
            <ul><br/>
                <li>
	                <label><span style="color:red">*</span>法规标题：</label>
	                <input type="text" name="statuteTitle" class="input input-large">
                </li>
                 <br/>
                                <li>
               		<label>发布日期：</label>
	                <div class="data" id="dataSelect-start">
	                    <input type="text"  name="pubDate" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
	                </div> 
	                <label>实施日期：</label>
	                <div class="data" id="dataSelect-start">
	                    <input type="text"  name="inplementDate" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
	                </div> 
                </li>
                 <br/>
                <li>
                    <label><span style="color:red">*</span>上传人：</label>
                    <input type="text" name="statuteAuthor"  value="${userName}"class="input input-small">
                </li>
                 <br/>
                <li>
                 	<label>发布机关：</label>
                    <input type="text" name="pubDapt" class="input input-small">
                    <label>发布文号：</label>
                    <input type="text" name="pubTextNum" class="input input-small">
	                <input type="hidden" name="deptName" value="${userDeptName}" class="input input-small">
                    <input type="hidden" name="entryUser" value="${userName}" class="input input-small">
                    <label>时效性：</label>
                    <select name="timeliness">
	        			<option value="有效">有效</option>
	        			<option value="失效">失效</option>
	        		</select>
                </li>

                <br/>
                <li>
                <label style="margin-top:0px;"><span style="color:red">*</span>题注：</label>
                <textarea style="border-radius: 3px;width:800px; border-color:#ddd;margin-left:2px;" rows="" cols=""  name="caption"  class="input-text"></textarea>
                </li>
                <br/>
                <li class="clearfix">
                    <label class="l"><span style="color:red">*</span>内容：</label>
                    <div class="textEditBox">
               	    	<script type="text/plain" id="myEditor">
                          ${newsContent}
                        </script>
               	    </div>
                </li>
                 <li>
	                <label>法规分类：</label>
	        		<select class="input-text" id="StatuteCat"  name="SUB_CATEGORY" style="height: 28px;">
	        			<option value="交通地方性法规库" >交通地方性法规库</option>
	        			<option value="交通地方政府规章库" >交通地方政府规章库</option>
	        			<option value="交通行政规范性文件库" >交通行政规范性文件库</option>
	        			<option value="党内法规库" >党内法规库</option>
	        		</select>
	        		<label>子分类：</label>
	        		<select class="input-text" id="Statute" name="SUB_CATEGORY_INFO" style="height: 28px;">
	        			<option value="">-= 请选择  =-</option>
	        		</select>     
                </li>
                
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
	
	$("#StatuteCat").change(function(){
		
		switch ($(this).val()) {
			case '交通地方性法规库':
				$("#Statute").html("<option value=''>-= 请选择  =-</option>");
				break;
			case '交通地方政府规章库':
				$("#Statute").html("<option value=''>-= 请选择  =-</option>");
				break;
			case '交通行政规范性文件库':
				$("#Statute").html("<option value='市政府制发文件'>市政府制发文件</option><option value='市交委制发文件'>市交委制发文件</option><option value='委属单位制发文件'>委属单位制发文件</option>");
				break;
			case '党内法规库':
				$("#Statute").html("<option value='其他相关文件'>其他相关文件</option><option value='市交通党委规范性文件'>市交通党委规范性文件</option><option value='市委党内法规'>市委党内法规</option><option value='中央党内法规'>中央党内法规</option>");
				break;
			default:
				$("#Statute").html("<option value=''>-= 请选择  =-</option>");
				break;
			}
			
	})
	
});

function delImg(obj){
	$(obj).remove();
}
function uploadFile(obj){
    ajaxFileUpload(obj);
}


function chooseSiteNews(obj) {
	if (obj.checked) {
		$("#newsCategory").attr("disabled", false);
		$("#showPhotoState").show();//新增图片点击显示 wl
		$("#showPublicState").show();//新增是否上外网显示 wl
	} else {
		$("#newsCategory")[0].selectedIndex = 0;
		$("#newsCategory").attr("disabled", true);
		$("#showPhotoState").hide(); //新增图片点击隐藏 wl
		$("#showPublicState").hide();//新增是否上外网显示wl
	} 
}

$(function(){
	//是否滚动显示权限设置
	var deptCategory=	$("input[name='deptCategory']").val()
	if(deptCategory=="true"){
		$("#IsRollShow").show();
	}else{
		$("#IsRollShow").hide();
	}
	
	$("input[name='newsTitle']").keyup(function(){
		var title1 = $(this).val();
		if(title1 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>标题不能为空</span>");
		}
	});
	$("input[name='newsAuthor']").keyup(function(){
		var title2 = $(this).val();
		if(title2 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>上传人不能为空</span>");
		}
	});
	/*
	$("select[name='approvaler']").click(function(){
		var title3 = $(this).val();
		if(title3 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>审核人不能为空</span>");
		}
	});*/
	
	$("input[name='newsImage']").click(function(){
		   $(this).next(".error").remove();
	});
  	$("#newsContent").val(UE.getEditor('myEditor').getContent());
	$("#myEditor").keyup(function(){
		var title5 = $("#newsContent").val();
		if(title5 != "" ){
			$(this).next(".error").remove();
		}
	});   

	
});    

 

function saveTextNews() {
 
 
  	var newstitle =  $("input[name='statuteTitle']").val();
 	var newsauthor = $("input[name='statuteAuthor']").val();
 	
    if(newstitle =="" && newstitle.length == 0){
    	$("input[name='statuteTitle']").next(".error").remove();
		$("input[name='statuteTitle']").after("<span class='error'>标题不能为空</span>");
		return;
	} 
    
 	
	$("#statuteContent").val(UE.getEditor('myEditor').getContent());
	//校验内容
	if(UE.getEditor('myEditor').getContent() == ''|| UE.getEditor('myEditor').getContent()==0){
		$(".textEditBox").next(".error").remove();
		$(".textEditBox").after("<span class='error'>内容不能为空</span>");
		return;
	}else{
		$(".textEditBox").next(".error").remove();
	}
	 
 
	var options = {
		url : 'textStatute.xhtml?action=saveStatute',
		type : 'post',
		dataType : "html",
		success : function(data) {
			var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg + "是否继续增加？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "textStatute.xhtml?face=addStatuteView";
				}else{
					document.location.href = "textStatute.xhtml?face=StatutelistView";
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
