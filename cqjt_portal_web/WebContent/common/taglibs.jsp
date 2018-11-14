<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="sio" value="${pageContext.servletContext.serverInfo }" />
<c:set var="rusr" value="${pageContext.request.remoteUser }"/>
<c:set var="radr" value="${pageContext.request.remoteAddr }"/>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="sch" value="${pageContext.request.scheme}"/>
<c:set var="snm" value="${pageContext.request.serverName}"/>
<c:set var="spt" value="${pageContext.request.serverPort}"/>
<c:set var="bth" value="${sch}://${snm}:${spt}${ctx}/"/>
