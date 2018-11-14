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
.inputs{width:150px !important;}
.tdd{margin-top:2px !important;}
</style>

<title>员工信息查询</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		内容管理
        <span>-></span>
		员工信息查询
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>员工展示</h2></div>
        <div class="con">
        	<table>
        		<tr>
        			<td>
        			 <a href="emp.xhtml?face=addEmp" class="btn btn-blue">新  增</a>
        			<label>员工名称：</label><input type="text" name="empName" class="input-text" value="${EmpName}">
        		<input type="hidden" value="${categoryName}" id="catn" /> 
						<label style="padding-left: 30px;">部门名称：</label> <select
							id="selectDeptName" name="deptID" class="input input-small inputs">
						</select>
        			</td>
        		</tr>
        		<tr>
        			<td>
        				 <label>录入时间：</label>
                <div class="data tdd" id="dataSelect-start">
                    <input type="text"  name="entryDateS" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal">
                </div>
                <span>&nbsp;～</span>
                <div class="data tdd" id="dataSelect-end">
                	<input type="text" name="entryDateE" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>'  class="input-text tcal" >
                </div>
                <a href="javascript:;" onclick="selectList();return false;" class="btn btn-blue">搜 索</a>
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
                    <td width="4%">员工照片</td>
                    <td width="5%">员工名称</td>
                    <td width="15%">员工职位</td>
                    <td width="7%">部门名称</td>
                    <td width="10%">录入时间</td>
                    <td width="8%">录入人</td>
                    <td width="2%">排序</td>
                    <td width="5%">编辑</td>
                    <td width="5%">删除</td>
                  </tr>
                </thead>
                <tbody id="tbodyVideo">
                <c:forEach items="${paginations}" var="tn" varStatus="status">
					<tr>
						<td>${tn.rowNo}</</td>
						<td style="padding:5px;"><img style="height:60px;"src="${pageContext.request.contextPath}/${tn.empUrl}"/></td>
                        <th align="center" style="text-align: center;">     
                        	<a href="javascript:;" onclick="editemp('${tn.empId}');return false;"><span style='color:blue'>${tn.empName}</span></a>
                    	</th> 
                    	<td align="center">
                    		 ${tn.empPost} 
                    	</</td>
                    	<td>${tn.categoryName}</td>
                        <td>${tn.entryDate}</td>
                        <td>${tn.entryUser}</td>
                        <td><input type="text" style="width:20px" maxlength="3" value="${tn.empSort}" onchange="updateNewsSort('${tn.empId}',this)"/></td>
                        <td><a href="javascript:;" onclick="editemp('${tn.empId}');return false;"class="icon i-edit"></a></td>
                        <td><a  href="javascript:;" onclick="deleteemp('${tn.empId}','${tn.flag}');return false;" class="icon i-del-02"></a></td>          
					</tr>
				</c:forEach>
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

 
$(function(){
	<c:if test="${!empty paginations[0].totalCount}">
	$("#userListPager").buildPager({
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

//排序
function updateNewsSort(empId,obj){
	var empSort = $(obj).val();
	$.ajax({
		url : "emp.xhtml?action=updateEmpSort",
		cache : false,
		type : "GET",
		dataType : "text",
		data : {
			"empId" :empId,
			"empSort" : empSort
		},
		success : function(data) {	
			var data = eval('(' + data + ')');
			asyncbox.alert(data.msg)
			selectList();
		},
		error :function(data){
			var data = eval('(' + data + ')');
			asyncbox.alert(data.msg)
		}
	});
}


function selectList() {
	var empName = encodeURI(encodeURI($("input[name='empName']").val()));
	var entryDateS =  $("input[name='entryDateS']").val();
	var entryDateE =  $("input[name='entryDateE']").val();
	var pageIndex = $("#userListPager").attr("curPage");
	var categoryName = encodeURI(encodeURI($('#selectDeptName').val()));
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="emp.xhtml?face=searchEmpList&empName="+empName+"&entryDateS="
	+entryDateS+"&entryDateE="+entryDateE+"&pageIndex="+pageIndex+"&deptName=&categoryName="+categoryName;
}

function deleteemp(empId,flag) {		
	asyncbox.confirm("确定是否删除此信息?", "信息窗口", function(action) {		
		if (action == "ok") {
			$.ajax({
				url : "emp.xhtml?action=deleteEmp",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"empId" :empId
				},
				success : function(content) {
					document.location.reload();
				}
			});
		}
	})
}
function editemp(empId){
	location.href ="emp.xhtml?face=editEmp&empId="+empId;
}

//对部门下拉列表连动
function searchList() {
	var deptCategory = "市交委机关";
	$.ajax({
			url : "../guest/SearchContent.xhtml?action=queryDeptInfoView",
			cache : false,
			type : "post",
			dataType : "text",
			data : {
				"deptCategory" : deptCategory
			},
			success : function(data) {
				var json = eval(data);

				var departmentInfoList = "<option value=''>-= 请选择  =-</option>";
				var dtext ="<option value='纪检组' >纪检组</option>";
				$.each(
								json,
								function(i, n) {
									if($("#catn").val() == n.deptName){
										
										if($("#catn").val() == "纪检组"){
											dtext = "<option value='纪检组' selected>纪检组</option>";
										}else{
											var deptName = n.deptName;
											departmentInfoList += "<option value='"+n.deptName+"' selected>"
											+ deptName
											+ "</option>";
										}
									}else{
										if(i != "17"){
											var deptName = n.deptName;
											departmentInfoList += "<option value='"+n.deptName+"' >"
											+ deptName
											+ "</option>";										
										}
										if(i == "19"){
											departmentInfoList += dtext;
										}
										
									}
								});
				$("#selectDeptName").html(departmentInfoList);
			},
			error : function(data) {
			}
		});
}

searchList();

//根据分类查询部门，并加载下拉连动	
$("#selectstatus").change(function() {
	searchList();
});



</script>
</html>
