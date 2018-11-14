<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet"
	type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<link
	href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/plugin/ueditor/themes/default/css/ueditor.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
.column {
	min-width: 940px;
}

.error {
	color: red;
	padding-left: 10px;
}
</style>
<title>重庆交通政务办公网--政务信息上报管理</title>
</head>

<body>
	<!--path Start-->
	<div class="path">
		<b class="icon i-home"></b> <span>当前位置：</span> 信息报送
		<span>-></span>政务信息上报管理
	</div>
	<!--path End-->
	<!--column01 Start-->
	<div class="column" id="search">
		<div class="title">
			<h2>
				<b class="icon i-search"></b>快速搜索
			</h2>
		</div>
		<div class="con">
			<label>标题：</label><input type="text" name="newsTitle" class="input-text" value="${newsTitle}">
			<label>采编时间：</label>
			<div class="data" id="dataSelect-start">
				<input type="text" name="entryDateS" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>'
					class="input-text tcal tcalInput">
			</div>
			<span>&nbsp;～&nbsp;</span>
			<div class="data" id="dataSelect-end">
				<input type="text" name="entryDateE" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>'
					class="input-text  tcal tcalInput">
			</div>


			<label>上报类型：</label> <select id="selectstatus" name="reportStatus" class="input-small">
				<option selected value="report">全部</option>
				<option value="reportTrafficDept">交通部</option>
				<option value="reportCityCommittee">市委</option>
				<option value="reportCityGovernment">市府</option>
			</select>
			<input type="hidden"  id="status"  value="${status}"/>
			 <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">搜 索</a>
		</div>
	</div>
	<!--column01 End-->
	<!--column02 Start-->
	<div class="column">
		<div class="title">
			<h2>
				<b class="icon i-search"></b>上报列表
			</h2>
		</div>
		<div class="con">
			
			<table width="100%" border="1" class="dataList">
				<thead>
					<tr>
						<td width="5%">序号</td>
						<td width="30%" style="text-align:left;">标题</td>
						<td width="10%" style="text-align:left;">作者</td>
						<td width="15%" style="text-align:left;">上报时间</td>
						<td width="10%" style="text-align:left;">采用类别</td>
						<td width="15%" style="text-align:left;">上报类别</td>
						<td width="5%" >编辑</td>
						<td width="5%">删除</td>
					</tr>
				</thead>
				<tbody id="tbodyNews">
					<c:forEach items="${paginations}" var="tn" varStatus="status">
						<tr>
							<td>${tn.rowNo}</td>
							<th style="text-align:left;"><a href="govTextNews.xhtml?face=editInfoReportView&giId=${tn.giId}"><span style='color:blue'>${tn.giTitle}</span></a></th>
							<td style="text-align:left;">${tn.newsAuthor}</td>
							<td style="text-align:left;">${tn.createDate}</td>
							<td style="text-align:left;" value="${tn.adoptType}">${tn.adoptType}</td>
<%-- 							<td  class="adoptType" value="${tn.adoptType}" style="text-align:left;"></td> --%>
							<td class="reportType" value="${tn.reportType}"  style="text-align:left;"></td>	
							<td><a href="govTextNews.xhtml?face=editInfoReportView&giId=${tn.giId}" class="icon i-edit"></a></td>				
							<td><a href="javascript:;"onclick="deleteInfoReport('${tn.giId}');return false;" class="icon i-del-02"></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="listPager" class="page clearfix"></div>
		</div>

	</div>
</div>
<!--column02 End-->


</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function(){
	var category = $("#status");
	$("#selectstatus option").each(function(){
		if($(this).val()==category.val()){
			$(this).attr("selected",true);
		}
	});
	<c:if test="${!empty paginations[0].totalCount}">
		$("#listPager").buildPager({
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

//默认显示列表时，取得上报信息的状态
$(function(){
	$(".adoptType").each(function(){
		
		var adoptType = $(this).attr("value");
		var	flagStr="";
		if(adoptType.indexOf("trafficDept")>=0){
			flagStr += " 交通部 ";
		}
		if(adoptType.indexOf("cityCommittee")>=0){
			flagStr += " 市委 ";
		}
		if(adoptType.indexOf("cityGovernment")>=0){
			flagStr += " 市府 ";
		}
		if(adoptType.indexOf("comLeaderAppr")>=0){
			flagStr += "<br/>被委领导批示<br/>";
		}
		if(adoptType.indexOf("govLeaderAppr")>=0){
			flagStr += "被市领导批示<br/>";
		}
		if(adoptType.indexOf("trafLeaderAppr")>=0){
			flagStr += "被交通部领导批示<br/>";
		}
		$(this).html(flagStr);
		
	});

//上报类别
	$(".reportType").each(function(){
		
		var reportType = $(this).attr("value");
		var	flagStr="";
		if(reportType.indexOf("reportTrafficDept")>=0){
			flagStr += " 上报交通部 ";
		}
		if(reportType.indexOf("reportCityCommittee")>=0){
			flagStr += " 上报市委 ";
		}
		if(reportType.indexOf("reportCityGovernment")>=0){
			flagStr += " 上报市府 ";
		}
		$(this).html(flagStr);
		
	});
}) 


function searchList() {
	var newsTitle = encodeURI(encodeURI($("input[name='newsTitle']").val()));
	newsTitle = newsTitle.replace(/#/g,"%23");
	var entryDateS =  $("input[name='entryDateS']").val();
	var entryDateE =  $("input[name='entryDateE']").val();
	var status=$("select[name='reportStatus']").val();
	var pageIndex = $("#listPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="govTextNews.xhtml?face=searchInfoReportView&newsTitle="+newsTitle+"&entryDateS="
			+entryDateS+"&entryDateE="+entryDateE+"&status="+status+"&pageIndex="+pageIndex;
	/* $.ajax({
		url : "govTextNews.xhtml?action=searchInfoReportView",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
		    "pageIndex" : pageIndex,
			"newsTitle" : newsTitle,
		    "entryDateS" : entryDateS,
		    "entryDateE" : entryDateE
		},
		success : function(data) {
			var json = $.parseJSON(data);
			var textGovInfoList="";
			    $.each(json.pageData, function(i, n) {
			    	var adoptType = n.adoptType;
			    	var flagStr = "";
			    	
			    	var flag = n.reportType;
			    	if (typeof flag == "undefined") flag = "";
			    	
			    	var str="";
			    	if(flag==null){
			    		flag="";
			    	}

			    	if(adoptType.indexOf("trafficDept")>=0){
			    		flagStr += " 交通部 ";
			    	}
			    	if(adoptType.indexOf("cityCommittee")>=0){
			    		flagStr += " 市委 ";
			    	}
			    	if(adoptType.indexOf("cityGovernment")>=0){
			    		flagStr += " 市府 ";
			    	}
			    	if(adoptType.indexOf("comLeaderAppr")>=0){
						flagStr += "<br/>被委领导批示<br/>";
					}
					if(adoptType.indexOf("govLeaderAppr")>=0){
						flagStr += "被市领导批示<br/>";
					}
					if(adoptType.indexOf("trafLeaderAppr")>=0){
						flagStr += "被交通部领导批示<br/>";
					}
			    	
			    	if(flag.indexOf("reportTrafficDept")>=0){
			    		str +=" 上报交通部 ";
			    	}
			    	if(flag.indexOf("reportCityCommittee")>=0){
			    		str +=" 上报市委 ";
			    	}
			    	if(flag.indexOf("reportCityGovernment")>=0){
			    		str +=" 上报市府 ";
			    	}
			    	
		    
			    	textGovInfoList+= "<tr>"+
			    	             "<td>"+(i+1)+"</td>"+
                                 "<th style=\"text-align:left;\"><a href='govTextNews.xhtml?face=editInfoReportView&newsId="+n.newsId+"'>"+"<span style='color:blue'>"+n.giTitle+"</span></th>"+
                                 "<td style=\"text-align:left;\">"+n.newsAuthor+"</td>"+
                                 "<td style=\"text-align:left;\">"+n.createDate+"</td>"+
                                 "<td style=\"text-align:left;\">"+flagStr+"</td>"+
                                 "<td style=\"text-align:left;\">"+str+"</td>"+
			    	             "<td><a href='govTextNews.xhtml?face=editInfoReportView&newsId="+n.newsId+"'  class='icon i-edit'></a></td>"+
                                 "<td><a href='javascript:;'  onclick=deleteInfoReport('"+n.giId+"');return false; class='icon i-del'></a></td>"
			    });
			  $("#tbodyNews").html("");
			  $("#tbodyNews").html(textGovInfoList);
			},
			error :function(data){
				alert("查询数据失败");
			}
		});
	 */
}

//删除上报信息
function deleteInfoReport(giId) {
	
	//alert(userId);
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) {
		
		if (action == "ok") {
			$.ajax({
				url : "govTextNews.xhtml?action=deleteInfoReport",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"giId" :giId
				},
				success : function(content) {
					/* asyncbox.alert(content, "信息窗口", function() {
						document.location.reload();
					});*/
					document.location.reload();
				}
			});
		}
	})
}


</script>

</html>
