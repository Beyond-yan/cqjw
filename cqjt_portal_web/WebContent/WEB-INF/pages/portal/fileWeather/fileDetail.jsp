<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><%@page 
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--水位和气象详情页</title>
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
                <a href="javascript:;">水位和气象</a>
            </div>
            <%@include file="../index/search.jspf"%>
        </div>
    	<!--search-box End-->
        <!--sub Start-->
        <div class="sub l">
        	<div class="column" id="userLogin">
            	<div class="title">
                	<h2>水位和气象</h2>
                </div>
                <div class="con">
                	<ul class="infoList">
                		<c:forEach items="${weatherList}" var="n">
                    	<li>
                            <a href="news.xhtml?face=fileWeatherDetails&fileId=${n.fileId}&fileType=${n.fileType}"><b class="i-rArr"></b>${n.fileName}</a>
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
                    <h2>${fileDownload.fileName}</h2>
                    <p class="dotLine_2px"></p>
                    <ul class="tip clearfix">
                        <li class="al_l">
                        	发布日期：
                        	<fmt:parseDate value="${fileDownload.createDate}" var="date"/>
							<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>
                        </li>
                        <li class="al_c">发布：${fileDownload.createBy}</li>
                    </ul>
                    <div class="newsDetail-con">
                    	${fileDownload.fileDesc}
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

</script>
</body>
</html>