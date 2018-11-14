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
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
</style>
<title>报送查询</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		信息报送
        <span>-></span>
		报送查询
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
        <!-- 	<div style="margin-bottom: 10px;"> -->
        		<label id="iii">标题：</label><input type="text" name="newsTitle" class="input-text" value="${newsTitle}">
        		<label>投稿人：</label><input type="text" name="entryUser" class="input-text" value="${entryUser}">
        		<label>状态：</label>
        		<c:if test="${tags == 'GovNews'}">
	        		<select class="input-text" id="flag" style="height: 28px;">
	        			<option name="GovNews" value="1">全部</option>
	        			<option name="GovNews" value="2" <c:if test="${flag=='2'}">selected</c:if>>草稿</option>
	        			<option name="GovNews" value="3" <c:if test="${flag=='3'}">selected</c:if>>未采编</option>
<%-- 	        			<option name="GovNews" value="4" <c:if test="${flag=='4'}">selected</c:if>>采编未成刊</option> --%>
<%-- 	        			<option name="GovNews" value="5" <c:if test="${flag=='5'}">selected</c:if>>采编已成刊</option> --%>
	        			<option name="GovNews" value="5" <c:if test="${flag=='5'}">selected</c:if>>采编已成刊</option>
	        		</select>
        		</c:if>
        		<c:if test="${tags == 'SiteNews'}">
	        		<select class="input-text" id="flag" style="height: 28px;">
	        			<option name="SiteNews" value="1">全部</option>
	        			<option name="SiteNews" value="2" <c:if test="${flag=='2'}">selected</c:if>>草稿</option>
	        			<option name="SiteNews" value="7" <c:if test="${flag=='7'}">selected</c:if>>分拣</option>
	        			<option name="SiteNews" value="3" <c:if test="${flag=='3'}">selected</c:if>>投稿</option>
	        			<option name="SiteNews" value="6" <c:if test="${flag=='6'}">selected</c:if>>归档</option>
	        		</select>
	        	</c:if>
	        	<br/>
	        	<br/>
                <label>发布时间：</label>
                <div class="data" id="dataSelect-start">
                    <input type="text"  name="entryDateS" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
                </div>
                <span>&nbsp;～</span>
                <div class="data" id="dataSelect-end">
                	<input type="text" name="entryDateE" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput" >
                </div>
                <a href="javascript:;" onclick="searchList(0,0);return false;" class="btn btn-blue">搜  索</a>
                <c:if test="${tags eq 'GovNews' }" >
                	<a href="javascript:;" onclick="exportExcel();" class="btn btn-blue">导出excel</a>
                </c:if>
            </div>    
                <!-- <a href="javascript:;" onclick="deleteAll();return false;" class="btn btn-blue">批量删除</a> -->
              <!--   <a href="textNews.xhtml?face=addView" class="btn btn-blue">新  增</a>  -->
        	</div>
     <!--column01 End-->
     <!--column02 Start-->
     <div class="column" >
          <div class="title"><h2><b class="icon i-search"></b>投稿列表</h2></div>
            <div class="con">
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>     
                  	<!-- <td width="2%"><input type='checkbox' name='selAllCheckBox' id='selAll'></td>  -->              
                    <td width="3%">序号</td>
                    <td width="35%">主标题</td>
                    <td width="5%">滚动展示</td>
                    <td width="7%">状态</td>
                    <td width="8%">投稿部门</td>
                    <td width="8%">投稿时间</td>
                    <c:if test="${tags == 'GovNews'}">
                    	<td width="12%">采用类别</td>
                    </c:if>
                    <td width="8%">投稿人</td>
                    <td width="5%">编辑</td>
                    <td width="5%">删除</td>
                    <td width="5%">预览</td>
                  </tr>
                </thead>
		               <tbody id="tbodyNews">
		               <c:forEach items="${paginations}" var="tn" varStatus="status">
						<tr>
							<%-- <td><input type="checkbox" class="checkBox"  name='singleBox' value="${tn.newsId}"/></td> --%>
							<td>${tn.rowNo}</td>	
							<th><a href="../console/textNews.xhtml?face=editView&newsId=${tn.newsId}" ><span style='color:blue'>${tn.newsTitle}</span></a></th>
		                       <td><c:if test='${tn.isPhotosShow==1}'>是</c:if><c:if test='${tn.isPhotosShow==0}'>否</c:if></td>
		                       <td>
		                       	    <c:if test='${tn.flag==7}'>已退稿</c:if>
				                    <c:if test='${tn.flag==3&&tn.isPublic==0}'>归档</c:if>
									<c:if test='${tn.flag==3&&tn.isPublic==1}'>归档（校编上外网）</c:if>
			                        <c:if test='${tn.flag==2}'>分拣</c:if>
			                        <c:if test='${tn.flag==1&&tn.govUseFlag==1}'>采编已成刊</c:if>
			                        <c:if test='${tn.flag==1&&tn.govUseFlag==2}'>采编已成刊</c:if>
			                        <c:if test="${tags == 'GovNews'}">
			                        	<c:if test='${tn.flag==1&&tn.govUseFlag==0}'>未采编</c:if>
			                        </c:if>
			                        <c:if test="${tags == 'SiteNews'}">
			                        	<c:if test='${tn.flag==1&&tn.govUseFlag==0}'>投稿</c:if>
			                        </c:if>
			                        <c:if test='${tn.flag==1&&tn.govUseFlag==3}'>采编已成刊</c:if>
			                        <c:if test='${tn.flag==0}'>草稿</c:if>
			                        <c:if test='${tn.flag==8}'>归档</c:if>
			                        <c:if test='${tn.flag==9}'>归档（上外网）</c:if>
		                       </td>
		                       <td>${tn.deptName}</td>
		                       <td><fmt:formatDate value="${tn.entryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                        <c:if test="${tags == 'GovNews'}">
		                        	<td>${tn.adoptType}</td>
		                        </c:if>
		                       <td>${tn.entryUser}</td>
		                       <td><a href="javascript:;" onclick="editTextNews('${tn.newsId}');return false;" class="icon i-edit"></a></td>
		                       <td><a href="javascript:;"  onclick="deleteTextNews('${tn.newsId}','${tn.outerNewsId}');return false;" class="icon i-del-02"></a></td>
		                       <td><a href="../guest/news.xhtml?face=newsDetail&newsId=${tn.newsId}" target="_blank" class="icon i-log"></a></td>
						</tr>
					</c:forEach>
				    </tbody>
			</table>
			<div id="textInfoListPager" class="page clearfix"></div>
			<input type="hidden" name="tags" value="${tags}">
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
			searchList(${paginations[0].pageIndex},1);
		}
	});
	</c:if>
});
function jumpToPage(pIndex) {
	//alert(pIndex);
}


function exportExcel() {
	var newsTitle = encodeURI(encodeURI($("input[name='newsTitle']").val()));
	newsTitle = newsTitle.replace(/#/g,"%23");
	var entryUser = encodeURI(encodeURI($("input[name='entryUser']").val()));
	var entryDateS =  $("input[name='entryDateS']").val();
	var entryDateE =  $("input[name='entryDateE']").val();
	var tags = $("input[name='tags']").val();
	var flag = $("#flag option:selected").val();
	
	location.href ="textNews.xhtml?face=exportExcel&newsTitle="+newsTitle+"&entryUser="+entryUser+"&entryDateS="
	+entryDateS+"&entryDateE="+entryDateE+"&tags="+tags+"&flag="+flag;	
	
}


function searchList(num,index) {
	var newsTitle = encodeURI(encodeURI($("input[name='newsTitle']").val()));
	newsTitle = newsTitle.replace(/#/g,"%23");
	var entryUser = encodeURI(encodeURI($("input[name='entryUser']").val()));
	var entryDateS =  $("input[name='entryDateS']").val();
	var entryDateE =  $("input[name='entryDateE']").val();
	var pageIndex = num;
	//var entryDateS = entryDateS1.split("").reverse().join("");
	if(index == 1){
		var pageIndex = $("#textInfoListPager").attr("curPage");
	}
		
	var tags = $("input[name='tags']").val();
	var flag = $("#flag option:selected").val();
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="textNews.xhtml?face=searchView&newsTitle="+newsTitle+"&entryUser="+entryUser+"&entryDateS="
			+entryDateS+"&entryDateE="+entryDateE+"&pageIndex="+pageIndex+"&tags="+tags+"&flag="+flag;
	
}
//删除信息
function deleteTextNews(newsId,outerNewsId) {
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
				success : function(data) {
				  /*   var content = eval('(' + data + ')');
					asyncbox.alert(content.msg, "信息窗口", function() {
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

 //全选或取消
$("#selAll").click(function(){
         $('input[name=singleBox]').attr("checked", this.checked );
});
  

//批量删除  
function deleteAll(){
	var arrChk=$("input[name='singleBox']:checked");
	if(arrChk.length>0)
	{
		asyncbox.confirm("确定删除信息?", "信息窗口", function(action) { 
			if(action=='ok'){
				var ids ="";
			    //遍历得到每个checkbox的value值
			    for (var i=0;i<arrChk.length;i++){
			        if(i==0){
			            ids = arrChk[i].value;
			        }else{
			            ids = ids+","+arrChk[i].value;
			        }
			   } 
			    $.ajax({
					url : "textNews.xhtml?action=deleteAll",
					cache : false,
					type : "GET",
					dataType : "text",
					data : {
						"newsIds" : ids
					},
					success : function(data) {
						document.location.reload();
						$("input[name='singleBox']").attr("checked","false");
					}
			    })
			}
		});
	}
    else{
    	asyncbox.alert("请选择要删除的记录！","信息窗口");
    } 
}

</script>
</html>
