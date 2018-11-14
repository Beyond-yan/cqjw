<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@page 
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
	
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--水位和气象</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/index.css" rel="stylesheet" type="text/css">
<%-- <link href="${StaticResourcePath}/css/news.css" rel="stylesheet" type="text/css"> --%>
<script type="text/javascript"  src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
.sub{width:270px;}
.sub-picNews .con{text-align:center;padding:10px 10px 10px;}
.main{float:left;width:722px;margin:0 0 0 8px;}
.content .column{height:581px;overflow:hidden;}
.infoList a{width:89%;}
.main .infoList a{max-width:600px;}
.infoList .time{text-align:right;color:#6d6d6d;font-family:Tahoma;}
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
                <a href="javascript:;">水位和气象</a>
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
        		<!-- 
                <div class="title"><h2>水位和气象</h2></div>
                <div class="con">
                    <ul class="infoList  clearfix" id="tbody">
						<c:forEach items="${paginations}" var="file">
	                    	<li>
	                            <a href="news.xhtml?face=fileWeatherDetails&fileId=${file.fileId}" ><b class="i-rArr"></b>${file.fileName}</a>
	                            <span class="time">${fn:substring(file.createDate,0,10)}</span>
	                        </li>
	                    </c:forEach>
                  </ul>
                  <div id="filePager" class="page"></div>
                </div>
                 -->
                 
                 <div class="column" id="sign3">
	                <div class="title">
	                    <ul class="tab">              
	                        <li class="active" id="li_waterLevel">水位</li>  
	                        <li id="li_weather">气象</li>
	                     </ul>
	                </div>
	                <div class="con clearfix" style="height:130px;">
	                    <ul class="tabCon infoList clearfix"  id="waterLevel">
	                    	<c:forEach items="${paginations}" var="file">
		                    	<li>
		                            <a href="news.xhtml?face=fileWeatherDetails&fileId=${file.fileId}&fileType=0" ><b class="i-rArr"></b>${file.fileName}</a>
		                            <span class="time">${fn:substring(file.createDate,0,10)}</span>
		                        </li>
		                    </c:forEach>
	                    </ul>
	                    <ul class="tabCon infoList clearfix" style="display:none;" id="weather">
	                    	
	                    </ul>
		                     <!-- <div id="filePager" class="page"></div> -->
								<div id="filePager" class="page"></div>
	                </div>
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

var totalCount = ${paginations[0].totalCount};
var pageSize = ${paginations[0].pageSize};
var pageIndex = ${paginations[0].pageIndex};

$(function(){	
	//alert("totalCount--->"+totalCount+",pageSize--->:"+pageSize+",pageIndex-->"+pageIndex);
	loadPage();
	$("#sign3 .tab li").click(function(){
		toggleEvent(this,"#sign3 .tab li","active","","#sign3 .tabCon",1);
		if(tabType == 0){
			loadWeather('waterLevel',0);
		} else {
			loadWeather('weather',1);
		}
		loadPage();
	});
	
	
});

//加载气象信息
function loadWeather(ulId,fileType){
	var pageIndex = $("#filePager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	showLoading(ulId);
	$.ajax({
		url : "news.xhtml?face=getWeatherList&fileType="+fileType+"&pageIndex="+pageIndex,
		cache : false,
		dataType : "text",
		async: false,
		success : function(data) {
			var json = $.parseJSON(data);
			var count=0;
			$("#"+ulId).html("");
			$.each(json, function(i, n) {
					var createDate=n.createDate;
		    		createDate=createDate.substring(0,10);
		    		if(count == 0){
		    			totalCount = n.totalCount;
		    			pageSize = n.pageSize;
		    			pageIndex = n.pageIndex;
		    		}
					var li = $(
							"<li>"+
							"<a href=\"news.xhtml?face=fileWeatherDetails&fileId="+n.fileId+"&fileType="+fileType+"\"><b class=\"i-rArr\"></b>"+n.fileName+"</a>"+
                            "<span class=\"time\">"+createDate+"</span>"+
                            "</li>");
					$("#"+ulId).append(li);
					count ++;
			});
		},
		error :function(data){
			alert("查询数据失败");
		}
	});
}

function showLoading(id){
	$("#"+id).html("");
	var li = $("<li style=\"text-align:center;padding-top:50px;\">"+
						"<img src=\"${StaticResourcePath}/images/loading.gif\" />"+
						"</li>");
	$("#"+id).append(li);
}

function loadPage(){
	//alert("totalCount--->"+totalCount+",pageSize--->:"+pageSize+",pageIndex-->"+pageIndex);
	$("#filePager").buildPager({
		totalLines:totalCount,
		pageSize:pageSize,
		startIndex:pageIndex,
		displayNum:5,
		afterChange:function() {
			//searchList();
			if(tabType == 0){
				loadWeather('waterLevel',0);
			} else {
				loadWeather('weather',1);
			}
		}
	});
}

</script>
</body>
</html>