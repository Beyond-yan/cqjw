<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css" />
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
</style>
<title>资料管理</title>
</head>
<body>
	<div>
		<!--path Start-->
		<div class="path">
			<b class="icon i-home"></b> <span>当前位置：</span> 系统管理 <span>-></span>
			红黑企业查询
		</div>
		<!--path End-->
		<!--column01 Start-->
		<div class="column" id="search">
			<div class="title">
				<h2>
					<b class="icon i-search"></b>红黑企业查询
				</h2>
			</div>
			<div class="con">
				<label>企业（人员）名称：</label><input type="text" name="name" id="name"
					class="input-text" value=""> 
				<label>统一社会信用代码：</label><input type="text" name="code" id="code"
					class="input-text" value=""> <label>查询类别：</label>
				<select id="selectClass" style="height:28px; width: 100px;" >
					<option value="z" title="请选择" > -- 请选择 -- </option>
					<option id="corpblack" value="corpblack" title="企业黑名单" >企业黑名单</option>
					<option id="corpred" value="corpred" title="企业红名单" >企业红名单</option>
					<option id="personblack" value="personblack" title="人员黑名单">人员黑名单</option>
				</select>
				<a href="javascript:;" style="margin: 0 10px 0 40px;" onclick="searchList()"
					class="btn btn-blue">搜 索</a>
			</div>
		</div>
		<!--column01 End-->
		<!--column02 Start-->
		<div class="column">
			<div class="title">
				<h2>
					<b class="icon i-search"></b>红黑企业查询列表
				</h2>
			</div>
			<div class="con">
				<table width="100%" border="1" class="dataList">
					<thead>
						<tr id="titleTr" >
							
						</tr>
					</thead>
					<tbody id="tbodyFile">
					
					</tbody>
				</table>
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
	$(function() {

	});

	function searchList() {
		var submmitUrl = "";
		var name = $('#name').val();
		var code = $('#code').val();
		var selectClass = $('#selectClass').val();
		
		if (selectClass == 'corpblack') {
			submmitUrl = "redBalckQuery.xhtml?action=enterpriseBlacklist";
		} else if (selectClass == 'corpred') {
			submmitUrl = "redBalckQuery.xhtml?action=enterpriseRedlist";
		} else if (selectClass == 'personblack') {
			submmitUrl = "redBalckQuery.xhtml?action=personalBlacklist";
		}

		if (!name || ! code || selectClass=='z') {
			alert("请将信息填写完成");
		} else {
			$.ajax({
				url : submmitUrl,
				cache : false,
				type : "POST",
				dataType : "text",
				data : {
					"name" : name,
					"code" : code
				},
				success : function(content) {
					var data = eval('(' + content + ')');
					if (data.result == 'success') {
						createHtmlNew(data);
					} else {
						alert("查询失败，请稍后再试")
					}
				}
			});
		}
	}
	
	function createHtmlNew(data) {

		var lists = data.lists;
		if (lists.length < 1) {
			$('#titleTr').empty();
			$('#tbodyFile').empty();
			alert("未查询到数据");
			return false;
		}
		
		// create title
		var title = data.dataTitle;
		var titles = title.split(",");
		var titleHtml = "";
		for (var i=0; i < titles.length; i++) {
			titleHtml += "<td style='width=20%;' >"+titles[i]+"</td>";
		}
		$('#titleTr').empty().append(titleHtml);
		
		// create data
		var dataHtml = "";
		if (data.dataType == '1') {
			for (var j=0; j < lists.length; j++) {
				dataHtml += "<tr>";
				dataHtml += "<td>"+lists[j].corpname+"</td>";
				dataHtml += "<td>"+lists[j].creditcode+"</td>";
				dataHtml += "<td>"+lists[j].reason +"</td>";
				dataHtml += "<td>"+lists[j].black_type_name+"</td>";
				dataHtml += "<td>"+lists[j].auth_organ_name+"</td>";
				dataHtml += "<td>"+lists[j].auth_time+"</td>";
				dataHtml += "</tr>";
			}
		} else if (data.dataType == '2') {
			for (var j=0; j < lists.length; j++) {
				dataHtml += "<tr>";
				dataHtml += "<td>"+lists[j].corpname+"</td>";
				dataHtml += "<td>"+lists[j].creditcode+"</td>";
				dataHtml += "<td>"+lists[j].reason+"</td>";
				dataHtml += "<td>"+lists[j].red_type_name+"</td>";
				dataHtml += "<td>"+lists[j].auth_organ_name+"</td>";
				dataHtml += "<td>"+lists[j].auth_time+"</td>";
				dataHtml += "</tr>";
			}
		} else if (data.dataType == '3') {
			for (var j=0; j < lists.length; j++) {
				dataHtml += "<tr>";
				dataHtml += "<td>"+lists[j].personname+"</td>";
				dataHtml += "<td>"+lists[j].id_num+"</td>";
				dataHtml += "<td>"+lists[j].reason+"</td>";
				dataHtml += "<td>"+lists[j].black_type_name+"</td>";
				dataHtml += "<td>"+lists[j].auth_organ_name+"</td>";
				dataHtml += "<td>"+lists[j].auth_time+"</td>";
				dataHtml += "</tr>";
			}
		}
		$('#tbodyFile').empty().append(dataHtml);
	}
	
</script>
</html>
