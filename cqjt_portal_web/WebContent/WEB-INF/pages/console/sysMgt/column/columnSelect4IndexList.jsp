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
#search {float: left;width:100%;hight:100%}
  a {color:blue;cursor: pointer;} 
</style>
<title>系统管理</title>
</head>
<body>
<div>
	 <div class="column" id="search">
		<div class="title"><h2><b class="icon i-search"></b>栏目查询模板关系管理
        	<span style="float:right;margin-right:10px;margin-top:10px">
				  	<a onclick="addColumnSelect('${id}')" class="btn btn-blue">新  增</a>
				 </span>
			</h2>
        </div>
        <div class="con" style="margin-top:10px"">
        <table width="100%" border="1" class="dataList">
       		  <thead>
                  <tr>
						<td width="5%" >序号</td>
						<td width="20%">所属模块</td>
						<td width="20%" >栏目名称</td>
						<td width="30%" >操作</td>
					</tr>
                </thead>
               <tbody id="tbodyDept">
            		  <c:forEach items="${page}" var="tn" varStatus="status">
             			 <tr>
						  	<td>${tn.rowNo}</td>
						  	<td>首页</td>
						  	<td>${tn.categoryName}</td>
						  	 <td>
						  		[<a href="javascript:;" onclick="deleteCategoryInfo('${tn.categoryId}');return false;">删除栏目</a>] 
						  	</td> 
						  	
							
						</tr>
					</c:forEach>
			    </tbody>
			 </table>	 
    
		</div>
		</div>
		<!--column01 End-->
		</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript">
window.APP_HOME_URL = "${pageContext.request.contextPath}";
//删除
function deleteCategoryInfo(categoryId) {
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) {
		if (action == "ok") {
			$.ajax({
				url : "column.xhtml?action=delete",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"categoryId" : categoryId,
					"id"     : ${id},
				},
				success : function(data) {
					var content = eval('(' + data + ')');
					/* document.location.reload(); */
					document.location.href="column.xhtml?face=queryCategoryUnSelectList&id="+content.msg; 
				}
			});
		}
	})
} 

function addColumnSelect(id) {
	asyncbox.open({
	    title:"部门对应栏目添加窗口",
		id:"columnDeptMgtAddWindow",
		modal: true,
	　　  width : 700,
	　　 height : 400,
	    url: "column.xhtml?face=getCategoryUnSelectList&id="+id,
	    btnsbar:$.btn.OKCANCEL,
	    callback : function(action){
	        if (action=="ok") {
	        	var contents = $("#columnDeptMgtAddWindow_content").contents();
	        	$.each(contents.find("#tbodyNews input:checked"), function(i, n) {
	        		var saveUrl = "column.xhtml?action=saveSelectInfo";
	        		$.ajax({
	        			url : saveUrl,
	        			data : {
	        				categoryId : $(n).attr("id"),
	        				id : id
	        			},
	        			async : false, //同步，保存完成才会继续下一个操作
	        			success : function() {
	        				/* console.log(saveUrl); */
	        				asyncbox.confirm("保存成功!" , "确认窗口", function(action) {
	    							document.location.href="column.xhtml?face=queryCategoryUnSelectList&id="+id;
	    					}); 
	        			}
	        		});
	        	});
	        }
	    }
	});
} 


</script>
</html>
