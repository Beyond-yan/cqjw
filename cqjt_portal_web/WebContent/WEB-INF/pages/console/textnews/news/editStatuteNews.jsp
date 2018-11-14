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
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
.column {min-width:940px;}
.error{color:red;padding-left: 10px;}
</style>
<title>编辑法律法规</title>
</head>
<body onload="init()" >
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		内容管理
        <span>-></span>
		编辑法律法规
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>投稿信息编辑</h2></div>
        <div class="con docEdit" >
			<form id="textstatuteForm" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="statuteContent" id="statuteContent" />
			<input type="hidden" name="statuteTagsStr" id="statuteTags" />
			<input type="hidden" name="flag" id="statuteFlag"  value="${textNewsEditInfo.flag}"/>
			<input type="hidden" name="fg"  value="${textNewsEditInfo.flag}"/>
			<input type="hidden" name="entryDept" value="${userDeptID}"/>
			<input type="hidden" name="statuteId" value="${textNewsEditInfo.statuteId}"/>
 		 
 
 
			<input type="hidden" name="createDate" value="${textNewsEditInfo.createDate}">
            <ul><br/>
                <li>
	                <label><span style="color:red">*</span>法规标题：</label>
	                <input type="text" name="statuteTitle" value="${textNewsEditInfo.statuteTitle}" class="input input-large">
                </li>
                 <br/>
                                <li>
               		<label>发布日期：</label>
	                <div class="data" id="dataSelect-start">
	                    <input type="text"  name="pubDate" value="${textNewsEditInfo.pubDate}" class="input-text tcal tcalInput">
	                </div> 
	                <label>实施日期：</label>
	                <div class="data" id="dataSelect-start">
	                    <input type="text"  name="inplementDate" value="${textNewsEditInfo.inplementDate}" class="input-text tcal tcalInput">
	                </div> 
                </li>
                 <br/>
                <li>
                    <label><span style="color:red">*</span>上传人：</label>
                    <input type="text" name="statuteAuthor"  value="${textNewsEditInfo.statuteAuthor}"class="input input-small">
                </li>
                <br/>
                <li>
                    <label>发布机关：</label>
                    <input type="text" name="pubDapt" value="${textNewsEditInfo.pubDapt}" class="input input-small">
                    <label>发布文号：</label>
                    <input type="text" name="pubTextNum" value="${textNewsEditInfo.pubTextNum}" class="input input-small">
	                <input type="hidden" name="deptName" value="" class="input input-small">
                    <input type="hidden" name="entryUser" value="" class="input input-small">
                    <label>时效性：</label>
                    <select name="timeliness">
	        			<option value="有效" <c:if test="${textNewsEditInfo.timeliness=='有效'}">selected</c:if>>有效</option>
	        			<option value="无效" <c:if test="${textNewsEditInfo.timeliness=='无效'}">selected</c:if>>无效</option>
	        		</select>
                </li>

                <br/>
                <li>
                <label style="margin-top:0px;"><span style="color:red">*</span>题注：</label>
                <textarea style="border-radius: 3px;width:800px; border-color:#ddd;margin-left:2px;" rows="" cols=""  name="caption"  class="input-text">${textNewsEditInfo.caption}</textarea>
                </li>
                <br/>
                <li class="clearfix">
                    <label class="l"><span style="color:red">*</span>内容：</label>
                    <div class="textEditBox">
               	    	<script type="text/plain" id="myEditor">
 							${textNewsEditInfo.statuteContent}
                        </script>
               	    </div>
                </li>
                 <li>
	                <label>法规分类：</label>
        		<select class="input-text" id="StatuteCat"  name="SUB_CATEGORY" style="height: 28px;">
        			<option value="交通地方性法规库" <c:if test="${textNewsEditInfo.SUB_CATEGORY=='交通地方性法规库'}">selected</c:if>>交通地方性法规库</option>
        			<option value="交通地方政府规章库" <c:if test="${textNewsEditInfo.SUB_CATEGORY=='交通地方政府规章库'}">selected</c:if>>交通地方政府规章库</option>
        			<option value="交通行政规范性文件库" <c:if test="${textNewsEditInfo.SUB_CATEGORY=='交通行政规范性文件库'}">selected</c:if>>交通行政规范性文件库</option>
        			<option value="党内法规库（A分类）" <c:if test="${textNewsEditInfo.SUB_CATEGORY=='党内法规库（A分类）'}">selected</c:if>>党内法规库（A分类）</option>
        			<option value="党内法规库（B分类）" <c:if test="${textNewsEditInfo.SUB_CATEGORY=='党内法规库（B分类）'}">selected</c:if>>党内法规库（B分类）</option>
        		</select>
        		<label>子分类：</label>
        		<select class="input-text" id="Statute" name="SUB_CATEGORY_INFO" style="height: 28px;">
        			<c:choose>
					    <c:when test="${textNewsEditInfo.SUB_CATEGORY_INFO == '市政府制发文件'}">
					       <option value='市政府制发文件' selected>市政府制发文件</option><option value='市交委制发文件'>市交委制发文件</option><option value='委属单位制发文件'>委属单位制发文件</option>
					    </c:when>
					    <c:when test="${textNewsEditInfo.SUB_CATEGORY_INFO == '市交委制发文件'}">
					       <option value='市政府制发文件' >市政府制发文件</option><option value='市交委制发文件' selected>市交委制发文件</option><option value='委属单位制发文件'>委属单位制发文件</option>
					    </c:when>  
					    <c:when test="${textNewsEditInfo.SUB_CATEGORY_INFO == '委属单位制发文件'}">
					       <option value='市政府制发文件' >市政府制发文件</option><option value='市交委制发文件'>市交委制发文件</option><option value='委属单位制发文件' selected>委属单位制发文件</option>
					    </c:when>
					    <c:when test="${textNewsEditInfo.SUB_CATEGORY_INFO == '中央党内法规'}">
					       <option value='中央党内法规' selected>中央党内法规</option><option value='重庆市委党内法规'>重庆市委党内法规</option><option value='重庆市交通党委规范性文件'>重庆市交通党委规范性文件</option>
					    </c:when>
					    <c:when test="${textNewsEditInfo.SUB_CATEGORY_INFO == '重庆市委党内法规'}">
					       <option value='中央党内法规' >中央党内法规</option><option value='重庆市委党内法规' selected>重庆市委党内法规</option><option value='重庆市交通党委规范性文件'>重庆市交通党委规范性文件</option>
					    </c:when>
					    <c:when test="${textNewsEditInfo.SUB_CATEGORY_INFO == '重庆市交通党委规范性文件'}">
					       <option value='中央党内法规'>中央党内法规</option><option value='重庆市委党内法规'>重庆市委党内法规</option><option value='重庆市交通党委规范性文件' selected>重庆市交通党委规范性文件</option>
					    </c:when>
					   <c:otherwise>  
					     <option value="">-= 请选择  =-</option>
					   </c:otherwise>
					 </c:choose>
        		</select>     
                </li>  
                <br/>
                <li>
                	<label></label>
                    <input id="btn1"  type="button" class="btn-gray" onclick="saveSubmit(1)"  value="保存修改" />
                    <a href="javascript:history.go(-1);" class="btn-gray"><b class="icon i-back"></b>返 回</a>
                </li>
                <br/>
            </ul>
            </form>
        </div>
    </div>
    <!--column01 End-->
     
     
</div>
</body>

<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>

<script type="text/javascript">
window.APP_HOME_URL = "${pageContext.request.contextPath}";
function show1(){
	 window.open ('${StaticResourcePath}/${textstatuteEditInfo.photoUrl}','add', 
	 'height=600, width=800, top=100, left=200, toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no, status=no') ;
	 } 
$(function(){
	UE.getEditor('myEditor');
	//复选框选中
	var ischeck = '${textstatuteEditInfo.isPhotosShow}';
	if(ischeck == 1){
		  $("#isPhotosShowCheck").attr("checked", true);
		  $("#isPhotosShowCheck").val(1);
	}
	
	
	
$("#StatuteCat").change(function(){
		
		switch ($(this).val()) {
		case '交通地方性法规库':
			$("#Statute").html("<option value=''>-= 请选择  =-</option>");
			break;
		case '交通地方政府规章库':
			$("#Statute").html("<option value=''>-= 请选择  =-</option>");
			break;
		case '交通行政规范性文件库':
			$("#Statute").html("<option value='市政府制发文件'>市政府制发文件</option><option value='市交委制发文件'>市交委制发文件</option><option value='委属单位制发文件'>委属单位制发文件</option>");
			break;
		case '党内法规库（A分类）':
			$("#Statute").html("<option value='中央党内法规'>中央党内法规</option><option value='重庆市委党内法规'>重庆市委党内法规</option><option value='重庆市交通党委规范性文件'>重庆市交通党委规范性文件</option>");
			break;
		case '党内法规库（B分类）':
			$("#Statute").html("<option value='中央党内法规'>中央党内法规</option><option value='重庆市委党内法规'>重庆市委党内法规</option><option value='重庆市交通党委规范性文件'>重庆市交通党委规范性文件</option>");
			break;

		default:
			break;
		}
		
	})
	
	
});

function delImg(obj){
	$(obj).remove();
}

function uploadFile(obj){
    ajaxFileUpload(obj);
}

function ajaxFileUpload(obj){
	//判断是否有选择上传文件
    var imgPath = $("#statuteImage").val();
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
        url: 'textstatute.xhtml?action=uploadFile', 
        type: 'post',
        secureuri: false, //一般设置为false
        fileElementId: 'statuteImage', // 上传文件的id、name属性名
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
function chooseSitestatute(obj) {
	if (obj.checked) {
		$("#statuteCategory").attr("disabled", false);
		$("#showPhotoState").show();//新增图片点击显示 wl
		$("#showPublicState").show();//新增是否上外网显示 wl
	} else {
		$("#statuteCategory")[0].selectedIndex = 0;
		$("#statuteCategory").attr("disabled", true);
		$("#showPhotoState").hide(); //新增图片点击隐藏 wl
		$("#showPublicState").hide();//新增是否上外网显示wl
	}
}
function checkFormData() {
	//待完成
	return true;
}
$(function(){
	var valType = $("#statuteTagChecked").attr("value");
	/*
	$("input[name='statuteTag']:checkbox").each(function(){
		if(valType.indexOf($(this).val())>=0||$(this).val()==valType){
			$(this).attr("checked","checked");
			if($(this).val()=='Sitestatute'){
				$("#statuteCategory").attr("disabled", false);
				$("#showPhotoState").show();//新增图片点击显示 wl
				$("#showPublicState").show();//新增是否上外网显示 wl
				var category = $("#categoryEdit");
				$("#statuteCategory option").each(function(){
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
	
	$("input[name='statuteTitle']").keyup(function(){
		var title1 = $(this).val();
		if(title1 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>标题不能为空</span>");
		}
	});
	$("input[name='statuteAuthor']").keyup(function(){
		var title2 = $(this).val();
		if(title2 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>上传人不能为空</span>");
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
  	$("#statuteContent").val(UE.getEditor('myEditor').getContent());
	$("#myEditor").keyup(function(){
		var title5 = $("#statuteContent").val();
		if(title5 != "" ){
			$(this).next(".error").remove();
		}
	});   
});    

function saveSubmit(flag){
	asyncbox.confirm("涉密信息禁止上网!", "信息窗口", function(action) { 
		if (action == "ok") {
			saveTextstatute(flag);
		}
	});
}

function saveTextstatute(flag) {
	
	
  	var statutetitle =  $("input[name='statuteTitle']").val();
 	var statuteauthor = $("input[name='statuteAuthor']").val();
	//校验输入框
    if(statutetitle =="" && statutetitle.length == 0){
    	$("input[name='statuteTitle']").next(".error").remove();
		$("input[name='statuteTitle']").after("<span class='error'>标题不能为空</span>");
		return;
	}     
 	if(statuteauthor == "" && statuteauthor.length == 0){
 		$("input[name='statuteAuthor']").next(".error").remove();
		$("input[name='statuteAuthor']").after("<span class='error'>上传人不能为空</span>");
		return;
	}
 
 
	$("#statuteContent").val(UE.getEditor('myEditor').getContent());
	//校验内容
	if($("#statuteContent").val() == ''||$("#statuteContent").val().length==0){
		$(".textEditBox").next(".error").remove();
		$(".textEditBox").after("<span class='error'>内容不能为空</span>");
		return;
	}else{
		$(".textEditBox").next(".error").remove();
	}
	 
 
	var options = {
		url : 'textStatute.xhtml?action=updateTextStatute',
		type : 'post',
		dataType : "html",
		beforeSubmit : checkFormData,
		success : function(data) {			
		    var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg + "是否返回法律法律页面？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "textStatute.xhtml?face=StatutelistView";
				}
			});  
		},
		error :function(data){
			asyncbox.confirm("内部错误，保存失败", "信息窗口");
		}
	};
	$("#textstatuteForm").ajaxSubmit(options);
}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.all.min.js"></script>
</html>
