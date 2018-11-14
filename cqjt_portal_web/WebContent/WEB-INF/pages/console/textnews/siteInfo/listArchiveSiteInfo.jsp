<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ 
	taglib prefix="ut" uri="/WEB-INF/userTag/Pager.tld"%>
	<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><!DOCTYPE html 
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
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
</style>
<title>网站信息查询</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		网站信息
        <span>-></span>
		网站信息归档
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
        		<label>标题：</label><input type="text" name="newsTitle" class="input-text" value="${newsTitle}">
        		<label>投稿人：</label><input type="text" name="entryUser" class="input-text" value="${entryUser}">
        		<label></label>
        		<select class="select-small" name="categorys" id="categorys" style="width:110px">
		        	<option value="">--请选择类型--</option>
		           	<option value="交通要闻" ${newsCategory == '交通要闻' ? 'selected="selected"':'' }>政务信息</option>
	            </select>   
                <label>投稿时间：</label>
                <div class="data" id="dataSelect-start">
                    <input type="text"  name="entryDateS" class="input-text tcal" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>'>
                </div>
                <span>&nbsp;～</span>
                <div class="data" id="dataSelect-end">
                	<input type="text" name="entryDateE" class="input-text tcal" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>'>
                </div>
                <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">搜索</a>
                <!-- <a href="textNews.xhtml?face=addView" class="btn btn-blue">新  增</a> -->
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
                    <td width="15%">投稿时间</td>
                    <td width="10%">投稿人</td>
                    <td width="5%">排序</td>
                    <td width="5%">编辑</td>
                     <td width="5%">删除</td>
                  </tr>
                </thead>
                <tbody id="tbodyNews">
                <c:forEach items="${paginations}" var="tn" varStatus="status">
                    <c:if test='${tn.flag==9}'><tr style="background: #dcac6c"></c:if>
                    <c:if test='${tn.flag!=9}'><tr></c:if>
						<td>${tn.rowNo}</</td>
						<th><a  href="javascript:;" onclick="editSite('${tn.newsId}','${tn.flag}');return false;"target="_blank>"><span style='color:blue'>${tn.newsTitle}</span></a></th>
						<!-- <th><a href="../guest/news.xhtml?face=newsDetail&newsId=${tn.newsId}" target="_blank>"><span style='color:blue'>${tn.newsTitle}</span></a></th> -->
                        <td><c:if test='${tn.isPhotosShow==1}'>是</c:if><c:if test='${tn.isPhotosShow==0}'>否</c:if></td>
                        <td>
							<c:if test='${tn.flag==3&&tn.isPublic==0}'>归档</c:if>
							<c:if test='${tn.flag==3&&tn.isPublic==1}'>归档（校编上外网）</c:if>
							<c:if test='${tn.flag==8}'>归档</c:if>
							<c:if test='${tn.flag==9}'>归档（上外网）</c:if>
						<!-- <c:if test='${tn.flag==4}'>待校编</c:if>-->
						</td>
                        <td ><fmt:formatDate value="${tn.entryDate}" type="both"/></td>
                        <td>${tn.entryUser}</td>
                        <td><input type="text" style="width:20px" value="${tn.newsSort}" onchange="updateNewsSort('${tn.newsId}',this)"/></td>
                        <td><a  href="javascript:;" onclick="editSite('${tn.newsId}','${tn.flag}');return false;" class="icon i-edit"></a></td>
                        <td><a  href="javascript:;" onclick="deleteSite('${tn.newsId}','15','${tn.outerNewsId}');return false;" class="icon i-del"></a></td>
     			  </tr>
				</c:forEach>
			    </tbody>
			</table>
			<div id="SiteNewsPager" class="page clearfix"></div>
        </div>
    </div>
    <!--column02 End-->
</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function(){

	<c:if test="${!empty paginations[0].totalCount}">
	$("#SiteNewsPager").buildPager({
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
//校编
function editSite(newsId, flag){
	//if (flag=="4")
	//	location.href ="siteTextNews.xhtml?face=editVerifySiteNewsView&newsId="+newsId;
	//else 
		
		location.href ="siteTextNews.xhtml?face=updateVerifySiteNewsView&newsId="+newsId;
}
function searchList() {
	var newsTitle = encodeURI(encodeURI($("input[name='newsTitle']").val()));
	newsTitle = newsTitle.replace(/#/g,"%23");
	var entryUser = encodeURI(encodeURI($("input[name='entryUser']").val()));
	var entryDateS =  $("input[name='entryDateS']").val();
	var entryDateE =  $("input[name='entryDateE']").val();
	var pageIndex = $("#SiteNewsPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	var categorys = encodeURI(encodeURI($("#categorys").val()));
	location.href ="siteTextNews.xhtml?face=queryArchiveSiteView&newsTitle="+newsTitle+"&entryUser="+entryUser+"&entryDateS="
	+entryDateS+"&entryDateE="+entryDateE+"&pageIndex="+pageIndex+"&newsCategory="+categorys;
}

//逻辑删除
function deleteSite(newsId,flag,outerNewsId) {
	
	//alert(newsId);
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) {
		
		if (action == "ok") {
			$.ajax({
				url : "siteTextNews.xhtml?action=deleteSiteInfo",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"newsId" :newsId,
					"outerNewsId" : outerNewsId,
					"flag" :flag
				},
				success : function(data) {			
				/* 	 var data = eval('(' + data + ')');
					asyncbox.confirm(data.msg , "确认窗口", function(action) {
						if (action == "ok") {
							document.location.reload();
						}
					});  */
					document.location.reload();
				},
				error :function(data){
					var data = eval('(' + data + ')');
					asyncbox.confirm(data.msg, "确认窗口", function(action) {
						if (action == "ok") {
							document.location.reload();
						}
					}); 
				}
			});
		}
	})
}

function updateNewsSort(newsId,obj){
	var newsSort = $(obj).val();
	$.ajax({
		url : "siteTextNews.xhtml?action=updateNewsSort",
		cache : false,
		type : "GET",
		dataType : "text",
		data : {
			"newsId" :newsId,
			"newsSort" : newsSort
		},
		success : function(data) {	
			var data = eval('(' + data + ')');
			asyncbox.alert(data.msg)
			searchList();
		},
		error :function(data){
			var data = eval('(' + data + ')');
			asyncbox.alert(data.msg)
		}
	});
}

</script>
</html>
