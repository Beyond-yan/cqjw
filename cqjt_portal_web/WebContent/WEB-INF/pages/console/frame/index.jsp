<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@page 
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
<html>
<head>
<title>重庆交委门户后台管理系统</title>
<meta charset="utf-8">
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css" />
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${StaticResourcePath}/plugin/zTree/zTreeStyle/zTreeStyle.css"
	rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
body {overflow: hidden;}
.treeMenu .con { padding:0 7px; }
#footer {height: 50px;}
.copyright {padding-top:0;}
.ztree {padding: 0;}
.ztree li span.switch {margin-top:5px;margin-bottom:5px;}
.ztree li span.button.roots_docu {background-position: -56px -18px;}
.ztree li a { padding: 5px 3px 5px 0; }
.ztree li a.curSelectedNode {background-color: #FFE6B0;border: 1px solid #FFB951;padding: 4px 3px 4px 0}
.ztree li a span.button { margin-left:2px; margin-right:6px; 
	background-image: url("${StaticResourcePath}/images/bg_sysSprite.png");
}

.ztree li span.button.contributors_ico_docu,
.ztree li span.button.contributors_ico_open,
.ztree li span.button.contributors_ico_close
{width:16px;height:15px;background-position: 0px -145px; }
.ztree li span.button.submission_ico_docu,
.ztree li span.button.submission_ico_open,
.ztree li span.button.submission_ico_close
{width:18px;height:18px;background-position:-33px -145px;}
.ztree li span.button.govInfo_ico_docu,
.ztree li span.button.govInfo_ico_open,
.ztree li span.button.govInfo_ico_close,
.ztree li span.button.govInfo-manage_ico_docu,
.ztree li span.button.govInfo-manage_ico_open,
.ztree li span.button.govInfo-manage_ico_close
{width:15px;height:13px;background-position:-76px -145px;}
.ztree li span.button.govInfo-editing_ico_docu,
.ztree li span.button.govInfo-editing_ico_open,
.ztree li span.button.govInfo-editing_ico_close
{width:15px;height:15px;background-position:-76px -169px;}
.ztree li span.button.govInfo-recycled_ico_docu,
.ztree li span.button.govInfo-recycled_ico_open,
.ztree li span.button.govInfo-recycled_ico_close
{width:13px;height:15px;background-position:-76px -213px;}
.ztree li span.button.webInfo_ico_docu,
.ztree li span.button.webInfo_ico_open,
.ztree li span.button.webInfo_ico_close
{width:16px;height:16px;background-position:-114px -145px;}
.ztree li span.button.webInfo-sorting_ico_docu,
.ztree li span.button.webInfo-sorting_ico_open,
.ztree li span.button.webInfo-sorting_ico_close
{width:15px;height:13px;background-position:-114px -169px;}
.ztree li span.button.webInfo-edit_ico_docu,
.ztree li span.button.webInfo-edit_ico_open,
.ztree li span.button.webInfo-edit_ico_close
{width:13px;height:14px;background-position:-114px -193px;}
.ztree li span.button.webInfo-search_ico_docu,
.ztree li span.button.webInfo-search_ico_open,
.ztree li span.button.webInfo-search_ico_close
{width:16px;height:16px;background-position:-114px -213px;}
.ztree li span.button.webInfo-archive_ico_docu,
.ztree li span.button.webInfo-archive_ico_open,
.ztree li span.button.webInfo-archive_ico_close
{width:14px;height:14px;background-position:-114px -235px;}
.ztree li span.button.webInfo-recycled_ico_docu,
.ztree li span.button.webInfo-recycled_ico_open,
.ztree li span.button.webInfo-recycled_ico_close
{width:12px;height:15px;background-position:-114px -258px;}
</style>
</head>
<body >
<div class="wrap">
<!--Header Start-->
    <div class="header">
        <a href="../web/index.xhtml?face=home" class="logo"></a>
        <ul class="nav">
            <li><a href="../web/index.xhtml?face=home">首页</a></li>
            <c:forEach items="${topMenu}" var="p">
			<li id="topMenu_${p.id}"><a href="${p.url}"><c:out value="${p.name}" /></a></li>
			</c:forEach>
        </ul>
        <div class="tip">
        <a href="javascript:;"><b class="icon i-user"></b>${userName} (${userNo})</a>
        <% if (SystemContext.getEnableSSO()) {%>
        <a href="../j_spring_cas_security_logout"><b class="icon i-exit"></b>退出</a>
        <% } else { %>
        <a href="../j_spring_security_logout"><b class="icon i-exit"></b>退出</a>
        <% } %>
        </div>
    </div>
    <!--Header End-->
    <!--content Start-->
    <div class="content clearfix">
    	<c:choose>
    	<c:when test="${not empty thisMenu}">
        <!--treeMenu Start-->
        <div  id="leftMenu" class="treeMenu" style="overflow:auto;" >
            <h2 class="title"><b class="icon i-treeMenu"></b>${thisMenu.name}</h2>
            <div class="con">
            	<ul id="treeMenu" class="ztree"></ul>
            </div>
        </div>
        <!--treeMenu End-->
        <!--main Start-->
        <div class="main">
            <!--column01 Start-->
            <div>
            	<iframe name="mainFrame" id="mainFrame" frameborder="0" marginheight="0" 
            		marginwidth="0" frameborder="0" scrolling="auto" style="width:100%">
            	</iframe>
            </div>
            <!--column01 End-->
        </div>
        <!--main End-->
	    <script type="text/javascript">
			$(function() { 
				
				var setting = {
					async : {
						enable : true,
						url : "index.xhtml?face=childMenus&parentId=${thisMenu.id}",
						type : "post",
						dataType : "text"
					},
					data : {
						simpleData : {
							enable : true,
							idKey : "id",
							idPKey : "pId"
						}
					}
				};
				$.fn.zTree.init($("#treeMenu"), setting); 
				var high = document.body.clientHeight;
				$("#leftMenu").height(0.6*high);
		}); 
		</script>
	    </c:when>
	    <c:otherwise>
	    	<div style="text-align:center">^_^</div>
		</c:otherwise>
		</c:choose>
    </div>
    <!--content End-->
    <!--footer Start-->
    <div id="footer">
        <p class="copyright">Copyright © 2014-2016     重庆市交通委员会</p>
    </div>
    <!--footer End-->
</div>

<script type="text/javascript" src="${StaticResourcePath}/plugin/zTree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>
<script language="javascript" type="text/javascript"> 
$(function(){
	resize();
});
$(window).resize(function() {
	resize();
});
function resize() {
	$(".wrap").height($("body").height());
	$("#mainFrame").height($(".wrap").height()-$(".header").height()-$("#footer").height()-20);
}
</script> 
</body>
</html>
