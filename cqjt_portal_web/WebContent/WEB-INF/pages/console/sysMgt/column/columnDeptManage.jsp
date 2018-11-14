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
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
#search {float: left;width:30%;}
#searchList{float: left;width:100%;}
#Permission {float: right; width:70%;}
  a {color:blue;cursor: pointer;} 
</style>
<title>系统管理</title>
</head>
<body>
<div >
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		系统管理
        <span>-></span>
		栏目与处室关系管理
    </div>
    <!--path End-->
    <!--column02 Start-->
    <div class="column" id="searchList" >
    	<div class="title"><h2><b class="icon i-search"></b>部门列表</h2></div>
    <div class="con">
	    <div style="margin-bottom: 10px;">
	       	<label style="padding-left: 30px;">部门名称：</label><input type="text" name="deptName" class="input-text" style="width:300px">
	        <label style="padding-left: 30px;">部门分类：</label> 
	        <select id="selectstatus" name="deptCategory" class="input-small" style="width:300px">
					<option selected value="">-=请选择=-</option>
					<option value="市交委机关">市交委机关</option>
					<option value="委属单位">委属单位</option>
					<option value="区县交通局">区县交通局</option>
					<option value="市属相关交通企业">市属相关交通企业</option>
					
			</select>
			<label></label>
	                <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">搜索</a>
	         </div>
	        </div>    
        </div>  
         <div class="column" id="search">
        <div class="con">
        <table width="100%" border="1" class="dataList">
       		  <thead>
                  <tr>    
                  	<td width="5%">序号</td>                
                    <td width="13%">部门分类</td>
                    <td width="20%">部门名称</td>
                  </tr>
                </thead>
              <tbody id="tbodyDept">
            		  <c:forEach items="${page}" var="tn" varStatus="status">
             			 <tr>
						  
						  	<td>${tn.rowNo}</td>
						  	<td>${tn.deptCategory}</td>
							<th><a onclick="showDeptCategory(${tn.deptID});return false;" id="dept${tn.deptID}">${tn.deptName}</a></th>
							
						</tr>
					</c:forEach>	
			    </tbody>
			 </table>	 
			<div id="deptInfoListPager" class="page clearfix"></div>
        </div>
        </div>
          <div class="column" id="Permission" >
	        <div class="con">
	        <iframe name="columDeptMgtFrame" id="columDeptMgtFrame" width="100%" height="100%"
		    		src="" frameborder="0" scrolling="auto"> 
		    </iframe>
	    	</div>
    	</div>
    </div>
    <!--column02 End-->
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript">
$(function() {
	var minHeight = $("body").height()-110;
	if ($("#search .con").height() > minHeight) {
		$("#Permission .con").height($("#search .con").height());
	} else {
		$("#search .con").height(minHeight)
		$("#Permission .con").height(minHeight);
	}
	$("#Permission").width($("body").width()-$("#search").width()-38);
	<c:if test="${!empty page[0].totalCount}">
	$("#deptInfoListPager").buildPager({
		totalLines:${page[0].totalCount},
		pageSize:${page[0].pageSize},
		startIndex:${page[0].pageIndex},
		displayNum:5,
		afterChange:function() {
			searchList();
		}
	});
	</c:if>
});
function searchList() {
	var deptName = $("input[name='deptName']").val();
	var deptCategory = $("select[name='deptCategory']").val();
	var pageIndex = $("#deptInfoListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	$.ajax({
		url : "columnDept.xhtml?action=queryDeptInfoView",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
			"deptName" : deptName,
		    "deptCategory" : deptCategory,
		    "pageIndex" : pageIndex
		},
		 success : function(data) {
			 var obj=eval(data);
			  var json =eval(data);
			  var startIndex=json[0].pageIndex;
			  var pageSize=json[0].pageSize;
		      var totalLines =json[0].totalCount;
			  
			    var departmentInfoList="";
			    $.each(json, function(i, n) {
				     var deptName=n.deptName;
				     var deptCategory=n.deptCategory;
				     
					departmentInfoList+= "<tr>"+
 										"<td>"+n.rowNo +"</td>"+
 										"<td>"+deptCategory+"</td>"+
 										"<th><a onclick='showDeptCategory("+n.deptID+");return false;' id='dept"+n.deptID+"'>" +deptName+"</a></th>"+
 										"</tr>"
                });
              $("#tbodyDept").html(departmentInfoList); 
        		 $("#deptInfoListPager").buildPager({
					totalLines:totalLines,
					pageSize:pageSize,
					startIndex:startIndex,
					displayNum:5,
					afterChange:function() {
						searchList();
					}
			  }); 
			},
			error :function(data){
				
			}
		}); 
}
//查询
function showDeptCategory(deptID){
	//更改点击 <a>的颜色
	
	$("a[id^='dept']").attr("style","color:blue");
	$("#dept"+deptID).attr("style","color:red");
	$("#columDeptMgtFrame").attr(
			"src",
			"columnDept.xhtml?face=queryCategoryDeptList&deptID="
					+ deptID);
}
//编辑
function editCategoryInfo(categoryId,deptID){
	location.href="columnDept.xhtml?face=editColumnDept&categoryId="+categoryId+"&deptID="+deptID;
}
</script>
</html>