<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.gdssoft.core.tools.SystemContext"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>重庆交通政务办公网--首页</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet"
	type="text/css">
<link href="${StaticResourcePath}/css/index.css" rel="stylesheet"
	type="text/css">
<link
	href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
.main .infoList a {
	width: 398px;
	max-width: 398px;
}

#sign .infoList a {
	max-width: 420px;
	width: 420px;
}

#ranking td {
	text-align: left;
}

.box {
	height: 70px;
	line-height: 23px;
	white-space: nowrap;
	overflow: hidden;
	height: 70px;
}

.box ul {
	margin: 0;
	padding: 0
}

.box li {
	font-size: 12px;
	text-align: left;
}

.notice {
	height: 30px;
	line-height: 30px;
	white-space: nowrap;
	overflow: hidden;
}

.left {
	float: left;
	width: 20px;
	margin-top: 8px;
}

.login a {
	color: #0278e2;
}

.login a:hover {
	color: #c93609;
}

.left {
	float: left;
	width: 20px;
	margin-top: 8px;
	height: 40px;
}

.i-rArr {
	margin: 0 4px 0 5px;
}

.videoTitle {
	max-width: 60px;
	overflow-x: hidden;
	overflow-y: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	word-break: keep-all
}

.tabCon li a {
	display: block !important;
}

.main {
	width: 100%;
}

.time {
	margin-right: 22px;
}

.tabConli {
	width: 489px;
	float: left;
}

.textFrom {
	width: 130px !important;
}

select {
	height: 25px;
}
#sign1{
	height:210px !important;
}
</style>
</head>
<body>
	<div class="wrap">
		<%@include file="../head.jspf"%>
		<div class="content clearfix">
			<!--search-box Start-->
			<div class="search-box">
				<div class="breadCutNav">
                <b class="icon icon_home"></b>
                <span>当前位置：</span>
                <a href="index.xhtml">首页</a>
                <span> &gt;</span>
                <a href="javascript:;">部门上报</a>
            </div> 
				<%@include file="search.jspf"%>
			</div>
			<!--search-box End-->


			<!--详细信息查询 Start-->
			<div class="column">
				<div class="con">
					<div style="margin-bottom: 10px;">
						<label>部门分类：</label> <select id="selectstatus" name="deptCategory"
							class="input-small textFrom" style="width: 300px">
							<option selected value="市交委机关">市交委机关</option>
							<option value="委属单位">委属单位</option>
						</select> <label style="padding-left: 30px;">部门名称：</label> <select
							id="selectDeptName" name="deptID" class="input-small textFrom">
							<option selected value="110">办公室</option>
						</select> <a href="javascript:;" onclick="searchInfo();return false;"
							class="btn btn-blue">搜索</a>
					</div>
				</div>
			</div>
			<!--详细信息查询  End-->


			<div class="clear"></div>

			<div class="main" id="maintab">
				 
			</div>
		</div>






	</div>
	<!--content End-->
	<%@include file="../footer.jspf"%>
	</div>
	<script type="text/javascript"
		src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
	<script type="text/javascript"
		src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
	<script type="text/javascript">
		//对部门下拉列表连动
		function searchList() {
			var deptCategory = $("select[name='deptCategory']").val();
			$.ajax({
					url : "SearchContent.xhtml?action=queryDeptInfoView",
					cache : false,
					type : "post",
					dataType : "text",
					data : {
						"deptCategory" : deptCategory
					},
					success : function(data) {
						var json = eval(data);

						var departmentInfoList = "";
						$.each(
										json,
										function(i, n) {
											var deptName = n.deptName;
											if("委领导"!=deptName){
												if(i==1&&"市交委机关"==deptCategory){
													departmentInfoList += "<option selected value='"+n.deptID+"'>"
													+ deptName
													+ "</option>";
												}else{
													departmentInfoList += "<option value='"+n.deptID+"'>"
													+ deptName
													+ "</option>";
												}
											}
										});
						$("#selectDeptName").html(departmentInfoList);
					},
					error : function(data) {
					}
				});
		}

		searchList();

		//根据分类查询部门，并加载下拉连动	
		$("#selectstatus").change(function() {
			searchList();
		});

		
		 
		function searchInfo() {
			var deptID = $("select[name='deptID']").val();
			if(deptID==""){
				deptID="110";
			}
			$.ajax({
				url : "SearchContent.xhtml?face=queryCategoryDeptList",
				cache : false,
				type : "post",
				dataType : "json",
				data : {
					"deptID" : deptID
				},
				success : function(data) {
					
					//组装导航选项卡
					var oneTitleInfo = "";
					var towTitleInfo = "";
					var threeTitleInfo = "";
					$.each(data.categoryList,function(i, n) {
						if(i==0){
							oneTitleInfo += "<li id='"+n.categoryId+"' class='active' onclick='catViewInfo("+n.categoryId+",\""+n.deptName+"\");return false;'>"+n.categoryName+"</li>";
						}else if(i>0&&i<3){
							oneTitleInfo += "<li id='"+n.categoryId+"' onclick='catViewInfo("+n.categoryId+",\""+n.deptName+"\");return false;'>"+n.categoryName+"</li>";
						}
						if(i==3){
							towTitleInfo += "<li id='"+n.categoryId+"' class='active' onclick='catViewInfo("+n.categoryId+",\""+n.deptName+"\");return false;'>"+n.categoryName+"</li>";
						}else if(i>3&&i<6){
							towTitleInfo += "<li id='"+n.categoryId+"' onclick='catViewInfo("+n.categoryId+",\""+n.deptName+"\");return false;'>"+n.categoryName+"</li>";
						}
						
						if(i==6){
							threeTitleInfo += "<li id='"+n.categoryId+"' class='active' onclick='catViewInfo("+n.categoryId+",\""+n.deptName+"\");return false;'>"+n.categoryName+"</li>";
						}else if(i>6){
							threeTitleInfo += "<li id='"+n.categoryId+"' onclick='catViewInfo("+n.categoryId+",\""+n.deptName+"\");return false;'>"+n.categoryName+"</li>";
						}
					});
					//组装tab主体
					var oneCatViewInfo = "";
					var towCatViewInfo = "";
					var threeCatViewInfo = "";
					var catlength = parseInt(data.categoryList.length);
					if(catlength>0){
						oneCatViewInfo = "<div class='column' id='sign1'><div class='title'><ul class='tab'>"+oneTitleInfo+
						"</ul></div><div class='con clearfix'><ul class='tabCon infoList clearfix'>";
						$.each(data.oneNewsList,function(i, n) {
							if("112"==deptID){
								var entryDate=n.pubDate.substring(0,10);
								oneCatViewInfo+="<li  class='tabConli'><a href='person.xhtml?face=gatPersonDetails&personId="+n.personId+"' target='_blank'>"+
								"<b class='i-list'></b>"+n.personTitle+"</a><span class='time' style='float:right;'>"+entryDate+"</span></li>";
							}else{
								var entryDate=n.entryDate.substring(0,10);
								oneCatViewInfo+="<li  class='tabConli'><a href='../guest/SearchContent.xhtml?face=queryTextNewsByID&newsId="+n.newsId+"'>"+
								"<b class='i-list'></b>"+n.newsTitle+"</a><span class='time' style='float:right;'>"+entryDate+"</span></li>";
							}
						})
                    	if(data.oneNewsList.length>0){
                    		if("112"==deptID){
                    			oneCatViewInfo+="<li style='float:right;'><a href='${pageContext.request.contextPath}/guest/person.xhtml?face=personList'><span style='color:#C93609;font-weight:bold;font-size:14px;font-family: 微软雅黑;float:right;padding-bottom:5px'>更多></span></a></li>";
                    		}else{
                    			oneCatViewInfo+="<li style='float:right;'><a href='../guest/SearchContent.xhtml?face=contentMoreView&categoryId="+data.categoryList[0].categoryId+"&deptName="+data.categoryList[0].deptName+"'><span style='color:#C93609;font-weight:bold;font-size:14px;font-family: 微软雅黑;float:right;padding-bottom:5px'>更多></span></a></li>";
                    		}	
                    	}else{
                    		oneCatViewInfo+="<li style='text-align:center;padding-top:40px;'>暂无信息</li>";
                    	}
						oneCatViewInfo+="</ul></div></div><div class='clear'></div>";
					}
					if(catlength>3){
						towCatViewInfo = "<div class='column' id='sign1'><div class='title'><ul class='tab'>"+towTitleInfo+
						"</ul></div><div class='con clearfix'><ul class='tabCon infoList clearfix'>";
						$.each(data.towNewsList,function(i, n) {
							
							if("116"==deptID){
	                    		var entryDate=n.createDate.substring(0,10);
								towCatViewInfo+="<li  class='tabConli'><a href='statute.xhtml?face=gatStatuteDetails&statuteId="+n.statuteId+"' target='_blank'>"+
								"<b class='i-list'></b>"+n.statuteTitle+"</a><span class='time' style='float:right;'>"+entryDate+"</span></li>";
							}else{
								var entryDate=n.entryDate.substring(0,10);
								towCatViewInfo+="<li  class='tabConli'><a href='../guest/SearchContent.xhtml?face=queryTextNewsByID&newsId="+n.newsId+"'>"+
								"<b class='i-list'></b>"+n.newsTitle+"</a><span class='time' style='float:right;'>"+entryDate+"</span></li>";	
							}
						})
                    	if(data.towNewsList.length>0){
                    		if("116"==deptID){
                    			towCatViewInfo+="<li style='float:right;'><a href='${pageContext.request.contextPath}/guest/statute.xhtml?face=lawRegulations'><span style='color:#C93609;font-weight:bold;font-size:14px;font-family: 微软雅黑;float:right;padding-bottom:5px'>更多></span></a></li>";
                    		}else{
                    			towCatViewInfo+="<li style='float:right;'><a href='../guest/SearchContent.xhtml?face=contentMoreView&categoryId="+data.categoryList[3].categoryId+"&deptName="+data.categoryList[3].deptName+"'><span style='color:#C93609;font-weight:bold;font-size:14px;font-family: 微软雅黑;float:right;padding-bottom:5px'>更多></span></a></li>";
                    		}
                    	}else{
                    		towCatViewInfo+="<li style='text-align:center;padding-top:40px;'>暂无信息</li>";
                    	}
                    	towCatViewInfo+="</ul></div></div><div class='clear'></div>";
					}
					if(catlength>6){
						threeCatViewInfo = "<div class='column' id='sign1'><div class='title'><ul class='tab'>"+threeTitleInfo+
						"</ul></div><div class='con clearfix'><ul class='tabCon infoList clearfix'>";
						$.each(data.threeNewsList,function(i, n) {
							var entryDate=n.entryDate.substring(0,10);
							threeCatViewInfo+="<li  class='tabConli'><a href='../guest/SearchContent.xhtml?face=queryTextNewsByID&newsId="+n.newsId+"'>"+
							"<b class='i-list'></b>"+n.newsTitle+"</a><span class='time' style='float:right;'>"+entryDate+"</span></li>";
						})
                    	if(data.threeNewsList.length>0){
                    		threeCatViewInfo+="<li style='float:right;'><a href='../guest/SearchContent.xhtml?face=contentMoreView&categoryId="+data.categoryList[6].categoryId+"&deptName="+data.categoryList[6].deptName+"'><span style='color:#C93609;font-weight:bold;font-size:14px;font-family: 微软雅黑;float:right;padding-bottom:5px'>更多></span></a></li>";
                    	}else{
                    		threeCatViewInfo+="<li style='text-align:center;padding-top:40px;'>暂无信息</li>";
                    	}
						threeCatViewInfo+="</ul></div></div><div class='clear'></div>";
					}
					var sumCatViewInfo = oneCatViewInfo+towCatViewInfo+threeCatViewInfo;
					
					$("#maintab").html(sumCatViewInfo);
				},
				error : function(data) {
					alert("查询失败");
				}
			});
		}
		//页面初始化
		searchInfo();
		
		
		function catViewInfo(categoryId,deptName){
			$.ajax({
				url : "SearchContent.xhtml?face=queryTextNewsByCatId",
				cache : false,
				type : "post",
				dataType : "json",
				data : {
					"categoryId" : categoryId,
					"deptName" : deptName
				},
				success : function(data) {
					var cat = "#"+categoryId
					$(cat).parent().children().removeClass("active");
					$(cat).addClass("active");
					var catViewInfo = "<ul class='tabCon infoList clearfix'>";
					$.each(data,function(i, n) {
						if("21"==categoryId){
							var entryDate=n.createDate.substring(0,10);
							catViewInfo+="<li  class='tabConli'><a href='statute.xhtml?face=gatStatuteDetails&statuteId="+n.statuteId+"' target='_blank'>"+
							"<b class='i-list'></b>"+n.statuteTitle+"</a><span class='time' style='float:right;'>"+entryDate+"</span></li>";
						}else if("80"==categoryId){
							var entryDate=n.pubDate.substring(0,10);
							catViewInfo+="<li  class='tabConli'><a href='person.xhtml?face=gatPersonDetails&personId="+n.personId+"' target='_blank'>"+
							"<b class='i-list'></b>"+n.personTitle+"</a><span class='time' style='float:right;'>"+entryDate+"</span></li>";
						}else{
							var entryDate=n.entryDate.substring(0,10);
							catViewInfo+="<li  class='tabConli'><a href='../guest/SearchContent.xhtml?face=queryTextNewsByID&newsId="+n.newsId+"'>"+
							"<b class='i-list'></b>"+n.newsTitle+"</a><span class='time' style='float:right;'>"+entryDate+"</span></li>";
						}
					})
                	if(data.length>0){
                		if("21"==categoryId){
                			catViewInfo+="<li style='float:right;'><a href='${pageContext.request.contextPath}/guest/statute.xhtml?face=lawRegulations'><span style='color:#C93609;font-weight:bold;font-size:14px;font-family: 微软雅黑;float:right;padding-bottom:5px'>更多></span></a></li>";
                		}else if("80"==categoryId){
                			catViewInfo+="<li style='float:right;'><a href='${pageContext.request.contextPath}/guest/person.xhtml?face=personList'><span style='color:#C93609;font-weight:bold;font-size:14px;font-family: 微软雅黑;float:right;padding-bottom:5px'>更多></span></a></li>";
                		}else{
                			catViewInfo+="<li style='float:right;'><a href='../guest/SearchContent.xhtml?face=contentMoreView&categoryId="+categoryId+"&deptName="+deptName+"'><span style='color:#C93609;font-weight:bold;font-size:14px;font-family: 微软雅黑;float:right;padding-bottom:5px'>更多></span></a></li>";
                		}
                	}else{
                		catViewInfo+="<li style='text-align:center;padding-top:40px;'>暂无信息</li>";
                	}
					catViewInfo+="</ul></div></div><div class='clear'></div>";
					$(cat).parent().parent().next().html(catViewInfo);
				},
				error : function(data) {
					alert("查询失败！");
				}
			});
			
		}
		
		
		
		
	</script>

</body>
</html>
