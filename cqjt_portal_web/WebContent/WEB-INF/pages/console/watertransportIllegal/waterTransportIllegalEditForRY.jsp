<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公路水运工程违规行为编辑</title>
<link rel="stylesheet" type="text/css"
	href="${StaticResourcePath}/ww/css/sysManage.css" />
<link
	href="${StaticResourcePath}/ww/js/asyncbox/skins/ZCMS/asyncbox.css"
	type="text/css" rel="stylesheet" />
<script src="${StaticResourcePath}/ww/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${StaticResourcePath}/ww/js/jquery.form_3.25.js"></script>
<script type="text/javascript"
	src="${StaticResourcePath}/ww/js/asyncbox/AsyncBox.v1.4.js"></script>
<script
	src="${StaticResourcePath}/ww/js/My97DatePicker/WdatePicker.js"></script>
</head>
<style>
p {
	line-height: 6px;
	letter-spacing: 3px;
}

table {
	margin: 0 auto;
	font-size: 14px;
}

.inputTex {
	width: 100%;
}

.titleLabel {
	float: right;
}

.btn {
	width: 98%;
	margin-bottom: 60px;
}

a {
	text-decoration: none;
}

.searchBox {
	margin-top: 0px;
	padding: 0px 8px;
}
</style>

<script type="text/javascript">

	var ILLEGAL_BEHAVIOR_TYPE = '${waterTransportIllegal.ILLEGAL_BEHAVIOR_TYPE}';
	var arrts = ILLEGAL_BEHAVIOR_TYPE.split(',');
	$(function(){
		$(".ILLEGAL_BEHAVIOR_TYPE").each(function(){
			for(var i=0; i<arrts.length; i++) {
				if (arrts[i] == $(this).val()) {
					$(this).attr("checked","true"); 
				}
			}
		});
	});

	function saveData() {
		
		var i=0;
		$(".inputTex").each(function(){ 
			var vl=$(this).val();
			if(vl==""){
				i=1;
			}
		});
		$("textarea").each(function(){ 
			var vl=$(this).val();
			if(vl==""){
				i=1;
			}
		});
		if (i==1) {   
			alert('请将信息填写完整');
			return false;
		}

		$.ajax({
            cache: true,
            type: "POST",
            url: "waterTransport.xhtml?action=waterTransportIllegalEdit",
            data:$('#formData').serialize(),
            async: false,
            error: function(request) { 
				asyncbox.alert("新增失败", "信息窗口", function() {
// 					window.location.reload();
				});
            },
            success: function(data) {
            	if (data.status == '0') {
    				asyncbox.alert(data.msg, "信息窗口", function() {
    					document.location.href = "${StaticResourcePath}/console/waterTransport.xhtml?action=waterTransportIllegalManager";
    				});
            	} else if (data.status == '1') {
    				asyncbox.alert(data.msg, "信息窗口", function() {
//     					window.location.reload();
    				});
            	}
            }
        });
		
	}

</script>

<body style="background: white;">
	<h1 class="sys_pageTitle">编辑公路水运工程违规行为</h1>
	<div class="searchBox" style="width: 98%;">
		<form
		    id="formData" action="${StaticResourcePath}/console/waterTransport.xhtml?action=waterTransportIllegalEdit" method="post" >
			<table border="0" cellspacing="0" cellpadding="5">
				<tr>
					<input type="hidden" id="ID"
						name="ID" value="${waterTransportIllegal.ID}" >
					<input type="hidden" id="REPORT_FORM_TYPE"
						name="REPORT_FORM_TYPE" value="${REPORT_FORM_TYPE}" >
					<td width="15%"><label class="titleLabel">填报单位：</label></td>
					<td width="35%"><input class="inputTex" type="text"
						id="FILL_COMPANY" name="FILL_COMPANY" value="${waterTransportIllegal.FILL_COMPANY}"></td>
					<td width="15%"><label class="titleLabel">编号：</label></td>
					<td width="35%"><input class="inputTex" type="text"
						id="BIANHAO" name="BIANHAO" value="${waterTransportIllegal.BIANHAO}"></td>
				</tr>
				<tr>
					<td><label class="titleLabel">违法违规行为人员姓名：</label></td>
					<td><input class="inputTex" type="text"
						id="ILLEGAL_PERSONNEL_NAME" name="ILLEGAL_PERSONNEL_NAME"
						value="${waterTransportIllegal.ILLEGAL_PERSONNEL_NAME}"></td>
					<td><label class="titleLabel">统一社会信用代码：</label></td>
					<td><input class="inputTex" type="text"
						id="SOCIOLOGY_CREDIT_CODE" name="SOCIOLOGY_CREDIT_CODE"
						value="${waterTransportIllegal.SOCIOLOGY_CREDIT_CODE}"></td>
				</tr>
				<tr>
					<td><label class="titleLabel">身份证号码：</label></td>
					<td><input class="inputTex" type="text"
						id="ID_CARD_NO" name="ID_CARD_NO"
						value="${waterTransportIllegal.ID_CARD_NO}"></td>
					<td><label class="titleLabel">所在单位名称：</label></td>
					<td><input class="inputTex" type="text"
						id="WHERE_COMPANY_NAME" name="WHERE_COMPANY_NAME"
						value="${waterTransportIllegal.WHERE_COMPANY_NAME}"></td>
				</tr>
				<tr>
					<td><label class="titleLabel">项目及合同段：</label></td>
					<td><input class="inputTex" type="text"
						id="PROJECT_CONTRACT_SECTION" name="PROJECT_CONTRACT_SECTION"
						value="${waterTransportIllegal.PROJECT_CONTRACT_SECTION}"
						placeholder="违法违规行为所涉及的项目名称及合同段号"></td>
					<td><label class="titleLabel">填报时间：</label></td>
					<td><input class="inputTex" type="text" id="FILL_TIME"
						name="FILL_TIME" value="${waterTransportIllegal.FILL_TIME}" 
						onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="true" ></td>
				</tr>
				<tr>
					<td><label class="titleLabel">违法违规行为发生位置：</label></td>
					<td><input class="inputTex" type="text"
						id="ILLEGAL_BEHAVIOR_LOCATION" name="ILLEGAL_BEHAVIOR_LOCATION"
						value="${waterTransportIllegal.ILLEGAL_BEHAVIOR_LOCATION}"
						placeholder="填写具体桥梁、隧道、港口、等结构物名称或路基路面、航道桩号、实验室名称等"></td>
					<td><label class="titleLabel">工程等级：</td>
					<td><input class="inputTex" type="text" id="ENGINEERING_GRADE"
						name="ENGINEERING_GRADE" value="${waterTransportIllegal.ENGINEERING_GRADE}"
						placeholder="按照违法违规行为所涉及项目的相关技术标准规范填写"></td>
				</tr>
				<tr>
					<td><label class="titleLabel">认定文件：</label></td>
					<td><input class="inputTex" type="text" id="COGNIZANCE_FILE"
						name="COGNIZANCE_FILE" value="${waterTransportIllegal.COGNIZANCE_FILE}"
						placeholder="文件名称、发文机关、发文字号）（具体文件附后"></td>
					<td><label class="titleLabel">认定时间：</label></td>
					<td><input class="inputTex" type="text" id="COGNIZANCE_TIME"
						name="COGNIZANCE_TIME" value="${waterTransportIllegal.COGNIZANCE_TIME}"
						placeholder="认定文件的发文时间" 
						onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="true" ></td>
				</tr>
				<tr>
					<td><label class="titleLabel">填报人：</label></td>
					<td><input class="inputTex" type="text" id="APPLICANT"
						name="APPLICANT" value="${waterTransportIllegal.APPLICANT}"></td>
					<td><label class="titleLabel">联系电话：</label></td>
					<td><input class="inputTex" type="text" id="CONTACT_PHONE"
						name="CONTACT_PHONE" value="${waterTransportIllegal.CONTACT_PHONE}"></td>
				</tr>
				<tr>
					<td><label class="titleLabel">告知书发放时间：</label></td>
					<td><input class="inputTex" type="text"
						id="NOTIFICATION_GRANT_TIME" name="NOTIFICATION_GRANT_TIME"
						value="${waterTransportIllegal.NOTIFICATION_GRANT_TIME}" 
						onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="true" ></td>
					<td><label class="titleLabel">被告知单位是否提起申诉：</label></td>
					<td><input class="inputTex" type="text" id="IS_MENTION_APPEAL"
						name="IS_MENTION_APPEAL" value="${waterTransportIllegal.IS_MENTION_APPEAL}"></td>
				</tr>
				<tr>
					<td><label class="titleLabel">主要违法行为事实：</label></td>
					<td colspan="3"><textarea style="width: 100%; height: 90px;"
							id="ILLEGAL_BEHAVIOR_FACT" name="ILLEGAL_BEHAVIOR_FACT"
							placeholder="简单罗列发生的主要违法违规行为事实，涉及质量或生产安全事故的，应说明事故认定情况">${waterTransportIllegal.ILLEGAL_BEHAVIOR_FACT}</textarea>
					</td>
				</tr>
				<tr>
					<td><label class="titleLabel">责任处理结果：</label></td>
					<td colspan="3"><textarea style="width: 100%; height: 90px;"
							id="RESPONSIBILITY_HANDLE_RESULT"
							name="RESPONSIBILITY_HANDLE_RESULT"
							placeholder="交通主管部门对质量安全违法违规行为责任认定的处罚，如通报、罚款、停工、限制市场准入、降低或取消资质/资格等行政处罚">${waterTransportIllegal.RESPONSIBILITY_HANDLE_RESULT}</textarea>
					</td>
				</tr>
				<tr>
					<td><label class="titleLabel">项目建设状态：</label></td>
					<td><input type="radio" name="PROJECT_BUILD_STATE" value="1"  <c:if test="${waterTransportIllegal.PROJECT_BUILD_STATE eq 1}">  checked </c:if>  />在建 <input
						type="radio" name="PROJECT_BUILD_STATE" value="2" <c:if test="${waterTransportIllegal.PROJECT_BUILD_STATE eq 2}">  checked </c:if> />交工试运营 <input
						type="radio" name="PROJECT_BUILD_STATE"  value="3" <c:if test="${waterTransportIllegal.PROJECT_BUILD_STATE eq 3}">  checked </c:if> />竣工</td>
					<td><label class="titleLabel">项目类别：</label></td>
					<td><input type="radio" name="PROJECT_CATEGORY" value="1" <c:if test="${waterTransportIllegal.PROJECT_CATEGORY eq 1}">  checked </c:if> />公路 <input
						type="radio" name="PROJECT_CATEGORY" value="2" <c:if test="${waterTransportIllegal.PROJECT_CATEGORY eq 2}">  checked </c:if> />水运</td>
				</tr>
				<tr>
					<td><label class="titleLabel">项目投资类型：</label></td>
					<td colspan="3"><input type="radio"
						name="PROJECT_INVESTMENT_TYPE" value="1" <c:if test="${waterTransportIllegal.PROJECT_INVESTMENT_TYPE eq 1}">  checked </c:if> />全部国家投资 <input type="radio"
						name="PROJECT_INVESTMENT_TYPE" value="2" <c:if test="${waterTransportIllegal.PROJECT_INVESTMENT_TYPE eq 2}">  checked </c:if> />BOT <input type="radio"
						name="PROJECT_INVESTMENT_TYPE" value="3" <c:if test="${waterTransportIllegal.PROJECT_INVESTMENT_TYPE eq 3}">  checked </c:if> />BT <input type="radio"
						name="PROJECT_INVESTMENT_TYPE" value="4" <c:if test="${waterTransportIllegal.PROJECT_INVESTMENT_TYPE eq 4}">  checked </c:if> />PPP <input type="radio"
						name="PROJECT_INVESTMENT_TYPE" value="5" <c:if test="${waterTransportIllegal.PROJECT_INVESTMENT_TYPE eq 5}">  checked </c:if> />其它</td>

				</tr>
				<tr>
					<td><label class="titleLabel">违法违规行为类型：</label></td>
					<td colspan="3">
						<div style="float: left;">
							<input class="ILLEGAL_BEHAVIOR_TYPE" name="ILLEGAL_BEHAVIOR_TYPE" type="checkbox" value="发生质量或安全责任事故" <c:if test="${waterTransportIllegal.ILLEGAL_BEHAVIOR_TYPE eq '发生质量或安全责任事故'}">  checked="checked" </c:if> />发生质量或安全责任事故<br /> 
							<input class="ILLEGAL_BEHAVIOR_TYPE" name="ILLEGAL_BEHAVIOR_TYPE" type="checkbox" value="存在重大质量安全隐患问题"  <c:if test="${waterTransportIllegal.ILLEGAL_BEHAVIOR_TYPE eq '存在重大质量安全隐患问题'}"> checked="checked" </c:if>  />存在重大质量安全隐患问题<br /> 
							<input class="ILLEGAL_BEHAVIOR_TYPE" name="ILLEGAL_BEHAVIOR_TYPE" type="checkbox" value="质量安全举报经查实问题"  <c:if test="${waterTransportIllegal.ILLEGAL_BEHAVIOR_TYPE eq '质量安全举报经查实问题'}"> checked="checked" </c:if>  />质量安全举报经查实问题<br />
							<input class="ILLEGAL_BEHAVIOR_TYPE" name="ILLEGAL_BEHAVIOR_TYPE" type="checkbox" value="主要原材料及产品不合格"  <c:if test="${waterTransportIllegal.ILLEGAL_BEHAVIOR_TYPE eq '主要原材料及产品不合格'}"> checked="checked" </c:if>  />主要原材料及产品不合格<br />
							<input class="ILLEGAL_BEHAVIOR_TYPE" name="ILLEGAL_BEHAVIOR_TYPE" type="checkbox" value="其他严重质量安全违法违规行为"  <c:if test="${waterTransportIllegal.ILLEGAL_BEHAVIOR_TYPE eq '其他严重质量安全违法违规行为'}"> checked="checked" </c:if>  />其他严重质量安全违法违规行为
						</div>
						<div style="float: left;">
							<input class="ILLEGAL_BEHAVIOR_TYPE" name="ILLEGAL_BEHAVIOR_TYPE" type="checkbox" value="瞒报质量或安全责任事故"   <c:if test="${waterTransportIllegal.ILLEGAL_BEHAVIOR_TYPE eq '瞒报质量或安全责任事故'}"> checked="checked" </c:if>  />瞒报质量或安全责任事故<br />
							<input class="ILLEGAL_BEHAVIOR_TYPE" name="ILLEGAL_BEHAVIOR_TYPE" type="checkbox" value="拒不整改质量安全隐患问题"  <c:if test="${waterTransportIllegal.ILLEGAL_BEHAVIOR_TYPE eq '拒不整改质量安全隐患问题'}"> checked="checked" </c:if>  />拒不整改质量安全隐患问题<br />
							<input class="ILLEGAL_BEHAVIOR_TYPE" name="ILLEGAL_BEHAVIOR_TYPE" type="checkbox" value="被列为安全生产重点监管名单"  <c:if test="${waterTransportIllegal.ILLEGAL_BEHAVIOR_TYPE eq '被列为安全生产重点监管名单'}"> checked="checked" </c:if>  />被列为安全生产重点监管名单<br />
							<input class="ILLEGAL_BEHAVIOR_TYPE" name="ILLEGAL_BEHAVIOR_TYPE" type="checkbox" value="被交通主管部门通报或行政处罚"  <c:if test="${waterTransportIllegal.ILLEGAL_BEHAVIOR_TYPE eq '被交通主管部门通报或行政处罚'}"> checked="checked" </c:if>  />被交通主管部门通报或行政处罚
						</div>
					</td>
				</tr>
			</table>
		</form>
		<div class="btn">
			<a href="javascript:;" onclick="saveData()" class="btnBlue77_32">提交</a>
			<a href="javascript:;" onclick="history.go(-1)" class="btnBlue77_32">返回</a>
		</div>
	</div>
</body>
</html>