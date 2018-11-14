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
.main{width:1000px;margin:0 0 0 8px;}
/* .content .column{height:366px;overflow:hidden;} */
body{background-image: none;}
.wrap {
    margin: 0 auto;
    padding-top: 0px;
    width: 1000px;
}
.icon{
	background-image:url("../images/bg_sysSprite.png");
	background-repeat:no-repeat;
}
.i_home{
    background-position: -76px -38px;
    height: 17px;
    vertical-align: middle;
    width: 14px;
}
.btn-gray {
    background-position: 0 -280px;
    border: 1px solid #ababab;
    border-radius: 7px;
    font-weight: bold;
    height: 20px;
    line-height: 20px;
    padding: 2px 7px;
    display: inline-block;
}
.i-back {
    background-position: -376px -76px;
    height: 17px;
    vertical-align: middle;
    width: 14px;
}
</style>
</head>

<body>
<div class="wrap">
    
    <!--content Start-->
    <div class="content clearfix" >
    	<!--search-box Start-->
    	<div class="search-box">
            <div class="breadCutNav">
                <b class="icon i_home"></b>
              	  当前位置：约稿通知
                <span> &gt;</span>
              	  约稿通知详情
           	  	<a class="btn-gray" href="javascript:history.go(-1);" style="height;margin-left:650px;">
					<b class="icon i-back"></b>
					返　回
				</a>
				
            </div>
        </div>
    	<!--search-box End-->
        <!--sub Start-->
        <!--sub End-->
        <!--main Start-->
        <div class="main">
        	<div class="column" >
                <!--<div class="title"><h2>每日信息</h2></div>-->
                <div class="con newsDetail">
                    <h2>${subsVoList.subsTitle}</h2>
                    <p class="dotLine_2px"></p>
                    <ul class="tip clearfix">
                        <li class="al_l">
                        	发布日期：
                        	<fmt:parseDate value="${subsVoList.subsCreateDateStr}" var="date"/>
							<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>
                        </li>
                        <li class="al_c">发布：
                        	<c:forEach items="${userList }" var="user">
	                        	<c:if test="${subsVoList.subssendUserId == user.userId }">${user.userName}</c:if>
                        	</c:forEach>
                        </li>
                    </ul>
                    <div class="newsDetail-con">
                    	${subsVoList.subsContent}
					</div>
    			</div>
            </div>
        </div>
        <!--main End-->
    </div>
    
</div>
<script type="text/javascript"  src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"  src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript">

</script>
</body>
</html>