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
		网站信息统计报表
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
                <label>报送时间：</label>
                <div class="data" id="dataSelect-start">
                    <input type="text"  name="entryDateS" class="input-text tcal" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>'>
                </div>
                <span>&nbsp;～</span>
                <div class="data" id="dataSelect-end">
                	<input type="text" name="entryDateE" class="input-text tcal" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>'>
                </div>
                <label>报表分组：</label> <select id="selectstatus" name="status" class="input-small">
					<option value="全部">全部</option>
					<option value="市交委机关">市交委机关</option>
					<option value="委属单位">委属单位</option>
					<option value="区县交通局">区县交通局</option>
					<option value="市属相关交通企业">市属相关交通企业</option>
				</select>
                <a href="javascript:;" onclick="selectList();return false;" class="btn btn-blue">统计</a>
                <a href="javascript:;" onclick="exportTable();return false;" class="btn btn-blue">导出Excel</a>
        </div>
   	 </div>
     <!--column01 End-->
     
     <!--column02 Start-->
 	 <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>网站信息统计分析列表</h2></div>
        <div class="con">
        	
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>                    
                    <td width="7%">分类</td>
                    <td width="10%">单位</td>
                    <td width="7%">累计上报数量</td>
                    <td width="7%">累计综合得分</td>
                    <td width="20%">栏目</td> 
                    <td width="7%">上报数量</td>
                    <td width="7%">退回数量</td>
                    <td width="7%">内网信息数量</td>
                    <td width="7%">内网信息得分</td>
                    <td width="7%">外网信息数量</td>
                    <td width="7%">外网信息得分</td>
                    <td width="7%">累计得分</td>
                    
                  </tr>
                </thead>
                <tbody id="tbodyReport">

			    </tbody>
			</table>
			
        </div>
    </div>
    <!--column02 End-->
</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">

function selectList() {
	var startDate =  $("input[name='entryDateS']").val();
	var endDate =  $("input[name='entryDateE']").val();
	var status =  $("#selectstatus").val();
	if(startDate==""||endDate==""){
		asyncbox.alert("请选择报送时间！","信息窗口");
	}else{
	$.ajax({
		url : "reportForm.xhtml?action=querySiteReportByDate",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
		    "startDate" : startDate,
		    "endDate" : endDate,
		    "status" : status
		},
		success : function(data) {
		        var json = $.parseJSON(data);
			    var reportList="";
			    var jw=0;
			    var ws=0;
			    var qx=0;
			    var ss=0;
			    var temp=0;
			    var temp1=0;
			    var temp2=0;
			  
			    $.each(json,function(i,n){
			    	var deptCategory=n.deptCategory;
			    	var deptName=n.deptName;
			    	var categoryName=n.categoryName;
			    	var totalCount=n.allTotalCount;
			    	var totalCountSorce=n.allTotalCountSorce;
			    	var num=parseInt(n.adoptNum);
			    	
			    	if(totalCount==null){
			    		totalCount="";
			    	}
			    	if(totalCountSorce==null){
			    		totalCountSorce="";
			    	}
			    	
			    	if(deptCategory=='市交委机关'){
			    		jw=jw+num;
			    		if(i==0){
			    			reportList+="<tr>"+
						   	            "<td  id='jw'>"+deptCategory+"</td>"+
					                    "<td  rowspan='"+num+"'>"+deptName+"</td>"+
					                    "<td  rowspan='"+num+"'>"+totalCount+"</td>"+
					                    "<td  rowspan='"+num+"'>"+totalCountSorce+"</td> " 
			    		}else{
			    			reportList+="<tr>"+ 
					                    "<td  rowspan='"+num+"'>"+deptName+"</td>"+
					                    "<td  rowspan='"+num+"'>"+totalCount+"</td>"+
					                    "<td  rowspan='"+num+"'>"+totalCountSorce+"</td> "
			    		}
			    		temp++;
			    		
			    	}
		    	
			    	if(deptCategory=='委属单位'){
			    		 qx=qx+num;
			    		 if(i==temp){
			    			 reportList+="<tr>"+
				   	            "<td id='qx'>"+deptCategory+"</td>"+
			                    "<td rowspan='"+num+"'>"+deptName+"</td>"+
			                    "<td  rowspan='"+num+"'>"+totalCount+"</td>"+
			                    "<td  rowspan='"+num+"'>"+totalCountSorce+"</td>" 
			                     temp1 = temp;
			    		 }else{
			    			 reportList+="<tr>"+
			    			    "<td rowspan='"+num+"'>"+deptName+"</td>"+
			                    "<td  rowspan='"+num+"'>"+totalCount+"</td>"+
			                    "<td  rowspan='"+num+"'>"+totalCountSorce+"</td>" 
			    		 }
			    		 temp1++;
			    		 
			    	}
			    
			    	if(deptCategory=='市属相关交通企业'){
						 ws=ws+num;
						 if(i==temp1){
							 reportList+="<tr>"+
				   	            "<td id='ws'>"+deptCategory+"</td>"+
			                    "<td rowspan='"+num+"'>"+deptName+"</td>"+
			                    "<td  rowspan='"+num+"'>"+totalCount+"</td>"+
			                    "<td  rowspan='"+num+"'>"+totalCountSorce+"</td>"
			                    temp2 = temp1;
						 }else{
			    			 reportList+="<tr>"+
			    			    "<td rowspan='"+num+"'>"+deptName+"</td>"+
			                    "<td  rowspan='"+num+"'>"+totalCount+"</td>"+
			                    "<td  rowspan='"+num+"'>"+totalCountSorce+"</td>"
			    		 }
						temp2++;
			    	}
			    	
			    	if(deptCategory=='区县交通局'){
						 ss=ss+num;
						 if(i==temp2){
							 reportList+="<tr>"+
				   	            "<td id='ss'>"+deptCategory+"</td>"+
			                    "<td rowspan='"+num+"'>"+deptName+"</td>"+
			                    "<td  rowspan='"+num+"'>"+totalCount+"</td>"+
			                    "<td  rowspan='"+num+"'>"+totalCountSorce+"</td>"
						 }else{
			    			 reportList+="<tr>"+
			    			    "<td rowspan='"+num+"'>"+deptName+"</td>"+
			                    "<td  rowspan='"+num+"'>"+totalCount+"</td>"+
			                    "<td  rowspan='"+num+"'>"+totalCountSorce+"</td>"
			    		 }
						
			    	}
			    	$.ajax({
			    		url:"reportForm.xhtml?action=querySiteReportByDept",
			    		cache : false,
			    		type : "post",
			    		dataType : "text",
			    		async:false,
			    		data : {
			    		    "deptName" : deptName,
			    		    "startDate" : startDate,
			    		    "endDate" : endDate
			    		},
			    		success : function(data) {
			    			var jsonObj = $.parseJSON(data);
		    			    $.each(jsonObj, function(i, m) {
		    			    	
		    			    	if(m.reportCount==null){m.reportCount="";}
		    			    	if(m.backCount==null){m.backCount="";}
		    			    	if(m.inNetCount==null){m.inNetCount="";}
		    			    	if(m.inNetCountSorce==null){m.inNetCountSorce="";}
		    			    	if(m.outNetCount==null){m.outNetCount="";}
		    			    	if(m.outNetCountSorce==null){m.outNetCountSorce="";}
		    			    	if(m.addTotalSorce==null){m.addTotalSorce="";}
		    			    	
		    			    	if(i>0){
			                    	 reportList+=
			                    	 	"<td>"+m.categoryName+"</td>"+
				                         "<th>"+m.reportCount+"</th>"+
				                         "<td>"+m.backCount+"</td>"+
				                         "<td>"+m.inNetCount+"</td>"+
				                         "<td>"+m.inNetCountSorce+"</td>"+
				                         "<td>"+m.outNetCount+"</td>"+
				                         "<td>"+m.outNetCountSorce+"</td>"+
				                         "<td>"+m.addTotalSorce+"</td></tr>"
			                    	 
			                     }else{
			                    	 reportList+=  
			                    		 "<td>"+m.categoryName+"</td>"+
			                    		 "<th>"+m.reportCount+"</th>"+
				                         "<td>"+m.backCount+"</td>"+
				                         "<td>"+m.inNetCount+"</td>"+
				                         "<td>"+m.inNetCountSorce+"</td>"+
				                         "<td>"+m.outNetCount+"</td>"+
				                         "<td>"+m.outNetCountSorce+"</td>"+
				                         "<td>"+m.addTotalSorce+"</td></tr>"
			                     }	
			    		});	
			    		},
			    		error :function(data){
		    				
		    			}
			    	});
			    	
                });
              $("#tbodyReport").html(reportList); 
               $("#jw").attr("rowspan",jw); 
	 		  $("#qx").attr("rowspan",qx);
			  $("#ws").attr("rowspan",ws);
			  $("#ss").attr("rowspan",ss);
			},
			error :function(data){
				alert("查询数据失败");
			}
		});
	}
}

function exportTable(){
	var startDate =  $("input[name='entryDateS']").val();
	var endDate =  $("input[name='entryDateE']").val();
	var status =  encodeURI(encodeURI($("#selectstatus").val()));
	if(startDate==""||endDate==""){
		asyncbox.alert("请选择报送时间！","信息窗口");
	}else{
	location.href ="reportForm.xhtml?action=exportSiteReport&startDate="+startDate+"&endDate="+endDate+"&status="+status;
	}
}
</script>
</html>
