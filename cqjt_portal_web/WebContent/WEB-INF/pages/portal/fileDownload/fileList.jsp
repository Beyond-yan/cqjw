<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@page 
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
	
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--交通课堂</title>
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
                <a href="javascript:;">交通课堂</a>
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
                	<div style="float: left;">
                		<h2>交通课堂  </h2>
					</div>
					<div style="float: right;">
                			<input type="text" style="height: 21px" id="fileName" title="仅针对交通课堂栏目查询" value=""/>&nbsp;&nbsp;&nbsp;&nbsp;
                			<a href="javascript:searchList();" style="color:#0F5DC5">搜索</a>&nbsp;&nbsp;&nbsp;&nbsp;
                	</div>
                </div>
                <div class="con">
                    <ul class="infoList  clearfix" id="tbody">
						<c:forEach items="${paginations}" var="file">
	                    	<li>
	                            <a href="javascript:;" onclick="downLoad('${file.filePath}','${file.attachment}');return false;" target="_blank"><b class="i-rArr"></b>${file.fileName}</a>
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
	$("#filePager").buildPager({
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
	var pageIndex = $("#filePager").attr("curPage");
	var fileName = $("#fileName").val();
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}

	 $.ajax({
		url : "news.xhtml?action=getfileView",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
		    "pageIndex" : pageIndex,
		    "fileName":fileName
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
			    			n.fileName+"</a><span class='time'>"+
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