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
			<b class="icon i-home"></b> <span>当前位置：</span> 考核标准管理 <span>-></span>
			新增考核项
		</div>
		<!--path End-->
		<div class="column" id="search">
			<div class="title">
				<h2>
					<b class="icon i-search"></b>新增考核项
				</h2>
			</div>
			<div class="con">
			   <form id="checkForm" enctype="multipart/form-data" method="POST">
					<div class="docEdit">
						<ul>
							<li><label class="red">名称：</label>
							   <input  type="text"  name="itemName" class="input input-small inpu"/>
							 </li>
							<li class="summary"><label>编码：</label>
							   <input type="text"name="code" class="input input-small inpu" id="code" onchange="checkCode(this);"/>
							 </li>
							<li class="summary"><label>分值：</label>
							   <input type="text"name="score" class="input input-small inpu" />
							</li>
							<li class="summary"><label>奖金额度：</label>
							   <input type="text"name="reward" class="input input-small inpu" />
							   <span style="color:gray">(单位：元)</span>
							</li>
							<li class="summary" ><label>创建人：</label> 
							   <input type="text" name="createUser" "class="input input-small inpu" value="${userName}"/>
							</li>

							<li class="infoCon clearfix">
								<div class="btn" style="margin-left:100px;margin-top:20px;">
									<a href="javascript:;" onclick="saveCheckInfo();return false;"class="btn-gray"><b class="icon i-save"></b>保存</a> 
									<a href="javascript:history.go(-1);" class="btn-gray"><b class="icon i-back"></b>返 回</a>
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
<!--<script language=JavaScript> window.opener.location.reload(); </script>--> 
<script language=JavaScript> self.opener.location.reload(); </script>
<script type="text/javascript">
function checkCode(obj){
	var code = obj.value;
	if(null == code ||code == "") return false;
	$.ajax({
        type: "post",
        cache : false,
        dataType : "text",
        url:"sysMgt.xhtml?action=checkInfo",
        data : {
			"code" :code
		},
        success: function(data){
        	var content = eval('(' + data + ')');
	        	if(content.msg=="false"){
	        		asyncbox.confirm("该编码已存在,请重新编码！点击‘确定’或‘取消’后,重新填写编码信息！","信息提示", function(action) {
	    				if (action == "ok") {
	    					document.location.href = "sysMgt.xhtml?face=addCheckInfoView";
	    				 }
	    				else{
	    					document.location.href = "sysMgt.xhtml?face=addCheckInfoView";
	    				}
	    			})	
	       		}
         },
         error:function(data){
        	 alert('事件保存AJAX调用失败');
         }
     });
}
	function saveCheckInfo() {
		var options = {
				url : "sysMgt.xhtml?action=saveCheckInfo",
				type : "post",
				dataType : "html",
				success : function(data) {	
					var content = eval('(' + data + ')');
					asyncbox.confirm(content.msg+"是否继续添加信息,点击‘确定’后,继续添加信息,点击“取消”后,返回到列表显示页面！" ,"确认窗口", function(action) {
						if (action == "ok") {
							document.location.href = "sysMgt.xhtml?face=addCheckInfoView";
						}
						else{
							document.location.href = "sysMgt.xhtml?face=listCheckStandardView";
						}
					}); 
				},
				error :function(data){
					asyncbox.confirm("内部错误，保存失败", "信息窗口");
				}
			};
	  $("#checkForm").ajaxSubmit(options);
	}
</script>
</html>