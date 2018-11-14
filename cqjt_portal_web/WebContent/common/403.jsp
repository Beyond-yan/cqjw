<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html 
	PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>403页面错误</title>
<style>
body {
	margin: 0 0 0 0;
	background:#ffffff url(${StaticResourcePath}/images/layer_topbg.jpg) repeat-x top;
	line-height: 150%;
}
</style>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="80px">&nbsp;</td>
	</tr>
	<tr>
		<td align="left" valign="top" width="100px">
			<div style="padding-top: 10px; padding-left: 50px">
				<img src="${StaticResourcePath}/images/icon-acc.png" border="0"/>
			</div>
		</td>
		<td width="100%">
			<div style="padding-top: 10px; padding-left: 10px">
				<table width="auto" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="600px" height="60px" valign="middle"><font style="font-family: 黑体; font-size: 20px; color: #052964">没有权限访问页面！</font></td>
					</tr>
					<tr>
						<td height="10px" style="background: #ffffff url('images/line.jpg') repeat-x left;">&nbsp;</td>
					</tr>
					<tr>
						<td height="30px" valign="middle">
							<font style="font-family: 宋体; font-size: 14px; color: #052964">
								请确认您已经得到授权可以访问此页面。<br/>
								重新<a href="<%= request.getContextPath()%>/">登录</a>后台管理系统，或联系管理员！
							</font>
						</td>
					</tr>
				</table>
			</div>
		</td>
	</tr>
</table>
</body>