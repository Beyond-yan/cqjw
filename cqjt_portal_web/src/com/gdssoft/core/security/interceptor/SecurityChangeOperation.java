package com.gdssoft.core.security.interceptor;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.gdssoft.core.security.SecurityManager;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SecurityChangeOperation {    

	public static void operate(HttpServletRequest request) {  
		ServletContext servletContext = request.getSession().getServletContext();  
		SecurityManager securityManager = SecurityChangeOperation.getSecurityManager(servletContext);  
		Map<String, String> urlAuthorities = securityManager.loadUrlAuthorities();  
		servletContext.removeAttribute("urlAuthorities"); 
		servletContext.setAttribute("urlAuthorities", urlAuthorities);
	}
	
    public static SecurityManager getSecurityManager(ServletContext servletContext) {  
       return (SecurityManager) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("securityManager");   
    }
}
