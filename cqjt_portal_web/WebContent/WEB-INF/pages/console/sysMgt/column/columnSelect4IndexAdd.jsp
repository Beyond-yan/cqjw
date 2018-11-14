<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>系统管理</title>
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#search {float: left;width:100%;}
#Permission {float: left; margin-left:10px;}
a {color:blue;cursor: pointer;}
.error{color:red;padding-left: 10px;}
</style>
</head>
<body>
<div>
     <div id="search">
    	<form action="column.xhtml?face=getCategoryUnSelectList" method="post" enctype="multipart/form-data">
    	<input type="hidden" value="${id}" name="id"/>
        <div style="padding:8px;">
    		<label>栏目名称：</label><input type="text" name="columnName" class="input-text" value="${columnName}">
    		<label>创建时间：</label>
            <div class="data" id="dataSelect-start">
                <input type="text" name="entryDateS" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
            </div>
            <span>&nbsp;～</span>
            <div class="data" id="dataSelect-end">
            	<input type="text" name="entryDateE" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput" >
            </div>
            <input type="submit" value="搜 索" />
		</div>
		</form>
		<table width="100%" border="1" class="dataList">
			<thead>
				<tr>                    
                    <td width="6%">选择</td>
                    <td width="*">栏目名称</td>
                    <td width="25%">创建时间</td>
                </tr>
            </thead>
            <tbody id="tbodyNews">
            	<c:forEach items="${paginations}" var="column">
				<tr>
					<td><input type="checkbox" id="${column.categoryId}" /></td>
					<th>${column.categoryName}</th>
                    <td>${column.createDate}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
    <!--column01 End-->
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
</html>
