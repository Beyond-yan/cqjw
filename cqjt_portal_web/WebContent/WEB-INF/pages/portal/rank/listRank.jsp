<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><%@page 
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--考核信息</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/news.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<style title="text/css">
/*考评排行*/
.i-ranking{width:16px;height:20px;margin:0 10px; background-position:-30px -120px; vertical-align:middle;}
#ranking .con{padding:5px 8px;text-align:center;}    
#ranking table {
	width:42%;margin:0 3%; table-layout:fixed;
	border:none;border-collapse:collapse;
	line-height:30px;
	} 
#ranking thead td, #ranking thead th{border-bottom:1px dotted #424242;}  
#ranking td{text-align:center;} 
#ranking th{font-weight:100;text-align:left;}
#ranking thead th, #ranking thead td{font:bold 12px/36px \5b8b\4f53;} 
#ranking td, #ranking th{
	padding-left:6px;
	overflow:hidden;
	text-overflow:ellipsis;
	word-break:keep-all;
	white-space:nowrap;
	}
#ranking .otherMonth{color:#888;line-height:30px;}
#traficDoc .con li{height:26px;}
#traficDoc .con a{width:295px;float:left;}
#ranking td { text-align: left; }
.more {
    color: #d93925;
    font-family: 微软雅黑;
    font-weight: bold;
    position: absolute;
    right: 20px;
    top: 0;
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
                <a href="javascript:;">考核信息</a>
            </div>
            <%@include file="../index/search.jspf"%>
        </div>
    	<!--search-box End-->
        <!--main Start-->

        	<div class="column"  id="ranking">
                <div class="title">
	                <c:if test="${flag == '1'}">
	                	<h2>考核信息(${month}月)</h2>
	                </c:if>
	                <c:if test="${flag == '2'}">
	                	<h2>考核信息(1-${month}月)</h2>
	                </c:if>
	                	<a href="../guest/rank.xhtml?face=getRankHistory&type=1" class="more">更多></a>
                </div>
                 <c:if test="${flag == '1'}">
	                <div class="con clearfix" >
						  <table class="l" id="colee1">
	                    	<thead>
	                          <tr>
	                            <th width="28%">委机关</th>
	                            <!-- <td width="18%">网站考核</td> -->
	                            <td width="18%">网站累计</td>
	                            <!-- <td width="18%">政务考核</td> -->
	                            <td width="18%">政务累计</td>
	                          </tr>
	                        </thead>
	                        <tbody id="tbodyRank" >
	                        <c:forEach items="${govList}" var="tn" varStatus="status">
		                        <tr>
			                       <td style="text-align:left">${tn.deptName}</td>
			                       <!-- <td>${tn.siteScore}分</td>-->
			                       <td>${tn.siteTotalScore}分</td>
	                        	   <!-- <td>${tn.govScore}分</td>-->
	                        	   <td>${tn.govTotalScore}分</td>
	                      	 	</tr>
	                       	
	                        
	                        </c:forEach>
	                        </tbody>
	                    </table>
	                    <table class="r" id="colee2">
	                    	<thead>
	                          <tr>
	                            <th width="28%">行业/区县动态</th>
	                            <!--<td width="18%">网站考核</td>-->
	                            <td width="18%">网站累计</td>
	                            <!--<td width="18%">政务考核</td>-->
	                            <td width="18%">政务累计</td>
	                          </tr>
	                        </thead>
	                        <tbody>
	                           <c:forEach items="${govList1}" var="tn" varStatus="status">
	                        	<tr>
			                       <td style="text-align:left">${tn.deptName}</td>
			                       <!--<td>${tn.siteScore}分</td>-->
			                       <td>${tn.siteTotalScore}分</td>
	                        	   <!--<td>${tn.govScore}分</td>-->
	                        	   <td>${tn.govTotalScore}分</td>
	                      	 	</tr>
	                        </c:forEach>
	                        </tbody>
	                    </table>
						<div class="clear"></div>
			        </div>
		        </c:if>
		        <c:if test="${flag == '2'}">
	                <div class="con clearfix" >
						  <table class="l" id="colee1">
	                    	<thead>
	                          <tr>
	                            <th width="28%">委机关</th>
	                            <!--<td width="18%">网站考核</td>-->
	                            <td width="18%">网站累计</td>
	                            <!--<td width="18%">政务考核</td>-->
	                            <td width="18%">政务累计</td>
	                          </tr>
	                        </thead>
	                        <tbody id="tbodyRank" >
	                        <c:forEach items="${govTotalList}" var="tn" varStatus="status">
	                        
	                       		<tr>
			                       <td style="text-align:left">${tn.deptName}</td>
			                       <!--<td>${tn.siteScore}分</td>-->
			                       <td>${tn.siteTotalScore}分</td>
	                        	   <!--<td>${tn.govScore}分</td>-->
	                        	   <td>${tn.govTotalScore}分</td>
	                      	 </tr>
	                       	
	                        
	                        </c:forEach>
	                        </tbody>
	                    </table>
	                    <table class="r" id="colee2">
	                    	<thead>
	                          <tr>
	                            <th width="28%">行业/区县动态</th>
	                            <!--<td width="18%">网站考核</td>-->
	                            <td width="18%">网站累计</td>
	                            <!--<td width="18%">政务考核</td>-->
	                            <td width="18%">政务累计</td>
	                          </tr>
	                        </thead>
	                        <tbody>
	                           <c:forEach items="${govTotalList1}" var="tn" varStatus="status">
	                        <tr>
			                       <td style="text-align:left">${tn.deptName}</td>
			                       <!--<td>${tn.siteScore}分</td>-->
			                       <td>${tn.siteTotalScore}分</td>
	                        	   <!--<td>${tn.govScore}分</td>-->
	                        	   <td>${tn.govTotalScore}分</td>
	                      	 </tr>
	                        </c:forEach>
	                        </tbody>
	                    </table>
						<div class="clear"></div>
			        </div>
		        </c:if>
            </div>
        <!--main End-->
    </div>
    <!--content End-->
  
    <%@include file="../footer.jspf"%>
    </div>  
<script type="text/javascript"  src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"  src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript">

</script>
</body>
</html>
