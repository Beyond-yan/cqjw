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
</style>
<title>信息工作通知</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		信息报送
        <span>-></span>
		信息工作通知
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
        <!-- 	<div style="margin-bottom: 10px;"> -->
        		<label>标题：</label><input type="text" name="workTitle" class="input-text" value="${workTitle}">
        		<label>发布时间：</label>
                <div class="data" id="dataSelect-start">
                    <input type="text"  name="beginDate" value='<fmt:formatDate value="${beginDate}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
                </div>
                <span>&nbsp;～</span>
                <div class="data" id="dataSelect-end">
                	<input type="text" name="endDate" value='<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput" >
                </div>
                <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">搜  索</a>
        	</div>
     </div>
     <!--column01 End-->
     <!--column02 Start-->
     <div class="column" >
          <div class="title"><h2><b class="icon i-search"></b>通知列表</h2></div>
            <div class="con">
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>                    
                    <td width="5%">序号</td>
                    <td width="30%">标题</td>
                    <td width="10%">发布人</td>
                    <td width="10%">发布时间</td>
                  </tr>
                </thead>
                <tbody id="tbodyNews">
		               <c:forEach items="${infoNoticeList}" var="infoNotice" varStatus="status">
						<tr>
							<td>${status.index+1}</td>
		                    <td><a href="infoNotice.xhtml?face=infoNoticeDetail&workId=${infoNotice.workId}" ><span style='color:blue'>${infoNotice.workTitle}</span></a></td>
		                    <td>${infoNotice.workCreateUserName}</td>
		                    <td>${infoNotice.workPublishDateStr}</td>
						</tr>
					</c:forEach>
			    </tbody>
			</table>
			<div id="pubListPager" class="page clearfix"></div>
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
	var totalCount = $("#totalCount").val();
	var pageSize = $("#pageSize").val();
	var pageIndex = $("#pageIndex").val();
	
	<c:if test="${!empty infoNoticeList[0].totalCount}">
	$("#pubListPager").buildPager({
		totalLines:${infoNoticeList[0].totalCount},
		pageSize:${infoNoticeList[0].pageSize},
		startIndex:${infoNoticeList[0].pageIndex},
		displayNum:5,
		afterChange:function() {
			searchList();
		}
	});
	</c:if>
});
function searchList() {
	var workTitle = encodeURI(encodeURI($("input[name='workTitle']").val()));
	var beginDate = $("input[name='beginDate']").val();
	var endDate = $("input[name='endDate']").val();
	var pageIndex = $("#pubListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="infoNotice.xhtml?face=queryInfoNotice&workTitle="+workTitle+"&beginDate="+beginDate+"&endDate="
	+endDate+"&pageIndex="+pageIndex;
}
</script>
</html>
