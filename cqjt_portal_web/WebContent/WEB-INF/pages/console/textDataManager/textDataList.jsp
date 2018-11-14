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
<title>文字类信息管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		内容管理
        <span>-></span>
		文字类信息管理
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
       		<label>标题：</label><input type="text" name="title" class="input-text" value="${title}">
       		<label>作者：</label><input type="text" name="author" class="input-text" value="${author}">
       		<label>创建时间：</label>
               <div class="data" id="dataSelect-start">
                   <input type="text"  name="startDate" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
               </div>
               <span>&nbsp;～</span>
               <div class="data" id="dataSelect-end">
               	<input type="text" name="endDate" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput" >
               </div>
               <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">搜  索</a>
               <a href="textData.xhtml?face=addTextData" class="btn btn-blue">新  增</a>
       	</div>
     </div>
     <!--column01 End-->
     <!--column02 Start-->
     <div class="column" >
          <div class="title"><h2><b class="icon i-search"></b>文字类信息列表</h2></div>
            <div class="con">
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>                    
                    <td width="5%">序号</td>
                    <td width="20%">标题</td>
                    <td width="10%">版面位置</td>
                    <td width="10%">状态</td>
                    <td width="10%">文档来源</td>
                    <td width="10%">作者</td>
                    <td width="15%">创建时间</td>
                    <td width="5%">编辑</td>
                    <td width="5%">删除</td>
                    <td width="5%">发布</td>
                  </tr>
                </thead>
		               <tbody id="tbodyColumn">
		               <c:forEach items="${paginations}" var="text" varStatus="status">
						<tr>
							<td>${text.rowNo}</td>
		                       <td>${text.title}</td>
		                       <c:if test="${text.position==2}"><td>普通新闻</td></c:if>
		                       <c:if test="${text.position==1}"><td>头版头条</td></c:if>
		                       <c:if test="${text.position==0}"><td>头版次条</td></c:if>
		                       <c:if test="${text.status==1}"><td>发布</td></c:if>
		                       <c:if test="${text.status==0}"><td>草稿</td></c:if>
		                       <td>${text.textSource}</td>
		                       <td>${text.author}</td>
		                       <td>${text.createDate}</td>
		                       <td><a href="javascript:;" onclick="editColumn('${text.textId}');return false;" class="icon i-edit"></a></td>
		                       <td><a href="javascript:;"  onclick="deleteColumn('${text.textId}');return false;" class="icon i-del-02"></a></td>
		                       <td><a href="javascript:;"  onclick="updateStatus('${text.textId}','${text.status}');return false;" class="icon i-log"></a></td>
						</tr>
					</c:forEach>
				    </tbody>
			</table>
			<div id="columnListPager" class="page clearfix"></div>
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
	$("#columnListPager").buildPager({
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
	var title = encodeURI(encodeURI($("input[name='title']").val()));
	var author = encodeURI(encodeURI($("input[name='author']").val()));
	var startDate = $("input[name='startDate']").val();
	var endDate = $("input[name='endDate']").val();
	var pageIndex = $("#columnListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="textData.xhtml?face=searchTextDataList&title="+title+"&author="+author+"&startDate="
	+startDate+"&endDate="+endDate+"&pageIndex="+pageIndex;
	
	/* $.ajax({
		url : "textData.xhtml?action=searchTextDataList",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
			"title" : title,
			"author" : author,
			"startDate" : startDate,
			"endDate" : endDate,
		    "pageIndex" : pageIndex
		},
		success : function(data) {
			var json = $.parseJSON(data);
			var pageIndex=json.pageIndex;
			var pageSize=json.pageSize;
			var totalCount =json.totalCount;
			var columnList="";
			var position="",status="";
		    $.each(json.pageData, function(i, n) {
		    	position = n.position
		    	if(position==1){
		    		position="左上方";
		    	}else if(position==0){
		    		position="右上方";
		    	}else{
		    		position="";
		    	}
		    	status=n.status;
		    	if(status==1){
		    		status="发布";
		    	}else if(status==0){
		    		status="草稿";
		    	}
		    	columnList+= "<tr>"+
		                 	"<td>"+(i+1)+"</td>"+			    	             
                                "<td>"+n.title+"</td>"+
                                "<td>"+position+"</td>"+ 
                                "<td>"+status+"</td>"+ 
                                "<td>"+n.author+"</td>"+
                                "<td>"+n.createDate+"</td>"+
                                "<td><a href='javascript:;' onclick=editColumn('"+n.textId+"');return false; class='icon i-edit'></a></td>"+
                                "<td><a href='javascript:;' onclick=deleteColumn('"+n.textId+"');return false; class='icon i-del'></a></td>"+
                                "<td><a href='javascript:;' onclick=updateStatus('"+n.textId+"','"+n.status+"');return false; class='icon i-log'></a></td></tr>"
		    });
			$("#tbodyColumn").html("");
			$("#tbodyColumn").html(columnList);
			$("#columnListPager").buildPager({
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
function deleteColumn(textId) {
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) { 
		if (action == "ok") {
			$.ajax({
				url : "textData.xhtml?action=deleteTextData",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"textId" : textId
				},
				success : function(content) {
					//asyncbox.alert( "删除成功！", "信息窗口", function() {
						document.location.reload();
					//}); 

				}
			});
		}
	})
}

//编辑
function editColumn(textId){
	location.href ="textData.xhtml?face=editTextData&textId="+textId;
}
//发布
function updateStatus(textId,status){
	if(status==1){
		asyncbox.alert("已发布信息不能再发布", "信息窗口");
	}else{
		asyncbox.confirm("确定发布该笔信息?", "信息窗口", function(action) { 
			if (action == "ok") {
				$.ajax({
					url : "textData.xhtml?action=updateStatus",
					cache : false,
					type : "GET",
					dataType : "text",
					data : {
						"textId" : textId
					},
					success : function(content) {
						//asyncbox.alert( "删除成功！", "信息窗口", function() {
							document.location.reload();
						//}); 
	
					}
				});
			}
		})
	}
}
</script>
</html>
