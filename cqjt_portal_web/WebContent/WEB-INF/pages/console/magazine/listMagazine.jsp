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
<title>杂志刊物信息查询</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
	内容管理
        <span>-></span>
		杂志刊物信息查询
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
        <!-- 	<div style="margin-bottom: 10px;"> -->
        		<label>标题：</label><input type="text" name="title" class="input-text" value="${title}">
        		<label>投稿人：</label><input type="text" name="entryUser" class="input-text" value="${entryUser}">
                <label>投稿时间：</label>
                <div class="data" id="dataSelect-start">
                    <input type="text"  name="entryDateS" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
                </div>
                <span>&nbsp;～</span>
                <div class="data" id="dataSelect-end">
                	<input type="text" name="entryDateE" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput" >
                </div>
                <a href="javascript:;" onclick="selectList();return false;" class="btn btn-blue">搜  索</a>
              <a href="magazine.xhtml?face=addMagazine" class="btn btn-blue">新  增</a> 
        	</div>
     </div>
     <!--column01 End-->
     <!--column02 Start-->
     <div class="column" >
          <div class="title"><h2><b class="icon i-search"></b>投稿列表</h2></div>
            <div class="con">
                <input type="hidden"  id="totalCount" value="${paginations[0].totalCount}" />
                <input type="hidden" id="pageSize" value="${paginations[0].pageSize}" />
                <input type="hidden" id="pageIndex" value="${paginations[0].pageIndex}" />
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>                    
                    <td width="5%">序号</td>
                    <td width="40%">标题</td>
                    <td width="8%">状态</td>
                    <td width="8%">投稿人</td>
                    <td width="5%">编辑</td>
                    <td width="7%">删除</td>
                    <td width="7%">审核</td>
                  </tr>
                </thead>
		               <tbody id="tbodyNews">
		               <c:forEach items="${paginations}" var="tn" varStatus="status">
						<tr>
							<td>${tn.rowNo}</td>	
							<td>${tn.title}</td>
                       <td><c:if test='${tn.flag==1}'>投稿</c:if>
					     <c:if test='${tn.flag==0}'>草稿</c:if>
					     <c:if test='${tn.flag==2}'>已审核</c:if></td><td>${tn.entryUser}
                       </td>
		                 <td><a href="javascript:;" onclick="editMagazine('${tn.magazineId}');return false;" class="icon i-edit"></a></td>
		                 <td><a href="javascript:;"  onclick="deleteMagazine('${tn.magazineId}');return false;" class="icon i-del-02"></a></td>
                         <td><a href="javascript:;"  onclick="verifyMagazine('${tn.magazineId}');return false;" class="icon i-edit"></a></td>
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
			selectList();
		}
	});
	</c:if>
});

function jumpToPage(pIndex){
	//alert(pIndex);
}
function selectList(){
 	var title = encodeURI(encodeURI($("input[name='title']").val()));
	var entryUser = encodeURI(encodeURI($("input[name='entryUser']").val()));
	var entryDateS =  $("input[name='entryDateS']").val();
	var entryDateE =  $("input[name='entryDateE']").val();
	var pageIndex = $("#textInfoListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="magazine.xhtml?face=searchMagazineList&title="+title+"&entryUser="+entryUser+"&entryDateS="
	+entryDateS+"&entryDateE="+entryDateE+"&pageIndex="+pageIndex;
	/* $.ajax({
		url : "magazine.xhtml?action=searchMagazineList",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
			"title" : title,
			"entryUser" : entryUser,
		    "entryDateS" : entryDateS,
		    "entryDateE" : entryDateE,
		    "pageIndex" : pageIndex
		},
		success : function(data) {
			var json = $.parseJSON(data);
			var pageIndex=json.pageIndex;
			var pageSize=json.pageSize;
			var totalCount =json.totalCount;
			var textNewsList="";
			    $.each(json.pageData, function(i, n) {
			    	var flag = n.flag;
			    	var flagStr = "";
			    	if(flag==1){
			    		flagStr = "投稿";
			    	}else if(flag==0){
			    		flagStr = "草稿";
			    	}else if(flag==2){
			    		flagStr = "已审核";
			    	}
			    	textNewsList+= "<tr>"+
			             	"<td>"+(i+1)+"</td>"+			    
                    		"<td>"+n.Title+"</td>"+
                     		"<td>"+flagStr+"</td>"+  
                     		"<td>"+n.entryUser+"</td>"+
 							"<td><a href='javascript:;' onclick=editMagazine('"+n.magazineId+"');return false; class='icon i-edit'></a></td>"+ 
							"<td><a href='javascript:;' onclick=deleteMagazine('"+n.magazineId+"');return false; class='icon i-del'></a></td>"+
							"<td><a href='javascript:;' onclick=verifyMagazine('"+n.magazineId+"');return false; class='icon i-edit'></a></td></tr>"
						
			    });
			  $("#tbodyNews").html("");
			  $("#tbodyNews").html(textNewsList);
			},
			error :function(data){
				alert("查询数据失败");
			}
		});  */
}
function deleteMagazine(magazineId) {
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) { 
		if (action == "ok") {
			$.ajax({
				url : "magazine.xhtml?action=deleteMagazine",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"magazineId" : magazineId
				},
				success : function(data) {
					document.location.reload();
				}
			});
		}
	}) 
}
function editMagazine(magazineId){
	location.href ="magazine.xhtml?face=editMagazine&magazineId="+magazineId;
}
function verifyMagazine(magazineId){
	location.href ="magazine.xhtml?face=verifyMagazine&magazineId="+magazineId;
}

</script>
</html>
