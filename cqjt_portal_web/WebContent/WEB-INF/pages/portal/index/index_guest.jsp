<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<!doctype html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
  if (com.gdssoft.core.tools.SystemContext.getEnableSSO() 
			&& (null == request.getParameter("ticket") || request.getParameter("ticket")=="")) {
		response.sendRedirect(request.getContextPath() + "/web/index.xhtml?face=home");
	}
%>
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--首页</title>
<link href="${StaticResourcePath}/css/zzsc.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/index.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath}/plugin/layer2.4/layer.js"></script>
<style type="text/css">
.main .infoList a { width: 398px; max-width: 398px; }
#ranking td { text-align: left; }
.box{height: 110px;line-height:23px; white-space:nowrap;overflow:hidden;height:70px;} 
.box ul{margin:0; padding:0} 
.box li{font-size:12px; text-align:left;} 
.notice{height:30px;line-height:30px; white-space:nowrap;overflow:hidden;} 
.left {float:left;width:20px;margin-top:8px;height:40px;}
.i-rArr{margin:0 4px 0 5px;}
.demo-class .layui-layer-title{background:#c00; color:#fff; border: none;}
.focusBox .num li a {line-height: 20px;}
.focusBox .pic img{
    width: 282px;
}
#pic li a img {
	margin-left: -2px;
}
	
</style>
<script type="text/javascript"  src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
</head>
<body>

<div>
	<img id="floatImg" style="position:fixed;z-index:78;top:272px;left:180px;cursor:hand;" onclick="closeImgDiv();" src="${pageContext.request.contextPath}/images/delete2.png"></img>
	<div id="floatDiv" style="cursor:pointer; position:fixed;z-index:77;top:280px;left:33px;height:150px;width:150px;border:1px solid #0473b3;padding:2px;">
		<a href="http://app.cqjt.gov.cn/guest/news.xhtml?face=firstView&name=%E5%8A%A8%E6%80%81%E4%BF%A1%E6%81%AF.%E4%B8%BE%E6%8A%A5%E6%96%B9%E5%BC%8F.%E5%BA%94%E7%9F%A5%E5%BA%94%E4%BC%9A&index=0" target="_blank">
			<img src="${pageContext.request.contextPath}/images/boomblack.jpg" style="height:150px;width:150px;"></img>
		</a>
	</div>
</div>
<div style="display:none;">
	<img id="floatImg_1" style="position:fixed;z-index:78;top:272px;right:8px;cursor:hand;" onclick="closeImgDiv();" src="${pageContext.request.contextPath}/images/delete2.png"></img>
	<div id="floatDiv_1" style="cursor:pointer; position:fixed;z-index:77;top:280px;right:8px;height:120px;width:150px;border:1px solid #0473b3;padding:2px;">
		<a onclick="openImages();" target="_blank">
			<img src="${pageContext.request.contextPath}/images/newYear2018.jpg" style="height:120px;width:150px;"></img>
		</a>
	</div>
</div>

<div class="wrap">
	<%@include file="../header.jspf"%>
    <!--content Start-->
    <div class="content clearfix" >
    	<!--search-box Start-->
    	<div class="search-box">
        	<!-- Before logging-->
            <div class="notice"> 
             <div class="left"> 
              <b class="i-notice"></b><span style="font-size:14px;">公告：</span></div>
              <div id="scrollobj" style="white-space:nowrap;overflow:hidden;margin-top:8px;margin-left:60px;font-size:14px;"><span>             
               <c:forEach items="${noticeNews}" var="noticeNews" varStatus="n">  <c:if test="${n.index<3}">   
               <a href="news.xhtml?face=pubNoticenewsDetail&newsId=${noticeNews.newsId}" target="_blank"><b class="i-rArr"></b>
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
                </div>
                <!--unlogin Start-->
                <div class="con unlogin" id="unlogin">
                	<%@include file="login.jspf"%>
                </div>
                <!--unlogin end-->
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


        	<!--登录前 Start -->
            <div class="column" id="traffic" >
                <div class="title">
                    <h2>交通政务</h2><a href="../guest/news.xhtml?face=trafficView" class="more" target="_blank">更多></a>
                </div>
                
                <div class="con">
                	<div id="focus" class="clearfix">
                        <div id="focusBox" class="focusBox" style="float: right;" >
	                          <ul id="pic" class="pic" id="show_pic" >
	                            <c:forEach items="${trafficPhotoNews}" var="n">
	                            	<li >
	                            		<a  href="news.xhtml?face=newsDetail&newsId=${n.newsId}" target="_blank" title="${n.newsTitle}">
	                           				<img src="${pageContext.request.contextPath}/${n.photoUrl}" >
	                            		</a>
	                            	</li>
	                          	</c:forEach>
	                          </ul>
							<div class="txt-bg"></div>
							<div class="txt">
								<ul>
									<c:forEach items="${trafficPhotoNews}" var="n">
										<li>
											<a style="line-height: 18px;" target="_blank" href="news.xhtml?face=newsDetail&newsId=${n.newsId}">
												${n.newsTitle} 
											</a>
										</li>
									</c:forEach>
								</ul>
							</div>
							<ul class="num">
						        <li><a>1</a><span></span></li>
						        <li><a>2</a><span></span></li>
						        <li><a>3</a><span></span></li>
						        <li><a>4</a><span></span></li>
							</ul>
                        </div>
                        <ul class="focusList" style="float: left; width: 430px;" >
                        	<c:forEach items="${trafficNews}" var="n"  begin="0" end ="0">
	                       		 	<li>
		                          	 <a href="news.xhtml?face=newsDetail&newsId=${n.newsId}" title="${n.newsTitle}" target="_blank"><b>·${n.newsTitle}</b></a>
		                        	</li>
	                    	</c:forEach>
	                    	<c:forEach items="${trafficNews}" var="n" begin="1" end ="3">
	                       		 	<li>
		                          	 <a href="news.xhtml?face=newsDetail&newsId=${n.newsId}" title="${n.newsTitle}" target="_blank">·${n.newsTitle}</a>
		                        	</li>
	                    	</c:forEach>
	                    	<c:forEach items="${trafficNews}" var="n" begin="4" end ="4">
	                       		 	<li>
		                          	 <a href="news.xhtml?face=newsDetail&newsId=${n.newsId}" title="${n.newsTitle}" target="_blank"><b>·${n.newsTitle}</b></a>
		                        	</li>
	                    	</c:forEach>
	                    	<c:forEach items="${trafficNews}" var="n" begin="5" end ="6">
	                       		 	<li>
		                          	 <a href="news.xhtml?face=newsDetail&newsId=${n.newsId}" title="${n.newsTitle}" target="_blank">·${n.newsTitle}</a>
		                        	</li>
	                    	</c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
		<div class="main">
            <!--登录前 End-->
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
	                            <a href="news.xhtml?face=pubNoticenewsDetail&newsId=${n.newsId}" target="_blank" title="${n.newsTitle}"><b class="i-list"></b>${n.newsTitle}</a>
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
            <!--column-main-03 Start-->
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
			                    	<a href="../guest/news.xhtml?face=industryNewsList&flagType=hy"><span style="color:#C93609;font-weight:bold;font-size:14px;float:right;font-family: 微软雅黑;padding-bottom:5px">更多></span>	</a>
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
                    <ul id="ul_archives" class="tabCon infoList clearfix" style="display:none;">
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
            <!--column-main-03 End-->
            
         <div class="column"  style="width: 48%;float: left;display: none;">
             <div class="title"><h2>人事信息</h2><a href="javascript:alert('内容完善中...');" class="more">更多></a></div>
<!--                 <div class="con clearfix"> -->
<!--                     <ul class="tabCon infoList clearfix"> -->
<!--                     	<li> -->
<!--                             <a style="width:70%;" href="javascript:alert('内容完善中...');"><b class="i-list"></b>人事任免相关内容...</a> -->
<!--                             <span class="time" style="float:right;">(02-09)</span> -->
<!--                         </li> -->
<!--                     	<li> -->
<!--                             <a style="width:70%;" href="javascript:alert('内容完善中...');"><b class="i-list"></b>人事任免相关内容...</a> -->
<!--                             <span class="time" style="float:right;">(02-09)</span> -->
<!--                         </li> -->
<!--                     	<li> -->
<!--                             <a style="width:70%;" href="javascript:alert('内容完善中...');"><b class="i-list"></b>人事任免相关内容...</a> -->
<!--                             <span class="time" style="float:right;">(02-09)</span> -->
<!--                         </li> -->
<!--                     	<li> -->
<!--                             <a style="width:70%;" href="javascript:alert('内容完善中...');"><b class="i-list"></b>人事任免相关内容...</a> -->
<!--                             <span class="time" style="float:right;">(02-09)</span> -->
<!--                         </li> -->
<!--                     	<li> -->
<!--                             <a style="width:70%;" href="javascript:alert('内容完善中...');"><b class="i-list"></b>人事任免相关内容...</a> -->
<!--                             <span class="time" style="float:right;">(02-09)</span> -->
<!--                         </li> -->
<!--                     </ul> -->
<!-- 				</div> -->
         </div>
         <div class="column"  style="height:175px;width:48%;float:left;margin-left:13px;display: none;">
             <div class="title"><h2>员工展示</h2><a href="javascript:alert('内容完善中...');" class="more">更多></a></div>
<!-- 			<table style="margin-top: 15px;" > -->
<!-- 				<tr> -->
<!-- 					<td> -->
<!-- 						<div style="text-align: center;" href="javascript:alert('内容完善中...');"> -->
<%-- 							<img style="width: 70%;" src="${StaticResourcePath}/images/man.jpg" alt="员工照" /> --%>
<!-- 							</br><span style="font-weight: bold;" >姓名</span> -->
<!-- 					    </div> -->
<!-- 					</td> -->
<!-- 					<td> -->
<!-- 						<div style="text-align: center;" href="javascript:alert('内容完善中...');"> -->
<%-- 							<img style="width: 70%;" src="${StaticResourcePath}/images/man.jpg" alt="员工照" /> --%>
<!-- 							</br><span style="font-weight: bold;" >姓名</span> -->
<!-- 					    </div> -->
<!-- 					</td> -->
<!-- 					<td> -->
<!-- 						<div style="text-align: center;" href="javascript:alert('内容完善中...');"> -->
<%-- 							<img style="width: 70%;" src="${StaticResourcePath}/images/man.jpg" alt="员工照" /> --%>
<!-- 							</br><span style="font-weight: bold;" >姓名</span> -->
<!-- 					    </div> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
         </div>
        </div>
        <!--main End-->
        <!--sub-Right Start-->
        <div class="sub subR">
        	<!--ranking Start-->
            <div class="column downLoad" id="ranking" style="height: 175px;" >
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
<!-- 		                            <th width="44%">委机关</th> -->
									<th width="44%">部门/单位</th>
		                            <td width="28%">政务累计</td>
		                            <td width="28%">网站累计</td>
		                          </tr>
		                        </thead>
			                  </table>
			                
			                <div class="box" id="marqueebox0" style="height: 115px;" >
		                      <table>
								<ul>
							  		<c:forEach items="${govList }" var="tn" varStatus="status">
							            <tr>
										<li>
										<td style="text-align:left;width:95px;">${tn.deptName}</td>
										<td style="text-align:center;">${tn.govTotalScore}分</td>
										<td style="text-align:center;" >${tn.siteTotalScore}分</td>
										</li>
										</tr>
							        </c:forEach>
						       		<c:forEach items="${govList1 }" var="tn" varStatus="status">
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
		                   <!--   
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
							       		<c:forEach items="${govList1 }" var="tn" varStatus="status">
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
		                     </div>  -->
                    </ul>
					<ul class="tabCon infoList clearfix" style = "display:none;">
		                	<table>
		                    	<thead>
		                          <tr>
<!-- 		                            <th width="44%">委机关</th> -->
									<th width="44%">部门/单位</th>
		                            <td width="28%">政务累计</td>
		                            <td width="28%">网站累计</td>
		                          </tr>
		                        </thead>
			                  </table>
			                
			                <div class="box" id="marqueebox1" style="height: 115px;" >
		                      <table>
								<ul>
							  		<c:forEach items="${govTotalList }" var="tn" varStatus="status">
							            <tr>
										<li>
										<td style="text-align:left;width:95px;">${tn.deptName}</td>
										<td style="text-align:center;">${tn.govTotalScore}分</td>
										<td style="text-align:center;" >${tn.siteTotalScore}分</td>
										</li>
										</tr>
							        </c:forEach>
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
		                     <!-- 
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
		                     </div>  -->
					</ul>
				</div>
			</div>
            <!--ranking End-->
			<!--dailyInfo Start-->
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
            <!--downLoad Start
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
        	downLoad End-->
        	
         <div class="column" style="display: none;" >
             <div class="title"><h2>法律法规</h2><a href="javascript:alert('内容完善中...');" class="more">更多></a></div>
<!--                 <div class="con"> -->
<!--                 	<ul style="height:130px;"> -->
<!--                     	<li> -->
<!--                             <a href="javascript:alert('内容完善中...');" target="_blank"><b class="i-dot"></b>法律法规相关内容...</a> -->
<!--                         </li> -->
<!--                     	<li> -->
<!--                             <a href="javascript:alert('内容完善中...');" target="_blank"><b class="i-dot"></b>法律法规相关内容...</a> -->
<!--                         </li> -->
<!--                     	<li> -->
<!--                             <a href="javascript:alert('内容完善中...');" target="_blank"><b class="i-dot"></b>法律法规相关内容...</a> -->
<!--                         </li> -->
<!--                     	<li> -->
<!--                             <a href="javascript:alert('内容完善中...');" target="_blank"><b class="i-dot"></b>法律法规相关内容...</a> -->
<!--                         </li> -->
<!--                     	<li> -->
<!--                             <a href="javascript:alert('内容完善中...');" target="_blank"><b class="i-dot"></b>法律法规相关内容...</a> -->
<!--                         </li> -->
<!--                     </ul> -->
<!--                 </div> -->
         </div>
        	
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
                          <a href="news.xhtml?face=videoNewsDetail&videoId=${video.videoId}" class="pic">
                              <img src="${pageContext.request.contextPath}/${video.photoUrl}">
                              <!-- <span >02:39</span> -->
                          </a>
                          <a href="news.xhtml?face=videoNewsDetail&videoId=${video.videoId}" title="${video.videoTitle}" class="intro videoTitle">${fn:substring(video.videoTitle, 0, 10)}</a>
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
<script type="text/javascript"  src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript"  src="${StaticResourcePath}/js/jquery.SuperSlide.js"></script>
<script type="text/javascript"  src="${StaticResourcePath}/js/jquery.cookie.js"></script>
<script type="text/javascript">

function openImages(){
	layer.open({
	  type: 2,
	  title:'新年贺词',
	  skin : 'demo-class',
	  area: ['780px', '600px'],
	  fixed : true,
	  content: '../guest/index.xhtml?action=jumpNewYear'
	});
}
function closeImgDiv(){
    $("#floatImg").remove();
    $("#floatDiv").remove();
}
$("#floatImg").on('click',function(){
   closeImgDiv();
});
$(function() {	
	// 焦点图
	$(".focusBox").slide({
		titCell : ".num li",
		mainCell : ".pic",
		effect : "fold",
		autoPlay : true,
		trigger : "click",
		delayTime: 1000,
		interTime: 5000,
		startFun : function(i) {
			jQuery(".focusBox .txt li").eq(i).animate({
				"bottom" : -16
			}).siblings().animate({
				"bottom" : -36
			});
		}
	});
	
//         $("#pic").hover(function(){  
        	
			
// 			$(".focusBox .txt").css("margin-top","-16px");
// 			$(".focusBox .txt").css("height","36px");
// 			$(".focusBox .txt-bg").css("height","36px");
			
//         },function(){  
        	
        	
// 			$(".focusBox .txt").css("margin-top","0px");
// 			$(".focusBox .txt").css("height","20px");
// 			$(".focusBox .txt-bg").css("height","20px");
//         })  

	
	$.ajax({
		url : "index.xhtml?action=getPublicArchives",
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
	/* 温馨提示  
	if (get_cookie("popped")==""){ //判断是否已经弹出过窗口 
		 tip(); // 如果没有则弹出窗口 
	     document.cookie="popped=yes" ; // 设置cookie值 
	} */
});
function tip(){
	asyncbox.open({
		title:"",
		id:"updatePasswordWindow",
		modal: true,
	    width : 500,
	    height : 350,
	    url : "../guest/index.xhtml?face=tipWindow",
		btnsbar:$.btn.OK,
	    callback : function(action){
	    }
	});
}

function get_cookie(Name){ 
   var search = Name + "=" 
   var returnvalue = ""; 
   if (document.cookie.length > 0) { 
     offset = document.cookie.indexOf(search) 
     if (offset != -1) {/* 如果指定的cookie已经存在 */
        offset += search.length /* 长度指定到cookie值的位置    */            
        end = document.cookie.indexOf(";", offset); /* 判断是否还包括其他cookie值 */
        if (end == -1) /* 如果不包括 */
           end = document.cookie.length;  /* 获取cookie的长度 */
        returnvalue=unescape(document.cookie.substring(offset, end))/*  获取cookie的值 */
      } 
   }   
   return returnvalue; 
} 

function downLoad(num){
	var fileName=encodeURI(encodeURI($("#fileName"+num).val()));
	var filePath=$("#filePath"+num).val();
	location.href="../guest/index.xhtml?face=downLoad&fileName="+fileName+"&filePath="+filePath;
}
function startmarquee(lh,speed,delay,index){ 
    var t; 
    var p=false; 
    var o=document.getElementById(index); 
    o.innerHTML+=o.innerHTML; 
    o.onmouseover=function(){p=true} ;
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
// startmarquee(23,50,400,'marqueeb0'); 
startmarquee(23,50,400,'marqueebox1'); 
// startmarquee(23,50,400,'marqueeb1'); 
function scroll(obj) {
	var tmp = (obj.scrollLeft)++;
	if (obj.scrollLeft==tmp){
		obj.innerHTML += obj.innerHTML;
	}

    if (obj.scrollLeft>=obj.firstChild.offsetWidth) {
    	obj.scrollLeft=0;
    }
}
setInterval("scroll(document.getElementById('scrollobj'))",40);

</script>

</body>
</html>
