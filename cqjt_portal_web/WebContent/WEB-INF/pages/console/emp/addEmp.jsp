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
.inputs{width:150px !important;}
</style>
<title>内容管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>内容管理：</span>
		员工展示
        <span>-></span>
		新增
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>员工信息录入</h2></div>
        <div class="con docEdit" >
			<form id="picNewsForm" enctype="multipart/form-data" method="POST">
			<!-- <input type="hidden" name="picId" value="${picId}"> -->
			<input type="hidden" name="flag" id="newsFlag" />
			<input type="hidden" name="deptName" id="deptName" />
			<input type="hidden" name="categoryName" id="categoryName" />
            <ul>
                <li>
	                <label><span style="color:red">*</span>员工名称：</label>
	                <input type="text" name="empName" class="input input-medium" value="${empName}">
                </li>
                <li>
	                <label><span style="color:red">*</span>员工职位：</label>
	                <input type="text" name="empPost" class="input input-medium" value="${empPost}">
                </li>
                <li>
                    <label><span style="color:red">*</span>录入人：</label>
                    <input type="text" name="entryUser" class="input input-small inputs" value="${entryUser}" readOnly="true">                   
	                <label><span style="color:red"></span>录入时间：</label>
                    <input type="text" name="entryDate" class="input input-small inputs" value="${entryDate}" readOnly="true" >
                </li>  
                <li>
	                <label>部门名称：</label> <select id="selectDeptName" name="deptID" class="input input-small inputs">
					</select>
					<label><span style="color:red">*</span>员工排序：</label>
	                <input type="text" style="width:50px;" maxlength="3" id="empSort" name="empSort" class="input input-medium" value="500">
                </li>
                <li>
                	<label><span style="color:red">*</span>员工照片：</label>
                	<input type="file" name="picFile" onchange="Javascript:validate_img(this);" />
                	<input type="hidden" name="photo" value="${empUrl}"/>
                	<input type="hidden" id="picUrl" name="empUrl" class="input input-small">
                	<div style="color:red;position: relative;left:60px;">*请上传100*140像素的照片</div>
                </li>
                <li>
                <br/>
               		<label></label>
                    <a href="javascript:;" class="btn-gray" onclick="savePicNews(1)"><b class="icon i-send"></b>发布</a>
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
	$("input[name='empName']").keyup(function(){
		var title1 = $(this).val();
		if(title1 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>员工名称不能为空</span>");
		}
	});
	$("input[name='picFile']").click(function(){
		$(this).next(".error").remove();
	}); 	
});    
function savePicNews(flag) {
  	var empName =  $("input[name='empName']").val();
 	var entryUser = $("input[name='entryUser']").val();
 	var empPost = $("input[name='empPost']").val();
	var source = $("input[name='source']").val();
	var picFile = $("input[name='picFile']").val();
	var empSort = $("#empSort").val();
	var ex = /^\d+$/;
	if(empSort=="" && empSort.length == 0){
    	 alert("员工排序不能为空");
		return;
	} 
	if(isNaN(empSort) || !ex.test( empSort ) || !empSort>0){
		alert("员工排序必须为数字与正整数");
		return;
	} 
    if(empName=="" && empName.length == 0){
    	$("input[name='picTitle']").next(".error").remove();
		$("input[name='picTitle']").after("<span class='error'>员工名称不能为空</span>");
		return;
	}     
 	if(entryUser == "" && entryUser.length == 0){
 		$("input[name='entryUser']").next(".error").remove();
		$("input[name='entryUser']").after("<span class='error'>录入人不能为空</span>");
		return;
	}
 	if(empPost == "" && empPost.length == 0){
 		$("input[name='empPost']").next(".error").remove();
		$("input[name='empPost']").after("<span class='error'>员工职位不能为空</span>");
		return;
	}
 	 
 	 
 	if(picFile =="" && picFile.length == 0){
    	$("input[name='picFile']").next(".error").remove();
		$("input[name='picFile']").after("<span class='error'>员工照片不能为空</span>");
		return;
	}else{
		$("input[name='picFile']").next(".error").remove();
	}
	$("#newsFlag").val(flag);
	$("#picUrl").val(picFile);

	$("#categoryName").val($('#selectDeptName option:selected').text());
	var options = {
		url : 'emp.xhtml?action=saveEmpInfo',
		type : 'post',
		dataType : "html",
		beforeSubmit : checkFormData,
		success : function(data) {	
			var content = eval('(' + data + ')');
			if(content.msg == '图片宽度不符合,操作失败！'||content.msg == '图片高度不符合,操作失败！'){
				alert(content.msg)
			}else{
				asyncbox.confirm(content.msg + "点击“确定”,继续录入信息,点击“取消”,将返回到查询页面！", "确认窗口", function(action) {
					if (action == "ok") {
						document.location.href = "emp.xhtml?face=addEmp";
					}
					else {
						document.location.href = "emp.xhtml?face=getEmplistView";
					}
				}); 
			}
		},
		error :function(data){
			alert("内部错误，保存失败");
		}
	};
		$("#picNewsForm").ajaxSubmit(options);

}

//对部门下拉列表连动
function searchList() {
	var deptCategory = "市交委机关";
	$.ajax({
			url : "../guest/SearchContent.xhtml?action=queryDeptInfoView",
			cache : false,
			type : "post",
			dataType : "text",
			data : {
				"deptCategory" : deptCategory
			},
			success : function(data) {
				var json = eval(data);

				var departmentInfoList = "";
				$.each(
								json,
								function(i, n) {
									if(i != "17"){
										var deptName = n.deptName;
										departmentInfoList += "<option value='"+n.deptID+"'>"
										+ deptName
										+ "</option>";
									}
									
								});
				departmentInfoList += "<option value='107'>纪检组</option>";
				$("#selectDeptName").html(departmentInfoList);
			},
			error : function(data) {
			}
		});
}

searchList();

//根据分类查询部门，并加载下拉连动	
$("#selectstatus").change(function() {
	searchList();
});




 

</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.all.min.js"></script>
</html>
