<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>维护角色</title>
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<form id="uploadFile" enctype="multipart/form-data" method="POST"
		name="uploadFile">
		<div class="docEdit" style="padding-top: 30px;">
			<ul>
				<li>
					<input type="hidden" id=roleId name="roleId" value="${roleId}">
					<label for="title" class="red">名称：</label>
					<input value="${roleName}" type="text" id="roleName" name="roleName" class="input input-small" />
				</li>
				<li class="summary">
					<label>描述：</label>
					<input value="${description}" type="text" id="description" name="description" class="input input-small" />
				</li>
				<li class="infoCon clearfix">
					<label></label>
					<div class="btn">
						<a href="javascript:void(0);" onclick="addOrEditRole()" class="btn-gray"><b class="icon i-save"></b>保存</a>
					</div>
				</li>
			</ul>
		</div>
	</form>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
function addOrEditRole() {
	var options = {
		url : 'sysMgt.xhtml?face=addOrEditRole',
		type : 'post',
		dataType : "html",
		beforeSubmit : checkFormData,
		success : function(data) {
			var content = eval('(' + data + ')');
			
			asyncbox.alert(
				content.msg,
				"信息窗口",
				function() {
					if ($("#roleId").val()=="") {
						parent.location.reload();
					} else {
						$("#arrPermissionFrame", parent.document).removeAttr("src");
					}
				});

		}
	};
	$("#uploadFile").ajaxSubmit(options);
	return false;

}
function checkFormData() {
	if ($("#roleName").val() == "") {
		asyncbox.alert("请填写角色名称", "信息窗口");
		return false;
	}
}
</script>
</html>