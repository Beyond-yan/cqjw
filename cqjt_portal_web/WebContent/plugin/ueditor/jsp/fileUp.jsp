<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.gdssoft.core.tools.Uploader" %>
<%@ page import="com.gdssoft.core.tools.SystemContext" %>
<%
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");

    Uploader up = new Uploader(request);
    up.setSavePath(SystemContext.getUploaderFilePath()); //保存路径
    String[] fileType = SystemContext.getUploaderFileTypes().split(",");  //允许的文件类型
    up.setAllowFiles(fileType);
    up.setMaxSize(SystemContext.getUploaderFileMaxsize());        //允许的文件最大尺寸，单位KB
    up.upload();
    System.out.println(up.getUrl());
    response.getWriter().print("{'url':'"+up.getUrl()+"','fileType':'"+up.getType()+"','state':'"+up.getState()+"','original':'"+up.getOriginalName()+"'}");

%>
