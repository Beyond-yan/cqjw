<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<%@page import="com.gdssoft.core.tools.SystemContext"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.gdssoft.core.tools.EncryptSha256Util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${StaticResourcePath}/css/main.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/css/index.css" rel="stylesheet" type="text/css">
<link href="${StaticResourcePath}/plugin/asyncbox/skins/ZCMS/asyncbox.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form name="myform" action="<%=SystemContext.getSsoServiceUrl()%>/remoteLogin" method="post">
<ul>
	<c:if test="${not empty param.error}">
	<li>
		<div align="center">
			<span id="wrong" style="font-size: 12px; color: red; display: block;" 
			      class="login_txt">用户名或密码错误，请重新输入。111</span>
		</div>
	</li>
	</c:if>
	<li>
		<label>用户名：</label>
		<span class="input-text">
			<!-- <input type="input" name="username" id="username" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}"> -->
        	<input type="input" name="username" id="username"  />
        </span>
    </li>
    <li>
    	<label>密  码：</label>
    	<span class="input-text">
    		<input type="password" name="password" id="password" />
    	</span>
    </li>
    <li>
    	<label></label>${param.ticket}------->${param.service}---->
    	<input type="checkbox" class="checkbox" checked="true" onclick="if(this.checked){this.value=1;}else{this.value=0;}">记住用户名
    	<!--<a href="javascript:;" class="forgetPwd">忘记密码</a>-->
    	<input type="hidden" name="submit" value="true" />
		<input type="hidden" name="lt" value="LT-373-rFVgeBfoySdU2doQxU1NN30yolu57m" />
		<input type="hidden" name="service" value="http://app.cqjt.gov.cn/j_spring_cas_security_check" />
		<input type="hidden" name="loginUrl" value="http://app.cqjt.gov.cn/guest/index.xhtml" />
	</li>
	<li>
		<label></label>
		<a href="javascript:;" id="btnLogin" class="btn-login">登&nbsp;&nbsp;录</a>
		<input id="btnSubmit" type="submit" value="submit" style="display:none;" />
    </li>
</ul>
</form>
</body>
<script type="text/javascript"  src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"  src="js/main.js"></script>
<script type="text/javascript" src="plugin/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript"  src="js/jquery.cookie.js"></script>
<script type="text/javascript">
function validateAll() {
	var alertMessage = "";
	var userName = $('#username').val();
	userName = userName.replace(/\s+$|^\s+/g, "");
	var password = document.getElementById('password').value;
	password = password.replace(/\s+$|^\s+/g, "");
	if (userName.length == 0 && password.length == 0) {
		/* alertMessage = "usernull"; */
		asyncbox.alert("账号密码不能为空");
	} else if (userName.length == 0) {
		alertMessage = "usernull";
		asyncbox.alert("账号不能为空");
	} else if (password.length == 0) {
		/* alertMessage = "usernull"; */
		asyncbox.alert("密码不能为空");
	} else {
		alertMessage = "";
	}
	if (alertMessage.length == 0) {
		return true;
	} else {
		return false;
	}
}
$(function() {
	var COOKIE_NAME ="username";
	if(COOKIE_NAME){ //如果这个cookie变量确实存在；
		//把cookie变量的值设置为username的值；
		$("#username").val($.cookie(COOKIE_NAME));
	}; 
	$("#btnLogin").click(function() {
		if (validateAll()) {
			$("#btnSubmit").click();
			if($(".checkbox").val()==1){
			      $.cookie(COOKIE_NAME,$("#username").val(), { path: '/', expires: 365 });      
			}
		}
		else
			return false;
	});
	
	 /* document.onkeyup = function(ee) {
		var e = ee || event;
		var key = e.keyCode;
		if (key == 13) {
			$("#btnSubmit").click();
		}
	}; 
	 */
	$("#password").keyup(function(e){
		var key = e.keyCode;
		if (key == 13) {
			if (validateAll()) {
				$("#btnSubmit").click();
				if($(".checkbox").val()==1){
					$.cookie(COOKIE_NAME,$("#username").val(), { path: '/', expires: 365 }); 
				}
			}
			else
				return false;
		}
	})
	
	$("#username").focus();
});
</script>
</html>