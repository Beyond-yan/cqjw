<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %><%@ 
	taglib prefix="ut" uri="/WEB-INF/userTag/Pager.tld"%><!DOCTYPE html 
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
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
</style>
<title>部门管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		系统管理
        <span>-></span>
		部门管理
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
       		 <div style="margin-bottom: 10px;" >
        		<label style="padding-left: 30px;">部门ID：</label><input type="text" name="deptID"  style="margin-right: 30px;" class="input-text" value="${deptID}">
        		<label>部门名称：</label><input type="text" name="deptName"  style="margin-right: 30px;" class="input-text" value="${deptName}">
        		<label>部门分类：</label> 
        	<select id="selectstatus" name="deptCategory" class="input-small" style="margin-right: 30px;">
				<option selected value="">--请选择--</option>
				<option value="市交委机关">市交委机关</option>
				<option value="委属单位">委属单位</option>
				<option value="区县交通局">区县交通局</option>
                <option value="市属相关交通企业">市属相关交通企业</option>
			</select>
			<input type="hidden" id="status" value="${deptCategory}">
                <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">搜索</a>
                <a href="sysMgt.xhtml?face=listAddDept" class="btn btn-blue">新增</a>
        	</div>
   	 	</div>
     <!--column01 End-->
     
     <!--column02 Start-->
 	 <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>部门信息列表</h2></div>
        <div class="con">
        	
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>                    
                    <td width="5%">序号</td>
                    <td width="10%" style="text-align: left">部门分类</td>
                    <td width="8%" style="text-align: left">部门ID</td>
                    <td width="15%" style="text-align: left">部门名称</td>
                    <td width="8%">状态</td>
                    <td width="8%">编辑</td>
                    <td width="8%">启用/停用</td>
                    <td width="7%">删除</td>
                  </tr>
                </thead>
                <tbody id="tbodyMagazine">
                <c:forEach items="${departmentInfoList}" var="tn" varStatus="status">
					<tr>
						<td>${tn.rowNo}</td>
						<th>${tn.deptCategory}</th>
						<th>${tn.deptID}</th>
						<th>${tn.deptName}</th>
                        <td><c:if test='${tn.flag==1}'>停用</c:if><c:if test='${tn.flag==0}'>启用</c:if></td>
                        <td><a href="javascript:;" onclick="editDept('${tn.deptID}');return false;" class="icon i-edit"></a></td>
                        <td><c:if test='${tn.flag==1}'><a href="javascript:;" onclick="updateDept('${tn.deptID}','${tn.flag}');return false;" class="icon i-back"></a></c:if>
                        	<c:if test='${tn.flag==0}'><a href="javascript:;" onclick="updateDept('${tn.deptID}','${tn.flag}');return false;" class="icon i-del-02"></a></c:if>
                        </td>
                        <td>
							<a href="javascript:;" onclick="deleteDept('${tn.deptID}');return false;" class="icon i-del-02"></a>
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
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function(){
	var category = $("#status");
	$("#selectstatus option").each(function(){
		if($(this).val()==category.val()){
			$(this).attr("selected",true);
		}
	});
	
	<c:if test="${!empty departmentInfoList[0].totalCount}">
	$("#userListPager").buildPager({
		totalLines:${departmentInfoList[0].totalCount},
		pageSize:${departmentInfoList[0].pageSize},
		startIndex:${departmentInfoList[0].pageIndex},
		displayNum:5,
		afterChange:function() {
			searchList();
		}
	});
	</c:if>
});

function searchList() {
	var deptID = $("input[name='deptID']").val();
	var deptName = encodeURI(encodeURI($("input[name='deptName']").val()));
	var deptCategory = encodeURI(encodeURI($("select[name='deptCategory']").val()));
	var pageIndex = $("#userListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="sysMgt.xhtml?face=queryDeptInfoView&deptID="+deptID+"&deptName="+deptName+"&deptCategory="
	+deptCategory+"&pageIndex="+pageIndex;
}

//设置部门状态
function updateDept(deptID,flag) {
	
	if(flag==0){
	asyncbox.confirm("设置该部门为停用状态?", "信息窗口", function(action) {
		
		if (action == "ok") {
			$.ajax({
				url : "sysMgt.xhtml?action=updateDeptInfo",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"deptID" :deptID,
				},
				success : function(content) {
					 asyncbox.alert("设置成功！", "信息窗口", function() {
						document.location.reload();
					}); 
					document.location.reload();
				}
			});
		}
	})
	}else{
		asyncbox.confirm("设置该部门为启用状态?", "信息窗口", function(action) {
			
			if (action == "ok") {
				$.ajax({
					url : "sysMgt.xhtml?action=updateDeptInfo",
					cache : false,
					type : "GET",
					dataType : "text",
					data : {
						"deptID" :deptID,
					},
					success : function(content) {
						 asyncbox.alert("设置成功！", "信息窗口", function() {
							document.location.reload();
						}); 
						document.location.reload();
					}
				});
			}
		})
	}
}
//删除部门信息
function deleteDept(deptID) {
	
	asyncbox.confirm("是否删除该部门信息?", "信息窗口", function(action) {
		
		if (action == "ok") {
			$.ajax({
				url : "sysMgt.xhtml?action=deleteDeptInfo",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"deptID" :deptID,
				},
				success : function(content) {
					 asyncbox.alert("删除成功！", "信息窗口", function() {
						document.location.reload();
					}); 
					document.location.reload();
				}
			});
		}
	})
}
//编辑
function editDept(deptId){
	var deptID = $("input[name='deptID']").val();
	var deptName = encodeURI(encodeURI($("input[name='deptName']").val()));
	var deptCategory = encodeURI(encodeURI($("select[name='deptCategory']").val()));
	var pageIndex = $("#userListPager").attr("curPage");
	location.href ="sysMgt.xhtml?face=editDeptView&deptId="+deptId+"&deptName="+deptName+"&deptCategory="
	+deptCategory+"&pageIndex="+pageIndex+"&deptID="+deptID;
}

</script>
</html>
