    <%@ page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"%>
<%@ page import="com.gdssoft.core.tools.Uploader" %>
<%@ page import="com.gdssoft.core.tools.SystemContext" %>

<%
    request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
    Uploader up = new Uploader(request);
    up.setSavePath(SystemContext.getUploaderFilePath());
    String[] fileType = SystemContext.getUploaderImageTypes().split(",");
    up.setAllowFiles(fileType);
    up.setMaxSize(SystemContext.getUploaderImageMaxsize()); //单位KB
    up.upload();
    response.getWriter().print("{'original':'"+up.getOriginalName()+"','url':'"+up.getUrl()+"','title':'"+up.getTitle()+"','state':'"+up.getState()+"'}");
%>
