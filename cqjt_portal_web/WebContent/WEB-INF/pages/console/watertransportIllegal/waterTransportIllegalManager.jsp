<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>水运工程违规行为</title>
<link href="${StaticResourcePath}/ww/css/com.css"
	rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="${StaticResourcePath}/ww/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${StaticResourcePath}/ww/css/sysManage.css" />
<link
	href="${StaticResourcePath}/ww/js/asyncbox/skins/ZCMS/asyncbox.css"
	type="text/css" rel="stylesheet" />
<script src="${StaticResourcePath}/ww/js/jquery-1.10.2.min.js"></script>
<script src="${StaticResourcePath}/ww/js/selfpagination.js"></script>
<script src="${StaticResourcePath}/ww/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${StaticResourcePath}/ww/js/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript"
	src="${StaticResourcePath}/ww/js/util.js"></script>
	
<style type="text/css">
#searchTable {
	width: 50%;
}

#searchTable td {
	border: 0px;
}
</style>

<script type="text/javascript">
	$(function() {
		queryData('1', '15');
	});

	function queryData(p, s) {
		jumpPage(p, s);
	}
	
	//分页控制
	function jumpPage(jumpPage, pageSize) {
		var endTime = $("#endTime").val();
		var startTime = $("#startTime").val();
		var ILLEGAL_COMPANY_NAME = $("#ILLEGAL_COMPANY_NAME").val();
		var SOCIOLOGY_CREDIT_CODE = $("#SOCIOLOGY_CREDIT_CODE").val();
		$.ajax({
			url : "${StaticResourcePath}/console/waterTransport.xhtml?action=queryVideoNewsListByPageIndex",
			cache : false,
			type : "post",
			dataType : "text",
			data : {
				"pageIndex" : jumpPage,
				"pageSize" : pageSize,
				"startTime" : startTime,
				"endTime" : endTime,
				"ILLEGAL_COMPANY_NAME" : ILLEGAL_COMPANY_NAME,
				"SOCIOLOGY_CREDIT_CODE" : SOCIOLOGY_CREDIT_CODE
			},
			success : function(content) {
				var pageData = eval('(' + content + ')');
				var pageIndex = eval(pageData.pageIndex);
				var pageSize = eval(pageData.pageSize);
				var pageCount = eval(pageData.pageCount);
				var tableData = eval(pageData.result);
				$("#mytbody").empty().append(createNewPageHTML(tableData, pageIndex, pageSize, pageCount));
				updatePageInfo(pageData.pageIndex, pageData.totalCount, pageData.pageCount);
			}
		});
	}
	
	function updatePageInfo(pageIndex, totalCount, pageCount) {
		$("#totalCount").empty().append(totalCount);
		$("#pageIndex1").empty().append(pageIndex);
		$("#pageCount1").empty().append(pageCount);
		$("#jumpPage1").val(pageIndex);
		$("#pageIndex").val(pageIndex);
		$("#pageCount").val(pageCount);
		hanlderPageLinkAttr('firstPage', 'perPage', 'nextPage', 'lastPage',
				pageIndex, pageCount);
	}
	
	function createNewPageHTML(tableData) {
		var newPageHtml = "";
		for ( var i = 0; i < tableData.length; i++) {
			newPageHtml += "<tr height='25px;'>";

			newPageHtml += "<td >";
			newPageHtml += i + 1;
			newPageHtml += "</td>";
			newPageHtml += "<td style='text-align:left;'>";
			
			if (tableData[i].report_FORM_TYPE == '1') {
				newPageHtml += nullToEmpty(tableData[i].illegal_COMPANY_NAME);
			} else if (tableData[i].report_FORM_TYPE == '2') {
				newPageHtml += nullToEmpty(tableData[i].illegal_PERSONNEL_NAME);
			}
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += nullToEmpty(tableData[i].sociology_CREDIT_CODE);
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += nullToEmpty(tableData[i].project_CATEGORY) == '1'? '公路' : '水运';
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += nullToEmpty(tableData[i].report_FORM_TYPE) == '1'? '企业' : '人员';
			newPageHtml += "</td>";
			
			newPageHtml += "<td >";
			newPageHtml += nullToEmpty(tableData[i].applicant);
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += nullToEmpty(tableData[i].create_TIME);
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			
			newPageHtml += "<a class='btn44_20 btnBlue' href='#' onclick=editVideoNews('"
					+ tableData[i].id + "','"+ tableData[i].report_FORM_TYPE +"')>编辑</a>";
			newPageHtml += "<a class='btn44_20 btnBlue' href='#' style='margin-left:5px' onclick=deleteVideoNews('"
					+ tableData[i].id + "')>删除</a>";
			newPageHtml += "</td>";
			newPageHtml += "</tr>";
		}
		return newPageHtml;
	}
	
	function editVideoNews(id,type) {
		document.location.href = "${StaticResourcePath}/console/waterTransport.xhtml?action=jumpWaterTransportIllegalEdit&id="
				+ escape(id) + "&REPORT_FORM_TYPE="+type;
	}
	
	function deleteVideoNews(Id) {
		asyncbox.confirm("确定删除该条信息?", "信息窗口", function(action) {
			if (action == "ok") {
				$.ajax({
					url : "${StaticResourcePath}/console/waterTransport.xhtml?action=waterTransportIllegalDelete",
					cache : false,
					type : "GET",
					dataType : "json",
					data : {
						"Id" : Id
					},
					success : function(data) {
						asyncbox.alert(data.msg, "信息窗口", function() {
							window.location.reload();
						});
					}
				});
			}
		});
	}
	
</script>
</head>
<body style="background: white;">
	<div class="searchBox">
		<h3>水运工程违规行为</h3>
		<ul>
			<li><label for="title">违法违规行为单位（人员）名称：</label><input type="text"
				id="ILLEGAL_COMPANY_NAME" class="text"> <label for="pressPerson">统一社会信用代码：</label><input
				type="text" id="SOCIOLOGY_CREDIT_CODE" class="text"></li>
			<li><label class="l">创建时间：</label> <input class="Wdate text"
				id="startTime"
				onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy.MM.dd'})"
				size="12" maxlength="30" readonly /> <label>至&nbsp;&nbsp;&nbsp;</label> <input class="Wdate text"
				id="endTime"
				onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy.MM.dd'})"
				size="12" maxlength="30" readonly /> <a
				href="waterTransport.xhtml?action=jumpWaterTransportIllegalEdit&REPORT_FORM_TYPE=1" class="btnBlue77_32">新增(企业)</a><a
				href="waterTransport.xhtml?action=jumpWaterTransportIllegalEdit&REPORT_FORM_TYPE=2" class="btnBlue77_32">新增(人员)</a><a
				onclick="queryData('1','15');" class="btnBlue77_32">查询</a> <input
				type="button" value="查询" style="display: none" id="searchData"
				class="button" onclick="queryData('1','15')" />
		</ul>
	</div>
	<div>
		<table width="100%" align="center" class="infoList"
			style="margin: auto;">
			<tbody>
				<tr bgcolor="FFFFFF">
					<th style='width: 3%;'>序号</th>
					<th style='width: 25%;'>违法违规行为单位（人员）名称</th>
					<th style='width: 14%;'>统一社会信用代码</th>
					<th style='width: 12%;'>项目类型</th>
					<th style='width: 12%;'>报表类型</th>
					<th style='width: 12%;'>填报人</th>
					<th style='width: 12%;'>创建时间</th>
					<th style='width: 10%;'>操作</th>
				</tr>
			<tbody id="mytbody">
			</tbody>
		</table>
		<div>
			<label style="float: left; margin-left: 5px;">&nbsp;&nbsp;&nbsp;共&nbsp;<span
				id="totalCount" style="font-weight: bold;">${paginations.totalCount}</span>&nbsp;条记录
			</label> <label style="float: right; margin-right: 5px;"> <span
				id="firstPage" onclick="getFirstPage()"><a>首页</a> </span>&nbsp; <span
				id="perPage" onclick="getPerPage()"><a>上一页</a> </span>&nbsp; <span
				id="nextPage" onclick="getNextPage()"><a>下一页</a> </span>&nbsp; <span
				id="lastPage" onclick="getLastPage()"><a>尾页</a> </span>&nbsp;&nbsp;页次：
				<span id="pageIndex1" style="color: Red; font-weight: bold;">${paginations.pageIndex}</span>/
				<span id="pageCount1" style="font-weight: bold;">${paginations.pageCount}</span>页&nbsp;&nbsp;
				<span style="color: Red; font-weight: bold;">15</span>条记录/页&nbsp;&nbsp;转到第
				<input id="jumpPage" type="text" size="4"
				value="${paginations.pageIndex}" />页 <input id='jump' name=''
				type='image' src='${StaticResourcePath}/ww/images/go.gif'
				onclick='freeJump();return false;'> <input type="hidden"
				id="pageIndex" value="${paginations.pageIndex}"></input> <input
				type="hidden" id="pageCount" value="${paginations.pageCount}"></input>
			</label>
		</div>
	</div>
</body>
</html>