<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css"/>
<title>用户管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		系统管理
        <span>-></span>
		用户管理
    </div>
    <!--path End-->
    
    <!--column01 Start-->
	<div class="column">
	  	<div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
   		<div class="con">
	   		<div style="margin-bottom: 10px;">
					<label style="padding-left: 30px;">用户名：</label><input type="text" class="input-text" name="userNo" size="28" style="margin-right: 30px;" value="${userNo}">
					<label>角色：</label>
					<select id="userRoleName" name="roleName" class="select-small"   style="margin-right: 30px;">
						<option value="">--请选择--</option>
						<c:forEach var="s" items="${userRoleName}">
							<option value="${s.roleName}">${s.description}</option>
						</c:forEach>
					</select> 
					<input type="hidden" value="${roleName }" id="roleStatus">
					<label>部门：</label>
					<select id="userDeptName" name="deptname" class="select-small"  style="margin-right: 30px;">
						<option value="">--请选择--</option>
						<c:forEach var="dept" items="${userDeptName}">
							<option value="${dept.deptID}">${dept.deptName}</option>
						</c:forEach>
					</select> 
					<input type="hidden" value="${deptID }" id="deptStatus">
					<a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">查询</a>
					<a href="sysMgt.xhtml?face=listAddUser" class="btn btn-blue">新  增</a>
			</div>
		</div>
        <div class="con">
			
			<table width="100%" border="1" class="dataList">
				<thead>
					<tr>
						<td width="5%">序号</td>
						<td width="15%" style="text-align:left;">用户名</td>
						<td width="15%" style="text-align:left;">姓名</td>
						<td width="15%" style="text-align:left;">角色</td>
						<td width="15%" style="text-align:left;">部门</td>
						<td width="5%">编辑</td>
						<td width="5%">删除</td>
					</tr>
				</thead>
				<tbody id="tbodyNews">
					<c:forEach items="${paginations}" var="user" varStatus="status">
						<tr>
							<td>${user.rowNo}</td>
							<td style="text-align:left;">${user.userNo}</td>
							<td style="text-align:left;">${user.userName}</td>
							<td style="text-align:left;">${user.roleDesc}</td>
							<td style="text-align:left;">${user.deptName}</td>
							<td>
								<a href="javascript:;" onclick="editTextNews('${user.userId}');return false;" class="icon i-edit"></a>
							</td>
							<td>
								<a href="javascript:;" onclick="deleteUser('${user.userId}');return false;" class="icon i-del-02"></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="userListPager" class="page clearfix"></div>
		</div>
     </div>
	<!--column02 End-->

</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function(){
	var roleName = $("#roleStatus");
	$("#userRoleName option").each(function(){
		if($(this).val()==roleName.val()){
			$(this).attr("selected",true);
		}
	});
	var deptStatus = $("#deptStatus");
	$("#userDeptName option").each(function(){
		if($(this).val()==deptStatus.val()){
			$(this).attr("selected",true);
		}
	});
	
	<c:if test="${!empty paginations[0].totalCount}">
	$("#userListPager").buildPager({
		totalLines:${paginations[0].totalCount},
		pageSize:${paginations[0].pageSize},
		startIndex:${paginations[0].pageIndex},
		displayNum:5,
		afterChange:function() {
			searchList();
		}
	});
	</c:if>
});

//用户信息查询功能
function searchList() {
	var userNo = encodeURI(encodeURI($("input[name='userNo']").val()));
	var roleName = encodeURI(encodeURI($("select[name='roleName']").val()));
	var deptID =  $("select[name='deptname']").val();
	/* var pageIndex = $("#pageIndex").val(); */
	var pageIndex = $("#userListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="sysMgt.xhtml?face=searchUserInfo&userNo="+userNo+"&roleName="+roleName+"&deptID="
	+deptID+"&pageIndex="+pageIndex;
	
	
}

//删除用户信息
function deleteUser(userId) {
	
	//alert(userId);
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) {
		
		if (action == "ok") {
			$.ajax({
				url : "sysMgt.xhtml?face=deleteUser",
				cache : false,
				type : "get",
				dataType : "text",
				data : {
					"userId" :userId
				},
				success : function(date) {
				    var content = eval('(' + date + ')');
					asyncbox.alert(content.msg, "信息窗口", function() {
						document.location.reload();
					});

				}
			});
		}
	})
}

//编辑
function editTextNews(userId){
	location.href ="sysMgt.xhtml?face=editView&userId="+userId;
}

</script>
</html>
