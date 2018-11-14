package com.gdssoft.core.listener;
/*
 *  捷达世软件(深圳)有限公司
 *  Copyright (C) 2010-2015 ShenZhen JieDaShi Software Limited Company.
*/

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import com.gdssoft.core.tools.PropertiesUtil;

/**
 * 系统启动加载器
 * @author Jimxu
 */
public class StartupListener extends ContextLoaderListener {
	
	protected transient final Log logger = LogFactory.getLog(getClass());
	
	@Override
	protected void customizeContext(ServletContext servletContext, ConfigurableWebApplicationContext applicationContext) {
		String initConfigLocation = servletContext.getInitParameter(CONFIG_LOCATION_PARAM);
		boolean enableSSO = Boolean.parseBoolean(this.getWebAppProperties("sso.enabled").toString());
		if (enableSSO) 
			initConfigLocation += ",classpath:security/spring-security-sso.xml";
		else
			initConfigLocation += ",classpath:security/spring-security.xml";
		applicationContext.setConfigLocation(initConfigLocation);
		super.customizeContext(servletContext, applicationContext);
	}
	
	public void contextInitialized(ServletContextEvent event) {
		
		super.contextInitialized(event);
    	
		event.getServletContext().setAttribute("StaticResourcePath", this.getWebAppProperties("static.resource.path"));
		
	}
	
	private Object getWebAppProperties(String key) {
		String configFilePath = getClass().getResource("/webapp.properties").getFile();
		try {
			configFilePath = java.net.URLDecoder.decode(configFilePath,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("配置文件地址处理失败", e1);
		}
		
    	Properties config = PropertiesUtil.getFromFile(configFilePath);
		
    	return config.getProperty(key);
	}
}
