package com.gdssoft.cqjt.util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 初始化servlet
 * @author lll
 */
public class InitServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {

		String CurrentClassFilePath = this.getClass().getResource("").getPath();
		int lastpath = CurrentClassFilePath.lastIndexOf("classes/");
		String subCurrent = CurrentClassFilePath.substring(0, lastpath);
		final String web_rootPath = subCurrent.replace("WEB-INF/", "index_static.htm");
		
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("延时执行开始时间"+new Date());
				boolean isGenerator = HtmlGenerator.createHtmlPage("http://localhost:8080/cqjt_portal_web/guest/index.xhtml", web_rootPath);
//				boolean isGenerator = HtmlGenerator.createHtmlPage("http://www.cqjt.gov.cn/index.html", web_rootPath);
				if(isGenerator) {
					System.out.println("生成静态页面成功:" + web_rootPath);
				}else { 
					System.out.println("生成静态页面失败！");
				}
			}
		};
		Timer timer = new Timer();
		System.out.println("执行开始时间"+new Date());
		timer.scheduleAtFixedRate(task, 60000, (60 * (60 * 1000)));
	}
}
