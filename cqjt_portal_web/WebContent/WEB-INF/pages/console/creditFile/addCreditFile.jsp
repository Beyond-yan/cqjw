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
<link href="${StaticResourcePath}/css/chosen.min.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/plugin/ueditor/themes/default/css/ueditor.css" rel="stylesheet" type="text/css" />
<style type="text/css">
a {color:blue;cursor: pointer;}
.column {min-width:940px;}
.error{color:red;padding-left: 10px;}
</style>
<title>投稿</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		信用法规文件
        <span>-></span>
		法规文件新增
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>信用法规文件录入</h2></div>
        <div class="con docEdit" >
			<form id="textNewsForm" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="newsContent" id="newsContent" />
			<input type="hidden" name="newsTagsStr" id="newsTags" />
			<input type="hidden" name="flag" id="newsFlag" />
			<input type="hidden" name="entryDept" value="${userDeptID}"/>
			<input type="hidden" name="deptCategory" value="${deptCategory}"/>
            <input type="hidden" name="outerCategory" class="input input-small"/>
            <ul>
                <li>
	                <label><span style="color:red">*</span>标题：</label>
	                <input type="text" name="newsTitle" class="input input-large">
                </li>
                <li>
                    <label><span style="color:red">*</span>作者：</label>
                    <input type="text" name="newsAuthor"  value="${userName}"class="input input-small">
                    
	                <label><span style="color:red"></span>审核人：</label>
	                <select class="select-small" name="approvaler">
	                    <option value="">--请选择审核人--</option>
						<c:forEach items="${approvers}" varStatus="status">
	                    <option>${status.current.userName}</option>
	                    </c:forEach>
	                </select>
                </li>
                <li>
                    <label>来源出处：</label>
                    <input type="text" name="newsSource" class="input input-small">
                    
                    <label><span style="color:red">*</span>投稿部门：</label>
                    <select class="select-small" disabled="disabled" name="userDept">
	                    <option value="${userDeptID}">${userDeptName}</option>
	                </select>
	                <input type="hidden" name="deptName" value="${userDeptName}" class="input input-small">
                    <input type="hidden" name="entryUser" value="${userName}" class="input input-small">
                </li>
                <li>
	                <label><span style="color:red">*</span>文件分类：</label>
	                <select class="select-small" name="approvaler">
	                    <option value="">--请选择文件分类--</option>
						<c:forEach items="${approvers}" varStatus="status">
	                    <option>${status.current.userName}</option>
	                    </c:forEach>
	                </select>
	                &nbsp;&nbsp;
	                <label><span style="color:red">*</span>发布机构：</label>
	                <select class="select-small" name="approvaler">
	                    <option value="">--请选择发布机构--</option>
						<c:forEach items="${approvers}" varStatus="status">
	                    <option>${status.current.userName}</option>
	                    </c:forEach>
	                </select>
                </li>
                 <li>
                    <label><span style="color:red">*</span>文号：</label>
                    <input type="text" name="newsSource" class="input input-small">
                </li>
                <li class="clearfix">
                    <label class="l"><span style="color:red">*</span>内容：</label>
                    <div class="textEditBox">
               	    	<script type="text/plain" id="myEditor">
                          ${newsContent}
                        </script>
               	    </div>
                </li>
                 <li>
                    <label><span style="color:red">*</span>信息类别：</label>
                    <input type="checkbox" name="newsTag" value="SiteNews" onclick="chooseSiteNews(this)"><span>网站信息</span>
                    &nbsp;&nbsp;
                    <select class="select-small" name="category" id="newsCategory" disabled="disabled" onchange="choose()">
                    	<option value="0">-=请选择栏目=-</option>
                    	<c:forEach items="${textCategorys}" var="c">
	                    <option value="${c.categoryId}">${c.categoryName}</option>
	                    </c:forEach>
	                </select>
                </li>
                <li>
                	<label>&nbsp;</label>
                    <input type="checkbox" name="newsTag" value="GovNews"><span>政务信息</span>
<!--                     <input type="checkbox" name="newsTag" value="TrafficNews"><span>交通杂志</span> -->
<!--                     <input type="checkbox" name="newsTag" value="TrafficPaper"><span>交通报</span> -->
                </li>
                <!-- 
                <li id="showPhotoState" style="display:none">
                	<label>主题图片：</label>
                	<input type="file" name="newsImage" />
                	<input type="hidden" id="image" name="photoUrl" class="input input-small">
                	
                	<label style="width:120px" id="IsRollShow">
                	<input type="checkbox" name="isPhotosShow" onclick="if(this.checked){this.value=1;}else{this.value=0;}" />
                	是否滚动显示
                	</label>
                </li>
                 -->
               <li id="showPhotoState" style="display:none">
                	<label>主题图片：</label>
                	<input type="hidden" id="image" name="photoUrl" class="input input-small">
                	<label style="width:120px" id="IsRollShow">
                	<input type="checkbox" name="isPhotosShow" onclick="if(this.checked){this.value=1;}else{this.value=0;}" />
                	是否滚动显示
                	</label>
                	<input type="file" name="newsImage" id="newsImage1" onchange="uploadFile('newsImage1')"/>
                	<div id="showImg">
                		<label>&nbsp;</label>
                	</div>		           
                </li>
                
                <li id="showPublicState" style="display:none">
                    <label><span style="color:red">*</span>是否上外网：</label>
                    <input type="radio" name="isPublic" id="public" value="1" onclick="selectOuter(0);"><span>是</span>&nbsp;&nbsp;
                    <input type="radio" name="isPublic" id="unPublic" value="0" checked="checked" onclick="selectOuter(1);"><span>否</span>
                    <div id="outer" style="display:none">
	                    <label>外网栏目：</label>
	                    <select class="select-small" name="categoryOuter" id="newsCategoryOuter">
	                    <option>-=请选择栏目=-</option>
	                    	<!--
	                    	<c:forEach items="${textCategorysOuter}" var="c">
		                    <option value="${c.outCategoryId}">${c.outCategoryName}</option>
		                    </c:forEach>-->
		                </select>
		           </div>
                </li>
                <li>
                	<label></label>
                    <a href="javascript:;" class="btn-gray" onclick="saveSubmit(1)"><b class="icon i-send"></b>提 交</a>
                    <a href="javascript:;" class="btn-gray" onclick="saveTextNews(0)"><b class="icon i-save"></b>存草稿</a>
                    <a href="javascript:history.go(-1);" class="btn-gray"><b class="icon i-back"></b>返 回</a>
                </li>
            </ul>
            </form>
        </div>
    </div>
    <!--column01 End-->
</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/chosen.jquery.min.js"></script>

<script type="text/javascript">
window.APP_HOME_URL = "${pageContext.request.contextPath}";
$(function(){
	UE.getEditor('myEditor');
});

function delImg(obj){
	$(obj).remove();
}
function uploadFile(obj){
    ajaxFileUpload(obj);
}

function ajaxFileUpload(obj){
	//判断是否有选择上传文件
    var imgPath = $("#newsImage1").val();
    if (imgPath == "") {
        alert("请选择上传图片！");
        return;
    }
    //判断上传文件的后缀名
    var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
    strExtension = strExtension.toLowerCase ();
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
        fileElementId: 'newsImage1', // 上传文件的id、name属性名
        dataType: 'text', //返回值类型，一般设置为json、application/json
        elementIds: elementIds, //传递参数到服务器
        success: function(data, status){  
        	//$("#showImg").append('src',"${StaticResourcePath}/"+data); 
        	$("#showImg").append("<a href='javascript:' onclick='delImg(this);'><img src='${StaticResourcePath}/"+data+"' width='100px' name='showUploadImg' height=150px alt='"+data+"'/></a>"); 
        	/**
        	//var json = jQuery.parseJSON(data);
        	var json = eval("(" + data + ")");
            alert("1111111111111"+json.code);
            if(json.code == 0){
            	url = json.imgUrl;            	
            	alert(url);
            	$("#show1").attr('src',url); 
            } else {
            	alert("上传失败");
            }
           */
        },
        error: function(data, status, e){ 
            //alert(e);
        }
    });
    
    /**
    
    //判断是否有选择上传文件
    var imgPath = $("#newsImage1").val();
	alert(imgPaht);
    if (imgPath == "") {
        alert("请选择上传图片！");
        return;
    }
    //判断上传文件的后缀名
    var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
    if (strExtension != 'jpg' && strExtension != 'gif' && strExtension != 'png' && strExtension != 'bmp') {
        alert("请选择图片文件");
        return;
    }
    
	$.ajax({
        type: "POST",
        url: "textNews.xhtml?action=uploadFile",
        data: { newsImage: imgPath },
        cache: false,
        enctype: 'multipart/form-data',
        success: function(data) {
            alert("data:"+data);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("上传失败，请检查网络后重试");
        }
    });*/
}
function chooseSiteNews(obj) {
	if (obj.checked) {
		$("#newsCategory").attr("disabled", false);
		$('#newsCategory').chosen();
		$("#showPhotoState").show();//新增图片点击显示 wl
		$("#showPublicState").show();//新增是否上外网显示 wl
		
	} else {
		$("#newsCategory")[0].selectedIndex = 0;
		$("#newsCategory").attr("disabled", true);
		$("#showPhotoState").hide(); //新增图片点击隐藏 wl
		$("#showPublicState").hide();//新增是否上外网显示wl
	} 
}

$(function(){
	//是否滚动显示权限设置
	var deptCategory=	$("input[name='deptCategory']").val()
	if(deptCategory=="true"){
		$("#IsRollShow").show();
	}else{
		$("#IsRollShow").hide();
	}
	
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
	$("select[name='approvaler']").click(function(){
		var title3 = $(this).val();
		if(title3 != "" ){
			$(this).next(".error").remove();
		}else{
			$(this).next(".error").remove();
			$(this).after("<span class='error'>审核人不能为空</span>");
		}
	});*/
	
	$("input[name='newsImage']").click(function(){
		   $(this).next(".error").remove();
	});
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
	/**
	var imgList=$("#showImg img");//获取ID为div里面的所有img
	for(var i=0;i<imgList.length;i++){ //循环为每个img设置
		var alt = imgList[i].alt;
		newsimage+=alt +",";
	}
	alert("前"+newsimage);
	newsimage = newsimage.substring(0, (newsimage.length - 1));
	alert(newsimage);
	**/
  	var newstitle =  $("input[name='newsTitle']").val();
 	var newsauthor = $("input[name='newsAuthor']").val();
	/*var approvaler = $("select[name='approvaler']").val(); */
	var isphotosshow  = $("input[name='isPhotosShow']").val();
	//var newsimage = $("input[name='newsImage']").val(); 
	var newsimage = $("input[name='photoUrl']").val(); 
	var category = $("select[name='category']").val();
	var newstag = $("input[name='newsTag']").val();
	$("input[name='outerCategory']").val($('#newsCategoryOuter option:selected').text());
	var outerCategory=$("input[name='outerCategory']").val();
	//alert(outerCategory);
    if(newstitle =="" && newstitle.length == 0){
    	$("input[name='newsTitle']").next(".error").remove();
		$("input[name='newsTitle']").after("<span class='error'>标题不能为空</span>");
		return;
	}     
 	if(newsauthor == "" && newsauthor.length == 0){
 		$("input[name='newsTitle']").next(".error").remove();
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
			}
			else{
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
	var img = 0;
	$("input[name='isPhotosShow']:checkbox").each(function(){
		if($(this).val()==1){
			img = 1;
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
	if(img==1){
		//$("#showImg>img")
		
		if(newsimage==""||newsimage.length==0){
			$("input[name='newsImage']").next(".error").remove();
			$("input[name='newsImage']").after("<span class='error'>请选择图片文件</span>");
			return;
		}else{
			$("input[name='newsImage']").next(".error").remove();
		}
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
	$("#newsTags").val(newsTag);
	$("#image").val(newsimage);
	var options = {
		url : 'textNews.xhtml?action=save',
		type : 'post',
		dataType : "html",
		success : function(data) {			
			var content = eval('(' + data + ')');
			asyncbox.confirm(content.msg + "是否继续增加？", "确认窗口", function(action) {
				if (action == "ok") {
					document.location.href = "textNews.xhtml?face=addView";
				}else{
					document.location.href = "textNews.xhtml?face=location";
				}
			}); 
		},
		error :function(data){
			alert("内部错误，保存失败");
		}
	};
	$("#textNewsForm").ajaxSubmit(options);
}
//判断外网栏目是否显示
function selectOuter(flag){
	if(flag==0){
		$('#outer').show();
		//$("input[name='outerCategory']").val($('#newsCategoryOuter option:selected').text());
	}else{
		$('#outer').hide();
	}
}
function choose(){
	var innerId = $('#newsCategory').val();
	//根据内网网id找到对应外网
	$.ajax({
		url : "sysMgt.xhtml?action=searchCategoryRelation",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
		    "categoryId" : innerId,
		    "type":"add"
		},
		success : function(data) {
			var json = $.parseJSON(data);
			str ="";
			$.each(json.Data, function(i, n) {
				str +="<option value="+n.outerId+">"+n.outerCategory+"</option>";
			});
			$("#newsCategoryOuter").html("");
			$("#newsCategoryOuter").append(str);
		},
		error :function(data){
			$("#newsCategoryOuter option").each(function(){
				if($(this).val()=="0"){
					$(this).attr("selected",true);
				}
			});
		}
	})
}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/ueditor/ueditor.all.min.js"></script>
</html>
