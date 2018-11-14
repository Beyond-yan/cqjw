<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@page 
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
	
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--交通政务</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/index.css" rel="stylesheet" type="text/css">
<%-- <link href="${StaticResourcePath}/css/news.css" rel="stylesheet" type="text/css"> --%>
<script type="text/javascript"  src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
.sub{width:270px;}
.r{width:221px;}
.pic img{width:201px;height: 145px;}
.sub-picNews .con{text-align:center;padding:10px 10px 10px;}
.main{float:left;width:492px;margin:0 8px;}
.content .column{height:581px;overflow:hidden;}
.infoList a{width:89%;}
.infoList .time{text-align:right;color:#6d6d6d;font-family:Tahoma;}
    .active{
        cursor: default !important;
    color: black;
    }
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
                <a href="javascript:;">交通政务</a>
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
                <div class="title">
                    <c:forEach items="${titles}" var="title">
                        <c:if test="${title eq cur}">
                            <h2 style="float:left;cursor: pointer" class="title1 active">${title}</h2>
                        </c:if>
                        <c:if test="${title ne cur}">
                            <h2 style="float:left;cursor: pointer" class="title1">${title}</h2>
                        </c:if>
                    </c:forEach>

                </div>
                <div class="con">
                    <ul class="infoList  clearfix" id="tbody">
						<c:forEach items="${paginations}" var="traffic" begin="0" end="0">
	                    	<li>
	                            <a href="news.xhtml?face=newsDetail&newsId=${traffic.newsId}" target="_blank"><b class="i-rArr"></b><b>${traffic.newsTitle}</b></a>
	                            <span class="time">${fn:substring(traffic.createDate,0,10)}</span>
	                        </li>
	                    </c:forEach>
	                    <c:forEach items="${paginations}" var="traffic" varStatus="status" begin="1" end="${paginations[0].pageSize}">
	                    	<li>
	                            <a href="news.xhtml?face=newsDetail&newsId=${traffic.newsId}" target="_blank"><b class="i-rArr"></b>${traffic.newsTitle}</a>
	                            <span class="time">${fn:substring(traffic.createDate,0,10)}</span>
	                        </li>
	                    </c:forEach>
                  </ul>
                  <div id="trafficPager" class="page"></div>
                </div>
            </div>
        </div>
        <!--main End-->
        <!--sub Start-->
        <div class="sub r">
            <div class="column sub-picNews">
                <div class="title"><h2>图片新闻</h2></div>
                <div class="con">
                	<c:forEach items="${trafficPhotoNews}" var="t">
	                	<a href="news.xhtml?face=newsDetail&newsId=${t.newsId}" class="pic" >
	                		<div style="width:201px;height:150px">
	                    		<img src="${pageContext.request.contextPath}/${t.photoUrl}">
	                    	</div>
	                    	<span>${t.newsTitle}</span>
	                    </a>
                	</c:forEach>
                </div>
           </div>
        </div>
        <!--sub End-->
    </div>
    <!--content End-->
    <%@include file="../footer.jspf"%>
</div>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"  src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript">
    var named;
$(function(){

    $(".title1").click(function(){

        named=$(this).text();
        $(this).siblings(".title1").removeClass("active");
        $(this).addClass("active");
        searchList($(this).text(),-1);
    });

	$("#trafficPager").buildPager({
		totalLines:${paginations[0].totalCount},
		pageSize:${paginations[0].pageSize},
		startIndex:${paginations[0].pageIndex},
		displayNum:5,
		afterChange:function() {
			searchList(${cur},null);
		}
	});

});

function searchList(name,index) {
	var pageIndex = $("#trafficPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
    if (typeof name == "undefined") {
        name=named;
    }
	$.ajax({
		url : "news.xhtml?action=getView",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
		    "pageIndex" : pageIndex,
            "name" : name
		},
		success : function(data) {
			var json = $.parseJSON(data);
			var trafficList="";
			var createDate="";
			var categoryName="";
			    $.each(json, function(i, n) {
			    	createDate=n.createDate;
			    	createDate=createDate.substring(0,10);
			    	categoryName=n.categoryName;
			    	if(categoryName==""||categoryName==null){
			    		categoryName="";
			    	}
			    	if(n.rowNo==1){
			    		trafficList+= "<li> <a href='news.xhtml?face=newsDetail&newsId="+
		    			n.newsId+"' target='_blank'><b class='i-rArr'></b><b>"+n.newsTitle+"</b></a><span class='time'>"+
		    			createDate+"</span></li>" 
			    	}else{
			    		trafficList+= "<li> <a href='news.xhtml?face=newsDetail&newsId="+
		    			n.newsId+"' target='_blank'><b class='i-rArr'></b>"+n.newsTitle+"</a><span class='time'>"+
		    			createDate+"</span></li>" 
			    	}
			    	
			    });
			  $("#tbody").html("");
			  $("#tbody").html(trafficList);
			  if(index==-1){
                  $("#trafficPager").html("");
			      if(json.length==0){
                      $.pager($("#trafficPager"),{
                          totalLines:0,
                          pageSize:15,
                          startIndex:0,
                          displayNum:5,
                          afterChange:function() {
                              searchList(${cur},null);
                          }
                      });
                  }else{
                      $.pager($("#trafficPager"),{
                          totalLines:json[0].totalCount,
                          pageSize:json[0].pageSize,
                          startIndex:0,
                          displayNum:5,
                          afterChange:function() {
                              searchList(${cur},null);
                          }
                      });
                  }
              }
			},
			error :function(data){
				alert("查询数据失败");
			}
		});
}
</script>
</body>
</html>