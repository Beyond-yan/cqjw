<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
</style>
<title>政务信息查询</title>
</head>
<body>
<div>
	<!--path Start-->
    <div class="path">
        <b class="icon i-home"></b>
        <span>当前位置：</span>
		信息报送
        <span>-></span>
		政务信息采编
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
        <!-- 	<div style="margin-bottom: 10px;"> -->
        		<label>标题：</label><input type="text" name="newsTitle" class="input-text" value="${newsTitle}">
        		<label>投稿人：</label><input type="text" name="entryUser" class="input-text" value="${entryUser}">
        		<input type="hidden" name="isSelected" >
                <label>创建时间：</label>
                <div class="data" id="dataSelect-start">
                    <input type="text"  name="entryDateS" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput">
                </div>
                <span>&nbsp;～</span>
                <div class="data" id="dataSelect-end">
                	<input type="text" name="entryDateE" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>' class="input-text tcal tcalInput" >
                	<input type="hidden" name="newsTag" value="GovNews">
                </div>
                <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">搜  索</a>
                <a href="javascript:;" onclick="mergeInformation();return false;" class="btn btn-blue">合并信息</a>
              <!--   <a href="textNews.xhtml?face=addView" class="btn btn-blue">新  增</a>  -->
              <input type="hidden" name="zxx" class="input-text" value="${zxx}">
              <input type="hidden" name="hb" class="input-text" value="${hb}">
        	</div>
     </div>
     <!--column01 End-->
     <!--column02 Start-->
     <div class="column" >
          <div class="title"><h2><b class="icon i-search"></b>政务信息列表</h2></div>
            <div class="con">
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>
                    <td width="4%">主信息</td>   
                    <td width="2%"><input type='checkbox' name='selAllCheckBox' id='selAll'></td>                  
                    <td width="4%">序号</td>
                    <td width="24%">主标题</td>
                    <td width="7%">状态</td>
                    <td width="15%">投稿部门</td>
                    <td width="8%">投稿人</td>
                    <td width="15%">创建时间</td>
                    <td width="5%">采编</td>
                    <td width="5%">删除</td>
                  </tr>
                </thead>
                <tbody id="tbodyNews">
                <c:forEach items="${paginations}" var="tn" varStatus="status">
					<tr>
					    <td><input type="radio" class="chk"  name='chk' onclick ="get_onclick(this)"value="${tn.newsId}"/></td>
						<td><input type="checkbox" class="checkBox"  name='singleBox' value="${tn.newsId}"/></td>
						<td>${status.index+1}</td>
						<th><a href="javascript:;"  onclick="adoptGovNews('${tn.newsId}','${tn.govUseFlag}');return false;"  target="_blank"><span style='color:blue'>${tn.newsTitle}</span></a></th>
						<td><%--<c:if test='${tn.govUseFlag==1}'>已采编</c:if>--%>
							<c:if test='${tn.govUseFlag==0}'>未采编</c:if>
							<c:if test='${tn.govUseFlag==1}'>采编已成刊</c:if>
							<c:if test='${tn.govUseFlag==2}'>采编已成刊</c:if> 
							<c:if test='${tn.govUseFlag==3}'>采编已成刊</c:if> 
<%-- 							<c:if test='${tn.govUseFlag==2}'>采编未成刊</c:if>  --%>
<%-- 							<c:if test='${tn.govUseFlag==3}'>采编未成刊</c:if>  --%>
						</td>
                        <td>${tn.deptName}</td>
                        <td>${tn.entryUser}</td>
                        <td>${tn.createDate}</td>
                        <td><a href="javascript:;"  onclick="adoptGovNews('${tn.newsId}','${tn.govUseFlag}');return false;" class="icon i-edit"></a></td>
                        <td><a href="javascript:;"  onclick="deleteGovNews('${tn.newsId}','${tn.outerNewsId}')" class="icon i-del-02"></a></td>
					</tr>
				</c:forEach>
			    </tbody>
			</table>
			<div id="govInfoListPager" class="page clearfix"></div>
        </div>
    </div>
    <!--column02 End-->
</div>
<%@ include file="/common/loading.jsp"%>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function(){
	<c:if test="${!empty paginations[0].totalCount}">
	$("#govInfoListPager").buildPager({
		totalLines:${paginations[0].totalCount},
		pageSize:${paginations[0].pageSize},
		startIndex:${paginations[0].pageIndex},
		displayNum:5,
		afterChange:function() {
			searchList();
		}
	});
	</c:if>	
	var hbType = $("input[name='hb']").val();
	//alert("111");
	$("input[name='chk']:radio").each(function(){
		if(hbType.indexOf($(this).val())>=0||$(this).val()==hbType){
			$(this).attr("checked","checked");
		}
	});	
	//alert("222");
	var valType = $("input[name='zxx']").val();
	//alert("000");
	$("input[name='singleBox']:checkbox").each(function(){
		if(valType.indexOf($(this).val())>=0||$(this).val()==valType){
			$(this).attr("checked","checked");
		}
	});	
	
});
function jumpToPage(pIndex) {
	//alert(pIndex);
}

function searchList() {
	var newsTitle =  encodeURI(encodeURI($("input[name='newsTitle']").val()));
	newsTitle = newsTitle.replace(/#/g,"%23");
	var entryUser =  encodeURI(encodeURI($("input[name='entryUser']").val()));
	var entryDateS =  $("input[name='entryDateS']").val();
	var entryDateE =  $("input[name='entryDateE']").val();
	//var pageIndex = $("#pageIndex").val();
	var newsTag =  $("input[name='newsTag']").val();
	var zxx = $("input[name='zxx']").val();

	var pageIndex = $("#govInfoListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	//选中主信息和需要合并信息newsid
	var arrChk=$("input[name='singleBox']:checked");
	var ids ="";
    //遍历得到每个checkbox的value值
    for (var i=0;i<arrChk.length;i++){
        if(i==0){
            ids = arrChk[i].value;
        }else{
            ids = ids+","+arrChk[i].value;
        }
   } 
    if(ids!=""||ids!=null){
    	if (zxx!=""){
    		var zxx = $("input[name='zxx']").val()+","+ids;	
    	}
    	else{
		var zxx = $("input[name='zxx']").val()+ids;
    	}
		// alert("001");
    }else{
    	var zxx = $("input[name='zxx']").val();
    }
    if($("input[name='isSelected']").val()!=""){
    	var hb = $("input[name='isSelected']").val();
    }else{
    	var hb = $("input[name='hb']").val();
    }
	//alert("zxx:"+zxx+"======hb:"+hb);
	location.href ="govTextNews.xhtml?face=searchGovInfoView&newsTitle="+newsTitle+"&entryUser="+entryUser+"&entryDateS="
	+entryDateS+"&entryDateE="+entryDateE+"&pageIndex="+pageIndex+"&newsTag="+newsTag+"&zxx="+zxx+"&hb="+hb;
	/*  $.ajax({
		url : "govTextNews.xhtml?action=searchGovInfoView",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
			"newsTitle" : newsTitle,
			"entryUser" : entryUser,
		    "entryDateS" : entryDateS,
		    "entryDateE" : entryDateE,
		    "pageIndex" : pageIndex,
		    "newsTag" : newsTag
		},
		success : function(data) {
			var json = $.parseJSON(data);
			var pageIndex=json.pageIndex;
			var pageSize=json.pageSize;
			var totalCount =json.totalCount;
			  
			var textNewsList="";
			    $.each(json.pageData, function(i, n) {
			
			    	var useFlag = n.govUseFlag;
			    	var state="";
			    	if(useFlag== '1'){
			    		state="已采编";
			    		textNewsList+= "<tr>"+
	    				 "<td>"+(i+1)+"</td>"+
	    				 "<th><a href='govTextNews.xhtml?face=editGovInfoView&newsId="+n.newsId+"'>"+"<span style='color:blue'>"+n.newsTitle+"</span></a></th>"+
                         "<td>"+state+"</td>"+
                         "<td>"+n.deptName+"</td>"+
                         "<td>"+n.entryUser+"</td>"+
                         "<td>"+n.createDate+"</td>"+
                         "<td><a href='govTextNews.xhtml?face=editGovInfoView&newsId="+n.newsId+"' class='icon i-edit'></a></td>"+
                         "<td><a href='javascript:;'  onclick=deleteGovNews('"+n.newsId+"');return false; class='icon i-del'></a></td></tr>"
			    	}
			    	if(useFlag=='0'){
			    		state="未采编";
			    		textNewsList+= "<tr>"+
	    				 "<td>"+(i+1)+"</td>"+
	    				 "<th><a href='govTextNews.xhtml?face=adoptGovInfoView&newsId="+n.newsId+"'>"+"<span style='color:blue'>"+n.newsTitle+"</span></a></th>"+
                         "<td>"+state+"</td>"+
                         "<td>"+n.deptName+"</td>"+
                         "<td>"+n.entryUser+"</td>"+
                         "<td>"+n.createDate+"</td>"+
                         "<td><a href='govTextNews.xhtml?face=adoptGovInfoView&newsId="+n.newsId+"' class='icon i-edit'></a></td>"+
                         "<td><a href='javascript:;'  onclick=deleteGovNews('"+n.newsId+"');return false; class='icon i-del'></a></td></tr>"
	  
	    
			    	}
			    });
				  $("#tbodyNews").html("");
				  $("#tbodyNews").html(textNewsList);
				  
				  $("#govInfoListPager").buildPager({
						totalLines:totalCount,
						pageSize:pageSize,
						startIndex:pageIndex,
						displayNum:5,
						afterChange:function() {
							searchList();
						}
				  });
				
			
			}
			
		});  */
}


//删除信息
function deleteGovNews(newsId,outerNewsId) {
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) { 
		if (action == "ok") {
			$.ajax({
				url : "govTextNews.xhtml?action=deleteInfo",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"outerNewsId" : outerNewsId,
					"newsId" : newsId
				},
				success : function(content) {
					/* asyncbox.alert(content, "信息窗口", function() {
						document.location.reload();
					}); */
					asyncbox.alert(content,"信息窗口");
					//document.location.reload();
					searchList();
				}
			});
		}
	})
}
//采编
//添加采编状态修改 by llj
function adoptGovNews(newsId, flag){
	var newsTitle =  encodeURI(encodeURI($("input[name='newsTitle']").val()));
	newsTitle = newsTitle.replace(/#/g,"%23");
	var entryUser =  encodeURI(encodeURI($("input[name='entryUser']").val()));
	var entryDateS =  $("input[name='entryDateS']").val();
	var entryDateE =  $("input[name='entryDateE']").val();
	//var pageIndex = $("#pageIndex").val();
	var newsTag =  $("input[name='newsTag']").val();
	var zxx = $("input[name='zxx']").val();

	var pageIndex = $("#govInfoListPager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	//选中主信息和需要合并信息newsid
	var arrChk=$("input[name='singleBox']:checked");
	var ids ="";
    //遍历得到每个checkbox的value值
    for (var i=0;i<arrChk.length;i++){
        if(i==0){
            ids = arrChk[i].value;
        }else{
            ids = ids+","+arrChk[i].value;
        }
   } 
    if(ids!=""||ids!=null){
    	if (zxx!=""){
    		var zxx = $("input[name='zxx']").val()+","+ids;	
    	}
    	else{
		var zxx = $("input[name='zxx']").val()+ids;
    	}
		// alert("001");
    }else{
    	var zxx = $("input[name='zxx']").val();
    }
    if($("input[name='isSelected']").val()!=""){
    	var hb = $("input[name='isSelected']").val();
    }else{
    	var hb = $("input[name='hb']").val();
    }
	var url="&newsTitle="+newsTitle+"&entryUser="+entryUser+"&entryDateS="
		+entryDateS+"&entryDateE="+entryDateE+"&pageIndex="+pageIndex+"&newsTag="+newsTag+"&zxx="+zxx+"&hb="+hb;
	
	if (flag=="0"){
		location.href ="govTextNews.xhtml?face=adoptGovInfoView&newsId="+newsId+url;
	} else {
		location.href ="govTextNews.xhtml?face=editGovInfoView&newsId="+newsId+"&flag="+flag+url;
	} 
}
//全选或取消
$("#selAll").click(function(){
    $('input[name=singleBox]').attr("checked", this.checked );
})
//合并信息
function mergeInformation(){
	var hb = $("input[name='hb']").val();
	var zxx = $("input[name='zxx']").val();
	var newsTag = "";
	$("input[name='newsTag']").each(function(){
		if (this.checked) {
			if (newsTag == ""){
				newsTag = this.value
			} else {
				newsTag += "," + this.value;
			}
		}
	});
	var isSelected = $("input[name='isSelected']").val();
	var arrChk=$("input[name='singleBox']:checked");
	if (hb !="" && arrChk.length >=1){
		asyncbox.confirm("是否要合并信息?", "信息窗口", function(action) { 
			if(action=='ok'){
				var ids ="";
				if (hb!==""){
					var isSelected =hb ;
				} else {
					var isSelected = $("input[name='isSelected']").val();
				}				
			    //遍历得到每个checkbox的value值
			    for (var i=0;i<arrChk.length;i++){
			        if(i==0){
			            ids = arrChk[i].value;
			        }else{
			            ids = ids+","+arrChk[i].value;
			        }
			    } 
			    if (zxx!=""){
			    	ids=ids+","+zxx;	
			    }
			    showLoad();
			    $.ajax({
					url : "govTextNews.xhtml?action=mergeInformation",
					cache : false,
					type : "GET",
					dataType : "text",
					data : {
						"newsIds" : ids,
						"isSelected":isSelected
					},
					success : function(data) {
						hideLoad();
						asyncbox.alert($.parseJSON(data).msg,"信息窗口");
						searchList();
						//document.location.href="govTextNews.xhtml?face=listGovInfoView";
					}
			    })
			} else{
				//document.location.href="govTextNews.xhtml?face=listGovInfoView";
			}
		});
		
	}
	if (hb =="" && arrChk.length ==1){
		//alert("2");
		asyncbox.confirm("是否要合并信息?", "信息窗口", function(action) { 
			if(action=='ok'){
				var ids ="";
				if (hb!==""){
					var isSelected =hb ;
				} else {
					var isSelected = $("input[name='isSelected']").val();
				}				
			    //遍历得到每个checkbox的value值
			    for (var i=0;i<arrChk.length;i++){
			        if(i==0){
			            ids = arrChk[i].value;
			        }else{
			            ids = ids+","+arrChk[i].value;
			        }
			   	} 
			    if (zxx!=""){
			    	ids=ids+","+zxx;	
			    }
			    showLoad();
			    $.ajax({
					url : "govTextNews.xhtml?action=mergeInformation",
					cache : false,
					type : "GET",
					dataType : "text",
					data : {
						"newsIds" : ids,
						"isSelected":isSelected
					},
					success : function(data) {
						hideLoad();
						asyncbox.alert($.parseJSON(data).msg,"信息窗口");
						searchList();
						//document.location.href="govTextNews.xhtml?face=listGovInfoView";
					}
			    })
			} else{
				//document.location.href="govTextNews.xhtml?face=listGovInfoView";
			}
		});
		
	} else if(arrChk.length>1) {
		asyncbox.confirm("是否要合并信息?", "信息窗口", function(action) { 
			if(action=='ok'){
				var ids ="";
				if (hb!==""){
					var isSelected =hb ;
				} else {
					var isSelected = $("input[name='isSelected']").val();
				}				
			    //遍历得到每个checkbox的value值
			    for (var i=0;i<arrChk.length;i++){
			        if(i==0){
			            ids = arrChk[i].value+zxx ;
			        }else{
			            ids = ids+","+arrChk[i].value;
			        }
			   	} 
			    showLoad();
			    $.ajax({
					url : "govTextNews.xhtml?action=mergeInformation",
					cache : false,
					type : "GET",
					dataType : "text",
					data : {
						"newsIds" : ids,
						"isSelected":isSelected
					},
					success : function(data) {
						hideLoad();
						asyncbox.alert($.parseJSON(data).msg,"信息窗口");
						searchList();
						//document.location.href="govTextNews.xhtml?face=listGovInfoView";
					}
			    })
			} else{
				//document.location.href="govTextNews.xhtml?face=listGovInfoView";			
			}
		});
	} else if (hb =="" && arrChk.length ==0 &&zxx==""){
    	asyncbox.alert("请选择要合并的信息！","信息窗口");
    } 
}
</script>
<script type="text/javascript">
		var uid=0;
		//只能选中一个值;
		function get_onclick(obj){
			uid = obj.value;
	        $("input:checkbox[value='"+uid+"']").attr('checked','true');  
	        $("input[name='isSelected']").val(uid);
		}
</script>
</html>
