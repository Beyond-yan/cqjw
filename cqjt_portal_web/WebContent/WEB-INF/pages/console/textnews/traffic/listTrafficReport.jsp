<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib prefix="ut" uri="/WEB-INF/userTag/Pager.tld"%><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>交通报信息查询</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div>
	<title>交通报信息查询</title>
	<!--path Start-->
	<div class="path">
		<b class="icon i-home"></b> <span>当前位置：</span> 信息报送 <span>-></span>
		交通报信息查询
	</div>
	<!--path end-->

	<!--column01 Start-->
	<div class="column" id="search">
		<div class="title">
			<h2>
				<b class="icon i-search"></b>快速搜索
			</h2>
		</div>
		<div class="con">
				<label>交通报标题：</label><input type="text" class="input-text" name="newsTitle" value="${newsTitle}">
				<label>投稿人：</label><input type="text" class="input-text" name="entryUser" value="${entryUser}"> 
				<input type="hidden" class="input-text" name="newsTagsStr">
				<label>发布时间：</label>
				<div class="data" id="dataSelect-start">
					 <input type="text"  name="entryDateS" class="input-text tcal tcalInput" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>'>
				</div>
				<span>&nbsp;～</span>
				<div class="data" id="dataSelect-end">
					<input type="text" name="entryDateE" class="input-text tcal tcalInput" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>'>
				</div>
				 <a href="javascript:;"  onclick="searchList();return false;" class="btn btn-blue">搜  索</a>
		</div>
	</div>
	<!--column01 End-->
	<!--column02 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>信息列表</h2></div>
        <div class="con">	
			<table width="100%" border="1" class="dataList">
				<thead>
					<tr>
						<td width="5%">序号</td>
						<td width="40%">主标题</td>
						<td width="7%">滚动显示</td>
						<td width="8%">状态</td>
						<td width="15%">投稿部门</td>
						<td width="8%">投稿人</td>
						<td width="7%">删除</td>
					</tr>
				</thead>
				<tbody id="tbodyNews">
					<c:forEach items="${paginations}" var="n" varStatus="status">
						<tr>
							<td>${n.rowNo}</td>
							<th><a href="javascript:;" onclick="editTextNews('${n.newsId}');return false;"><span style='color:blue'>${n.newsTitle}</span></a></th>
							<td>
								<c:if test='${n.isPhotosShow==1}'>是</c:if><c:if test='${n.isPhotosShow==0}'>否</c:if>
							</td>
							<td>
								<c:choose>
									<c:when test='${n.govUseFlag==1}'>政务采编</c:when>
									<c:when test='${n.govUseFlag==0&&(n.flag==1||n.flag==2||n.flag==3)}'>已投稿</c:when>
									<c:otherwise>草稿</c:otherwise>
								</c:choose>
							</td>
							
							<td>${n.deptName}</td>
							<td>${n.entryUser}</td>
							<td><a href="javascript:;" onclick="deleteTrafficReport('${n.newsId}','${tn.outerNewsId}');return false;" class="icon i-del-02"></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="userListPager" class="page clearfix"></div>
		</div>
	</div>
	<!--column02 End-->
</div>
</body>

<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript">
	$(function(){
		<c:if test="${!empty paginations[0]}">
		$("#userListPager").buildPager({
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
	function searchList() {
		var newsTitle = encodeURI(encodeURI($("input[name='newsTitle']").val()));
		newsTitle = newsTitle.replace(/#/g,"%23");
		var entryUser = encodeURI(encodeURI($("input[name='entryUser']").val()));
		var entryDateS =  $("input[name='entryDateS']").val();
		var entryDateE =  $("input[name='entryDateE']").val();
		var pageIndex = $("#userListPager").attr("curPage");
		var newsTagsStr ="TrafficPaper";
		if (typeof pageIndex == "undefined") {
			pageIndex = 0;
		}
		location.href ="textNews.xhtml?action=queryTraffictView&newsTitle="+newsTitle+"&entryUser="+entryUser+"&entryDateS="
		+entryDateS+"&entryDateE="+entryDateE+"&pageIndex="+pageIndex+"&newsTagsStr="+newsTagsStr;
		//var entryDateS = entryDateS1.split("").reverse().join("");
		/* $.ajax({
			url : "textNews.xhtml?action=queryTraffictView",
			cache : false,
			type : "post",
			dataType : "text",
			data : {
				"newsTitle" : newsTitle,
				"entryUser" : entryUser,
			    "entryDateS" : entryDateS,
			    "entryDateE" : entryDateE,
			    "pageIndex" : pageIndex,
			    "newsTagsStr":newsTagsStr
			},
			success : function(data) {
				 var json = $.parseJSON(data);
			   //拼接Json
				var textNewsList="";
				    $.each(json.pageData, function(i, n) {
				      var newsId = n.newsId ;
				      var govUseFlag =n.govUseFlag;
				      var flag = n.flag;
				      var isShow = n.isPhotosShow; 
				      var flagStr = "";
				      var show = "";
				      if(govUseFlag==1){
				    		flagStr = "政务采编";
				    	}else{
				    		if(flag==0){
				    			flagStr = "草稿";
				    		}else{
				    			flagStr = "已投稿";
				    		}
				    	}
				    	if(isShow==1){
				    		show = "是";
				    	}else{
				    		show = "否";
				    	}
				    	textNewsList+= "<tr>"+
				    	             // "<td><a href='javascript:;' class='icon i-edit'></a></td>"+ 
				    	             "<td>"+(i+1)+"</td>"+
	                                 "<th><a href='javascript:;' onclick=editTextNews('"+newsId+"');return false;'>"+"<span style='color:blue'>"+n.newsTitle+"</span></th>"+
	                                 "<td>"+show+"</td>"+ 
	                                 "<td>"+flagStr+"</td>"+
	                                 "<td>"+n.deptName+"</td>"+
	                                 "<td>"+n.entryUser+"</td>"+
	                                 "<td><a href='javascript:;' onclick=deleteTrafficReport('"+newsId+"');return false; class='icon i-del'></a></td></tr>"
				    });
				  $("#tbodyNews").html("");
				  $("#tbodyNews").html(textNewsList);
				},
				error :function(data){
					
				}
			}); */
	}
	
	//删除信息
	function deleteTrafficReport(newsId,outerNewsId) {
		asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) { 
			if (action == "ok") {
				$.ajax({
					url : "textNews.xhtml?action=deleteInfo",
					cache : false,
					type : "GET",
					dataType : "text",
					data : {
						"outerNewsId" : outerNewsId,
						"newsId" : newsId
					},
					success : function(content) {
						/* asyncbox.alert("删除成功！", "信息窗口", function() {
							document.location.reload();
						}); */
						document.location.reload();
					}
				});
			}
		})
	}
	//编辑
	function editTextNews(newsId){
		location.href ="textNews.xhtml?face=editView&newsId="+newsId;
	}
	
	</script>
</html>
