<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%><%@page
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--法律法规详情页</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet"
	type="text/css">
<link href="${StaticResourcePath}/css/news.css" rel="stylesheet"
	type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
.column .con li a {
	max-width: 220px;
}
.newsDetail {
	font: 16px/30px 宋体
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
			<!--search-box End-->
			<!--sub Start-->
			<div class="sub l">
				<div class="column" id="userLogin">
					<div class="title">
						<h2>法律法规</h2>
					</div>
					<div class="con">
						<ul class="infoList">
							<c:forEach items="${textStatuteList}" var="n">
								<li><a
									href="statute.xhtml?face=gatStatuteDetails&statuteId=${n.statuteId}"><b
										class="i-rArr"></b>${n.statuteTitle}</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<!--sub End-->
			<!--main Start-->
			<div class="main">
				<div class="column">
					<div class="con newsDetail" style="min-height: 200px;" >
						<ul class="tip clearfix">
							<li class="al_l">
								<div>法规标题：&nbsp;&nbsp;${textStatute.statuteTitle}</div>
								<hr
									style="height: 1px; border: none; border-top: 1px solid #555555;" />
								<div>发布机关：&nbsp;&nbsp;${textStatute.pubDapt}</div>
								<hr
									style="height: 1px; border: none; border-top: 1px solid #555555;" />
								<div>发布日期：&nbsp;&nbsp;<script>document.write("${textStatute.pubDate}".substring(0, 10));</script></div>
								<hr
									style="height: 1px; border: none; border-top: 1px solid #555555;" />
								<div>实时日期：&nbsp;&nbsp;<script>document.write("${textStatute.inplementDate}".substring(0, 10));</script></div>
								<hr
									style="height: 1px; border: none; border-top: 1px solid #555555;" />
								<c:choose>
									<c:when test="${textStatute.timeliness eq '有效'}">
										<div>
											时 效 性：&nbsp;&nbsp;<span style="color: red;">${textStatute.timeliness}</span>
										</div>
									</c:when>
									<c:otherwise>
										<div>时 效 性：&nbsp;&nbsp;${textStatute.timeliness}</div>
									</c:otherwise>
								</c:choose>
								<hr
									style="height: 1px; border: none; border-top: 1px solid #555555;" />
								<div>题&nbsp;&nbsp;注：&nbsp;&nbsp;${textStatute.caption}</div>
								<hr
									style="height: 1px; border: none; border-top: 1px solid #555555;" />
							</li>
						</ul>
						<ul>
							<div class="newsDetail-con" style="margin-top:80px;" >
								<li>${textStatute.statuteContent}</li>
							</div>
						</ul>
					</div>
				</div>
			</div>
			<!--main End-->
		</div>
		<%@include file="../footer.jspf"%>
	</div>
</body>
</html>