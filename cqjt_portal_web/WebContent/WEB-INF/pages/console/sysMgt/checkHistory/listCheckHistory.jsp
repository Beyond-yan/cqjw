<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<!DOCTYPE html 
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
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
</style>
<title>历史考核记录</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		系统管理
        <span>-></span>
		历史考核记录
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
        <!-- 	<div style="margin-bottom: 10px;"> -->
        		<label>考核记录：</label><input type="text" name="checkName" class="input-text" value="${checkName}">
        		<label>创建时间：</label>
                <div class="data" id="dataSelect-start">
                    <input type="text"  name="startDate" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
                </div>
                <span>&nbsp;～</span>
                <div class="data" id="dataSelect-end">
                	<input type="text" name="endDate" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput" >
                </div>
                <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">搜  索</a>
                <a href="checkHistory.xhtml?face=addFile" class="btn btn-blue">新  增</a>
        	</div>
     </div>
     <!--column01 End-->
     <!--column02 Start-->
     <div class="column" >
          <div class="title"><h2><b class="icon i-search"></b>历史考核记录列表</h2></div>
            <div class="con">
                <input type="hidden"  id="totalCount" value="${paginations[0].totalCount}" />
                <input type="hidden" id="pageSize" value="${paginations[0].pageSize}" />
                <input type="hidden" id="pageIndex" value="${paginations[0].pageIndex}" />
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>                    
                    <td width="5%">序号</td>
                    <td width="20%">考核记录</td>
                    <td width="20%">附件</td>
                    <td width="10%">上传人</td>
                    <td width="15%">创建时间</td>
                    <td width="20%">备注</td>
                    <td width="5%">编辑</td>
                    <td width="5%">删除</td>
                  </tr>
                </thead>
		               <tbody id="tbodyFile">
		               <c:forEach items="${paginations}" var="file" varStatus="status">
						<tr>
							<td>${file.rowNo}</td>
		                       <td><a href="../console/checkHistory.xhtml?face=editFile&checkId=${file.checkId}" ><span style='color:blue'>${file.checkName}</span></a></td>
		                       <td>${file.attachment}</td>
		                       <td>${file.createBy}</td>
		                       <td>${file.createDate}</td>
		                       <td>${file.checkDesc}</td>
		                       <td><a href="javascript:;" onclick="editFile('${file.checkId}');return false;" class="icon i-edit"></a></td>
		                       <td><a href="javascript:;"  onclick="deleteFile('${file.checkId}');return false;" class="icon i-del"></a></td>
						</tr>
					</c:forEach>
				    </tbody>
			</table>
			<div id="textInfoListPager" class="page clearfix"></div>
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
	<c:if test="${!empty paginations[0].totalCount}">
	$("#textInfoListPager").buildPager({
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
	var checkName = encodeURI(encodeURI($("input[name='checkName']").val()));
	var startDate = $("input[name='startDate']").val();
	var endDate = $("input[name='endDate']").val();
	var pageIndex = $("#textInfoListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 1;
	}
	location.href ="checkHistory.xhtml?face=searchCheckHistoryList&checkName="+checkName+"&startDate="+startDate+"&endDate="
	+endDate+"&pageIndex="+pageIndex;
}


//删除信息
function deleteFile(fileId) {
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) { 
		if (action == "ok") {
			$.ajax({
				url : "checkHistory.xhtml?action=deleteFile",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"checkId" : fileId
				},
				success : function(content) {
					document.location.reload();
				}
			});
		}
	})
}

//编辑
function editFile(fileId){
	location.href ="checkHistory.xhtml?face=editFile&checkId="+fileId;
}

</script>
</html>
