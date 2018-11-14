<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %><%@ 
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
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
</style>
<title>政务信息刊物管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		信息报送
		<span>-></span>
		政务信息刊物管理
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
        <!-- 	<div style="margin-bottom: 10px;"> -->
        		<label>刊物名称：</label><input type="text" name="pubName" class="input-text" value="${pubName }">
        		<label>创建人：</label><input type="text" name="createUser" class="input-text" value="${createUser }">
                <label>创建时间：</label>
                <div class="data" id="dataSelect-start">
                    <input type="text"  name="createDateS" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
                </div>
                <span>&nbsp;～</span>
                <div class="data" id="dataSelect-end">
                	<input type="text" name="createDateE" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput" >
                </div>
                <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">搜  索</a>
                <a href="govTextNews.xhtml?face=addPubInfoView" class="btn btn-blue">新  增</a>  
        	</div>
     </div>
     <!--column01 End-->
     <!--column02 Start-->
     <div class="column" >
          <div class="title"><h2><b class="icon i-search"></b>刊物列表</h2></div>
            <div class="con">
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>                    
                    <td width="10%">序号</td>
                    <td width="30%">刊物名称</td>
                    <td width="10%">状态</td>
                    <td width="10%">创建人</td>
                    <td width="20%">创建时间</td>
                    <td width="10%">编辑</td>
                    <td width="10%">删除</td>
                  </tr>
                </thead>
                <tbody id="tbodyNews">
                <c:forEach items="${paginations}" var="tn" varStatus="status">
					<tr>
						<td>${tn.rowNo}</td>
						<th><a href="../console/govTextNews.xhtml?face=managePubInfoView&pubId=${tn.pubId}" ><span style='color:blue'>${tn.pubName}</span></a></th>
                        <td><c:if test="${tn.isPublic=='0'}">草稿</c:if>
							<c:if test="${tn.isPublic=='1'}">已发布</c:if></td>
                        <td>${tn.createUser}</td>
                        <td>${tn.createDate}</td>
                        <td><a href="javascript:;" onclick="editPubInfo('${tn.pubId}');return false;" class="icon i-edit"></a></td>
                        <td><a href="javascript:void(0);"  onclick="deletePubInfo('${tn.pubId}')" class="icon i-del-02"></a></td>
					</tr>
				</c:forEach>
			    </tbody>
			</table>
			<div id="pubListPager" class="page clearfix"></div>
        </div>
    </div>
    <!--column02 End-->
</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function(){
	
	var totalCount = $("#totalCount").val();
	var pageSize = $("#pageSize").val();
	var pageIndex = $("#pageIndex").val();
	<c:if test="${!empty paginations[0].totalCount}">
	$("#pubListPager").buildPager({
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
	var pubName = encodeURI(encodeURI($("input[name='pubName']").val()));
	var createUser = encodeURI(encodeURI($("input[name='createUser']").val()));
	var createDateS =  $("input[name='createDateS']").val();
	var createDateE =  $("input[name='createDateE']").val();
	//var entryDateS = entryDateS1.split("").reverse().join("");
	
	var pageIndex = $("#pubListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="govTextNews.xhtml?face=queryPubInfoView&pubName="+pubName+"&createUser="+createUser+"&createDateS="
	+createDateS+"&createDateE="+createDateE+"&pageIndex="+pageIndex;
	/* $.ajax({
		url : "govTextNews.xhtml?action=queryPubInfoView",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
			"pubName" : pubName,
			"createUser" : createUser,
		    "createDateS" : createDateS,
		    "createDateE" : createDateE,
		    "pageIndex" : pageIndex
		},
		success : function(data) {
			var json = $.parseJSON(data);
			
			$("#totalCount").attr("value",json.totalCount);
			$("#pageSize").attr("value",json.pageSize);
			$("#pageIndex").attr("value",json.pageIndex);
			
			var textNewsList="";
			    $.each(json.pageData, function(i, n) {
			    	textNewsList+= "<tr>"+
			    	             "<td>"+(i+1)+"</td>"+
                                 "<th><a href='../console/govTextNews.xhtml?face=managePubInfoView&pubId="+
                                 n.pubId+"' ><span style='color:blue'>"+
                                 n.pubName+"</span></a></th>"+
                                 "<td>"+n.createUser+"</td>"+
                                 "<td>"+n.createDate+"</td>"+
                                 "<td><a href='javascript:;' onclick=editPubInfo('"+n.pubId+"');return false; class='icon i-edit'></a></td>"+
                                 "<td><a href='javascript:;'  onclick=deletePubInfo('"+n.pubId+"');return false; class='icon i-del'></a></td>"
			    });
			  $("#tbodyNews").html("");
			  $("#tbodyNews").html(textNewsList);
			},
			error :function(data){
				alert("查询数据失败");
			}
		}); */
}


//删除信息
function deletePubInfo(pubId) {
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) { 
		if (action == "ok") {
			$.ajax({
				url : "govTextNews.xhtml?action=deletePubInfo",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"pubId" : pubId
				},
				success : function(content) {
					/* asyncbox.alert(content, "信息窗口", function() {
						document.location.reload();
					}); */
					document.location.reload();
				}
			});
		}
	})
}
//编辑
function editPubInfo(pubId){
	location.href ="govTextNews.xhtml?face=managePubInfoView&pubId="+pubId;
}
</script>
</html>
