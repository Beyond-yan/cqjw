<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@page 
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--每日信息</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<style type="text/css">
.icon_home {margin:6px 0 8px 8px}
.info-title {
    border-bottom: 1px dotted #828080;
    color: #000000;
    font: 24px/70px Microsoft YaHei;
    text-align: center;
}
.info-date{color:#888;text-align:center;line-height:30px;}
.info-con{line-height:26px;margin:0 10px 10px;}
.info-con h3{color:#084e8f;text-align:left;margin:5px 0;}
.info-con h4{text-indent:5px;}
.info-con ul{color:#777;padding-left:25px;}
.info-con dl{padding-left:5px;}
.info-con dt{float:left;font-weight:bold;font-size:14px;}
.btn:hover{color:#fff;}
.btn-blue {
    background-position: 0 0;
    color: #fff;
    font: 12px/21px 宋体;
    height: 21px;
    margin: 0 10px;
    text-align: center;
    width: 66px;
    background-image: url("../images/bg_sysSprite.png");
    background-repeat: no-repeat;
    display: inline-block;
    margin-left: 640px;
}
</style>
</head>

<body>
<div class="wrap">
	<%@include file="../head.jspf"%>
    
    <!--content Start-->
    <div class="content clearfix" >
    	<!--search-box Start-->
    	<div class="search-box">
            <div class="breadCutNav">
                <b class="icon icon_home"></b>
                <span>当前位置：</span>
                <a href="index.xhtml">首页</a>
                <span> &gt;</span>
                <a href="javascript:;">每日信息</a>
            </div>
            <%@include file="search.jspf"%>
        </div>
    	<!--search-box End-->
        <!--sub Start-->
        <div class="sub l">
        	<div class="column" id="userLogin">
            	<div class="title">
                	<h2>每日信息</h2>
                </div>
                <div class="con">
                	<ul class="infoList">
                		<c:forEach items="${textPublications}" var="pub">
                    	<li>
                            <a href="news.xhtml?face=dailyInfoDetail&pubId=${pub.pubId}"><b class="i-rArr"></b>${pub.pubName}</a>
                        </li>
                    	</c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <!--sub End-->
        <!--main Start-->
        <div class="main">
        	<div class="column" >
                <!--<div class="title"><h2>每日信息</h2></div>-->
                <div class="con">
                	<input type="hidden" id="countyDynamics" value="${countyDynamics}">
                	<input type="hidden" id="netInfos" value="${netInfos}">
                	<input type="hidden" id="oneInfos" value="${oneInfos}">
                	<input type="hidden" id="workDynamics" value="${workDynamics}">
                	<input type="hidden" id="pubId" value="${pubId}">
                	<fmt:parseDate value="${textPublication.createDate}"  var="parsedEmpDate" pattern="yyyy-MM-dd HH:mm:ss"/>
                	<h4 style="color: #084e8f;">内部消息&nbsp;&nbsp;严禁外传</h4>
                	<h2 class="info-title">${textPublication.pubName}
                    	<p class="info-date" style="font: 19px/30px 宋体">
                    	<fmt:formatDate value='${parsedEmpDate}' type="date" dateStyle="full"/>&nbsp;&nbsp;&nbsp;&nbsp;重庆市交通委员会办公室</p>
                    </h2>
                    <div class="info-con">
                   	 <h3>【内容提要】</h3>
                    <h4 class="workDynamics">工作动态</h4>
                    <ul>
                    	<c:forEach items="${textPublication.workDynamics}" var="n" varStatus="status">
                    	<li>
                            <a href="#${n.giId}">${status.index + 1}、${n.giTitle}</a>
                        </li>
                    	</c:forEach>
                    </ul>
                    <h4 class="countyDynamics">区县动态</h4>
                    <ul>
                    	<c:forEach items="${textPublication.countyDynamics}" var="n" varStatus="status">
                    	<li>
                            <a href="#${n.giId}">${status.index + 1}、${n.giTitle}</a>
                        </li>
                    	</c:forEach>
                    </ul>
                    <h4 class="oneInfos">一句话信息</h4>
                    <ul>
                    	<c:forEach items="${textPublication.oneInfos}" var="n" varStatus="status">
                    	<li>
                            <a href="#${n.giId}">${status.index + 1}、${n.giTitle}</a>
                        </li>
                    	</c:forEach>
                    </ul>
                    
                    <h4 class="netInfos">网络信息</h4>
                    <ul>
                    	<c:forEach items="${textPublication.netInfos}" var="n" varStatus="status">
                    	<li>
                            <a href="#${n.giId}">${status.index + 1}、${n.giTitle}</a>
                        </li>
                    	</c:forEach>
                    </ul>
                    
                    <h3 class="workDynamics">【工作动态】</h3>
                    <c:forEach items="${textPublication.workDynamics}" var="n">
                   	<dl>
                        <dt><a name="${n.giId}">${n.giTitle}</a>&nbsp;</dt>
                        <dd>${n.giContent}</dd>
                        <dd style="text-align: right;">（${n.entryDept}）</dd>
                    </dl>
                   	</c:forEach>
                    
                    <h3 class="countyDynamics">【区县动态】</h3>
                    <c:forEach items="${textPublication.countyDynamics}" var="n">
                   	<dl>
                        <dt><a name="${n.giId}">${n.giTitle}</a>&nbsp;</dt>
                        <dd>${n.giContent}</dd>
                        <dd style="text-align: right;">（${n.entryDept}）</dd>
                    </dl>
                   	</c:forEach>
                    <h3 class="oneInfos">【一句话信息】</h3>
                    <c:forEach items="${textPublication.oneInfos}" var="n">
                   	<dl>
                        <dt><a name="${n.giId}">${n.giTitle}</a>&nbsp;</dt>
                        <dd>${n.giContent}</dd>
                        <dd style="text-align: right;">（${n.entryDept}）</dd>
                    </dl>
                   	</c:forEach>
                    <h3 class="netInfos">【网络信息】</h3>
                    <c:forEach items="${textPublication.netInfos}" var="n">
                   	<dl>
                        <dt><a name="${n.giId}">${n.giTitle}</a>&nbsp;</dt>
                        <dd>${n.giContent}</dd>
                        <dd style="text-align: right;">（${n.entryDept}）</dd>
                    </dl>
                   	</c:forEach>
                    </div>
                    <h3><a href="javascript:;" onclick="exportTable();return false;" class="btn btn-blue">导出word</a></h3> 
                </div>
            </div>
        </div>
        <!--main End-->
    </div>
    
    <%@include file="../footer.jspf"%>
</div>
<script type="text/javascript"  src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"  src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript">
$(function(){
	var countyDynamics =$('#countyDynamics').val();
	var netInfos = $('#netInfos').val();
	var oneInfos = $('#oneInfos').val();
	var workDynamics = $('#workDynamics').val();
	if(countyDynamics==0){
		$(".countyDynamics").hide();
	}
	if(netInfos==0){
		$(".netInfos").hide();
	}
	if(oneInfos==0){
		$(".oneInfos").hide();
	}
	if(workDynamics==0){
		$(".workDynamics").hide();
	}
})

function exportTable(){
	var pubId = $("#pubId").val();
	location.href ="../guest/news.xhtml?action=exportTable&pubId="+pubId;
}
</script>
</body>
</html>
