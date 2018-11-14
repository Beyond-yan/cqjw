<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@page
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>

<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--法律法规</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet"
	type="text/css">
<link href="${StaticResourcePath}/css/index.css" rel="stylesheet"
	type="text/css">
<style type="text/css">
.sub {
	width: 270px;
}

.sub-picNews .con {
	text-align: center;
	padding: 10px 10px 10px;
}

.main {
	float: left;
	width: 722px;
	margin: 0 0 0 8px;
}

.content .column {
	height: 581px;
	overflow: hidden;
}

.infoList a {
	width: 89%;
}

.main .infoList a {
	max-width: 600px;
}

.infoList .time {
	text-align: right;
	color: #6d6d6d;
	font-family: Tahoma;
}
.input-text{
	width:145px;
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
					<b class="icon icon_home"></b> <span>当前位置：</span> <a
						href="index.xhtml">首页</a> <span> &gt;</span> <a
						href="javascript:;">法律法规</a>
				</div>
				<%@include file="../index/search.jspf"%>
			</div>
			<div class="column" id="searchList" style="height: 80px;">
				<div class="title">
					<h2>
						<b class="icon i-search"></b>快速搜索
					</h2>
				</div>
				<div class="con">
					<label id="iii">标题：</label><input style="height:23px" type="text" name="newsTitle" class="input-text inputs" value="${newsTitle}">
        		&nbsp;&nbsp;&nbsp;<label>法规分类：</label>
        		<select class="input-text" id="StatuteCat"  name="SUB_CATEGORY" style="height: 28px;">
        			<option value="">-= 请选择  =-</option>
        			<option value="交通地方性法规库" <c:if test="${statuteCat=='交通地方性法规库'}">selected</c:if>>交通地方性法规库</option>
        			<option value="交通地方政府规章库" <c:if test="${statuteCat=='交通地方政府规章库'}">selected</c:if>>交通地方政府规章库</option>
        			<option value="交通行政规范性文件库" <c:if test="${statuteCat=='交通行政规范性文件库'}">selected</c:if>>交通行政规范性文件库</option>
        			<option value="党内法规库" <c:if test="${statuteCat=='党内法规库'}">selected</c:if>>党内法规库</option>
        		</select>&nbsp;&nbsp;&nbsp;
        		<label>子分类：</label>
        		<select class="input-text" id="Statute" name="SUB_CATEGORY_INFO" style="height: 28px;">
        			<c:choose>
					    <c:when test="${statute == '市政府制发文件'}">
					       <option value='市政府制发文件' selected>市政府制发文件</option><option value='市交委制发文件'>市交委制发文件</option><option value='委属单位制发文件'>委属单位制发文件</option>
					    </c:when>
					    <c:when test="${statute == '市交委制发文件'}">
					       <option value='市政府制发文件' >市政府制发文件</option><option value='市交委制发文件' selected>市交委制发文件</option><option value='委属单位制发文件'>委属单位制发文件</option>
					    </c:when>  
					    <c:when test="${statute == '委属单位制发文件'}">
					       <option value='市政府制发文件' >市政府制发文件</option><option value='市交委制发文件'>市交委制发文件</option><option value='委属单位制发文件' selected>委属单位制发文件</option>
					    </c:when>
					    <c:when test="${statute == '其他相关文件'}">
					       <option value='其他相关文件' selected>其他相关文件</option><option value='市交通党委规范性文件'>市交通党委规范性文件</option><option value='市委党内法规'>市委党内法规</option><option value='中央党内法规'>中央党内法规</option>
					    </c:when>
					     <c:when test="${statute == '市交通党委规范性文件'}">
					       <option value='其他相关文件' >其他相关文件</option><option value='市交通党委规范性文件' selected>市交通党委规范性文件</option><option value='市委党内法规'>市委党内法规</option><option value='中央党内法规'>中央党内法规</option>
					    </c:when>
					    <c:when test="${statute == '市委党内法规'}">
					       <option value='其他相关文件' >其他相关文件</option><option value='市交通党委规范性文件'>市交通党委规范性文件</option><option value='市委党内法规' selected>市委党内法规</option><option value='中央党内法规'>中央党内法规</option>
					    </c:when>
					    <c:when test="${statute == '中央党内法规'}">
					       <option value='其他相关文件' >其他相关文件</option><option value='市交通党委规范性文件'>市交通党委规范性文件</option><option value='市委党内法规'>市委党内法规</option><option value='中央党内法规' selected>中央党内法规</option>
					    </c:when>
					   <c:otherwise>  
					     <option value="">-= 请选择  =-</option>
					   </c:otherwise>
					 </c:choose>
        		</select>  &nbsp;&nbsp;&nbsp;
        		 <a href="javascript:;" onclick="searchList(0,0);return false;"
						class="btnh btn btn-blue">搜 索</a>
				 <a href="javascript:;" onclick="chongzhi();return false;"
						class="btnh btn btn-blue">重置</a>
				</div>
			</div>
			<!--search-box End-->
			<!--sub Start-->
			<div class="sub l">
				<div class="column">
					<div class="title">
						<h2>快速通道</h2>
					</div>
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
						<h2>法律法规</h2>
					</div>
					<div class="con">
						<ul class="infoList  clearfix" id="tbody">
							<c:forEach items="${paginations}" var="statute" >
<%-- 							<c:forEach items="${paginations}" var="statute" --%>
<%-- 								varStatus="status" begin="0" end="${paginations[0].pageSize}"> --%>
								<li><a
									href="statute.xhtml?face=gatStatuteDetails&statuteId=${statute.statuteId}"
									target="_blank"><b class="i-rArr"></b>${statute.statuteTitle}</a>
									<span class="time">${fn:substring(statute.createDate,0,10)}</span>
								</li>
							</c:forEach>
						</ul>
						<div id="textInfoListPager" class="page clearfix"></div>
					</div>
				</div>
			</div>
			<!--main End-->
		</div>
		<!--content End-->
		<%@include file="../footer.jspf"%>
	</div>
	<script type="text/javascript"
		src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
	<script type="text/javascript"
		src="${StaticResourcePath}/js/j.pager.js"></script>
	<script type="text/javascript"
		src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
	<script>	
	
		function chongzhi(){
			$("input[name='newsTitle']").val("")
			$("#StatuteCat").html("<option value=''>-= 请选择  =-</option>"+
					"<option value='交通地方性法规库'>交通地方性法规库</option>"+
			"<option value='交通地方政府规章库' >交通地方政府规章库</option>"+
			"<option value='交通行政规范性文件库' >交通行政规范性文件库</option>"+
			"<option value='党内法规库' >党内法规库</option>");
			$("#Statute").html("<option value=''>-= 请选择  =-</option>");
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
			location.href ="statute.xhtml?face=StatuteView&newsTitle="+newsTitle+"&entryDateS=&entryDateE=&pageIndex="+pageIndex+"&StatuteCat="+$("#StatuteCat").val()+"&Statute="+$("#Statute").val();
  
		}	
		
		$("#StatuteCat").change(function(){
			
			switch ($(this).val()) {
				case '交通地方性法规库':
					$("#Statute").html("<option value=''>-= 请选择  =-</option>");
					break;
				case '交通地方政府规章库':
					$("#Statute").html("<option value=''>-= 请选择  =-</option>");
					break;
				case '交通行政规范性文件库':
					$("#Statute").html("<option value='市政府制发文件'>市政府制发文件</option><option value='市交委制发文件'>市交委制发文件</option><option value='委属单位制发文件'>委属单位制发文件</option>");
					break;
				case '党内法规库':
					$("#Statute").html("<option value='其他相关文件'>其他相关文件</option><option value='市交通党委规范性文件'>市交通党委规范性文件</option><option value='市委党内法规'>市委党内法规</option><option value='中央党内法规'>中央党内法规</option>");
					break;
				default:
					$("#Statute").html("<option value=''>-= 请选择  =-</option>");
					break;
				}	
		})
	</script>
</body>
</html>