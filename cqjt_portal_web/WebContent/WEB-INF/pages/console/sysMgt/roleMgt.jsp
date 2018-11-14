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
<style type="text/css">
#search {float: left;width:560px;}
#Permission {float: left; margin-left:10px;}
a {color:blue;cursor: pointer;}
</style>
<title>角色管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		系统管理
        <span>-></span>
		角色管理
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>角色列表</h2></div>
        <div class="con">
        	<div style="margin-bottom: 10px;">
        		<a href="sysMgt.xhtml?face=turnToAddRole&roleId=" target="arrPermissionFrame" 
        			class="btn btn-blue">新  增</a>
        	</div>
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>
                    <td width="30">序号</td>
                    <td width="120" style="text-align: left">名称</td>
                    <td width="*" style="text-align: left">描述</td>
                    <td width="140">操作</td>
                  </tr>
                </thead>
                <tbody>
                <c:forEach items="${paginations}" var="role" varStatus="status">
					<tr>
						<td>${status.index + 1}</</td>
						<td style="text-align: left">${role.roleName}</td>
						<td style="text-align: left">${role.description}</td>
						<td>
							[<a onclick="showEdit('${role.roleId}');return false;">编辑</a>] 
							[<a onclick="deleteRole('${role.roleId}');return false;">删除</a>] 
							[<a onclick="arrPermission('${role.roleId}');return false;">权限</a>]
							<%-- [<a onclick="selUser('${role.roleId}');return false;">用户</a>] --%>
						</td>
					</tr>
				</c:forEach>
			    </tbody>
			</table>
        </div>
    </div>
    <div class="column" id="Permission">
        <div class="title"><h2><b class="icon i-search"></b>角色编辑</h2></div>
        <div class="con">
	    	<iframe name="arrPermissionFrame" id="arrPermissionFrame" width="100%" height="100%"
	    		src="" frameborder="0" scrolling="auto"> </iframe>
    	</div>
    </div>
    <!--column01 End-->

</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
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
/*
function queryData() {

}
function jumpToPage(pIndex) {
	var roleName = $("#roleName").val();
	var url = "sysMgt.xhtml?face=getRoleList&roleName=" + roleName + "&pageIndex=" + pIndex;
	document.location.href = url;
}
*/
function showEdit(roleId) {
	$("#arrPermissionFrame")
	.attr(
			"src",
			"sysMgt.xhtml?face=turnToAddRole&roleId="
					+ roleId);
}
function deleteRole(roleId) {
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) {
		if (action == "ok") {
			$.ajax({
				url : "sysMgt.xhtml?face=deleteRole",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"roleId" : roleId
				},
				success : function(content) {
					asyncbox.alert("删除成功！", "信息窗口", function() {
						document.location.reload();
					});

				}
			});
		}
	})
}
function arrPermission(roleId) {
	$("#arrPermissionFrame")
			.attr(
					"src",
					"sysMgt.xhtml?face=turnToArrPermission&roleId="
							+ roleId);
}
/*
$(document).ready(function() {
	if ("${msg}" != "") {
		alertInfo("${msg}");
	}
});

function showAdd() {
	document.getElementById("arrPermissionFrame").style.display = "none";
	document.location.href = "sysMgt.xhtml?face=turnToAddRole&roleId=";
}



function arrPermission(roleId) {
	document.getElementById("arrPermissionFrame").style.display = "";
	$("#arrPermissionFrame")
			.attr(
					"src",
					"sysMgt.xhtml?face=turnToArrPermission&roleId="
							+ roleId);
}



function alertInfo(message) {
	asyncbox.alert(message, "信息窗口", function() {
	});
}

function queryData(p, s) {
	jumpPage(p, s);
}

//分页控制
function jumpPage(jumpPage, pageSize) {
	document.getElementById("arrPermissionFrame").style.display = "none";
	$.ajax({
		url : "sysMgt.xhtml?face=queryRoleListByPageIndex",
		cache : false,
		type : "POST",
		dataType : "text",
		data : {
			"pageIndex" : jumpPage,
			"pageSize" : pageSize,
			"roleName" : $("#roleName").val() == null ? ""
					: encodeURIComponent($("#roleName").val())
		},
		success : function(content) {
			var pageData = eval('(' + content + ')');
			var pageIndex = eval(pageData.pageIndex);
			var pageSize = eval(pageData.pageSize);
			var pageCount = eval(pageData.pageCount);
			var tableData = eval(pageData.pageData);
			$("#mytbody").empty().append(
					createNewPageHTML(tableData, pageIndex, pageSize,
							pageCount));
			updatePageInfo(pageData.pageIndex, pageData.totalCount,
					pageData.pageCount);
		},
		error : function() {
		}
	});
}
function updatePageInfo(pageIndex, totalCount, pageCount) {
	$("#totalCount").empty().append(totalCount);
	$("#pageIndex1").empty().append(pageIndex);
	$("#pageCount1").empty().append(pageCount);
	$("#jumpPage1").val(pageIndex);
	$("#pageIndex").val(pageIndex);
	$("#pageCount").val(pageCount);
	hanlderPageLinkAttr('firstPage', 'perPage', 'nextPage', 'lastPage',
			pageIndex, pageCount);
}
function createNewPageHTML(tableData) {
	var newPageHtml = "";
	for ( var i = 0; i < tableData.length; i++) {
		newPageHtml += "<tr>";

		newPageHtml += "<td>";
		newPageHtml += i + 1;
		newPageHtml += "</td>";

		newPageHtml += "<td>";
		newPageHtml += tableData[i].ROLENAME;
		newPageHtml += "</td>";

		newPageHtml += "<td>";
		newPageHtml += tableData[i].DESCRIPTION;
		newPageHtml += "</td>";

		newPageHtml += "<td >";
		newPageHtml += "<a class='btn44_20 btnBlue' onclick=showEdit('"
				+ tableData[i].ROLEID + "');return false;'>编辑</a>";
		newPageHtml += "<a class='btn44_20 btnBlue' onclick=deleteRole('"
				+ tableData[i].ROLEID + "');>删除</a>";
		newPageHtml += "<a class='btn44_20 btnBlue' onclick=arrPermission('"
				+ tableData[i].ROLEID + "');return false;'>权限</a>";
		newPageHtml += "</td>";

		newPageHtml += "</tr>";
	}

	return newPageHtml;
}
*/
</script>
</html>