<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.gdssoft.core.tools.SystemContext"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--首页</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/index.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.main .infoList a { width: 398px; max-width: 398px; }
#sign .infoList a { max-width: 420px; width: 420px; }
#ranking td { text-align: left; }
.box{height:70px;line-height:23px; white-space:nowrap;overflow:hidden;height:70px;} 
.box ul{margin:0; padding:0} 
.box li{font-size:12px; text-align:left;}
.notice{height:30px;line-height:30px; white-space:nowrap;overflow:hidden;} 
.left {float:left;width:20px;margin-top:8px;}
.login a{color: #0278e2;} 
.login a:hover{color:#c93609;}
.left {float:left;width:20px;margin-top:8px;height:40px;}
.i-rArr{margin:0 4px 0 5px;}

.videoTitle{
    max-width: 60px;
	overflow-x: hidden;
    overflow-y: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    word-break: keep-all
    }
</style>
</head>
<body>
<div class="wrap">
	<%@include file="../header.jspf"%>
<form action="http://10.224.5.177:8080/oa/login.jsp" method="post" id=oatest target="_blank">
<!-- <form action="http://10.224.5.177:8080/oa/login.jsp" method="post" id=oatest target="_blank"> -->
<input type=hidden id="innerNetWork201412031834" name="poRtal" value="T24GFAWa">
<input type=hidden id=username name=username value="<%=SystemContext.getUserNO()%>">
</form>
    <!--content Start-->
    <div class="content clearfix" >
    	<!--search-box Start-->
    	<div class="search-box">
        	<!-- Before logging-->
          <div class="notice"  >
            <div class="left"> 
              <b class="i-notice"></b><span style="font-size:14px;">公告：</span></div>
              <div id="scrollobj" style="white-space:nowrap;overflow:hidden;margin-top:8px;margin-left:60px;font-size:14px;"><span>             
               <c:forEach items="${noticeNews}" var="noticeNews" varStatus="n">  <c:if test="${n.index<3}">      
               <a href="../guest/news.xhtml?face=pubNoticenewsDetail&newsId=${noticeNews.newsId}" target="_blank"><b class="i-rArr"></b>
                ${noticeNews.newsTitle}<img border="0" src="${StaticResourcePath}/images/new.gif"/>&nbsp;&nbsp;&nbsp;&nbsp;</a>    
                </c:if></c:forEach>
                </span>     
               </div>            
            </div>
            <!-- Before logging-->
            <%@include file="search.jspf"%>
        </div>
    	<!--search-box End-->
        <!--sub Start-->
        <div class="sub l">
        
        	<!--login Start-->
        	<div class="column" id="userLogin">
            	<div class="title">
                	<h2>用户登录</h2>
                	<% if (SystemContext.getEnableSSO()) {%>
                	<a href="${pageContext.request.contextPath}/j_spring_cas_security_logout" class="exit"><b class="i-exit"></b>退出</a>
                	<% } else { %>
                	<a href="${pageContext.request.contextPath}/j_spring_security_logout" class="exit"><b class="i-exit"></b>退出</a>
                	<% } %>
                </div>
                <!-- 登录后  login Start-->
                 <div class="con login">
                	<dl>
						<dt>欢迎您，<b class="i-user"></b><a href="javascript:;" class="username">${userName}</a></dt>
                        <dd><b class="i-arr"></b>未读邮件：<b class="i-mail"></b><a href="${mailServerUrl}?${mailSession}" target="_blank"><b class="num">（${newMailCnt}）</b></a></dd>
                        <dd><b class="i-arr"></b><b></b>待办事项：<a href="${oaServerUrl}/login.jsp?viewId=MyTaskView&poRtal=gdspassword&username=<%=SystemContext.getUserNO()%>" target="_blank">共<b class="num">（${todoListCount}）</b>项</a></dd>
                        <dd><b class="i-arr"></b><b></b>最新公文：<a href="${oaServerUrl}" target="_blank">共<b class="num" id="c_archives">（${totleCount }）</b>个</a></dd>
                    </dl><a href="javascript:;" onclick="updatePassword();" style="margin-left: 168px;">修改密码</a>
                </div>
                <!--unLogin end-->
            </div>
        	<!--login End-->
            <!--sysEnter Start-->
            <%@include file="sysLink.jspf"%>
            <!--sysEnter End-->

        </div>
        <!--sub End-->
        <!--main-Top Start-->
        <div class="w742 r clearfix">
        <!--main Start-->
        <div class="main">
			<!--登陆后 Start-->
            <div class="column" id="sign">
                <div class="title">
                    <ul class="tab">              
                        <li class="active">我的邮件</li>  
                        <li>待办事项</li>
                        <li id="UnReceiveArchivesTab">公文签收</li>
                        <li id="lcxzsp">行政审批</li>
                     </ul>
                </div>
                <div class="con clearfix">
                    <ul class="tabCon infoList clearfix" id="mail">
                    	<c:forEach items="${newMailList}" var="mail">
                    	<li>
                            <a href="javascript:;" onclick="mailJump('${mail.mid}');return false;" title="${mail.subject}"><b class="i-list"></b>${mail.subject}</a>
                            <span class="time">(<fmt:formatDate value="${mail.date}" pattern="MM-dd"/>)</span>
                        </li>
                    	</c:forEach>
                    	<c:if test="${empty newMailList}">
                    	<li style="text-align:center;padding-top:80px;">
                    		暂无未读邮件
                    	</li>
                    	</c:if>
                    </ul>
                    <ul class="tabCon infoList clearfix" style="display:none;">
                    	<c:forEach items="${todoList}" var="todo">
                    	<li>
                            <a href="${oaServerUrl}/login.jsp?viewId=MyTaskView&poRtal=gdspassword&username=<%=SystemContext.getUserNO()%>" target="_blank" title="${todo.title}"><b class="i-list"></b>${todo.title}</a>
<%--                             <a href="${oaServerUrl}/index.jsp?viewId=MyTaskView" target="_blank" title="${todo.title}"><b class="i-list"></b>${todo.title}</a> --%>
                            <span class="time">(<fmt:formatDate value="${todo.date}" pattern="MM-dd"/>)</span>
                        </li>
                    	</c:forEach>
                    	<c:if test="${empty todoList}">
                    	<li style="text-align:center;padding-top:80px;">
                    		暂无待办事项
                    	</li>
                    	</c:if>
                    </ul>
                    <ul id="ul_archives" class="tabCon infoList clearfix" style="display:none;">
                        <li style="text-align:center;padding-top:80px;">
                    		<img src="${StaticResourcePath}/images/loading.gif" />
                    	</li>
                    </ul>  
                     <!--<ul id="ul_lcxzsp" class="tabCon infoList clearfix" style="display:none;">
                        <li style="text-align:center;padding-top:80px;">
                    		<img src="${StaticResourcePath}/images/loading.gif" />
                    	</li>
                    </ul> -->
                </div>
            </div>
            <!--登陆后 End-->
            <!--traficDoc Start-->
			<!--column-main-02 Start
            <div class="column">
                <div class="title"><h2>公开公文</h2><a href="../guest/news.xhtml?face=publicArchsList" class="more" target="_blank">更多></a></div>
                <div class="con">
					<ul id="ul_public_archives" style="height:130px;" class="infoList clearfix">
                        <li style="text-align:center;padding-top:50px;">
                    		<img src="${StaticResourcePath}/images/loading.gif" />
                    	</li>
                    </ul>
                </div>
            </div>-->
            <div class="column" id="sign2">
                <div class="title">
                    <ul class="tab">              
                        <li class="active">公开公文</li>  
                        <li>公告公示</li>
                     </ul>
                </div>
                <div class="con clearfix" style="height:130px;">
                    <ul class="tabCon infoList clearfix" id="ul_public_archives">
                    	<li style="text-align:center;padding-top:50px;">
                    		<img src="${StaticResourcePath}/images/loading.gif" />
                    	</li>
                    </ul>
                    <ul class="tabCon infoList clearfix" style="display:none;">
                    	<c:forEach items="${noticeNews}" var="n" varStatus="status">
	                    	<li>
	                            <a href="../guest/news.xhtml?face=pubNoticenewsDetail&newsId=${n.newsId}" target="_blank" title="${n.newsTitle}"><b class="i-list"></b>${n.newsTitle}</a>
	                            <span class="time" style="float:right;">(<fmt:formatDate value="${n.entryDate}" pattern="MM-dd"/>)</span>
	                        </li>
	                    </c:forEach>
	                    <li style="float:right;">
	                    	<a href="../guest/news.xhtml?face=publicView" target="_blank"><span style="color:#C93609;font-weight:bold;font-size:14px;font-family: 微软雅黑;float:right;padding-bottom:5px">更多></span>	</a>
	                    </li>
                    </ul>
                </div>
            </div>
          <!--column-main-02 End-->
           <div class="column" id="sign1">
                <div class="title">
                    <ul class="tab">              
                        <li class="active">处室动态</li>  
                        <li>行业动态</li>
						<li>交通运行动态</li>
                        <li>区县动态</li>
                     </ul>
                </div>
                <div class="con clearfix">
                    <ul class="tabCon infoList clearfix" id="mail">
                        <c:forEach items="${chushiNews}" var="n" varStatus="status">
	                    	<li>
	                            <a href="../guest/news.xhtml?face=dynamicNewsDetail&newsId=${n.newsId}&flagType=cs"><b class="i-list"></b>${n.newsTitle}</a>
	                            <c:if test='${n.isPhotosShow eq "1"}'><b class="i-pic"></b></c:if>
	                            <span class="time" style="float:right;">(<fmt:formatDate value="${n.entryDate}" pattern="MM-dd"/>)</span>
	                        </li>
	                    </c:forEach>
						<c:choose>
							<c:when	test="${empty chushiNews}">
								<li style="text-align:center;padding-top:40px;">
	                    			暂无处室动态
	                    	    </li>
							</c:when>
							<c:otherwise>
								<li style="float:right;">
			                    	<a href="../guest/news.xhtml?face=industryNewsList&flagType=cs"><span style="color:#C93609;font-weight:bold;font-size:14px;font-family: 微软雅黑;float:right;padding-bottom:5px">更多></span>	</a>
			                    </li>
							</c:otherwise>
						</c:choose>
                    </ul>
                    <ul class="tabCon infoList clearfix" style="display:none;">
                    	<c:forEach items="${industryNews}" var="n" varStatus="status">
	                    	<li>
	                            <a href="../guest/news.xhtml?face=dynamicNewsDetail&newsId=${n.newsId}&flagType=hy"><b class="i-list"></b>${n.newsTitle}</a>
	                            <c:if test='${n.isPhotosShow eq "1"}'><b class="i-pic"></b></c:if>
	                            <span class="time" style="float:right;">(<fmt:formatDate value="${n.entryDate}" pattern="MM-dd"/>)</span>
	                        </li>
	                    </c:forEach>
	                  	<c:choose>
							<c:when	test="${empty industryNews}">
								<li style="text-align:center;padding-top:40px;">
	                    			暂无行业动态
	                    	    </li>
							</c:when>
							<c:otherwise>
								<li style="float:right;">
			                    	<a href="../guest/news.xhtml?face=industryNewsList&flagType=hy"><span style="color:#C93609;font-weight:bold;font-size:14px;font-family: 微软雅黑;float:right;padding-bottom:5px">更多></span>	</a>
			                    </li>
							</c:otherwise>
						</c:choose>
                    </ul>
<ul class="tabCon infoList clearfix" style="display:none;">
                    	<c:forEach items="${runVoList}" var="n" varStatus="status">
	                    	<li>
	                            <a href="../guest/news.xhtml?face=runnVoDetails&runnId=${n.runnId}"><b class="i-list"></b>${n.runnTitle}</a>
	                            
	                            <span class="time" style="float:right;">(<fmt:formatDate value="${n.runnCreateDate}" pattern="MM-dd"/>)</span>
	                        </li>
	                    </c:forEach>
	                  	<c:choose>
							<c:when	test="${empty runVoList}">
								<li style="text-align:center;padding-top:40px;">
	                    			暂无交通运行动态
	                    	    </li>
							</c:when>
							<c:otherwise>
								<li style="float:right;">
			                    	<a href="../guest/news.xhtml?face=runnVoListView"><span style="color:#C93609;font-weight:bold;font-size:14px;float:right;font-family: 微软雅黑;padding-bottom:5px">更多></span>	</a>
			                    </li>
							</c:otherwise>
						</c:choose>
                    </ul>
                    <ul class="tabCon infoList clearfix" style="display:none;">
                      <c:forEach items="${quxianNews}" var="n" varStatus="status">
	                    	<li>
	                            <a href="../guest/news.xhtml?face=dynamicNewsDetail&newsId=${n.newsId}&flagType=qx"><b class="i-list"></b>${n.newsTitle}</a>
	                            <c:if test='${n.isPhotosShow eq "1"}'><b class="i-pic"></b></c:if>
	                            <span class="time" style="float:right;">(<fmt:formatDate value="${n.entryDate}" pattern="MM-dd"/>)</span>
	                        </li>
	                  </c:forEach>
	                 	<c:choose>
							<c:when	test="${empty quxianNews}">
								<li style="text-align:center;padding-top:40px;">
	                    			暂无区县动态
	                    	    </li>
							</c:when>
							<c:otherwise>
								<li style="float:right;">
			                    	<a href="../guest/news.xhtml?face=industryNewsList&flagType=qx"><span style="color:#C93609;font-weight:bold;font-size:14px;font-family: 微软雅黑;float:right;padding-bottom:5px">更多></span>	</a>
			                    </li>
							</c:otherwise>
						</c:choose>
                    </ul>  
                </div>
            </div>
          
          
            <!--column-main-03 Start
                <div class="column">
                    <div class="title"><h2>行业动态</h2><a href="../guest/news.xhtml?face=industryNewsList" class="more">更多></a></div>
                    <div class="con">
                        <ul style="height:130px;" class="infoList clearfix">
                        	<c:forEach items="${industryNews}" var="n">
	                    	<li>
	                            <a href="../guest/news.xhtml?face=newsDetail&newsId=${n.newsId}"><b class="i-list"></b>${n.newsTitle}</a>
	                            <c:if test='${n.isPhotosShow eq "1"}'><b class="i-pic"></b></c:if>
	                            <span class="time" style="float:right;">(<fmt:formatDate value="${n.entryDate}" pattern="MM-dd"/>)</span>
	                        </li>
	                    	</c:forEach>
                        </ul>
                    </div>
                </div>-->
              <!--column-main-03 End-->


        </div>
        <!--main End-->
        <!--sub-Right Start-->
        <div class="sub subR">
        	<!--ranking Start-->
            <div class="column downLoad" id="ranking">
				<div class="title">
					<ul class="tab" id="checkTabRanking">
						<li class="active" style="padding: 0px 7px 0px 7px">考核(${month }月)</li>
						<li style="padding: 0px 7px 0px 7px">考核(1-${month }月)</li>
					</ul>
					<a href="javascript:checkTabRanking('11');" class="more" style="right: 10px">更多></a>
				</div>
				<div>
					<ul class="tabCon infoList">
		                	<table>
		                    	<thead>
		                          <tr>
		                            <th width="44%">委机关</th>
		                            <td width="28%">政务累计</td>
		                            <td width="28%">网站累计</td>
		                          </tr>
		                        </thead>
			                  </table>
			                
			                <div class="box" id="marqueebox0">
		                      <table>
								<ul>
							  		<c:forEach items="${govList}" var="tn" varStatus="status">
							            <tr>
										<li>
										<td style="text-align:left;width:95px;">${tn.deptName}</td>
										<td style="text-align:center;">${tn.govTotalScore}分</td>
										<td style="text-align:center;" >${tn.siteTotalScore}分</td>
										</li>
										</tr>
							        </c:forEach>
							      </ul>
							   </table>
		                     </div> 
		                     
							<table>
		                    	<thead>
		                          <tr>
		                            <th width="44%">行业/区县单位</th>
		                            <td width="28%">政务累计</td>
		                            <td width="28%">网站累计</td>
		                          </tr>
		                        </thead>
		                      </table>
		                     
		                   <div class="box" id="marqueeb0">
		                      <table>
							       <ul>
							       		<c:forEach items="${govList1}" var="tn" varStatus="status">
							            <tr>
							            <li>
										<td style="text-align:left;width:95px;">${tn.deptName}</td>
										<td style="text-align:center;">${tn.govTotalScore}分</td>
										<td style="text-align:center;" >${tn.siteTotalScore}分</td>
										</li>
										</tr>
							            </c:forEach>
							         </ul>
							   	</table>
		                     </div> 
                    </ul>
					<ul class="tabCon infoList clearfix" style = "display:none;">
		                	<table>
		                    	<thead>
		                          <tr>
		                            <th width="44%">委机关</th>
		                            <td width="28%">政务累计</td>
		                            <td width="28%">网站累计</td>
		                          </tr>
		                        </thead>
			                  </table>
			                
			                <div class="box" id="marqueebox1">
		                      <table>
								<ul>
							  		<c:forEach items="${govTotalList}" var="tn" varStatus="status">
							            <tr>
										<li>
										<td style="text-align:left;width:95px;">${tn.deptName}</td>
										<td style="text-align:center;">${tn.govTotalScore}分</td>
										<td style="text-align:center;" >${tn.siteTotalScore}分</td>
										</li>
										</tr>
							        </c:forEach>
							      </ul>
							   </table>
		                     </div> 
		                     
							<table>
		                    	<thead>
		                          <tr>
		                            <th width="44%">行业/区县单位</th>
		                            <td width="28%">政务累计</td>
		                            <td width="28%">网站累计</td>
		                          </tr>
		                        </thead>
		                      </table>
		                     
		                   <div class="box" id="marqueeb1">
		                      <table>
							       <ul>
							       		<c:forEach items="${govTotalList1}" var="tn" varStatus="status">
							            <tr>
							        	<li>
										<td style="text-align:left;width:95px;">${tn.deptName}</td>
										<td style="text-align:center;">${tn.govTotalScore}分</td>
										<td style="text-align:center;" >${tn.siteTotalScore}分</td>
										</li>
										</tr>
							            </c:forEach>
							         </ul>
							   	</table>
		                     </div> 
					</ul>
				</div>
			</div>
            <!--ranking End-->
			<!--dailyInfo Start
            <div class="column" id="dailyInfo" >
                <div class="title"><h2>每日信息</h2><a href="../guest/news.xhtml?face=dailyInfoList" class="more">更多></a></div>
                <div class="con">
                    <ul style="height:130px;">
                    	<c:forEach items="${textPublications}" var="pub">
                    	<li>
                            <a href="../guest/news.xhtml?face=dailyInfoDetail&pubId=${pub.pubId}"><b class="i-dot"></b>${pub.pubName}</a>
                        </li>
                    	</c:forEach>
                    </ul>
                </div>
            </div>-->
			<div class="column downLoad" id="sign3">
				<div class="title">
					<ul class="tab" id="checkTab">
						<li class="active">每日信息</li>
						<li>专报信息</li>
					</ul>
					<a href="javascript:checkTab();" class="more">更多></a>
				</div>
				<div class="con clearfix" style="height: 130px;">
					<ul style="height:130px;" class="tabCon infoList clearfix">
                    	<c:forEach items="${textPublications}" var="pub">
	                    	<li>
	                            <a href="../guest/news.xhtml?face=dailyInfoDetail&pubId=${pub.pubId}"><b class="i-dot"></b>${pub.pubName}</a>
	                        </li>
                    	</c:forEach>
                    </ul>
					<ul class="tabCon infoList clearfix" style = "display:none;">
						<c:forEach items="${specialVoList}" var="special">
							<li>
								<a href="../guest/specialInformation.xhtml?face=specialVoDetails&specialId=${special.specialId}">
									<b class="i-dot"></b>${special.specialTitle}
								</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
          	<!--dailyInfo End-->
            <!--downLoad Start-->
        	<div class="column downLoad">
            	<div class="title"><h2>交通课堂</h2><a href="../guest/news.xhtml?face=fileView" class="more">更多></a></div>
                <div class="con">
                	<ul style="height:130px;">
                		<c:forEach items="${fownloadFiles}" var="file" varStatus="status">
                    	<li>
                            <a href="javascript:;" onclick="downLoad(${status.index});return false;" target="_blank"><b class="i-dot"></b>${file.fileName}</a>
                        	<input type="hidden"  id="fileName${status.index}"  value="${file.attachment}"/>
                        	<input type="hidden"  id="filePath${status.index}"  value="${file.filePath}"/>
                        </li>
                    	</c:forEach>
                    </ul>
                </div>
            </div>
        	<!--downLoad End-->
        </div>
        <!--sub-Right End-->
        <div class="clear"></div>
        <!--video Start-->
          <div class="column"  style="height:167px">
              <div class="title"><h2>交通视频</h2><a href="../guest/news.xhtml?face=videoView" class="more">更多></a></div>
              <div class="con" id="video">
                 <ul class="clearfix">
                     <c:forEach items="${videoNewsList}" var="video">
                        <li>
                            <a href="../guest/news.xhtml?face=videoNewsDetail&videoId=${video.videoId}" class="pic">
                                <img src="${pageContext.request.contextPath}/${video.photoUrl}">
                                <!-- <span >02:39</span> -->
                            </a>
                            <a href="../guest/news.xhtml?face=videoNewsDetail&videoId=${video.videoId}" title="${video.videoTitle}" class="videoTitle intro">${fn:substring(video.videoTitle, 0, 10)}</a>
                        </li>
                     </c:forEach>   
                  </ul>   
              </div>
          </div>
          <!--video End-->
        </div>
        <!--main-Top End-->
    </div>
    <!--content End-->
    <%@include file="../footer.jspf"%>
</div>
<script type="text/javascript"  src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"  src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
function oatest(){
	document.getElementById("oatest").submit();
}
$(function(){
	$.ajax({
		url : "../guest/index.xhtml?action=getPublicArchives",
		cache : false,
		dataType : "text",
		success : function(data) {
			var json = $.parseJSON(data);
			var count=0;
			$("#ul_public_archives").html("");
			$.each(json, function(i, n) {
				if (count<4) {
					var li = $("<li>"
							+ "<a href=\"../guest/news.xhtml?face=getPublicArchivesDetail&archivesId="+n.archivesid+"&schema="+n.schema_code+"\" target=\"_blank\" title='"+n.subject+"'>"
							+ "<b class=\"i-list\"></b>" + n.subject
							+ "</a>"
							/* + "<span class=\"time\">(02-26)</span>" */
							+ "</li>");
					$("#ul_public_archives").append(li);
				}
				count++;
			});
			var more = '<li style="float:right;">'+
					   '<a href="../guest/news.xhtml?face=publicArchsList" target="_blank"><span style="color:#C93609;font-weight:bold;font-size:14px;font-family: 微软雅黑;float:right;padding-bottom:5px">更多></span>	</a>'+
					   '</li>';
			$("#ul_public_archives").append(more);
		}
	});
});

$("#UnReceiveArchivesTab").click(function(){
	if ($("#ul_archives li").size()<2) {
		$.ajax({
			url : "index.xhtml?action=getUnReceiveArchives",
			cache : false,
			dataType : "text",
			success : function(data) {
				var json = $.parseJSON(data);
				if (json.Status=="0") {
					var count=0;
					$("#ul_archives").html("");
					$.each(json.Data, function(i, n) {
						if (count<7) {
							var li = $("<li>"
									+ "<a href=\"${oaServerUrl}/index.jsp?viewId=ArchivesDepReceiveView\" target=\"_blank\" title='"+n.subject+"'>"
									+ "<b class=\"i-list\"></b>" + n.subject
									+ "</a>"
									+ "</li>");
							$("#ul_archives").append(li);
						}
						count++;
					});
				} else {
					$("#ul_archives li").text(json.Error_Msg);
				}
			},
			error : function(){
				$("#ul_archives li").text("没有公文或系统未启用公文整合功能");
			}
		});
	}
});

$(function(){
		var ssoUrl="${lcxzspSsoUrl}?username=${username_ws}&sybmol=${sybmol}&target=${target}&token=${token}";
		var busUrl="${pageContext.request.contextPath}/sso/api.xhtml?method=getUserBusinessInfo&oaLoginId=${userNo}";
		console.error(ssoUrl);
		console.error(busUrl);
});

$("#lcxzsp").click(function(){
	if ($("#ul_lcxzsp li").size()<2) {
		var ssoUrl="${lcxzspSsoUrl}?username=${username_ws}&sybmol=${sybmol}&target=${target}&token=${token}";
		var busUrl="${pageContext.request.contextPath}/sso/api.xhtml?method=getUserBusinessInfo&oaLoginId=${userNo}";
		$.ajax({
			url : busUrl,
			cache : false,
			dataType : "JSON",
			success : function(data) {
				var json = $.parseJSON(data);
				if ("200" == json.code) {
					var count=0;
					$("#ul_lcxzsp").html("");
					$.each(json.userbusinfo, function(i, n) {
						if (count<7) {
							var li = $("<li>"
									+ "<a href=\""+ssoUrl+"\" target=\"_blank\" title='"+n.APPLY_SUBJECT+"'>"
									+ "<b class=\"i-list\"></b>" + n.APPLY_SUBJECT
									+ "</a>"
									+ "</li>");
							$("#ul_lcxzsp").append(li);
						}
						count++;
					});
				} else {
					$("#ul_lcxzsp").html("<li style=\"text-align:center;padding-top:80px;\">暂无待办事项</li>");
				}
			},
			error : function(){
				$("#ul_lcxzsp").html("<li style=\"text-align:center;padding-top:80px;\">暂无待办事项</li>");
			}
		});
	}
});
function mailJump(mid){
	$.ajax({
		url : "index.xhtml?action=getEmailUrl",
		cache : false,
		type : "GET",
		dataType : "text",
		data : {
			"mid" :mid
		},
		success : function(data) {
			var json = $.parseJSON(data);
			$.each(json.Data, function(i, n) {
			/* 	window.open('http://10.224.5.179/coremail/XJS/index.jsp?'+n.pp+'&firstShowPage='+n.qq); */
				window.open('${mailServerUrl}?'+n.pp+'&firstShowPage='+n.qq);
			});
		}
	});
}
function downLoad(num){
	var fileName=encodeURI(encodeURI($("#fileName"+num).val()));
	var filePath=$("#filePath"+num).val();
	location.href="../guest/index.xhtml?face=downLoad&fileName="+fileName+"&filePath="+filePath;
}

function updatePassword(){
	asyncbox.open({
		title:"修改密码",
		id:"updatePasswordWindow",
		modal: true,
		width : 300,
		height : 220,
	    url : "../console/sysMgt.xhtml?face=updatePassword",
		btnsbar:$.btn.OKCANCEL,
	    callback : function(action){
	        if (action=="ok") {
	        	var contents = $("#updatePasswordWindow_content").contents();
	        	var oldPassWord = contents.find("#tbodyNews input[id='oldPassWord']").val();
	        	var newPassWord = contents.find("#tbodyNews input[id='newPassWord']").val();
	        	var confirmPassWord = contents.find("#tbodyNews input[id='confirmPassWord']").val();
	        	if(newPassWord!=confirmPassWord){
	        		asyncbox.alert("新密码与确认密码不同！", "信息窗口");
	        	}else{
		        	$.ajax({
	        			url : "../console/sysMgt.xhtml?action=savePassword",
	        			data : {
	        				"oldPassword" : oldPassWord,
	        				"newPassword" : newPassWord
	        			},
	        			async : false, /* 同步，保存完成才会继续下一个操作 */
	        			success : function(data) {
	        				var content = eval('(' + data + ')');
	        				if(content.msg=="success"){
	        					asyncbox.alert("修改成功！", "信息窗口");
	        				}else if(content.msg=="false"){
	        					asyncbox.alert("原始密码错误！", "信息窗口");
	        				}
	        			}
	        		});
	        	}
	        }
	    }
	});
}
function startmarquee(lh,speed,delay,index){ 
    var t; 
    var p=false; 
    var o;
    o=document.getElementById(index); 
    o.innerHTML+=o.innerHTML; 
    o.onmouseover=function(){p=true}; 
    o.onmouseout=function(){p=false} ;
    o.scrollTop = 0; 
    
    function start(){ 
        t=setInterval(scrolling,speed); 
        if(!p){ o.scrollTop += 1;} 
    } 
    function scrolling(){ 
        if(o.scrollTop%lh!=0){ 
        o.scrollTop += 1; 
        if(o.scrollTop>=o.scrollHeight/2) o.scrollTop = 0; 
        }else{ 
            clearInterval(t); 
            setTimeout(start,delay); 
        } 
    } 
    setTimeout(start,delay); 
} 
startmarquee(23,50,400,'marqueebox0'); 
startmarquee(23,50,400,'marqueeb0'); 
startmarquee(23,50,400,'marqueebox1'); 
startmarquee(23,50,400,'marqueeb1'); 

function scroll(obj) {
	var tmp = (obj.scrollLeft)++;
	
	if (obj.scrollLeft==tmp) obj.innerHTML += obj.innerHTML;
	
	if (obj.scrollLeft>=obj.firstChild.offsetWidth) obj.scrollLeft=0;
}
setInterval("scroll(document.getElementById('scrollobj'))",40);
</script>

</body>
</html>
