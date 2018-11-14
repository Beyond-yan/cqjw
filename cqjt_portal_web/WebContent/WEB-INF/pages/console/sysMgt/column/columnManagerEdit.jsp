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
<title>栏目管理</title>
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#search {float: left;width:1000px;}
#Permission {float: left; margin-left:10px;}
a {color:blue;cursor: pointer;}
.error{color:red;padding-left: 10px;}
.docEdit lable{width:100px}
</style>
</head>
<body>
<div>
<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		<a href="column.xhtml?face=getColumnList">栏目管理</a>
        <span>-></span>
      	  栏目编辑
    </div>
    <!--path End-->
     <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>栏目编辑</h2></div>
         <div class="con">
		<form id="uploadFile" enctype="multipart/form-data" method="POST"
		name="uploadFile">
		<div class="docEdit" >
			<input type="hidden" name="categoryId" value="${column.categoryId}"/>
			<ul>
			 <li>
	                <label><span style="color:red"></span>创建时间：</label>
                    <input type="text" name="createDate" class="input input-small" value="${column.createDate}" readonly="true" >
                	<label><span style="color:red"></span>创建人：</label>
                    <input type="text" name="createBy" class="input input-small" value="${column.createBy}" readonly="true" >
                </li>
    			<li>
                    <label><span style="color:red">*</span>栏目名称：</label>
                    <input type="text" name="categoryName" value="${column.categoryName}" class="input input-small" style="margin: 3px;width:481px;">
	    
                </li>
               <li >
                	<label><span style="color:red">*</span>是否发布外网：</label>
                	<c:if test="${column.isOutsite==1}">
                       <input type="radio" name="isOutsite" value="1" checked="true"><span>是</span>
                       <input type="radio" name="isOutsite" value="0"><span>否</span>
                    </c:if>
                    <c:if test="${column.isOutsite==0}">
                       <input type="radio" name="isOutsite" value="1" ><span>是</span>
                       <input type="radio" name="isOutsite" value="0" checked="true"><span>否</span>
                    </c:if>  
                </li>
				<li class="infoCon clearfix">
					<label></label>
					<div class="btn">
						<a  href="javascript:;"   onclick="save();return false;" class="btn-gray"><b class="icon i-save"></b>保存</a>
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
	$("input[name='categoryName']").keyup(function(){
		var categoryName = $(this).val();
		if(categoryName != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>栏目名称不能为空</span>");
		}
	});
})
function save() {
	var categoryName =  $("input[name='categoryName']").val();
	if(categoryName =="" && categoryName.length == 0){
    	$("input[name='categoryName']").next(".error").remove();
		$("input[name='categoryName']").after("<span class='error'>栏目名称不能为空</span>");
		return;
	}   
	
	var options = {
		url : 'column.xhtml?action=save',
		type : 'post',
		dataType : "html",
		beforeSubmit : function(){
			
		},
		success : function(data) {			
			var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg + "是否返回栏目列表？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "column.xhtml?face=getColumnList";
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
