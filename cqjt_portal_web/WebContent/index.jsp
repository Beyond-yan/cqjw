<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@page 
	import="com.gdssoft.core.tools.SystemContext"%><!doctype html>
<html>
<head>
<title>router</title>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%if (!SystemContext.getEnableSSO() && !SystemContext.isAuthenticated()) {%>
<meta http-equiv="refresh" content="0;url=guest/index.xhtml">
<%} else {%>
<meta http-equiv="refresh" content="0;url=web/index.xhtml?face=home">
<%} %>
</head>
<body>
</body>
</html>