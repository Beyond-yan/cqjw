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
 						<a style="position: relative;right:0px;" href="textStatute.xhtml?face=addStatuteView" class="btn btn-blue">新  增</a>
						<label id="iii">标题：</label><input type="text" name="newsTitle" class="input-text inputs" value="${newsTitle}">
						<label>法规分类：</label>
			        		<select class="input-text" id="StatuteCat"  name="SUB_CATEGORY" style="height: 28px;">
        			<option value="">-= 请选择  =-</option>
        			<option value="交通地方性法规库" <c:if test="${statuteCat=='交通地方性法规库'}">selected</c:if>>交通地方性法规库</option>
        			<option value="交通地方政府规章库" <c:if test="${statuteCat=='交通地方政府规章库'}">selected</c:if>>交通地方政府规章库</option>
        			<option value="交通行政规范性文件库" <c:if test="${statuteCat=='交通行政规范性文件库'}">selected</c:if>>交通行政规范性文件库</option>
        			<option value="党内法规库" <c:if test="${statuteCat=='党内法规库'}">selected</c:if>>党内法规库</option>
        		</select>&nbsp;&nbsp;&nbsp;
        		<label>子分类：</label>
        		<select class="input-text" id="Statute" name="SUB_CATEGORY_INFO" style="height: 28px;">
        			<c:choose>
					    <c:when test="${statute == '市政府制发文件'}">
					       <option value='市政府制发文件' selected>市政府制发文件</option><option value='市交委制发文件'>市交委制发文件</option><option value='委属单位制发文件'>委属单位制发文件</option>
					    </c:when>
					    <c:when test="${statute == '市交委制发文件'}">
					       <option value='市政府制发文件' >市政府制发文件</option><option value='市交委制发文件' selected>市交委制发文件</option><option value='委属单位制发文件'>委属单位制发文件</option>
					    </c:when>  
					    <c:when test="${statute == '委属单位制发文件'}">
					       <option value='市政府制发文件' >市政府制发文件</option><option value='市交委制发文件'>市交委制发文件</option><option value='委属单位制发文件' selected>委属单位制发文件</option>
					    </c:when>
					    <c:when test="${statute == '其他相关文件'}">
					       <option value='其他相关文件' selected>其他相关文件</option><option value='市交通党委规范性文件'>市交通党委规范性文件</option><option value='市委党内法规'>市委党内法规</option><option value='中央党内法规'>中央党内法规</option>
					    </c:when>
					     <c:when test="${statute == '市交通党委规范性文件'}">
					       <option value='其他相关文件' >其他相关文件</option><option value='市交通党委规范性文件' selected>市交通党委规范性文件</option><option value='市委党内法规'>市委党内法规</option><option value='中央党内法规'>中央党内法规</option>
					    </c:when>
					    <c:when test="${statute == '市委党内法规'}">
					       <option value='其他相关文件' >其他相关文件</option><option value='市交通党委规范性文件'>市交通党委规范性文件</option><option value='市委党内法规' selected>市委党内法规</option><option value='中央党内法规'>中央党内法规</option>
					    </c:when>
					    <c:when test="${statute == '中央党内法规'}">
					       <option value='其他相关文件' >其他相关文件</option><option value='市交通党委规范性文件'>市交通党委规范性文件</option><option value='市委党内法规'>市委党内法规</option><option value='中央党内法规' selected>中央党内法规</option>
					    </c:when>
					   <c:otherwise>  
					     <option value="">-= 请选择  =-</option>
					   </c:otherwise>
					 </c:choose>
        		</select>
					
					</td>
        			<td>
						
					</td>
        		</tr>
        		<tr>
        			<td style="padding-top: 5px">
						
			        		
			        		<label>发布时间：</label>
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
          <div class="title"><h2><b class="icon i-search"></b>投稿列表</h2></div>
            <div class="con">
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>     
                  	<!-- <td width="2%"><input type='checkbox' name='selAllCheckBox' id='selAll'></td>  -->              
                    <td width="5%">序号</td>
                    <td width="37%">主标题</td>
                    <td width="10%">法律分类</td>
                    <td width="12%">子分类</td>
                    <td width="8%">发布日期</td>
                    <td width="8%">实施日期</td>
                    <td width="8%">时效性</td>
                    <td width="5%">编辑</td>
                    <td width="5%">删除</td>
                  </tr>
                </thead>
		               <tbody id="tbodyNews">
		               <c:forEach items="${paginations}" var="tn" varStatus="status">
						<tr>
							<%-- <td><input type="checkbox" class="checkBox"  name='singleBox' value="${tn.newsId}"/></td> --%>
							<td>${tn.rowNo}</td>	
							<th style="text-align: center;"><a href="../guest/statute.xhtml?face=gatStatuteDetails&statuteId=${tn.statuteId}" target="_blank"><span style='color:blue'>${tn.statuteTitle}</span></a></th>
		                      
		                       <td>${tn.SUB_CATEGORY}</td>
		                       <td>${tn.SUB_CATEGORY_INFO}</td>
		                       <td>
		                       <script>document.write("${tn.pubDate}".substring(0, 10));</script>
		                       </td>
		                       <td>  
		                       	<script>document.write("${tn.inplementDate}".substring(0, 10));</script>
		                       </td>
		                       <td>
		                       ${tn.timeliness}
		                       </td>
		                       <td><a href="javascript:;" onclick="editTextNews('${tn.statuteId}');return false;" class="icon i-edit"></a></td>
		                       <td><a href="javascript:;"  onclick="deleteTextNews('${tn.statuteId}');return false;" class="icon i-del-02"></a></td>
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

$("#StatuteCat").change(function(){
	
	switch ($(this).val()) {
		case '交通地方性法规库':
			$("#Statute").html("<option value=''>-= 请选择  =-</option>");
			break;
		case '交通地方政府规章库':
			$("#Statute").html("<option value=''>-= 请选择  =-</option>");
			break;
		case '交通行政规范性文件库':
			$("#Statute").html("<option value='市政府制发文件'>市政府制发文件</option><option value='市交委制发文件'>市交委制发文件</option><option value='委属单位制发文件'>委属单位制发文件</option>");
			break;
		case '党内法规库':
			$("#Statute").html("<option value='其他相关文件'>其他相关文件</option><option value='市交通党委规范性文件'>市交通党委规范性文件</option><option value='市委党内法规'>市委党内法规</option><option value='中央党内法规'>中央党内法规</option>");
			break;
		default:
			$("#Statute").html("<option value=''>-= 请选择  =-</option>");
			break;
		}
		
})

function jumpToPage(pIndex) {
	//alert(pIndex);
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
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="textStatute.xhtml?face=StatuteView&newsTitle="+newsTitle+"&entryDateS="
			+entryDateS+"&entryDateE="+entryDateE+"&pageIndex="+pageIndex+"&StatuteCat="+$("#StatuteCat").val()+"&Statute="+$("#Statute").val();
  
}
//删除信息
function deleteTextNews(statuteId) {
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) { 
		if (action == "ok") {
			$.ajax({
				url : "textStatute.xhtml?action=deleteInfo",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"statuteId" : statuteId
				},
				success : function(data) {
					document.location.reload();
				}
			});
		}
	})
}

//编辑
function editTextNews(statuteId){
	location.href ="textStatute.xhtml?face=editStatuteView&statuteId="+statuteId;
}

 

 

</script>
</html>
