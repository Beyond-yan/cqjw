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
<title>考核标准管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		系统管理
        <span>-></span>
		考核标准管理
    </div>
    <!--path End-->
    
    <!--column01 Start-->
	<div class="column">
	  	<div class="title">
		  	<h2><b class="icon i-search"></b>考核标准列表
			  	<span style="float:right;margin-right:50px;margin-top:5px">
			  	<a href="sysMgt.xhtml?face=addCheckInfoView" class="btn btn-blue">新  增</a>
			  	</span>
		  	</h2>
	  	</div>
   		
        <div class="con">
			
			<table width="100%" border="1" class="dataList">
				<thead>
					<tr>
						<td width="5%">序号</td>
						<td width="15%" style="text-align:left;">名称</td>
						<td width="15%" style="text-align:left;">分值</td>
						<td width="15%" style="text-align:left;">创建人</td>
						<td width="15%" style="text-align:left;">创建时间</td>
						<td width="5%">编辑</td>
						<td width="5%">删除</td>
					</tr>
				</thead>
				<tbody id="tbodyNews">
					<c:forEach items="${paginations}" var="checkInfo" varStatus="status">
						<tr>
							<td>${checkInfo.rowNo}</td>
							<td style="text-align:left;">${checkInfo.itemName}</td>
							<td style="text-align:left;">${checkInfo.score}</td>
							<td style="text-align:left;">${checkInfo.createUser}</td>
							<td style="text-align:left;">${checkInfo.createDate}</td>
							<td>
								<a href="javascript:;" onclick="editCheckInfo('${checkInfo.code}');return false;" class="icon i-edit"></a>
							</td>
							<td>
								<a href="javascript:;" onclick="deleteCheckInfo('${checkInfo.checkId}');return false;" class="icon i-del-02"></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="checkInfoListPager" class="page clearfix"></div>
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
	<c:if test="${!empty paginations[0].totalCount}">
	$("#checkInfoListPager").buildPager({
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
	
	var pageIndex = $("#checkInfoListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 1;
	}
	location.href ="sysMgt.xhtml?face=listCheckStandardPage&pageIndex="+pageIndex;
}
//删除
function deleteCheckInfo(checkId) {
	
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) {
		
		if (action == "ok") {
			$.ajax({
				url : "sysMgt.xhtml?action=deleteCheckInfo",
				cache : false,
				type : "get",
				dataType : "text",
				data : {
					"checkId" :checkId
				},
				success : function(date) {
					var content = eval('(' + date + ')');
					//asyncbox.alert(content.msg, "信息窗口", function() {
						document.location.reload();
					//});

				},
				error: function(content){
					asyncbox.alert("内部错误,删除失败", "信息窗口", function() {
						document.location.reload();
					});
				}
			});
		}
	})
}

//编辑
function editCheckInfo(code){
	location.href ="sysMgt.xhtml?face=editCheckInfoView&code="+code;
}

</script>
</html>