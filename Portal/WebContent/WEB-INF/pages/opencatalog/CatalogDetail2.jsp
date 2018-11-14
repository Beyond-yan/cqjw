<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
<title></title>
<style>
.newsDetail  h2 {
	line-height: 40px;
	padding: 0 0 22px;
}
tr {
    display: table-row;
    vertical-align: inherit;
    border-color: inherit;
}
.govDetailTable .tdlab {
    text-align: right;
    width: 15%;
    background: #f2f2f2;
    padding-right: 5px;
}

.govDetailTable td {
    background: #fff;
    padding: 6px 15px;
    /* width: 23%; */
    border: 1px solid #ddd;
    color: #333;
    font-size: 12px;
}
</style>
</head>

<body style="background: white;" onload="loadExternalResource()">
<!-- 禁止复制 -->
<!--  <body style="background: white;" onload="loadExternalResource()"  topmargin="0" oncontextmenu="return false" 
	ondragstart="return false" onselectstart ="return false" 
	oncopy="document.selection.empty()" onbeforecopy="return false">  -->
	
	<div class="mainList" id="lH" style="width:100%">
		<div class="breadCutNav">
			<b class="icon_home"></b> <span>当前位置：</span> <a
				href="${pageContext.request.contextPath}/index.html" target="top">首页</a>
			<span> &gt;</span><a href="${pageContext.request.contextPath}/openCatalog/init_/openCatalog/f1e8c2db-9e92-4a1e-a1cb-48569fa7f308.html"  target="_top">政务公开</a><span> &gt;</span>${topName}
		</div>
		<div class="blank_7px"></div>
		<div class="newsDetail">
			<h2>${newsTitle}</h2>
			<p class="dotLine_2px"></p>
			<ul class="tip clearfix">
				<c:if test="${modifyDate ne null}">
					<li class="al_l">发布时间：${modifyDate}&nbsp;&nbsp;&nbsp;发布：${newsSourceName}</li>
					<li class="al_c">共${readRecordCount}人阅读</li>
				</c:if>
				
				<%@ include file="/WEB-INF/pages/common/share.jsp"%>
			</ul>
			<div>
				<table width="100%" class="govDetailTable" cellpadding="0" cellspacing="0">
	            
	            <tr>
	                <td class="tdlab">文件分类：</td>
	                <td >其他</td>
	                <td class="tdlab">发布机构：</td>
	                <td>重庆市人民政府</td>
	            </tr>
	            <tr>
	                
	                <td class="tdlab">印发日期：</td>
	                <td>${modifyDate}</td>
	                <td class="tdlab">发布日期：</td>
	                <td>${modifyDate}</td>
	            </tr>
				<tr>
					<td class="tdlab">名称：</td>
					<td colspan="5">${newsTitle}</td>
				</tr>
	            <tr>
	                <td class="tdlab">文 号：</td>
	                <td colspan="5">渝府发〔2018〕46号</td>
	            </tr>
	            </tbody>
	       		</table>
				
				${newsContent}
			</div>
		</div>
	</div>
</body>
</html>