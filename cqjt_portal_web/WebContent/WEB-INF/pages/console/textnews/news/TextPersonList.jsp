<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
        <div class="title">
        
        <h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
        	<table width="100%">
        		<tr>
        			<td>    
 						<a style="position: relative;right:0px;" href="textPerson.xhtml?face=addPersonView" class="btn btn-blue">新  增</a>
						<label id="iii">标题：</label><input type="text" name="newsTitle" class="input-text inputs" value="${newsTitle}">
						<label>任免分类：</label>
		        		<select class="input-text" id="personCat"  name="SUB_CATEGORY" style="height: 28px;">
		        			<option value="">-= 请选择  =-</option>
		        			<option value="交通运输部领导" <c:if test="${PersonCat=='交通运输部领导'}">selected</c:if>>交通运输部领导</option>
		        			<option value="委系统市管干部" <c:if test="${PersonCat=='委系统市管干部'}">selected</c:if>>委系统市管干部</option>
		        			<option value="委管干部" <c:if test="${PersonCat=='委管干部'}">selected</c:if>>委管干部</option>
		        			<option value="区县交委" <c:if test="${PersonCat=='区县交委'}">selected</c:if>>区县交委</option>
		        			<option value="其他" <c:if test="${PersonCat=='其他'}">selected</c:if>>其他</option>
		        		</select> 
					</td>
        			<td>	
					</td>
        		</tr>
        		<tr>
        			<td style="padding-top: 5px">
			        		<label>发文日期：</label>
			                <div class="data" id="dataSelect-start">
			                    <input type="text"  name="entryDateS" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
			                </div>
			                <span>&nbsp;～</span>
			                <div class="data" id="dataSelect-end">
			                	<input type="text" name="entryDateE" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput" >
			                </div>
			        		<a href="javascript:;" onclick="searchList(0,0);return false;" class="btn btn-blue">搜  索</a>
					</td>
        			<td>
					</td>
        		</tr>
        	</table>
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
                    <td width="37%">主标题</td>
                    <td width="10%">任免分类</td>
                    <td width="8%">发文日期</td>
                     
                    <td width="5%">编辑</td>
                    <td width="5%">删除</td>
                  </tr>
                </thead>
		               <tbody id="tbodyNews">
		               <c:forEach items="${paginations}" var="tn" varStatus="status">
						<tr>
						   <td>${tn.rowNo}</td>	
						   <th style="text-align: center;"><a href="../guest/person.xhtml?face=gatPersonDetails&personId=${tn.personId}" target="_blank"><span style='color:blue'>${tn.personTitle}</span></a></th>
	                       <td>${tn.appointCat}</td>
	                       <td><script>document.write("${tn.pubDate}".substring(0, 10));</script></td>
	                        
	                       <td><a href="javascript:;" onclick="editTextNews('${tn.personId}');return false;" class="icon i-edit"></a></td>
	                       <td><a href="javascript:;"  onclick="deleteTextNews('${tn.personId}');return false;" class="icon i-del-02"></a></td>
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
			searchList(${paginations[0].pageIndex},1);
		}
	});
	</c:if>
});
function searchList(num,index) {
	var newsTitle = encodeURI(encodeURI($("input[name='newsTitle']").val()));
	newsTitle = newsTitle.replace(/#/g,"%23");
	var entryDateS =  $("input[name='entryDateS']").val();
	var entryDateE =  $("input[name='entryDateE']").val();
	var pageIndex = num;
	if(index == 1){
		var pageIndex = $("#textInfoListPager").attr("curPage");
	}
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="textPerson.xhtml?face=PersonView&newsTitle="+newsTitle+"&entryDateS="
			+entryDateS+"&entryDateE="+entryDateE+"&pageIndex="+pageIndex+"&personCat="+$("#personCat").val();
  
}
//删除信息
function deleteTextNews(personId) {
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) { 
		if (action == "ok") {
			$.ajax({
				url : "textPerson.xhtml?action=deleteInfo",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"personId" : personId
				},
				success : function(data) {
					document.location.reload();
				}
			});
		}
	})
}
//编辑
function editTextNews(personId){
	location.href ="textPerson.xhtml?face=editPersonView&personId="+personId;
}
</script>
</html>
