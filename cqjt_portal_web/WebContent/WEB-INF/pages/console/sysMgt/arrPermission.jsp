<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>菜单权限分配</title>
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/plugin/zTree/zTreeStyle/zTreeStyle.css"
	rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
	<div class="docEdit">
		<div class="btn">
			<a href="javascript:void(0);" onclick="savePermission()" class="btn-gray"><b class="icon i-save"></b>保存</a>
		</div>
		<div>
			<div>
				<ul id="treeDemo" class="ztree"></ul>
			</div>
		</div>
		<div class="btn">
			<a href="javascript:void(0);" onclick="savePermission()" class="btn-gray"><b class="icon i-save"></b>保存</a>
		</div>
		<input type="hidden" id="roleID" name="roleID" value="${roleID}" />
	</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/zTree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/zTree/jquery.ztree.excheck-3.5.min.js"></script>
<SCRIPT type="text/javascript">
	var setting = {
		check : {
			enable : true
		},
		data : {
			simpleData : {
				enable : true
			}
		}
	};

	var zNodes = ${treeContents};
	var code;

	function setCheck() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"), py = "p", sy = "s", pn = "p", sn = "s", type = {
			"Y" : py + sy,
			"N" : pn + sn
		};
		zTree.setting.check.chkboxType = type;
		showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "'
				+ type.N + '" };');
	}

	function showCode(str) {
		if (!code)
			code = $("#code");
		code.empty();
		code.append("<li>" + str + "</li>");
	}

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		setCheck();
	});

	function savePermission() {
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);
		var nodesIDArr = "";
		for ( var i = 0, l = nodes.length; i < l; i++) {
			nodesIDArr = nodesIDArr + nodes[i].id + ";";
		}
		$.ajax({
			url : "sysMgt.xhtml?face=savePermission",
			cache : false,
			type : "POST",
			dataType : "text",
			data : {
				"nodesIDArr" : nodesIDArr,
				"roleID" : $("#roleID").val()
			},
			success : function(content) {
				alert('保存成功');
				$("#arrPermissionFrame", parent.document).removeAttr("src");
			},
			error : function() {
				alert("保存失败");
			}
		});
	}
</SCRIPT>
</html>