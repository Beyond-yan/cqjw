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
#search {float: left;width:98%;margin-left:10px;}
#Permission {float: left; margin-left:10px;}
a {color:blue;cursor: pointer;}
</style>
</head>
<body>
<div>
	<div class="column" id="search">
	<div class="con"  >
	   		<label>账号名称：</label><input type="text" name="userNo" class="input-text" value="${userNo }">
	   		<label>用户姓名：</label><input type="text" name="userName" class="input-text" value="${userName }">
	        <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">搜  索</a>
     </div>
     </div> 
      <div class="column" id="search">
 	  <div class="con"  >  
        <table width="100%" border="1" class="dataList" >
				<thead>
					<tr>
						<td width="5%" >序号</td>
						<td width="20%" >账号名称</td>
						<td width="20%" >用户姓名</td>
					</tr>
				</thead>
				<tbody id="tbodyNews">
					<c:forEach items="${paginations}" var="ur" varStatus="status">
						<tr>
							<td>${ur.rowNo}</td>
							<td>${ur.userNo}</td>
							<td>${ur.userName}</td>
						</tr>
					</c:forEach> 
				</tbody>
			</table> 
			<div id="userListPager" class="page clearfix"></div>
    	</div>
    </div>
</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/custom.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function(){
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

function searchList() {
	var userNo = encodeURI(encodeURI($("input[name='userNo']").val()));
	var userName = encodeURI(encodeURI($("input[name='userName']").val()));
	var pageIndex = $("#userListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="sysMgt.xhtml?face=queryUserList&userNo="+userNo+"&userName="+userName+"&pageIndex="+pageIndex;
}
</script>
</html>
