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
<title>编辑用户</title>
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
  a {color:blue;cursor: pointer;} 
</style>
</head>
<body>
<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
         <a href="sysMgt.xhtml?face=userMgt">用户管理</a>
        <span>-></span>
		用户编辑
    </div>
    <!--path End-->
     <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>用户编辑</h2></div>
         <div class="con">
	<form id="uploadFile" enctype="multipart/form-data" method="POST"
		name="uploadFile">
		<div class="docEdit" >
			<ul>
				<li>
					<label class="red">用户名：</label>
					<input type="text"  name="userNo" class="input input-small" value="${userEditInfo.userNo}" disabled='disabled' style="color:gray ;margin: 3px; padding: 5px 0px;"/>
				</li>
				<li class="summary">
					<label>姓名：</label>
					<input type="text"  name="userName" class="input input-small" value="${userEditInfo.userName}"style="margin: 3px; padding: 5px 0px;"/>
				</li>
				<li class="summary">
				<label>角   色：</label>
				<select  name="userList" class="select-small" style="margin: 2px; padding: 4px 0px;" id="roleName">
					<%-- <option value="${userEditInfo.roleID}">${userEditInfo.roleDesc}</option> --%>
					<c:forEach var="s" items="${userRoleName}">
						<option value="${s.roleId}">${s.description}</option>
					</c:forEach>
				</select> 
				<input type="hidden" id="roleId" value="${userEditInfo.roleID}">
				<li class="summary" style="margin: 2px; padding: 4px 0px;">
				<label>部   门：</label>
				<select class="select-small" name="deptList" id="deptName">
						<%-- <option value="${userEditInfo.deptID}">${userEditInfo.deptName}</option> --%>
						<c:forEach items="${userDeptList}" var="dept">
	                   	 <option  value="${dept.deptID}">${dept.deptName}</option>
	                    </c:forEach>
                </select>
                <input type="hidden" id="deptId" value="${userEditInfo.deptID}">
				</li>
				
				<li class="infoCon clearfix">
					<label></label>
					<div class="btn">
						<a  href="javascript:;"   onclick="saveUserInfo();return false;" class="btn-gray"><b class="icon i-save"></b>保存</a>
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
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function(){
	 var roleId = $("#roleId");
	 $("#roleName option").each(function(){
	 	if($(this).val()==roleId.val()){
	 		$(this).attr("selected",true);
	  	}
	 }); 
	 
	 var deptId = $("#deptId");
	 $("#deptName option").each(function(){
	 	if($(this).val()==deptId.val()){
	 		$(this).attr("selected",true);
	  	}
	 }); 
})

function saveUserInfo() {
	var userNo = $("input[name='userNo']").val();
	var userName = $("input[name='userName']").val();
	var roleId=$("select[name='userList']").val();
	var deptID =  $("select[name='deptList']").val();	
	$.ajax({
		url : "sysMgt.xhtml?face=saveEditUserInfo",
		cache : false,
		type : "post",
		dataType : "html",
		data : {
			"userNo" : userNo,
			"userName" : userName,
		    "deptID" : deptID,
		    "roleId":roleId
		},
		success : function(content) {	
			var content = eval('(' + content + ')');
			asyncbox.confirm(content.msg,"信息窗口", function(action) {
				if (action == "ok") {
					document.location.href = "sysMgt.xhtml?face=userMgt";
				}
			});
		},
		error :function(content){
			var content = eval('(' + content + ')');
			alert(content.msg);
		}
			});
		}
</script>
</html>