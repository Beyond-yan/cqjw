<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html 
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
<title>编辑</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		信息报送
		<span>-></span>
		<a href="siteTextNews.xhtml?face=getSortSiteInfoView">网站信息分拣</a>
        <span>-></span>
		编辑稿件
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>网站信息分拣编辑</h2></div>
        <div class="con docEdit" >
			<form id="textNewsForm" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="newsContent" id="newsContent" />
			<input type="hidden" name="flag" id="newsFlag" />
			<input type="hidden" name="entryDept" value="${userDeptID}"/>
			<input type="hidden" name="newsId" value="${textNewsEditInfo.newsId}"/>
			<input type="hidden" name="outerNewsId" value="${textNewsEditInfo.outerNewsId}"/>
			<input type="hidden" name="createDate" value="${textNewsEditInfo.createDate}">
			<input type="hidden" name="govUseFlag" value="${textNewsEditInfo.govUseFlag}">
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
	                <c:if test='${empty textNewsEditInfo.approvaler}'>
		                 <option value="">--请选择审核人--</option>
		                 <c:forEach items="${approvers}" varStatus="status">
		                 <option value="${status.current.userName}">${status.current.userName}</option>
		                 </c:forEach>   
		            </c:if>
	               <c:if test='${!empty textNewsEditInfo.approvaler}'>   
	                   <option value="${textNewsEditInfo.approvaler}">${textNewsEditInfo.approvaler}</option>
	                </c:if>
	               </select>   
                </li>
                
                <li>
                    <label>来源出处：</label>
                    <input type="text" name="newsSource" value="${textNewsEditInfo.newsSource}" class="input input-small">
                    
                    <label><span style="color:red">*</span>投稿部门：</label>
                    <select class="select-small" disabled="disabled" name="userDept">
	                    <option value="${textNewsEditInfo.entryDept}">${textNewsEditInfo.deptName}</option>
	                </select>
	                <input type="hidden" name="deptName" value="${textNewsEditInfo.deptName}" class="input input-small">
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
                <li>
                	<input type="hidden" name="newsTagsStr" value="${textNewsEditInfo.newsTagsStr}" />
                    <label><span style="color:red">*</span>信息栏目：</label>
                    <select class="select-small" name="category" id="newsCategory">
                    	<option value="0">-=请选择栏目=-</option>
                    	<c:forEach items="${textCategorys}" var="c">
	                    <option value="${c.categoryId}">${c.categoryName}</option>
	                    </c:forEach>
	                </select>
	                <input type="hidden" id="categoryEdit" value="${textNewsEditInfo.category}">
                </li>
                <li id="showPhotoState">
                	<label>主题图片：</label>
                    <input type="checkbox" id="isPhotosShow" name="isPhotosShow" onclick="if(this.checked){this.value=1;}else{this.value=0;}" />
                	<input type="hidden" id="isPhoto" value=" ${textNewsEditInfo.isPhotosShow}" />
                	<span>是否滚动显示</span>
                	<input type="hidden" name="imageUrl" value="${textNewsEditInfo.photoUrl}"/>
                	<input type="hidden" id="image" name="photoUrl" />
                	
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
                <li id="showPublicState">
                    <label><span style="color:red">*</span>是否上外网：</label>
                    <input type="hidden" id="publicChecked" value="${textNewsEditInfo.isPublic}">
                    <input type="radio" name="isPublic" id="public" value="1"  ><span>是</span>&nbsp;&nbsp;
                    <input type="radio" name="isPublic" id="unPublic" value="0" ><span>否</span>
                </li>
                <!-- 是否置顶 -->
                 <li id="isStickState">
                    <label><span style="color:red">*</span>是否置顶：</label>
                    <input type="hidden" id="stickStated" value="${textNewsEditInfo.stickState}">
                    <input type="radio" name="stickState" id="stickState" value="1"  ><span>是</span>&nbsp;&nbsp;
                    <input type="radio" name="stickState" id="stickState" value="0" ><span>否</span>
                </li>
                <li>
                	<label></label>
                	<a href="javascript:;" class="btn-gray" onclick="saveTextNews(8)"><b class="icon i-send"></b>归档</a>
                    <a href="javascript:;" class="btn-gray" onclick="saveTextNews(2)"><b class="icon i-send"></b>分拣</a>
                     <a href="javascript:;" class="btn-gray" onclick="saveTextNews(7)"><b class="icon i-del"></b>  退稿</a>
                    <a href="javascript:history.go(-1);" class="btn-gray"><b class="icon i-back"></b>返 回</a>
                </li>
                <li> <label>退稿原因：</label></li>
                 <li>
                	 <textarea style="width:30%; resize:none;margin-left: 70px;" name="rejectReason" ></textarea>
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
	
	var category = $("#categoryEdit");
	$("#newsCategory option").each(function(){
		if($(this).val()==category.val()){
			$(this).attr("selected",true);
		}
	});
});

function checkFormData() {
	//待完成
	return true;
}
$(function(){
	
	//是否置顶
	
   var valStated = $("#stickStated").attr("value");
	$("input[name='stickState']:radio").each(function(){
		if($(this).val()==valStated){
			$(this).attr("checked","checked");
		}
	});
	
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

	var isPhoto = $("#isPhoto").val();
	if(isPhoto==1){
		$("input[name='isPhotosShow']").attr("checked","checked");
		$("input[name='isPhotosShow']").val("1");
	}
	
  	$("#newsContent").val(UE.getEditor('myEditor').getContent());
	$("#myEditor").keyup(function(){
		var title5 = $("#newsContent").val();
		if(title5 != "" ){
			$(this).next(".error").remove();
		}
	});   
});    
function saveTextNews(flag) {
  	var newstitle =  $("input[name='newsTitle']").val();
 	var newsauthor = $("input[name='newsAuthor']").val();
	//var approvaler = $("select[name='approvaler']").val();
	var newsimage = $("input[name='newsImage']").val(); 
	var category = $("select[name='category']").val();
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
	/*if(approvaler ==""){
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
	}
	newsimage = "";
	var imgList=$("#showImg img");//获取ID为div里面的所有img
	for(var i=0;i<imgList.length;i++){ //循环为每个img设置
		var alt = imgList[i].alt;
		newsimage+=alt +",";
	}
	if(!(newsimage==""||newsimage.length==0)){
		newsimage = newsimage.substring(0, (newsimage.length - 1));
	}
	$("#image").val(newsimage);
	
	var isPhotosShow = $("#isPhotosShow").val();
	if(isPhotosShow == 'on'){
		$("#isPhotosShow").val("0");
	}
	
	var options = {
		url : 'textNews.xhtml?action=updateNews',
		type : 'post',
		dataType : "html",
		beforeSubmit : checkFormData,
		success : function(data) {			
		    var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg + "是否清空表单？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "siteTextNews.xhtml?face=getSortSiteInfoView";
				}
			});  
		},
		error :function(data){
			alert("内部错误，保存失败");
		}
	};
	$("#textNewsForm").ajaxSubmit(options);
	
	
}

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
    if(strExtension != null){
    	strExtension = strExtension.toLowerCase();
    }
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
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.all.min.js"></script>
</html>