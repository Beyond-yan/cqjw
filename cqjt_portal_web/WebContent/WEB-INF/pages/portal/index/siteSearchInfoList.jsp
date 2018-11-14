<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><%@page 
	import="com.gdssoft.core.tools.SystemContext"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--网站搜索</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/index.css" rel="stylesheet" type="text/css">
<style type="text/css">
.icon_home {margin:6px 0 8px 8px}
.column .con {padding:10px;}
.sub{width:270px;}
.main{float:left;width:722px;margin:0 0 0 8px;}
.content .column{height:589px;overflow:hidden;}
.infoList a{width:89%;}
.infoList .time{width:8%;text-align:right;color:#6d6d6d;font-family:Tahoma;}
.main .infoList a {max-width: 620px;}
</style>
</head>
<body>
<div class="wrap">
	<%@include file="../header.jspf"%>
    
    <!--content Start-->
    <div class="content clearfix">
    	<!--search-box Start-->
    	<div class="search-box">
        	<!-- Before logging-->
            <div class="breadCutNav">
                <b class="icon icon_home"></b>
                <span>当前位置：</span>
                <a href="../guest/index.xhtml">首页</a>
                <span> &gt;</span>
                <a href="javascript:;">网站搜索结果</a>
            </div>
            <!-- Before logging-->
         <!--  -->   <div class="search">
                <a href="javascript:;" title="搜索" class="btn" onclick="searchInfo(1);"></a>
                <div class="search-input">
                	<input type="text" name="keyStr" value="${keyStr}">
                </div>
                <span>网站搜索</span>
            </div>
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
                <div class="title"><h2>搜索结果</h2></div>
                <div class="con">
                    <ul id="ul_pubs" class="infoList clearfix">
                    	<c:forEach items="${searchResult}" var="result">
                    	<li>
                          <c:if test='${result.news_flag=="1"}'>
                    	     <a href="../guest/news.xhtml?face=newsDetail&newsId=${result.news_id}"><b class="i-rArr"></b>${result.news_title}</a>
                    	  </c:if>
                    	   <c:if test='${result.news_flag=="2"}'>
                    	     <a href="../guest/news.xhtml?face=govInfoDetail&giId=${result.news_id}"><b class="i-rArr"></b>${result.news_title}</a>
                    	  </c:if>
                    	   <c:if test='${result.news_flag=="3"}'>
                    	     <a href="../guest/news.xhtml?face=videoNewsDetail&videoId=${result.news_id}"><b class="i-rArr"></b>${result.news_title}</a>
                    	  </c:if>
                    	   <c:if test='${result.news_flag=="4"}'>
                    	     <a href="../guest/news.xhtml?face=dailyInfoDetail&pubId=${result.news_id}"><b class="i-rArr"></b>${result.news_title}</a>
                    	  </c:if>
                        </li>
                    	</c:forEach>
                  </ul>
                  <div id="searchListPager" class="page"></div>
                </div>
            </div>
        </div>
        <!--main End-->
    </div>
    <!--content End-->
    <%@include file="../footer.jspf"%>
</div>

<script type="text/javascript"  src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"  src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript"  src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript">
$(function(){
	<c:if test="${!empty searchResult[0].totals}">
	$("#searchListPager").buildPager({
		totalLines:${searchResult[0].totals},
		pageSize:${searchResult[0].pageSize},
		startIndex:${searchResult[0].pageIndex},
		displayNum:5,
		afterChange:function() {
			searchInfo(0);
		}
	});
	</c:if>
});

function searchInfo(flag){
    var key = $("input[name='keyStr']").val();
    var keyStr = " "+encodeURI(encodeURI(key));
	var pageIndex = $("#searchListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	if(flag==1){
		pageIndex = 0;
	}
	document.location.href="index.xhtml?face=siteSearchInfoView&keyStr="+keyStr+"&pageIndex="+pageIndex;
}


</script>
</body>
</html>