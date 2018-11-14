<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>系统管理</title>
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#search {float: right;width:100%;}
#Permission {float: right; margin-left:10px;}
a {color:blue;cursor: pointer;}
.error{color:red;padding-left: 10px;}
</style>
</head>
<body>
<div>
<!--path Start-->
  <!--   <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		栏目与处室关系管理
        <span>-></span>
      	部门栏目编辑
    </div> -->
    <!--path End-->
     <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>部门与栏目编辑页面</h2></div>
         <div class="con">
		<form id="columnDept" enctype="multipart/form-data" method="POST"
		name="uploadFile">
		<div class="docEdit" >
		<input type="hidden" value="${categoryDeptDetail.dcId}" name="dcId"/>
			<ul>
                <li>
                	<label><span style="color:red">*</span>部门名称：</label>
                	 <input type="hidden" name="deptID" value="${categoryDeptDetail.deptID}"/>
                	<select class="select-small" name="dept" id="deptName" style="margin: 3px;width:300px;" disabled="disabled">
                		<option value="${categoryDeptDetail.deptID}">${categoryDeptDetail.deptName}</option>
                			<c:forEach items="${dept}" var="c">
                				<option value="${c.deptID}">${c.deptName}</option>
                			</c:forEach>
                	</select>
                </li>
    			<li>
    				<label><span style="color:red">*</span>栏目名称：</label>
    				<select class="select-small" name="categoryId" id="categoryName" style="margin: 3px;width:300px;">
                    	<%-- <option value="${categoryDeptDetail.categoryId}">${categoryDeptDetail.categoryName}</option> --%>
                    	<c:forEach items="${TextCategorys}" var="c">
	                   	 <option value="${c.categoryId}">${c.categoryName}</option>
	                    </c:forEach>
	                </select>
	                <input type="hidden" id="categoryId" value="${categoryDeptDetail.categoryId}">
                </li>
                <li><br><br></li>
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
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function(){
	 var categoryId = $("#categoryId");
	 $("#categoryName option").each(function(){
	 	if($(this).val()==categoryId.val()){
	 		$(this).attr("selected",true);
	  	}
	 }); 
})


function save() {
	
	var options = {
		url : "columnDept.xhtml?action=save",
		type : "post",
		dataType : "html",
		beforeSubmit : function(){
			
		},
		success : function(data) {
			var content = eval('(' + data + ')');
			if(content.msg !="error"){
				asyncbox.confirm("更新成功!" + "是否返回列表页面？", "确认窗口", function(action) {
					if (action == "ok") {
						document.location.href="columnDept.xhtml?face=queryCategoryDeptList&deptID="+content.msg;
					}
				}); 
			}else{
				asyncbox.confirm("该栏目已存在!请重新选择对应栏目!", "确认窗口");
			}
			
		},
		error :function(data){
			alert("内部错误，保存失败");
		}
	};
	$("#columnDept").ajaxSubmit(options);
	
}
		
</script>
</html>
