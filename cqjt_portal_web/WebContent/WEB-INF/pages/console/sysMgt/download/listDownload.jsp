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
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
</style>
<title>资料管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		系统管理
        <span>-></span>
		资料管理
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
        <!-- 	<div style="margin-bottom: 10px;"> -->
        		<label>资料名称：</label><input type="text" name="fileName" class="input-text" value="${fileName}">
        		<label>创建时间：</label>
                <div class="data" id="dataSelect-start">
                    <input type="text"  name="startDate" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
                </div>
                <span>&nbsp;～</span>
                <div class="data" id="dataSelect-end">
                	<input type="text" name="endDate" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput" >
                </div>
                <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">搜  索</a>
                <a href="download.xhtml?face=addFile" class="btn btn-blue">新  增</a>
        	</div>
     </div>
     <!--column01 End-->
     <!--column02 Start-->
     <div class="column" >
          <div class="title"><h2><b class="icon i-search"></b>资料列表</h2></div>
            <div class="con">
                <input type="hidden"  id="totalCount" value="${paginations[0].totalCount}" />
                <input type="hidden" id="pageSize" value="${paginations[0].pageSize}" />
                <input type="hidden" id="pageIndex" value="${paginations[0].pageIndex}" />
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>                    
                    <td width="5%">序号</td>
                    <td width="20%">资料名称</td>
                    <td width="20%">附件</td>
                    <td width="7%">文件大小</td>
                    <td width="8%">上传人</td>
                    <td width="13%">创建时间</td>
                    <td width="17%">备注</td>
                    <td width="5%">编辑</td>
                    <td width="5%">删除</td>
                  </tr>
                </thead>
		               <tbody id="tbodyFile">
		               <c:forEach items="${paginations}" var="file" varStatus="status">
						<tr>
							<td>${file.rowNo}</td>
		                       <td><a href="../console/download.xhtml?face=editFile&fileId=${file.fileId}" ><span style='color:blue'>${file.fileName}</span></a></td>
		                       <td>${file.attachment}</td>
		                       <td>${file.fileSize}</td>
		                       <td>${file.createBy}</td>
		                       <td>${file.createDate}</td>
		                       <td>${file.fileDesc}</td>
		                       <td><a href="javascript:;" onclick="editFile('${file.fileId}');return false;" class="icon i-edit"></a></td>
		                       <td><a href="javascript:;"  onclick="deleteFile('${file.fileId}');return false;" class="icon i-del"></a></td>
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

function jumpToPage(pIndex) {
	//alert(pIndex);
}

function searchList() {
	var fileName = encodeURI(encodeURI($("input[name='fileName']").val()));
	var startDate = $("input[name='startDate']").val();
	var endDate = $("input[name='endDate']").val();
	var pageIndex = $("#textInfoListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 1;
	}
	location.href ="download.xhtml?face=searchFileList&fileName="+fileName+"&startDate="+startDate+"&endDate="
	+endDate+"&pageIndex="+pageIndex;
	
	/* $.ajax({
		url : "download.xhtml?action=searchFileList",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
			"fileName" : fileName,
			"startDate" : startDate,
			"endDate" : endDate,
		    "pageIndex" : pageIndex
		},
		success : function(data) {
			var json = $.parseJSON(data);
			var pageIndex=json.pageIndex;
			var pageSize=json.pageSize;
			var totalCount =json.totalCount;
			var fileList="";
			var attachment="",fileSize="";
		    $.each(json.pageData, function(i, n) {
		    	attachment=n.attachment;
		    	fileSize=n.fileSize;
		    	if(attachment==""||attachment==null){
		    		attachment="";
		    	}
		    	if(fileSize==""||fileSize==null){
		    		fileSize="";
		    	}
		    	fileList+= "<tr>"+
		                 	"<td>"+(i+1)+"</td>"+			    	             
                                "<td><a href='../console/download.xhtml?face=editFile&fileId="+
                                n.fileId+"' ><span style='color:blue'>"+
                                n.fileName+"</span></a></td>"+
                                "<td>"+attachment+"</td>"+
                                "<td>"+fileSize+"</td>"+ 
                                "<td>"+n.createBy+"</td>"+
                                "<td>"+n.createDate+"</td>"+
                                "<td>"+n.fileDesc+"</td>"+
                                "<td><a href='javascript:;' onclick=editFile('"+n.fileId+"');return false; class='icon i-edit'></a></td>"+
                                "<td><a href='javascript:;'  onclick=deleteFile('"+n.fileId+"');return false; class='icon i-del'></a></td></tr>"
		    });
			$("#tbodyFile").html("");
			$("#tbodyFile").html(fileList);
			$("#textInfoListPager").buildPager({
				totalLines:totalCount,
				pageSize:pageSize,
				startIndex:pageIndex,
				displayNum:5,
				afterChange:function() {
					searchList();
				}
			});
			},
			error :function(data){
				alert("查询数据失败");
			}
		});  */
}


//删除信息
function deleteFile(fileId) {
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) { 
		if (action == "ok") {
			$.ajax({
				url : "download.xhtml?action=deleteFile",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"fileId" : fileId
				},
				success : function(content) {
					/*
					asyncbox.alert( "删除成功！", "信息窗口", function() {
						document.location.reload();
					});*/
					document.location.reload();
				}
			});
		}
	})
}

//编辑
function editFile(fileId){
	location.href ="download.xhtml?face=editFile&fileId="+fileId;
}

</script>
</html>
