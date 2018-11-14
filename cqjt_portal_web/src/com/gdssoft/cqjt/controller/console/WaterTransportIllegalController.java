package com.gdssoft.cqjt.controller.console;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.pojo.ww.WaterTransportIllegal;
import com.gdssoft.cqjt.service.ww.WaterTransportIllegalService;
import com.gdssoft.cqjt.util.Page;
import com.gdssoft.cqjt.util.PaginationRelated;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公路水运工程建设质量安全违法违规行为信息公开 
 * @author WANGSHAN
 *
 */
@Controller
@RequestMapping("/console/waterTransport.xhtml")
public class WaterTransportIllegalController {

	@Autowired
	@Resource(name = "waterTransportIllegalServiceImpl")
	private WaterTransportIllegalService waterTransportIllegalServiceImpl;
	
	/**
	 * 列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "action=waterTransportIllegalManager")
	public String waterTransportIllegalManager(HttpServletRequest request) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		List<WaterTransportIllegal> llegals = waterTransportIllegalServiceImpl.SelectWaterTransportIllegal(map);
		
		request.setAttribute("llegals", llegals);
		return "console/watertransportIllegal/waterTransportIllegalManager";
	}
	
	/**
	 * 列表数据
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(params = "action=queryVideoNewsListByPageIndex")
	public Object getVideoNewsList(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		
		String endTime = request.getParameter("endTime");
		String startTime = request.getParameter("startTime");
		String pageSize = request.getParameter("pageSize");
		String pageIndex = request.getParameter("pageIndex");
		
		String ILLEGAL_COMPANY_NAME = request.getParameter("ILLEGAL_COMPANY_NAME");
		String SOCIOLOGY_CREDIT_CODE = request.getParameter("SOCIOLOGY_CREDIT_CODE");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("endTime", endTime);
		map.put("startTime", startTime);
		map.put("ILLEGAL_COMPANY_NAME", ILLEGAL_COMPANY_NAME);
		map.put("SOCIOLOGY_CREDIT_CODE", SOCIOLOGY_CREDIT_CODE);
		
		List<WaterTransportIllegal> illegalList = 
				waterTransportIllegalServiceImpl.SelectWaterTransportIllegal(map);
		Page page = PaginationRelated.listToPage(
				illegalList, Integer.valueOf(pageIndex), Integer.valueOf(pageSize));
		return page;
	}
	
	
	/**
	 * 新增/编辑页面跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "action=jumpWaterTransportIllegalEdit")
	public String jumpWaterTransportIllegalEdit(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		String REPORT_FORM_TYPE = request.getParameter("REPORT_FORM_TYPE");
		
		if (StringUtils.isNotEmpty(id)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("ID", id);
			List<WaterTransportIllegal> list = waterTransportIllegalServiceImpl.SelectWaterTransportIllegal(map);
			if (null != list && list.size() > 0) {
				request.setAttribute("waterTransportIllegal", list.get(0));
			}
		}
		request.setAttribute("REPORT_FORM_TYPE", REPORT_FORM_TYPE);
		if (null != REPORT_FORM_TYPE && "1".equals(REPORT_FORM_TYPE)) {
			return "console/watertransportIllegal/waterTransportIllegalEditForQY";
		} 
		return "console/watertransportIllegal/waterTransportIllegalEditForRY";
	}
		
		
	
	/** 
	 * 保存新增/编辑数据
	 * @param request
	 * @param illegal
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "action=waterTransportIllegalEdit")
	public Map<String, String> waterTransportIllegalEdit(HttpServletRequest request, WaterTransportIllegal illegal) {
		Map<String, String> map = new HashMap<String, String>();
		illegal.setCREATE_TIME(DateUtil.getFormatDateString(new Date()));
		illegal.setCREATE_USER(SystemContext.getUserNO());
		try {
			if (StringUtils.isNotEmpty(illegal.getID())) {
				waterTransportIllegalServiceImpl.UpdateWaterTransportIllegal(illegal);
			} else {
				waterTransportIllegalServiceImpl.InsertWaterTransportIllegal(illegal);
			}
			map.put("status", "0");
			map.put("msg", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "1");
			map.put("msg", "保存失败");
		}
		
		return map;
	}
	
	/** 
	 * 删除数据
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "action=waterTransportIllegalDelete")
	public Map<String, String> waterTransportIllegalDelete(HttpServletRequest request) {
		
		Map<String, String> map = new HashMap<String, String>();
		WaterTransportIllegal illegal = new WaterTransportIllegal();
		String Id = request.getParameter("Id");
		illegal.setIS_DEL("1");
		illegal.setID(Id);
		try{
			waterTransportIllegalServiceImpl.DeleteWaterTransportIllegal(illegal);
			map.put("msg", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "删除失败");
		}
		
		return map;
	}
}
