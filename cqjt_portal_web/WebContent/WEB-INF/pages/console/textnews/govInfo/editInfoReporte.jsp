<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
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
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/plugin/ueditor/themes/default/css/ueditor.css" rel="stylesheet" type="text/css" />
<style type="text/css">
a {color:blue;cursor: pointer;}
.column {min-width:940px;}
.error{color:red;padding-left: 10px;}
</style>
<title>政务信息上报管理</title>
</head>
<body>
<!--path Start-->
<div class="path">
      <b class="icon i-home"></b>
      <span>当前位置：</span>
     	 信息报送
      <span>-></span>
      	<a href="govTextNews.xhtml?face=listGovInfoReportView">政务信息上报管理</a>
      	<span>-></span>
      	信息上报编辑
	</div>
            <!--path End-->
            <!--column01 Start-->
            <div class="column" >
                <div class="title">
                  <h2><b class="icon i-search"></b>信息上报编辑</h2>
                </div>
                <div class="con docEdit" >
                  <form id="infoReportForm" enctype="multipart/form-data" method="POST">
					<ul>
					<input type="hidden" id="adoptTypeChecked" value="${InfoReportDetail.adoptType}">
<!--                             <label>采用类别：</label> -->
<%--                             <input type="hidden" id="adoptTypeChecked" value="${InfoReportDetail.adoptType}"> --%>
<!--                             <input type="checkbox" name="adoptType" value="dailyInfo" style="display:none"><span style="display:none">每日信息</span> -->
<!--                             <input type="checkbox" name="adoptType" value="subjectInfo" style="display:none"><span style="display:none">专报信息</span> -->
<!--                             <input type="checkbox" name="adoptType" value="trafficDept"><span>交通部</span> -->
<!--                             <input type="checkbox" name="adoptType" value="cityCommittee"><span>市委</span> -->
<!--                             <input type="checkbox" name="adoptType" value="cityGovernment"><span>市府</span> -->
<!--                             <input type="checkbox" name="adoptType" value="comLeaderAppr"><span>被委领导批示</span> -->
<!--                             <input type="checkbox" name="adoptType" value="govLeaderAppr"><span>被市领导批示</span> -->
<!--                             <input type="checkbox" name="adoptType" value="trafLeaderAppr"><span>被交通部领导批示</span> -->
<!--                         </li> -->
	
                        <li>
                        <label>采用类别：</label>
                        	<span>市&nbsp;&nbsp;&nbsp;委&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        	<span>信息专报:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> 
					     	<input type="checkbox" name="adoptType" value="swdbsubjectInfo"><span>独编</span>
					    	<input type="checkbox" name="adoptType" value="swzhsubjectInfo" style="margin-left: 41px;"><span>综合</span>
					    	<input type="checkbox" name="adoptType" value="headphonepa" style="margin-left: 81px;"><span>一把手手机报</span>
					    	<input type="checkbox" name="adoptType" value="swdayinfo"><span>每日要情</span
                        </li>
                         <li style="margin-left: 84px;height: 30px;">
                        	<span>市&nbsp;&nbsp;&nbsp;政&nbsp;&nbsp;&nbsp;府&nbsp;&nbsp;</span>
                        	<span>专报信息:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> 
					     	<input type="checkbox" name="adoptType" value="sfdbsubjectInfo"><span>独编</span>
						    <input type="checkbox" name="adoptType" value="sfzhsubjectInfo" style="margin-left: 41px;"><span>综合</span>
					    	<span     style="margin-left: 20px;">昨日简报:</span> 
					    	<input type="checkbox" name="adoptType" value="workdynamic"><span>工作动态</span>
					    	<input type="checkbox" name="adoptType" value="sfwordinfo" style="margin-left: 29px;"><span>一句话信息</span>
                        </li>
                        <li style="margin-left: 84px;height: 30px;">
                        	<span>交通运输部&nbsp;&nbsp;</span>
                        	<span>交通运输简报:</span> 
				    	 	<input type="checkbox" name="adoptType" value="trafsitcomm"><span>情况与交流</span>
					    	<input type="checkbox" name="adoptType" value="trafdaypa"><span>日报</span>
                        </li>
                        <li style="margin-left: 84px;height: 30px;">
                        	<span>被上级转报&nbsp;&nbsp;</span>
                        	<span>中办、国办:&nbsp;&nbsp;&nbsp;</span> 
				    		<input type="checkbox" name="adoptType" value="otherdbsubjectInfo"><span>独编</span>
					    	<input type="checkbox" name="adoptType" value="otherzhsubjectInfo" style="margin-left: 41px;"><span>综合</span>
                        </li>
                        <li style="margin-left: 84px;height: 30px;">
                        	<span>领导批示&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        	<input type="checkbox" name="adoptType" value="comLeaderAppr"><span>委领导</span>
                        	<input type="checkbox" name="adoptType" value="govLeaderAppr" style="margin-left: 29px;"><span>市领导</span>
                        	<input type="checkbox" name="adoptType" value="otherLeaderAppr" style="margin-left: 69px;"><span>上级领导</span>
                        </li>
                        <li style="margin-left: 84px;height: 30px;">
                           <span>约&nbsp;&nbsp;&nbsp;稿&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>                                       
                            <input type="checkbox" name="adoptType" value="yuegao"><span>约稿</span>
                        </li>
                             <li>
                            <label><span style="color:red">*</span>上报类别:</label>                                                                            
                            <input type="hidden" id="reportTypeChecked" value="${InfoReportDetail.reportType}">
                            <input type="checkbox" name="reportType" value="reportCityCommittee"><span>上报市委</span>
                            <input type="checkbox" name="reportType" id="reportNext" value="reportCityGovernment"><span>上报市政府</span>
                            <input type="checkbox" name="reportType" value="reportTrafficDept"><span>上报交通运输部</span>
                        </li>
                        <li><label><span style="color:red">*</span>标题：</label><input type="text" class="input input-large" value="${InfoReportDetail.giTitle}" name="giTitle">
                        <input type="hidden" name="newsId" value="${InfoReportDetail.newsId}">
                        <input type="hidden" name="giId" value="${InfoReportDetail.giId}">
                        <input type="hidden" name="isReport" value="1">
                        <input type="hidden" name="entryDateStr" value="${InfoReportDetail.entryDateStr}">
                        <input type="hidden" name="createDate" value="${InfoReportDetail.createDate}">
                        <input type="hidden" name="createBy" value="${InfoReportDetail.createBy}">
                        <input type="hidden" name="pubId" value="${InfoReportDetail.pubId}">
                        <input type="hidden" name="pubType" value="${InfoReportDetail.pubType}">
                        <input type="hidden" name="entryUser" value="${InfoReportDetail.entryUser}">
                        <input type="hidden" id="mergeId" name="mergeId" value="${InfoReportDetail.mergeId}">
                        </li>
                        <li>
                            <label><span style="color:red">*</span>作者：</label><input type="text" class="input input-small" value="${InfoReportDetail.newsAuthor}" name="newsAuthor">
                            <label><span style="color:red">*</span>投稿部门：</label><input type="text" class="input input-small" value="${InfoReportDetail.entryDept}" name="entryDept">
                        </li>
                        <li class="clearfix">
                           <label class="l"><span style="color:red">*</span>内容：</label>
                              <div class="textEditBox">
               	    				<script type="text/plain" id="myEditor">${InfoReportDetail.giContent}</script>
               	    				<input type="hidden" name="giContent" id="giContent">
               	    		 </div>
                            
                            
                      </li>
                        <li>
                        	<label></label>
                        	<a href="javascript:;" class="btn-gray" onclick="saveInfoReport()"><b class="icon i-add"></b>上报</a>
                            <a href="javascript:history.go(-1);" class="btn-gray"><b class="icon i-back"></b>返回</a>
                        </li>
                    </ul>
    			</form>
                </div>
</div>
<!--column01 End-->
 <!--column02 Start-->
      <div class="column" style="width:65%" >
        <div class="title"><h2><b class="icon i-search"></b>信息报送处理记录表</h2></div>
        <div class="con">
        	<table width="60%" border="1" class="dataList">
                <thead>
                  <tr>                    
                    <td width="5%">序号</td>
                    <td width="10%">处理人</td>
                    <td width="15%">处理时间</td>
                    <td width="8%">处理事项</td>
                  </tr>
                </thead>
                <tbody id="tbodyVideo">
                <c:forEach items="${newsRecord}" var="tn" varStatus="status">
					<tr>
						<td>${status.index + 1}</</td>     
                        <td>${tn.updateUser}</td>
                        <td>${tn.updateDate}</td>
                       <td>
                       		<c:if test='${tn.flag=="18"}'>归档</c:if>
                       		<c:if test='${tn.flag=="17"}'>发布</c:if>
                        	<c:if test='${tn.flag=="16"}'>复原</c:if>
	                        <c:if test='${tn.flag=="15"}'>已归档删除</c:if>
	                        <c:if test='${tn.flag=="14"}'>校编时退稿</c:if>
	                        <c:if test='${tn.flag=="13"}'>分拣时退稿</c:if>
	                        <c:if test='${tn.flag=="12"}'>校编时删除</c:if>
	                        <c:if test='${tn.flag=="11"}'>分拣时删除</c:if>
                       		<c:if test='${tn.flag=="10"}'>上报市府</c:if>
                       		<c:if test='${tn.flag=="9"}'>上报市委</c:if>
                       		<c:if test='${tn.flag=="8"}'>上报</c:if>
                       		<c:if test='${tn.flag=="7"}'>待校编</c:if>
                       		<c:if test='${tn.flag=="6"}'>采编时删除</c:if>
                      		<c:if test='${tn.flag=="5"}'>采编</c:if>
                       		<c:if test='${tn.flag=="4"}'>修改</c:if>
                            <c:if test='${tn.flag=="3"}'>校编</c:if>
	                        <c:if test='${tn.flag=="2"}'>分拣</c:if>
	                        <c:if test='${tn.flag=="1"}'>投稿</c:if>
	                        <c:if test='${tn.flag=="0"}'>存草稿</c:if>
                        </td>
           					</tr>
				</c:forEach>
			    </tbody>
			</table>
			<div id="userListPager" class="page clearfix"></div>
        </div>
    </div>
    <!--column02 End-->
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
var _ue;
window.APP_HOME_URL = "${pageContext.request.contextPath}";
$(function(){
	 /*
	_ue = UE.getEditor('myEditor',{
		sourceEditorFirst : true, 
		sourceEditor:"textarea",
		pasteplain:true,
		toolbars : [],
		onready : function() {
			_ue.setContent(_ue.getContentTxt().replace(/\r\n/g,''));
		}
	});
*/
// 	<!-- 根据获取的checkbox类别信息通过判断选定 -->
	var valType = $("#adoptTypeChecked").attr("value");
	//var valTypeStr = valType.substring(1,valType.length-1);
	$("input[name='adoptType']:checkbox").each(function(){
		if(valType.indexOf($(this).val())>0||$(this).val()==valType){
			$(this).attr("checked","checked");
		}
	});
	
	
	
	
	var valType1 = $("#reportTypeChecked").attr("value");
	$("input[name='reportType']:checkbox").each(function(){
		if(valType1.indexOf($(this).val())>0||$(this).val()==valType1){
			$(this).attr("checked","checked");
		}
	});
	
	var valType2 = $("#pubTypeChecked").attr("value");
	
	$("input[name='pubType']:checkbox").each(function(){
		if(valType2.indexOf($(this).val())>0||$(this).val()==valType2){
			$(this).attr("checked","checked");
		}
	});
	
	var category = $("#pubIdChecked");
	$("#newsCategory option").each(function(){
		if($(this).val()==category.val()){
			$(this).attr("selected",true);
		}
	});
	
	if($("#newsCategory").val()==''){
		$("#newsCategory").attr("disabled", true);
	}else{
		$("#newsCategory").attr("disabled", false);
	}
	//标题不能为空
	$("input[name='giTitle']").keyup(function(){
		var title1 = $(this).val();
		if(title1 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>标题不能为空!</span>");
		}
	});
	//鼠标转移事件 投稿人
	$("input[name='newsAuthor']").keyup(function(){
		var title2 = $(this).val();
		if(title2 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>作者不能为空!</span>");
		}
	});
	//鼠标转移事件 投稿部门
	$("input[name='entryDept']").keyup(function(){
		var title3 = $(this).val();
		if(title3 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>投稿部门不能为空!</span>");
		}
	});
	
//初始化编辑器
	
	$("#giContent").val(UE.getEditor('myEditor').getContent());
	$("#myEditor").keyup(function(){
		var title5 = $("#giContent").val();
		if(title5 != "" ){
			$(this).next(".error").remove();
		}
	}); 
	/** 许健：这个事件貌似不起作用？！！
	//鼠标转移事件 内容不能为空
	$("#giContent").val(UE.getEditor('myEditor').getContentTxt());
	$("#myEditor").keyup(function(){
		var title4 = $("#giContent").val();
		if(title4 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>内容不能为空!</span>");
		}
	});  */
});

function saveInfoReport() {
	var giTitle =  $("input[name='giTitle']").val(); //标题
	var entryDept= $("input[name='entryDept']").val();//投稿部门
	var newsAuthor= $("input[name='newsAuthor']").val();//投稿人
	var reportType=$("input[name='reportType']").val();
	var adoptType = $("input[name='adoptType']").val();//
	//复选框 校验 上报类型
	var reportTypeList=[];
	$("input[name='reportType']:checked").each(function(){
		reportTypeList= $(this).val();
	});
	if(reportTypeList.length == 0){
		$("#reportNext").next().next(".error").remove();
		$("#reportNext").next().after("<span class='error'>请选择上报类型 !</span>");
		return;
	}else{
		$("#reportNext").next().next(".error").remove();
	}
	//标题校验
	if(giTitle =="" && giTitle.length == 0){
    	$("input[name='giTitle']").next(".error").remove();
		$("input[name='giTitle']").after("<span class='error'>标题不能为空!</span>");
		return;
	} else{
		$("input[name='giTitle']").next(".error").remove();
	}
	//投稿部门校验
	if(entryDept =="" && entryDept.length == 0){
    	$("input[name='entryDept']").next(".error").remove();
		$("input[name='entryDept']").after("<span class='error'>投稿部门不能为空!</span>");
		return;
	}else{
		$("input[name='entryDept']").next(".error").remove();
	}
	//投稿人验证
	if(newsAuthor =="" && newsAuthor.length == 0){
    	$("input[name='newsAuthor']").next(".error").remove();
		$("input[name='newsAuthor']").after("<span class='error'>作者不能为空!</span>");
		return;
	}else{
		$("input[name='newsAuthor']").next(".error").remove();
	}
	//内容校验
	$("#giContent").val(UE.getEditor('myEditor').getContent());
	//校验内容
	if($("#giContent").val() == ''||$("#giContent").val().length==0){
		$(".textEditBox").next(".error").remove();
		$(".textEditBox").after("<span class='error'>内容不能为空</span>");
		return;
	}else{
		$(".textEditBox").next(".error").remove();
	}
	//添加字符串
    var adoptType = "";
	$("input[name='adoptType']").each(function(){
		if (this.checked) {
			if (adoptType == "") 
				adoptType = this.value
			else 
				adoptType += "," + this.value;
		}
	});
	$("#adoptType").val(adoptType);
	//添加字符串 上报类型
	var reportType="";
	$("input[name='reportType']").each(function(){
		if (this.checked) {
			if (reportType == "") 
				reportType = this.value
			else 
				reportType += "," + this.value;
		}
	});
	$("#reportType").val(reportType);
	

	var options = {
		url : 'govTextNews.xhtml?action=saveInfoReport',
		type : 'post',
		dataType : "html",
		success : function(data) {	
		document.location.href = "govTextNews.xhtml?face=listGovInfoReportView";
		var content = eval('(' + data + ')'); 
		/*alert(content); 返回类型为object*/
		/*alert(content.msg);属性值（提示信息） */
		document.location.href = "govTextNews.xhtml?face=listGovInfoReportView";
			<%--asyncbox.confirm(content.msg , "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "govTextNews.xhtml?face=listGovInfoReportView";
				}})
				--%>
		},
		error :function(data){
		 asyncbox.confirm(data + "内部错误，保存失败", "确认窗口", function(action) {
			if (action == "ok") {
				document.location.href = "govTextNews.xhtml?face=editInfoReportView&newId="+newsId;
			}}) 
		}
	};
	$("#infoReportForm").ajaxSubmit(options);
}

</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.all.min.js"></script>
</html>
