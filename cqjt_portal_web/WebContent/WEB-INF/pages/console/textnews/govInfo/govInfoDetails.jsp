<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ 
	taglib prefix="ut" uri="/WEB-INF/userTag/Pager.tld"%><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.body{margin: 0 auto;
		width:960px;}
.font{font-size: 16px;
		/* text-indent:4em */}
#p1{line-height:2;}	

</style>
<title>查看详细信息</title>
</head>
<body>
<div  class="body" id="p1"  >
	<!--path Start-->
	<div class="path">
		<div align="left"><b class="icon i-home"></b> 
		<span>当前位置：</span> 
		信息报送
		<span>-></span>
		详细信息
		</div>
	</div>
	<!--path end-->
	
	<h1 align="center">${GovInfoDetail.giTitle}</h1>
	<hr color="gray" size="5">
	<p align="center">【上报类别:&nbsp;<span id="reportType" value="${GovInfoDetail.reportType}"></span>&nbsp;&nbsp;采用类别：<span id="adoptType" value="${GovInfoDetail.adoptType}"></span>】</p>
	<p align="center">【投稿人：${GovInfoDetail.entryUser} &nbsp; &nbsp;投稿部门：${GovInfoDetail.entryDept} &nbsp; &nbsp; 投稿日期：${GovInfoDetail.entryDate}】</p>

	<%-- <p align="justify" id="p1" class="suojin">${textNewsDetails.newsContent}</p> --%>
	<br>
	<br>
	<div  id="p1" class="font" align="center" >
	
	${GovInfoDetail.giContent}
	
	</div>
	<br>
	<br>
	<hr color="gray" size="5">

</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
 $(function(){
	
	var adoptType = $("#adoptType").attr("value");
	//alert(val);
	var val3="";
	if(adoptType.indexOf("trafficDept")>0){
		val3+=" 交通部 ";
		/* $("#adoptType").html("上报类别：交通部 "); */
	}
	 if(adoptType.indexOf("cityCommittee")>0){
		val3+=" 市委  ";
		/* $("#adoptType").html("上报类别：市委 "); */
	}
	if(adoptType.indexOf("cityGovernment")>0){
		val3+=" 市府 ";
		/* $("#adoptType").html("上报类别： 市府"); */
	}
	$("#adoptType").html(val3);
});
 $(function(){
	var reportType = $("#reportType").attr("value");
	var val1="";
	if(reportType.indexOf("trafficDept")>0){
		val1+=" 交通部 ";
		/* $("#adoptType").html("上报类别：交通部 "); */
	}
	 if(reportType.indexOf("cityCommittee")>0){
		val1+=" 市委  ";
		/* $("#adoptType").html("上报类别：市委 "); */
	}
	if(reportType.indexOf("cityGovernment")>0){
		val1+=" 市府 ";
		/* $("#adoptType").html("上报类别： 市府"); */
	}
	$("#reportType").html(val1);
 });
 
</script>
</html>