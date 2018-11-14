package com.gdssoft.core.security.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gdssoft.core.security.SecurityManager;
import com.gdssoft.core.tools.SystemContext;

public class UnauthorizedAccessInterceptor extends HandlerInterceptorAdapter {
	protected transient final Log logger = LogFactory.getLog(getClass());
	@Autowired
	@Resource(name = "securityManager")
	private SecurityManager SecurityManagerService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String action = uri.substring(contextPath.length() + 1) + "?"
				+ request.getQueryString();
		String userRole = "";
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			userRole = SystemContext.getRoleName();
		}
		boolean flag = false;
		if (!"Admin".equalsIgnoreCase(userRole) && !"".equals(userRole)) {
			if (SecurityManagerService.checkAuthority(
					SystemContext.getRoleID(), action))
				flag = true;
		} else
			flag = true;
		if (!flag) {
			logger.debug("没有权限：" + action);
			response.sendRedirect(contextPath + "/common/403.jsp");
			return false;
		}
		return super.preHandle(request, response, handler);
	}
}
