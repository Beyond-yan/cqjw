<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@page
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>

<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--交通搜索</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet"
	type="text/css">
<link href="${StaticResourcePath}/css/index.css" rel="stylesheet"
	type="text/css">
<%-- <link href="${StaticResourcePath}/css/news.css" rel="stylesheet" type="text/css"> --%>
<script type="text/javascript"
	src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
.sub {
	width: 100%;
}

.r {
	width: 221px;
}

.pic img {
	width: 201px;
	height: 130px;
}

.sub-picNews .con {
	text-align: center;
	padding: 10px 10px 10px;
}

.main {
	float: left;
	width: 492px;
	margin: 0 8px;
}

.content .column {
	height: 640px;
	overflow: hidden;
}

.infoList a {
	width: 89%;
}

.infoList .time {
	text-align: right;
	color: #6d6d6d;
	font-family: Tahoma;
}

.kind a {
	font-size: 18px;
	text-decoration: underline;
	cursor: pointer;
	/* font-weight: bold; */
}

.div1 {
	padding-left: 40px;
	font-size: 12px;
	/* font-weight: bold; */
}
</style>
</head>

<body>
	<!-- onload="pageLoad()" -->
	<div class="wrap">
		<%@include file="../head.jspf"%>
		<!--content Start-->
		<div class="content clearfix">
			<!--search-box Start-->
			<div class="search-box">
				<div class="breadCutNav">
					<b class="icon icon_home"></b> <span>当前位置：</span> <a
						href="index.xhtml">首页</a> <span> &gt;</span> <a
						href="javascript:;">交通搜索</a>
				</div>
			</div>
			<!--search-box End-->
			<!--sub Start-->
			<div class="sub l">
				<div class="column">
					<p>&nbsp;</p>
					<p>&nbsp;</p>
					<input type="hidden" name="category" id="category" value="" /> <input
						type="hidden" name="userName" id="userName" value="${userName}" />
					<input type="hidden" name="token" id="token" value="" />
					<div class="kind" style="text-align: left; margin-left: 230px;">
						<a onclick="querySearch(1);return false;" style="color: blue;"
							id="dept1">公开公文</a> <span>&nbsp;&nbsp;&nbsp;</span> <a
							onclick="querySearch(5);return false;" style="color: blue;"
							id="dept5">交通课堂</a> <span>&nbsp;&nbsp;&nbsp;</span> <a
							onclick="querySearch(6);return false;" style="color: blue;"
							id="dept6">交通动态</a> <span>&nbsp;&nbsp;&nbsp;</span> <a
							onclick="querySearch(3);return false;" style="color: blue;"
							id="dept3">交通视频</a> <span>&nbsp;&nbsp;&nbsp;</span> <a
							onclick="querySearch(7);return false;" style="color: blue;"
							id="dept7">图文影视</a> <span>&nbsp;&nbsp;&nbsp;</span> <a 
							onclick="querySearch(8);return false;" style="color: blue;" 
							id="dept8">企业信用</a>
							
					</div>
					<table border="0" align="center"margin-top:"100px">
						<tr id="aborigines" style="display: none;" >
							<td ><input
								type="text" name="searchword" value="${searchword}"
								id="searchword"
								style="height: 40px; width: 450px; vertical-align: middle; font-size: 14px; line-height: 40px;">&nbsp;&nbsp;&nbsp;
								<input type="button"
								style="height: 40px; width: 80px; vertical-align: middle; text-align: center; font-size: 16pt"
								value="查询" onclick="searchList();return false;"></td>
						</tr>
						<tr id="redBlackQ" style="display: none;" >
							<td><select id="selectClass"
								style="height: 44px; width: 100px; font-size: 15px;">
									<option value="z" title="请选择">-- 请选择 --</option>
									<option id="corpblack" value="corpblack" title="企业黑名单">企业黑名单</option>
									<option id="corpred" value="corpred" title="企业红名单">企业红名单</option>
									<option id="personblack" value="personblack" title="人员黑名单">人员黑名单</option>
							</select>&nbsp;&nbsp;&nbsp;</td>
							<td><input type="text" name="name" value="${name}"
								id="name" placeholder="企业(人员)名称"
								style="height: 40px; width: 180px; vertical-align: middle; font-size: 14px; line-height: 40px;">&nbsp;&nbsp;&nbsp;
								<input type="text" placeholder="社会信用代码(身份证号码)" name="code" value="${code}" id="code"
								style="height: 40px; width: 180px; vertical-align: middle; font-size: 14px; line-height: 40px;">&nbsp;&nbsp;&nbsp;
								<input type="button"
								style="height: 40px; width: 80px; vertical-align: middle; text-align: center; font-size: 16pt"
								value="查询" onclick="searchList();return false;"></td>
						</tr>
					</table>
					<div class="con">
						<input type="hidden" id="totalCount"
							value="${searchResult[0].totals}" /> <input type="hidden"
							id="pageSize" value="${searchResult[0].pageSize}" /> <input
							type="hidden" id="pageIndex" value="${searchResult[0].pageIndex}" />
						<input type="hidden" id="flag" value="${flag}" /> <input
							type="hidden" id="userNo" value="${userNo}" />
						<ul class="infoList  clearfix" id="tbody">
							<c:if test="${flag==1}">
								<c:forEach items="${searchResult}" var="n">
									<li
										style="display: block; text-align: left; margin-left: 100px; margin-right: 100px">
										<a
										href='../guest/news.xhtml?face=getPublicArchivesDetail&archivesId=${n.archivesid}&schema=${n.schema_code}'
										target='_blank'> <b class='i-rArr'></b>${n.subject}
									</a> <span class='time'>${fn:substring(n.createtime,0,10)}</span>
									</li>
								</c:forEach>
							</c:if>
							<c:if test="${flag==3}">
								<c:forEach items="${searchResult}" var="n">
									<li
										style="display: block; text-align: left; margin-left: 100px; margin-right: 100px">
										<a href='news.xhtml?face=videoNewsDetail&videoId=${n.news_id}'
										target='_blank'> <b class='i-rArr'></b>${n.news_title}
									</a> <span class='time'>${fn:substring(n.entry_date,0,10)}</span>
									</li>
								</c:forEach>
							</c:if>
							<c:if test="${flag==6}">
								<c:forEach items="${searchResult}" var="n">
									<li
										style="display: block; text-align: left; margin-left: 100px; margin-right: 100px">
										<a href='news.xhtml?face=newsDetail&newsId=${n.news_id}'
										target='_blank'> <b class='i-rArr'></b>${n.news_title}
									</a> <span class='time'>${fn:substring(n.entry_date,0,10)}</span>
									</li>
								</c:forEach>
							</c:if>
							<c:if test="${flag==5}">
								<c:forEach items="${searchResult}" var="n" varStatus="status">
									<li
										style="display: block; text-align: left; margin-left: 100px; margin-right: 100px">
										<a href="javascript:;"
										onclick="downLoad(${status.index});return false;"
										target='_blank'> <b class='i-rArr'></b>${n.news_title} <input
											type="hidden" id="fileName${status.index}"
											value="${n.attachment}" /> <input type="hidden"
											id="filePath${status.index}" value="${n.path}" />
									</a> <span class='time'>${fn:substring(n.entry_date,0,10)}</span>
									</li>
								</c:forEach>
							</c:if>
							
							<c:if test="${flag==8}">
								<table width="100%" border="1" class="dataList">
									<thead>
										<tr id="titleTr" > </tr>
									</thead>
									<tbody id="tbodyFile"> </tbody>
								</table>
							</c:if>
							
						</ul>
						<div id="trafficPager" class="page"></div>
					</div>
				</div>
			</div>
		</div>
		<!--content End-->
		<%@include file="../footer.jspf"%>
	</div>
	<script type="text/javascript"
		src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
	<script type="text/javascript"
		src="${StaticResourcePath}/js/j.pager.js"></script>
	<script type="text/javascript">
$(function(){
	 <c:if test="${!empty searchResult[0].totals}">
		$("#trafficPager").buildPager({
			totalLines:${searchResult[0].totals},
			pageSize:${searchResult[0].pageSize},
			startIndex:${searchResult[0].pageIndex},
			displayNum:5,
			afterChange:function() {
				searchList();
			}
		});
		</c:if>
		var flag = $('#flag').val();
		$("a[id^='dept']").attr("style","color:blue");
		$("#dept"+flag).attr("style","color:black");
		$("#dept"+flag).attr("style","text-decoration:none;");
		
		if (flag == 8) {
			$('#redBlackQ').show();
			$('#aborigines').hide();
		} else {
			$('#aborigines').show();
			$('#redBlackQ').hide();
		}
});
//公开公文
function queryPubNotice(searchword,flag){
	var pageIndex = $("#trafficPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	searchword = encodeURI(encodeURI(searchword));
	location.href="../guest/news.xhtml?face=queryPubNotice&pageIndex="+pageIndex+"&searchword="+searchword+"&flag="+flag;
	
};
//交通信息交通课堂交通视频
function queryInfo(searchword,flag){
	var pageIndex = $("#trafficPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	searchword = encodeURI(encodeURI(searchword));
	flag = encodeURI(encodeURI(flag));
	location.href="../guest/news.xhtml?face=queryInfo&pageIndex="+pageIndex+"&searchword="+searchword+"&flag="+flag;	
};

//查询方法
function querySearch(flag){	
	$("a[id^='dept']").attr("style","color:blue");
	$("#dept"+flag).attr("style","color:black");
	$("#dept"+flag).attr("style","text-decoration:none;");
	var searchword = $('#searchword').val();
	
	if (flag == 8) {
		$('#redBlackQ').show();
		$('#aborigines').hide();
	} else {
		$('#aborigines').show();
		$('#redBlackQ').hide();
	}
	
	if(flag==1){ //公开公文
		queryPubNotice(searchword,flag);	
	}else if(flag==5){//交通课堂
		queryInfo(searchword,flag);	
	}else if(flag==6){//交通信息
		queryInfo(searchword,flag);	
	}else if(flag==3){//交通视频
		queryInfo(searchword,flag);	
	}else if(flag==7){//图文影视
		queryPhotoInfo(searchword,flag);
	} else if (flag==8) { //企业信用 
		$('#flag').val(8);
		<%if (!SystemContext.isAuthenticated()) {%>
			alert("登陆后才可查询");
		<%} else {%>
			jumpRedBlack(flag);
		<%} %>
	} 
}

function downLoad(num){
	var fileName=encodeURI(encodeURI($("#fileName"+num).val()));
	var filePath=$("#filePath"+num).val();
	location.href="../guest/index.xhtml?face=downLoad&fileName="+fileName+"&filePath="+filePath;
}
function queryPhotoInfo(searchword,flag){
	    $('#flag').val(7);
		var jsonPara1 = {
		            method: "login",
		            parameter:JSON.stringify({ 
						userId:"administrator", password:"evias", appkey:"publish"
						})
					};
		$.ajax({
				type : "get",
				url : "http://10.224.5.44:8080/evias/api.action",                                                      
			    dataType : "jsonp",    								 		
				jsonp:"jsonpCallback",
		      	data: jsonPara1,
		      	success : function(data){
		         var token = data['token'];
		         $('#token').val(token);
		       	},
		       	error:function(response){ 
		       		//alert("error");
		       	}
		      });
		var token =$('#token').val();
	    var pageIndex = $("#trafficPager").attr("curPage");
	    var num ="";
	    if (typeof pageIndex == "undefined") {
			pageIndex = 0;
			num=0;
		}else {
	    	var test=parseInt(pageIndex);
	    	num=15*(parseInt(pageIndex));
			num=parseInt("15")+parseInt(num);
	    }  
		var jsonPara = {
		            method: "getResultsByKeyword",
		            parameter:JSON.stringify({ 
						folderID:" ",content:searchword,start:num, pageSize:15,token:token
						})
					};
		$.ajax({
			    url:"http://10.224.5.44:8080/evias/api.action",
				cache: false,
				type : "get",
				dataType:"jsonp",
		       	jsonp:"jsonpCallback",
		      	data: jsonPara,  
				success : function(data) {
					var json = data;
					var pageSize=15;
					var totalCount =json.resourceTotalCount;
					var textNewsList="";
					var created="";
					$("#trafficPager").buildPager({
						totalLines:totalCount,
						pageSize:15,
						startIndex:pageIndex,
						displayNum:5,
						afterChange:function() {
							searchList();
						}
					});
				  $.each(json.queryResult.results, function(i, n) {
						created = n.created;
						created = created.substring(0,10);
		textNewsList+= "<li style=\"display:block;text-align:left;margin-left:100px;margin-right:100px;\"> <a href='http://10.224.5.44:8080/evias/toDetail2.action?resourceID="+
						n.id+"' target='_blank'><b class='i-rArr'></b>"+n.name+"</a>"+"<span class='time' >"+created+"</span></li>"
					    });
					  $("#tbody").html("");
					  $("#tbody").html(textNewsList); 
					},
					error :function(data){
						alert("登陆后才可查询");
					}
				});
		
};
function searchList(){
	var flag = $('#flag').val();
	var searchword = $('#searchword').val();
	if(flag==1){ //公开公文
		queryPubNotice(searchword,flag);	
	}else if(flag==5){//交通课堂
		queryInfo(searchword,flag);	
	}else if(flag==6){//交通信息
		queryInfo(searchword,flag);	
	}else if(flag==3){//交通视频
		queryInfo(searchword,flag);	
	}else if(flag==7){//图文影视
		queryPhotoInfo(searchword,flag);
	} else if(flag==8){// 企业信用
		<%if (!SystemContext.isAuthenticated()) {%>
			alert("登陆后才可查询");
		<%} else {%>
			searchRedBlack();
		<%} %>
	}
}

function jumpRedBlack(flag) {
	location.href="../guest/news.xhtml?face=queryInfo&pageIndex=0&searchword=&flag="+flag;
}

function searchRedBlack() {
	var submmitUrl = "";
	var name = $('#name').val();
	var code = $('#code').val();
	var selectClass = $('#selectClass').val();
	
	if (selectClass == 'corpblack') {
		submmitUrl = "../console/redBalckQuery.xhtml?action=enterpriseBlacklist";
	} else if (selectClass == 'corpred') {
		submmitUrl = "../console/redBalckQuery.xhtml?action=enterpriseRedlist";
	} else if (selectClass == 'personblack') {
		submmitUrl = "../console/redBalckQuery.xhtml?action=personalBlacklist";
	}

	//if (!name || ! code || selectClass=='z') {
		//alert("请将信息填写完成");
	//} else {
		$.ajax({
			url : submmitUrl,
			cache : false,
			type : "POST",
			dataType : "text",
			data : {
				"name" : name,
				"code" : code
			},
			success : function(content) {
				var data = eval('(' + content + ')');
				if (data.result == 'success') {
					createHtmlNew(data);
				} else {
					alert("查询失败，请稍后再试")
				}
			}
		});
	//}
}

function createHtmlNew(data) {

	var lists = data.lists;
	if (lists.length < 1) {
		$('#titleTr').empty();
		$('#tbodyFile').empty();
		alert("未查询到数据");
		return false;
	}
	
	// create title
	var title = data.dataTitle;
	var titles = title.split(",");
	var titleHtml = "";
	for (var i=0; i < titles.length; i++) {
		if (i == 0) {
			titleHtml += "<td style='width=1%;' >序号</td>";
		}
		titleHtml += "<td style='width=20%;' >"+titles[i]+"</td>";
	}
	$('#titleTr').empty().append(titleHtml);
	
	// create data
	var dataHtml = "";
	if (data.dataType == '1') {
		for (var j=0; j < lists.length; j++) {
			dataHtml += "<tr>";
			dataHtml += "<td>"+j+1+"</td>";
			dataHtml += "<td>"+lists[j].corpname+"</td>";
			dataHtml += "<td>"+lists[j].creditcode+"</td>";
			dataHtml += "<td>"+lists[j].reason +"</td>";
			dataHtml += "<td>"+lists[j].black_type_name+"</td>";
			dataHtml += "<td>"+lists[j].auth_organ_name+"</td>";
			dataHtml += "<td>"+lists[j].auth_time+"</td>";
			dataHtml += "</tr>";
		}
	} else if (data.dataType == '2') {
		for (var j=0; j < lists.length; j++) {
			dataHtml += "<tr>";
			dataHtml += "<td>"+j+1+"</td>";
			dataHtml += "<td>"+lists[j].corpname+"</td>";
			dataHtml += "<td>"+lists[j].creditcode+"</td>";
			dataHtml += "<td>"+lists[j].reason+"</td>";
			dataHtml += "<td>"+lists[j].red_type_name+"</td>";
			dataHtml += "<td>"+lists[j].auth_organ_name+"</td>";
			dataHtml += "<td>"+lists[j].auth_time+"</td>";
			dataHtml += "</tr>";
		}
	} else if (data.dataType == '3') {
		for (var j=0; j < lists.length; j++) {
			dataHtml += "<tr>";
			dataHtml += "<td>"+j+1+"</td>";
			dataHtml += "<td>"+lists[j].personname+"</td>";
			dataHtml += "<td>"+lists[j].id_num+"</td>";
			dataHtml += "<td>"+lists[j].reason+"</td>";
			dataHtml += "<td>"+lists[j].black_type_name+"</td>";
			dataHtml += "<td>"+lists[j].auth_organ_name+"</td>";
			dataHtml += "<td>"+lists[j].auth_time+"</td>";
			dataHtml += "</tr>";
		}
	}
	$('#tbodyFile').empty().append(dataHtml);
}

</script>
</body>
</html>