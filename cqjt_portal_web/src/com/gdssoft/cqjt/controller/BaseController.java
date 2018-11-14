package com.gdssoft.cqjt.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.JsonUtil;
import com.gdssoft.cqjt.pojo.LogCount;
import com.gdssoft.cqjt.service.LogCountService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

public class BaseController {
	
	protected transient final Log logger = LogFactory.getLog(getClass());
	@Resource(name = "logCountServiceImpl")
	private LogCountService logCountService;
	public int count; 
	/**
	 * ajaxForm返回提交结果
	 * @param response
	 * @param msg
	 */
	protected void writeMessage(HttpServletResponse response, String msg) {
		Map<String, String> jsonMsg = new HashMap<String, String>();
		jsonMsg.put("msg", msg);
		response.setContentType("text/html; charset=utf-8");
		try {
			response.getWriter().write(JsonUtil.toJson(jsonMsg));
		} catch (IOException e) {
			logger.error("get response Writer error!", e);
		}
	}
	protected void writeResult(HttpServletResponse response, Object obj) {
		response.setContentType("text/html; charset=utf-8");
		try {
			response.getWriter().write(JsonUtil.toJson(obj));
		} catch (IOException e) {
			logger.error("get response Writer error!", e);
		}
	}
	/**
	 * 获取开始日期
	 * @author F3201252 许健
	 * @param arg0
	 * @return
	 */
	protected Date getStartDate(String arg0) {
		Calendar calendar = Calendar.getInstance();
		//默认取当前减7天的日期时间为开始
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		Date start = calendar.getTime();
		
		if (!StringUtils.isBlank(arg0)) {
			arg0 += " 00:00:01";
			start = DateUtil.dateParse(arg0);
		}else{
			String startDate=DateUtil.dateFormat(start, "yyyy-MM-dd");
			startDate += " 00:00:01";
			start=DateUtil.parseDate(startDate);
		}
		return start;
	}
	/**
	 * 获取结束日期
	 * @author F3201252 许健
	 * @param arg0
	 * @return
	 */
	protected Date getEndDate(String arg0) {
		Calendar calendar = Calendar.getInstance();
		//默认取当前日期时间为结束 
		Date end = calendar.getTime();
		if (!StringUtils.isBlank(arg0)) {
			arg0 += " 23:59:59";
			end = DateUtil.dateParse(arg0);
		}else{
			String endData=DateUtil.dateFormat(end, "yyyy-MM-dd");
			endData += " 23:59:59";
			end=DateUtil.parseDate(endData);
		}
		return end;
	}
	
	/**
	 * @return the count
	 */
	public int getCount() {
		/* ==========当前访问人数========== */
		LogCount logCount=logCountService.getCount();
		count=logCount.getId();
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 将字符串转换为Date类
	 *
	 * @param binder
	 * @param request
	 */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtil.parseAllDate(text));
			}
		});

    }
}
