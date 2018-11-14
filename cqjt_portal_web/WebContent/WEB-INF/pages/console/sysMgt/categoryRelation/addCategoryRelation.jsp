<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html 
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
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/plugin/ueditor/themes/default/css/ueditor.css" rel="stylesheet" type="text/css" />
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
#search {float: left;width:500px;}
a {color:blue;cursor: pointer;}
.column {min-width:400px;}
.error{color:red;padding-left: 10px;}
</style>
<title>内网与外网栏目对应关系管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		内网与外网栏目对应关系
        <span>-></span>
		新增功能
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-add"></b>新增功能</h2></div>
        <div class="con docEdit" >
			<form id="columnForm" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="innerCategory"/>
			<input type="hidden" name="outerCategory"/>
			<input type="hidden" name="innerId"/>
			<input type="hidden" name="outerId"/>
			<input type="hidden" id="categoryId" name="categoryId" value="${textCategoryById.categoryId}">
            <ul>
            	<li>
                	<label></label>
                    <a href="javascript:;" class="btn-gray" onclick="saveCategoryRelation()"><b class="icon i-send"></b>保存</a>
                    <a href="sysMgt.xhtml?face=listCategoryRelationView&categoryId=${textCategoryById.categoryId}" class="btn-gray"><b class="icon i-back"></b>返 回</a>
             	</li>
               <%--  <li>
                    <label><span style="color:red">*</span>创建人：</label>
                    <input type="text" name="createUser"  value="${userNo}" class="input input-small">
                    <label><span style="color:red">*</span>创建时间：</label>
                    <input type="text" name="createDate"  value="${createDate}" class="input input-small">
                </li>
                <li>
                    <label><span style="color:red">*</span>直接上网：</label>
                    <select class="select-small" name="isPublic" id="isPublicFlag" style="margin: 4px;">
                    	<option value="0">是</option>
                    	<option value="1">否</option>
	                </select>
                </li> --%>
               
                <!-- 内网栏目 -->
            <div class="column" id="search" style="height:500px; overflow:auto;">
            <label>内网栏目：</label>
            <ul>
            <c:forEach items="${textCategoryList}" var="t" varStatus="status">
            <li>
            	<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            	<input type="checkbox" name="inType" value="${t.categoryId}"/>${t.categoryName}
            	<input type="hidden" name="categoryName${status.index}" value="${t.categoryName}"/>
            </li>
            </c:forEach>
            </ul>
            </div>
            
            <!-- 外网栏目 -->
            <div class="column" id="Permission" style="width:500px; height:500px; overflow:auto;">
            <label>外网栏目：</label>
            <ul>
            <c:forEach items="${programAllList}" var="p">
            <li>
            	<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            	${p.name}
            		<c:forEach items="${programSubAllList}" var="sp" varStatus="status">
            		<c:if test='${sp.parentId==p.id}'>
           			 <li>
            			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            			<input type="checkbox" name="outType" value="${sp.id}"/>${sp.name}
            			<input type="hidden" name="name${sp.id}" value="${sp.name}"/>
            		</li>
            		</c:if>
            		</c:forEach>
            </li>
            </c:forEach>
            </ul>
            </div>
            </ul>
             
            </form>
        </div>
    </div>
    <!--column01 End-->
</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">

var categoryId = $("#categoryId").attr("value");
$("input[name='inType']:checkbox").each(function(){
	if($(this).val()==categoryId){
		$(this).attr("checked","checked");
	}
});

window.APP_HOME_URL = "${pageContext.request.contextPath}";
$(function(){
	UE.getEditor('myEditor');
});
function saveCategoryRelation() {
	
	var inTypeList=[];
	$("input[name='inType']:checked").each(function(){
		inTypeList= $(this).val();
	});
	
	var outTypeList=[];
	$("input[name='outType']:checked").each(function(){
		outTypeList= $(this).val();
	});
	
	if(inTypeList.length == 0){
		asyncbox.alert("请选择内网栏目！", "信息窗口");
		return;
	}else if(outTypeList.length == 0){
		asyncbox.alert("请选择外网栏目！", "信息窗口");
		return;
	}
	
	   var innerType = "";
	   var categoryName="";
	   var i=0;
		$("input[name='inType']").each(function(){
			if (this.checked) {
				if (innerType == ""){
					innerType = this.value;
					categoryName=$("input[name='categoryName"+i+"']").val();
					}else{
					innerType += "," + this.value;
					categoryName += "," + $("input[name='categoryName"+i+"']").val();
					}
			}
			i++;
		});
		
		var outerType = "";
		var name="";
		var outerName = "";
		$("input[name='outType']").each(function(){
			if (this.checked) {
				if (outerType == ""){ 
					outerType = this.value;
					name=$("input[name='name"+outerType+"']").val();
				}else{ 
					outerType += "," + this.value;
					outerName = this.value;
					name +="," + $("input[name='name"+outerName+"']").val();
				}
			}
		});
	
		$("input[name='innerCategory']").val(categoryName);
		$("input[name='outerCategory']").val(name);
		$("input[name='innerId']").val(innerType);
		$("input[name='outerId']").val(outerType);
		
	var options = {
		url : 'sysMgt.xhtml?action=saveCategoryRelation',
		type : 'post',
		dataType : "html",
		success : function(data) {			
			var content = eval('(' + data + ')');
			asyncbox.confirm("保存成功！" + "是否继续增加？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.reload();
					/* document.location.href = "sysMgt.xhtml?face=listCategoryRelationView"; */
					
				}else{
					 document.location.href = "sysMgt.xhtml?face=listCategoryRelationView&categoryId="+content.msg;
					/* document.location.href = "sysMgt.xhtml?face=queryCategoryRelation&categoryId="+content.msg; */
				}
			}); 
		},
		error :function(data){
			alert("内部错误，保存失败");
		}
	};
	$("#columnForm").ajaxSubmit(options);
}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.all.min.js"></script>
</html>
