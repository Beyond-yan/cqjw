<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>重庆交通政务办公网--政务信息刊物管理<</title>
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/plugin/ueditor/themes/default/css/ueditor.css" rel="stylesheet" type="text/css" />
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.spOverFlow{
display:block;
width:230px;
overflow: hidden;
white-space: nowrap;
text-overflow: ellipsis;
}
.fileList {height: 320px;}
a {color:blue;cursor: pointer;}
</style>

</head>
<body >
	<body >
         <!--path Start-->
            <div class="path">
                <b class="icon i-home"></b>
                <span>当前位置：</span>
               		信息报送
                <span>-></span>
                	<a href="govTextNews.xhtml?face=listPubInfoView">政务信息刊物管理</a>
                <span>-></span>
                		刊物信息维护
            </div>
            <!--path End-->
            <!--column01 Start-->
            <div class="column" >
                <div class="title"><h2><b class="icon i-search"></b>刊物信息维护</h2></div>
                <div class="con docEdit" >
					<ul>
                        <li>
                        	<input type="hidden" name="isPublic" id="isPublic">
                        	<input type="hidden" name="pubId" id="pubId" value="${pubDetail.pubId}">
                            <label>刊物名称：</label><input type="text" class="input input-medium" value="${pubDetail.pubName}" name="pubName"> 
                            <label>刊物号：</label><input type="text" class="input input-small" value="${pubDetail.pubCode}"  name="pubCode">
                        </li>
                        <li>
                        <label>创建人：</label><span>${pubDetail.createUser}</span>
                        <label>创建时间：</label><span>${pubDetail.createDate}</span>
                        </li>
                        <li class="clearfix">
                          <label class="l">刊物内容：</label>
                          <div  class="fileList">
                       	  	<h3><span>工作动态：</span><a href="javascript:;" onclick="addTextGovInfo('workDynamic')" class="btn-gray"><b class="icon i-addFile"></b> 添加</a></h3>
                            <ul>
                              <c:forEach items="${pubDetail.workDynamics}" var="workDy" varStatus="status">
                            	<li>
                                    <a href="../guest/news.xhtml?face=govInfoDetail&giId=${workDy.giId}" target="_blank" class="file-name" title="${workDy.giTitle}"><span class="spOverFlow">${workDy.giTitle}</span></a>
                                    <p class="file-operate">
                                        <span>顺序号：</span><input type="text" name="sequenceNum" class="input input-mini" value="${workDy.sequenceNum}">
                                        <input type="hidden" name="giId" value="${workDy.giId}">
                                        <a href="javascript:;" onclick="deleteGovInfo('${workDy.giId}')" class="btn-gray">删除</a>
                                    </p>
                                </li>
                              </c:forEach>
                            </ul>

                       	  	<h3><span>区县动态：</span><a href="javascript:;" onclick="addTextGovInfo('countyDynamic')" class="btn-gray"><b class="icon i-addFile"></b> 添加</a></h3>
                            <ul>
                              <c:forEach items="${pubDetail.countyDynamics}" var="countyDy" varStatus="status">
                            	<li>  
                                    <a href="../guest/news.xhtml?face=govInfoDetail&giId=${countyDy.giId}" target="_blank" class="file-name" title="${countyDy.giTitle}"><span class="spOverFlow">${countyDy.giTitle}</span></a>
                                    <p class="file-operate">
                                        <span>顺序号：</span><input type="text" name="sequenceNum" class="input input-mini" value="${countyDy.sequenceNum}">
                                        <input type="hidden" name="giId" value="${countyDy.giId}">
                                        <a href="javascript:;" onclick="deleteGovInfo('${countyDy.giId}')" class="btn-gray">删除</a>
                                    </p>
                                </li>
                              </c:forEach>
                            </ul>

                       	  	<h3><span>一句话信息：</span><a href="javascript:;" onclick="addTextGovInfo('oneInfo')" class="btn-gray"><b class="icon i-addFile"></b> 添加</a></h3>
                            <ul>
                              <c:forEach items="${pubDetail.oneInfos}" var="oneInfo" varStatus="status">
                            	<li>
                                    <a href="../guest/news.xhtml?face=govInfoDetail&giId=${oneInfo.giId}" target="_blank" class="file-name" title="${oneInfo.giTitle}"><span class="spOverFlow">${oneInfo.giTitle}</span></a>
                                    <p class="file-operate">
                                        <span>顺序号：</span><input type="text" name="sequenceNum" class="input input-mini" value="${oneInfo.sequenceNum}">
                                        <input type="hidden" name="giId" value="${oneInfo.giId}">
                                        <a href="javascript:;" onclick="deleteGovInfo('${oneInfo.giId}')" class="btn-gray">删除</a>
                                    </p>
                                </li>
                              </c:forEach>
                            </ul>

                       	  	<h3><span>网站信息：</span><a href="javascript:;" onclick="addTextGovInfo('netInfo')" class="btn-gray"><b class="icon i-addFile"></b> 添加</a></h3>
                            <ul>
                            	  <c:forEach items="${pubDetail.netInfos}" var="netInfo" varStatus="status">
                            	<li>
                                    <a href="../guest/news.xhtml?face=govInfoDetail&giId=${netInfo.giId}" target="_blank" class="file-name" title="${netInfo.giTitle}"><span class="spOverFlow">${netInfo.giTitle}</span></a>
                                    <p class="file-operate">
                                        <span>顺序号：</span><input type="text" name="sequenceNum" class="input input-mini" value="${netInfo.sequenceNum}">
                                        <input type="hidden" name="giId" value="${netInfo.giId}">
                                        <a href="javascript:;" onclick="deleteGovInfo('${netInfo.giId}')" class="btn-gray">删除</a>
                                    </p>
                                </li>
                              </c:forEach>
                            </ul>
                          </div>
                      	</li>
                        <li>
                            <div style="margin-top:20px;margin-bottom:25px;margin-left:30%;">

	                        	<a href="javascript:;" onclick="savePubDetail(0)"  class="btn-gray"><b class="icon i-add"></b>保存草稿</a>
	                        	<a href="javascript:;" onclick="savePubDetail(1)"  class="btn-gray"><b class="icon i-add"></b>发布</a>
	                            <a href="javascript:history.go(-1);"  class="btn-gray"><b class="icon i-back"></b>返回</a>
                            </div>
                        </li>
                    </ul>
    
                </div>
            </div>
            <!--column01 End-->
            <!--column02 Start-->
      <div class="column" style="width:65%" >
        <div class="title"><h2><b class="icon i-search"></b>信息报送处理记录表</h2></div>
        <div class="con">
        	<table width="60%" border="1" class="dataList">
                <thead>
                  <tr>                    
                    <td width="5%">序号</td>
                    <td width="10%">处理人</td>
                    <td width="15%">处理时间</td>
                    <td width="8%">处理事项</td>
                  </tr>
                </thead>
                <tbody id="tbodyVideo">
                <c:forEach items="${newsRecord}" var="tn" varStatus="status">
					<tr>
						<td>${status.index + 1}</</td>     
                        <td>${tn.updateUser}</td>
                        <td>${tn.updateDate}</td>
                       <td>
                       		<c:if test='${tn.flag=="17"}'>发布</c:if>
                        	<c:if test='${tn.flag=="16"}'>复原</c:if>
	                        <c:if test='${tn.flag=="1"}'>投稿</c:if>
	                        <c:if test='${tn.flag=="0"}'>存草稿</c:if>
                        </td>
           					</tr>
				</c:forEach>
			    </tbody>
			</table>
			<div id="userListPager" class="page clearfix"></div>
        </div>
    </div>
    <!--column02 End-->
</body>
</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">
window.APP_HOME_URL = "${pageContext.request.contextPath}";
//增加
function addTextGovInfo(pubType) {
	asyncbox.open({
	    title:"政务信息选择窗口",
		id:"selectGovInfoWindow",
		modal: true,
	　　width : 680,
	　　height : 400,
	    url: "govTextNews.xhtml?face=listGovInfoForPub",
	    btnsbar:$.btn.OKCANCEL,
	    callback : function(action){
	        if (action=="ok") {
	        	var contents = $("#selectGovInfoWindow_content").contents();
	        	$.each(contents.find("#tbodyNews input:checked"), function(i, n) {
	        		var saveUrl = "govTextNews.xhtml?action=savePubGovRelation";
	        		$.ajax({
	        			url : saveUrl,
	        			data : {
	        				pubId : "${param.pubId}",
	        				pubType : pubType,
	        				giId : $(n).attr("id")
	        			},
	        			async : false, //同步，保存完成才会继续下一个操作
	        			success : function() {
	        				console.log(saveUrl);
	        			}
	        		});
	        	});
	        	//刷新当前页面
	        	location.reload();
	        }
	    }
	});
}

//新增
function savePubDetail(flag){
	var giIdStr = "";
	var sequenceNumStr = "";
	$("input[name='giId']").each(function(){
		giIdStr += $(this).val()+",";
		
	});
	$("input[name='sequenceNum']").each(function(){
		if($(this).val()!=""){
			sequenceNumStr +=$(this).val()+",";
		}
	});
	$("#isPublic").val(flag);
	var isPublic=$("#isPublic").val();
	var pubId=$("#pubId").val();
	var pubName=$("input[name='pubName']").val();
	var pubCode=$("input[name='pubCode']").val();
	
	//alert(giIdStr);
	//alert(sequenceNumStr);
	if(giIdStr==""||sequenceNumStr==""){
		asyncbox.confirm("刊物内容或顺序号为空！", "信息窗口");
	}else{
		$.ajax({
			url : "govTextNews.xhtml?action=savePubDetail",
			cache : false,
			type : "post",
			dataType : "text",
			data : {
				"giIdStr" : giIdStr,
				"sequenceNumStr" : sequenceNumStr,
				"isPublic" : isPublic,
				"pubId" : pubId,
				"pubName" : pubName,
				"pubCode" : pubCode
			},
			success : function(data) {
				document.location.href = "govTextNews.xhtml?face=listPubInfoView";
				<%--var content = eval('(' + data + ')');
				asyncbox.confirm(content.msg + "是否返回刊物信息列表页面？", "确认窗口", function(action) {
					if (action == "ok") {
						document.location.href = "govTextNews.xhtml?face=listPubInfoView";
					}
				});  
				--%>
				},
				error :function(data){
					alert("保存数据失败");
				}
			});
	}
}

//删除信息
function deleteGovInfo(giId) {
	asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) { 
		if (action == "ok") {
			$.ajax({
				url : "govTextNews.xhtml?action=deleteGovPubRef",
				cache : false,
				type : "GET",
				dataType : "text",
				data : {
					"giId" : giId
				},
				success : function(content) {
					//asyncbox.alert(content, "信息窗口", function() {
						document.location.reload();
					//});

				}
			});
		}
	})
}

</script>
</html>
