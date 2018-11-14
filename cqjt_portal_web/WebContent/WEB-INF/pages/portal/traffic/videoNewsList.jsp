<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@page 
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
	
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--交通视频</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<%-- <link href="${StaticResourcePath}/css/index.css" rel="stylesheet" type="text/css"> --%>
<link href="${StaticResourcePath}/css/news.css" rel="stylesheet" type="text/css">
<script type="text/javascript"  src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
.main{float:left;width:722px;margin:0 0 0 8px;}
.content .column{height:581px;overflow:hidden;}
.sub{width:270px;}
.videoList li{width:220px;}
.video-title{max-width:230px}
/*@:sysEnter*/
.sysEnter{width:185px;margin:0 0;} 
.sysEnter li{float:left;font-size:0;}
.sysEnter a{
	width:248px;height:38px;margin-bottom:12.5px;*margin-bottom:12.8px;
	background:url(../images/bg_sysEnter.png);
	}
.sysEnter a:hover{background:url(../images/bg_sysEnterHover.png);}
.sysEnter .btn-01,.sysEnter .btn-01:hover{background-position:0 0px;}
.sysEnter .btn-02,.sysEnter .btn-02:hover{background-position:0 -46px;}
.sysEnter .btn-03,.sysEnter .btn-03:hover{background-position:0 -92px;}
.sysEnter .btn-04,.sysEnter .btn-04:hover{background-position:0 -138px;}
.sysEnter .btn-05,.sysEnter .btn-05:hover{background-position:0 -184px;}
.sysEnter .btn-06,.sysEnter .btn-06:hover{background-position:0 -230px;}
.sysEnter .btn-07,.sysEnter .btn-07:hover{background-position:0 -276px;}
.sysEnter .btn-08,.sysEnter .btn-08:hover{background-position:0 -322px;}
.sysEnter .btn-09,.sysEnter .btn-09:hover{background-position:0 -368px;}
.sysEnter .btn-10,.sysEnter .btn-10:hover{background-position:0 -414px;}
.sysEnter .btn-11,.sysEnter .btn-11:hover{background-position:0 -460px;}
.sysEnter .btn-12,.sysEnter .btn-12:hover{background-position:0 -506px;}
body,h1,h2,h3,h4,h5,h6,hr,p,dl,dt,dd,ul,ol,li,button,input,textarea,th,td,a,span,i{margin:0;padding:0;}
button,input,select,textarea{font:12px/1.5 Tahoma,\5b8b\4f53;color:#424242;}
</style>
</head>

<body>
<div class="wrap">
<%@include file="../head.jspf"%>
 <!--content Start-->
    <div class="content clearfix">
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
        	<div class="column">
                <div class="title"><h2>交通视频</h2></div>
                <div class="con">
                    <ul class="videoList  clearfix" id="tbody">
						<c:forEach items="${paginations}" var="video">
	                    	 <li>
	                            <a href="news.xhtml?face=videoNewsDetail&videoId=${video.videoId}" class="video-pic">
		                            <div style="width:210px;height:163px;"><img src="${pageContext.request.contextPath}/${video.photoUrl}"></div>
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
                  <div id="publicPager" class="page"></div>
                </div>
            </div>
        </div>
        <!--main End-->
    </div>
    <!--content End-->
    <%@include file="../footer.jspf"%>
</div>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"  src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript">
$(function(){
	$("#publicPager").buildPager({
		totalLines:${paginations[0].totalCount},
		pageSize:${paginations[0].pageSize},
		startIndex:${paginations[0].pageIndex},
		displayNum:5,
		afterChange:function() {
			searchList();
		}
	});
});

function searchList() {
	var pageIndex = $("#publicPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	$.ajax({
		url : "news.xhtml?action=getVideoView",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
		    "pageIndex" : pageIndex
		},
		success : function(data) {
			var json = $.parseJSON(data);
			var publicList="";
			var createDate="";
			    $.each(json, function(i, n) {
			    	createDate=n.createDate;
			    	createDate=createDate.substring(0,10);
			    	publicList+= "<li> <a href='news.xhtml?face=videoNewsDetail&videoId="+
	    			n.videoId+"' target='_blank' class='video-pic'><div style='width:210px;height:163px;'><img src='${pageContext.request.contextPath}/"+
	    			n.photoUrl+"'></div><i class='icon i-video-big'></i></a><a href='news.xhtml?face=videoNewsDetail&videoId="+
	    			n.videoId+"' class='video-title clearfix'><i class='icon i-video'><b></b></i><strong>"+
	    			n.videoTitle+"</strong></a><p class='video-time'>"+
	    			createDate+"</p></li>"
			    });
			    
			  $("#tbody").html("");
			  $("#tbody").html(publicList);
			},
			error :function(data){
				alert("查询数据失败");
			}
		}); 
}
</script>
</body>
</html>