<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><%@page 
	import="com.gdssoft.core.tools.SystemContext"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--公开公文详情页</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/news.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.column .con li a { max-width:220px; }
.detail h2{color: #000;
    font: 24px/50px Microsoft YaHei;
    text-align: center;}
.table-info {
    border-collapse: collapse;
    color: #03386c;
    font-size: 12px;
    margin: 5px;
    table-layout: fixed;
}
.table-info th {
    color: #0168b7;
    font-weight: normal;
    line-height: 28px;
    vertical-align: top;
    text-align:left;
}
.table-info td {
    color: #555;
}
.table-info a {
    color: #555;
}
.table-info th, .table-info td {
    background-color: white;
    border: 1px solid #333;
    height: 28px;
    padding: 2px 2px 2px 4px;
}
</style>
</head>

<body>
<div class="wrap">
	<%@include file="../head.jspf"%>
    
    <!--content Start-->
    <div class="content clearfix" >
    	<!--search-box Start-->
    	<div class="search-box">
            <div class="breadCutNav">
                <b class="icon icon_home"></b>
                <span>当前位置：</span>
                <a href="index.xhtml">首页</a>
                <span> &gt;</span>
                <a href="javascript:;">公开公文详情</a>
            </div>
            <%@include file="../index/search.jspf"%>
        </div>
    	
        <!--main Start-->
        <div class="main" style="float:left;width:100%">
        	<div class="column" >
                <div class="con">
                	<input type="hidden" value="${archivesId}" id="archivesId">
                	<input type="hidden" value="${schema}" id="schema">
                	<div class="detail">
	                    
					</div>
    			</div>
            </div>
        </div>
        <!--main End-->
    </div>
    
    <%@include file="../footer.jspf"%>
</div>
<script type="text/javascript"  src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"  src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript">
$(function(){
	var archivesId=$("#archivesId").val();
	var schema=$("#schema").val();
	var Enclosure="",CcTo="";
	$.ajax({
		url : "../guest/news.xhtml?action=getPublicArchivesDetail",
		cache : false,
		dataType : "text",
		data : {
		    "archivesId" : archivesId,
		    "schema" : schema
		},
		success : function(data) {
			var json = $.parseJSON(data);
			var detailList = "";
			var docName = "",doc="",flag="";ArchivesNo="";
			if (json.Status=="0") {
				$.each(json.Data, function(i, n) {
					Enclosure=n.Enclosure;
					CcTo=n.CcTo;
					ArchivesNo=n.ArchivesNo;
					if(Enclosure=="null"||Enclosure==null){Enclosure="";}
					if(CcTo=="null"||CcTo==null){CcTo="";}
					if(ArchivesNo=="0"){ArchivesNo="未生成编号";}
					detailList +="<table class='table-info'  cellpadding=\"0\" cellspacing=\"1\" width=\"98%\" align=\"center\"><tr><th>发文字号</th><td colspan=\"5\">"+ArchivesNo+
						"</td></tr><tr><th>主送</th><td colspan=\"5\">"+n.SendTo+
						"</td></tr><tr><th>抄送</th><td colspan=\"5\">"+CcTo+
						"</td></tr><tr><th>标题</th><td colspan=\"5\">"+n.Subject+
						"</td></tr><tr><th>附件（无文档）</th><td colspan=\"5\">"+Enclosure+
						"</td></tr><tr><th>正文</th><td colspan=\"5\" style=\"height:60px;padding-top:5px;\" valign=\"top\">";
						for(var m=0;m<n.ArchivesDocs.length;m++){
							docName = n.ArchivesDocs[m].docName.substring();
							doc += docName.substring(docName.length-3,docName.length);
							if(doc.indexOf("pdf")>=0){
								flag = 1;
							}						
						}
						if(flag==1){
							for(var m=0;m<n.ArchivesDocs.length;m++){
								docName = n.ArchivesDocs[m].docName.substring();
								if(docName.substring(docName.length-3,docName.length)=="pdf"){
									detailList+="<a href='javascript:;' onclick=\"downLoad('"+n.ArchivesDocs[m].docPath+"','"+n.ArchivesDocs[m].docName+"')\"><span style='color:blue'>"+n.ArchivesDocs[m].docName+"</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
								}
							}
						}else{
							for(var m=0;m<n.ArchivesDocs.length;m++){				
								detailList+="<a href='javascript:;' onclick=\"downLoad('"+n.ArchivesDocs[m].docPath+"','"+n.ArchivesDocs[m].docName+"')\"><span style='color:blue'>"+n.ArchivesDocs[m].docName+"</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
							}
						}
						
						detailList+="</td></tr><tr><th>附件</th><td colspan=\"5\" style=\"height:60px;padding-top:5px;\" valign=\"top\">";
						for(var m=0;m<n.ArchivesFiles.length;m++){
							detailList+="<a href='javascript:;' onclick=\"downLoad('"+n.ArchivesFiles[m].filePath+"','"+n.ArchivesFiles[m].fileName+"')\"><span style='color:blue'>"+n.ArchivesFiles[m].fileName+"</span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
						}
						"</td></tr></table>";
				});
				$(".detail").html("");
				$(".detail").html(detailList);
			}else {
				$("#ul_public_archives li").text(json.Error_Msg);
			}
		}
	});
});
function downLoad(filePath,name){
	var fileName=encodeURI(encodeURI(name));
	
	location.href="../guest/index.xhtml?face=downLoad&fileName="+fileName+"&filePath="+filePath;
}
</script>
</body>
</html>