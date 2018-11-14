<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" 
	uri="http://java.sun.com/jsp/jstl/fmt" %><%@page 
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript"  src="${StaticResourcePath}/js/jquery-1.7.2.min.js"></script>
<title>重庆交通政务办公网--首页</title>
<script type="text/javascript">
//$("#myform").submit();
$(function(){
	$("#btnSubmit").click();
});

</script>
</head>
<body>
<form name="myform" action="<%=SystemContext.getSsoServiceUrl()%>/remoteLogin" method="post" id="myform">
		<input type="hidden" name="submit" value="true" />
		<input type="hidden" name="lt" value="LT-259-r4yv54f4KQjQOaihqD5Fy55heh7eEU" />
		<input type="hidden" name="service" value="http://app.cqjt.gov.cn/j_spring_cas_security_check" />
		<input type="hidden" name="loginUrl" value="http://app.cqjt.gov.cn/guest/index.xhtml" />
		<input type="hidden" name="username" value="${user.username }" />
		<input type="hidden" name="password" value="${user.password }" />
		<!-- <input type="hidden" name="password" value="123456" /> 
		<input id="btnSubmit" type="submit" value="submit" style="display:black;" />-->
		<input id="btnSubmit" type="submit" value="submit" style="display:none;" />
</form>
</body>

</html>