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
#search {float: left;width:40%;}
#Permission {float: left; margin-left:10px;}
a {color:blue;cursor: pointer;}
</style>
<title>后台管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		系统管理
        <span>-></span>
		外网栏目与内外栏目对应关系管理
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <input type="hidden" name="neiwangId" value="${categoryId}"/>
        <div class="con">
        <!-- 	<div style="margin-bottom: 10px;"> -->
        		<label style="padding-left: 30px;">内外栏目：</label><input style="margin-right: 30px;" type="text" name="categoryName" class="input-text" value="${categoryName}">
                <a href="javascript:;" onclick="searchList()" class="btn btn-blue">搜  索</a>
                
        </div>
     </div>
     <!--column01 End-->
     <!--column02 Start-->
     <div class="column"  id="search">
          <div class="title"><h2><b class="icon i-search"></b>内网栏目列表</h2></div>
            <div class="con"  style="height:460px; overflow:auto;" >
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>                    
                    <td width="5%">序号</td>
                    <td width="20%">内网栏目</td>
                  </tr>
                </thead>
		               <tbody id="tbodyRelation">
		               <c:forEach items="${textCategoryList}" var="t" varStatus="status">
						<tr>
							   <td>${status.index + 1}</td>
		                       <td><a onclick="showCategoryDetail(${t.categoryId});return false;" id="dept${t.categoryId}">${t.categoryName}</a></td>
						</tr>
					</c:forEach>
				    </tbody>
			</table>
        </div>
       
    </div>
    <!--column02 End-->
    <div class="column" id="Permission">
        <div class="title">
	        <h2><b class="icon i-search"></b>对应外网栏目
		        <span id="add" style="display:none;">
		        	<a href="javascript:;" onclick="addCategory()" class="btn btn-blue" style="float: right;margin-top: 5px;">新  增</a>
		        </span>
	        </h2>
        	<!-- <a href="sysMgt.xhtml?face=addCategoryRelationView" class="btn btn-blue" style="float: right;margin-top: 5px;" >新  增</a></span></h2> -->
        </div>
        <div class="con" style="height:460px"> 
	    	<iframe name="categoryRelationFrame" id="categoryRelationFrame" width="100%" height="100%"
	    		src="" frameborder="0" scrolling="auto">
	    	</iframe>
    	</div>
    </div>
    
</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function() {
	var minHeight = $("body").height()-170;
	if ($("#search .con").height() > minHeight) {
		$("#Permission .con").height($("#search .con").height());
	} else {
		$("#search .con").height(minHeight)
		$("#Permission .con").height(minHeight);
	}
	$("#Permission").width($("body").width()-$("#search").width()-15);
	var Id = $("input[name='neiwangId']").val();
	showCategoryDetail(Id);
});

function showCategoryDetail(categoryId){
	//更改点击 <a>的颜色
	$("#add").show();
	$("#add").val(categoryId);
	$("a[id^='dept']").attr("style","color:blue");
	$("#dept"+categoryId).attr("style","color:red");
	$("#categoryRelationFrame").attr(
			"src",
			"sysMgt.xhtml?face=queryCategoryRelation&categoryId="
					+ categoryId);
}

function searchList() {
	var categoryName = encodeURI(encodeURI($("input[name='categoryName']").val()));
	location.href ="sysMgt.xhtml?face=queryCategory&categoryName="+categoryName;
}

function addCategory() {
	var categoryId=$("#add").val();
	location.href ="sysMgt.xhtml?face=addCategoryRelationView&categoryId="+categoryId;
}

</script>
</html>
