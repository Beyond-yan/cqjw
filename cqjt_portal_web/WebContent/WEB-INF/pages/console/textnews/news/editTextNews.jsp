<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
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
/*a {color:blue;cursor: pointer;}*/
.column {min-width:940px;}
.error{color:red;padding-left: 10px;}
</style>
<title>编辑</title>
</head>
<body onload="init()" >
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		信息报送
        <span>-></span>
		编辑稿件
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>投稿信息编辑</h2></div>
        <div class="con docEdit" >
			<form id="textNewsForm" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="newsContent" id="newsContent" />
			<input type="hidden" name="newsTagsStr" id="newsTags" />
			<input type="hidden" name="flag" id="newsFlag"  value="${textNewsEditInfo.flag}"/>
			<input type="hidden" name="fg"  value="${textNewsEditInfo.flag}"/>
			<input type="hidden" name="entryDept" value="${userDeptID}"/>
			<input type="hidden" name="newsId" value="${textNewsEditInfo.newsId}"/>
			<input type="hidden" name="outerNewsId" value="${textNewsEditInfo.outerNewsId}"/>
			<input type="hidden" name="roleName"  value="${roleName}">
			<input type="hidden" name="govUseFlag"  value="${textNewsEditInfo.govUseFlag}">
			<input type="hidden" name="createDate" value="${textNewsEditInfo.createDate}">
            <ul>
                <li>      
	                <label><span style="color:red">*</span>标题：</label>
	                <input type="text" name="newsTitle" class="input input-large" value="${textNewsEditInfo.newsTitle}">
                </li>
                <li>
                    <label><span style="color:red">*</span>作者：</label>
                    <input type="text" name="newsAuthor" value="${textNewsEditInfo.newsAuthor}" class="input input-small">
                    
	                <label><span style="color:red"></span>审核人：</label>
	               <select class="select-small" name="approvaler">
	                   <c:if test='${textNewsEditInfo.approvaler==null}'>
		                 <option value="">--请选择审核人--</option>
		                 <c:forEach items="${approvers}" varStatus="status">
		                 <option value="${status.current.userName}">${status.current.userName}</option>
		                 </c:forEach>   
		            </c:if>
	                <c:if test='${textNewsEditInfo.approvaler!=null}'>   
	                   <option value="${textNewsEditInfo.approvaler}">${textNewsEditInfo.approvaler}</option>
	                </c:if>
	                </select>
                </li>
                
                <li>
                    <label>来源出处：</label>
                    <input type="text" name="newsSource" value="${textNewsEditInfo.newsSource}" class="input input-small">
                    
                    <label><span style="color:red">*</span>投稿部门：</label>
                   <%--  <select class="select-small" disabled="disabled" name="userDept">
	                    <option value="${textNewsEditInfo.entryDept}">${textNewsEditInfo.deptName}</option>
	                </select> --%>
	                <input type="text" name="deptName" value="${textNewsEditInfo.deptName}" class="input input-small">
                    <input type="hidden" name="entryUser" value="${textNewsEditInfo.entryUser}" class="input input-small">
                </li>
                
                <li class="clearfix">
                    <label class="l"><span style="color:red">*</span>内容：</label>
                    <div class="textEditBox">
               	    	<script type="text/plain" id="myEditor">
                        ${textNewsEditInfo.newsContent}
                        </script>
               	    </div>
                </li>
				<input type="hidden" id="newsTagChecked" value="${textNewsEditInfo.newsTagsStr}">
                   <c:choose>
				   <c:when test="${ not empty textNewsEditInfo.category}">
				   <li>
                   		<label><span style="color:red">*</span>信息类别：</label>
                    	<input type="checkbox" name="newsTag" value="SiteNews"  checked="checked"  disabled="disabled"><span>网站信息</span>&nbsp;&nbsp;
						<select class="select-small" name="category" id="newsCategory" >
                    		<option value="0">==请选择栏目==</option>
	                    	<c:forEach items="${textCategorys}" var="c">
		                    	<option value="${c.categoryId}" <c:if test="${c.categoryId eq textNewsEditInfo.category}">selected='selected'</c:if>>${c.categoryName}</option>
		                    </c:forEach>
	                	</select>
	               	</li>
	            	<li id="showPhotoState" >
	                	<label>主题图片：</label>
	                	<input type="checkbox" name="isPhotosShow" id="isPhotosShowCheck" onclick="if(this.checked){this.value=1;}else{this.value=0;}" />
	                	<span>是否滚动显示</span>
	                	<input type="file" name="newsImage"  id="newsImage" onchange="uploadFile('newsImage')" />
	                	<!--<label id="tu">图片链接：</label><a href="${StaticResourcePath}/${textNewsEditInfo.photoUrl}" target="_blank">${textNewsEditInfo.photoUrl}</a>  -->
	                	<div id="showImg">
                			<label>&nbsp;</label>
                			<c:forEach items="${photoList}" var="imgUrl" varStatus="status">
                				<a href="javascript:" onclick="delImg(this);">
                				<img src="${StaticResourcePath}/${imgUrl}" width="100px'"name="showUploadImg" height="150px" alt="${imgUrl}"/>
                				</a>
                			</c:forEach>
                		</div>	
	                </li>
	                <li id="showPublicState" >
	                    <label><span style="color:red">*</span>是否上外网：</label>
	                    <input type="radio" name="isPublic" id="public" value="1"  ><span>是</span>&nbsp;&nbsp;
	                    <input type="radio" name="isPublic" id="unPublic" value="0" ><span>否</span>
	                </li>
				   </c:when>   
				   <c:otherwise>  
				   <li>
                   		<label><span style="color:red">*</span>信息类别：</label>
				    	<input type="checkbox" name="newsTag" value="GovNews"  <c:if test="${fn:contains(textNewsEditInfo.newsTagsStr, 'GovNews')}" >checked='checked'</c:if>><span>政务信息</span>
<%-- 					    <input type="checkbox" name="newsTag" value="TrafficNews"  <c:if test="${fn:contains(textNewsEditInfo.newsTagsStr, 'TrafficNews')}" >checked='checked'</c:if>><span>交通杂志</span> --%>
<%--                     	<input type="checkbox" name="newsTag" value="TrafficPaper"  <c:if test="${fn:contains(textNewsEditInfo.newsTagsStr, 'TrafficPaper')}" >checked='checked'</c:if>><span>交通报</span> --%>
				   </li>
				   </c:otherwise>  
				</c:choose>
	            <input type="hidden" id="isPhoto" value=" ${textNewsEditInfo.isPhotosShow}" />
	            <input type="hidden" id="image" name="photoUrl" />
	            <input type="hidden" name="imageUrl" value="${textNewsEditInfo.photoUrl}"/>
	            <input type="hidden" id="publicChecked" value="${textNewsEditInfo.isPublic}">
                
                <li>
                	<label></label>
                    <input id="btn1"  type="button" class="btn-gray" onclick="saveSubmit(1)"  value="保存修改" />
                    <input id="btn2"  type="button" class="btn-gray"  onclick="saveTextNews(0)"value="存草稿"/>
                    <a href="javascript:history.go(-1);" class="btn-gray"><b class="icon i-back"></b>返 回</a>
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
                    <td width="8%">退稿原因</td>
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
                        <td>${tn.rejectReason}</td>
           					</tr>
				</c:forEach>
			    </tbody>
			</table>
			<div id="userListPager" class="page clearfix"></div>
        </div>
    </div>
    <!--column02 End-->
</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/ajaxfileupload.js"></script>
<script type="text/javascript">
window.APP_HOME_URL = "${pageContext.request.contextPath}";
function show1(){
	 window.open ('${StaticResourcePath}/${textNewsEditInfo.photoUrl}','add', 
	 'height=600, width=800, top=100, left=200, toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no, status=no') ;
	 } 
$(function(){
	UE.getEditor('myEditor');
	//复选框选中
	var ischeck = '${textNewsEditInfo.isPhotosShow}';
	if(ischeck == 1){
		  $("#isPhotosShowCheck").attr("checked", true);
		  $("#isPhotosShowCheck").val(1);
	}
});

function delImg(obj){
	$(obj).remove();
}

function uploadFile(obj){
    ajaxFileUpload(obj);
}

function ajaxFileUpload(obj){
	//判断是否有选择上传文件
    var imgPath = $("#newsImage").val();
    if (imgPath == "") {
        alert("请选择上传图片！");
        return;
    }
    //判断上传文件的后缀名
    var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
    strExtension = strExtension.toLowerCase();
    if (strExtension != 'jpg' && strExtension != 'gif' && strExtension != 'png' && strExtension != 'bmp') {
        alert("请选择图片文件");
        return;
    }
	
 // 开始上传文件时显示一个图片
   // $("#show1").ajaxStart(function() {
      //  $(this).show();
    // 文件上传完成将图片隐藏起来
    //}).ajaxComplete(function() {
       // $(this).hide();
    //});
    var elementIds=["flag"]; //flag为id、name属性名
    $.ajaxFileUpload({
        url: 'textNews.xhtml?action=uploadFile', 
        type: 'post',
        secureuri: false, //一般设置为false
        fileElementId: 'newsImage', // 上传文件的id、name属性名
        dataType: 'text', //返回值类型，一般设置为json、application/json
        elementIds: elementIds, //传递参数到服务器
        success: function(data, status){  
        	//$("#showImg").append('src',"${StaticResourcePath}/"+data); 
        	$("#showImg").append("<a href='javascript:' onclick='delImg(this);'><img src='${StaticResourcePath}/"+data+"' width='100px' name='showUploadImg' height=150px alt='"+data+"'/></a>"); 
        },
        error: function(data, status, e){ 
            //alert(e);
        }
    });
    
}

function init()
{  
	var roleName =  $("input[name='roleName']").val();
	var flag =  $("input[name='flag']").val();
	var govUseFlag =  $("input[name='govUseFlag']").val();
	//alert (roleName);
	//alert (flag);
	//alert (govUseFlag);
	//$("#btn").attr("disabled", true); 
   if(roleName=='Intern' && flag=='3')
   { 
	   $("#btn1").attr("disabled", true);
	   $("#btn2").attr("disabled", true);
    } 
   else if(roleName=='Intern' && govUseFlag=='1')
   { 
	   $("#btn1").attr("disabled", true);
	   $("#btn2").attr("disabled", true);
    }
   else if(roleName=='Intern' && govUseFlag=='2')
   { 
	   $("#btn1").attr("disabled", true);
	   $("#btn2").attr("disabled", true);
    } 
} 
function chooseSiteNews(obj) {
	if (obj.checked) {
		$("#newsCategory").attr("disabled", false);
		$("#showPhotoState").show();//新增图片点击显示 wl
		$("#showPublicState").show();//新增是否上外网显示 wl
	} else {
		$("#newsCategory")[0].selectedIndex = 0;
		$("#newsCategory").attr("disabled", true);
		$("#showPhotoState").hide(); //新增图片点击隐藏 wl
		$("#showPublicState").hide();//新增是否上外网显示wl
	}
}
function checkFormData() {
	//待完成
	return true;
}
$(function(){
	var valType = $("#newsTagChecked").attr("value");
	/*
	$("input[name='newsTag']:checkbox").each(function(){
		if(valType.indexOf($(this).val())>=0||$(this).val()==valType){
			$(this).attr("checked","checked");
			if($(this).val()=='SiteNews'){
				$("#newsCategory").attr("disabled", false);
				$("#showPhotoState").show();//新增图片点击显示 wl
				$("#showPublicState").show();//新增是否上外网显示 wl
				var category = $("#categoryEdit");
				$("#newsCategory option").each(function(){
					if($(this).val()==category.val()){
						$(this).attr("selected",true);
					}
				});
				
				var isPhoto = $("#isPhoto").val();
				if(isPhoto==1){
					$("input[name='isPhotosShow']").attr("checked","checked");
					$("input[name='isPhotosShow']").val("1");
				}
			}
		}
	});*/
	var valPublic = $("#publicChecked").attr("value");
	$("input[name='isPublic']:radio").each(function(){
		if($(this).val()==valPublic){
			$(this).attr("checked","checked");
		}
	});
	
	$("input[name='newsTitle']").keyup(function(){
		var title1 = $(this).val();
		if(title1 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>标题不能为空</span>");
		}
	});
	$("input[name='newsAuthor']").keyup(function(){
		var title2 = $(this).val();
		if(title2 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>作者不能为空</span>");
		}
	});
	/*
	$("select[name='approvaler']").keyup(function(){
		var title3 = $(this).val();
		if(title3 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>审核人不能为空</span>");
		}
	});
	*/
  	$("#newsContent").val(UE.getEditor('myEditor').getContent());
	$("#myEditor").keyup(function(){
		var title5 = $("#newsContent").val();
		if(title5 != "" ){
			$(this).next(".error").remove();
		}
	});   
});    

function saveSubmit(flag){
	asyncbox.confirm("涉密信息禁止上网!", "信息窗口", function(action) { 
		if (action == "ok") {
			saveTextNews(flag);
		}
	});
}

function saveTextNews(flag) {
	
	
  	var newstitle =  $("input[name='newsTitle']").val();
 	var newsauthor = $("input[name='newsAuthor']").val();
 //	var approvaler = $("select[name='approvaler']").val();
	var newsimage = $("input[name='photoUrl']").val(); 
	var category = $("select[name='category']").val();
	var newstag = $("input[name='newsTag']").val();
	//var ispublic = $("input[name=isPublic]")
	//校验输入框
    if(newstitle =="" && newstitle.length == 0){
    	$("input[name='newsTitle']").next(".error").remove();
		$("input[name='newsTitle']").after("<span class='error'>标题不能为空</span>");
		return;
	}     
 	if(newsauthor == "" && newsauthor.length == 0){
 		$("input[name='newsAuthor']").next(".error").remove();
		$("input[name='newsAuthor']").after("<span class='error'>作者不能为空</span>");
		return;
	}
 	/*
	if(approvaler ==""){
		$("select[name='approvaler']").next(".error").remove();
		$("select[name='approvaler']").after("<span class='error'>审核人不能为空</span>");
		return;
	}
   */
	$("#newsFlag").val(flag);
	
	$("#newsContent").val(UE.getEditor('myEditor').getContent());
	//校验内容
	if($("#newsContent").val() == ''||$("#newsContent").val().length==0){
		$(".textEditBox").next(".error").remove();
		$(".textEditBox").after("<span class='error'>内容不能为空</span>");
		return;
	}else{
		$(".textEditBox").next(".error").remove();
	}
	//校验信息类别
	var newsTagsList=[];
	var checkresult='';
    $("input[name='newsTag']:checked").each(function(){
    	newsTagsList = $(this).val();
		if($(this).val()=='SiteNews'){
			if(category==''||category==0){
				$("select[name='category']").next(".error").remove();
				$("select[name='category']").after("<span class='error'>栏目不能为空</span>");
				checkresult='msg';
				return;
			}else{
				$("select[name='category']").next(".error").remove();
			}
		}
	});  
    if(checkresult=='msg') return;
    if(newsTagsList.length==0){
    	$("input[value='TrafficPaper']:checkbox").next().next(".error").remove();
		$("input[value='TrafficPaper']:checkbox").next().after("<span class='error'>请选择信息类别</span>");
		return;
	}else{
		$("input[value='TrafficPaper']:checkbox").next().next(".error").remove();
	}
	
	var newsTag = "";
	$("input[name='newsTag']").each(function(){
		if (this.checked) {
			if (newsTag == "") 
				newsTag = this.value
			else 
				newsTag += "," + this.value;
		}
	});
	
	newsimage = "";
	var imgList=$("#showImg img");//获取ID为div里面的所有img
	for(var i=0;i<imgList.length;i++){ //循环为每个img设置
		var alt = imgList[i].alt;
		newsimage+=alt +",";
	}
	if(!(newsimage==""||newsimage.length==0)){
		newsimage = newsimage.substring(0, (newsimage.length - 1));
	}
	var img = 0;
	$("input[name='isPhotosShow']:checkbox").each(function(){
		if($(this).val()==1){
			img = 1;
		}
	});
	if(img==1){
		
		if(newsimage==""||newsimage.length==0){
			$("input[name='newsImage']").next(".error").remove();
			$("input[name='newsImage']").after("<span class='error'>请选择图片文件</span>");
			return;
		}else{
			$("input[name='newsImage']").next(".error").remove();
		}
	}
	
	$("#newsTags").val(newsTag);
	$("#image").val(newsimage);
	var options = {
		url : 'textNews.xhtml?action=updateNews',
		type : 'post',
		dataType : "html",
		beforeSubmit : checkFormData,
		success : function(data) {			
		    var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg + "是否返回报送查询页面？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "textNews.xhtml?face=listView";
				}
			});  
		},
		error :function(data){
			asyncbox.confirm("内部错误，保存失败", "信息窗口");
		}
	};
	$("#textNewsForm").ajaxSubmit(options);
}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.all.min.js"></script>
</html>
