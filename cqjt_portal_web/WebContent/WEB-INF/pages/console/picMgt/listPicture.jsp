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
</style>
<title>图片信息查询</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		内容管理
        <span>-></span>
		图片信息查询
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>图片新闻</h2></div>
        <div class="con">
        		<label>标题：</label><input type="text" name="picTitle" class="input-text" value="${picTitle}">
        		<label>发稿人：</label><input type="text" name="entryUser" class="input-text" value="${entryUser}">
                <label>发稿时间：</label>
                <div class="data" id="dataSelect-start">
                    <input type="text"  name="entryDateS" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal">
                </div>
                <span>&nbsp;～</span>
                <div class="data" id="dataSelect-end">
                	<input type="text" name="entryDateE" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>'  class="input-text tcal" >
                </div>
                <a href="javascript:;" onclick="selectList();return false;" class="btn btn-blue">搜 索</a>
                <a href="pic.xhtml?face=addPic" class="btn btn-blue">新  增</a>
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
                    <td width="15%">标题</td>
                    <td width="20%">主图</td>
                    <td width="10%">发稿时间</td>
                    <td width="8%">状态</td>
                    <td width="8%">发稿人</td>
                    <td width="5%">编辑</td>
                    <td width="5%">删除</td>
                  </tr>
                </thead>
                <tbody id="tbodyVideo">
                <c:forEach items="${paginations}" var="tn" varStatus="status">
					<tr>
						<td>${tn.rowNo}</</td>
                        <th><a href="javascript:;" onclick="editPic('${tn.picId}');return false;"><span style='color:blue'>${tn.picTitle}</span></a></th>      
                        <td>
                        <img style="width:80px;height:60px;"src="${pageContext.request.contextPath}/${tn.picUrl}">
                        </td>
                        <td>${tn.entryDate}</td>
                       <td>
                            <c:if test='${tn.flag==3}'>已撤销</c:if>
	                        <c:if test='${tn.flag==1}'>已投稿</c:if>
	                        <c:if test='${tn.flag==2}'>已审核</c:if>
	                        <c:if test='${tn.flag==0}'>草稿</c:if>
                        </td>
                        <td>${tn.entryUser}</td>
                        <td><a href="javascript:;" onclick="editPic('${tn.picId}');return false;"class="icon i-edit"></a></td>
                        <td><a  href="javascript:;" onclick="deletePic('${tn.picId}','${tn.flag}');return false;" class="icon i-del-02"></a></td>          
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

function selectList() {
	var picTitle = encodeURI(encodeURI($("input[name='picTitle']").val()));
	var entryUser = encodeURI(encodeURI($("input[name='entryUser']").val()));
	var entryDateS =  $("input[name='entryDateS']").val();
	var entryDateE =  $("input[name='entryDateE']").val();
	var pageIndex = $("#userListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="pic.xhtml?face=searchPicList&picTitle="+picTitle+"&entryUser="+entryUser+"&entryDateS="
	+entryDateS+"&entryDateE="+entryDateE+"&pageIndex="+pageIndex;

}

function deletePic(picId,flag) {		
	asyncbox.confirm("确定是否删除此信息?", "信息窗口", function(action) {		
		if (action == "ok") {
			$.ajax({
				url : "pic.xhtml?action=deletePic",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"picId" :picId
				},
				success : function(content) {
					document.location.reload();
				}
			});
		}
	})
}
function editPic(picId){
	location.href ="pic.xhtml?face=editPic&picId="+picId;
}

</script>
</html>
