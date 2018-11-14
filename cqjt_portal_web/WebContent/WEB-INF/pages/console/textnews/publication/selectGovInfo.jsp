<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><!DOCTYPE html 
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
<title>政务信息查询</title>
</head>
<body>
<div>
    <!--column01 Start-->
    <div id="search">
    	<form action="govTextNews.xhtml?face=listGovInfoForPub" method="post" enctype="multipart/form-data">
        <div style="padding:8px;">
    		<label>标题：</label><input type="text" name="newsTitle" class="input-text" value="${newsTitle}">
    		<label>时间：</label>
            <div class="data" id="dataSelect-start">
                <input type="text" name="entryDateS" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
            </div>
            <span>&nbsp;～</span>
            <div class="data" id="dataSelect-end">
            	<input type="text" name="entryDateE" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput" >
            </div>
            <input type="submit" value="提 交" />
		</div>
		</form>
		<table width="100%" border="1" class="dataList">
			<thead>
				<tr>                    
                    <td width="6%">选择</td>
                    <td width="*">标题</td>
                    <td width="16%">投稿部门</td>
                    <td width="25%">创建时间</td>
                </tr>
            </thead>
            <tbody id="tbodyNews">
            	<c:forEach items="${govInfoList}" var="gi">
				<tr>
					<td><input type="checkbox" id="${gi.giId}" /></td>
					<th>${gi.giTitle}</th>
                    <td>${gi.entryDept}</td>
                    <td><fmt:formatDate value="${gi.entryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!--column01 End-->
</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
</html>
