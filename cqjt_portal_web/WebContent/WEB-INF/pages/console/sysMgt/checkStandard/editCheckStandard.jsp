<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>新增考核项</title>
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet"
	type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet"
	type="text/css" />
<link
	href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
#search {
	float: left;
	width: 600px;
}

#Permission {
	float: left;
	margin-left: 10px;
}

a {
	color: blue;
	cursor: pointer;
}
.inpu{
    margin: 3px; 
    padding: 5px 0px;
}
</style>
</head>
<body>
	<div>
		<!--path Start-->
		<div class="path">
			<b class="icon i-home"></b> <span>当前位置：</span> <a href="sysMgt.xhtml?face=listCheckStandardView">考核标准管理</a> <span>-></span>
			考核标准管理编辑
		</div>
		<!--path End-->
		<div class="column" id="search">
			<div class="title">
				<h2>
					<b class="icon i-search"></b>编辑考核项
				</h2>
			</div>
			<div class="con">
			   <form id="editCheckForm" enctype="multipart/form-data" method="POST">
					<div class="docEdit">
						<ul>
							<li><label class="red">名称：</label>
							   <input  type="text"  name="itemName" class="input input-small inpu" value="${checkInfo.itemName}"/>
							   <input  type="hidden"  name="checkId" value="${checkInfo.checkId}"/>
							  <!--  <input  type="hidden"  name="createDate" value="${checkInfo.createDate}"/> -->
							 </li>
							<li class="summary"><label>编码：</label>
							   <input type="text"name="code" class="input input-small inpu" value="${checkInfo.code}"/>
							 </li>
							<li class="summary"><label>分值：</label>
							   <input type="text"name="score" class="input input-small inpu" value="${checkInfo.score}"/>
							</li>
							<li class="summary"><label>奖金额度：</label>
							   <input type="text"name="reward" class="input input-small inpu" value="${checkInfo.reward}" />
							   <span style="color:gray">(单位：元)</span>
							</li>
							<li class="summary" ><label>创建人：</label> 
							   <input type="text" name="createUser" class="input input-small inpu" value="${checkInfo.createUser}"/>
							</li>

							<li class="infoCon clearfix">
								<div class="btn" style="margin-left:100px;margin-top:20px;">
									<a href="javascript:;" onclick="updateCheckInfo()"class="btn-gray"><b class="icon i-save"></b>保存</a> 
									<a href="sysMgt.xhtml?face=listCheckStandardView" class="btn-gray"><b class="icon i-back"></b>返 回</a>
								</div>
							</li>
						</ul>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
	$(function() {
		
	});

	function updateCheckInfo() {
		var options = {
				url : "sysMgt.xhtml?action=updateCheckInfo",
				type : "post",
				dataType : "html",
				success : function(data) {			
					//var content = eval('(' + data + ')');
					asyncbox.confirm("保存成功，是否跳转到列表页面？", "确认窗口", function(action) {
						if (action == "ok") {
							document.location.href = "sysMgt.xhtml?face=listCheckStandardView";
						}
					}); 
				},
				error :function(data){
					asyncbox.confirm("内部错误，保存失败", "信息窗口");
				}
			};
	  $("#editCheckForm").ajaxSubmit(options);
		
	}
</script>
</html>