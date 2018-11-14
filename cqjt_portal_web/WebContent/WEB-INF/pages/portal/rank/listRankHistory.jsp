<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@page 
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
	
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--考核信息</title>
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
                <a href="javascript:;">考核信息</a>
            </div>  
            <%@include file="../index/search.jspf"%>
        </div>
        
    	<!--search-box End-->
        <!--sub Start-->
        <div class="sub l">
           <div class="column">
           	  <div class="title"><h2>快速通道</h2></div>
              <div class="con" style="padding: 30px 75px 10px;">
                  <ul class="clearfix">
				    <li><a id="ty1" href="../guest/rank.xhtml?face=getRankHistory&type=1" ><b class="i-list"></b>委机关</a></li>
				    <li><a id="ty2" href="../guest/rank.xhtml?face=getRankHistory&type=2"><b class="i-list"></b>委属单位</a></li>
				    <li><a id="ty3" href="../guest/rank.xhtml?face=getRankHistory&type=3"><b class="i-list"></b>区县交通局</a></li>
				    <li><a id="ty4" href="../guest/rank.xhtml?face=getRankHistory&type=4"><b class="i-list"></b>市属相关交通企业</a></li>
				</ul>
            </div>
          </div>
        </div>
        <!--sub End-->
        <!--main Start-->
        <div class="main">
        	<div class="column">
                <div class="title"><h2>考核信息</h2></div>
                <div class="con">
                	<input type="hidden" name="type" id="type" value="${type}">
                    <ul class="infoList  clearfix" id="tbody">
						<c:forEach items="${paginations}" var="file">
	                    	<li>
	                            <a href="javascript:;" onclick="downLoad('${file.filePath}','${file.attachment}');return false;" target="_blank"><b class="i-rArr"></b>${file.checkName}</a>
	                            <span class="time">${fn:substring(file.createDate,0,10)}</span>
	                        </li>
	                    </c:forEach>
                  </ul>
                  <div id="filePager" class="page"></div>
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
	var type = $("#type").val();
	$("#ty"+type).attr("style","color:blue");
	
	<c:if test="${!empty paginations[0].totalCount}">
		$("#filePager").buildPager({
			totalLines:${paginations[0].totalCount},
			pageSize:${paginations[0].pageSize},
			startIndex:${paginations[0].pageIndex},
			displayNum:5,
			afterChange:function() {
				searchList();
			}
		});
	</c:if>
});

function searchList() {
	
	var pageIndex = $("#filePager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	var type = $("#type").val();
	
	 $.ajax({
		url : "../guest/rank.xhtml?action=serachRankHistory",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
			"type" : type,
		    "pageIndex" : pageIndex
		},
		success : function(data) {
			var json = $.parseJSON(data);
			var fileList="";
			var createDate="";
			    $.each(json, function(i, n) {
			    	createDate=n.createDate;
			    	createDate=createDate.substring(0,10);
			    	fileList+= "<li> <a href='javascript:;' onclick=\"downLoad('"+
			    			n.filePath+"','"+
			    			n.attachment+"');return false;\" target='_blank'><b class='i-rArr'></b>"+
			    			n.checkName+"</a><span class='time'>"+
			    			createDate+"</span></li>" 
			    });
			  $("#tbody").html("");
			  $("#tbody").html(fileList);
			},
			error :function(data){
				alert("查询数据失败");
			}
		}); 
}
function downLoad(filePath,name){
	var fileName=encodeURI(encodeURI(name));
	
	location.href="../guest/index.xhtml?face=downLoad&fileName="+fileName+"&filePath="+filePath;
}
</script>
</body>
</html>