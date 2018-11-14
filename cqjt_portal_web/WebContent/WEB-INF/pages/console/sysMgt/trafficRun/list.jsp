<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>资料管理</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		系统管理
        <span>-></span>
		交通运行信息
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
        <!-- 	<div style="margin-bottom: 10px;"> -->
        		<label>名称：</label><input type="text" name="runnTitle" class="input-text" value="${runnTitle}">
        		
        		<label>创建时间：</label>
                <div class="data" id="dataSelect-start">
                    <input type="text"  name="beginDate" value='<fmt:formatDate value="${beginDate}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
                </div>
                <span>&nbsp;～</span>
                <div class="data" id="dataSelect-end">
                	<input type="text" name="endDate" value='<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput" >
                </div>
                <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">搜  索</a>
                <a href="trafficRun.xhtml?face=addPage" class="btn btn-blue">新  增</a>
        	</div>
     </div>
     <!--column01 End-->
     <!--column02 Start-->
     <div class="column" >
          <div class="title"><h2><b class="icon i-search"></b>交通运行信息列表</h2></div>
            <div class="con">
                <input type="hidden"  id="totalCount" value="${page.recoredCount}" />
                <input type="hidden" id="pageSize" value="${page.pageCount}" />
                <input type="hidden" id="pageIndex" value="${page.pageIndex}" />
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>                    
                    <td width="10%">序号</td>
                    <td width="30%">名称</td>
                    <td width="10%">发布人</td>
                    <td width="10%">是否置顶</td>
                    <td width="15%">创建时间</td>
                    <td width="15%">修改时间</td>
                    <td width="20%">操作</td>
                  </tr>
                </thead>
		               <tbody id="tbodyFile">
		               <c:forEach items="${runVoList}" var="runVo" varStatus="status">
						<tr>
							<td>${status.index+1}</td>
		                    <td><a href="/console/trafficRun.xhtml?face=editPage&runnId=${runVo.runnId}" ><span style='color:blue'>${runVo.runnTitle}</span></a></td>
		                    <td>${runVo.runnCreateUserName}</td>
		                    <td>${runVo.isTop}</td>
		                    <td>${runVo.runnCreateDateStr}</td>
		                    <td>${runVo.runnEditDateStr}</td>
		                    <td><a href="javascript:;" onclick="editRunnVo('${runVo.runnId}');return false;" class="icon i-edit"  title="编辑"></a>
		                       &nbsp;&nbsp;
		                       <a href="javascript:;"  onclick="deleteRunVo('${runVo.runnId}');return false;" class="icon i-del" title="删除"></a></td>
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
	$("#textInfoListPager").buildPager({
		totalLines: '${page.recoredCount}',
		pageSize: '${page.limitCount}',
		startIndex: '${page.pageIndex}',
		displayNum:5,
		afterChange:function() {
			searchList();
		}
	});
});

function searchList() {
	var runnTitle = encodeURI(encodeURI($("input[name='runnTitle']").val()));
	var beginDate = $("input[name='beginDate']").val();
	var endDate = $("input[name='endDate']").val();
	var pageIndex = $("#textInfoListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 1;
	}
	location.href ="trafficRun.xhtml?face=queryPageList&runnTitle="+runnTitle+"&beginDate="+beginDate+"&endDate="
	+endDate+"&pageIndex="+pageIndex;
}


//删除信息
function deleteRunVo(runnId) {
		asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) { 
		if (action == "ok") {
			$.ajax({
				url : "trafficRun.xhtml?action=del",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"runnId" : runnId
				},
				success : function(content) {
					document.location.reload();
				}
			});
		}
	})
}

//编辑
function editRunnVo(runnId){
	location.href ="trafficRun.xhtml?face=editPage&runnId="+runnId;
}

</script>
</html>
