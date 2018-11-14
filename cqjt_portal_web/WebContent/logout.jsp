<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
       String path = request.getContextPath();
       String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登出</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		function load() {
			window.top.location.target="_top";
			window.top.location.href="j_spring_security_logout";
			alert("超时，请重新登陆。");
		}
		window.onload.href="login1.jsp";
	</script>
  </head>
  
  <body onload="load();">
  	<!-- 當前會話無效 <a href="login.jsp" target="_top">重新登录</a> -->
  </body>
</html>