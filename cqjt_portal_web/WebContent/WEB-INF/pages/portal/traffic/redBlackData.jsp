<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="c" 

uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib
	prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@page import="com.gdssoft.core.tools.SystemContext"%><!doctype 

html>
<html>
<head>
<meta charset="utf-8">
<title>详细信息</title>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/layer/layer.js"></script>
</head>
<body>
	<style>
.dataList {
	width: 90%;
	margin: 0 auto;
	border-collapse: collapse;
}

.inputp {
	width: 90%;
	margin: 0 auto;
}
.inputpHidden {
	width: 90%;
	margin: 0 auto;
        display:none;
}

.databody {
	width: 100%;
	text-align: center;
}
 .bott {
	width: 100%;
	text-align: center;
}
 .bottHidenn {
	width: 100%;
	text-align: center;
    display:black;
}
.con {
	width: 89%;
	height: 35px;
	margin: 5px 0px 2px 0px;
}

.redblackList {
	width: 910px;
	border-collapse: collapse;
}
.one-td {
	width: 225px;
}
.two-td {
	width: 450px;
}
.three-td {
	width: 675px;
}
</style>

	<script>
		var redBlackIndex = 0;
		var RBrbTypeList = [];
        var RBdetailList = [];
		// 回传URL
        var urldata_pre = '../console/redBalckQuery.xhtml?action=commitRedBlackBack&';
		var urldata = urldata_pre;

		// 请求参数
		$(function() {

			$.ajax({
				url : "../console/redBalckQuery.xhtml?action=enterpriseRedlist",
				cache : false,
				type : "POST",
				dataType : "json",
				data : {
// 					var url = '../console/redBalckQuery.xhtml?action=enterpriseRedlistpage&name='+name+'&code='+code+'&type='+type+'&num='+num;
// 					url = encodeURI(url);
					
					"name" : $('#RBname').val(),
					"code" : $('#RBcode').val(),
					"type" : $('#RBtype').val(),
					"rednum" : $('#RBrednum').val(),
                    "blacknum" : $('#RBblacknum').val(),
					"recordId" : $('#RBrecordId').val()
				},
				success : function(data) {
					var record = data.record ? data.record : {};
                    createList(data.rbTypeList, record);

					// createData(data);
				}
			});
		});

        /**
		 * 创建列表（进入页面）
		 *
         * @param rbTypeList
         */
		function createList(rbTypeList, record) {
		    var dataList = rbTypeList;
			if (!rbTypeList || rbTypeList.length <= 0) {
                dataList = [];
			}
			var headHtml = "<thead><tr><td><div class='one-td'>序号</div></td><td colspan=\"2\"><div class='two-td'>描述</div></td><td><div class='one-td'>操作</div></td></tr></thead>";
			var html = "<tbody>";
            var rednum = $("#RBrednum").val().split(",");
            var blacknum = $("#RBblacknum").val().split(",");
			var redDes = $("#RBredDes").val().split(",");
            var blackDes = $("#RBblackDes").val().split(",");
            html += appendRedBlackList(rednum, redDes, dataList, record);
            html += appendRedBlackList(blacknum, blackDes, dataList, record);
            html += "</tbody>";
            $("#RBredblackList").html(headHtml + html);
		}

        /**
		 * 创建列表的数据
		 *
         * @param rednum
         * @param redDes
         * @param dataList
         * @returns {string}
         */
		function appendRedBlackList(rednum, redDes, dataList, record) {
		    var commitInfo = record && record.commitInfoJson ? record.commitInfoJson : {};
		    var commitDetailId = $("#RBcommitDetailId").val();
		    var html = "";
            for (var i = 0; i < rednum.length; i++) {
                var count = 0;
                var redid = rednum[i];
                if (redid == "") {
                    continue;
                }
                html += "<tr><td>"+(++redBlackIndex)+"</td><td colspan=\"2\">"+redDes[i]+"</td><td><div></div></td></tr>";
                for (var j = 0; j < dataList.length; j++) {
                    var unitedId = dataList[j]["unitedId"];
                    if (unitedId == redid) {
                        count++;
                        var detailList = dataList[j]["redblackDetailList"];
                        for (var k = 0; k < detailList.length; k++) {
                            var redDetail =detailList[k];
                            var redDetailList = redDetail["redblackDetailContentList"];
                            // 判断该条记录是否已经提交成功
                            var isCommit = "false";
                            if (commitDetailId != "" && commitDetailId.indexOf(redDetail.id) > -1) {
                                isCommit = "true";
                            }

                            redDetail["commitInfo"] = commitInfo[redDetail.id];
                            redDetail["isCommit"] = isCommit;
                            RBrbTypeList.push(dataList[j]);
                            RBdetailList.push(redDetail);
                            var ccontent = "";
                            var cindex = 0;
                            for (var m = 0; m < redDetailList.length; m++) {
                                if (redDetailList[m]["content"] && redDetailList[m]["content"].length > 0) {
                                    ccontent += redDetailList[m]["title"]+":"+redDetailList[m]["content"] + " ";
                                    cindex ++;
								}
								if (cindex >= 2) {
                                    break;
								}
							}
                            var btnMsg = "提交反馈";
							if (isCommit == "true") {
                                btnMsg = "查看详情";
                            }
                            html += "<tr style='background-color: #eee;'><td></td><td style='width: 50px;'>("+(k+1)+")</td><td align=\"left\">"+ccontent+"</td><td align=\"center\" ><button type=\"button\" value="+unitedId+" onclick=\"createData("+(RBdetailList.length-1)+", '"+unitedId+"', '"+isCommit+"');\" >"+btnMsg+"</button></td></tr>";
                        }
                    }
                }
                if (count <= 0) {
                    html += "<tr><td></td><td align=\"left\" colspan=\"3\">暂无数据</td></tr>";
                }
            }
			return html;
		}

		/**
         * 填充详情参数
         */
		function createData(index, unitedId, isCommit) {
            var rbType = RBrbTypeList[index];
            var redblackDetail = RBdetailList[index];
            var commitInfo = redblackDetail.commitInfo;
            var unionPairCommitList = commitInfo && commitInfo.unionPairs ? commitInfo.unionPairs : [];
            var data = redblackDetail["redblackDetailContentList"];
			var dataList = '';
			for (var i = 0; i < data.length; i ++) {
				dataList += '<tr><th>' + data[i].title + '</th>';
				dataList += '<th>' + data[i].content + '</th></tr>';
			}
			$('#RBdata').empty().append(dataList);

			// 补充表单，奖惩措施实施
            var rbType = RBrbTypeList[index];
			if (rbType.unionPairList && rbType.unionPairList.length > 0) {
			    var unionPairList = rbType.unionPairList;
			    var html = "";
                for (var i = 0; i < unionPairList.length; i++) {
                    var unionPair = unionPairList[i];
					// 查询提交过的措施内容，
                    var unionPairCommitContent = "";
                    for (var c = 0; c < unionPairCommitList.length; c++) {
                        if (unionPair.itemName && unionPair.itemName != "" && unionPairCommitList[c]["itemName"] == unionPair.itemName) {
                            unionPairCommitContent = unionPairCommitList[c]["unionContent"];
                            if (!unionPairCommitContent) {
                                unionPairCommitContent = "";
							}
                            break;
						}
					}
                    html += "<tr class='unionPair-tr'><td>备忘录名称:</td><td colspan=\"3\" align=\"left\"><input class='con itemName' value='"+unionPair.itemName+"' style='width: 570px;' readonly /></td></tr>";
                    html += "<tr class='unionPair-tr'><td>措施:</td><td colspan=\"3\" align=\"left\" style='position: relative; padding-bottom: 10px; height: 50px;'>";

                    var unionPairContentsStr = "";
                    for (var j = 0; j < unionPair.contents.length; j++) {
                        unionPairContentsStr += unionPair['contents'][j];
                        if (j != unionPair.contents.length - 1) {
                            unionPairContentsStr += ",";
                        }
                    }
                    html += "<textarea class='con union-content' itemName='"+unionPair.itemName+"' originDept='"+unionPair.originDept+"' data-for='"+unionPair.itemName+"' style='width: 490px; height: 45px; position: absolute; top: 0px; left: 0px;' readonly >"+unionPairCommitContent+"</textarea>";
					if (isCommit && isCommit == "true") {
					} else {
                        html += "<button style=' position: absolute; top: 10px; left: 505px;' type=\"button\" value='"+unionPairContentsStr+"' data-for='"+unionPair.itemName+"' onclick=\"selectUnionPairContents(this)\">选择措施</button>";
					}
					html += "</td></tr>";

                    $(".unionPair-tr").remove();
					$("#inputp-data-table").append(html);
				}
			}


			callData(index, unitedId, isCommit);
	
		}

		// 组装回传参数
		function callData(index, unitedId, isCommit) {

            urldata = urldata_pre;
			var redblackDetail = RBdetailList[index];
			var redblackDetailContentList = redblackDetail["redblackDetailContentList"];
			var rbType = RBrbTypeList[index];
			// 已反馈提交信息
            var commitInfo = redblackDetail.commitInfo ? redblackDetail.commitInfo : {};

            commitInfo.content = commitInfo.content ? commitInfo.content : "";
            commitInfo.remark = commitInfo.remark ? commitInfo.remark : "";
            commitInfo.action = commitInfo.action ? commitInfo.action : "";
            commitInfo.handleType = commitInfo.handleType ? commitInfo.handleType : "0";

			// 再次打开详情页面窗口，把输入框清空
            $('#content').val(commitInfo.content);
            $('#remark').val(commitInfo.remark);
            $('#action').val(commitInfo.action);
            $('#handleType').val(commitInfo.handleType);

            // 已提交
			if (redblackDetail.isCommit && redblackDetail.isCommit == "true") {
                $('#content').attr("readonly", true);
                $('#remark').attr("readonly", true);
                $('#action').attr("readonly", true);
                $('#handleType').attr("disabled", true);
			} else {
                $('#content').attr("readonly", false);
                $('#remark').attr("readonly", false);
                $('#action').attr("readonly", false);
                $('#handleType').attr("disabled", false);
			}

			// var unionPairContentList = obj.unionPairContentList;

			var title = '';
			var content = '';

			for (var j = 0; j < redblackDetailContentList.length; j++) {
				if (j == redblackDetailContentList.length - 1) {
					title += redblackDetailContentList[j].title;
                    content += redblackDetailContentList[j].content;
				} else {
					title += redblackDetailContentList[j].title + ",";
					content += redblackDetailContentList[j].content + ",";
				}
			}
            urldata += "recordId=" + $("#RBrecordId").val() + "&";
			urldata += "requestId=" + rbType.requestId + "&";
			urldata += "title3=" + title + "&";
			urldata += "content3=" + content + "&";
			
// 			alert(content);
// 			urldata += "unionPairContentList=" + unionPairContentList[0] + "&";

			urldata += "unitedName=" + rbType.unitedName + "&";
			urldata += "unitedType=" + rbType.unitedType + "&";
			urldata += "unitedId=" + rbType.unitedId + "&";

			urldata += "detailId=" + redblackDetail["id"] + "&name=" + redblackDetail["name"] + "&code=" + redblackDetail["code"] + "&num=" + unitedId;

			// 判断是否展示提交信息
			if (isCommit && isCommit == "true") {
			    // 已提交，隐藏
			    // $("#inputp-data-table").hide();
                layer.open({
                    id: 'L3989',
                    type: 1,
                    title: "详情信息",
                    area: ['800px', '450px'],
                    fixed: false,
                    maxmin: true,
                    content: $("#dataDetail"),
                    btn: ['取消'],
                    btnAlign: 'c',
                    btn2: function(index, layero) {
                        layer.close(index);
                        return false;
                    }
                });
            } else {
			    // 未提交
                // $("#inputp-data-table").show();
                layer.open({
                    id: 'L3989',
                    type: 1,
                    title: "详情信息",
                    area: ['800px', '450px'],
                    fixed: false,
                    maxmin: true,
                    content: $("#dataDetail"),
                    btn: ['提交反馈', '取消'],
                    btnAlign: 'c',
                    btn1: function(index, layero) {
                        submitData();
                    },
                    btn2: function(index, layero) {
                        layer.close(index);
                        return false;
                    }
                });
            }
		}

		// 取消弹窗
		function quitB() {
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭  
		}

		// 提交反馈信息
		function submitData() {
			var content = $('#content').val();;
			var remark = $('#remark').val();;
			var action = $('#action').val();;
			var handleType = $('#handleType').val();

// 			if (!title || !content) {
// 				alert('请填写反馈信息');
// 			}

			if (handleType == "0") {
                alert('请选择处理类型！');
                return;
			}
            if (action.length <= 0) {
                alert('请填写奖惩措施！');
                return;
            }

            // 奖惩措施依据
			var itemNames = "";
			var originDepts = "";
			var unionPairContents = "";
			var unionPairCount = 0;
            $(".union-content").each(function () {
                if ($(this).val().length > 0) {
                    unionPairCount++;
                    itemNames += $(this).attr("itemName") + ",";
                    originDepts += $(this).attr("originDept") + ",";
                    unionPairContents += $(this).val() + ",";
                }
            });
            if (unionPairCount == 0 && handleType == "1") {
                alert('请选择奖惩措施依据！');
                return;
			}
            if (unionPairCount > 0) {
                itemNames = itemNames.substring(0, itemNames.length - 1);
                originDepts = originDepts.substring(0, originDepts.length - 1);
                unionPairContents = unionPairContents.substring(0, unionPairContents.length - 1);
			}

			urldata += '&content=' + content + '&remark=' + remark + '&action1=' + action +
				'&handleType=' + handleType + '&itemNames=' + itemNames + '&originDepts=' + originDepts + '&unionPairContents=' + unionPairContents;
			urldata = encodeURI(encodeURI(urldata));
			
			$.ajax({
				url : urldata,
				cache : false,
				type : "POST",
				dataType : "json",
                data: {
				    "test": "test"
				},
				success : function(data) {
					if(data=="反馈成功"){
						
					alert('反馈成功');
					}else{
						alert('反馈失败');
					}
					// quitB();
                    // 刷新当前页面
					// 调用父方法重新打开窗口
                    // window.parent.reOpenLayerIframe();
					var toUrl = window.location.href;
                    var toUrlInd = toUrl.lastIndexOf("&recordId=");
                    toUrl = toUrl.substring(0, toUrlInd) + "&recordId=" + $('#RBrecordId').val();
                    window.location.href = toUrl;
				}
			});

		}

        /**
		 * 选择措施
         */
		function selectUnionPairContents(_this) {
		    var htl = "<tr><th width=\"50px\">选择</th><th width=\"450px\">措施</th></tr>";
		    var contentStr = $(_this).attr("value");
		    var dataFor = $(_this).attr("data-for");// 一个标志
            $("#unionPairList-div").attr("data-for", dataFor);
		    if (contentStr && contentStr.length > 0) {
		        var contentArr = contentStr.split(",");
		        for (var i = 0; i < contentArr.length; i++) {
					htl += "<tr><td><input type=\"radio\" name=\"select-unionPair-content\" value='"+contentArr[i]+"'/></td><td>'"+contentArr[i]+"'</td>";
				}
			}
			$("table.unionPairList").html(htl);

            layer.open({
                id: 'L3990',
                type: 1,
                title: "选择措施",
                area: ['600px', '400px'],
                fixed: false,
                maxmin: true,
                content: $("#unionPairList-div"),
                btn: ['确定'],
                btnAlign: 'c',
                btn1: function(index, layero) {
                    // 将选择的值回填回输入框
                    var checkContentVal = $("input[name='select-unionPair-content']:checked").val();
					$(".union-content").each(function () {
					    if ($(this).attr("data-for") == dataFor) {
                            $(this).val(checkContentVal);
						}
					});
                    layer.close(index);
                }
            });
		}
	</script>
	<input type="hidden" name="RBname" id="RBname" value="${name} " />
	<input type="hidden" name="RBcode" id="RBcode" value="${code}" />
	<input type="hidden" name="RBtype" id="RBtype" value="${type}" />
	<input type="hidden" name="RBrednum" id="RBrednum" value="${rednum}" />
	<input type="hidden" name="RBblacknum" id="RBblacknum" value="${blacknum}" />
	<input type="hidden" name="RBredDes" id="RBredDes" value="${redDes}" />
	<input type="hidden" name="RBblackDes" id="RBblackDes" value="${blackDes}" />
	<input type="hidden" name="RBrecordId" id="RBrecordId" value="${recordId}" />
    <input type="hidden" name="RBcommitDetailId" id="RBcommitDetailId" value="${record.commitDetailIds}" />
	<div class="databody">
		<table class="redblackList" border="1" id="RBredblackList" style="display: block;">
		</table>
	</div>
	<div class="databody" id="dataDetail" style="display: none;">
		<table class="dataList" border="1" ; id="RBdata">
		</table>
		<table class="inputp" id="inputp-data-table">
			<tr>
				<td width="120px">处理类型:</td>
				<td colspan="3" align="left">

					<select class="con" id = "handleType" style="width: 570px;" >
						<option value="0">请选择</option>
						<option value="1">实施奖惩</option>
						<option value="2">其它奖惩</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>奖惩措施:</td>
				<td colspan="3" align="left">
					<textarea style="width:570px; height: 60px;" class="con" id="action"></textarea>
				</td>
			</tr>
			<tr>
				<td>反馈结果:</td>
				<td>
					<textarea style="width:570px; height: 60px;" class="con" id="content"></textarea>
				</td>
			</tr>
			<tr>
				<td>反馈备注:</td>
				<td>
					<textarea style="width:570px; height: 60px;" class="con" id="remark"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4">--------------------------------------奖惩措施依据--------------------------------------------</td>
			</tr>
			<%--<tr>
				<td>备忘录名称:</td>
				<td>
					<textarea class="con" id="content2"></textarea>
				</td>
				<td>备忘录内容:</td>
				<td>
					<textarea class="con" id="title2"></textarea>
				</td>
			</tr>--%>
		</table>
		<%--<div class="bott">
			<button type="button" onclick="submitData();">提交反馈</button>
			<button type="button" onclick="quitB();">取消</button>
		</div>--%>
	</div>
	<div class="databody" id="unionPairList-div" style="display: none;">
		<table class="unionPairList" border="1" style="margin-left: 25px; margin-top:15px; width: 550px; border-collapse: collapse;">
		</table>
	</div>
</body>
</html>