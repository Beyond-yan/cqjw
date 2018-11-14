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
<title>新增信息</title>
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/plugin/ueditor/themes/default/css/ueditor.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#search {float: left;width:1000px;}
#Permission {float: left; margin-left:10px;}
a {color:blue;cursor: pointer;}
.error{color:red;padding-left: 10px;}

 .mask {       
		position: absolute; top: 0px; filter: alpha(opacity=60); background-color: #fff;     
		z-index: 1002; left: 0px;     
		opacity:0.5; -moz-opacity:0.5;
		display:none;text-align:center;line-height:550px;
    } 
</style>
</head>
<body>
<div>
<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		专报信息
        <span>-></span>
        专报信息新增
    </div>
    <!--path End-->
     <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>专报信息新增</h2></div>
         <div class="con">
		<form id="uploadFile" enctype="multipart/form-data" method="POST" name="uploadFile">
		<div class="docEdit" >
			<ul>
    			<li>
                    <label><span style="color:red">*</span>名称：</label>
                    <input type="text" name="specialTitle" value="" class="input input-small" style="margin: 3px;width:600px;">
                	 <input type="hidden" id="specialContent" name="specialContent" value="不需要内容"/>
                </li>
                <!-- 
				<li>
					<label style="vertical-align: top;" ><span style="color:red">*</span>内容：</label> 
					<div class="textEditBox">
               	    	<script type="text/plain" id="myEditor">
                          
                        </script>
               	    </div>
				</li>
				 -->
                <li style="margin: 3px;">
                	<label>是否置顶：</label>
                    <input type="radio" name="isTop" value="0" checked="true"><span>否</span>
                    <input type="radio" name="isTop" value="1" ><span>是</span>
                   
                    <label>是否发布：</label>
                    <input type="radio" name="isPublish" value="1" ><span>否</span>
                    <input type="radio" name="isPublish" value="0" checked="true"><span>是</span>
                </li>
                <li>
                    <label>附件：</label>
                    <input type="file" name="accessoryUrl" />
                </li>
                <li>
                    <label></label>
                    
                </li>
				<li class="infoCon clearfix">
					<label></label>
					<div class="btn">
						<a  href="javascript:;"   onclick="insertSpecialVo();return false;" class="btn-gray"><b class="icon i-save"></b>保存</a>
					    <a href="javascript:history.go(-1);" class="btn-gray"><b class="icon i-back"></b>返 回</a>
					</div>
				</li>
			</ul>
		</div>
	</form>
	 	</div>
</div>
    <!--column01 End-->
    
    <div id="mask" class="mask">正在加载中，请稍等...</div> 
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>

<script type="text/javascript">
window.APP_HOME_URL = "${pageContext.request.contextPath}";
$(function(){
	UE.getEditor('myEditor');
});
$(function(){
	$("input[name='specialTitle']").keyup(function(){
		var fileName = $(this).val();
		if(fileName != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>文件名称不能为空</span>");
		}
	});

	/**
  	$("#specialContent").val(UE.getEditor('myEditor').getContent());
	$("#myEditor").keyup(function(){
		var title5 = $("#specialContent").val();
		if(title5 != "" ){
			$(this).next(".error").remove();
		}
	});   
	*/
})
function insertSpecialVo() {
	showMask();
	var fileName =  $("input[name='specialTitle']").val();
	if(fileName =="" && fileName.length == 0){
    	$("input[name='specialTitle']").next(".error").remove();
		$("input[name='specialTitle']").after("<span class='error'>文件名称不能为空</span>");
		return;
	}   
	
	/**
	$("#specialContent").val(UE.getEditor('myEditor').getContent());
	//校验内容
	if($("#specialContent").val() == ''||$("#specialContent").val().length==0){
		$(".textEditBox").next(".error").remove();
		$(".textEditBox").after("<span class='error'>内容不能为空</span>");
		return;
	}else{
		$(".textEditBox").next(".error").remove();
	}
	*/
	var options = {
		url : 'specialInformation.xhtml?action=insert',
		type : 'post',
		dataType : "html",
		beforeSubmit : function(){
			
		},
		success : function(data) {		
			hideMask();
			var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg + "是否继续增加资料？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "specialInformation.xhtml?face=addPage";
				}else{
					document.location.href = "specialInformation.xhtml?face=queryPageList";
				}
			}); 			
		},
		error :function(data){
			hideMask();
			alert("内部错误，保存失败");
		}
	};
	$("#uploadFile").ajaxSubmit(options);
	
}
		
		
//显示遮罩层    
function showMask(){     
    $("#mask").css("height",$(document).height());    
    $("#mask").css("width",$(document).width());   
    $("#mask").show();
}  
//隐藏遮罩层  
function hideMask(){     
    $("#mask").hide();     
}  
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.all.min.js"></script>

</html>
