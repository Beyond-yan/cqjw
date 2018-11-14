<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><!DOCTYPE html 
	PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${StaticResourcePath}/css/main-sys.css" rel="stylesheet"
	type="text/css">
<link href="${StaticResourcePath}/css/custom.css" rel="stylesheet" type="text/css"/>
<link
	href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/plugin/ueditor/themes/default/css/ueditor.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
/*a {color:blue;cursor: pointer;}*/
.column {
	min-width: 940px;
}

.error {
	color: red;
	padding-left: 10px;
}

.header11 {
	line-height: 32px;
  FONT-WEIGHT: bold;
  VERTICAL-ALIGN: middle;
  TEXT-ALIGN: center;
  FONT-SIZE: 9pt;
  COLOR: #5a5a5a;
  background-color: #f2f2f2;
  FONT-FAMILY: 宋体;
  BORDER-RIGHT: #666666 1px solid;
  BORDER-BOTTOM: #666666 1px solid;
  BORDER-LEFT: #666666 1px solid;
  BORDER-TOP: #666666 1px solid;
}
.report11 {
  border-collapse: collapse;
  table-layout: fixed;
}

</style>
<title>重庆交通政务办公网--政务信息统计报表</title>
</head>

<body>
	<!--path Start-->
	<div class="path">
		<b class="icon i-home"></b> <span>当前位置：</span> <a href="javascript:;">统计分析报表</a>
		<span>-></span> <a href="javascript:;">政务信息统计报表</a>
	</div>
	<!--path End-->
	<!--column01 Start-->
	<div class="column" id="search">
		<div class="title">
			<h2>
				<b class="icon i-search"></b>快速搜索
			</h2>
		</div>
		<div class="con">
			<label>报送时间：</label>
			<div class="data" id="dataSelect-start">
				<input type="text" name="entryDateS" value='<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>'
					class="input-text tcal tcalInput">
			</div>
			<span>&nbsp;～&nbsp;</span>
			<div class="data" id="dataSelect-end">
				<input type="text" name="entryDateE" value='<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>'
					class="input-text  tcal tcalInput">
			</div>
			<label>报表分组：</label> <select id="selectstatus" name="status" class="input-small">
					<option value="全部">全部</option>
					<option value="市交委机关">市交委机关</option>
					<option value="委属单位">委属单位</option>
					<option value="区县交通局">区县交通局</option>
					<option value="市属相关交通企业">市属相关交通企业</option>
			</select>
			<%-- <input type="hidden"  id="status"  value="${status}"/> --%>
			<a href="javascript:;" onclick="searchList();return false;" class="btn btn-blue">统计</a>
			<a href="javascript:;" onclick="exportList();return false;" class="btn btn-blue">导出EXCEL</a>
		</div>
	</div>
	<!--column01 End-->
	<!--column02 Start-->
	<div class="column">
		<div class="title">
			<h2>
				<b class="icon i-search"></b>统计报表
			</h2>
		</div>
		<div class="con" >
			
			<table width="100%" border="1"  class="report11" align="center">
				<thead>
 <colgroup>
    <col width="2%"/>
    <col width="5%"/>
    <col width="8%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="3%"/>
    <col width="4%"/>
    </colgroup>
    <tr height="28" style="min-height:28px; overflow:auto;">
      <td
          rowSpan="3" 
      	  colSpan="1" 
      	  class="header11">
      	 		序号
      	 </td>
      <td
          rowSpan="3" 
      	  colSpan="1"
      	  class="header11">
      	 		分类
      	 </td>
      <td
          rowSpan="3" 
      	  colSpan="1"
      	  class="header11">
      	 		单位
      	 </td>
      <td
      	  colSpan="6"
      	  class="header11">
      	 		交委
      	 </td>
      
      <td
      	  colSpan="4"
      	  class="header11">
      	 		市委
      	 </td>
      	 <td
      	  colSpan="4"
      	  class="header11">
      	 		市政府
      	 </td>
      <td
      	  colSpan="2"
      	  class="header11">
      	 		交通部
      	 </td>
      <td
      	  colSpan="2"
      	  class="header11">
      	 		上级单位
      	 </td>
      <td
      	  colSpan="3"
      	  class="header11">
      	 		领导批示
      	 </td>
     
      <td
          rowSpan="3" 
      	  colSpan="1"
      	  class="header11">
      	 		上报总数
      	 </td>
      	 
      	  <td
          rowSpan="3" 
      	  colSpan="1"
      	  class="header11">
      	 		约稿
      	 </td>

      <td
          rowSpan="3" 
      	  colSpan="1"
      	  class="header11">
      	 		当月得分
      	 </td>
      <td
       	  rowSpan="3" 
      	  colSpan="1"
      	  class="header11">
      	 		累积得分
      	 </td>
    </tr>
    <tr height="28" style="min-height:28px; overflow:auto;">
      <td 
      colSpan="4"
      	  class="header11">
      	 		每日信息
      	 </td>
      <td
       colSpan="2"
      	  class="header11">
      	 		信息专报
      	 </td>
      	 <td
      	  colSpan="2"
      	  class="header11">
      	 		信息专报
      	 </td>
      <td 
      rowSpan="2"
      	  class="header11">
      	 		一把手手机报
      	 </td>
      	 
      <td 
      rowSpan="2"
      	  class="header11">
      	 		每日要情
      	 </td>
      	 
      	 <td
      	  colSpan="2"
      	  class="header11">
      	 		专报信息
      	 </td>
      <td 
      colSpan="2"
      	  class="header11">
      	 		昨日简报
      	 </td>
      	 
      	 <td
      	  colSpan="2"
      	  class="header11">
      	 		交通部简报
      	 </td>

      	
      <td 
       colSpan="2"
      	  class="header11">
      	 		中办、国办
      	 </td>

	<td rowSpan="2"
      	 
      	  class="header11">
      	 		被委领导批示
      	 </td>
     <td 
      rowSpan="2"
      	  class="header11">
      	 		被市领导批示
      	 </td>
      <td 
   	 rowSpan="2"
   	  class="header11">
   	 		被上级领导批示
   	 </td>
    </tr>
    <tr height="28" style="min-height:28px; overflow:auto;">
    <td
      	  class="header11">
      	 		工作动态
      	 </td>
      	 <td
      	 
      	  class="header11">
      	 		区县动态
      	 </td>
      	 <td
      	 
      	  class="header11">
      	 		一句话信息
      	 </td>
      	 <td
      	 
      	  class="header11">
      	 		网络信息
      	 </td>
      <td
      	 
      	  class="header11">
      	 		独编
      	 </td>
      <td
      	 
      	  class="header11">
      	 		综合
      	 </td>
      <td
      	 
      	  class="header11">
      	 		独编
      	 </td>
      <td
      	 
      	  class="header11">
      	 		综合
      	 </td>
      	       <td
      	 
      	  class="header11">
      	 		独编
      	 </td>
      <td
      	 
      	  class="header11">
      	 		综合
      	 </td>
      <td
      	 
      	  class="header11">
      	 		工作动态
      	 </td>
      <td
      	 
      	  class="header11">
      	 		一句话信息
      	 </td>
      <td
      	 
      	  class="header11">
      	 		情况与交流
      	 </td>
      <td
      	 
      	  class="header11">
      	 		日报
      	 </td>
      <td
      	 
      	  class="header11">
      	 		独编
      	 </td>
      <td
      	 
      	  class="header11">
      	 		综合
      	 </td>
    </tr>

				</thead>
				<tbody id="tbody">
					<c:forEach items="${textGovReportList}" var="tn" varStatus="status">
						<tr>
							<td>${status.index + 1}</td>
							<th style="text-align:left;">${tn.deptCategory}</th>
							<td style="text-align:left;">${tn.deptName}</td>
							<td>${tn.pubtypedynamic}</td>
							<td>${tn.countyDynamic}</td>
							<td>${tn.pubTypewordinfo}</td>
							<td>${tn.netInfo}</td>
							<td>${tn.jwdbsubjectInfo}</td>
							<td>${tn.jwzhsubjectInfo}</td>
							<td>${tn.comLeader}</td>
							<td>${tn.sfdbsubjectInfo}</td>
							<td>${tn.sfzhsubjectInfo}</td>
							<td>${tn.workdynamic}</td>
							<td>${tn.sfwordinfo}</td>
							<td>${tn.govLeader}</td>
							<td>${tn.jwdbsubjectInfo}</td>
							<td>${tn.jwzhsubjectInfo}</td>
							<td>${tn.headphonepa}</td>
							<td>${tn.swdayinfo}</td>
							<td>${tn.govLeader}</td>
							<td>${tn.trafsitcomm}</td>
							<td>${tn.trafdaypa}</td>
							<td>${tn.trafLeader}</td>
							<td>${tn.otherdbsubjectInfo}</td>
							<td>${tn.otherzhsubjectInfo}</td>
							<td>${tn.otherLeader}</td>
							<td>${tn.yuegao}</td>
							<td>${tn.shangb}</td>
							<td>${tn.shangbScore}</td>
							<td>${tn.total}</td>
							<td>${tn.totalYear}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>

	</div>
</div>
<!--column02 End-->


</body>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/main.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/js/date.js"></script>
<script type="text/javascript" src="${StaticResourcePath}/plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript">


function searchList() {
	var startDate =  $("input[name='entryDateS']").val();
	var endDate =  $("input[name='entryDateE']").val();
	var status =  $("#selectstatus").val();
	if(startDate==""||endDate==""){
		asyncbox.alert("请选择报送时间！","信息窗口");
	}else{
		$.ajax({
			url : "reportForm.xhtml?action=getGovInfoReportListAll",
			cache : false,
			type : "post",
			dataType : "text",
			data : {
				"startDate" : startDate,
				"endDate" : endDate,
				"status" : status
			},
			success : function(data) {
				var json = $.parseJSON(data);
				
				
				var fileList="",daily="",jwdbsubjectInfo="",jwzhsubjectInfo="",comLeader="",sfdbsubjectInfo="",sfzhsubjectInfo="",
				workdynamic="",sfwordinfo="",govLeader="",swdbsubjectInfo="",swzhsubjectInfo="",headphonepa="",
				swdayinfo="",govComLeader="",trafsitcomm="",trafdaypa="",trafLeader="",otherdbsubjectInfo="",otherzhsubjectInfo="",
				otherLeader="",shangb="",shangbScore="",total="",totalYear="",
				pubtypedynamic="",countyDynamic="",netInfo="",pubTypewordinfo="",yuegao="";
				
				var tdaily=0,tjwdbsubjectInfo=0,tjwzhsubjectInfo=0,tcomLeader=0,tsfdbsubjectInfo=0,tsfzhsubjectInfo=0,
				tworkdynamic=0,tsfwordinfo=0,tgovLeader=0,
					tswdbsubjectInfo=0,tswzhsubjectInfo=0,theadphonepa=0,tswdayinfo=0,tgovComLeader=0,ttrafsitcomm=0,ttrafdaypa=0,
					ttrafLeader=0,
					totherdbsubjectInfo=0,totherzhsubjectInfo=0,totherLeader=0,tshangb = 0,tshangbScore =0,ttotal =0,
					ttotalYear=0,tpubtypedynamic=0,tcountyDynamic=0,tnetInfo=0,tpubTypewordinfo=0,tyuegao=0;
			    $.each(json, function(i, n) {
			    	
			    daily=n.daily;jwdbsubjectInfo=n.jwdbsubjectInfo;jwzhsubjectInfo=n.jwzhsubjectInfo;
				comLeader=n.comLeader;sfdbsubjectInfo=n.sfdbsubjectInfo;sfzhsubjectInfo=n.sfzhsubjectInfo;
				workdynamic=n.workdynamic;sfwordinfo=n.sfwordinfo;govLeader=n.govLeader;yuegao=n.yuegao;
				swdbsubjectInfo=n.swdbsubjectInfo;swzhsubjectInfo=n.swzhsubjectInfo;headphonepa=n.headphonepa;
				swdayinfo=n.swdayinfo;govComLeader=n.govComLeader;trafsitcomm=n.trafsitcomm;trafdaypa=n.trafdaypa;
				trafLeader=n.trafLeader;otherdbsubjectInfo=n.otherdbsubjectInfo;otherzhsubjectInfo=n.otherzhsubjectInfo;
				otherLeader=n.otherLeader;shangb=n.shangb;shangbScore=n.shangbScore;total=n.total;totalYear=n.totalYear;
				pubtypedynamic=n.pubtypedynamic;countyDynamic=n.countyDynamic;
				netInfo=n.netInfo;pubTypewordinfo=n.pubTypewordinfo;
			    	if(shangb==""||shangb==null){shangb=0;tshangb = parseFloat(tshangb)+parseFloat(shangb);shangb="";}
			    	else{tshangb = parseFloat(tshangb)+parseFloat(shangb);}
			    	if(shangbScore==""||shangbScore==null){
			    		if(shangbScore==0){tshangbScore = parseFloat(tshangbScore)+parseFloat(shangbScore);shangbScore=0;
			    		}else{
				    		shangbScore=0;tshangbScore = parseFloat(tshangbScore)+parseFloat(shangbScore);shangbScore="";
				    		}
			    		}
			    	else{tshangbScore = parseFloat(tshangbScore)+parseFloat(shangbScore);}

			    	if(daily==""||daily==null){daily=0;tdaily=parseFloat(tdaily)+parseFloat(daily);daily="";}
			    	else{tdaily=parseFloat(tdaily)+parseFloat(daily);}
			    	
			    	if(yuegao==""||yuegao==null){yuegao=0;tyuegao=parseFloat(tyuegao)+parseFloat(yuegao);yuegao="";}
			    	else{tyuegao=parseFloat(tyuegao)+parseFloat(yuegao);}
					
					if(pubtypedynamic==""||pubtypedynamic==null){pubtypedynamic=0;tpubtypedynamic=parseFloat(tpubtypedynamic)+parseFloat(pubtypedynamic);pubtypedynamic="";}
			    	else{tpubtypedynamic=parseFloat(tpubtypedynamic)+parseFloat(pubtypedynamic);}
					
					if(countyDynamic==""||countyDynamic==null){countyDynamic=0;tcountyDynamic=parseFloat(tcountyDynamic)+parseFloat(countyDynamic);countyDynamic="";}
			    	else{tcountyDynamic=parseFloat(tcountyDynamic)+parseFloat(countyDynamic);}
					
					if(netInfo==""||netInfo==null){netInfo=0;tnetInfo=parseFloat(tnetInfo)+parseFloat(netInfo);netInfo="";}
			    	else{tnetInfo=parseFloat(tnetInfo)+parseFloat(netInfo);}
					
					if(pubTypewordinfo==""||pubTypewordinfo==null){pubTypewordinfo=0;tpubTypewordinfo=parseFloat(tpubTypewordinfo)+parseFloat(pubTypewordinfo);pubTypewordinfo="";}
			    	else{tpubTypewordinfo=parseFloat(tpubTypewordinfo)+parseFloat(pubTypewordinfo);}

					
					if(jwdbsubjectInfo==""||jwdbsubjectInfo==null){jwdbsubjectInfo=0;tjwdbsubjectInfo=parseFloat(tjwdbsubjectInfo)+parseFloat(jwdbsubjectInfo);jwdbsubjectInfo="";}
			    	else{tjwdbsubjectInfo=parseFloat(tjwdbsubjectInfo)+parseFloat(jwdbsubjectInfo);}
					
					if(jwzhsubjectInfo==""||jwzhsubjectInfo==null){jwzhsubjectInfo=0;tjwzhsubjectInfo=parseFloat(tjwzhsubjectInfo)+parseFloat(jwzhsubjectInfo);jwzhsubjectInfo="";}
			    	else{tjwzhsubjectInfo=parseFloat(tjwzhsubjectInfo)+parseFloat(jwzhsubjectInfo);}
					
					if(comLeader==""||comLeader==null){comLeader=0;tcomLeader=parseFloat(tcomLeader)+parseFloat(comLeader);comLeader="";}
			    	else{tcomLeader=parseFloat(tcomLeader)+parseFloat(comLeader);}
					
					if(sfdbsubjectInfo==""||sfdbsubjectInfo==null){sfdbsubjectInfo=0;tsfdbsubjectInfo=parseFloat(tsfdbsubjectInfo)+parseFloat(sfdbsubjectInfo);sfdbsubjectInfo="";}
			    	else{tsfdbsubjectInfo=parseFloat(tsfdbsubjectInfo)+parseFloat(sfdbsubjectInfo);}

					if(sfzhsubjectInfo==""||sfzhsubjectInfo==null){sfzhsubjectInfo=0;tsfzhsubjectInfo=parseFloat(tsfzhsubjectInfo)+parseFloat(sfzhsubjectInfo);sfzhsubjectInfo="";}
			    	else{tsfzhsubjectInfo=parseFloat(tsfzhsubjectInfo)+parseFloat(sfzhsubjectInfo);}
					
					if(workdynamic==""||workdynamic==null){workdynamic=0;tworkdynamic=parseFloat(tworkdynamic)+parseFloat(workdynamic);workdynamic="";}
			    	else{tworkdynamic=parseFloat(tworkdynamic)+parseFloat(workdynamic);}
					
					
					if(sfwordinfo==""||sfwordinfo==null){sfwordinfo=0;tsfwordinfo=parseFloat(tsfwordinfo)+parseFloat(sfwordinfo);sfwordinfo="";}
			    	else{tsfwordinfo=parseFloat(tsfwordinfo)+parseFloat(sfwordinfo);}
					
					if(govLeader==""||govLeader==null){govLeader=0;tgovLeader=parseFloat(tgovLeader)+parseFloat(govLeader);govLeader="";}
			    	else{tgovLeader=parseFloat(tgovLeader)+parseFloat(govLeader);}
					
					if(swdbsubjectInfo==""||swdbsubjectInfo==null){swdbsubjectInfo=0;tswdbsubjectInfo=parseFloat(tswdbsubjectInfo)+parseFloat(swdbsubjectInfo);swdbsubjectInfo="";}
			    	else{tswdbsubjectInfo=parseFloat(tswdbsubjectInfo)+parseFloat(swdbsubjectInfo);}
						
					if(swzhsubjectInfo==""||swzhsubjectInfo==null){swzhsubjectInfo=0;tswzhsubjectInfo=parseFloat(tswzhsubjectInfo)+parseFloat(swzhsubjectInfo);swzhsubjectInfo="";}
			    	else{tswzhsubjectInfo=parseFloat(tswzhsubjectInfo)+parseFloat(swzhsubjectInfo);}
					
					if(headphonepa==""||headphonepa==null){headphonepa=0;theadphonepa=parseFloat(theadphonepa)+parseFloat(headphonepa);headphonepa="";}
			    	else{theadphonepa=parseFloat(theadphonepa)+parseFloat(headphonepa);}
					
					if(swdayinfo==""||swdayinfo==null){swdayinfo=0;tswdayinfo=parseFloat(tswdayinfo)+parseFloat(swdayinfo);swdayinfo="";}
			    	else{tswdayinfo=parseFloat(tswdayinfo)+parseFloat(swdayinfo);}
					
					if(govComLeader==""||govComLeader==null){govComLeader=0;tgovComLeader=parseFloat(tgovComLeader)+parseFloat(govComLeader);govComLeader="";}
			    	else{tgovComLeader=parseFloat(tgovComLeader)+parseFloat(govComLeader);}
					
					if(trafsitcomm==""||trafsitcomm==null){trafsitcomm=0;ttrafsitcomm=parseFloat(ttrafsitcomm)+parseFloat(trafsitcomm);trafsitcomm="";}
			    	else{ttrafsitcomm=parseFloat(ttrafsitcomm)+parseFloat(trafsitcomm);}
					
					if(trafdaypa==""||trafdaypa==null){trafdaypa=0;ttrafdaypa=parseFloat(ttrafdaypa)+parseFloat(trafdaypa);trafdaypa="";}
			    	else{ttrafdaypa=parseFloat(ttrafdaypa)+parseFloat(trafdaypa);}
					
					if(trafLeader==""||trafLeader==null){trafLeader=0;ttrafLeader=parseFloat(ttrafLeader)+parseFloat(trafLeader);trafLeader="";}
			    	else{ttrafLeader=parseFloat(ttrafLeader)+parseFloat(trafLeader);}
					
					if(otherdbsubjectInfo==""||otherdbsubjectInfo==null){otherdbsubjectInfo=0;totherdbsubjectInfo=parseFloat(totherdbsubjectInfo)+parseFloat(otherdbsubjectInfo);otherdbsubjectInfo="";}
			    	else{totherdbsubjectInfo=parseFloat(totherdbsubjectInfo)+parseFloat(otherdbsubjectInfo);}
					
					if(otherzhsubjectInfo==""||otherzhsubjectInfo==null){otherzhsubjectInfo=0;totherzhsubjectInfo=parseFloat(totherzhsubjectInfo)+parseFloat(otherzhsubjectInfo);otherzhsubjectInfo="";}
			    	else{totherzhsubjectInfo=parseFloat(totherzhsubjectInfo)+parseFloat(otherzhsubjectInfo);}
					
					if(otherLeader==""||otherLeader==null){otherLeader=0;totherLeader=parseFloat(totherLeader)+parseFloat(otherLeader);otherLeader="";}
			    	else{totherLeader=parseFloat(totherLeader)+parseFloat(otherLeader);}

			    	if(total==""||total==null){total=0;ttotal=parseFloat(ttotal)+parseFloat(total);total="";}
			    	else{ttotal=parseFloat(ttotal)+parseFloat(total);}
					
					
			    	
			    	if(totalYear==""||totalYear==null){totalYear=0;ttotalYear=parseFloat(ttotalYear)+parseFloat(totalYear);totalYear="";}
			    	else{ttotalYear=parseFloat(ttotalYear)+parseFloat(totalYear);}
			    				    	
				    	fileList+= "<tr><td>"+(i+1)+"</td>"+			    	             
		                               "<th style='text-align:left;'>"+n.deptCategory+"</th>"+
		    							"<td style='text-align:left;'>"+n.deptName+"</td>"+
		    							"<td>"+pubtypedynamic+"</td>"+
		    							"<td>"+countyDynamic+"</td>"+
		    							"<td>"+pubTypewordinfo+"</td>"+
		    							"<td>"+netInfo+"</td>"+
		    							"<td>"+jwdbsubjectInfo+"</td>"+
		    							"<td>"+jwzhsubjectInfo+"</td>"+
		    							
		    							
		    							
		    							"<td>"+swdbsubjectInfo+"</td>"+
		    							"<td>"+swzhsubjectInfo+"</td>"+
		    							"<td>"+headphonepa+"</td>"+
		    							"<td>"+swdayinfo+"</td>"+
		    							
		    							"<td>"+sfdbsubjectInfo+"</td>"+
		    							"<td>"+sfzhsubjectInfo+"</td>"+
		    							"<td>"+workdynamic+"</td>"+
		    							"<td>"+sfwordinfo+"</td>"+
		    							
		    							"<td>"+trafsitcomm+"</td>"+
		    							"<td>"+trafdaypa+"</td>"+
		    							"<td>"+otherdbsubjectInfo+"</td>"+
										"<td>"+otherzhsubjectInfo+"</td>"+
										 "<td>"+comLeader+"</td>"+
										 "<td>"+govLeader+"</td>"+
										 "<td>"+otherLeader+"</td>"+
										"<td>"+shangb+"</td>"+
										"<td>"+yuegao+"</td>"+
										
										"<td>"+total+"</td>"+
		    							"<td>"+totalYear+"</td></tr>"
			    });
			    fileList+="<tr><td></td>"+			    	             
			                 "<th style='text-align:left;'>合计</th>"+
							"<td style='text-align:left;'></td>"+
							"<td>"+tpubtypedynamic+"</td>"+
							"<td>"+tcountyDynamic+"</td>"+
							"<td>"+tpubTypewordinfo+"</td>"+
							"<td>"+tnetInfo+"</td>"+
								"<td>"+tjwdbsubjectInfo+"</td>"+
								"<td>"+tjwzhsubjectInfo+"</td>"+
								
								
								"<td>"+tswdbsubjectInfo+"</td>"+
								"<td>"+tswzhsubjectInfo+"</td>"+
								"<td>"+theadphonepa+"</td>"+
								"<td>"+tswdayinfo+"</td>"+
								
								"<td>"+tsfdbsubjectInfo+"</td>"+
								"<td>"+tsfzhsubjectInfo+"</td>"+
								"<td>"+tworkdynamic+"</td>"+
								"<td>"+tsfwordinfo+"</td>"+
								
								"<td>"+ttrafsitcomm+"</td>"+
								"<td>"+ttrafdaypa+"</td>"+
								"<td>"+totherdbsubjectInfo+"</td>"+
								"<td>"+totherzhsubjectInfo+"</td>"+
								 "<td>"+tcomLeader+"</td>"+
								 "<td>"+tgovLeader+"</td>"+
								 "<td>"+totherLeader+"</td>"+
								"<td>"+tshangb+"</td>"+
								"<td>"+tyuegao+"</td>"+
								
								"<td>"+ttotal+"</td>"+
								"<td>"+ttotalYear+"</td></tr>"
				$("#tbody").html("");
				$("#tbody").html(fileList);
				},
				error :function(data){
					alert("查询数据失败");
				}
		}); 
	}
}

//导出
function  exportList(){
	var startDate =  $("input[name='entryDateS']").val();
	var endDate =  $("input[name='entryDateE']").val();
	var status =  encodeURI(encodeURI($("#selectstatus").val()));
	if(startDate==""||endDate==""){
		asyncbox.alert("请选择报送时间！","信息窗口");
	}else{
		location.href ="reportForm.xhtml?face=exportGovInfoReportListAll&startDate="+startDate+"&endDate="+endDate+"&status="+status;
	}
		/* $.ajax({
		url : "govTextNews.xhtml?action=exportGovInfoReportListAll",
		cache : false,
		type : "post",
		dataType : "text",
		data : {
			"startDate" : startDate,
			"endDate" : endDate,
		},
		success : function(data) {
			alert("成功");
		},
		error :function(data){
			alert("查询数据失败");
		}
	}) */
}

</script>

</html>
