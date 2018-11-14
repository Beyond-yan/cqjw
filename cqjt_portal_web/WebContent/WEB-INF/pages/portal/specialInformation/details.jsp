<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><%@page 
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--专报信息详情页</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/news.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.column .con li a { max-width:220px; }
/*.newsDetail-con img{width:16px;height:16px}*/
.newsDetail{font: 16px/30px 宋体}

.btn:hover{color:#fff;}
.btn-blue {
    background-position: 0 0;
    color: #fff;
    font: 12px/21px 宋体;
    height: 21px;
    margin: 0 10px;
    text-align: center;
    width: 66px;
    background-image: url("../images/bg_sysSprite.png");
    background-repeat: no-repeat;
    display: inline-block;
    margin-left: 640px;
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
                <a href="javascript:;">专报信息</a>
            </div>
            <%@include file="../index/search.jspf"%>
        </div>
    	<!--search-box End-->
        <!--sub Start-->
        <div class="sub l">
        	<div class="column" id="userLogin">
            	<div class="title">
                	<h2>专报信息</h2>
                </div>
                <div class="con">
                	<ul class="infoList">
                		<c:forEach items="${specialVoList}" var="s">
                    	<li>
                            <a href="../guest/specialInformation.xhtml?face=specialVoDetails&specialId=${s.specialId}"><b class="i-rArr"></b>${s.specialTitle}</a>
                            <input type="hidden" id="specialId" value="${s.specialId}">
                        </li>
                    	</c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <!--sub End-->
        <!--main Start-->
        <div class="main">
        	<div class="column" >
                <!--<div class="title"><h2>每日信息</h2></div>-->
                <div class="con newsDetail">
                    <h2>${specialVo.specialTitle}</h2>
                    <p class="dotLine_2px"></p>
                    <ul class="tip clearfix " style="table-layout:fixed;">
                        <li class="al_l" style="word-break:break-all;">
                        	发布日期：
                        	<fmt:parseDate value="${specialVo.specialCreateDateStr}" var="date"/>
							<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>
                        </li>
                        <li class="al_c">发布：${specialVo.specialCreateUserName }</li>
                    </ul>
                    <ul>
                    <div class="newsDetail-con">
                    <iframe frameborder="0" src="${StaticResourcePath}/${specialVo.specialAccessoryPdfUrl}" width="650px" height="600"></iframe>
                    <li>
                    	
					</li>
					</div>
					</ul>
					<c:if test="${!empty  specialVo.specialAccessoryUrl}">
    				<h3><a href="JavaScript:downLoad('${specialVo.specialAccessoryUrl }','${specialVo.specialAccessoryName }')" class="btn btn-blue">下载附件</a></h3>
    				</c:if>
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
function exportTable(){
	var pubId = $("#pubId").val();
	location.href ="../guest/news.xhtml?action=exportTable&pubId="+pubId;
}

function downLoad(filePath,name){
	var fileName=encodeURI(encodeURI(name));
	
	location.href="../guest/index.xhtml?face=downLoad&fileName="+fileName+"&filePath="+filePath;
}
</script>
</body>
</html>