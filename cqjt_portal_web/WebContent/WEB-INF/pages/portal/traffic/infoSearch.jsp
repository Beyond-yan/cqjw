<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib
	prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@page import="com.gdssoft.core.tools.SystemContext"%><!doctype html>

<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--交通搜索</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/index.css" rel="stylesheet" type="text/css">
<%-- <link href="${StaticResourcePath}/css/news.css" rel="stylesheet" type="text/css"> --%>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/layer/layer.js"></script>

<style type="text/css">
.sub {
	width: 100%;
}

.r {
	width: 221px;
}

.pic img {
	width: 201px;
	height: 130px;
}

.sub-picNews .con {
	text-align: center;
	padding: 10px 10px 10px;
}

.main {
	float: left;
	width: 492px;
	margin: 0 8px;
}

.content .column {
<c:if test="${flag!=8}">
	height: 640px;
	overflow: hidden;
</c:if>
}

.infoList a {
	width: 89%;
}

.infoList .time {
	text-align: right;
	color: #6d6d6d;
	font-family: Tahoma;
}

.kind a {
	font-size: 18px;
	text-decoration: underline;
	cursor: pointer;
	/* font-weight: bold; */
}

.div1 {
	padding-left: 40px;
	font-size: 12px;
	/* font-weight: bold; */
}

.dataList {
	width: 100%;
	margin-top: 40px;
	border-collapse: collapse;
}
</style>
</head>

<body>
	<!-- onload="pageLoad()" -->
	<div class="wrap">
		<%@include file="../head.jspf"%>
		<!--content Start-->
		<div class="content clearfix">
			<!--search-box Start-->
			<div class="search-box">
				<div class="breadCutNav">
					<b class="icon icon_home"></b>
					<span>当前位置：</span>
					<a href="index.xhtml">首页</a>
					<span> &gt;</span>
					<a href="javascript:;">交通搜索</a>
				</div>
			</div>
			<!--search-box End-->
			<!--sub Start-->
			<div class="sub l">
				<div class="column">
					<p>&nbsp;</p>
					<p>&nbsp;</p>
					<input type="hidden" name="category" id="category" value="" />
					<input type="hidden" name="userName" id="userName" value="${userName}" />
					<input type="hidden" name="token" id="token" value="" />
					<input type="hidden" name="RBname" id="RBname" value="" />
					<input type="hidden" name="RBcode" id="RBcode" value="" />
					<input type="hidden" name="RBtype" id="RBtype" value="" />
					<div class="kind" style="text-align: left; margin-left: 230px;">
						<a onclick="querySearch(1);return false;" style="color: blue;" id="dept1">公开公文</a>
						<span>&nbsp;&nbsp;&nbsp;</span>
						<a onclick="querySearch(5);return false;" style="color: blue;" id="dept5">交通课堂</a>
						<span>&nbsp;&nbsp;&nbsp;</span>
						<a onclick="querySearch(6);return false;" style="color: blue;" id="dept6">交通动态</a>
						<span>&nbsp;&nbsp;&nbsp;</span>
						<a onclick="querySearch(3);return false;" style="color: blue;" id="dept3">交通视频</a>
						<span>&nbsp;&nbsp;&nbsp;</span>
						<a onclick="querySearch(7);return false;" style="color: blue;" id="dept7">图文影视</a>
						<span>&nbsp;&nbsp;&nbsp;</span>
						<a onclick="querySearch(8);return false;" style="color: blue;" id="dept8">社会信用</a>

					</div>
					<table id="aborigines" border="0" align="center" style="margin-top:100px; display: none;">
						<tr>
							<td>
								<input type="text" name="searchword" value="${searchword}" id="searchword"
									   style="height: 40px; width: 450px; vertical-align: middle; font-size: 14px; line-height: 40px;">
								&nbsp;&nbsp;&nbsp;
								<input type="button" style="height: 40px; width: 80px; vertical-align: middle; text-align: center; font-size: 16pt" value="查询"
									   onclick="searchList();return false;">
							</td>
						</tr>
					</table>
					<div class="con">
						<input type="hidden" id="totalCount" value="${searchResult[0].totals}" />
						<input type="hidden" id="pageSize" value="${searchResult[0].pageSize}" />
						<input type="hidden" id="pageIndex" value="${searchResult[0].pageIndex}" />
						<input type="hidden" id="flag" value="${flag}" />
						<input type="hidden" id="userNo" value="${userNo}" />
						<ul class="infoList  clearfix" id="tbody">
							<c:if test="${flag==1}">
								<c:forEach items="${searchResult}" var="n">
									<li style="display: block; text-align: left; margin-left: 100px; margin-right: 100px">
										<a href='../guest/news.xhtml?face=getPublicArchivesDetail&archivesId=${n.archivesid}&schema=${n.schema_code}' target='_blank'>
											<b class='i-rArr'></b>${n.subject}
										</a>
										<span class='time'>${fn:substring(n.createtime,0,10)}</span>
									</li>
								</c:forEach>
							</c:if>
							<c:if test="${flag==3}">
								<c:forEach items="${searchResult}" var="n">
									<li style="display: block; text-align: left; margin-left: 100px; margin-right: 100px">
										<a href='news.xhtml?face=videoNewsDetail&videoId=${n.news_id}' target='_blank'>
											<b class='i-rArr'></b>${n.news_title}
										</a>
										<span class='time'>${fn:substring(n.entry_date,0,10)}</span>
									</li>
								</c:forEach>
							</c:if>
							<c:if test="${flag==6}">
								<c:forEach items="${searchResult}" var="n">
									<li style="display: block; text-align: left; margin-left: 100px; margin-right: 100px">
										<a href='news.xhtml?face=newsDetail&newsId=${n.news_id}' target='_blank'>
											<b class='i-rArr'></b>${n.news_title}
										</a>
										<span class='time'>${fn:substring(n.entry_date,0,10)}</span>
									</li>
								</c:forEach>
							</c:if>
							<c:if test="${flag==5}">
								<c:forEach items="${searchResult}" var="n" varStatus="status">
									<li style="display: block; text-align: left; margin-left: 100px; margin-right: 100px">
										<a href="javascript:;" onclick="downLoad(${status.index});return false;" target='_blank'>
											<b class='i-rArr'></b>${n.news_title}
											<input type="hidden" id="fileName${status.index}" value="${n.attachment}" />
											<input type="hidden" id="filePath${status.index}" value="${n.path}" />
										</a>
										<span class='time'>${fn:substring(n.entry_date,0,10)}</span>
									</li>
								</c:forEach>
							</c:if>

							<c:if test="${flag==8}">
								<table border="1" class="dataList">
									<thead>
										<tr id="titleTr" style="font-size: 15px; font-weight: bold;">
										</tr>
									</thead>
									<tbody id="tbodyFile">
									</tbody>
								</table>
							</c:if>

						</ul>
						<div id="trafficPager" class="page"></div>
					</div>
					<table id="redBlackQ" border="0" align="center" style="margin-top:-20px; display: none;">
						<tr>
							<%--<td>
								<select id="selectClass" style="height: 44px; width: 100px; font-size: 15px;">
									<option value="z" title="请选择">-- 请选择 --</option>
									<option id="black" value="black" title="黑名单">黑名单</option>
									<option id="red" value="red" title="红名单">红名单</option>
								</select>
								&nbsp;&nbsp;&nbsp;
							</td>--%>
							<td>
								<input type="text" name="name" value="${name}" id="name" placeholder="企业(人员)名称/社会信用代码(身份证号码)"
									   style="height: 40px; width: 360px; vertical-align: middle; font-size: 14px; line-height: 40px;">
								<%--&nbsp;&nbsp;&nbsp;
								<input type="text" placeholder="社会信用代码(身份证号码)" name="code" value="${code}" id="code"
									style="height: 40px; width: 180px; vertical-align: middle; font-size: 14px; line-height: 40px;">--%>
								&nbsp;&nbsp;&nbsp;
								<input type="button" style="height: 40px; width: 80px; vertical-align: middle; text-align: center; font-size: 16pt" value="查询"
									   onclick="loadData(); return false;">
							</td>
						</tr>
					</table>
					<div id="query-record" class="query-record" style="width: 600px; margin: 0 auto; padding-bottom: 20px; display: none">
						<span>查询记录：</span><br>
					</div>
				</div>
			</div>
		</div>
		<!--content End-->
		<%@include file="../footer.jspf"%>
	</div>
	<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
	<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
	<script type="text/javascript">
$(function(){
	 <c:if test="${!empty searchResult[0].totals}">
    	var redBackUrl = "";
		$("#trafficPager").buildPager({
			totalLines:${searchResult[0].totals},
			pageSize:${searchResult[0].pageSize},
			startIndex:${searchResult[0].pageIndex},
			displayNum:5,
			afterChange:function() {
				searchList();
			}
		});
		</c:if>
		var flag = $('#flag').val();
		$("a[id^='dept']").attr("style","color:blue");
		$("#dept"+flag).attr("style","color:black");
		$("#dept"+flag).attr("style","text-decoration:none;");
		
		if (flag == 8) {
		    //开始就查询列表
            searchRedBlack(true);
			$('#redBlackQ').show();
			$('#aborigines').hide();

			// 查询之前查询的记录
            getQueryRecord();


		} else {
			$('#aborigines').show();
			$('#redBlackQ').hide();
		}
});
//公开公文
function queryPubNotice(searchword,flag){
	var pageIndex = $("#trafficPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	searchword = encodeURI(encodeURI(searchword));
	location.href="../guest/news.xhtml?face=queryPubNotice&pageIndex="+pageIndex+"&searchword="+searchword+"&flag="+flag;
	
};
//交通信息交通课堂交通视频
function queryInfo(searchword,flag){
	var pageIndex = $("#trafficPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	searchword = encodeURI(encodeURI(searchword));
	flag = encodeURI(encodeURI(flag));
	location.href="../guest/news.xhtml?face=queryInfo&pageIndex="+pageIndex+"&searchword="+searchword+"&flag="+flag;	
};

//查询方法
function querySearch(flag){	
	$("a[id^='dept']").attr("style","color:blue");
	$("#dept"+flag).attr("style","color:black");
	$("#dept"+flag).attr("style","text-decoration:none;");
	var searchword = $('#searchword').val();
	
	if (flag == 8) {

		$('#redBlackQ').show();
		$('#aborigines').hide();
	} else {
		$('#aborigines').show();
		$('#redBlackQ').hide();
	}
	
	if(flag==1){ //公开公文
		queryPubNotice(searchword,flag);	
	}else if(flag==5){//交通课堂
		queryInfo(searchword,flag);	
	}else if(flag==6){//交通信息
		queryInfo(searchword,flag);	
	}else if(flag==3){//交通视频
		queryInfo(searchword,flag);	
	}else if(flag==7){//图文影视
		queryPhotoInfo(searchword,flag);
	} else if (flag==8) { //企业信用 
		$('#flag').val(8);
		<%if (!SystemContext.isAuthenticated()) {%>
			alert("登陆后才可查询");
		<%} else {%>
			jumpRedBlack(flag);
		<%}%>
	} 
}

function downLoad(num){
	var fileName=encodeURI(encodeURI($("#fileName"+num).val()));
	var filePath=$("#filePath"+num).val();
	location.href="../guest/index.xhtml?face=downLoad&fileName="+fileName+"&filePath="+filePath;
}
function queryPhotoInfo(searchword,flag){
	    $('#flag').val(7);
		var jsonPara1 = {
		            method: "login",
		            parameter:JSON.stringify({ 
						userId:"administrator", password:"evias", appkey:"publish"
						})
					};
		$.ajax({
				type : "get",
				url : "http://10.224.5.44:8080/evias/api.action",                                                      
			    dataType : "jsonp",    								 		
				jsonp:"jsonpCallback",
		      	data: jsonPara1,
		      	success : function(data){
		         var token = data['token'];
		         $('#token').val(token);
		       	},
		       	error:function(response){ 
		       		//alert("error");
		       	}
		      });
		var token =$('#token').val();
	    var pageIndex = $("#trafficPager").attr("curPage");
	    var num ="";
	    if (typeof pageIndex == "undefined") {
			pageIndex = 0;
			num=0;
		}else {
	    	var test=parseInt(pageIndex);
	    	num=15*(parseInt(pageIndex));
			num=parseInt("15")+parseInt(num);
	    }  
		var jsonPara = {
		            method: "getResultsByKeyword",
		            parameter:JSON.stringify({ 
						folderID:" ",content:searchword,start:num, pageSize:15,token:token
						})
					};
		$.ajax({
			    url:"http://10.224.5.44:8080/evias/api.action",
				cache: false,
				type : "get",
				dataType:"jsonp",
		       	jsonp:"jsonpCallback",
		      	data: jsonPara,  
				success : function(data) {
					var json = data;
					var pageSize=15;
					var totalCount =json.resourceTotalCount;
					var textNewsList="";
					var created="";
					$("#trafficPager").buildPager({
						totalLines:totalCount,
						pageSize:15,
						startIndex:pageIndex,
						displayNum:5,
						afterChange:function() {
							searchList();
						}
					});
				  $.each(json.queryResult.results, function(i, n) {
						created = n.created;
						created = created.substring(0,10);
		textNewsList+= "<li style=\"display:block;text-align:left;margin-left:100px;margin-right:100px;\"> <a href='http://10.224.5.44:8080/evias/toDetail2.action?resourceID="+
						n.id+"' target='_blank'><b class='i-rArr'></b>"+n.name+"</a>"+"<span class='time' >"+created+"</span></li>"
					    });
					  $("#tbody").html("");
					  $("#tbody").html(textNewsList); 
					},
					error :function(data){
						alert("登陆后才可查询");
					}
				});
		
};
function searchList(){
	var flag = $('#flag').val();
	var searchword = $('#searchword').val();
	if(flag==1){ //公开公文
		queryPubNotice(searchword,flag);	
	}else if(flag==5){//交通课堂
		queryInfo(searchword,flag);	
	}else if(flag==6){//交通信息
		queryInfo(searchword,flag);	
	}else if(flag==3){//交通视频
		queryInfo(searchword,flag);	
	}else if(flag==7){//图文影视
		queryPhotoInfo(searchword,flag);
	} else if(flag==8){// 企业信用
		<%if (!SystemContext.isAuthenticated()) {%>
			alert("登陆后才可查询");
		<%} else {%>
			searchRedBlack();
		<%}%>
	}
}

function jumpRedBlack(flag) {
	location.href="../guest/news.xhtml?face=queryInfo&pageIndex=0&searchword=&flag="+flag;
}

function searchRedBlack(isFirst) {
	
	var submmitUrl = "";
	var name = $('#name').val();
	var code = $('#code').val();
	var selectClass = $('#selectClass').val();
	
	submmitUrl = "../console/redBalckQuery.xhtml?action=enterpriseBlacklist";
	// if (!isFirst && (!name || /*! code ||*/ selectClass=='z')) {
	// 	$('#tbodyFile').empty();
	// 	var html = "<td style='text-align:center;color:red;' >参数不能为空</td>"
	// 	$('#titleTr').empty().append(html);
	// } else {
		// 设置全局变量
		$('#RBname').val(name);
		$('#RBcode').val(code);
		$('#RBtype').val(selectClass);
		// 发起请求
		$.ajax({
			url : submmitUrl,
			cache : false,
			type : "POST",
			dataType : "json",
			data : {
				"name" : name,
				"code" : code,
				"type" : selectClass
			},
			success : function(data) {
				if (data.lists.length > 1) {
					createHtmlNew(data.lists);
				} else {
					var html = "<td style='text-align:center;color:red;' >没有查询到数据</td>"
					$('#titleTr').empty().append(html);
				}
			}
		});
 	// }
}

function createHtmlNew(data) {
	var titleTr = '<th align="left"><input name="red-black-item-all" style="margin-left: 20px;" onclick="checkItemAll();" type="checkbox"></th><th>编号</th><th>类型</th><th>名称</th>';
	$('#titleTr').empty().append(titleTr);
	var tbodyFile = "";
	for (var i = 0; i < data.length; i++) {
		var red = "红名单";
		var black = "黑名单";
		tbodyFile += '<tr>';
		if(data[i].unitedName=="red"){
		tbodyFile += '<td><input name="red-black-item" style="margin-left: 20px" type="checkbox" value="'+data[i].unitedId+'" data-type="red" data-des="'+data[i].unitedType+'"></td><td>'+/*data[i].unitedId*/(i+1)+'</td><td>'+red+'</td><td>'+data[i].unitedType+'</td>'; //<td align="center" ><button type="button" value='+data[i].unitedId+' onclick="loadData(this);" >查看详情</button></td>';
		}else{
		tbodyFile += '<td><input name="red-black-item" style="margin-left: 20px" type="checkbox" value="'+data[i].unitedId+'" data-type="black" data-des="'+data[i].unitedType+'"></td><td>'+/*data[i].unitedId*/(i+1)+'</td><td>'+black+'</td><td>'+data[i].unitedType+'</td>'; //<td align="center" ><button type="button" value='+data[i].unitedId+' onclick="loadData(this);" >查看详情</button></td>';
			
		}
	tbodyFile += '</tr>';
	}
	$('#tbodyFile').empty().append(tbodyFile);
}

function loadData() {
	
	var name = $('#name').val();//$('#RBname').val();
	
	var code = $('#RBcode').val();
	var type = $('#RBtype').val();
	var rednum = "";
    var redDes = "";
	var blacknum = "";
    var blackDes = "";
	// 获取复选框的值
    $("input[name='red-black-item']").each(function(){
        if ($(this).attr("checked")) {
            if ($(this).attr("data-type") == "red") {
                rednum += $(this).val() + ",";
                redDes += $(this).attr("data-des") + ",";
			} else {
                blacknum += $(this).val() + ",";
                blackDes += $(this).attr("data-des") + ",";
			}
        }
    });
    if (name.length <= 0) {
        alert("请输入企业(人员)名称或社会信用代码(身份证号码)！");
        return;
	}
    if (rednum.length <= 0 && blacknum.length <= 0) {
        alert("请选择查询项！");
        return;
	}
    if (rednum.length > 0) {
        rednum = rednum.substring(0, rednum.length - 1);
	}
    if (blacknum.length > 0) {
        blacknum = blacknum.substring(0, blacknum.length - 1);
    }
    if (redDes.length > 0) {
        redDes = redDes.substring(0, redDes.length - 1);
    }
    if (blackDes.length > 0) {
        blackDes = blackDes.substring(0, blackDes.length - 1);
    }
    loadDataParam(name, code, rednum, blacknum, redDes, blackDes);
	
}

function loadDataParam(name, code, rednum, blacknum, redDes, blackDes, recordId) {
    if (!recordId) {
        recordId == "";
	}
    name = encodeURI(name);
    redDes = encodeURI(redDes);
    blackDes = encodeURI(blackDes);
    redBackUrl = '../console/redBalckQuery.xhtml?action=enterpriseRedlistpage&name='+name+'&code='+code+'&rednum=' + rednum + '&blacknum=' + blacknum + '&redDes=' + redDes + '&blackDes=' + blackDes + '&recordId=' + recordId;
    redBackUrl = encodeURI(redBackUrl);
    layer.open({
        id: '3988',
        type: 2,
        title: "详情信息",
        area: ['950px', '550px'],
        fixed: false,
        maxmin: true,
        content: redBackUrl,
        btn: ['取消'],
        btnAlign: 'c',
        btn1: function(index, layero) {
            layer.close(index);
            return false;
        }
    });
    // 定时2s刷新
    window.setTimeout(" getQueryRecord()", 2000);
}

function reOpenLayerIframe() {
    layer.closeAll();
    layer.open({
        id: 'L123243547',
        type: 2,
        title: "详情信息",
        area: ['950px', '550px'],
        fixed: false,
        maxmin: true,
        content: redBackUrl,
        btn: ['取消'],
        btnAlign: 'c',
        btn1: function(index, layero) {
            layer.close(index);
            return false;
        }
    });
}

/**
 * 复选框选中
 */
function checkItemAll() {
    var checkAllDom = $("input[name='red-black-item-all']");
    var checkAll = checkAllDom.val();
    if (checkAll == "true") {
        checkAllDom.val("false");
	} else {
        checkAllDom.val("true");
	}
    $("input[name='red-black-item']").each(function(){
        if (checkAll == "true") {
            $(this).attr("checked", false);
        } else {
            $(this).attr("checked", true);
        }
    });
}

function getQueryRecord() {
    $.ajax({
        url : "../console/redBalckQuery.xhtml?action=getRedBlackQueryRecordList",
        cache : false,
        type : "POST",
        dataType : "json",
        data : {},
        success : function(list) {
            if (!list || list.length <= 0) {
                return;
            }
            $("#query-record").html('<span>查询记录：</span><br>');
            var lengthCount = list.length > 15 ? 15 : list.length;
            for (var i = 0; i < lengthCount; i++) {
                var record = list[i];
                var content = record.queryName + " ";
                if (record.redDes) {
                    content += record.redDes;
                }
                if ((!record.redDes || record.redDes.length <= 0)
                    && (record.blackDes && record.blackDes.length > 0)) {
                    content += ","
                }
                if (record.blackDes && record.blackDes.length > 0) {
                    content += record.blackDes
                }
                if (content.length > 40) {
                    content = content.substring(0, 40-1) + "......";
                }
                var html = '<a href="javascript:void(0);" onclick="loadDataParam(\''+record.queryName+'\', \'\', \''+record.redNum+'\', \''+record.blackNum+'\', \''+record.redDes+'\', \''+record.blackDes+'\', \''+record.id+'\');">'+record.createTimeStr+' '+content+'</a><br>';
                $("#query-record").append(html);
            }
            var moreHtm = '<a href="../console/redBalckQuery.xhtml?action=getRedBackQueryRecordPage" target="_blank" >查看更多</a><br>';
            $("#query-record").append(moreHtm);
            $("#query-record").show();
        }
    });
}

</script>
</body>
</html>