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
<title>新增用户</title>
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#search {float: left;width:600px;}
#Permission {float: left; margin-left:10px;}
a {color:blue;cursor: pointer;}
</style>
</head>
<body>
<div>
<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		用户管理
        <span>-></span>
		新增用户
    </div>
    <!--path End-->
     <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>新增用户</h2></div>
         <div class="con">
		<form id="uploadFile" enctype="multipart/form-data" method="POST"
		name="uploadFile">
		<div class="docEdit" >
			<ul>
				<li>
					<label class="red">账号名称：</label> 
					<input type="text"  name="userNo" id="userInfo" class="input input-small"  type="text" style="margin: 3px; padding: 5px 0px;"/>
					 <a onclick="showUserAll()"><b class="icon i-search"></b>用户名列表</a>
				</li>
				<li class="summary">
					<label>姓    名：</label>
					<input type="text"  name="userName" class="input input-small"  type="text" style="margin: 3px; padding: 5px 0px;"/>
				</li>
				<li class="summary">
				<label>角    色：</label>
				<select  name="userList" class="select-small"  style="margin: 2px; padding: 4px 0px;">
					<option value="">-=请选择栏目=-</option>
					<c:forEach var="s" items="${userRoleName}">
						<option value="${s.roleId}">${s.description}</option>
					</c:forEach>
				</select> 
				
				<li class="summary" style="margin: 2px; padding: 4px 0px;">
				<label>部     门：</label>
				<select class="select-small" name="deptList">
						<option value="">-=请选择栏目=-</option>
						<c:forEach items="${userDeptList}" var="dept">
	                   	 <option  value="${dept.deptID}">${dept.deptName}</option>
	                    </c:forEach>
                </select>
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
    
	<div class="column" id="Permission">
        <div class="title"><h2><b class="icon i-search"></b>用户列表</h2></div>
        <div class="con"  style="margin: 2px; padding: 4px 0px;">
        	<iframe name="userListFrame" id="userListFrame" width="100%" height="100%"
	    		src="" frameborder="0" scrolling="auto">
	    		 </iframe>
    	</div>
    </div>
</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function() {
	
	var minHeight = $("body").height()-140;
	if ($("#search .con").height() > minHeight) {
		$("#Permission .con").height($("#search .con").height());
	} else {
		$("#search .con").height(minHeight)
		$("#Permission .con").height(minHeight);
	}
	$("#Permission").width($("body").width()-$("#search").width()-38);
});

function showUserAll() {
	$("#userListFrame")
	.attr(
			"src",
			"sysMgt.xhtml?face=listUserForOA");
}
function saveUserInfo() {
	var userNo = $("input[name='userNo']").val();
	var userName = $("input[name='userName']").val();
	var roleId=$("select[name='userList']").val();
	var deptID =  $("select[name='deptList']").val();
	$.ajax({
		url : "sysMgt.xhtml?face=saveUserInfo",
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