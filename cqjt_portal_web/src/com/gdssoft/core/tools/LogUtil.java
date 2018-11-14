package com.gdssoft.core.tools;

/*
 *   捷达世软件(深圳)有限公司  重庆交委内网门户
 *   Copyright (C) 2010-2015 ShenZhen JieDaShi Software Limited Company.
 */
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.gdssoft.cqjt.pojo.LogCount;
import com.gdssoft.cqjt.service.LogCountService;

public class LogUtil {

   
	public static String insert() {
		LogCountService logCountService = (LogCountService)SystemContext.getBean("logCountServiceImpl");
		LogCount logCount = new LogCount();
		logCount.setUserName("visitor");
		logCount.setUpdateTime(DateUtil.getCurrentDate());
		InetAddress inet;
		try {
			inet = InetAddress.getLocalHost();
			logCount.setIp(inet.getHostAddress());
		} catch (UnknownHostException e) {
			logCount.setIp("");
			e.printStackTrace();
		}
		logCountService.insert(logCount);
		return "保存成功";

	}

}
