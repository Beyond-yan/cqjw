<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@page 
	import="com.gdssoft.core.tools.SystemContext"%>
<!doctype html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--员工展示</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/index.css" rel="stylesheet" type="text/css">
<%-- <link href="${StaticResourcePath}/css/news.css" rel="stylesheet" type="text/css"> --%>
<script type="text/javascript"  src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
.sub{width:270px;}
.sub-picNews .con{text-align:center;padding:10px 10px 10px;}
.main{float:left;width:722px;margin:0 0 0 8px;}
.content .column{height:581px;overflow:hidden;}
.infoList a{width:89%;}
.main .infoList a{max-width:600px;}
.infoList .time{text-align:right;color:#6d6d6d;font-family:Tahoma;}
#leftmiu{margin-top:10px;}
.miulist{font-size:14px;color:#fd3130;margin-left:45px;}
.milist{font-size:14px;color:#fd3130;margin-left:25px; padding:6px;}
.mlist{font-size:14px;color:#f7514e;margin-left:30px; padding:3px;}
.lidiv{float: left;}
.lia{padding:25px 70px 10px 50px;width: 80px;}
.lib{padding:25px 0px 10px 0px;width: 220px;}

#maul{width:500px;position: relative;left:50%;margin-left:-200px;margin-top:50px;}
.maulli{border-bottom: 2px solid #ddd; height:160px;}
h3,h4{cursor:pointer;}
</style>
</head>

<body>
<div class="wrap">
<%@include file="../head.jspf"%>
 <!--content Start-->
    <div class="content clearfix">
    	<!--search-box Start-->
    	<div class="search-box">
            <div class="breadCutNav">
                <b class="icon icon_home"></b>
                <span>当前位置：</span>
                <a href="index.xhtml">首页</a>
                <span> &gt;</span>
                <a href="javascript:;">员工展示</a>
            </div>  
            <%@include file="../index/search.jspf"%>
        </div>
        
    	<!--search-box End-->
        <!--sub Start-->
        <div class="sub l" style="position: absolute;z-index: -1;height:40px;">
           <div class="column" style="border:0;background: 0;">
           	  <div class="title"><h2 style="font-size:17px;text-align: center;color:#fd3130; text-indent:3px;">重庆市交通委员会员工墙</h2></div>
          </div>
        </div>
        <div class="sub l" style="position: relative;top:40px;">
           <div class="column" style="overflow:auto;">
              <div class="con">
        		<input type="hidden" value="${categoryName}" id="catn" /> 
        	     <!--<select id="selectDeptName" name="deptID" class="input input-small inputs"></select>◎-->
            	<ul id="leftmiu">
            		<li  class='milist'>
            			<h3 id='fasts0' class='dept' onclick="getEmpShowList('委领导',this)" >※委领导</h3><ul id='leftmula'></ul>
            		</li>
            		<li  class='milist'>
            			<h3 id='fasb' class='dept'  onclick="getDeptList(this)">※委机关</h3><ul id='leftmulb'></ul>
            		</li>
            		<li  class='milist'>
            			<h3 id='fasts17' class='dept'  onclick="getEmpShowList('纪检组',this)" >※纪检组</h3><ul id='leftmulc'></ul>
            		</li>
            	</ul>
            </div>
          </div>
        </div>
        <!--sub End-->
        <!--main Start-->
        <div class="main">
        	<input id="selectDeptName" type="hidden" value="${categoryName}"/>
        	<input id="fasts" type="hidden" value="${fasts}"/>
	        	<ul id="maul">
	        	<div style="font-size:20px;">${msgs}</div>
		        	<c:forEach items="${paginations}" var="tn" varStatus="status">
		        		<li class="maulli">
		        			<div class="lidiv lia">
								<img style="height:120px;border:1px solid #eee; "src="${pageContext.request.contextPath}/${tn.empUrl}"/>
							</div>
		        			<div class="lidiv lib">
								<h3 style="margin-bottom: 7px;">${tn.empName }</h3>
								<p>${tn.empPost}</p>
							</div>
		        		</li>
	        		</c:forEach>
	        	</ul>
	        	<div id="userListPager" class="page clearfix"></div>
        </div>
        <!--main End-->
    </div>
    <!--content End-->
    <%@include file="../footer.jspf"%>
</div>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"  src="${StaticResourcePath}/js/main.js"></script>

<script type="text/javascript">


function selectList() {
	var pageIndex = $("#userListPager").attr("curPage");
	var categoryName = $('#selectDeptName').val();
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="empShow.xhtml?face=searchEmpList&empName=&entryDateS=&entryDateE=&pageIndex="+pageIndex+"&deptName=&categoryName="+categoryName+"&fasts="+$("#fasts").val();
}
function getEmpList(deptName){
	
	$.ajax({
		url : "empShow.xhtml?action=queryCatNameList",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
			"deptName" : deptName
		},
		success : function(data) {
			var json = eval(data);
			var departmentInfoList = "";
			$.each(json,function(i, n) {
				departmentInfoList += "<li  class='mlist' ><h4 id='mli"+i+"' onclick='getEmp("+"\""+n.empId+"\""+")'>◎"+n.empName+"</h4></li>";
			});
			$(".mul").html("");
			var fobj = "#"+$("#fasts").val();
			 
			$(fobj).next().html(departmentInfoList);
 
		},
		error : function(data) {
			alert("查询失败！");
		}
	});
}
//对部门下拉列表连动
function seaList() {
	var deptCategory = "市交委机关";
	$.ajax({
			url : "SearchContent.xhtml?action=queryDeptInfoView",
			cache : false,
			type : "post",
			dataType : "text",
			data : {
				"deptCategory" : deptCategory
			},
			success : function(data) {
				var json = eval(data);
				var departmentInfoList = "";
				$.each(json,function(i, n) {
					var deptName = n.deptName;
					if(deptName != '委领导'){
						if(deptName != '纪检组'){
							if(i != '17'){
								departmentInfoList += "<li  class='miulist'><h3 id='fasts"+i+"' class='dept'   onclick='getEmpShowList("+"\""+deptName+"\""+",this)'>※"+deptName+"</h3><ul class='mul'></ul></li>";
							}
						}	 
					}
				});
				
				$("#leftmulb").html(departmentInfoList);
				
			},
				error : function(data) {
			}
		});
}
 


var aclick = 1;
function getEmpShowList(deptName,tobje){	
	var fobj = "#"+$("#fasts").val();
	if($(fobj).text()==$(tobje).text()){
		if(aclick==1){
			$(fobj).next().html("");
			aclick ++;
		}else{
			var fasts = $(tobje).attr("id");
			var pageIndex = 0;
			var categoryName = deptName;
			if (typeof pageIndex == "undefined") {
				pageIndex = 0;
			}
			location.href ="empShow.xhtml?face=searchEmpList&empName=&entryDateS=&entryDateE=&pageIndex="+pageIndex+"&deptName=&categoryName="+categoryName+"&fasts="+fasts;
		}
	}else{
		var fasts = $(tobje).attr("id");
		var pageIndex = 0;
		var categoryName = deptName;
		if (typeof pageIndex == "undefined") {
			pageIndex = 0;
		}
		location.href ="empShow.xhtml?face=searchEmpList&empName=&entryDateS=&entryDateE=&pageIndex="+pageIndex+"&deptName=&categoryName="+categoryName+"&fasts="+fasts;
	}	
}
var bclick = 1;
function getDeptList(tobje){	
	if(bclick==2){
		$(tobje).next().html("");
		bclick = 1;
	}else{
		$(".dept").next().html("");
		seaList();
		bclick = 2;
	}
	 	
}
function getEmp(empId){
	
	$.ajax({
		url : "empShow.xhtml?action=queryEmpInfo",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
			"empId" : empId
		},
		success : function(data) {
	 
			var d = $.parseJSON(data);
			
			var departmentInfoList = "";
		 
			departmentInfoList += "<li class='maulli'><div class='lidiv lia'>"+
			"<img style='height:120px;border:1px solid #eee;' src='/"+d.empUrl+"'/>"+
			"</div><div class='lidiv lib'><h3 style='margin-bottom: 7px;'>"+d.empName+"</h3><p>"+d.empPost+"</p></div></li>";
		 
			$("#maul").html(departmentInfoList);
		},
		error : function(data) {
			alert("查询失败！");
		}
	});
}
$(function(){
	
	if($("#selectDeptName").val() != "委领导"){
		if($("#selectDeptName").val() != "纪检组"){
			$(".dept").next().html("");
			seaList();
			bclick = 2;
			setTimeout("getEmpList($('#selectDeptName').val())","100");
			
		}else{
			getEmpList($('#selectDeptName').val());
		}
	}else{
		getEmpList($('#selectDeptName').val());
	}
	
 
})

</script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript">
$(function(){
	
	
	$("#userListPager").buildPager({
		totalLines:${paginations[0].totalCount},
		pageSize:${paginations[0].pageSize},
		startIndex:${paginations[0].pageIndex},
		displayNum:5,
		afterChange:function() {
			selectList();
		}
	});
});
</script>
</body>
</html>