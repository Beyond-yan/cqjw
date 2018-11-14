<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>重庆交通政务办公网--政务信息采编<</title>
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/plugin/ueditor/themes/default/css/ueditor.css" rel="stylesheet" type="text/css" />
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
a {color:blue;cursor: pointer;}
.column {min-width:940px;}
.error{color:red;padding-left: 10px;}
.w{ width:100%; clear:both; overflow:hidden;}
.c{ width:16%; float:left;height:90px;}
.c1{ width:21%; float:left;height:90px;}
.c2{ width:16%; float:left;height:90px;}
.c3{ width:21%; float:left;height:90px;}
.c4{ width:21%; float:left;height:90px;}
.d{ width:80%; margin-left:20%;height:90px;}
</style>
</head>
<body >
	<body >
        <!-- <div class="main">
            path Start -->
            <div class="path">
                <b class="icon i-home"></b>
                <span>当前位置：</span>
        		        信息报送
                <span>-></span>
         		       政务信息采编
            </div>
            <!--path End-->
            <!--column01 Start-->
            <div class="column" >
                <div class="title"><h2><b class="icon i-search"></b>政务信息采编</h2></div>
                <div class="con docEdit" >
                <form id="govNewsForm" enctype="multipart/form-data" method="POST">
					<ul>
                    	<!-- <li><label>创建时间：</label><span>2014-04-03   15:26</span></li> -->
                        <li>
                        <input type="hidden" id="adoptTypeChecked" name="adoptType" value="${adoptType}"> 
                        <input type="hidden" id="workDynamic" name="adoptType"> 
                        <input type="hidden" id="oneInfo" name="adoptType"> 
                        <input type="hidden" id="countyDynamic" name="adoptType"> 
                        <input type="hidden" id="netInfo" name="adoptType"> 
                        <label>采用类别：</label> 
							<span>交&nbsp;&nbsp;&nbsp;委&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<span>信息专报:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> 
						    <input type="checkbox" name="adoptType" value="jwdbsubjectInfo"><span>独编</span>
						    <input type="checkbox" name="adoptType" value="jwzhsubjectInfo" style="margin-left: 41px;"><span>综合</span>
						    </li>
						<li style="margin-left: 156px;height: 30px;">
						<span>每日信息:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        <input type="checkbox" name="pubType"  class="pubType" value="workDynamic" onclick="choosePubType(this)"><span>工作动态</span>
                        &nbsp;&nbsp;
                        <input type="checkbox" name="pubType"   class="pubType" value="countyDynamic" onclick="choosePubType(this)"><span>区县动态</span>
                        <input type="checkbox" name="pubType"   class="pubType" value="oneInfo" onclick="choosePubType(this)" style="margin-left: 57px;"><span>一句话信息</span>
                        <input type="checkbox" name="pubType"  class="pubType" value="netInfo" onclick="choosePubType(this)" style="margin-left: 17px;"><span>网络信息</span>
                        <span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>所属刊物:</span>
                        <select class="select-small" name="pubId" id="pubName" disabled="disabled" style="width:220px;">
                          <option value="">请选择刊物</option>
                          <c:forEach items="${textPublication}" var="pub">
                            <option value="${pub.pubId}">${pub.pubName}</option>
                          </c:forEach>
                        </select>
                        </li>
                        <li style="margin-left: 84px;height: 30px;">
                        	<span>市&nbsp;&nbsp;&nbsp;委&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        	<span>信息专报:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> 
					     	<input type="checkbox" name="adoptType" value="swdbsubjectInfo"><span>独编</span>
					    	<input type="checkbox" name="adoptType" value="swzhsubjectInfo" style="margin-left: 41px;"><span>综合</span>
					    	<input type="checkbox" name="adoptType" value="headphonepa" style="margin-left: 81px;"><span>一把手手机报</span>
					    	<input type="checkbox" name="adoptType" value="swdayinfo" ><span>每日要情</span>
					    	
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
                            <label>上报类别:</label>                                          
                            <input type="hidden" id="reportTypeChecked" value="${reportType}">                                  
                            <!-- <input type="checkbox" name="reportType" value="workReport"><span>工作简报</span>
                            <input type="checkbox" name="reportType" value="subjectInfo"><span>专报信息</span> -->
                            <input type="checkbox" name="reportType" value="reportCityCommittee"><span>上报市委</span>
                            <input type="checkbox" name="reportType" value="reportCityGovernment"><span>上报市政府</span>
                            <input type="checkbox" name="reportType"  value="reportTrafficDept" ><span>上报交通运输部</span>
                            <!-- <input type="checkbox" name="reportType"value="comLeaderAppr"><span>被委领导批示</span>
                            <input type="checkbox" name="reportType"value="govLeaderAppr"><span>被市领导批示</span>
                            <input type="checkbox" name="reportType"value="trafLeaderAppr"><span>被交通部领导批示</span> -->
                        </li>
                        <li><label><span style="color:red">*</span>标题:</label>
                       		 <input type="text" name="giTitle" class="input input-large" value="${govNewsInfo.newsTitle}">
                             <input type="hidden" name="newsId" value="${govNewsInfo.newsId}">
                             <input type="hidden" name="entryDate" value="${govNewsInfo.entryDate}">
                             <input type="hidden" name="entryUser" value="${govNewsInfo.entryUser}">
                             <input type="hidden" name="createBy" value="${userNo}">
                       		 <input type="hidden" id="mergeId" name="mergeId" value="${govNewsInfo.mergeId}">
                       		 <input type="hidden" name="isSelected" >
                        </li>
                        <li>
                            <label><span style="color:red">*</span>作者：</label><input type="text" name="newsAuthor" class="input input-small" value="${govNewsInfo.newsAuthor}">
                            <label><span style="color:red">*</span>投稿部门：</label><input type="text" name="entryDept" class="input input-small"  value="${govNewsInfo.deptName}">
                        </li>
                        <li class="clearfix">
                          <label class="l"><span style="color:red">*</span>内容：</label>
                    <div class="textEditBox">
               	    	<script type="text/plain" id="myEditor">
                          ${govNewsInfo.newsContent}
                        </script>
                        <input type="hidden" id="giContent" name="giContent">
               	    </div>
                      </li>
						<!-- 删除附件 -->
                        <!-- <li>
                        	<label>附件：</label>
                            <input type="file" value="govNewsInfo.photoUrl">
                        </li> -->
                        <li>
                        	<label></label>
                        	<a href="javascript:;" class="btn-gray" onclick="saveGovNews()"><b class="icon i-add"></b>提交</a>
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
                       		<c:if test='${tn.flag=="20"}'>未成刊</c:if>
                       		<c:if test='${tn.flag=="19"}'>未采编</c:if>
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
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
var _ue;
window.APP_HOME_URL = "${pageContext.request.contextPath}";
//多选框实现单选效果
$(function(){
	 $(".pubType").click(function(){
	    //$(this).attr("checked",true);
		$(this).nextAll().attr("checked",false);
// 		$(this).prevAll().attr("checked",false);
		$(this).siblings(".pubType").attr("checked",false);
	}); 
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
	 
	$("input[name='giTitle']").keyup(function(){
		var title1 = $(this).val();
		if(title1 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>标题不能为空</span>");
		}
	});
	$("input[name='entryUser']").keyup(function(){
		var title2 = $(this).val();
		if(title2 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>作者不能为空</span>");
		}
	});
	
	$("input[name='adoptType']:checkbox").click(function(){
			$("input[value='trafLeaderAppr']:checkbox").next().next(".error").remove();
	});
	
	$("input[name='reportType']:checkbox").click(function(){
		$("input[value='reportCityGovernment']:checkbox").next().next(".error").remove();
	});
	
	//初始化编辑器
	 $("#giContent").val(UE.getEditor('myEditor').getContent());
	$("#myEditor").keyup(function(){
		var title5 = $("#giContent").val();
		if(title5 != "" ){
			$(this).next(".error").remove();
		}
	});   
	
	/*  _ue = UE.getEditor('myEditor',{
		onready : function() {
			_ue.setContent(_ue.getPlainTxt());
		}
	});  */
	
});    

function saveGovNews() {
	var entryDate=$("input[name='entryDate']").val();
	//校验采用类别
	var adoptTypesList=[];
    $("input[name='adoptType']:checked").each(function(){
    	adoptTypesList = $(this).val();
	}); 
    
  //校验上报类别
  
    var reportTypesList=[];
    $("input[name='reportType']:checked").each(function(){
    	reportTypesList = $(this).val();
	}); 
    
    var pubTypeList=[];
    $("input[name='pubType']:checked").each(function(){
    	pubTypeList = $(this).val();
	}); 
    
	if(adoptTypesList.length==0 && reportTypesList.length==0 && pubTypeList.length==0 ){
		$("input[value='reportTrafficDept']:checkbox").next().after("<span class='error'>请选择采用或上报类别</span>");
		return;
	}

    
    //if(adoptTypesList.length==0){
    //	$("input[value='trafLeaderAppr']:checkbox").next().next(".error").remove();
	//	$("input[value='trafLeaderAppr']:checkbox").next().after("<span class='error'>请选择采用类别</span>");
	//	return;
	//} 
//     alert("reportTypesList.length:"+reportTypesList.length+", adoptTypesList.length:"+adoptTypesList.length);
//     if(adoptTypesList.length==0){
//     	$("input[value='trafLeaderAppr']:checkbox").next().next(".error").remove();
//     	$("input[value='reportCityGovernment']:checkbox").next().next(".error").remove();
// 		$("input[value='reportCityGovernment']:checkbox").next().after("<span class='error'>请选择采用或上报类别</span>");
// 		return;
// 	}
	
    
	var newstitle =  $("input[name='giTitle']").val();
 	var newsauthor = $("input[name='entryUser']").val();
	var isSelected;
	//校验输入框
    if(newstitle =="" && newstitle.length == 0){
    	$("input[name='giTitle']").next(".error").remove();
		$("input[name='giTitle']").after("<span class='error'>标题不能为空</span>");
		return;
	}     
 	if(newsauthor == "" && newsauthor.length == 0){
 		$("input[name='entryUser']").next(".error").remove();
		$("input[name='entryUser']").after("<span class='error'>作者不能为空</span>");
		return;
	}
 	
 	//政务信息要用getContentTxt()，去纯文本内容 将getContent()改为getContentTxt()
 	/* $("#giContent").val(UE.getEditor('myEditor').getContent()); */
 	$("#giContent").val(UE.getEditor('myEditor').getContentTxt());
	//校验内容
	if($("#giContent").val() == ''||$("#giContent").val().length==0){
		$(".textEditBox").next(".error").remove();
		$(".textEditBox").after("<span class='error'>内容不能为空</span>");
		return;
	}else{
		$(".textEditBox").next(".error").remove();
	}
 	
 	/*
	$("#giContent").val(_ue.getContentTxt());
	//校验内容
	if($("#giContent").val() == ''||$("#giContent").val().length==0){
		$(".textEditBox").next(".error").remove();
		$(".textEditBox").after("<span class='error'>内容不能为空</span>");
		return;
	}*/
	
	var adoptType = "";
	$("input[name='adoptType']").each(function(){
		if (this.checked) {
			if (adoptType != "") {
				adoptType += "," + this.value;
			}
		}
	});
	
	var reportType = "";
	$("input[name='reportType']").each(function(){
		if (this.checked) {
			if (reportType == "") {
				reportType = this.value
			}else{
				reportType += "," + this.value;
			}
		}
	});
	$("input[name='pubType']").each(function(){
		if (this.checked) {
			isSelected='1';
			if(this.value=="workDynamic"){
				$("#workDynamic").val("pubtypedynamic");
			}
			if(this.value=="oneInfo"){
				$("#oneInfo").val("pubTypewordinfo");
			}
			if(this.value=="countyDynamic"){
				$("#countyDynamic").val("countyDynamic");
			}
			if(this.value=="netInfo"){
				$("#netInfo").val("netInfo");
			}
		}
		else{
			isSelected='0';
		}
		$("input[name='isSelected']").val(isSelected);	
	});
	//是否选择所属刊物 
	<%--$("input[name='pubType']:checked").each(function(){
		if($("#pubName").val()==''||$("#pubName").val()==0){
			isSelected='0';
			alert (isSelected);
		}
		else{
			isSelected='1';
			alert (isSelected);
		} 
		$("input[name='isSelected']").val(isSelected);
	});  
	--%>
	//选择刊物类别后，所属刊物不能为空
	var checkresult='';
	$("input[name='pubType']:checked").each(function(){
		if($("#pubName").val()==''||$("#pubName").val()==0){
			$("#pubName").next(".error").remove();
			$("#pubName").after("<span class='error'>所属刊物不能为空</span>");
			checkresult='msg';
			return;
		}
		else{
			$("#pubName").next(".error").remove();
			isSelected='1';
			$("input[name='isSelected']").val(isSelected);
		} 
	});  
	if(checkresult=='msg') return;  
	var newsTitle =  "${newsTitle}";
	if(newsTitle){
		newsTitle = encodeURI(newsTitle);
		newsTitle = newsTitle.replace(/#/g,"%23");
	}
	var entryUser = "${entryUser}"; 
	if(entryUser){
		entryUser = encodeURI(entryUser);
	}
	var options = {
		url : 'govTextNews.xhtml?action=saveGovInfo',
		type : 'post',
		dataType : "html",
		success : function(data) {
			//document.location.href = "govTextNews.xhtml?face=listGovInfoView";
		  <%-- var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg + "是否清空表单？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "govTextNews.xhtml?face=listGovInfoView";
				}
			});--%>  
			location.href ="govTextNews.xhtml?face=searchGovInfoView&newsTitle="+newsTitle+"&entryUser="+entryUser+"&entryDateS=${entryDateS}"
			+"&entryDateE=${entryDateE}&pageIndex=${pageIndex}&newsTag=${newsTag}&zxx=${zxx}&hb=${hb}";

		},
		error :function(data){
			asyncbox.confirm("内部错误，保存失败", "确认窗口", function(action) {
				if (action == "ok") {
					//document.location.href = "govTextNews.xhtml?face=listGovInfoView";
					location.href ="govTextNews.xhtml?face=searchGovInfoView&newsTitle="+newsTitle+"&entryUser="+entryUser+"&entryDateS=${entryDateS}"
					+"&entryDateE=${entryDateE}&pageIndex=${pageIndex}&newsTag=${newsTag}&zxx=${zxx}&hb=${hb}";

				}
			});
		}
	};
	$("#govNewsForm").ajaxSubmit(options);
}

function choosePubType(obj) {
	if (obj.checked) {
		$("#pubName").attr("disabled", false);
	} 		
}


</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.all.min.js"></script>
</html>
