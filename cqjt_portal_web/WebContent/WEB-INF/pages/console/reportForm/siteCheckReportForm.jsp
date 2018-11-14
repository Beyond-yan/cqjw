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
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
</style>
<title>网站信息统计报表</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		统计分析报表
        <span>-></span>
		网站信息考核统计报表
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
                <label>时间：</label>
                <div class="data" id="dataSelect-start">
                    <input type="text"  name="createDateS" class="input-text tcal" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>'>
                </div>
                <span>&nbsp;～</span>
                <div class="data" id="dataSelect-end">
                	<input type="text" name="createDateE" class="input-text tcal" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>'>
                </div>
                <label>报表分组：</label> <select id="selectstatus" name="status" class="input-small">
						<option value="全部">全部</option>
					<option value="市交委机关">市交委机关</option>
					<option value="委属单位">委属单位</option>
					<option value="区县交通局">区县交通局</option>
					<option value="市属相关交通企业">市属相关交通企业</option>
				</select>
                <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue"><b>统计</b></a>
                <a href="javascript:;" onclick="checkReport('0');return false;" class="btn btn-blue"><b>考 核</b></a>
                <a href="javascript:;" onclick="checkReport('1');return false;" class="btn btn-blue"><b>当年考核</b></a>
                <a href="javascript:;" onclick="exportTable();return false;" class="btn btn-blue"><b>导出Excel</b></a>
        </div>
   	 </div>
     <!--column01 End-->
     
     <!--column02 Start-->
 	 <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>统计分析列表</h2></div>
        <div class="con">
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>                    
                    <td width="7%">分类</td>
                    <td width="10%">部门</td>
                    <td width="7%">采用条数</td>
                    <td width="7%">得分总计</td>
                    <td width="30%">信息标题</td>
                    <td width="7%">内网栏目</td>
                    <td width="7%">外网栏目</td>
                    <td width="7%">得分</td>
                    <td width="7%">发布渠道</td>
                  </tr>
                </thead>
                <tbody id="tbodyReport">
                
           <%--      <c:forEach items="${rankListTest.result}" var="tn" varStatus="status">
						<tr>
							<td>${tn.deptCategory}</td>
							<td>${tn.deptName}</td>
							<td>${tn.adoptNum}</td>
							<td>${tn.scoreSum}</td>
							<td>${tn.title}</td>
							<td><fmt:formatNumber value="${tn.score}"></fmt:formatNumber></td>
							<td>
								<c:if test="${tn.adoptType=='isPublic'}">内网、外网</c:if><c:if test="${tn.adoptType=='sendSite'}">内网</c:if>
							</td>
						</tr>
					</c:forEach> --%>
						<%-- <tr>
						<c:if test="${! empty rankList.result }">
						 <td rowspan="${rankList.pageSize}">委机关</td>
						  <c:forEach items="${rankList.result}" var="tn" varStatus="status">
							<td>${tn.deptName}</td>
							<td>${tn.adoptNum}</td>
							<td>${tn.scoreSum}</td>
							<td>${tn.title}</td>
							<td>${tn.score}</td>
							<td>
								<c:if test='${tn.isPublic==1}'>内网、外网</c:if><c:if test='${tn.isPublic==0}'>内网</c:if>
							</td>
						</tr>
					</c:forEach>
						</c:if>
					<tr>
						<c:if test="${! empty rankListS.result }">
						 <td rowspan="${rankListS.pageSize}">委属单位</td>
						  <c:forEach items="${rankListS.result}" var="tn" varStatus="status">
							<td>${tn.deptName}</td>
							<td>${tn.adoptNum}</td>
							<td>${tn.scoreSum}</td>
							<td>${tn.title}</td>
							<td>${tn.score}</td>
							<td>
								<c:if test='${tn.isPublic==1}'>内网、外网</c:if><c:if test='${tn.isPublic==0}'>内网</c:if>
							</td>
					</tr>
					</c:forEach>
						</c:if>
					<tr>
						<c:if test="${! empty rankListE.result }">
						 <td rowspan="${rankListE.pageSize}">区县交通局</td>
						  <c:forEach items="${rankListE.result}" var="tn" varStatus="status">
							<td>${tn.deptName}</td>
							<td>${tn.adoptNum}</td>
							<td>${tn.scoreSum}</td>
							<td>${tn.title}</td>
							<td>${tn.score}</td>
							<td>
							<c:if test='${tn.isPublic==1}'>内网、外网</c:if><c:if test='${tn.isPublic==0}'>内网</c:if>
							</td>
					</tr>
					</c:forEach>
					</c:if> --%>
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

function searchList() {
	var createDateS =  $("input[name='createDateS']").val();
	var createDateE =  $("input[name='createDateE']").val();
	var status =  $("#selectstatus").val();
	if(createDateS==""||createDateE==""){
		asyncbox.alert("请选择报送时间！","信息窗口");
	}else{
	$.ajax({
		url : "reportForm.xhtml?action=getSiteCheckReport",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
		    "createDateS" : createDateS,
		    "createDateE" : createDateE,
		    "status" : status
		},
		success : function(data) {
		        var json = $.parseJSON(data);
			    var siteReportList="";
			    var jw = 0;
		        var qx = 0;
		        var ws = 0;
		        var ss = 0;
		        var temp = 0;
		        var temp1 = 0;
		        var temp2 = 0;//新增部门分类
		        var temp3=0;
			    $.each(json, function(i, n) {
			    	var deptCategory = n.deptCategory;
			        var deptName = n.deptName;
			        var deptId =n.deptId;
			        var num = 1;
			        if(n.adoptNum!=null){
			        	num = parseInt(n.adoptNum);
			        }else{
			        	n.adoptNum = "";
			        	n.scoreSum = "";
			        }
			    		if(deptCategory=='市交委机关'){
				    		 jw=jw+num;
				    		 if(i==0){
				    			 siteReportList+=    "<tr>"+
					   	            "<td id='jw'>"+n.deptCategory+"</td>"+
				                    "<td  rowspan='"+num+"'>"+n.deptName+"</td>"+
				                    "<td  rowspan='"+num+"'>"+n.adoptNum+"</td>"+
				                    "<td  rowspan='"+num+"'>"+n.scoreSum+"</td>" 
				    		 }else{
				    			 siteReportList+="<tr>"+
				                    "<td  rowspan='"+num+"'>"+n.deptName+"</td>"+
				                    "<td  rowspan='"+num+"'>"+n.adoptNum+"</td>"+
				                    "<td  rowspan='"+num+"'>"+n.scoreSum+"</td>" 
				    		 }
				    		 temp++;
				    	}
				    	
				    	
				    	if(deptCategory=='委属单位'){
				    		 qx=qx+num;
				    		 if(i==temp){
				    			 siteReportList+=    "<tr>"+
					   	            "<td id='qx'>"+n.deptCategory+"</td>"+
				                    "<td rowspan='"+num+"'>"+n.deptName+"</td>"+
				                    "<td rowspan='"+num+"'>"+n.adoptNum+"</td>"+
				                    "<td rowspan='"+num+"'>"+n.scoreSum+"</td>" 
				                     temp1 = temp;
				    		 }else{
				    			 siteReportList+="<tr>"+
				    			    "<td rowspan='"+num+"'>"+n.deptName+"</td>"+
				                    "<td rowspan='"+num+"'>"+n.adoptNum+"</td>"+
				                    "<td rowspan='"+num+"'>"+n.scoreSum+"</td>" 
				    		 }
				    		 temp1++;
				    		 
				    
				    	   }else{
				    		if(i == temp){
				    			temp1 = temp;
				    		}
				    	}
				    	
						if(deptCategory=='市属相关交通企业'){
							 ws=ws+num;
							 if(i==temp1){
								 siteReportList+="<tr>"+
					   	            "<td id='ws'>"+deptCategory+"</td>"+
				                    "<td rowspan='"+num+"'>"+n.deptName+"</td>"+
				                    "<td rowspan='"+num+"'>"+n.adoptNum+"</td>"+
				                    "<td rowspan='"+num+"'>"+n.scoreSum+"</td>" 
				                    temp2= temp1;
							
							 }else{
								 siteReportList+="<tr>"+
				    			    "<td rowspan='"+num+"'>"+n.deptName+"</td>"+
				                    "<td rowspan='"+num+"'>"+n.adoptNum+"</td>"+
				                    "<td rowspan='"+num+"'>"+n.scoreSum+"</td>" 
				    		 }
							 temp2++;
						 }else{
				    		if(i == temp1){
				    			  temp2= temp1;
				    		}
				    	}
						if(deptCategory=='区县交通局'){
							 ss=ss+num;
							 if(i==temp2){
								 siteReportList+="<tr>"+
					   	            "<td id='ss'>"+deptCategory+"</td>"+
				                    "<td rowspan='"+num+"'>"+n.deptName+"</td>"+
				                    "<td rowspan='"+num+"'>"+n.adoptNum+"</td>"+
				                    "<td rowspan='"+num+"'>"+n.scoreSum+"</td>" 
							 }else{
								 siteReportList+="<tr>"+
				    			    "<td rowspan='"+num+"'>"+n.deptName+"</td>"+
				                    "<td rowspan='"+num+"'>"+n.adoptNum+"</td>"+
				                    "<td rowspan='"+num+"'>"+n.scoreSum+"</td>" 
				    		 }
				    	}
						 $.ajax({
				    		url : "reportForm.xhtml?action=getSiteCheckReportByDept",
				    		cache : false,
				    		type : "post",
				    		dataType : "text",
				    		async:false,
				    		data : {
				    		    "deptId" : deptId,
				    		    "createDateS" : createDateS,
				    		    "createDateE" : createDateE
				    		},
				    		success : function(data) {
				    			if(data=="[]"){
				    				siteReportList+=  
				                         "<td style=\"text-align:left;\"> </td>"+
				                         "<td> </td><td> </td><td> </td><td> </td></tr>"
				    			}else{
				    		        var jsonObj = $.parseJSON(data);
				    			    $.each(jsonObj, function(i, m) {
				    			    	var show="";
				    			    	var innerCategory="";
				    			    	if (m.innerCategory==""||m.innerCategory==null){
				    			    		innerCategory="";	
				    			    	}
				    			    	else {
				    			    		innerCategory=m.innerCategory;
				    			    	}
				    			    	var outerCategory="";
				    			    	if (m.outerCategory==""||m.outerCategory==null){
				    			    		 outerCategory="";
				    			    	}
				    			    	else {
				    			    		outerCategory=m.outerCategory;
				    			    	}
				   			    	 if(m.adoptType=="sendSite,isPublic"||m.adoptType=="isPublic,sendSite"){
				   			    		 show="内网、外网 ";
				   			    	 }else if(m.adoptType=="sendSite"){
				   			    		 show="内网 ";
				   			    		outerCategory="";
				   			    	 }else if(m.adoptType=="sendVideo"){
				   			    		 show="视频报送 ";
				   			    	 }
					                     if(i>0){
					                    	 siteReportList+=
						                         "<td style=\"text-align:left;\">"+m.title+"</td>"+
						                         "<td>"+innerCategory+"</td>"+
						                         "<td>"+outerCategory+"</td>"+
						                         "<td>"+m.score+"</td>"+
						                         "<td>"+show+"</td></tr>"
					                     }else{
					                    	 siteReportList+=  
						                         "<td style=\"text-align:left;\">"+m.title+"</td>"+
						                         "<td>"+innerCategory+"</td>"+
						                         "<td>"+outerCategory+"</td>"+
						                         "<td>"+m.score+"</td>"+
						                         "<td>"+show+"</td></tr>"
					                     }
				                    });
				    			}
				    			},
				    			error :function(data){	
				    			}
				    		 }); 
						
                });
              $("#tbodyReport").html(siteReportList); 
              $("#jw").attr("rowspan",jw);
			  $("#qx").attr("rowspan",qx);
			  $("#ws").attr("rowspan",ws);
			  $("#ss").attr("rowspan",ss);
			},
			error :function(data){
				asyncbox.alert(data, "信息窗口", function() {
					document.location.reload();
				});
			}
		});
	}
}

function exportTable(){
	var startDate =  $("input[name='createDateS']").val();
	var endDate =  $("input[name='createDateE']").val();
	var status =  encodeURI(encodeURI($("#selectstatus").val()));
	if(startDate==""||endDate==""){
		asyncbox.alert("请选择报送时间！","信息窗口");
	}else{
		location.href ="reportForm.xhtml?action=exportSiteCheckReport&startDate="+startDate+"&endDate="+endDate+"&status="+status;
	}
}
//考核
function checkReport(flag){
	var startDate =  $("input[name='createDateS']").val();
	var endDate =  $("input[name='createDateE']").val();
	var status =  encodeURI(encodeURI($("#selectstatus").val()));
	asyncbox.confirm("是否考核该信息?考核后网站信息得分将在首页显示!", "信息窗口", function(action) { 
		if (action == "ok") {
			$.ajax({
				url : "reportForm.xhtml?action=checkReport&startDate="+startDate+"&endDate="+endDate+"&status="+status+"&flag="+flag,
				cache : false,
				type : "GET",
				dataType : "text",
				success : function(data) {
				   var content = eval('(' + data + ')');
					asyncbox.alert(content.msg, "信息窗口", function() {
						
					}); 
				}
			});
		}
	})
}
</script>
</html>
