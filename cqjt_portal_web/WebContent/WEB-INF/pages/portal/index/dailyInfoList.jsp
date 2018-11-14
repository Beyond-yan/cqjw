<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><%@page 
	import="com.gdssoft.core.tools.SystemContext"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--每日信息</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/index.css" rel="stylesheet" type="text/css">
<style type="text/css">
.icon_home {margin:6px 0 8px 8px}
.column .con {padding:10px;}
.sub{width:270px;}
.main{float:left;width:722px;margin:0 0 0 8px;}
.content .column{height:587px;overflow:hidden;}
.infoList a{width:89%;}
.infoList .time{width:8%;text-align:right;color:#6d6d6d;font-family:Tahoma;}
.main .infoList a {max-width: 620px;}
</style>
</head>
<body>
<div class="wrap">
	<%@include file="../head.jspf"%>
    
    <!--content Start-->
    <div class="content clearfix">
    	<!--search-box Start-->
    	<div class="search-box">
        	<!-- Before logging-->
            <div class="breadCutNav">
                <b class="icon icon_home"></b>
                <span>当前位置：</span>
                <a href="index.xhtml">首页</a>
                <span> &gt;</span>
                <a href="javascript:;">每日信息</a>
            </div>
            <!-- Before logging-->
            <%@include file="search.jspf"%>
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
                <div class="title"><h2>每日信息</h2></div>
                <div class="con">
                    <ul id="ul_pubs" class="infoList clearfix">
                    	<c:forEach items="${textPublications}" var="pub">
                    	<li>
                            <a href="news.xhtml?face=dailyInfoDetail&pubId=${pub.pubId}"><b class="i-rArr"></b>${pub.pubName}</a>
                        </li>
                    	</c:forEach>
                  </ul>
                  <div id="pubListPager" class="page clearfix"></div>
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
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript">
$(function(){
	$("#pubListPager").buildPager({
		totalLines:${textPublications[0].totalCount},
		pageSize:${textPublications[0].pageSize},
		startIndex:${textPublications[0].pageIndex},
		displayNum:5,
		afterChange:function() {
			changePageIndex();
		}
	});
});
	
	function changePageIndex() {
		$.ajax({
			url : "news.xhtml?action=queryDailyInfo",
			cache : false,
			type : "post",
			dataType : "text",
			data : {
			    "pageIndex" : $("#pubListPager").attr("curPage")
			},
			success : function(data) {
				var li="";
				var json = $.parseJSON(data);
				$.each(json, function(i, n) {
					 li += "<li>"
							+ "<a href=\"news.xhtml?face=dailyInfoDetail&pubId="+n.pubId+"\">"
							+ "<b class=\"i-rArr\"></b>" + n.pubName
							+ "</a></li>";
					
				});
				$("#ul_pubs").html("");
				$("#ul_pubs").html(li);
			}
	})
  }
</script>
</body>
</html>