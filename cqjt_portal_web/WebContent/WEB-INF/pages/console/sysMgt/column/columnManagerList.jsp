<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<!DOCTYPE html 
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
<title>栏目管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		系统管理
        <span>-></span>
		栏目管理
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
        <!-- 	<div style="margin-bottom: 10px;"> -->
        		<label>栏目名称：</label><input type="text" name="columnName" class="input-text" value="${categoryName}">
        		<label>创建时间：</label>
                <div class="data" id="dataSelect-start">
                    <input type="text"  name="startDate" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
                </div>
                <span>&nbsp;～</span>
                <div class="data" id="dataSelect-end">
                	<input type="text" name="endDate" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput" >
                </div>
                <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">搜  索</a>
                <a href="column.xhtml?face=addColumn" class="btn btn-blue">新  增</a>
        	</div>
     </div>
     <!--column01 End-->
     <!--column02 Start-->
     <div class="column" >
          <div class="title"><h2><b class="icon i-search"></b>栏目列表</h2></div>
            <div class="con">
               <%--  <input type="hidden"  id="totalCount" value="${paginations.totalCount}" />
                <input type="hidden" id="pageSize" value="${paginations.pageSize}" />
                <input type="hidden" id="pageIndex" value="${paginations.pageIndex}" /> --%>
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>                    
                    <td width="5%">序号</td>
                    <td width="20%">栏目名称</td>
                     <td width="10%">是否发布外网</td>
                    <td width="10%">创建人</td>
                    <td width="15%">创建时间</td>
                    <td width="10%">编辑</td>
                    <td width="10%">删除</td>
                  </tr>
                </thead>
		               <tbody id="tbodyColumn">
		               <c:forEach items="${paginations}" var="column" varStatus="status">
						<tr>
							<td>${column.rowNo}</td>
		                      <td><a href="column.xhtml?face=editColumn&categoryId=${column.categoryId}"><span style='color:blue'>${column.categoryName}</span></a></td>
		                     <td>
		                     	<c:if test="${column.isOutsite==1}">是</c:if>
		                     	<c:if test="${column.isOutsite==0}">否</c:if>
		                     </td>
		                       <td>${column.createBy}</td>
		                       <td>${column.createDate}</td>
		                       <td><a href="javascript:;" onclick="editColumn('${column.categoryId}');return false;" class="icon i-edit"></a></td>
		                       <td><a href="javascript:;"  onclick="deleteColumn('${column.categoryId}');return false;" class="icon i-del-02"></a></td>
						</tr>
					</c:forEach>
				    </tbody>
			</table>
			<div id="columnListPager" class="page clearfix"></div>
        </div>
       
    </div>
    <!--column02 End-->
</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function(){
	<c:if test="${!empty paginations[0].totalCount}">
	$("#columnListPager").buildPager({
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

function searchList() {
	var columnName = encodeURI(encodeURI($("input[name='columnName']").val()));
	var startDate = $("input[name='startDate']").val();
	var endDate = $("input[name='endDate']").val();
	var pageIndex = $("#columnListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="column.xhtml?face=searchColumnList&categoryName="+columnName+"&startDate="+startDate+"&endDate="
	+endDate+"&pageIndex="+pageIndex;
}


//删除信息
function deleteColumn(categoryId) {
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) { 
		if (action == "ok") {
			$.ajax({
				url : "column.xhtml?action=deleteColumn",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"categoryId" : categoryId
				},
				success : function(content) {
					//asyncbox.alert( "删除成功！", "信息窗口", function() {
						document.location.reload();
					//});

				}
			});
		}
	})
}

//编辑
function editColumn(categoryId){
	location.href ="column.xhtml?face=editColumn&categoryId="+categoryId;
}

</script>
</html>
