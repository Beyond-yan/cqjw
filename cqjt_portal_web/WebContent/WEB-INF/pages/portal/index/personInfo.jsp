<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@page 
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
	
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--人事变动</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript"  src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
.sub{width:270px;}
.sub-picNews .con{text-align:center;padding:10px 10px 10px;}
.main{float:left;width:722px;margin:0 0 0 8px;}
.content .leftInfo{height:138px;overflow:hidden;}
.infoList a{width:89%;}
.main .infoList a{max-width:600px;}
.infoList .time{text-align:right;color:#6d6d6d;font-family:Tahoma;}
.afont{width:200px !important;margin-top:5px; }
.time{margin-top:6px;}
#headtab tr td{border-bottom: 1px dotted #ddd !important;padding: 8px;}
.newsDetail-con{margin-top:15px;}
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
                <a href="javascript:;">人事变动</a>
                <span> &gt;</span>
                <a href="javascript:;">变动详情</a>
            </div>  
            <%@include file="../index/search.jspf"%>
        </div>
    	<!--search-box End-->
        <!--sub Start-->
        <div class="sub l">
           <div class="column leftInfo">
           	  <div class="title"><h2>交通运输部领导</h2><a href="person.xhtml?face=PersonView&newsTitle=&entryDateS=&entryDateE=&pageIndex=0&PersonCat=交通运输部领导" class="more">更多></a></div>
              <div class="con">
                   <ul class="infoList  clearfix" id="tbody">
                      <c:forEach items="${textPersonLista}" var="person" >
						<li>
                           <a href="person.xhtml?face=gatPersonDetails&personId=${person.personId}" class="afont"><b class="i-rArr"></b>${person.personTitle}</a>
                       	</li>
					  </c:forEach>
                  </ul>
              </div>
          </div>
          <div class="column leftInfo">
           	  <div class="title"><h2>委系统市管干部</h2><a href="person.xhtml?face=PersonView&newsTitle=&entryDateS=&entryDateE=&pageIndex=0&PersonCat=委系统市管干部" class="more">更多></a></div>
              <div class="con">
                   <ul class="infoList  clearfix" id="tbody">
                      <c:forEach items="${textPersonListb}" var="person" >
						<li>
                           <a href="person.xhtml?face=gatPersonDetails&personId=${person.personId}" class="afont"><b class="i-rArr"></b>${person.personTitle}</a>
                       	</li>
					  </c:forEach>
                  </ul>
              </div>
          </div>
          <div class="column leftInfo">
           	  <div class="title"><h2>委管干部</h2><a href="person.xhtml?face=PersonView&newsTitle=&entryDateS=&entryDateE=&pageIndex=0&PersonCat=委管干部" class="more">更多></a></div>
              <div class="con">
                   <ul class="infoList  clearfix" id="tbody">
                      <c:forEach items="${textPersonListc}" var="person" >
						<li>
                           <a href="person.xhtml?face=gatPersonDetails&personId=${person.personId}" class="afont"><b class="i-rArr"></b>${person.personTitle}</a>
                       	</li>
					  </c:forEach>
                  </ul>
              </div>
          </div>
          <div class="column leftInfo">
           	  <div class="title"><h2>区县交委</h2><a href="person.xhtml?face=PersonView&newsTitle=&entryDateS=&entryDateE=&pageIndex=0&PersonCat=区县交委" class="more">更多></a></div>
              <div class="con">
              	  <ul class="infoList  clearfix" id="tbody">
                      <c:forEach items="${textPersonListd}" var="person" >
						<li>
                           <a href="person.xhtml?face=gatPersonDetails&personId=${person.personId}" class="afont"><b class="i-rArr"></b>${person.personTitle}</a>
                       	</li>
					  </c:forEach>
                  </ul>
              </div>
          </div>
          <div class="column leftInfo">
           	  <div class="title"><h2>其他</h2><a href="person.xhtml?face=PersonView&newsTitle=&entryDateS=&entryDateE=&pageIndex=0&PersonCat=其他" class="more">更多></a></div>
              <div class="con">
              	  <ul class="infoList  clearfix" id="tbody">
                      <c:forEach items="${textPersonListe}" var="person" >
						<li>
                           <a href="person.xhtml?face=gatPersonDetails&personId=${person.personId}" class="afont"><b class="i-rArr"></b>${person.personTitle}</a>
                       	</li>
					  </c:forEach>
                  </ul>
              </div>
          </div>
        </div>
        <!--sub End-->
        <!--main Start-->
		<div class="main">
			<div class="column">
				<div class="con newsDetail" style="min-height: 200px;padding:30px;font-size: 14px;" >
						<table id="headtab" style="width:100%;">
							<tr>
								<td colspan="2"><b>名称：</b>${textPerson.personTitle}</td>
							</tr>
							<tr>
								<td width="50%"><b>文号：</b>${textPerson.textNum}</td>
								<td><b>发布机构：</b>${textPerson.pubDapt}</td>
							</tr>
							<tr>
								<td><b>发文日期：</b><script>document.write("${textPerson.pubDate}".substring(0, 10));</script></td>
								<td><b>主题分类：</b>${textPerson.subjectCat}</td>
							</tr>
						
						</table>
 
						<div class="newsDetail-con">
							<li>${textPerson.personContent}</li>
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
		url : "news.xhtml?action=getPublicView",
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
			    	publicList+= "<li> <a href='news.xhtml?face=newsDetail&newsId="+
	    			n.newsId+"' target='_blank'><b class='i-rArr'></b>"+"【"+
	    			n.categoryName+"】"+n.newsTitle+"</a><span class='time'>"+
	    			createDate+"</span></li>"  
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