package com.gdssoft.core.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.gdssoft.core.tools.LogUtil;

public class UserSessionListener implements HttpSessionListener {



	public UserSessionListener() {
	}

	public void sessionCreated(HttpSessionEvent httpsessionevent) {
		/*if(SystemContext.isAuthenticated()){
			LogUtil.insert();
		}*/
		LogUtil.insert();
	}
	public void sessionDestroyed(HttpSessionEvent event) {
	}

	
}
