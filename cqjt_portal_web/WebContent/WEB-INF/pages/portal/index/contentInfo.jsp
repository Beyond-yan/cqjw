<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><%@page 
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--详情页</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">


<link href="${StaticResourcePath}/css/index.css" rel="stylesheet" type="text/css">

<style type="text/css">
.sub{width:270px;}
.sub-picNews .con{text-align:center;padding:10px 10px 10px;}
.main{float:left;width:722px;margin:0 0 0 8px;}
.content .column{overflow:hidden;padding-bottom: 20px;}
.infoList a{width:89%;}
.main .infoList a{max-width:600px;}
.newsDetail{font: 16px/30px 宋体}
.infoList .time{text-align:right;color:#6d6d6d;font-family:Tahoma;}
.dotLine_2px{
	border-top:2px dashed #666;
	margin:13px auto;
}
</style>
</head>

<body>
<div class="wrap">
	<%@include file="../head.jspf"%>
    
    <!--content Start-->
    <div class="content clearfix" >
    	<!--search-box Start-->
    	<div class="search-box">
            <div class="breadCutNav">
                <b class="icon icon_home"></b>
                <span>当前位置：</span>
                <a href="index.xhtml">首页</a>
                <span> &gt;</span>
                <a href="javascript:;">详情页</a>
            </div>
            <%@include file="../index/search.jspf"%>
        </div>
    	<!--search-box End-->
        <!--sub Start-->
        <div class="sub l">
			<div class="column">
           	  <div class="title"><h2>快速通道</h2></div>
              <div class="con">
                  <%@include file="../index/sysLink.jspf"%>
              </div>
          </div>
        </div>
        <!--sub End-->
        <!--main Start-->
        <div class="main">
        	<div class="column" >
                
                <div class="con newsDetail">
                    <h2 style="text-align:center;margin-top:25px">${textNews.newsTitle}</h2>
                    <p class="dotLine_2px"></p>
                    <ul class="tip clearfix">
                        <li class="al_l">
                        	发布日期：<fmt:formatDate value="${textNews.entryDate}" type="date" dateStyle="long"/>
                        	 <span >发布：${textNews.deptName}</span>
                        </li>
                       
                        <%-- <li class="share">
                            <span>所属栏目：${textNews.categoryName}</span>
                        </li> --%>
                    </ul>
                    <div class="newsDetail-con">
                    	<%-- <c:if test="${textNews.isPhotosShow=='1'}">
                    		<img src="${pageContext.request.contextPath}/${textNews.photoUrl}">
                    	</c:if> --%>
                    	${textNews.newsContent}
					</div>
    			</div>
            </div>
        </div>
        <!--main End-->
    </div>
    
    <%@include file="../footer.jspf"%>
</div>
<script type="text/javascript"  src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"  src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript">

$(function(){
	var tags = "${flagType}";
	var types = "";
	if(tags=='cs'){
		types="处室动态";
	}
	if(tags=='hy'){
		types="行业动态";
	}
	if(tags=='qx'){
		types="区县动态";
	}
	
	$("#titleTag1").html(types);
	$("#titleTag2").html(types);
});
	

</script>
</body>
</html>