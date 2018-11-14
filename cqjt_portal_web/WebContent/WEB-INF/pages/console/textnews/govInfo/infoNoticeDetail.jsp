<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><%@page 
	import="com.gdssoft.core.tools.SystemContext"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--信息工作通知详情页</title>

<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/css/news.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>


<script type="text/javascript"  src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"  src="${StaticResourcePath}/js/main.js"></script>

<style type="text/css">
	.column .con li a { max-width:220px; }
	.newsDetail{font: 16px/30px 宋体}
	.main{width:1000px;margin:0 0 0 8px;text-align:;}
	 body{background-image: none;}	
	.wrap{width:1000px;margin:0 auto; padding-top: 0px}
	.column .newsDetail{background-color:white;} 
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
	.i-back{
	    width: 16px;
	    height: 19px;
	    margin-right: 5px;
	    vertical-align: middle;
	    background-position: -376px -76px;
	}
	.icon{
	    background-image: url(../images/bg_sysSprite.png);
	    background-repeat: no-repeat;
	}
}
</style>
</head>

<body>
<div class="wrap">
    <!--content Start-->
    <div class="content clearfix" >
    	<div class="search-box">
            <div class="breadCutNav">
                <a>当前位置：</a>
                <a>信息报送</a>
                <span> &gt;</span>
                <a>信息工作通知</a>
                <span> &gt;</span>
                <a>通知详情</a>
                <a class="btn-gray" href="javascript:history.go(-1);" style="height;margin-left:630px;">
                	<b class="icon i-back"></b>
					返　回
				</a>
            </div>
        </div>
        <!--main Start-->
        <div class="main">
        	<div class="column" >
                <div class="con newsDetail">
                    <h2>${workTitle}</h2>
                    <p class="dotLine_2px"></p>
                    <ul class="tip clearfix">
                        <li class="al_l">
                        	发布日期：
                        	<fmt:parseDate value="${workCreateDate}" var="date"/>
							<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>
                        </li>
                        <li class="al_c">发布：${workCreateUserName}</li>
                    </ul>
                    <div class="newsDetail-con">
                    	${workContent}
					</div>
    			</div>
            </div>
        </div>
        <!--main End-->
    </div>
</div>
</body>
</html>