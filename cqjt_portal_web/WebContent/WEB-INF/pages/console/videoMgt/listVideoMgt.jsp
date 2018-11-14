<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><%@ 
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
<title>视频管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		内容管理
        <span>-></span>
		视频管理
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
        			<label>标题：</label><input type="text" name="videoTitle" class="input-text" value="${videoTitle}">
        		<label>投稿人：</label><input type="text" name="entryUser" class="input-text" value="${entryUser}">
                <label>创建时间：</label>
                <div class="data" id="dataSelect-start">
                    <input type="text"  name="entryDateS" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal">
                </div>
                <span>&nbsp;～</span>
                <div class="data" id="dataSelect-end">
                	<input type="text" name="entryDateE" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>'  class="input-text tcal" >
                </div>
                <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">搜 索</a>
                <a href="videoMgt.xhtml?face=addVideoMgtView" class="btn btn-blue">新  增</a> 
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
                    <td width="20%">文档标题</td>
                    <td width="15%">时间</td>
                    <td width="8%">状态</td>
                    <td width="8%">投稿人</td>
                    <td width="5%">编辑</td>
                    <td width="5%">删除</td>
                  </tr>
                </thead>
                <tbody id="tbodyVideo">
                <c:forEach items="${paginations}" var="tn" varStatus="status">
					<tr>
						<td>${tn.rowNo}</</td>
                        <th><a href="javascript:;" onclick="editVideo('${tn.videoId}');return false;"><span style='color:blue'>${tn.videoTitle}</span></a></th>      
                        <td>${tn.createDate}</td>
                       <td>
                            <c:if test='${tn.flag==3}'>已撤销</c:if>
	                        <c:if test='${tn.flag==1}'>已投稿</c:if>
	                        <c:if test='${tn.flag==2}'>已审核</c:if>
	                        <c:if test='${tn.flag==0}'>草稿</c:if>
                        </td>
                        <td>${tn.entryUser}</td>
                        <td><a href="javascript:;" onclick="editVideo('${tn.videoId}');return false;"class="icon i-edit"></a></td>
                        <td><a  href="javascript:;" onclick="deleteVideo('${tn.videoId}','${tn.flag}');return false;" class="icon i-del-02"></a></td>          
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
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function(){
	<c:if test="${!empty paginations[0].totalCount}">
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
	var videoTitle = encodeURI(encodeURI($("input[name='videoTitle']").val()));
	var entryUser = encodeURI(encodeURI($("input[name='entryUser']").val()));
	var entryDateS =  $("input[name='entryDateS']").val();
	var entryDateE =  $("input[name='entryDateE']").val();
	var pageIndex = $("#userListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="videoMgt.xhtml?face=searchFileList&videoTitle="+videoTitle+"&entryUser="+entryUser+"&entryDateS="
	+entryDateS+"&entryDateE="+entryDateE+"&pageIndex="+pageIndex;
}

function deleteVideo(videoId,flag) {
	var msg ="";
	if (flag==2){
		msg="此视频将不在首页显示,确认是否撤销?";
	}
	else {
		msg="确定是否删除此信息?";
		}	
	asyncbox.confirm( msg, "信息窗口", function(action) {		
		if (action == "ok") {
			$.ajax({
				url : "videoMgt.xhtml?action=deleteVideo",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"videoId" :videoId
				},
				success : function(content) {
					/*
					asyncbox.alert("删除成功！", "信息窗口", function() {
						document.location.reload();
					});
					*/
					document.location.reload();
				}
			});
		}
	})
}
function editVideo(videoId){
	location.href ="videoMgt.xhtml?face=editVideo&videoId="+videoId;
}

</script>
</html>
