<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ 
	taglib prefix="ut" uri="/WEB-INF/userTag/Pager.tld"%>
	<%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><!DOCTYPE html 
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
		政务信息
        <span>-></span>
		政务信息回收站
    </div>
    <!--path End-->
    <!--column01 Start-->
    <div class="column" id="search">
        <div class="title"><h2><b class="icon i-search"></b>快速搜索</h2></div>
        <div class="con">
        		<label>标题：</label><input type="text" name="newsTitle" class="input-text" value="${newsTitle}">
        		 <label>投稿人：</label><input type="text" name="entryUser" class="input-text" value="${entryUser}">
                <label>投稿时间：</label>
                <div class="data" id="dataSelect-start">
                    <input type="text"  name="entryDateS" class="input-text tcal" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>'>
                </div>
                <span>&nbsp;～</span>
                <div class="data" id="dataSelect-end">
                	<input type="text" name="entryDateE" class="input-text tcal" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>'>
                </div>
                <a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">搜索</a>
                <a href="javascript:;" onclick="deleteSiteAll();return false;" class="btn btn-blue">彻底删除</a>
        </div>
   	 </div>
     <!--column01 End-->
     
     <!--column02 Start-->
 	 <div class="column" >
        <div class="title"><h2><b class="icon i-search"></b>信息列表</h2></div>
        <div class="con">
        	
        	<table width="100%" border="1" class="dataList">
                <thead>
                  <tr>  
                    <td width="2%"><input type='checkbox' name='selAllCheckBox' id='selAll'></td>                  
                    <td width="5%">序号</td>
                    <td width="30%">主标题</td>
                    <td width="8%">状态</td>
                    <td width="15%">投稿时间</td>
                    <td width="8%">投稿人</td>
                    <td width="5%">复原</td>
                  </tr>
                </thead>
                <tbody id="tbodyNews">
                <c:forEach items="${paginations}" var="tn" varStatus="status">
					<tr>
					    <td><input type="checkbox" class="checkBox"  name='singleBox' value="${tn.newsId}"/></td>
						<td>${tn.rowNo}</</td>
						<th><a href="../guest/news.xhtml?face=newsDetail&newsId=${tn.newsId}" target="_blank>"><span style='color:blue'>${tn.newsTitle}</span></a></th>
                        <td>
                        <c:if test='${tn.isDel==1}'>已删除</c:if><c:if test='${tn.isDel==0}'>未删除</c:if>
						</td>
                        <td>${tn.createDate}</td>
                        <td>${tn.entryUser}</td>
                        <td><a  href="javascript:;" onclick="deleteRecyleGov('${tn.newsId}');return false;" class="icon i-back"></a></td>
                        
					</tr>
				</c:forEach>
			    </tbody>
			</table>
			<div id="recycleSitePager" class="page clearfix"></div>
        </div>
    </div>
    <!--column02 End-->
</div>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/j.pager.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
$(function(){
	var totalCount = $("#totalCount").val();
	var pageSize = $("#pageSize").val();
	var pageIndex = $("#pageIndex").val();
	<c:if test="${!empty paginations[0]}">
	$("#recycleSitePager").buildPager({
		totalLines:${paginations[0].totalCount},
		pageSize:${paginations[0].pageSize},
		startIndex:${paginations[0].pageIndex},
		displayNum:5,
		afterChange:function() {
			searchList();
		}
	});
	</c:if>
});

function searchList() {
	var newsTitle =  encodeURI(encodeURI($("input[name='newsTitle']").val()));
	newsTitle = newsTitle.replace(/#/g,"%23");
	var entryUser = encodeURI(encodeURI($("input[name='entryUser']").val()));
	var entryDateS =  $("input[name='entryDateS']").val();
	var entryDateE =  $("input[name='entryDateE']").val();
	var pageIndex = $("#recycleSitePager").attr("curPage");
	if (typeof pageIndex == "undefined") {
		pageIndex = 0;
	}
	location.href ="govTextNews.xhtml?face=queryRecycleGovInfoView&newsTitle="+newsTitle+"&entryUser="+entryUser+"&entryDateS="
	+entryDateS+"&entryDateE="+entryDateE+"&pageIndex="+pageIndex;
	
	/* $.ajax({
		url : "siteTextNews.xhtml?action=queryRecycleGovInfoView",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
			"newsTitle" : newsTitle,
			"entryUser" : entryUser,
		    "entryDateS" : entryDateS,
		    "entryDateE" : entryDateE,
		    "pageIndex" : pageIndex
		},
		success : function(data) {
		        var json = $.parseJSON(data);
		    	$("#totalCount").attr("value",json.totalCount);
				$("#pageSize").attr("value",json.pageSize);
				$("#pageIndex").attr("value",json.pageIndex);
			    var textNewsList="";
			    $.each(json.pageData, function(i, n) {
			    	 var newsId=n.newsId;
			    	 var govUseFlag =n.govUseFlag;
				     var entryDate=n.entryDate;
			    	 var isNewsShow=n.isPhotosShow;
			    	 var show="";
			    	 if(isNewsShow==1){
			    		 show="是";
			    	 }else{
			    		 show="否";
			    	 }
			    	 var isDel = n.isDel;
				     var delStr = "";
				     if(isDel==1){
				    	 delStr="已删除";
				     }else{
				    	 delStr="未删除";
				     }
		textNewsList+= "<tr>"+
	   	             "<td>"+(i+1)+"</td>"+
                     "<th>"+"<a href='../guest/news.xhtml?face=newsDetail&newsId="+newsId+"'  target='_blank'>"+"<span style='color:blue'>"+n.newsTitle+"</span></th>"+
                     "<td>"+show+"</td>"+
                     "<td>"+delStr+"</td>"+
                     "<td>"+entryDate+"</td>"+
                     "<td>"+n.entryUser+"</td>"+
                     "<td><a href='javascript:;' onclick=deleteRecyleGov('"+newsId+"');return false; class='icon i-back'></a></td></tr>"
                });
              $("#tbodyNews").html("");
              $("#tbodyNews").html(textNewsList); 
			},
			error :function(data){
				alert("内部错误!");
			}
		}); */
}

//逻辑删除
function deleteRecyleGov(newsId) {
	
	//alert(newsId);
	asyncbox.confirm("确定复原该笔信息?", "信息窗口", function(action) {
		
		if (action == "ok") {
			$.ajax({
				url : "govTextNews.xhtml?action=deleteRecycleSiteInfo",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"newsId" :newsId
				},
				success : function(data) {			
					 var data = eval('(' + data + ')');
					asyncbox.confirm(data.msg, "确认窗口", function(action) {
						if (action == "ok") {
							document.location.reload();
						}
					}); 
				},
				error :function(data){
					var data = eval('(' + data + ')');
					asyncbox.confirm(data.msg, "确认窗口", function(action) {
						if (action == "ok") {
							document.location.reload();
						}
					}); 
				/* alert("内部错误，保存失败"); */ 
				}
			});
		}
	})
}
//全选或取消
$("#selAll").click(function(){
         $('input[name=singleBox]').attr("checked", this.checked );
});
//彻底删除
function deleteSiteAll(){
	var arrChk=$("input[name='singleBox']:checked");
	if(arrChk.length>0)
	{
		asyncbox.confirm("是否彻底删除?", "信息窗口", function(action) { 
			if(action=='ok'){
				var ids ="";
			    //遍历得到每个checkbox的value值
			    for (var i=0;i<arrChk.length;i++){
			        if(i==0){
			            ids = arrChk[i].value;
			        }else{
			            ids = ids+","+arrChk[i].value;
			        }
			   } 
			    $.ajax({
					url : "govTextNews.xhtml?action=deleteGovAll",
					cache : false,
					type : "GET",
					dataType : "text",
					data : {
						"newsIds" : ids
					},
					success : function(data) {
						/* $("input[name='singleBox']").attr("checked","false"); */
						document.location.href="govTextNews.xhtml?face=getRecycleGovInfoView";
					}
			    })
			}
		});
	}
    else{
    	asyncbox.alert("请选择需要删除的信息！","信息窗口");
    } 
}

</script>
</html>
