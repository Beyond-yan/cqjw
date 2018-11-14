<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@ 
	taglib prefix="ut"
	uri="/WEB-INF/userTag/Pager.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet"
	type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet"
	type="text/css" />
<link
	href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
</style>
<title>网站信息</title>
</head>
<body>
	<div>
		<!--path Start-->
		<div class="path">
			<b class="icon i-home"></b> <span>当前位置：</span> 信息报送 <span>-></span>
			网站信息校编
		</div>
		<!--path End-->
		<!--column01 Start-->
		<div class="column" id="search">
			<div class="title">
				<h2>
					<b class="icon i-search"></b>快速搜索
				</h2>
			</div>
			<div class="con">
				<label>标题：</label><input type="text" name="newsTitle"
					class="input-text" value="${newsTitle}"> <label>投稿人：</label><input
					type="text" name="entryUser" class="input-text"
					value="${entryUser}"> <label>创建时间：</label>
				<div class="data" id="dataSelect-start">
					<input type="text" name="entryDateS" class="input-text tcal"
						value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>'>
				</div>
				<span>&nbsp;～</span>
				<div class="data" id="dataSelect-end">
					<input type="text" name="entryDateE" class="input-text tcal"
						value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>'>
				</div>
				<a href="javascript:;" onclick="selectList();return false;"
					class="btn btn-blue">搜索</a>
				<!-- <a href="textNews.xhtml?face=addView" class="btn btn-blue">新  增</a> -->
			</div>
		</div>
		<!--column01 End-->

		<!--column02 Start-->
		<div class="column">
			<div class="title">
				<h2>
					<b class="icon i-search"></b>信息列表
				</h2>
			</div>
			<div class="con">

				<table width="100%" border="1" class="dataList">
					<thead>
						<tr>
							<td width="5%">序号</td>
							<td width="33%">主标题</td>
							<td width="6%">滚动显示</td>
							<td width="6%">状态</td>
							<td width="13%">创建时间</td>
							<td width="11%">投稿部门</td>
							<td width="6%">投稿人</td>
							<td width="5%">编辑</td>
							<td width="5%">删除</td>
						</tr>
					</thead>
					<tbody id="tbodyMagazine">
						<c:forEach items="${paginations}" var="tn" varStatus="status">
							<tr>
								<td>${tn.rowNo}</</td>
								<!-- <td><a href="javascript:;" class="icon i-edit"></a></td> -->
								<th><a href="javascript:;"
									onclick="editTextNews('${tn.newsId}');return false;"><span
										style='color: blue'>${tn.newsTitle}</span>
								</a>
								</th>
								<td><c:if test='${tn.isPhotosShow==1}'>是</c:if>
									<c:if test='${tn.isPhotosShow==0}'>否</c:if>
								</td>
								<td>已分拣</td>
								<td>${tn.createDate}</td>
								<td>${tn.deptName}</td>
								<td>${tn.entryUser}</td>
								<td><a href="javascript:;"
									onclick="editTextNews('${tn.newsId}');return false;"
									class="icon i-edit"></a>
								</td>
								<td><a href="javascript:;"
									onclick="deleteNews('${tn.newsId}','12');return false;"
									class="icon i-del-02"></a>
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
<script type="text/javascript"
	src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>
<script type="text/javascript"
	src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function(){
	<c:if test="${!empty paginations[0]}">
	$("#userListPager").buildPager({
		totalLines:${paginations[0].totalCount},
		pageSize:${paginations[0].pageSize},
		startIndex:${paginations[0].pageIndex},
		displayNum:5,
		afterChange:function() {
			selectList();
		}
	});
	</c:if>
});

function selectList() {
	var newsTitle = encodeURI(encodeURI($("input[name='newsTitle']").val()));
	newsTitle = newsTitle.replace(/#/g,"%23");
	var entryUser = encodeURI(encodeURI($("input[name='entryUser']").val()));
	var entryDateS =  $("input[name='entryDateS']").val();
	var entryDateE =  $("input[name='entryDateE']").val();
	var pageIndex = $("#userListPager").attr("curPage");
	var flag="2";
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="siteTextNews.xhtml?face=queryVerifySiteNewsView&newsTitle="+newsTitle+"&entryUser="+entryUser+"&entryDateS="
	+entryDateS+"&entryDateE="+entryDateE+"&pageIndex="+pageIndex+"&flag="+flag;
}
/* //查看杂志信息
function showContent(newsTitle) {
	$("_blank")
	.attr(
			"src",
			"textNews.xhtml?face=showNews&newsTitle="
					+ newsTitle); 
}*/

//删除交通杂志信息
function deleteNews(newsId,flag) {
	
	//alert(newsId);
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) {
		
		if (action == "ok") {
			$.ajax({
				url : "siteTextNews.xhtml?action=deleteSiteInfo",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"outerNewsId" : "",
					"newsId" :newsId,
					"flag" :flag
				},
				success : function(data) {
					/* var content = eval('(' + data + ')');
					asyncbox.alert(content.msg, "信息窗口", function() {
						document.location.reload();
					}); */
					document.location.reload();
				}
			});
		}
	})
}

//编辑
function editTextNews(newsId){
	location.href ="siteTextNews.xhtml?face=editVerifySiteNewsView&newsId="+newsId;
}
</script>
</html>
