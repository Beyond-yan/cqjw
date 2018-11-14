<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><%@page 
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--新闻详情页</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/news.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.column .con li a { max-width:220px; }
.newsDetail-con img{width:400px;height:250px}
.newsDetail{font: 16px/30px 宋体}
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
                <a href="javascript:;">交通政务</a>
            </div>
            <%@include file="../index/search.jspf"%>
        </div>
    	<!--search-box End-->
        <!--sub Start-->
        <div class="sub l">
        	<div class="column" id="userLogin">
            	<div class="title">
                	<h2>交通政务</h2>
                </div>
                <div class="con">
                	<ul class="infoList">
                		<c:forEach items="${textNewsList}" var="n">
                    	<li>
                            <a href="news.xhtml?face=newsDetail&newsId=${n.newsId}"><b class="i-rArr"></b>${n.newsTitle}</a>
                        </li>
                    	</c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <!--sub End-->
        <!--main Start-->
        <div class="main">
        	<div class="column" >
                <!--<div class="title"><h2>每日信息</h2></div>-->
                <div class="con newsDetail">
                    <h2>${empty textNews.subNewsTitle ? textNews.newsTitle : textNews.subNewsTitle }</h2>
                    <p class="dotLine_2px"></p>
	                    <ul class="tip clearfix" >
	                        <li class="al_l">
	                        	发布日期：<fmt:formatDate value="${textNews.entryDate}" type="date" dateStyle="long"/>
	                        </li>
	                        <span class="share" >发布：${textNews.deptName}</span>
	                       <%--  <li class="share">
	                            <span>所属栏目：${textNews.categoryName}</span>
	                        </li> --%>
	                    </ul>
                    <ul>
                    <div class="newsDetail-con">
                    	<%-- <c:if test="${textNews.isPhotosShow=='1'}">
                    		<img src="${pageContext.request.contextPath}/${textNews.photoUrl}">
                    	</c:if> --%>
                    	<li>
                    	${textNews.newsContent}
                    	</li>
					</div>
					</ul>
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

</script>
</body>
</html>