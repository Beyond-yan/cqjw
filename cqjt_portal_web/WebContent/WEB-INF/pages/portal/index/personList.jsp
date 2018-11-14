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
            </div>  
            <%@include file="../index/search.jspf"%>
        </div>
        
    	<!--search-box End-->
			<div class="column" id="searchList" style="height: 80px;">
				<div class="title">
					<h2>
						<b class="icon i-search"></b>快速搜索
					</h2>
				</div>
				<div class="con">
					<label id="iii">标题：</label><input style="height:23px" type="text" name="newsTitle" class="input-text inputs" value="${newsTitle}">
        		&nbsp;&nbsp;&nbsp;<label>任免分类：</label>
		        		<select class="input-text" id="personCat"  name="SUB_CATEGORY" style="height: 28px;">
		        			<option value="">-= 请选择  =-</option>
		        			<option value="交通运输部领导" <c:if test="${PersonCat=='交通运输部领导'}">selected</c:if>>交通运输部领导</option>
		        			<option value="委系统市管干部" <c:if test="${PersonCat=='委系统市管干部'}">selected</c:if>>委系统市管干部</option>
		        			<option value="委管干部" <c:if test="${PersonCat=='委管干部'}">selected</c:if>>委管干部</option>
		        			<option value="区县交委" <c:if test="${PersonCat=='区县交委'}">selected</c:if>>区县交委</option>
		        			<option value="其他" <c:if test="${PersonCat=='其他'}">selected</c:if>>其他</option>
		        		</select>   &nbsp;&nbsp;&nbsp;
        		 <a href="javascript:;" onclick="searchList(0,0);return false;"
						class="btnh btn btn-blue">搜 索</a>
				 <a href="javascript:;" onclick="chongzhi();return false;"
						class="btnh btn btn-blue">重置</a>
				</div>
			</div>
			
			
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
			
			
		<div class="main">
        	<div id="perInfo" class="column">
                <div class="title"><h2>人事变动</h2></div>
                <div class="con">
					<ul class="infoList  clearfix" id="tbody">
						<c:forEach items="${paginations}" var="person" >
							<li><a href="person.xhtml?face=gatPersonDetails&personId=${person.personId}"
								target="_blank"><b class="i-rArr"></b>${person.personTitle}</a>
								<span class="time"><script>document.write("${person.pubDate}".substring(0, 10));</script></span>
							</li>
						</c:forEach>
					</ul>
                  <div id="textInfoListPager" class="page clearfix"></div>
                </div>
            </div>
         </div>
         
         
    </div>
    <!--content End-->
    <%@include file="../footer.jspf"%>
</div>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"  src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript">
function chongzhi(){
	$("input[name='newsTitle']").val("")
	$("#personCat").html("<option value=''>-= 请选择  =-</option>"+
	"<option value='交通运输部领导'>交通运输部领导</option>"+
	"<option value='委系统市管干部'>委系统市管干部</option>"+
	"<option value='委管干部'>委管干部</option>"+
	"<option value='区县交委'>区县交委</option>");
	searchList(0,0);
}



$(function(){
	<c:if test="${!empty paginations[0].totalCount}">
	$("#textInfoListPager").buildPager({
		totalLines:${paginations[0].totalCount},
		pageSize:${paginations[0].pageSize},
		startIndex:${paginations[0].pageIndex},
		displayNum:5,
		afterChange:function() {
			searchList(${paginations[0].pageIndex},1);
		}
	});
	</c:if>
});

function searchList(num,index) {
	var newsTitle = encodeURI(encodeURI($("input[name='newsTitle']").val()));
	newsTitle = newsTitle.replace(/#/g,"%23");
	var pageIndex = num;
	if(index == 1){
		var pageIndex = $("#textInfoListPager").attr("curPage");
	}
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="person.xhtml?face=PersonView&newsTitle="+newsTitle+"&entryDateS=&entryDateE=&pageIndex="+pageIndex+"&PersonCat="+$("#personCat").val();
}	


 
</script>
</body>
</html>