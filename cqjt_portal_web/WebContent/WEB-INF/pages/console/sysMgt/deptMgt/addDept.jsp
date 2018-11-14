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
/*a {color:blue;cursor: pointer;}*/
.column {min-width:940px;}
.error{color:red;padding-left: 10px;}
</style>
<title>部门管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		部门管理
		<span>-></span>
		新增部门
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>部门新增</h2></div>
        <div class="con docEdit" >
			<form id="deptInfoForm" enctype="multipart/form-data" method="POST">
            <ul>
             	<li>
	                <label><span style="color:red">*</span>排序号：</label>
	                <input type="text" name="sequenceNum" class="input input-small" >
                </li>
                <li>
	                <label><span style="color:red">*</span>部门名称：</label>
	                <input type="text" name="deptName" class="input input-small" >
                </li>
                <li>
	                <label><span style="color:red">*</span>部门分类：</label>
	                <select id="deptCategory" name="deptCategory" class="select-small"  style="margin-right: 30px; margin: 4px;" >
						<option value="">--请选择--</option>
						<option value="交委机关">市交委机关</option>
						<option value="委属单位">委属单位</option>
						<option value="区县单位">区县交通局</option>
						<option value="市属相关交通企业">市属相关交通企业</option>
					</select>
                </li>
                <li>
                	<label>状态：</label>
                    <input type="radio" id="onUsed" name="flag" value="0" checked="true"><span>启用</span>
                    <input type="radio" id="offUserd" name="flag" value="1"><span>停用</span>
                </li>
                <li>
                	<label></label>
                    <a href="javascript:;" class="btn-gray" onclick="saveDeptInfo()"><b class="icon i-send"></b>保存</a>
                    <a href="sysMgt.xhtml?face=listDeptMgtView" class="btn-gray"><b class="icon i-back"></b>返 回</a>
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
	$("input[name='deptName']").keyup(function(){
		var deptName = $(this).val();
		if(deptName != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>部门名称不能为空</span>");
		}
	});
	$("select[name='deptCategory']").click(function(){
		var deptCategory = $(this).val();
		if(deptCategory != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>部门分类不能为空</span>");
		}
	});
});    
function saveDeptInfo() {
  	var deptName =  $("input[name='deptName']").val();
 	var deptCategory = $("select[name='deptCategory']").val();
	//校验输入框
    if(deptName =="" && deptName.length == 0){
    	$("input[name='deptName']").next(".error").remove();
		$("input[name='deptName']").after("<span class='error'>部门名称不能为空</span>");
		return;
	}     
 	if(deptCategory == "" && deptCategory.length == 0){
 		$("select[name='deptCategory']").next(".error").remove();
		$("select[name='deptCategory']").after("<span class='error'>部门分类不能为空</span>");
		return;
	}
 	
	var options = {
		url : 'sysMgt.xhtml?action=save',
		type : 'post',
		dataType : "html",
		success : function(data) {			
			 var data = eval('(' + data + ')');
			asyncbox.confirm(data.msg + "是否清空表单？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "sysMgt.xhtml?face=listDeptMgtView";
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
	$("#deptInfoForm").ajaxSubmit(options);
}
</script>
</html>