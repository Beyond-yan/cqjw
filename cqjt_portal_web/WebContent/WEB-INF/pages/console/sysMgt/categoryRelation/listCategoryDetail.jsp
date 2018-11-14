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
#search {float: left;width:100%;}
a {color:blue;cursor: pointer;}
</style>
<title>后台管理</title>
</head>
<body>
<div>
        <div class="con">
        <table width="100%" border="1" class="dataList">
       		  <thead>
                  <tr>
						<td width="10%" >序号</td>
						<td width="15%">内网栏目</td>
						<td width="15%" >映射外网栏目</td>
						<td width="15%" >操作</td>
					</tr>
                </thead>
               <tbody id="tbodyDept">
            		  <c:forEach items="${relationList}" var="tn" varStatus="status">
             			 <tr>
						  	<td>${status.index + 1}</td>
						  	<td>${tn.innerCategory}</td>
						  	<td>${tn.outerCategory}</td>
						  	<td>
						  		[<a href="javascript:;" onclick="deleteCategoryInfo('${tn.relationId}');return false;">删除</a>] 
	   	             		    &nbsp; &nbsp;[<a href="javascript:;" onclick="editCategoryInfo('${tn.relationId}');return false;">编辑</a>]
						  	</td>
						</tr>
					</c:forEach>	
			    </tbody>
			 </table>	 
    
		</div>
		<!--column01 End-->
</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">

//删除信息
function deleteCategoryInfo(relationId) {
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) { 
		if (action == "ok") {
			$.ajax({
				url : "sysMgt.xhtml?action=deleteRelation",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"relationId" : relationId
				},
				success : function(content) {
				/* 	asyncbox.alert( "删除成功！", "信息窗口", function() {
						document.location.reload();
					}); */
					document.location.reload();
				}
			});
		}
	})
}

//编辑
function editCategoryInfo(relationId){
	location.href ="sysMgt.xhtml?face=editCategoryRelationView&relationId="+relationId;
}

</script>
</html>
