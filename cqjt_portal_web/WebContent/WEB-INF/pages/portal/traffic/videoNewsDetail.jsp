<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><%@page 
	import="com.gdssoft.core.tools.SystemContext"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--交通视频详情页</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/news.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<script  type="text/javascript" src="${StaticResourcePath}/flowplayer-3.2.14/flowplayer-3.2.11.min.js" ></script>
<style type="text/css">
.column .con li a { max-width:220px; }
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
                <a href="javascript:;">交通视频</a>
            </div>
            <%@include file="../index/search.jspf"%>
        </div>
    	<!--search-box End-->
        <!--sub Start-->
        <div class="sub l">
        	<div class="column" id="userLogin">
            	<div class="title">
                	<h2>交通视频</h2>
                </div>
                <div class="con">
                	<ul class="clearfix videoList">
                		<c:forEach items="${videoNewsList}" var="video">
	                		<li>
	                            <a href="news.xhtml?face=videoNewsDetail&videoId=${video.videoId}" class="video-pic">
	                            <div style="width:215px;height:145px;"><img src="${pageContext.request.contextPath}/${video.photoUrl}"></div>
	                            <i class="icon i-video-big"></i>
	                            </a>
	                            <a href="news.xhtml?face=videoNewsDetail&videoId=${video.videoId}" class="video-title clearfix" title="${video.videoTitle}">
	                                <i class="icon i-video"><b></b></i>
	                                <strong>${video.videoTitle}</strong>
	                            </a>
	                            <p class="video-time">${fn:substring(video.createDate,0,10)}</p>
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
                <div class="con">
                	<div class="newsDetail">
	                    <h2>${videoNews.videoTitle}</h2>
	                    <p class="dotLine_2px"></p>
	                    <ul class="tip clearfix">
	                        <li class="al_l">
	                        	发布日期：${fn:substring(videoNews.createDate,0,10)}
	                        </li>
	                        <li class="al_c">发布：${videoNews.entryDeptName}</li>
	                        <li class="share">
	                            <span>来源出处：${videoNews.videoSource}</span>
	                        </li>
	                    </ul> 
                         <div  title="视频播放" style="margin-left: 20px">
                         	<a href="${pageContext.request.contextPath}/${videoNews.videoUrl}"
								style="display: block; width: 600px; height: 450px"
								id="player01"> </a>
							<script type="text/javascript">
								flowplayer("player01", "${pageContext.request.contextPath}${player}",{clip:{scaling:"fit"}});
							</script>
						</div>
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